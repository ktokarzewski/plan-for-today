package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.User;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByOwner(User user);
}
