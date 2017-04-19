package pl.com.tokarzewski.domain;

import org.hibernate.validator.constraints.NotEmpty;
import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Task extends AbstractDomainObject {

    @ManyToOne
    private User owner;

    @NotEmpty
    private String description;

    @OneToOne
    private Priority priority;

    @OneToOne
    private TaskType type;

    private Date startDate;

    private boolean done;

    private boolean expired;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
