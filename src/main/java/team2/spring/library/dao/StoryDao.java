package team2.spring.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

import team2.spring.library.dao.interfaces.StoryDaoInfs;
import team2.spring.library.entities.Story;

@Transactional
@Repository
public class StoryDao implements StoryDaoInfs {

  private static final String TAG = StoryDao.class.getName();
  private SessionFactory sessionFactory;

  @Autowired
  public StoryDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int insert(Story story) {
    try (Session session = sessionFactory.getCurrentSession()) {
      return (int) session.save(story);
    }
  }

  @Override
  public Story findById(int id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      return session.find(Story.class, id);
    }
  }

  @Override
  public List<Story> findAll() {
    try (Session session = sessionFactory.getCurrentSession()) {
      return session.createQuery("SELECT s FROM Story s", Story.class).getResultList();
    }
  }

  /**
   * Updates an entity in database.
   *
   * @param story with updated fields.
   * @return updated entity.
   */
  @Override
  public Story update(Story story) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(story);
      return session.find(Story.class, story.getId());
    }
  }

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  @Override
  public boolean delete(int id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      Story story = session.find(Story.class, id);
      session.delete(story);
      return (null == session.find(Story.class, id));
    }
  }
}
