package com.mbd.mercado.domain;

import androidx.annotation.NonNull;

import com.mbd.mercado.domain.enums.ProductCategory;
import com.mbd.mercado.sk.DatabaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements DatabaseEntity {

    @EqualsAndHashCode.Include
    private UUID id;
    private String description;
    private BigDecimal value;
    private int quantity;
    private ProductCategory category;

    @NonNull
    @Override
    public String toString() {
        return this.description + " - " + this.category + " - R$ " + this.value;
    }
}
