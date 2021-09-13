package se.ifmo.pepe.soa1.dto.request;

import lombok.Data;
import se.ifmo.pepe.soa1.domain.Coordinates;
import se.ifmo.pepe.soa1.domain.Label;
import se.ifmo.pepe.soa1.domain.MusicGenre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateMusicBandRequest {
    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Coordinates can not be null")
    private Coordinates coordinates;

    @Positive(message = "Number of participants must exceed 0")
    @NotNull(message = "Number of participants can not be null")
    private long numberOfParticipants;

    @NotNull(message = "Description can not be null")
    private String description;

    @NotNull(message = "Music Genre can not be null")
    private MusicGenre genre;

    @NotNull(message = "Label can not be null")
    private Label label;
}
