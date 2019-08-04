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
import team2.spring.library.dao.interfaces.CopyDaoInfs;
import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.dao.interfaces.StoryDaoInfs;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;
import team2.spring.library.entities.Reader;
import team2.spring.library.entities.Story;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ReaderDaoTest {
  private static final String READER_NAME = "Roma Zahorui";
  private static final LocalDate READER_BIRTH = LocalDate.now();
  private static final String TAG = ReaderDaoTest.class.getName();
  private ReaderDaoInfs readerDao;
  private StoryDaoInfs storyDao;
  private BookDaoInfs bookDao;
  private CopyDaoInfs copyDao;

  @Autowired
  public void setReaderDao(ReaderDaoInfs readerDao) {
    this.readerDao = readerDao;
  }

  @Autowired
  public void setStoryDao(StoryDaoInfs storyDao) {
    this.storyDao = storyDao;
  }

  @Autowired
  public void setBookDao(BookDaoInfs bookDao) {
    this.bookDao = bookDao;
  }

  @Autowired
  public void setCopyDao(CopyDaoInfs copyDao) {
    this.copyDao = copyDao;
  }

  @Test
  public void insertTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    Assert.assertNotEquals(0, id);
    boolean isDeleted = readerDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test(expected = HibernateException.class)
  public void insertTwiceTheSameTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    try {
      readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    } catch (HibernateException e) {
      readerDao.delete(id);
      throw new HibernateException(e.getCause());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertNullTest() {
    readerDao.insert(null);
  }

  @Test(expected = HibernateException.class)
  public void insertEmptyTest() {
    readerDao.insert(new Reader());
  }

  @Test
  public void findByIdTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    Assert.assertNotEquals(0, id);
    Assert.assertEquals(READER_NAME, readerDao.findById(id).getName());
    boolean isDeleted = readerDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByZeroIdTest() {
    Reader found = readerDao.findById(0);
    Assert.assertNull(found);
  }

  @Test
  public void findByNegativeIdTest() {
    Reader found = readerDao.findById(-10);
    Assert.assertNull(found);
  }

  @Test
  public void findAllTest() {
    Reader added = new Reader(READER_NAME, READER_BIRTH);
    int id = readerDao.insert(added);
    Assert.assertNotEquals(0, id);
    added.setId(id);
    List<Reader> foundList = readerDao.findAll();
    boolean contained = false;
    for (Reader a : foundList) {
      if (a.getName().equals(added.getName())) {
        contained = true;
      }
    }
    Assert.assertTrue(contained);
    boolean isDeleted = readerDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void updateTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    Reader reader = readerDao.findById(id);
    reader.setName(READER_NAME+READER_NAME);
    Reader updated = readerDao.update(reader);
    Assert.assertEquals(updated.getName(), reader.getName());
    readerDao.delete(id);
  }

  @Test
  public void findByNameTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    Assert.assertNotEquals(0, id);
    List<Reader> foundList = readerDao.findByName(READER_NAME);
    boolean contained = false;
    for (Reader a : foundList) {
      if (a.getId() == id) {
        contained = true;
      }
    }
    Assert.assertTrue(contained);
    boolean isDeleted = readerDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByNullNameTest() {
    List<Reader> found = readerDao.findByName(null);
    Assert.assertTrue(found.isEmpty());
  }

  @Test
  public void findByNotExistingNameTest() {
    List<Reader> found = readerDao.findByName(READER_NAME);
    Assert.assertTrue(found.isEmpty());
  }

  @Test
  public void getBlackList() {
    int bookId = bookDao.insert(new Book("TEST BOOK"));
    Book book = bookDao.findById(bookId);

    int readerId = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    Reader reader = readerDao.findById(readerId);

    int copyId = copyDao.insert(new Copy(book, false));
    Copy copy = copyDao.findById(copyId);

    int storyId = storyDao.insert(new Story(reader, book, copy, READER_BIRTH));

    List<Reader> foundList = readerDao.findAll();
    boolean contained = false;
    for (Reader a : foundList) {
      if (a.getId() == readerId) {
        contained = true;
        break;
      }
    }

    Assert.assertTrue(contained);

    storyDao.delete(storyId);
    copyDao.delete(copyId);
    readerDao.delete(readerId);
    bookDao.delete(bookId);
  }

  @Test
  public void listOfTookBookForNonExistingTest() {
    Map<Reader, List<Book>> bookListMap = readerDao.listOfTookBook(READER_NAME);
    Assert.assertTrue(bookListMap.isEmpty());
  }

  @Test
  public void listOfTookBookForNullTest() {
    Map<Reader, List<Book>> bookListMap = readerDao.listOfTookBook(null);
    Assert.assertTrue(bookListMap.isEmpty());
  }

  @Test
  public void listOfNotReturnedForNonExistingTest() {
    Map<Reader, List<Book>> bookListMap = readerDao.listOfNotReturnedBook(READER_NAME);
    Assert.assertTrue(bookListMap.isEmpty());
  }

  @Test
  public void listOfNotReturnedForNullTest() {
    Map<Reader, List<Book>> bookListMap = readerDao.listOfNotReturnedBook(null);
    Assert.assertTrue(bookListMap.isEmpty());
  }

  @Test
  public void findRegistrationDateNonExistedTest() {
    Map<Reader, LocalDate> readerRegistrationDate = readerDao.findRegistrationDate(READER_NAME);
    Assert.assertTrue(readerRegistrationDate.isEmpty());
  }

  @Test
  public void findRegistrationDateNullTest() {
    Map<Reader, LocalDate> readerRegistrationDate = readerDao.findRegistrationDate(null);
    Assert.assertTrue(readerRegistrationDate.isEmpty());
  }

  @Test
  public void getAvgReaderTest() {
    int id = readerDao.insert(new Reader(READER_NAME, READER_BIRTH));
    double avgAge = readerDao.getAvgReader();
    Assert.assertTrue(avgAge > 0);
    readerDao.delete(id);
  }

  @Test
  public void getAvgAgeByNullAuthorTest() {
    double avgAge = readerDao.getAvgAgeByAuthor(null);
    Assert.assertEquals(0, avgAge, 0.0);
  }

  @Test(expected = PersistenceException.class)
  public void getAvgAgeByEmptyAuthorTest() {
    List<Book> booksForAuthor = new ArrayList<>();
    readerDao.getAvgAgeByAuthor(booksForAuthor);
  }
}
