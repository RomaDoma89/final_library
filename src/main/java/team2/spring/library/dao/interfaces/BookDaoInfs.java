package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import javax.persistence.NoResultException;
import java.util.List;

public interface BookDaoInfs extends Dao<Book> {

  Book findByTitle(String title) throws NoResultException;

  List<Book> findBooksByAuthor(Author author);
}
