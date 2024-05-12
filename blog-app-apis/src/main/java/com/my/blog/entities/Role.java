package com.my.blog.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@Getter
@Setter
public class Role {

    @Id
    private  Integer id;

    private String name;
}
