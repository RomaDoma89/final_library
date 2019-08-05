package team2.spring.library.dao.interfaces;

import org.hibernate.HibernateException;

import java.util.List;

/**
 * The interface defines the behavior of all DAP objects for CRUD operations.
 *
 * @param <E> is a type of entity.
 */
public interface Dao<E> {

  /**
   * Inserts an entity object to a db
   *
   * @param entity to insert.
   * @return the id of the inserted entity.
   * @throws HibernateException in cases when the object is not fully initialized or inserted more
   *     than once.
   * @throws IllegalArgumentException if the entity is null
   */
  int insert(E entity) throws HibernateException, IllegalArgumentException;

  /**
   * Finds an entity by id parameter
   *
   * @param id of the desired entity.
   * @return the found entity.
   */
  E findById(int id);

  /**
   * Finds all entities of given type.
   *
   * @return list of the found entities.
   */
  List<E> findAll();

  /**
   * Updates an entity in database.
   *
   * @param entity with updated fields.
   * @return updated entity.
   */
  E update(E entity);

  /**
   * Deletes an entity with given <code>id</code> from database.
   *
   * @param id of the entity to delete.
   * @return true if the entity was successfully deleted.
   */
  boolean delete(int id);
}
