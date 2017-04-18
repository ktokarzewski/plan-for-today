package pl.com.tokarzewski.domain;

import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.Entity;

@Entity
public class Privilege extends AbstractDomainObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
