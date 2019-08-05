package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
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

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private AuthorDaoInfs authorDaoInfs;
  private BookDaoInfs bookDaoInfs;

  /**
   * @param bookDto
   * @return
   */
  @Override
  public BookDto isBookAvailable(BookDto bookDto) {
    bookDto.setAvailable(bookDaoInfs.isBookAvailable(bookDto.getTitle()));
    return bookDto;
  }

  /** @return */
  @Override
  public List<Book> findAll() {
    return bookDaoInfs.findAll();
  }

  /**
   * @param author
   * @return
   */
  @Override
  public List<Book> findBooksByAuthor(Author author) {
    Author authorFromDao = authorDaoInfs.findByName(author.getName());
    return bookDaoInfs.findBooksByAuthor(authorFromDao);
  }

  /**
   * @param bookByPeriodDto
   * @return
   * @throws ParseException
   */
  @Override
  public long getCountOfBookByPeriod(BookByPeriodDto bookByPeriodDto) throws ParseException {
    if (bookByPeriodDto.getDateFrom().compareTo(bookByPeriodDto.getDateTo()) > 0
        || bookByPeriodDto.getDateFrom().compareTo(bookByPeriodDto.getDateTo()) == 0) {
      throw new ParseException("Date to is lower then date from ", 0);
    }
     bookByPeriodDto.setCountOfBookByPeriod(bookDaoInfs.getCountOfBookByPeriod(bookByPeriodDto.getDateFrom(),bookByPeriodDto.getDateTo()));
    return 0;
  }

  /**
   * @param bookStatisticDto
   * @return
   */
  @Override
  public BookStatisticDto getBookStatisticDto(BookStatisticDto bookStatisticDto) {
    bookStatisticDto.setGetAvgTimeOfUsage(
        bookDaoInfs.getAvgTimeOfUsage(bookStatisticDto.getTitle()));
    bookStatisticDto.setGetUsageCountForCopies(
        bookDaoInfs.getUsageCountForCopies(bookStatisticDto.getTitle()));
    bookStatisticDto.setTotalUsageCount(
        bookDaoInfs.getTotalUsageCount(bookStatisticDto.getTitle()));
    return bookStatisticDto;
  }

  /**
   * @param book object which need for searching copies
   * @return List of copies
   */
  @Override
  public List<Copy> getCopiesInfo(Book book) {
    return bookDaoInfs.getCopiesInfo(book.getTitle());
  }

  /**
   * @param firstDate start date
   * @param secondDate end date
   * @return TreeMap<Long, Book>
   * @throws ParseException if date is not valid
   */
  @Override
  public TreeMap<Long, Book> getPopular(LocalDate firstDate, LocalDate secondDate)
      throws ParseException {
    if (firstDate.compareTo(secondDate) > 0 || firstDate.compareTo(secondDate) == 0) {
      throw new ParseException("Input date is invalid, it's lower than ", 0);
    }
    return bookDaoInfs.getPopular(firstDate, secondDate);
  }
}
