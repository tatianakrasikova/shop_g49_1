package ait.cochort49.shop_g49_1.model.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.Objects;



@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private boolean active;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Cart cart;


    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s, active - %s",
                id, name, active ? "yes" : "no");
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return active == customer.active && Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Boolean.hashCode(active);
        return result;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
