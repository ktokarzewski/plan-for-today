package pl.com.tokarzewski.domain;

import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Score extends AbstractDomainObject {
    @OneToOne
    private User owner;
    private int dailyScore;
    private int totalScore;
    private int maxForToday;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getMaxForToday() {
        return maxForToday;
    }

    public void setMaxForToday(int maxForToday) {
        this.maxForToday = maxForToday;
    }
}
