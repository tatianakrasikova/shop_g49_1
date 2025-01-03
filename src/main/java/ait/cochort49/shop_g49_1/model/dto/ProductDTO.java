package ait.cochort49.shop_g49_1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;





@Schema(description = "Class that describes Product")
public class ProductDTO {

    @Schema(description = "Product unique identifier", example = "15", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Product title", example = "Banana")
    @NotNull(message = "Product title cannot be null") // title = null
    @NotBlank(message = "Product title cannot be empty") //title = "";
//    @Pattern(regexp = "^[A-Z][a-z ]{2,}$", message = "Product title should be at least 3 characters long, start with a capital letter")
    @Pattern(regexp = "^[A-Z][a-z][a-zA-Z0-9 ]+$", message = "Product title should be at least 3 characters long, start with a capital letter")
    private String title;

    @Schema(description = "Product price", example = "8.50")
    @DecimalMin(value = "1.0", message = "Product price should be greater or equals than 1.0")
    @DecimalMax(value = "100000.0", inclusive = false, message = "Product price should be less  than 100000.0")
    private BigDecimal price;


    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s",
                id, title, price);
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



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof ProductDTO that)) return false;

        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(price);
        return result;
    }
}
