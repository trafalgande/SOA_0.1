package se.ifmo.pepe.soa1.service;

import dto.music_band.request.CreateMusicBandRequest;
import dto.music_band.request.UpdateMusicBandRequest;
import dto.music_band.response.MusicBandView;
import org.springframework.data.domain.Pageable;

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

    MusicBandView removeParticipantFromBand(Long bandId);
    MusicBandView addSingleToBand(Long bandId, String single);
}
