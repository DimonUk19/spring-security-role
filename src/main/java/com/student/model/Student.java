package com.student.model;

import javax.persistence.*;

import lombok.*;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;


	@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "group_id", nullable = false)
	@JoinColumn(name = "group_id")
	public Group group;

	@OneToOne(mappedBy = "student", fetch = FetchType.EAGER)
	private Performance performance;

	public Student(String name, String lastName) {
		super();
		this.firstName = name;
		this.lastName = lastName;
	}

	public Student(String name, String lastName, Group group) {
		super();
		this.firstName = name;
		this.lastName = lastName;
		this.group = group;
	}
}
