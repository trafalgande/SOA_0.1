package se.ifmo.pepe.soa1.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.pepe.soa1.domain.MusicBand;
import se.ifmo.pepe.soa1.dto.request.CreateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.request.UpdateMusicBandRequest;

@Component
@Mapper(componentModel = "spring")
public abstract class MusicBandRequestMapper {

    public abstract MusicBand create(CreateMusicBandRequest request);
    public abstract void update(UpdateMusicBandRequest request, @MappingTarget MusicBand musicBand);
}
