package se.ifmo.pepe.soa1.mapper;

import dto.music_band.CoordinatesDto;
import dto.music_band.LabelDto;
import dto.music_band.response.MusicBandView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import se.ifmo.pepe.soa1.domain.MusicBand;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class MusicBandViewMapper {
    public abstract MusicBandView toMusicBandView(MusicBand musicBand);

    protected void afterToMusicBandViewMapping(@MappingTarget MusicBandView musicBandView, MusicBand musicBand) {
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setX(musicBand.getCoordinates().getX());
        coordinatesDto.setY(musicBand.getCoordinates().getY());
        musicBandView.setCoordinates(coordinatesDto);

        musicBandView.setLabel(new LabelDto(musicBand.getLabel().getSales()));
        musicBandView.setSingles(musicBand.getSingles());
    }


    public abstract List<MusicBandView> toMusicBandPagesView(Page<MusicBand> musicBands);
}
