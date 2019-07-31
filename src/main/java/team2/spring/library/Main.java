package team2.spring.library;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import team2.spring.library.dao.*;
import team2.spring.library.dao.interfaces.*;
import team2.spring.library.entities.*;

import java.util.Iterator;
import java.util.List;

public class Main {
  private static String TAG = "Main";

  public static void main(String[] args) {
    Configuration cfg =
        new Configuration()
            .addAnnotatedClass(team2.spring.library.entities.Book.class)
            .addAnnotatedClass(team2.spring.library.entities.Author.class)
            .addAnnotatedClass(team2.spring.library.entities.Copy.class)
            .addAnnotatedClass(team2.spring.library.entities.Reader.class)
            .addAnnotatedClass(team2.spring.library.entities.Story.class)
            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://localhost:3306/library_spring?createDatabaseIfNotExist=true&serverTimezone=UTC")
            .setProperty("hibernate.current_session_context_class", "thread")
            .setProperty("hibernate.connection.username", "root")
            .setProperty("hibernate.connection.password", "root")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
            .setProperty("hibernate.show_sql", "false")
            .setProperty("hibernate.format_sql", "true")
            .setProperty("hibernate.hbm2ddl.auto", "update");

    // Create SessionFactory
    SessionFactory sessionFactory = cfg.buildSessionFactory();

    Log.debug(TAG, "Create spring_library scheme!");

    BookDaoInfs bookDao = new BookDao(sessionFactory);
    AuthorDaoInfs authorDao = new AuthorDao(sessionFactory);
    ReaderDaoInfs readerDao = new ReaderDao(sessionFactory);
    CopyDaoInfs copyDao = new CopyDao(sessionFactory);
    StoryDaoInfs storyDao = new StoryDao(sessionFactory);

    // all books
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Book> books = bookDao.findAll();
    Log.debug(TAG, books.toString());

    // all copies
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Copy> copies = copyDao.findAll();
    Log.debug(TAG, copies.toString());

    // all authors
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Author> authors = authorDao.findAll();
    Log.debug(TAG, authors.toString());

    // all readers
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Reader> readers = readerDao.findAll();
    Log.debug(TAG, readers.toString());

    // all stories
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Story> stories = storyDao.findAll();
    Log.debug(TAG, stories.toString());

    // book by title
    sessionFactory.getCurrentSession().getTransaction().begin();
    Book book = bookDao.findByTitle("Effective Java");
    Log.debug(TAG, book.toString());

    // author by name
    sessionFactory.getCurrentSession().getTransaction().begin();
    Author author = authorDao.findByName("Joshua Bloch");
    Log.debug(TAG, author.toString());

    // book for the author
    sessionFactory.getCurrentSession().getTransaction().begin();
    List<Book> bookList = bookDao.findBooksByAuthor(authors.get(0));
    Log.debug(TAG, bookList.toString());

    // is book available
    sessionFactory.getCurrentSession().getTransaction().begin();
    long availableCount = bookDao.isBookAvailable("Effective Java");
    Log.debug(TAG, availableCount + " ");
  }
}
