package finalproject.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import finalproject.dao.BooksDao;
import finalproject.model.Books;

@Component
public class implBooksDao implements BooksDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Books get(long book_id) {
		return (Books) sessionFactory.getCurrentSession().get(Books.class, book_id);
	}

	@Override
	public void saveOrUpdate(Books book) {
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}

	@Override
	public void delete(Books book) {
		sessionFactory.getCurrentSession().delete(book);

	}

	@Override
	public void delete(long book_id) {
		Books book = get(book_id);
        if (book != null) {
            delete(book);
        }
	}

	@Override
	public List<Books> search(Map<String,String> conditions) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Books.class);
		for (Map.Entry<String, String> entry : conditions.entrySet()) {
			criteria.add(Restrictions.like(entry.getKey(), makeLikeString(entry.getValue())));
		}
		criteria.addOrder(Order.asc("book_id"));		
		
		return criteria.list();
	}
	
	private String makeLikeString(String str) {
		String retVal = str.replace("%", "");
		retVal = "%" + retVal + "%";
		
		return retVal;
	}


}
