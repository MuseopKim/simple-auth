package com.simpleauth.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Account {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 30, nullable = false)
    private String password;

    @Builder
    private Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
