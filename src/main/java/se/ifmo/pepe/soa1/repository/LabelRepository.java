package se.ifmo.pepe.soa1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.ifmo.pepe.soa1.domain.Label;

public interface LabelRepository extends CrudRepository<Label, Long> {
}
