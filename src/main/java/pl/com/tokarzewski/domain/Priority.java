package pl.com.tokarzewski.domain;

import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.Entity;

@Entity
public class Priority extends AbstractDomainObject {
    private String priority;
    private int points;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
