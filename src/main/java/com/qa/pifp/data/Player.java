package com.qa.pifp.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String name;
	private Integer age;
	private String position;
	private String nationality;

	public Player(Integer id, String name, Integer age, String position, String nationality) {
		super();
		Id = id;
		this.name = name;
		this.age = age;
		this.position = position;
		this.nationality = nationality;
	}

	public Player() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, age, name, nationality, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(age, other.age) && Objects.equals(name, other.name)
				&& Objects.equals(nationality, other.nationality) && Objects.equals(position, other.position);
	}

}
