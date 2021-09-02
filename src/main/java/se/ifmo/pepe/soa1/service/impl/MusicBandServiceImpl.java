package se.ifmo.pepe.soa1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.pepe.soa1.domain.MusicBand;
import se.ifmo.pepe.soa1.dto.mapper.MusicBandRequestMapper;
import se.ifmo.pepe.soa1.dto.mapper.MusicBandViewMapper;
import se.ifmo.pepe.soa1.dto.request.CreateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.request.UpdateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.response.MusicBandView;
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
public class MusicBandServiceImpl implements MusicBandService {

    private final MusicBandRepository musicBandRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LabelRepository labelRepository;

    private final MusicBandRequestMapper musicBandRequestMapper;
    private final MusicBandViewMapper musicBandViewMapper;

    @Override
    @Transactional
    public MusicBandView createMusicBand(CreateMusicBandRequest request) {
        MusicBand musicBand = musicBandRequestMapper.create(request);
        return musicBandViewMapper.toMusicBandView(carefulSave(musicBand));
    }

    @Override
    @Transactional
    public MusicBandView readMusicBand(Long id) {
        return musicBandViewMapper
                .toMusicBandView(musicBandRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException(format("There's no such element with ID:[%s]", id))));
    }

    @Override
    @Transactional
    public MusicBandView updateMusicBand(UpdateMusicBandRequest request, Long id) {
        MusicBand previousMusicBand = musicBandRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("There's no such element with ID:[%s]", id)));
        musicBandRequestMapper.update(request, previousMusicBand);
        return musicBandViewMapper.toMusicBandView(carefulSave(previousMusicBand));
    }

    @Override
    @Transactional
    public void deleteMusicBand(Long id) {
        if (!musicBandRepository.existsById(id))
            throw new NoSuchElementException(format("There's no such element with ID:[%s]", id));
        musicBandRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Long getMusicBandsAmountByNumberOfParticipants(Long amount) {
        return musicBandRepository.countMusicBandsByNumberOfParticipantsLessThan(amount);
    }

    @Override
    @Transactional
    public List<MusicBandView> getMusicBandsByDescriptionLessThen(Integer value, Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAllByDescriptionLengthShorterThan(value, pageable));
    }

    @Override
    @Transactional
    public List<MusicBandView> getMusicBandsBySalesLessThen(Double value, Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAllByLabelSalesGreaterThan(value, pageable));
    }

    @Override
    @Transactional
    public List<MusicBandView> readAllMusicBands(Pageable pageable) {
        return musicBandViewMapper
                .toMusicBandPagesView(musicBandRepository.findAll(pageable));
    }

    @Transactional
    MusicBand carefulSave(MusicBand musicBand) {
        coordinatesRepository.save(musicBand.getCoordinates());
        labelRepository.save(musicBand.getLabel());
        return musicBandRepository.save(musicBand);
    }
}

