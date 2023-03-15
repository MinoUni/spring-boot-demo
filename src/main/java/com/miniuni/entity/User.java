package com.miniuni.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    @Column(
            name = "id",
            updatable = false)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 50
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 50
    )
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(
            name = "birth_date",
            nullable = false)
    private Date birthdate;

    public User(String firstName,
                String lastName,
                Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
