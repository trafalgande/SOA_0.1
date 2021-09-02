package se.ifmo.pepe.soa1.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import se.ifmo.pepe.soa1.domain.MusicBand;
import se.ifmo.pepe.soa1.dto.CoordinatesDTO;
import se.ifmo.pepe.soa1.dto.LabelDTO;
import se.ifmo.pepe.soa1.dto.response.MusicBandView;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class MusicBandViewMapper {
    public abstract MusicBandView toMusicBandView(MusicBand musicBand);
    protected void afterToMusicBandViewMapping(@MappingTarget MusicBandView musicBandView, MusicBand musicBand) {
        musicBandView.setCoordinates(
                CoordinatesDTO.builder()
                        .x(musicBand.getCoordinates().getX())
                        .y(musicBand.getCoordinates().getY())
                        .build()
        );

        musicBandView.setLabel(
                LabelDTO.builder()
                        .sales(musicBand.getLabel().getSales())
                        .build()
        );
    }


    public abstract List<MusicBandView> toMusicBandPagesView(Page<MusicBand> musicBands);
}
