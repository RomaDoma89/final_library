package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import java.util.List;

public interface CopyDaoInfs extends Dao<Copy> {

  List<Copy> findByBook(Book book);
}
