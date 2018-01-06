package finalproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Marks {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long mark_id;
	
	@Column(length=40)
	private String mark_Name;
	
	@Column(length=60)
	private String mark_information;

	public long getMark_id() {
		return mark_id;
	}

	public void setMark_id(long mark_id) {
		this.mark_id = mark_id;
	}

	public String getMark_Name() {
		return mark_Name;
	}

	public void setMark_Name(String mark_Name) {
		this.mark_Name = mark_Name;
	}
	
	public String getMark_information() {
		return mark_information;
	}

	public void setMark_information(String mark_information) {
		this.mark_information = mark_information;
	}


}
