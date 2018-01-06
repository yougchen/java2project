package finalproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="link")
public class Link implements Serializable{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long mark_id;

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long book_id;
	
	public long getMark_id() {
		return mark_id;
	}

	public void setMark_id(long mark_id) {
		this.mark_id = mark_id;
	}
	
	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
}