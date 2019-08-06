package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.entities.Author;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The class implements {@link AuthorDaoInfs interface}. Contains CRUD operations for {@link Author
 * entity class}
 */
@Repository
@Transactional
public class AuthorDao implements AuthorDaoInfs {

  private static final String TAG = AuthorDao.class.getName();
  private SessionFactory sessionFactory;

  /**
   * Autowired dependency. Provides a <code>SessionFactory</code> implementation.
   *
   * @param sessionFactory implementation
   */
  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /** {@inheritDoc} */
  @Override
  public int insert(Author author) throws HibernateException, IllegalArgumentException {
    Session session = sessionFactory.getCurrentSession();
    return (int) session.save(author);
  }

  /** {@inheritDoc} */
  @Override
  public Author findById(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.find(Author.class, id);
  }

  /** {@inheritDoc} */
  @Override
  public List<Author> findAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT a FROM Author a", Author.class).list();
  }

  /**
   * Updates an entity in database.
   *
   * @param author with updated fields.
   * @return updated entity.
   */
  @Override
  public Author update(Author author) {
    Session session = sessionFactory.getCurrentSession();
    session.update(author);
    return session.find(Author.class, author.getId());
  }

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  @Override
  public boolean delete(int id) throws IllegalArgumentException, DataIntegrityViolationException {
    Session session = sessionFactory.getCurrentSession();
    Author author = session.find(Author.class, id);
    session.remove(author);
    Author deleted = session.find(Author.class, id);
    return (deleted == null);
  }

  /**
   * Finds an author by given name.
   *
   * @param name of the author.
   * @return an object of the found author.
   */
  @Override
  public Author findByName(String name) {
    Session session = sessionFactory.getCurrentSession();
    return findAuthorByName(session, name);
  }

  /**
   * Finds an author by the given name. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param name of author to find.
   * @return the found author.
   */
  private Author findAuthorByName(Session session, String name) {
    return session
        .createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class)
        .setParameter("name", name)
        .uniqueResult();
  }
}
