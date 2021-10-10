package se.ifmo.pepe.soa1.dto.response;

import lombok.Data;
import se.ifmo.pepe.soa1.domain.MusicGenre;
import se.ifmo.pepe.soa1.dto.CoordinatesDTO;
import se.ifmo.pepe.soa1.dto.LabelDTO;

@Data
public class MusicBandView {
    private String id;
    private String name;
    private CoordinatesDTO coordinates;
    private Long numberOfParticipants;
    private String description;
    private MusicGenre genre;
    private LabelDTO label;
}
