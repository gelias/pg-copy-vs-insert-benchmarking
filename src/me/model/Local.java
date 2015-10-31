package me.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "local")
public class Local {

	@Id
	@Column(name = "id")
	private Long id;

}
