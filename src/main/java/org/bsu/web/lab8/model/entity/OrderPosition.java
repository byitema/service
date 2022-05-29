package org.bsu.web.lab8.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "orders_positions")
@IdClass(OrderPosition.OrderPositionPK.class)
public class OrderPosition implements Serializable {

    @Id
    @Column(name = "order_id", nullable = false)
    Integer orderID;

    @Id
    @Column(name = "position_id", nullable = false)
    Integer positionID;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @ManyToOne
    @MapsId("positionID")
    @JoinColumn(name = "position_id", nullable = false)
    Position position;

    @Column(name = "amount", nullable = false)
    int amount;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getPositionID() {
        return positionID;
    }

    public void setPositionID(Integer positionID) {
        this.positionID = positionID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        orderID = order.getId();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        positionID = position.getId();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return amount == that.amount && orderID.equals(that.orderID) && positionID.equals(that.positionID) && Objects.equals(order, that.order) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, positionID, order, position, amount);
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "orderID=" + orderID +
                ", positionID=" + positionID +
//                ", order=" + order +
//                ", position=" + position +
                ", amount=" + amount +
                '}';
    }

    public static class OrderPositionPK implements Serializable {

        private Integer orderID;
        private Integer positionID;

        public OrderPositionPK() {
        }

        public Integer getOrderID() {
            return orderID;
        }

        public void setOrderID(Integer orderID) {
            this.orderID = orderID;
        }

        public Integer getPositionID() {
            return positionID;
        }

        public void setPositionID(Integer positionID) {
            this.positionID = positionID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderPositionPK that = (OrderPositionPK) o;
            return orderID.equals(that.orderID) && positionID.equals(that.positionID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderID, positionID);
        }

        @Override
        public String toString() {
            return "OrderPositionPK{" +
                    "orderID=" + orderID +
                    ", positionID=" + positionID +
                    '}';
        }
    }
}


