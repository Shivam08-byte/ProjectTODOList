package com.sheryians.s2todoapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;


    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    public Todo(){

    }

}
