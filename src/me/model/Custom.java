package me.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "custom")
public class Custom {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "token_a")
	private String tokenA;

	@Column(name = "token_b")
	private String tokenB;

	@Column(name = "ite_id")
	private Long item;

	public Custom(long id, long i, String string, String string2) {
		this.id = id;
		this.item = i;
		this.tokenA = string;
		this.tokenB = string2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTokenA() {
		return tokenA;
	}

	public void setTokenA(String tokenA) {
		this.tokenA = tokenA;
	}

	public String getTokenB() {
		return tokenB;
	}

	public void setTokenB(String tokenB) {
		this.tokenB = tokenB;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

}
