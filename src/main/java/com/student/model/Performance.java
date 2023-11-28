package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_performance")
public class Performance {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private long id;

    @Column(name = "system_analysis")
    private int systemAnalysis;

    @Column(name = "application_programming")
    private int applicationProgramming;

    @Column(name = "organisation_database")
    private int organisationDB;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    public Student student;

    public Performance(int systemAnalysis, int applicationProgramming, int organisationDB){
        this.systemAnalysis = systemAnalysis;
        this.applicationProgramming = applicationProgramming;
        this.organisationDB = organisationDB;
    }

    public Performance(long id,int systemAnalysis, int applicationProgramming, int organisationDB){
        this.id = id;
        this.systemAnalysis = systemAnalysis;
        this.applicationProgramming = applicationProgramming;
        this.organisationDB = organisationDB;
    }

}