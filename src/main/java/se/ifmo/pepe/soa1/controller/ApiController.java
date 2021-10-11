package se.ifmo.pepe.soa1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.pepe.soa1.dto.request.CreateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.request.UpdateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.response.MusicBandView;
import se.ifmo.pepe.soa1.service.MusicBandService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ApiController {
    private final MusicBandService musicBandService;

    @GetMapping("/music-bands")
    public Page<MusicBandView> getAll(Pageable pageable) {
        return new PageImpl<>(musicBandService.readAllMusicBands(pageable));
    }

    @GetMapping(value = "/music-bands", params = {"by-description-length"})
    public Page<MusicBandView> getMusicBandsByDescriptionLength(@RequestParam(name = "by-description-length") Integer length,
                                                                                Pageable pageable) {
        return new PageImpl<>(musicBandService.getMusicBandsByDescriptionLessThen(length, pageable));
    }

    @GetMapping(value = "/music-bands", params = {"by-label-value"})
    public Page<MusicBandView> getMusicBandsByLabelValue(@RequestParam(name = "by-label-value") Double value,
                                                                         Pageable pageable) {
        return new PageImpl<>(musicBandService.getMusicBandsBySalesGreaterThen(value, pageable));
    }

    @GetMapping(value = "/music-bands/count", params = {"by-number-of-participants"})
    public Long countMusicBandsByAmountOfParticipants(@RequestParam(name = "by-number-of-participants") Long amount) {
        return musicBandService.getMusicBandsAmountByNumberOfParticipants(amount);
    }

    @GetMapping("/music-band/{id}")
    public MusicBandView getMusicBandById(@PathVariable("id") Long id) {
        return musicBandService.readMusicBand(id);
    }

    @PostMapping("/music-band")
    public ResponseEntity<MusicBandView> createMusicBand(@RequestBody @Valid CreateMusicBandRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(musicBandService.createMusicBand(request));
    }

    @PutMapping("/music-band/{id}")
    public MusicBandView updateMusicBand(@PathVariable("id") Long id,
                                                         @RequestBody @Valid UpdateMusicBandRequest request) {
        return musicBandService.updateMusicBand(request, id);
    }

    @DeleteMapping("/music-band/{id}")
    public ResponseEntity<Object> deleteMusicBand(@PathVariable("id") Long id) {
        musicBandService.deleteMusicBand(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
