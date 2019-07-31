package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

import team2.spring.library.dao.interfaces.CopyDaoInfs;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

@Transactional
@Repository
public class CopyDao implements CopyDaoInfs {

  private static final String TAG = CopyDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public CopyDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Copy copy) {
    int id = -1;
    try (Session session = sessionFactory.getCurrentSession()) {
      id = (int) session.save(copy);
    }
    return id;
  }

  @Override
  public Copy findById(int id) {
    Copy copy = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      copy = session.find(Copy.class, id);
    }
    return copy;
  }

  @Override
  public List<Copy> findAll() {
    List<Copy> copies = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      copies = session.createQuery("SELECT c FROM Copy c", Copy.class).getResultList();
    }
    return copies;
  }

  /**
   * Updates an entity in database.
   *
   * @param copy with updated fields.
   * @return updated entity.
   */
  @Override
  public Copy update(Copy copy) {
    Copy updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(copy);
      updated = session.find(Copy.class, copy.getId());
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
      Copy copy = session.find(Copy.class, id);
      session.delete(copy);
      if (null == session.find(Copy.class, id)) {
        isDeleted = true;
      }
    }
    return isDeleted;
  }

  @Override
  public List<Copy> getAllCopies(Book book) throws NoResultException {
    return null;
  }

  @Override
  public List<Copy> getAvailableCopies(Book book) throws NoResultException {
    return null;
  }

  @Override
  public List<Copy> getUnavailableCopies(Book book) throws NoResultException {
    return null;
  }
}
