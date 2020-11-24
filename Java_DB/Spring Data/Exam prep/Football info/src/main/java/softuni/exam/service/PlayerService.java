package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PlayerService {
    String importPlayers() throws IOException;

    boolean areImported();

    String readPlayersJsonFile() throws IOException;

    String exportPlayersWhereSalaryBiggerThan();

    String exportPlayersInATeam();
}
