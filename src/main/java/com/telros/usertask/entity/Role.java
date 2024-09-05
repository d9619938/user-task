package com.telros.usertask.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    public Role() {
    }

    public Role(int id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    public enum RoleType{
        ROLE_USER, ROLE_ADMIN// префикс ROLE обязателен
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}