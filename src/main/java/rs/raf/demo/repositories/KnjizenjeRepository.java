package rs.raf.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Knjizenje;

@Repository
public interface KnjizenjeRepository extends JpaRepository<Knjizenje, Long> {
}
