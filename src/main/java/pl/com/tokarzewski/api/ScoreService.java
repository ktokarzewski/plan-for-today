package pl.com.tokarzewski.api;

import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;
import java.util.List;

public interface ScoreService {
    void updateUserScore(User user, int points);

    Score getUserScore(User user);

    Collection<Score> findAll();

    void save(Collection<Score> scores);
}
