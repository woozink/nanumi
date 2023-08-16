package com.ssafy.nanumi.db.entity;

import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name="address")
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="si", columnDefinition="VARCHAR(20)", nullable = false)
    private String si;

    @Column(name="gugun", columnDefinition="VARCHAR(20)", nullable = false)
    private String guGun;

    @Column(name="dong", columnDefinition="VARCHAR(20)", nullable = false)
    private String dong;


}
