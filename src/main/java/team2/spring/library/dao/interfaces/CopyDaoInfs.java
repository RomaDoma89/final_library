package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public interface CopyDaoInfs extends Dao<Copy> {

  List<Copy> getAllCopies(Book book);

  List<Copy> getAvailableCopies(Book book);

  List<Copy> getUnavailableCopies(Book book);
}
