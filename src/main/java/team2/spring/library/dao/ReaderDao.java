package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.entities.Reader;

@Transactional
@Repository
public class ReaderDao implements ReaderDaoInfs {

  private static final String TAG = ReaderDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public ReaderDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Reader reader) {
    int id = -1;
    try (Session session = sessionFactory.getCurrentSession()) {
      id = (int) session.save(reader);
    }
    return id;
  }

  @Override
  public Reader findById(int id) {
    Reader reader = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      reader = session.find(Reader.class, id);
    }
    return reader;
  }

  @Override
  public List<Reader> findAll() {
    List<Reader> readers = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      readers = session.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
    }
    return readers;
  }

  @Override
  public Reader update(Reader reader) {
    Reader updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(reader);
      updated = session.find(Reader.class, reader.getId());
    }
    return updated;
  }

  @Override
  public boolean delete(int id) {
    boolean isDeleted = false;
    try (Session session = sessionFactory.getCurrentSession()) {
      Reader reader = session.find(Reader.class, id);
      session.delete(reader);
      if (null == session.find(Reader.class, id)) {
        isDeleted = true;
      }
    }
    return isDeleted;
  }

  @Override
  public List<Reader> findByName(String name) throws NoResultException {
    List<Reader> readers = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      readers = session.createQuery("SELECT r FROM Reader r WHERE r.name = ?1")
              .setParameter(1, name)
              .getResultList();
    }
    return readers;
  }

  public List<Reader> getBlackList() throws NoResultException {
    return null;
  }
}
