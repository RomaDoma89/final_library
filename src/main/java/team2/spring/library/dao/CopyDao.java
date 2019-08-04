package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.CopyDaoInfs;
import team2.spring.library.entities.Copy;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CopyDao implements CopyDaoInfs {

  private static final String TAG = CopyDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Copy copy) throws HibernateException, IllegalArgumentException {
    Session session = sessionFactory.getCurrentSession();
    return (int) session.save(copy);
  }

  @Override
  public Copy findById(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.find(Copy.class, id);
  }

  @Override
  public List<Copy> findAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT c FROM Copy c", Copy.class).list();
  }

  /**
   * Updates an entity in database.
   *
   * @param copy with updated fields.
   * @return updated entity.
   */
  @Override
  public Copy update(Copy copy) {
    Session session = sessionFactory.getCurrentSession();
    session.update(copy);
    return session.find(Copy.class, copy.getId());
  }

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  @Override
  public boolean delete(int id) {
    Session session = sessionFactory.getCurrentSession();
    Copy copy = session.find(Copy.class, id);
    session.delete(copy);
    return (null == session.find(Copy.class, id));
  }
}
