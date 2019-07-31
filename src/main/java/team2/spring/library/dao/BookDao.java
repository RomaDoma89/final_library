package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

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

  @Override
  public List<Book> findAll() throws NoResultException {
    List<Book> books = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      books = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
    return books;
  }

  @Override
  public Book update(Book book) {
    Book updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(book);
      updated = session.find(Book.class, book.getId());
    }
    return updated;
  }

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

  @Override
  public Book findByTitle(String title) throws NoResultException {
    Book book = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      book =
          (Book)
              session
                  .createQuery("SELECT b FROM Book b WHERE b.title = ?1")
                  .setParameter(1, title)
                  .getSingleResult();
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
}
