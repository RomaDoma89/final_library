package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.entities.Author;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class AuthorDao implements AuthorDaoInfs {

  private static final String TAG = AuthorDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public AuthorDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Author author) {
    try (Session session = sessionFactory.openSession()) {
      return (int) session.save(author);
    }
  }

  @Override
  public Author findById(int id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Author.class, id);
    }
  }

  @Override
  public List<Author> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("SELECT a FROM Author a", Author.class).list();
    }
  }

  /**
   * Updates an entity in database.
   *
   * @param author with updated fields.
   * @return updated entity.
   */
  @Override
  public Author update(Author author) {
    try (Session session = sessionFactory.openSession()) {
      session.update(author);
      return session.find(Author.class, author.getId());
    }
  }

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  @Override
  public boolean delete(int id) {
    try (Session session = sessionFactory.openSession()) {
      Author author = session.find(Author.class, id);
      session.delete(author);
      return (null == session.find(Author.class, id));
    }
  }

  /**
   * Finds an author by given name.
   *
   * @param name of the author.
   * @return an object of the found author.
   */
  @Override
  public Author findByName(String name) {
    try (Session session = sessionFactory.openSession()) {
      return findAuthorByName(session, name);
    }
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
