package team2.spring.library.services;

import org.springframework.dao.DataIntegrityViolationException;
import team2.spring.library.dto.BookByPeriodDto;
import team2.spring.library.dto.BookDto;
import team2.spring.library.dto.BookStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import java.text.ParseException;
import java.util.List;

public interface BookService {
  BookDto isBookAvailable(BookDto bookDto);

  List<Book> findAll();

  List<Book> findBooksByAuthor(Author author);

  long getCountOfBookByPeriod(BookByPeriodDto bookByPeriodDto) throws ParseException;

  BookStatisticDto getBookStatisticDto(BookStatisticDto bookStatisticDto);

  /**
   * @param book object which need for searching copies
   * @return List of copies
   */
  List<Copy> getCopiesInfo(Book book);

  /**
   * Tries to delete a book by id.
   *
   * @param id of the book.
   * @return list of the existed book after the deletion.
   */
  List<Book> deleteBook(int id) throws IllegalArgumentException, DataIntegrityViolationException;
}
