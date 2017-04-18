package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
