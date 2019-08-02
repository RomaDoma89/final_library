package team2.spring.library;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import team2.spring.library.dao.*;
import team2.spring.library.dao.interfaces.*;
import team2.spring.library.entities.*;

import java.time.LocalDate;
import java.util.*;

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
    List<Book> books = bookDao.findAll();
    Log.debug(TAG, books.toString());

    // all copies
    List<Copy> copies = copyDao.findAll();
    Log.debug(TAG, copies.toString());

    // all authors
    List<Author> authors = authorDao.findAll();
    Log.debug(TAG, authors.toString());

    // all readers
    List<Reader> readers = readerDao.findAll();
    Log.debug(TAG, readers.toString());

    // all stories
    List<Story> stories = storyDao.findAll();
    Log.debug(TAG, stories.toString());

    // book by title
    Book book = bookDao.findByTitle("Effective Java");
    Log.debug(TAG, book.toString());

    // author by name
    Author author = authorDao.findByName("Joshua Bloch");
    Log.debug(TAG, author.toString());

    // book for the author
    List<Book> bookList = bookDao.findBooksByAuthor(authors.get(0));
    Log.debug(TAG, bookList.toString());

    // is book available
    long availableCount = bookDao.isBookAvailable("Effective Java");
    Log.debug(TAG, availableCount + " ");

    // list of book took for a reader
    Map<Reader, List<Book>> tookBooks = readerDao.listOfTookBook("Margaret Watkins");
    Log.debug(TAG, tookBooks.toString());

    // list of not returned book for a reader
    Map<Reader, List<Book>> notReturnedBooks = readerDao.listOfNotReturnedBook("Margaret Watkins");
    Log.debug(TAG, notReturnedBooks.toString());

    // list of not returned book for a reader
    Map<Reader, LocalDate> registrationDates = readerDao.findRegistrationDate("Margaret Watkins");
    Log.debug(TAG, registrationDates.toString());

    // count of books published in the period;
    LocalDate fromDate = LocalDate.of(2018, Calendar.FEBRUARY, 11);
    LocalDate toDate = LocalDate.of(2019, Calendar.DECEMBER, 11);
    long countOfTookBookForPeriod = bookDao.getCountOfBookByPeriod(fromDate, toDate);
    Log.debug(TAG, countOfTookBookForPeriod + " ");

    // list of readers whose don't return book
    List<Reader> bList = readerDao.getBlackList();
    Log.debug(TAG, bList.toString());

    // average age by book
    double value = bookDao.getReaderAvgByBook("Hooked on Java");
    Log.debug(TAG, Double.toString(value));

    // how many times the book has been taken.
    long countOfUsageForBook = bookDao.getTotalUsageCount("Hooked on Java");
    Log.debug(TAG, Long.toString(countOfUsageForBook));

    // how many times all copies of the book have been taken.
    Map<Copy, Long> copyTookCount =
        bookDao.getUsageCountForCopies(
            "Pro Spring 5: An In-Depth Guide to the Spring Framework and Its Tools 5th ed. Edition");
    Log.debug(TAG, copyTookCount.toString());

    // an average time of reading of the book
    Log.debug(TAG, String.valueOf(bookDao.getAvgTimeOfUsage("Hooked on Java")));

    // an average age of readers
    double avg = readerDao.getAvgReader();
    Log.debug(TAG, Double.toString(avg));

    // popular book
    LocalDate firstPeriod = LocalDate.of(2019, Calendar.FEBRUARY, 11);
    LocalDate secondPeriod = LocalDate.of(2019, Calendar.AUGUST, 11);
    Map<Book, Long> popular = bookDao.getPopular(firstPeriod, secondPeriod);
    Log.debug(TAG, popular.toString());
  }
}
