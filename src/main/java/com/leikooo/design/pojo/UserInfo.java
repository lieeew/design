package com.leikooo.design.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private Date creatData;

    @Column
    private String userEmail;
}
