package org.bsu.web.lab8.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "select o from Order o"),
        @NamedQuery(name = "Order.findByConfirmedStatus",
                query = "select o from Order o where o.isConfirmed = :isConfirmed"),
        @NamedQuery(name = "Order.findByClientId",
                query = "select o from Order o where o.client.id = :id"),
        @NamedQuery(name = "Order.confirmOrder",
                query = "update Order o set o.isConfirmed = true where o.id = :id"),
        @NamedQuery(name = "Order.getCost",
                query = "select sum(op.amount * p.cost) " +
                " from Order o left join OrderPosition op" +
                " on o.id = op.orderID left join Position p" +
                " on op.positionID = p.id" +
                " where o.id = :id")
})
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderPosition> positions;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;
    @Column(name = "is_payed", nullable = false)
    private boolean isPayed;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    public Order() {
        positions = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", \n\torderDate=" + orderDate +
                ", \n\tisConfirmed=" + isConfirmed +
                ", \n\tisPayed=" + isPayed +
                ", \n\tpositions=" + positions +
                ", \n\tclient=" + client.getId() +
                ", \n\tadmin=" + admin.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isConfirmed == order.isConfirmed && isPayed == order.isPayed
                && id.equals(order.id) && orderDate.equals(order.orderDate)
                && Objects.equals(positions, order.positions)
                && client.equals(order.client)
                && admin.equals(order.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, isConfirmed,
                isPayed, positions, client, admin);
    }

    public List<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderPosition> positions) {
        this.positions = positions;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
