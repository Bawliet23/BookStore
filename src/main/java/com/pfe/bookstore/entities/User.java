package com.pfe.bookstore.entities;


import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "role",
        columnDefinition = "VARCHAR(20)"
)
@DiscriminatorValue("admin")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date birthday;
    private String image;
    private int reportednumber;
    @Column(insertable=false,updatable=false)
    private String role;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

//
//    public void addComment(Comment comment) {
//        comments.add(comment);
//        comment.setUser(this);
//    }
//
//    public void removeComment(Comment comment) {
//        comments.remove(comment);
//        comment.setUser(null);
//    }

}
