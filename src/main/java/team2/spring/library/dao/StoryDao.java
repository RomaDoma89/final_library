package team2.spring.library.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team2.spring.library.dao.interfaces.StoryDaoInfs;
import team2.spring.library.entities.Story;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class StoryDao implements StoryDaoInfs {

  private static final String TAG = StoryDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Story story) throws HibernateException, IllegalArgumentException {
    Session session = sessionFactory.getCurrentSession();
    return (int) session.save(story);
  }

  @Override
  public Story findById(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.find(Story.class, id);
  }

  @Override
  public List<Story> findAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT s FROM Story s", Story.class).list();
  }

  /**
   * Updates an entity in database.
   *
   * @param story with updated fields.
   * @return updated entity.
   */
  @Override
  public Story update(Story story) {
    Session session = sessionFactory.getCurrentSession();
    session.update(story);
    return session.find(Story.class, story.getId());
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
    Story story = session.find(Story.class, id);
    session.delete(story);
    return (null == session.find(Story.class, id));
  }

  /**
   * Find count of visiting by period
   *
   * @param firstPeriod
   * @param secondPeriod
   * @return Long
   */
  @Override
  public Long getCountOfVisiting(LocalDate firstPeriod, LocalDate secondPeriod) {
    Session session = sessionFactory.openSession();
    return session
        .createQuery(
            "SELECT COUNT(s.timeTake) FROM Story s"
                + " WHERE s.timeTake BETWEEN :firstPeriod AND :secondPeriod",
            Long.class)
        .setParameter("firstPeriod", firstPeriod)
        .setParameter("secondPeriod", secondPeriod)
        .getSingleResult();
  }
}
