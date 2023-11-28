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
@Table(name = "_user")
public class Users {

  @Id
  @GeneratedValue
  @Column(nullable = false, unique = true)
  public long _id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  public String username;

  @Column(nullable = false)
  public String password;

  @Column(nullable = false)
  public String role;

  public Users(String email, String username, String password, String role) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.role = role;
  }

}