package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.mitch.config.DataKeeper;
import ru.mitch.constant.MessageConstant;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.tournament.*;
import ru.mitch.helper.ExtractorContentFile;
import ru.mitch.mapping.TournamentMapper;
import ru.mitch.model.Player;
import ru.mitch.model.Status;
import ru.mitch.model.Tournament;
import ru.mitch.model.TournamentParticipant;
import ru.mitch.repository.PlayerRepository;
import ru.mitch.repository.TournamentParticipantRepository;
import ru.mitch.repository.TournamentRepository;
import ru.mitch.service.TournamentService;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static ru.mitch.dto.StatusCodeEnum.ACTIVE;
import static ru.mitch.dto.StatusCodeEnum.REGISTRATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final PlayerRepository playerRepository;

    private final TournamentMapper tournamentMapper;
    private final TournamentRepository tournamentRepository;
    private final TournamentParticipantRepository tournamentParticipantRepository;

    private final DataKeeper dataKeeper;

    @Override
    @Transactional
    public void create(TournamentDto request) {
        Tournament entity = tournamentMapper.createTournament(request);
        entity.setStatus(dataKeeper.getStatuses().get(request.getStatusCode()));
        Tournament tournament = tournamentRepository.save(entity);

        List<TournamentParticipant> participants = request.getParticipants().stream()
                .filter(TournamentParticipantDto::getStatus)
                .map(participantDto -> {
                    participantDto = tournamentMapper.handleParticipant(participantDto);
                    TournamentParticipant participant = tournamentMapper.createParticipant(participantDto);
                    participant.setTournament(tournament);
                    return participant;
                })
                .toList();
        tournamentParticipantRepository.saveAll(participants);
    }

    @Override
    public void update(TournamentDto request) {
        Tournament entity = tournamentMapper.toTournament(request);
        entity.setStatus(dataKeeper.getStatuses().get(request.getStatusCode()));
        Tournament tournament = tournamentRepository.save(entity);

        List<TournamentParticipant> participants = request.getParticipants().stream()
                .filter(TournamentParticipantDto::getStatus)
                .map(participantDto -> {
                    participantDto = tournamentMapper.handleParticipant(participantDto);
                    TournamentParticipant participant = tournamentMapper.toParticipant(participantDto);
                    participant.setTournament(tournament);
                    return participant;
                })
                .toList();

        List<TournamentParticipant> deleteParticipants = request.getParticipants().stream()
                .filter(participantDto -> !participantDto.getStatus())
                .map(tournamentMapper::toParticipant)
                .toList();

        tournamentParticipantRepository.saveAll(participants);
        tournamentParticipantRepository.deleteAll(deleteParticipants);
    }

    @Override
    public TournamentListDto getTournaments(RequestPageableDto request) {
        Integer total = tournamentRepository.countAll();
        if (total == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("eventDate").descending());
        List<TournamentListDataDto> rows = tournamentRepository.findAll(pageable)
                .get()
                .map(tournamentMapper::toDto)
                .toList();
        return new TournamentListDto(total, rows);
    }

    @Override
    public String getTitle() {
        String[] titles = ExtractorContentFile.getTemplateMessage(MessageConstant.TITLES).split(",");
        int index = new Random().nextInt(titles.length);
        return titles[index];
    }

    @Override
    public TournamentDto getTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        tournament.setEventDate(tournament.getEventDate().truncatedTo(ChronoUnit.MINUTES));
        return tournamentMapper.toTournamentDto(tournament);
    }

    @Override
    public void registerForTournament(RegistrationDto registrationDto) {
        Status registrationStatus = dataKeeper.getStatuses().get(REGISTRATION.name());
        Status activePlayer = dataKeeper.getStatuses().get(ACTIVE.name());
        Tournament tournament = tournamentRepository.findById(registrationDto.getTournamentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        if (!registrationStatus.getCode().equals(tournament.getStatus().getCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Регистрация закрыта");
        }

        Player player = playerRepository.findById(registrationDto.getPlayerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        if (!activePlayer.getCode().equals(player.getStatus().getCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Игрок не активен");
        }

        if (registrationDto.getIsRegistered()) {
            TournamentParticipant participant = tournamentParticipantRepository.findByTournamentAndPlayer(tournament, player);
            tournamentParticipantRepository.delete(participant);
        } else {
            if (tournamentParticipantRepository.existsByTournamentAndPlayer(tournament, player)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ты уже зарегистрирован на этом турнире");
            }
            tournamentParticipantRepository.save(tournamentMapper.newParticipant(tournament, player));
        }
    }

}
