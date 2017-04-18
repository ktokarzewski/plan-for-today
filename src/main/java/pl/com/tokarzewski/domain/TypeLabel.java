package pl.com.tokarzewski.domain;


import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TypeLabel extends AbstractDomainObject {

    private String label;

    @ManyToOne
    private TaskType type;

    private String language;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
