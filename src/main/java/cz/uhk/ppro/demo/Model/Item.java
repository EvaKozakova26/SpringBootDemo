package cz.uhk.ppro.demo.Model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Indexed
@Table(name = "item", schema = "demo")
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private Timestamp createdAt;

    @Column
    private int count;

    @Column
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}