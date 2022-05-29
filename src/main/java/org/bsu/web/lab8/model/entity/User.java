package org.bsu.web.lab8.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "User.findAll",
                query = "select u from User u"),
        @NamedQuery(name = "User.getByUsernameAndPassword",
                query = "select u from User u " +
                        "where u.nickname=:username " +
                        "and u.password=:password")
})
@Entity
@Table(name = "users")
public class User implements Serializable {
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    List<Order> clientOrders;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    List<Order> adminOrders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Order> getClientOrders() {
        return clientOrders;
    }

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public List<Order> getAdminOrders() {
        return adminOrders;
    }

    public void setAdminOrders(List<Order> adminOrders) {
        this.adminOrders = adminOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin && Objects.equals(clientOrders, user.clientOrders) && Objects.equals(adminOrders, user.adminOrders) && id.equals(user.id) && nickname.equals(user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientOrders, adminOrders, id, nickname, isAdmin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
//                ", clientOrders=" + clientOrders +
//                ", adminOrders=" + adminOrders +
                ", nickname='" + nickname + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
