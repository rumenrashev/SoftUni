package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.json.PlayerJsonDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.constants.GlobalConstants.*;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;

    @Autowired
    public PlayerServiceImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, FileUtil fileUtil,
                             TeamRepository teamRepository, PlayerRepository playerRepository, PictureRepository pictureRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(this.gson.fromJson(readPlayersJsonFile(), PlayerJsonDto[].class))
                .forEach(pdto -> {
                    Optional<Picture> picture = this.pictureRepository.findByUrl(pdto.getPicture().getUrl());
                    Optional<Team> team = this.teamRepository.findByName(pdto.getTeam().getName());
                    if (picture.isPresent() && team.isPresent() && this.validatorUtil.isValid(pdto)) {
                        Player player = this.modelMapper.map(pdto, Player.class);
                        player.setPicture(picture.get());
                        player.setTeam(team.get());
                        this.playerRepository.saveAndFlush(player);
                        sb.append(String.format(PLAYER_IMPORTED, player.getFirstName(), player.getLastName()));
                    } else {
                        sb.append(INVALID_PLAYER);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return this.fileUtil.readFile(PLAYERS_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        final BigDecimal salary = new BigDecimal("1000");
        return this.playerRepository.getAllBySalaryHigherThanOrderBySalaryDesc(salary)
                .stream()
                .map(p -> String.format("Player name: %s %s \n" +
                                "\tNumber: %d\n" +
                                "\tSalary: %s\n" +
                                "\tTeam: %s",
                        p.getFirstName(),
                        p.getLastName(),
                        p.getNumber(),
                        p.getSalary().toString(),
                        p.getTeam().getName()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String exportPlayersInATeam() {
        final String teamName = "North Hub";
        StringBuilder sb = new StringBuilder();


        return "Team: " +
                teamName
                + System.lineSeparator()
                + this.playerRepository.getByTeamNameOrderById(teamName)
                .stream()
                .map(p -> String.format("\tPlayer name: %s %s - %s Number: %d",
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPosition().name(),
                        p.getNumber()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
