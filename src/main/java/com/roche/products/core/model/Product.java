package com.roche.products.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String sku;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false, name = "price_in_cents")
    @NotNull
    private Integer priceInCents;

    @Column(name = "is_deleted")
    @NotNull
    private Boolean isDeleted;

    @Column(updatable = false, name ="created_at")
    private LocalDateTime createdAt;

    @Column(insertable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

    public Product() {
    }

    public Product(Long id, String sku, String name, Integer priceInCents, Boolean isDeleted) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.priceInCents = priceInCents;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(sku, product.sku) &&
                Objects.equals(name, product.name) &&
                Objects.equals(priceInCents, product.priceInCents) &&
                Objects.equals(isDeleted, product.isDeleted) &&
                Objects.equals(createdAt, product.createdAt) &&
                Objects.equals(updatedAt, product.updatedAt) &&
                Objects.equals(version, product.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, priceInCents, isDeleted, createdAt, updatedAt, version);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", priceInCents=" + priceInCents +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                '}';
    }
}
