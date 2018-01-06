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

import finalproject.dao.LinkDao;
import finalproject.model.Books;
import finalproject.model.Link;
import finalproject.model.Marks;

@Component
public class implLinkDao implements LinkDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Link get(long mark_id,long book_id) {
		return (Link) sessionFactory.getCurrentSession().get(Link.class, mark_id);
	}
	
	@Override
	public void saveOrUpdate(Link link) {
		sessionFactory.getCurrentSession().saveOrUpdate(link);
	}


	@Override
	public void delete(Link link) {
		sessionFactory.getCurrentSession().delete(link);

	}
	
	@Override
	public void delete(long mark_id,long book_id) {

		Link link = get(mark_id,book_id);
        if (link != null) {
            delete(link);
        }
		
	}

	@Override
	public List<Link> search(Map<String,String> conditions){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Link.class);
		for (Map.Entry<String, String> entry : conditions.entrySet()) {
			criteria.add(Restrictions.like(entry.getKey(), makeLikeString(entry.getValue())));
		}
		criteria.addOrder(Order.asc("mark_id"));		
		
		return criteria.list();
		
	}
	
	private String makeLikeString(String str) {
		String retVal = str.replace("%", "");
		retVal = "%" + retVal + "%";
		
		return retVal;
	}

}