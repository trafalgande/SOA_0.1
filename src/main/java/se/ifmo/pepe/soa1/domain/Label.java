package se.ifmo.pepe.soa1.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Table(name = "labels")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Label {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private long id;

    @Positive
    private double sales;
}
