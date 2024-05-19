package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    protected String id;
    protected String name;
    protected double price;
    protected String description;
    @Column(name = "on_special")
    protected boolean onSpecial;
    @ManyToMany(mappedBy = "items")
    private Set<Ingredient> ingredients;

    public Item(String id, String name, double price, String description, boolean onSpecial) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.onSpecial = onSpecial;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }
}