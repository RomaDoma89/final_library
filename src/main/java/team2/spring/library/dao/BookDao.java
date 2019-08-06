package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.*;

/**
 * The class implements {@link BookDaoInfs interface}. Contains CRUD operations for {@link Book
 * entity class}
 */
@Transactional
@Repository
public class BookDao implements BookDaoInfs {

  private static final String TAG = BookDao.class.getName();
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
  public int insert(Book book) throws HibernateException, IllegalArgumentException {
    Session session = sessionFactory.getCurrentSession();
    return (int) session.save(book);
  }

  /** {@inheritDoc} */
  @Override
  public Book findById(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.find(Book.class, id);
  }

  /** {@inheritDoc} */
  @Override
  public List<Book> findAll() {
    Session session = sessionFactory.getCurrentSession();
    return session
        .createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors", Book.class)
        .list();
  }

  /**
   * Updates an entity in database.
   *
   * @param book with updated fields.
   * @return updated entity.
   */
  @Override
  public Book update(Book book) {
    Session session = sessionFactory.getCurrentSession();
    session.update(book);
    return session.find(Book.class, book.getId());
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
    Book book = session.find(Book.class, id);
    session.delete(book);
    return (null == session.find(Book.class, id));
  }

  /**
   * Finds a book by given title.
   *
   * @param title of the book.
   * @return an object of the found book.
   */
  @Override
  public Book findByTitle(String title) {
    Session session = sessionFactory.getCurrentSession();
    return findBookByTitle(session, title);
  }

  /**
   * Finds all books by <code>Author</code>.
   *
   * @param author to find books.
   * @return list of books by this author.
   */
  @Override
  public List<Book> findBooksByAuthor(Author author) {
    Session session = sessionFactory.getCurrentSession();
    TypedQuery<Book> query =
        session.createQuery(
            "SELECT b FROM Author a LEFT JOIN a.books b WHERE a = :author", Book.class);
    query.setParameter("author", author);
    return query.getResultList();
  }

  /**
   * Checks if a book is available.
   *
   * @param title of book to find.
   * @return a count of the available book copies
   */
  @Override
  public long isBookAvailable(String title) {
    Session session = sessionFactory.getCurrentSession();
    Book book = findBookByTitle(session, title);
    TypedQuery<Long> query =
        session.createQuery(
            "SELECT count(c) FROM Copy c "
                + "JOIN c.book b WHERE b = :book AND c.available =: available",
            Long.class);
    query.setParameter("book", book);
    query.setParameter("available", true);
    return query.getSingleResult();
  }

  /**
   * Finds how many books in the library have been took over a period of time.
   *
   * @param fromDate start of the period.
   * @param toDate end of the period.
   * @return count of took book.
   */
  @Override
  public long getCountOfBookByPeriod(LocalDate fromDate, LocalDate toDate) {
    Session session = sessionFactory.getCurrentSession();
    TypedQuery<Long> query =
        session.createQuery(
            "SELECT count(s.book) FROM Story s WHERE s.timeTake BETWEEN :fromDate AND :toDate",
            Long.class);
    query.setParameter("fromDate", fromDate);
    query.setParameter("toDate", toDate);
    return query.getSingleResult();
  }

  /**
   * Find all copies of book and return total info about copy.
   *
   * @param title of a book to find its copies.
   * @return List<Copy> list of copies of the book.
   */
  @Override
  public List<Copy> getCopiesInfo(String title) {
    Session session = sessionFactory.getCurrentSession();
    return session
        .createQuery("SELECT c FROM Copy c WHERE c.book.title = :title", Copy.class)
        .setParameter("title", title)
        .getResultList();
  }

  /**
   * Find average age of readers whose have read this book.
   *
   * @param title of a book to find.
   * @return double value of average age of readers of the book.
   */
  @Override
  public double getReaderAvgByBook(String title) {
    Session session = sessionFactory.getCurrentSession();
    double avgAge = 0;
    Book book = findBookByTitle(session, title);
    if (null != book) {
      TypedQuery<Double> query =
          session
              .createQuery(
                  "SELECT avg(YEAR(current_date) - YEAR (s.reader.birthday)) FROM Story s WHERE s.book = :book",
                  Double.class)
              .setParameter("book", book);
      avgAge = query.getSingleResult();
    }
    return avgAge;
  }

  /**
   * Return list of books and their counter of reading
   *
   * @param firstPeriod start of the period.
   * @param secondPeriod end of the period.
   * @return TreeMap<Long, Book> a map of books and the number of times they were taken
   */
  @Override
  public TreeMap<Long, Book> getPopular(LocalDate firstPeriod, LocalDate secondPeriod) {
    Session session = sessionFactory.getCurrentSession();
    TreeMap<Long, Book> resultMap = new TreeMap<>(Collections.reverseOrder());

    List<Book> bookList =
        session
            .createQuery(
                "SELECT DISTINCT s.book FROM Story s "
                    + "WHERE s.timeTake between :date1 AND :date2",
                Book.class)
            .setParameter("date1", firstPeriod)
            .setParameter("date2", secondPeriod)
            .list();
    if (bookList != null) {
      for (Book book : bookList) {
        Long count =
            session
                .createQuery(
                    "SELECT count(s.timeTake) " + "FROM Story s" + " WHERE s.book = :book",
                    Long.class)
                .setParameter("book", book)
                .getSingleResult();
        resultMap.put(count, book);
      }
    }
    return resultMap;
  }

  /**
   * Finds a book by given title and counts how many times the book has been taken.
   *
   * @param title of book to find.
   * @return how many times the book has taken.
   */
  @Override
  public long getTotalUsageCount(String title) {
    Session session = sessionFactory.getCurrentSession();
    Book book = findBookByTitle(session, title);
    return session
        .createQuery("SELECT count(s.book) FROM Story s WHERE s.book = :book", Long.class)
        .setParameter("book", book)
        .uniqueResult();
  }

  /**
   * Finds a book by given title and counts how many times the book has been taken.
   *
   * @param title of book to find.
   * @return how many times the book has taken.
   */
  public Map<Copy, Long> getUsageCountForCopies(String title) {
    Session session = sessionFactory.getCurrentSession();
    Map<Copy, Long> resultMap = new HashMap<>();

    Book book = findBookByTitle(session, title);

    List<Copy> copies =
        session
            .createQuery("SELECT c FROM Copy c WHERE c.book = :book", Copy.class)
            .setParameter("book", book)
            .list();

    if (null != copies) {
      for (Copy copy : copies) {
        Long count =
            session
                .createQuery("SELECT count(s.id) FROM Story s WHERE s.copy = :copy", Long.class)
                .setParameter("copy", copy)
                .uniqueResult();
        resultMap.put(copy, count);
      }
    }
    return resultMap;
  }

  /**
   * Finds a book by given title and counts an average time of reading.
   *
   * @param title of book to find.
   * @return an average time of reading.
   */
  public Double getAvgTimeOfUsage(String title) {
    Session session = sessionFactory.getCurrentSession();
    Book book = findBookByTitle(session, title);
    List<Integer> days =
        session
            .createQuery(
                "SELECT (DATEDIFF(s.timeReturn, s.timeTake)) FROM Story s WHERE s.book = :book AND s.timeReturn IS NOT NULL",
                Integer.class)
            .setParameter("book", book)
            .list();
    return days.stream().mapToInt(i -> i).average().orElse(0);
  }

  /**
   * Finds a book by the given title. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param title of book to find.
   * @return the found book.
   */
  private Book findBookByTitle(Session session, String title) {
    return session
        .createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class)
        .setParameter("title", title)
        .uniqueResult();
  }
}
