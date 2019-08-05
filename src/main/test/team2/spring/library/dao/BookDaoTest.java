package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team2.spring.library.configs.TestConfig;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class BookDaoTest {
  private static final String BOOK_TITLE = "Test Book";
  private static final String TAG = BookDaoTest.class.getName();
  private BookDaoInfs bookDao;

  @Autowired
  public void setBookDao(BookDaoInfs bookDao) {
    this.bookDao = bookDao;
  }

  @Test
  public void insertTest() {
    int id = bookDao.insert(new Book(BOOK_TITLE));
    Assert.assertNotEquals(0, id);
    boolean isDeleted = bookDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test(expected = HibernateException.class)
  public void insertTwiceTheSameTest() {
    int id = bookDao.insert(new Book(BOOK_TITLE));
    try {
      bookDao.insert(new Book(BOOK_TITLE));
    } catch (HibernateException e) {
      bookDao.delete(id);
      throw new HibernateException(e.getCause());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertNullTest() {
    bookDao.insert(null);
  }

  @Test(expected = HibernateException.class)
  public void insertEmptyTest() {
    bookDao.insert(new Book());
  }

  @Test
  public void findByIdTest() {
    int id = bookDao.insert(new Book(BOOK_TITLE));
    Assert.assertNotEquals(0, id);
    Assert.assertEquals(BOOK_TITLE, bookDao.findById(id).getTitle());
    boolean isDeleted = bookDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByZeroIdTest() {
    Book found = bookDao.findById(0);
    Assert.assertNull(found);
  }

  @Test
  public void findByNegativeIdTest() {
    Book found = bookDao.findById(-10);
    Assert.assertNull(found);
  }

  @Test
  public void updateTest() {
    int id = bookDao.insert(new Book(BOOK_TITLE));
    Book book = bookDao.findById(id);
    book.setTitle(BOOK_TITLE + BOOK_TITLE);
    Book updated = bookDao.update(book);
    Assert.assertEquals(updated.getTitle(), book.getTitle());
    bookDao.delete(id);
  }

  @Test
  public void findAllTest() {
    Book added = new Book(BOOK_TITLE);
    int id = bookDao.insert(added);
    Assert.assertNotEquals(0, id);
    added.setId(id);
    List<Book> foundList = bookDao.findAll();
    boolean contained = false;
    for (Book b : foundList) {
      if (b.getTitle().equals(added.getTitle())) {
        contained = true;
      }
    }
    Assert.assertTrue(contained);
    boolean isDeleted = bookDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByTitleTest() {
    int id = bookDao.insert(new Book(BOOK_TITLE));
    Assert.assertNotEquals(0, id);
    Assert.assertEquals(BOOK_TITLE, bookDao.findByTitle(BOOK_TITLE).getTitle());
    boolean isDeleted = bookDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByNullTest() {
    Book found = bookDao.findByTitle(null);
    Assert.assertNull(found);
  }

  @Test
  public void findByNotExistingNameTest() {
    Book found = bookDao.findByTitle(BOOK_TITLE);
    Assert.assertNull(found);
  }

  @Test
  public void findBooksByNullAuthorTest() {
    List<Book> books = bookDao.findBooksByAuthor(null);
    Assert.assertTrue(books.isEmpty());
  }

  @Test
  public void isNonExistedBookAvailableTest() {
    long copyOfBookCount = bookDao.isBookAvailable(BOOK_TITLE);
    Assert.assertEquals(0L, copyOfBookCount);
  }

  @Test
  public void isNullBookAvailableTest() {
    long copyOfBookCount = bookDao.isBookAvailable(null);
    Assert.assertEquals(0L, copyOfBookCount);
  }

  @Test
  public void getCopiesInfoNonExistingTest() {
    List<Copy> copies = bookDao.getCopiesInfo(BOOK_TITLE);
    Assert.assertTrue(copies.isEmpty());
  }

  @Test
  public void getCopiesInfoNullTest() {
    List<Copy> copies = bookDao.getCopiesInfo(null);
    Assert.assertTrue(copies.isEmpty());
  }

  @Test
  public void getCountOfBookByPeriodNullTest() {
    long count = bookDao.getCountOfBookByPeriod(null, null);
    Assert.assertEquals(0, count);
  }

  @Test
  public void getTotalUsageCountNonExistsTest() {
    long count = bookDao.getTotalUsageCount(BOOK_TITLE);
    Assert.assertEquals(0, count);
  }

  @Test
  public void getTotalUsageCountNullTest() {
    long count = bookDao.getTotalUsageCount(null);
    Assert.assertEquals(0, count);
  }

  @Test
  public void getUsageCountForCopiesNonExistingTest() {
    Map<Copy, Long> copyCount = bookDao.getUsageCountForCopies(BOOK_TITLE);
    Assert.assertTrue(copyCount.isEmpty());
  }

  @Test
  public void getReaderAvgByNonExistedBookTest() {
    double avgByBook = bookDao.getReaderAvgByBook(BOOK_TITLE);
    Assert.assertEquals(0, avgByBook, 0.0);
  }

  @Test
  public void getReaderAvgByNullBookTest() {
    double avgByBook = bookDao.getReaderAvgByBook(null);
    Assert.assertEquals(0, avgByBook, 0.0);
  }

  @Test
  public void getUsageCountForCopiesNullTest() {
    Map<Copy, Long> copyCount = bookDao.getUsageCountForCopies(null);
    Assert.assertTrue(copyCount.isEmpty());
  }

  @Test
  public void getAvgTimeOfUsageNonExistedTest() {
    double avgTimeOfUsage = bookDao.getAvgTimeOfUsage(BOOK_TITLE);
    Assert.assertEquals(0, avgTimeOfUsage, 0.0);
  }

  @Test
  public void getAvgTimeOfUsageNullTest() {
    double avgTimeOfUsage = bookDao.getAvgTimeOfUsage(null);
    Assert.assertEquals(0, avgTimeOfUsage, 0.0);
  }

  @Test
  public void getPopularNullDatesTest() {
    Map<Long, Book> bookCountMap = bookDao.getPopular(null, null);
    Assert.assertTrue(bookCountMap.isEmpty());
  }
}
