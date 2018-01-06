package finalproject.dao;

import java.util.List;
import java.util.Map;

import finalproject.model.Marks;

public interface MarksDao {
	Marks get(long mark_id);
	void saveOrUpdate(Marks mark);
	void delete(Marks mark);
	void delete(long mark_id);
	List<Marks> search(Map<String,String> conditions);
	
}
