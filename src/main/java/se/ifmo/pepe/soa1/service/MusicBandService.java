package se.ifmo.pepe.soa1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.ifmo.pepe.soa1.dto.request.CreateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.request.UpdateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.response.MusicBandView;

import java.util.List;

public interface MusicBandService {

    MusicBandView createMusicBand(CreateMusicBandRequest request);
    MusicBandView readMusicBand(Long id);
    MusicBandView updateMusicBand(UpdateMusicBandRequest request, Long id);
    void deleteMusicBand(Long id);

    List<MusicBandView> readAllMusicBands(Pageable pageable);


    Long getMusicBandsAmountByNumberOfParticipants(Long amount);
    List<MusicBandView> getMusicBandsByDescriptionLessThen(Integer value, Pageable pageable);
    List<MusicBandView> getMusicBandsBySalesLessThen(Double value, Pageable pageable);

}
