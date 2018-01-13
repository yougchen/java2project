package finalproject.dao;

import java.util.List;
import java.util.Map;

import finalproject.model.Books;

public interface BooksDao {
	Books get(long book_id);
	void saveOrUpdate(Books book);
	void delete(Books book);
	void delete(long book_id);
	List<Books> search(Map<String,String> conditions);
	List<Books> msearch(List<Long> book_ids) ;
	
}