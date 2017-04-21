package pl.com.tokarzewski.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.ScoreService;
import pl.com.tokarzewski.dao.ScoreRepository;
import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.User;

import javax.transaction.Transactional;
import java.util.Collection;

@Profile("database")
@Service
public class ScoreServiceImpl implements ScoreService {

    private ScoreRepository scoreRepository;

    @Transactional
    @Override
    public void updateUserScore(User user, int points) {
        Score userScore = getUserScore(user);

        userScore.setDailyScore(userScore.getDailyScore() + points);
        userScore.setTotalScore(userScore.getTotalScore() + points);

        scoreRepository.save(userScore);
    }

    @Override
    public Score getUserScore(User user) {
        Score userScore = scoreRepository.findByOwner(user);
        if (userScore == null) {
            userScore = new Score();
            userScore.setOwner(user);
        }
        return userScore;
    }

    @Override
    public Collection<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score getById(long id) {
        return scoreRepository.findOne(id);
    }

    @Override
    public void updateAll(Collection<Score> scores) {
        scoreRepository.save(scores);
    }

    @Override
    public Score create(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score update(Score score) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteUserScore(User user) {
        Score userScore = getUserScore(user);
        scoreRepository.delete(userScore);
    }

    @Override
    public void increaseMaxForToday(User owner, int points) {
        Score userScore = getUserScore(owner);
        userScore.setMaxForToday(userScore.getMaxForToday() + points);

        scoreRepository.save(userScore);
    }


    @Override
    public double getUserProgress(User user) {
        Score userScore = scoreRepository.findByOwner(user);
        double dailyScore = userScore.getDailyScore();
        double maxForToday = userScore.getMaxForToday();
        return Math.floor(dailyScore / maxForToday * 100D);
    }

    @Override
    public void decreaseMaxForToday(User owner, int points) {
        Score userScore = getUserScore(owner);
        userScore.setMaxForToday(userScore.getMaxForToday() - points);
        scoreRepository.save(userScore);
    }

    @Autowired
    public void setScoreRepository(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
}
