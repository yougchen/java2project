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
	List<Long> bsearch(long book_id);
	List<Long> msearch(long mark_id);
	void b_delete(long book_id);
	void m_delete(long mark_id);
	
}
