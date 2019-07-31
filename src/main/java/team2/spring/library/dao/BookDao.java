package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class BookDao implements BookDaoInfs {

  private static final String TAG = BookDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public BookDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Book book) {
    int id = -1;
    try (Session session = sessionFactory.getCurrentSession()) {
      id = (int) session.save(book);
    }
    return id;
  }

  @Override
  public Book findById(int id) {
    Book book = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      book = session.find(Book.class, id);
    }
    return book;
  }

  @Transactional(propagation = Propagation.NEVER)
  @Override
  public List<Book> findAll() throws NoResultException {
    List<Book> books = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      books = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
    return books;
  }

  /**
   * Updates an entity in database.
   *
   * @param book with updated fields.
   * @return updated entity.
   */
  @Override
  public Book update(Book book) {
    Book updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(book);
      updated = session.find(Book.class, book.getId());
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
      Book book = session.find(Book.class, id);
      session.delete(book);
      if (null == session.find(Book.class, id)) {
        isDeleted = true;
      }
    }
    return isDeleted;
  }

  /**
   * Finds a book by given title.
   *
   * @param title of the book.
   * @return an object of the found book.
   * @throws NoResultException if there is no book with the given name.
   */
  @Override
  public Book findByTitle(String title) throws NoResultException {
    Book book = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      book = findBookByTitle(session, title);
      return book;
    }
  }

  @Override
  public List<Book> findBooksByAuthor(Author author) {
    List<Book> books = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      TypedQuery<Book> query =
          session.createQuery(
              "SELECT DISTINCT b FROM Author a LEFT JOIN a.books b WHERE a = :author", Book.class);
      query.setParameter("author", author);
      books = query.getResultList();
    }
    return books;
  }

  /**
   * Checks if a book is available.
   *
   * @param title of book to find.
   * @return a count of the available book copies
   */
  @Override
  public long isBookAvailable(String title) {
    long availableCount = 0;
    try (Session session = sessionFactory.getCurrentSession()) {
      Book book = findBookByTitle(session, title);
      TypedQuery<Long> query =
          session.createQuery(
              "SELECT count(c) FROM Copy c "
                  + "JOIN c.book b WHERE b = :book AND c.available =: available");
      query.setParameter("book", book);
      query.setParameter("available", true);
      availableCount = query.getSingleResult();
    }
    return availableCount;
  }

  /**
   * Finds a book by the given title. Uses an instance of the session.
   *
   * @param session - an instance of the current session.
   * @param title of book to find.
   * @return the found book object.
   */
  private Book findBookByTitle(Session session, String title) {
    return (Book)
        session
            .createQuery("SELECT b FROM Book b WHERE b.title = ?1")
            .setParameter(1, title)
            .getSingleResult();
  }
}
