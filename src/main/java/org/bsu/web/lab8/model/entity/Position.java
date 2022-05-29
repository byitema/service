package org.bsu.web.lab8.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Position.findAll",
                query = "select p from Position p")
})
@Table(name = "positions")
@Entity
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "cost", nullable = false, precision = 6, scale = 2)
    private BigDecimal cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return position.cost.equals(cost) && id.equals(position.id) && itemName.equals(position.itemName);
    }

    public Position() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, cost);
    }
}
