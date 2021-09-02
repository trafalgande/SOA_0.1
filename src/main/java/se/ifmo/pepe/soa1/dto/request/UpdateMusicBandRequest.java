package se.ifmo.pepe.soa1.dto.request;

import lombok.Data;
import se.ifmo.pepe.soa1.domain.Coordinates;
import se.ifmo.pepe.soa1.domain.Label;
import se.ifmo.pepe.soa1.domain.MusicGenre;

@Data
public class UpdateMusicBandRequest {
    private String name;
    private Coordinates coordinates;
    private Long numberOfParticipants;
    private String description;
    private MusicGenre genre;
    private Label label;
}
