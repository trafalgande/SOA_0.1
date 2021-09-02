package se.ifmo.pepe.soa1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.ifmo.pepe.soa1.domain.MusicBand;

public interface MusicBandRepository extends JpaRepository<MusicBand, Long> {
    Long countMusicBandsByNumberOfParticipantsLessThan(Long amount);

    @Query("select m from MusicBand m where length(m.description) < ?1")
    Page<MusicBand> findAllByDescriptionLengthShorterThan(Integer length, Pageable pageable);

    @Query("select m from MusicBand m where m.label.sales > ?1")
    Page<MusicBand> findAllByLabelSalesGreaterThan(Double value, Pageable pageable);
}
