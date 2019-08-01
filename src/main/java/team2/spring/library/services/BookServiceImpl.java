package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private AuthorDaoInfs authorDaoInfs;
  private BookDaoInfs bookDaoInfs;

  @Override
  public BookDto isBookAvailable(BookDto bookDto) {
    bookDto.setAvailable(bookDaoInfs.isBookAvailable(bookDto.getTitle()));
    return bookDto;
  }

  @Override
  public List<Book> findAll() {
    return bookDaoInfs.findAll();
  }

  @Override
  public List<Book> findBooksByAuthor(Author author) {
    Author authorFromDao = authorDaoInfs.findByName(author.getName());
    return bookDaoInfs.findBooksByAuthor(authorFromDao);
  }
}
