package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "study_user")
public class User extends BaseEntity{
    String name;
    String userId;
    String joinDate;
    String email;
    int authority;
    String photo;
    String interest;
    String info;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
