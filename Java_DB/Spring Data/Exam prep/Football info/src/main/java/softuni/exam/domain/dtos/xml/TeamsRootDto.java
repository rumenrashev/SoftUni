package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.NONE)
public class TeamsRootDto {

    @XmlElement(name = "team")
    private List<TeamXmlDto> teamDtos;

    public TeamsRootDto() {
    }

    public List<TeamXmlDto> getTeamDtos() {
        return teamDtos;
    }

    public void setTeamDtos(List<TeamXmlDto> teamDtos) {
        this.teamDtos = teamDtos;
    }
}
