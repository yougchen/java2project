package finalproject.dao;

import java.util.List;
import java.util.Map;

import finalproject.model.Books;
import finalproject.model.Link;
import finalproject.model.Marks;

public interface LinkDao {
	Link get(long mark_id,long book_id);
	void saveOrUpdate(Link link);
	void delete(Link link);
	void delete(long mark_id,long book_id);
	List<Link> search(Map<String,String> conditions);
	
}
