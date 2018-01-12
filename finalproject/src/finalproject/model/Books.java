package finalproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long book_id;
	
	@Column(length=40)
	private String book_Name;
	
	@Column(length=30)
	private String writer_Name;
	
	@Column(length=300)
	private String book_information;

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getBook_Name() {
		return book_Name;
	}

	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}
	
	public String getwriter_Name() {
		return writer_Name;
	}

	public void setwriter_Name(String writer_Name) {
		this.writer_Name = writer_Name;
	}

	public String getBook_information() {
		return book_information;
	}

	public void setBook_information(String book_information) {
		this.book_information = book_information;
	}


}