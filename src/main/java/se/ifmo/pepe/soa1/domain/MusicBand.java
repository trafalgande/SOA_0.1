package se.ifmo.pepe.soa1.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "music_bands")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MusicBand {
    @Id
    @NotNull
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "band_name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "coordinates", nullable = false)
    private Coordinates coordinates;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "number_of_participants", nullable = false)
    private Long numberOfParticipants;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "genre", nullable = false)
    private MusicGenre genre;

    @OneToOne
    @JoinColumn(name = "label", nullable = false)
    private Label label;

    @ElementCollection
    @Column(name = "singles")
    private List<String> singles;
}
