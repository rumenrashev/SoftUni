package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.TeamXmlDto;
import softuni.exam.domain.dtos.xml.TeamsRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class TeamServiceImpl implements TeamService {

    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final TeamRepository teamRepository;
    private final FileUtil fileUtil;
    private final PictureRepository pictureRepository;

    @Autowired
    public TeamServiceImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser,
                           TeamRepository teamRepository, FileUtil fileUtil, PictureRepository pictureRepository) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.teamRepository = teamRepository;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        TeamsRootDto teamsRootDto = this.xmlParser.fromXML(TeamsRootDto.class, TEAMS_PATH);
        for (TeamXmlDto teamDto : teamsRootDto.getTeamDtos()) {
            Optional<Picture> byUrl = this.pictureRepository.findByUrl(teamDto.getPictureXmlDto().getUrl());
            if (byUrl.isPresent() && this.validatorUtil.isValid(teamDto)){
                Team team = this.modelMapper.map(teamDto, Team.class);
                team.setPicture(byUrl.get());
                this.teamRepository.saveAndFlush(team);
                sb.append(String.format(TEAM_IMPORTED,team.getName()));
            }else {
                sb.append(INVALID_TEAM);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return this.fileUtil.readFile(TEAMS_PATH);
    }
}
