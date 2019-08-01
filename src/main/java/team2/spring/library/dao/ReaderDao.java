package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;
import javax.transaction.Transactional;
import java.util.List;

/** */
@Transactional
@Repository
public class ReaderDao implements ReaderDaoInfs {

  private static final String TAG = ReaderDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public ReaderDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Reader reader) {
    try (Session session = sessionFactory.getCurrentSession()) {
      return (int) session.save(reader);
    }
  }

  @Override
  public Reader findById(int id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      return session.find(Reader.class, id);
    }
  }

  @Override
  public List<Reader> findAll() {
    try (Session session = sessionFactory.getCurrentSession()) {
      return session.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
    }
  }

  /**
   * Updates an entity in database.
   *
   * @param reader with updated fields.
   * @return updated entity.
   */
  @Override
  public Reader update(Reader reader) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(reader);
      return session.find(Reader.class, reader.getId());
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
    try (Session session = sessionFactory.getCurrentSession()) {
      Reader reader = session.find(Reader.class, id);
      session.delete(reader);
      return (null == session.find(Reader.class, id));
    }
  }

  /**
   * Finds a reader by given name.
   *
   * @param name of the reader.
   * @return an object of the found reader.
   */
  @Override
  public List<Reader> findByName(String name) {
    try (Session session = sessionFactory.getCurrentSession()) {
      return findReaderByName(session, name);
    }
  }

  /** @return List<Reader> */
  public List<Reader> getBlackList() {
    try (Session session = sessionFactory.getCurrentSession()) {
      return session
          .createQuery("SELECT s.reader FROM Story s WHERE s.timeReturn IS NULL ")
          .getResultList();
    }
  }

  //  3. Переглянути статистику по читачу (які книжки брав, які на руках, скільки часу користується
  // послугами бібліотеки)

  public List<Book> listOfTookBook(String readerName) {
    return null;
  }

  /**
   * Finds all readers by the given name. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param name of the reader to find.
   * @return a list of Reader object with given name.
   */
  private List<Reader> findReaderByName(Session session, String name) {
    return (List<Reader>)
        session
            .createQuery("SELECT r FROM Reader r WHERE r.name = ?1")
            .setParameter(1, name)
            .getSingleResult();
  }
}
