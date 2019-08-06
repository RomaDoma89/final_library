package team2.spring.library.services;

import org.springframework.dao.DataIntegrityViolationException;
import team2.spring.library.dto.BookByPeriodDto;
import team2.spring.library.dto.BookDto;
import team2.spring.library.dto.BookStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public interface BookService {
  /**
   * @param bookDto dto witch contain book title what is looking for
   * @return bookDto with information of available book status
   */
  BookDto isBookAvailable(BookDto bookDto);

  /** @return List with all book in library */
  List<Book> findAll();

  /**
   * @param author witch contain name
   * @return list books written by author
   */
  List<Book> findBooksByAuthor(Author author);

  /**
   * @param bookByPeriodDto contain two date star and end of period
   * @return amount of ordered book in library by chosen period
   * @throws ParseException throw exception that input date is not correct
   */
  long getCountOfBookByPeriod(BookByPeriodDto bookByPeriodDto) throws ParseException;

  /**
   * @param bookStatisticDto contain book title
   * @return return BookStatisticDto with statistic of book
   */
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

  /**
   * @param firstDate start date
   * @param secondDate end date
   * @return TreeMap<Long, Book>
   * @throws ParseException if date is not valid
   */
  TreeMap<Long, Book> getPopular(LocalDate firstDate, LocalDate secondDate) throws ParseException;

  /**
   * Update book title
   *
   * @param bookDto set new title in entity
   * @return updated book
   */
  Book update(BookDto bookDto);
}
