package com.cookingrecipe.dao.user;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cookingrecipe.dao.GenericDAOImp;
import com.cookingrecipe.entity.User;

public class UserDaoImpl extends GenericDAOImp<User, Integer> implements UserDao{

	public UserDaoImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
	     this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createAccount(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
		log.info("Create account successful!");
	}

	@Override
	public User getById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User)(session.get(User.class, id));
		return p;
	}

	@Override
	public User findUserByEmail(String email){
		System.out.println("In find user by email - UserDao");
		return getHibernateTemplate().execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {
				Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.mail=:email", User.class)
						.setParameter("email", email);
				return query.uniqueResult();
			}
		});
	}
}
