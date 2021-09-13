package se.ifmo.pepe.soa1.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.pepe.soa1.domain.Role;
import se.ifmo.pepe.soa1.dto.request.CreateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.request.UpdateMusicBandRequest;
import se.ifmo.pepe.soa1.dto.response.MusicBandView;
import se.ifmo.pepe.soa1.service.MusicBandService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@RolesAllowed(Role.USER)
public class ApiController {
    private final MusicBandService musicBandService;

    @GetMapping("/music-bands")
    public ResponseEntity<List<MusicBandView>> getAll(Pageable pageable) {
        return ResponseEntity
                .ok(musicBandService.readAllMusicBands(pageable));
    }

    @GetMapping(value = "/music-bands", params = {"by-description-length"})
    public ResponseEntity<List<MusicBandView>> getMusicBandsByDescriptionLength(@RequestParam(name = "by-description-length") Integer length,
                                                                                Pageable pageable) {
        return ResponseEntity
                .ok(musicBandService.getMusicBandsByDescriptionLessThen(length, pageable));
    }

    @GetMapping(value = "/music-bands", params = {"by-label-value"})
    public ResponseEntity<List<MusicBandView>> getMusicBandsByLabelValue(@RequestParam(name = "by-label-value") Double value,
                                                                         Pageable pageable) {
        return ResponseEntity
                .ok(musicBandService.getMusicBandsBySalesLessThen(value, pageable));
    }

    @GetMapping(value = "/music-bands/count", params = {"by-number-of-participants"})
    public ResponseEntity<Long> countMusicBandsByAmountOfParticipants(@RequestParam(name = "by-number-of-participants") Long amount) {
        return ResponseEntity
                .ok(musicBandService.getMusicBandsAmountByNumberOfParticipants(amount));
    }

    @GetMapping("/music-band/{id}")
    public ResponseEntity<MusicBandView> getMusicBandById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(musicBandService.readMusicBand(id));
    }

    @PostMapping("/music-band")
    public ResponseEntity<MusicBandView> createMusicBand(@RequestBody @Valid CreateMusicBandRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(musicBandService.createMusicBand(request));
    }

    @PatchMapping("/music-band/{id}")
    public ResponseEntity<MusicBandView> updateMusicBand(@PathVariable("id") Long id,
                                                         @RequestBody @Valid UpdateMusicBandRequest request) {
        return ResponseEntity
                .ok(musicBandService.updateMusicBand(request, id));
    }

    @DeleteMapping("/music-band/{id}")
    public ResponseEntity<Object> deleteMusicBand(@PathVariable("id") Long id) {
        musicBandService.deleteMusicBand(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
