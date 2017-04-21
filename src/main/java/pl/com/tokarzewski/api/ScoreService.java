package pl.com.tokarzewski.api;

import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface ScoreService extends CRUDService<Score>{
    void updateUserScore(User user, int points);

    Score getUserScore(User user);

    void updateAll(Collection<Score> scores);

    @Transactional
    void deleteUserScore(User user);

    void increaseMaxForToday(User owner, int points);

    double getUserProgress(User user);

    void decreaseMaxForToday(User owner, int points);
}
