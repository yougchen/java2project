package finalproject.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import finalproject.dao.BooksDao;
import finalproject.model.Books;

import finalproject.dao.MarksDao;
import finalproject.model.Marks;
import finalproject.dao.LinkDao;
import finalproject.model.Link;

@Controller
public class finalcontroller{
	
	@Autowired
	private BooksDao BooksDao;

	@Autowired
	private MarksDao MarksDao;
	
	@Autowired
	private LinkDao LinkDao;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String indexview() {
    	return "index";
	}
   //書本管理相關的URL
    @RequestMapping(value="/binsert", method=RequestMethod.GET)
	public String booksInsertion(Model model) {
    	
    	Books book = new Books();
    	model.addAttribute("book", book);
    	return "binsert";
	}
    
    @RequestMapping(value="/binsert", method=RequestMethod.POST)
    @Transactional
	public String doBInsertion(@ModelAttribute("book") Books book, Model model) {
    	BooksDao.saveOrUpdate(book);  
		model.addAttribute("book_id", book.getBook_id());
    	
    	return "redirect:blist";
	}
    
    @RequestMapping(value="/minsert", method=RequestMethod.POST)
    @Transactional
	public String doMInsertion(@ModelAttribute("mark") Marks mark, Model model) {
    	MarksDao.saveOrUpdate(mark);   
		model.addAttribute("mark_id", mark.getMark_id());
    	
    	return "redirect:blist";
	}

    @RequestMapping(value="/bupdate")
    @Transactional
	public String UpdateBForm(@RequestParam(value="book_id", defaultValue="") String book_id
			,Model model) {

		Map<String, String> mconditions = new HashMap<String,String>();
    	Books book = BooksDao.get(Long.parseLong(book_id));

		Map<String, String> lconditions = new HashMap<String,String>();
		lconditions.put("book_id", book_id);
		

    	model.addAttribute("book", book);
		model.addAttribute("marks", MarksDao.search(mconditions));
    	return "bupdate";
	}
    @RequestMapping(value="/blist")
	@Transactional
	public String dobUpdate(@RequestParam(value="book_id", defaultValue="") String book_id,
			@RequestParam(value="mark_id", defaultValue="") String mark_id,
			Model model) {


		model.addAttribute("book_id", book_id);

		model.addAttribute("mark_id", mark_id);
		
    	return "redirect:bquery";
	}

	@RequestMapping(value="/bquery")
	@Transactional
	public String doBQuery(@RequestParam(value="book_Name", defaultValue="") String book_Name,
			@RequestParam(value="writer_Name", defaultValue="") String writer_Name,
			@RequestParam(value="book_id", defaultValue="") String book_id,
			@RequestParam(value="mark_id", defaultValue="") String mark_id,
			Model model) {
		Map<String, String> bconditions = new HashMap<String,String>();
		bconditions.put("book_Name", book_Name);
		bconditions.put("writer_Name", writer_Name);
		model.addAttribute("books", BooksDao.search(bconditions));

		Map<String, String> mconditions = new HashMap<String,String>();
		model.addAttribute("marks", MarksDao.search(mconditions));

		model.addAttribute("book_Name", book_Name);
		model.addAttribute("writer_Name", writer_Name);
		model.addAttribute("book_id", book_id);
		model.addAttribute("mark_id", mark_id);
		return "BBrowse";
	}
	
	@RequestMapping(value="/bdelete")
	@Transactional
	public String doBDeletion(
			@RequestParam(value="book_id", defaultValue="") String book_id,
			Model model, HttpServletResponse response) {
		
		BooksDao.delete(Long.parseLong(book_id));
				
		return "redirect:bquery";
	}

	//書本類型管理相關的URL
    @RequestMapping(value="/minsert", method=RequestMethod.GET)
	public String marksInsertion(Model model) {
    	
    	Marks mark = new Marks();
    	model.addAttribute("mark", mark);
    	return "minsert";
	}
    
    @RequestMapping(value="/mupdate")
    @Transactional
	public String UpdateMForm(@RequestParam(value="mark_id", defaultValue="") String mark_id
			,Model model) {

    	Marks mark = MarksDao.get(Long.parseLong(mark_id));
    	
    	model.addAttribute("mark", mark);
    	return "mupdate";
	}
    
    @RequestMapping(value="/mlist")
	@Transactional
	public String dobUpdate(@RequestParam(value="mark_id", defaultValue="") String mark_id,
			Model model) {

		model.addAttribute("mark_id", mark_id);

		Map<String, String> mconditions = new HashMap<String,String>();
		model.addAttribute("marks", MarksDao.search(mconditions));
		
    	return "MBrowse";
	}

	@RequestMapping(value="/mdelete")
	@Transactional
	public String doMDeletion(
			@RequestParam(value="mark_id", defaultValue="") String mark_id,
			Model model, HttpServletResponse response) {
		
		MarksDao.delete(Long.parseLong(mark_id));
				
		return "redirect:mlist";
	}

	//書本與標籤連結管理相關的URL
    @RequestMapping(value="/linsert", method=RequestMethod.GET)
    @Transactional
	public String dolinkInsertion(@RequestParam(value="book_id", defaultValue="") String book_id,
			@RequestParam(value="mark_id", defaultValue="") String mark_id,
			Model model) {
    	
    	Link link = new Link();

    	link.setMark_id(Long.parseLong(mark_id));
    	link.setBook_id(Long.parseLong(book_id));
        
    	LinkDao.saveOrUpdate(link); 
    	
		model.addAttribute("book_id", link.getBook_id());
    	
    	return "redirect:bupdate";
	}

	

}
