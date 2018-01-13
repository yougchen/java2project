package finalproject.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
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
	public List<Long> bsearch(long book_id){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Link.class);

		criteria.add(Restrictions.like("book_id", book_id));
		List links = criteria.list();

		List<Long> listA = new ArrayList();
		for(Iterator it = links.iterator(); it.hasNext(); ) {
		    Link link = (Link) it.next();
		    listA.add(link.getMark_id());
		}	

		return listA;
		
	}
	
	@Override
	public List<Long> msearch(long mark_id){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Link.class);

		criteria.add(Restrictions.like("mark_id", mark_id));
		List links = criteria.list();

		List<Long> listA = new ArrayList();
		for(Iterator it = links.iterator(); it.hasNext(); ) {
		    Link link = (Link) it.next();
		    listA.add(link.getBook_id());
		}	

		return listA;
		
	}

	@Override
	public void b_delete(long book_id) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Link.class);
		//取link where book_id = $book_id
		criteria.add(Restrictions.like("book_id", book_id));
		List links = criteria.list();
		
		for(Iterator it = links.iterator(); it.hasNext(); ) {
			//刪除
		    Link link = (Link) it.next();
			delete(link);
		}	
	}	

	@Override
	public void m_delete(long mark_id) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Link.class);
		//取link where mark_id = $mark_id
		criteria.add(Restrictions.like("mark_id", mark_id));
		List links = criteria.list();

		for(Iterator it = links.iterator(); it.hasNext(); ) {
			//刪除
		    Link link = (Link) it.next();
			delete(link);
		}	


	}

}