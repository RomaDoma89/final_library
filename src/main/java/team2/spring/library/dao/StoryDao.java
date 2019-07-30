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
    int id = -1;
    try (Session session = sessionFactory.getCurrentSession()) {
      id = (int) session.save(story);
    }
    return id;
  }

  @Override
  public Story findById(int id) {
    Story story = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      story = session.find(Story.class, id);
    }
    return story;
  }

  @Override
  public List<Story> findAll() {
    List<Story> stories = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      stories = session.createQuery("SELECT s FROM Story s", Story.class).getResultList();
    }
    return stories;
  }

  @Override
  public Story update(Story story) {
    Story updated = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.update(story);
      updated = session.find(Story.class, story.getId());
    }
    return updated;
  }

  @Override
  public boolean delete(int id) {
    boolean isDeleted = false;
    try (Session session = sessionFactory.getCurrentSession()) {
      Story story = session.find(Story.class, id);
      session.delete(story);
      if (null == session.find(Story.class, id)) {
        isDeleted = true;
      }
    }
    return isDeleted;
  }
}
