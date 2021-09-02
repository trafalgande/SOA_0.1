package se.ifmo.pepe.soa1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.ifmo.pepe.soa1.domain.Coordinates;

public interface CoordinatesRepository extends CrudRepository<Coordinates, Long> {
}
