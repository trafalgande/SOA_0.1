package se.ifmo.pepe.soa1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;

@Table
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue
    private Long id;
    @DecimalMax("142")
    private double x;
    @DecimalMax("142")
    private double y;
}
