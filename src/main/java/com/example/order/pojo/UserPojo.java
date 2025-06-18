package com.example.order.pojo;

import com.example.order.model.enums.ROLE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long mobileNumber;
    @Column(unique = true)
    private String emailId;
    private String password;
    @Column(unique = true)
    private String userId;
    @Enumerated(EnumType.STRING)
    private ROLE userRole;
}
