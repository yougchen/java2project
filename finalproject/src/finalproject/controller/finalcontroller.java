package finalproject.controller;


import java.util.HashMap;
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
   
    @RequestMapping(value="/binsert", method=RequestMethod.GET)
	public String booksInsertion(Model model) {
    	
    	Books book = new Books();
    	model.addAttribute("book", book);
    	return "binsert";
	}
    
    @RequestMapping(value="/minsert", method=RequestMethod.GET)
	public String marksInsertion(Model model) {
    	
    	Marks mark = new Marks();
    	model.addAttribute("mark", mark);
    	return "minsert";
	}

    @RequestMapping(value="/linsert", method=RequestMethod.GET)
	public String linkInsertion(Model model) {
    	
    	Link link = new Link();
    	model.addAttribute("link", link);
    	return "linsert";
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

    @RequestMapping(value="/linsert", method=RequestMethod.POST)
	public String dolInsertion(@ModelAttribute("link") Link link, Model model) {
    	LinkDao.saveOrUpdate(link);   
		model.addAttribute("book_id", link.getBook_id());
    	
    	return "redirect:bupdate";
	}
    @RequestMapping(value="/bupdate")
    @Transactional
	public String UpdateBForm(@RequestParam(value="book_id", defaultValue="") String book_id
			,Model model) {

		Map<String, String> mconditions = new HashMap<String,String>();
    	Books book = BooksDao.get(Long.parseLong(book_id));
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
/*
	@RequestMapping(value="/mdelete")
	@Transactional
	public String doMDeletion(
			@RequestParam(value="book_id", defaultValue="") String book_id,
			Model model, HttpServletResponse response) {
		
		BooksDao.delete(Long.parseLong(book_id));
				
		return "redirect:bquery";
	}
*/
	

}
