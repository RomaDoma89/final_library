package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class BookDao implements BookDaoInfs {

  private static final String TAG = BookDao.class.getName();
  private SessionFactory sessionFactory;

  //  @Autowired
  public BookDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Book book) {
    try (Session session = sessionFactory.openSession()) {
      return (int) session.save(book);
    }
  }

  @Override
  public Book findById(int id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Book.class, id);
    }
  }

  @Override
  public List<Book> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("SELECT b FROM Book b", Book.class).list();
    }
  }

  /**
   * Updates an entity in database.
   *
   * @param book with updated fields.
   * @return updated entity.
   */
  @Override
  public Book update(Book book) {
    try (Session session = sessionFactory.openSession()) {
      session.update(book);
      return session.find(Book.class, book.getId());
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
      Book book = session.find(Book.class, id);
      session.delete(book);
      return (null == session.find(Book.class, id));
    }
  }

  /**
   * Finds a book by given title.
   *
   * @param title of the book.
   * @return an object of the found book.
   */
  @Override
  public Book findByTitle(String title) {
    try (Session session = sessionFactory.openSession()) {
      return findBookByTitle(session, title);
    }
  }

  @Override
  public List<Book> findBooksByAuthor(Author author) {
    try (Session session = sessionFactory.openSession()) {
      TypedQuery<Book> query =
          session.createQuery(
              "SELECT b FROM Author a LEFT JOIN a.books b WHERE a = :author", Book.class);
      query.setParameter("author", author);
      return query.getResultList();
    }
  }

  /**
   * Checks if a book is available.
   *
   * @param title of book to find.
   * @return a count of the available book copies
   */
  @Override
  public long isBookAvailable(String title) {
    try (Session session = sessionFactory.openSession()) {
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
  }

  /**
   * Finds how many books in the library have been took over a period of time
   *
   * @param fromDate start of the period.
   * @param toDate end of the period.
   * @return count of took book.
   */
  @Override
  public long getCountOfBookByPeriod(Date fromDate, Date toDate) {
    try (Session session = sessionFactory.openSession()) {
      TypedQuery<Long> query =
          session.createQuery(
              "SELECT count(s.book) FROM Story s WHERE s.timeTake BETWEEN :fromDate AND :toDate",
              Long.class);
      query.setParameter("fromDate", fromDate);
      query.setParameter("toDate", toDate);
      return query.getSingleResult();
    }
  }

  /**
   * Find all copies of book and return total info about copy
   *
   * @param title
   * @return List<Copy>
   */
  @Override
  public List<Copy> getCopiesInfo(String title) {
    try (Session session = sessionFactory.openSession()) {
      return session
          .createQuery("SELECT c FROM Copy c WHERE c.book.title = ?1", Copy.class)
          .setParameter(1, title)
          .getResultList();
    }
  }

  /**
   * Find average age of readers whose have read this book
   *
   * @param title
   * @return double
   */
  @Override
  public double getReaderAvgByBook(String title) {
    try (Session session = sessionFactory.openSession()) {
      return (double)
          session
              .createQuery(
                  "SELECT AVG (YEAR(current_date) - YEAR(s.reader.birthday)) FROM Story s WHERE s.book.title = ?1")
              .setParameter(1, title)
              .getSingleResult();
    }
  }

  /**
   * Return list of books and their counter of reading
   *
   * @param firstPeriod
   * @param secondPeriod
   * @return Map<Book, Long>
   */
  @Override
  public Map<Book, Long> getPopular(Date firstPeriod, Date secondPeriod) {
    try (Session session = sessionFactory.openSession()) {
      Map<Book, Long> resultMap = new HashMap<>();

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
          resultMap.put(book, count);
        }
      }
      return resultMap;
    }
  }

  /**
   * Finds a book by given title and counts how many times the book has been taken.
   *
   * <p>5. Скільки разів брали певну книжку (в загальному)
   *
   * @param title of book to find.
   * @return how many times the book has taken.
   */
  @Override
  public long getTotalUsageCount(String title) {
    try (Session session = sessionFactory.openSession()) {
      Book book = findBookByTitle(session, title);
      return session
          .createQuery("SELECT count(s.book) FROM Story s WHERE s.book = :book", Long.class)
          .setParameter("book", book)
          .uniqueResult();
    }
  }

  /**
   * Finds a book by given title and counts how many times the book has been taken.
   *
   * <p>5. Скільки разів брали певну книжку ( по примірникам)
   *
   * @param title of book to find.
   * @return how many times the book has taken.
   */
  public Map<Copy, Long> getUsageCountForCopies(String title) {
    try (Session session = sessionFactory.openSession()) {
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
  }

  /**
   * Finds a book by given title and counts an average time of reading.
   *
   * <p>5. Скільки разів брали певну книжку ( СЕРЕДНІЙ ЧАС ЧИТАННЯ)
   *
   * @param title of book to find.
   * @return an average time of reading.
   */
  public Double getAvgTimeOfUsage(String title) {
    try (Session session = sessionFactory.openSession()) {
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
