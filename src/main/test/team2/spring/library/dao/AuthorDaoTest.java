package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team2.spring.library.configs.TestConfig;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.entities.Author;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class AuthorDaoTest {
  private static final String AUTHOR_NAME = "Roma Zahorui";
  private static final String TAG = AuthorDaoTest.class.getName();
  private AuthorDaoInfs authorDao;

  @Autowired
  public void setAuthorDao(AuthorDaoInfs authorDao) {
    this.authorDao = authorDao;
  }

  @Test
  public void insertTest() {
    int id = authorDao.insert(new Author(AUTHOR_NAME));
    Assert.assertNotEquals(0, id);
    boolean isDeleted = authorDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test(expected = HibernateException.class)
  public void insertTwiceTheSameTest() {
    int id = authorDao.insert(new Author(AUTHOR_NAME));
    try {
      authorDao.insert(new Author(AUTHOR_NAME));
    } catch (HibernateException e) {
      authorDao.delete(id);
      throw new HibernateException(e.getCause());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertNullTest() {
    authorDao.insert(null);
  }

  @Test(expected = HibernateException.class)
  public void insertEmptyTest() {
    authorDao.insert(new Author());
  }

  @Test
  public void findByIdTest() {
    int id = authorDao.insert(new Author(AUTHOR_NAME));
    Assert.assertNotEquals(0, id);
    Assert.assertEquals(AUTHOR_NAME, authorDao.findById(id).getName());
    boolean isDeleted = authorDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByZeroIdTest() {
    Author found = authorDao.findById(0);
    Assert.assertNull(found);
  }

  @Test
  public void findByNegativeIdTest() {
    Author found = authorDao.findById(-10);
    Assert.assertNull(found);
  }

  @Test
  public void updateTest() {
    int id = authorDao.insert(new Author(AUTHOR_NAME));
    Author author = authorDao.findById(id);
    author.setName(AUTHOR_NAME+AUTHOR_NAME);
    Author updated = authorDao.update(author);
    Assert.assertEquals(updated.getName(), author.getName());
    authorDao.delete(id);
  }

  @Test
  public void findAllTest() {
    Author added = new Author(AUTHOR_NAME);
    int id = authorDao.insert(added);
    Assert.assertNotEquals(0, id);
    added.setId(id);
    List<Author> foundList = authorDao.findAll();
    boolean contained = false;
    for (Author a : foundList) {
      if (a.getName().equals(added.getName())) {
        contained = true;
      }
    }
    Assert.assertTrue(contained);
    boolean isDeleted = authorDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByNameTest() {
    int id = authorDao.insert(new Author(AUTHOR_NAME));
    Assert.assertNotEquals(0, id);
    Assert.assertEquals(AUTHOR_NAME, authorDao.findByName(AUTHOR_NAME).getName());
    boolean isDeleted = authorDao.delete(id);
    Assert.assertTrue(isDeleted);
  }

  @Test
  public void findByNullNameTest() {
    Author found = authorDao.findByName(null);
    Assert.assertNull(found);
  }

  @Test
  public void findByNotExistingNameTest() {
    Author found = authorDao.findByName(AUTHOR_NAME);
    Assert.assertNull(found);
  }
}
