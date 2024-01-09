package com.trupper.brian.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {

    CUSTOMER(List.of(Permission.READ_ALL_PRODUCTS)),
    ADMINISTRATOR(Arrays.asList(
            Permission.SAVE_ONE_PRODUCT,
            Permission.READ_ALL_PRODUCTS,
            Permission.CREATE_LISTA_COMPRAS,
            Permission.GET_LISTA_COMPRAS,
            Permission.UPDATE_LISTA_COMPRAS,
            Permission.DELETE_LISTA_COMPRAS
    ));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
