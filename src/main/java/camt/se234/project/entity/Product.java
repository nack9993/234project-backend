package camt.se234.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    String name;
    String description;
    String imageLocation;
    double price;

    public Product(String productId, String name, String description, String imageLocation, int price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageLocation = imageLocation;
        this.price = price;
    }
}
