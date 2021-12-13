package se.ifmo.pepe.soa1.service.impl;

import dto.music_band.request.CreateMusicBandRequest;
import dto.music_band.request.UpdateMusicBandRequest;
import dto.music_band.response.MusicBandView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.pepe.soa1.domain.MusicBand;
import se.ifmo.pepe.soa1.mapper.MusicBandRequestMapper;
import se.ifmo.pepe.soa1.mapper.MusicBandViewMapper;
import se.ifmo.pepe.soa1.repository.CoordinatesRepository;
import se.ifmo.pepe.soa1.repository.LabelRepository;
import se.ifmo.pepe.soa1.repository.MusicBandRepository;
import se.ifmo.pepe.soa1.service.MusicBandService;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MusicBandServiceImpl implements MusicBandService {

    private final MusicBandRepository musicBandRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LabelRepository labelRepository;

    private final MusicBandRequestMapper musicBandRequestMapper;
    private final MusicBandViewMapper musicBandViewMapper;

    @Override
    public MusicBandView createMusicBand(CreateMusicBandRequest request) {
        MusicBand musicBand = musicBandRequestMapper.create(request);
        return musicBandViewMapper.toMusicBandView(carefulSave(musicBand));
    }

    @Override
    @Transactional(readOnly = true)
    public MusicBandView readMusicBand(Long id) {
        return musicBandViewMapper.toMusicBandView(getMusicBandById(id));
    }

    @Override
    public MusicBandView updateMusicBand(UpdateMusicBandRequest request, Long id) {
        MusicBand previousMusicBand = getMusicBandById(id);
        musicBandRequestMapper.update(request, previousMusicBand);
        return musicBandViewMapper.toMusicBandView(carefulSave(previousMusicBand));
    }

    @Override
    public void deleteMusicBand(Long id) {
        if (!musicBandRepository.existsById(id))
            throw new NoSuchElementException(format("There's no such element with ID:[%s]", id));
        musicBandRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getMusicBandsAmountByNumberOfParticipants(Long amount) {
        return musicBandRepository.countMusicBandsByNumberOfParticipantsLessThan(amount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicBandView> getMusicBandsByDescriptionLessThen(Integer value, Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAllByDescriptionLengthShorterThan(value, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicBandView> getMusicBandsBySalesLessThen(Double value, Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAllByLabelSalesGreaterThan(value, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicBandView> readAllMusicBands(Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAll(pageable));
    }

    @Override
    public MusicBandView removeParticipantFromBand(Long bandId) {
        MusicBand musicBand = getMusicBandById(bandId);
        if (musicBand.getNumberOfParticipants() > 0) {
            musicBand.setNumberOfParticipants(musicBand.getNumberOfParticipants() - 1);
        } else {
            throw new RuntimeException(format("Can't remove participants from music band with ID:[%d]", bandId));
        }
        return musicBandViewMapper.toMusicBandView(carefulSave(musicBand));
    }

    @Override
    public MusicBandView addSingleToBand(Long bandId, String single) {
        MusicBand musicBand = getMusicBandById(bandId);
        musicBand.getSingles().add(single);
        return musicBandViewMapper.toMusicBandView(carefulSave(musicBand));
    }

    private MusicBand getMusicBandById(long id) {
        return musicBandRepository.findById(id)
                                  .orElseThrow(() -> new NoSuchElementException(format("There's no such element with ID:[%s]", id)));
    }

    private MusicBand carefulSave(MusicBand musicBand) {
        coordinatesRepository.save(musicBand.getCoordinates());
        labelRepository.save(musicBand.getLabel());
        return musicBandRepository.save(musicBand);
    }
}

