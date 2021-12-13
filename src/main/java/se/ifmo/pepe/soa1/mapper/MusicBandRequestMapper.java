package se.ifmo.pepe.soa1.mapper;

import dto.music_band.request.CreateMusicBandRequest;
import dto.music_band.request.UpdateMusicBandRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import se.ifmo.pepe.soa1.domain.MusicBand;

@Component
@Mapper(componentModel = "spring")
public abstract class MusicBandRequestMapper {

    public abstract MusicBand create(CreateMusicBandRequest request);
    public abstract void update(UpdateMusicBandRequest request, @MappingTarget MusicBand musicBand);
}
