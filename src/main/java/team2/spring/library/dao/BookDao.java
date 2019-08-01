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
import java.util.List;

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
      return session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
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
  // todo two method find book by title
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
   * Finds a book by the given title. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param title of book to find.
   * @return the found book.
   */
  private Book findBookByTitle(Session session, String title) {
    return (Book)
            session
                    .createQuery("SELECT b FROM Book b WHERE b.title = ?1")
                    .setParameter(1, title)
                    .getSingleResult();
  }
}
