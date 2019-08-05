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

/**
 * The class implements {@link StoryDaoInfs interface}. Contains CRUD operations for {@link Story
 * entity class}
 */
@Transactional
@Repository
public class StoryDao implements StoryDaoInfs {

  private static final String TAG = StoryDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /** {@inheritDoc} */
  @Override
  public int insert(Story story) throws HibernateException, IllegalArgumentException {
    Session session = sessionFactory.getCurrentSession();
    return (int) session.save(story);
  }

  /** {@inheritDoc} */
  @Override
  public Story findById(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.find(Story.class, id);
  }

  /** {@inheritDoc} */
  @Override
  public List<Story> findAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("SELECT s FROM Story s", Story.class).list();
  }

  /** {@inheritDoc} */
  @Override
  public Story update(Story story) {
    Session session = sessionFactory.getCurrentSession();
    session.update(story);
    return session.find(Story.class, story.getId());
  }

  /** {@inheritDoc} */
  @Override
  public boolean delete(int id) {
    Session session = sessionFactory.getCurrentSession();
    Story story = session.find(Story.class, id);
    session.delete(story);
    return (null == session.find(Story.class, id));
  }


}
