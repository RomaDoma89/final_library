package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.entities.Author;

import javax.persistence.NoResultException;
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
    int id = -1;
    try (Session session = sessionFactory.getCurrentSession()) {
      id = (int) session.save(author);
    }
    return id;
  }

  @Override
  public Author findById(int id) {
    Author author = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      author = session.find(Author.class, id);
    }
    return author;
  }

  @Override
  public List<Author> findAll() throws NoResultException {
    List<Author> authors = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      authors = session.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
    return authors;
  }

  /**
   * Updates an entity in database.
   *
   * @param author with updated fields.
   * @return updated entity.
   */
  @Override
  public Author update(Author author) {
    Author updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(author);
      updated = session.find(Author.class, author.getId());
    }
    return updated;
  }

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  @Override
  public boolean delete(int id) {
    boolean isDeleted = false;
    try (Session session = sessionFactory.getCurrentSession()) {
      Author author = session.find(Author.class, id);
      session.delete(author);
      if (null == session.find(Author.class, id)) {
        isDeleted = true;
      }
    }
    return isDeleted;
  }

  /**
   * Finds an author by given name.
   *
   * @param name of the author.
   * @return an object of the found author.
   * @throws NoResultException if there is no author with the given name.
   */
  @Override
  public Author findByName(String name) throws NoResultException {
    Author author = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      author = (Author) session.createQuery("SELECT a FROM Author a WHERE a.name = ?1")
                  .setParameter(1, name)
                  .getSingleResult();
      return author;
    }
  }
}
