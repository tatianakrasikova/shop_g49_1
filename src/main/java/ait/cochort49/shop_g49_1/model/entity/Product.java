package ait.cochort49.shop_g49_1.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;



@Entity // Данный класс является сущностью
@Table(name = "product") // Определяет таблицу в БД
@Schema(description = "Class that describes Product")
public class Product {
    @Schema(description = "Product unique identifier", example = "15", accessMode = Schema.AccessMode.READ_ONLY)
    @Id // Указывает, что поле является первичным ключом
    @Column(name = "id") // Связывает поля класса с соответствующей колонкой в БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Product title", example = "Banana")
    @Column(name = "title")
    private String title;

    @Schema(description = "Product price", example = "8.50")
    @Column(name = "price")
    private BigDecimal price;

    @Schema(description = "Is product available", accessMode = Schema.AccessMode.READ_ONLY)
    @Column
    private boolean active;


    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s, active - %s",
                id, title, price, active ? "yes" : "no");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;

        return active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Boolean.hashCode(active);
        return result;
    }
}
