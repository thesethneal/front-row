package com.techelevator.model;

import java.util.List;

public class RoleDto {

    private String role;
    private List<Band> managedBands;

    public RoleDto(String role) {
        this.role = role;
    }

    public RoleDto(String role, List<Band> managedBands) {
        this.role = role;
        this.managedBands = managedBands;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Band> getManagedBands() {
        return managedBands;
    }

    public void setManagedBands(List<Band> managedBands) {
        this.managedBands = managedBands;
    }
}
