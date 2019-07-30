package team2.spring.library;

import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import team2.spring.library.dao.BookDao;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Book;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
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


    sessionFactory.getCurrentSession().getTransaction().begin();
    BookDaoInfs bookDao = new BookDao(sessionFactory);
    List<Book> books = bookDao.findAll();
    Log.debug(TAG, books.toString());


    sessionFactory.getCurrentSession().getTransaction().begin();
    Book book = bookDao.findByTitle("Effective Java");
    Log.debug(TAG, book.toString());
  }
}
