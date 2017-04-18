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
    public void save(Collection<Score> scores) {
        scoreRepository.save(scores);
    }

    @Autowired
    public void setScoreRepository(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
}