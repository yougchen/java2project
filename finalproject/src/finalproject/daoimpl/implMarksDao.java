package finalproject.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import finalproject.dao.MarksDao;
import finalproject.model.Books;
import finalproject.model.Marks;

@Component
public class implMarksDao implements MarksDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Marks get(long mark_id) {
		return (Marks) sessionFactory.getCurrentSession().get(Marks.class, mark_id);
	}

	@Override
	public void saveOrUpdate(Marks mark) {
		sessionFactory.getCurrentSession().saveOrUpdate(mark);
	}

	@Override
	public void delete(Marks mark) {
		sessionFactory.getCurrentSession().delete(mark);

	}

	@Override
	public void delete(long mark_id) {
		Marks mark = get(mark_id);
        if (mark != null) {
            delete(mark);
        }
	}

	@Override
	public List<Marks> search(Map<String,String> conditions) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Marks.class);
		for (Map.Entry<String, String> entry : conditions.entrySet()) {
			criteria.add(Restrictions.like(entry.getKey(), makeLikeString(entry.getValue())));
		}
		criteria.addOrder(Order.asc("mark_id"));		
		
		return criteria.list();
	}

	@Override
	public List<Marks> bsearch(List<Long> mark_ids) {
		Session session=sessionFactory.getCurrentSession();
		List<Long> ids = mark_ids;
		
		Query query =  session.createQuery("FROM Marks item WHERE item.id IN (:ids)");
		query.setParameterList("ids", ids);
		
		List<Marks> marks = query.list();
		
		return  marks;
	}
	
	private String makeLikeString(String str) {
		String retVal = str.replace("%", "");
		retVal = "%" + retVal + "%";
		
		return retVal;
	}


}
