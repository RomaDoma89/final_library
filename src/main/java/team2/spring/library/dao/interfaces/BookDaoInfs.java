package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import java.util.List;

public interface BookDaoInfs extends Dao<Book> {

  Book findByTitle(String title);

  List<Book> findBooksByAuthor(Author author);

  long isBookAvailable(String title);
}
