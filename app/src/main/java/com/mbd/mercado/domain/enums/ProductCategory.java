package com.mbd.mercado.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategory {
    ALIMENTO("Alimento"),
    BEBIDA("Bebida"),
    HIGIENE("Higiene"),
    LIMPEZA("Limpeza"),
    ELETRONICO("Eletrônico"),
    VESTUARIO("Vestuário"),
    PAPELARIA("Papelaria"),
    OUTROS("Outros");

    private final String descricao;
}
