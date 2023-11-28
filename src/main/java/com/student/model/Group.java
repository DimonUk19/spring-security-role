package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_group")
public class Group {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String nameOfGroup;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> students;

    public Group(String nameOfGroup){
        this.nameOfGroup = nameOfGroup;
    }
}
