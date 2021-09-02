package se.ifmo.pepe.soa1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDTO {
    private Double x;
    private Double y;
}
