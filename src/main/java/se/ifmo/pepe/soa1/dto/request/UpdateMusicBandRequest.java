package se.ifmo.pepe.soa1.dto.request;

import lombok.Data;
import se.ifmo.pepe.soa1.domain.Coordinates;
import se.ifmo.pepe.soa1.domain.Label;
import se.ifmo.pepe.soa1.domain.MusicGenre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateMusicBandRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Coordinates coordinates;

    @Positive
    @NotNull
    private long numberOfParticipants;

    @NotNull
    private String description;

    @NotNull
    private MusicGenre genre;

    @NotNull
    private Label label;
}
