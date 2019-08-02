package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;

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
    try (Session session = sessionFactory.openSession()) {
      return (int) session.save(reader);
    }
  }

  @Override
  public Reader findById(int id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Reader.class, id);
    }
  }

  @Override
  public List<Reader> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("SELECT r FROM Reader r", Reader.class).list();
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
    try (Session session = sessionFactory.openSession()) {
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
    try (Session session = sessionFactory.openSession()) {
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
  public List<Reader> findByName(String name) throws NoResultException {
    try (Session session = sessionFactory.openSession()) {
      return findReaderByName(session, name);
    }
  }

  /** @return List<Reader> */
  public List<Reader> getBlackList() {
    try (Session session = sessionFactory.openSession()) {
      return session
          .createQuery("SELECT s.reader FROM Story s WHERE s.timeReturn IS NULL", Reader.class)
          .getResultList();
    }
  }

  /**
   * Finds a list of books that the readers with the given name have read.
   *
   * @param readerName the name of the reader.
   * @return a map of readers and their books that they have read.
   */
  public Map<Reader, List<Book>> listOfTookBook(String readerName) {
    Map<Reader, List<Book>> readerBooks = new HashMap<>();

    try (Session session = sessionFactory.openSession()) {
      List<Reader> readers = findReaderByName(session, readerName);
      if (null != readers) {
        for (Reader reader : readers) {
          TypedQuery<Book> query =
              session.createQuery(
                  "SELECT b FROM Story s JOIN s.book b WHERE s.reader = :reader GROUP BY b.id",
                  Book.class);
          query.setParameter("reader", reader);
          List<Book> books = query.getResultList();
          if (null != books) {
            readerBooks.put(reader, books);
          }
        }
      }
    }
    return readerBooks;
  }

  /**
   * Finds a list of books that the he readers with the given name have not returned.
   *
   * @param readerName the name of the reader.
   * @return a map of readers and their books that they have not returned.
   */
  public Map<Reader, List<Book>> listOfNotReturnedBook(String readerName) {
    Map<Reader, List<Book>> readerBooks = new HashMap<>();

    try (Session session = sessionFactory.openSession()) {
      List<Reader> readers = findReaderByName(session, readerName);

      if (null != readers) {
        for (Reader reader : readers) {
          TypedQuery<Book> query =
              session.createQuery(
                  "SELECT b FROM Story s JOIN s.book b WHERE s.reader = :reader AND s.timeReturn IS NULL",
                  Book.class);
          query.setParameter("reader", reader);
          List<Book> books = query.getResultList();
          if (null != books) {
            readerBooks.put(reader, books);
          }
        }
      }
    }
    return readerBooks;
  }

  /**
   * Finds the date of the first visit of to the library for a book for readers with the given name.
   *
   * @param readerName the name of the reader.
   * @return a map of readers and their dates of registration.
   */
  public Map<Reader, Date> findRegistrationDate(String readerName) {
    Map<Reader, Date> readerRegistryDate = new HashMap<>();

    try (Session session = sessionFactory.openSession()) {
      List<Reader> readers = findReaderByName(session, readerName);
      if (null != readers) {
        for (Reader reader : readers) {
          TypedQuery<Date> query =
              session.createQuery(
                  "SELECT min(s.timeTake) FROM Story s JOIN s.reader r WHERE r = :reader",
                  Date.class);
          query.setParameter("reader", reader);
          readerRegistryDate.put(reader, query.getSingleResult());
        }
      }
    }
    return readerRegistryDate;
  }

  /**
   * Return average by reader
   *
   * @return */
  @Override
  public double getAvgReader() {
    try (Session session = sessionFactory.openSession()) {
      return (double)
          session
              .createQuery("SELECT AVG (YEAR(current_date) - YEAR(r.birthday)) FROM Reader r")
              .getSingleResult();
    }
  }

  /**
   * Finds all readers by the given name. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param name of the reader to find.
   * @return a list of Reader object with given name.
   */
  private List<Reader> findReaderByName(Session session, String name) {
    return session
        .createQuery("SELECT r FROM Reader r WHERE r.name = :name", Reader.class)
        .setParameter("name", name)
        .list();
  }
}
