package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    TaskType findByType(String type);
}
