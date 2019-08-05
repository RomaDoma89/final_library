package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.*;
import team2.spring.library.dto.BookByPeriodDto;
import team2.spring.library.dto.BookDto;
import team2.spring.library.dto.BookStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;
import team2.spring.library.entities.Story;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private AuthorDaoInfs authorDao;
  private BookDaoInfs bookDao;
  private StoryDaoInfs storyDao;
  private CopyDaoInfs copyDao;

  /**
   * @param bookDto
   * @return
   */
  @Override
  public BookDto isBookAvailable(BookDto bookDto) {
    bookDto.setAvailable(bookDao.isBookAvailable(bookDto.getTitle()));
    return bookDto;
  }

  /** @return */
  @Override
  public List<Book> findAll() {
    return bookDao.findAll();
  }

  /**
   * @param author
   * @return
   */
  @Override
  public List<Book> findBooksByAuthor(Author author) {
    Author authorFromDao = authorDao.findByName(author.getName());
    return bookDao.findBooksByAuthor(authorFromDao);
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
     bookByPeriodDto.setCountOfBookByPeriod(bookDao.getCountOfBookByPeriod(bookByPeriodDto.getDateFrom(),bookByPeriodDto.getDateTo()));
    return 0;
  }

  /**
   * @param bookStatisticDto
   * @return
   */
  @Override
  public BookStatisticDto getBookStatisticDto(BookStatisticDto bookStatisticDto) {
    bookStatisticDto.setGetAvgTimeOfUsage(
        bookDao.getAvgTimeOfUsage(bookStatisticDto.getTitle()));
    bookStatisticDto.setGetUsageCountForCopies(
        bookDao.getUsageCountForCopies(bookStatisticDto.getTitle()));
    bookStatisticDto.setTotalUsageCount(
        bookDao.getTotalUsageCount(bookStatisticDto.getTitle()));
    return bookStatisticDto;
  }

  /**
   * @param book object which need for searching copies
   * @return List of copies
   */
  @Override
  public List<Copy> getCopiesInfo(Book book) {
    return bookDao.getCopiesInfo(book.getTitle());
  }

  @Override
  public List<Book> deleteBook(int id) throws IllegalArgumentException, DataIntegrityViolationException {
    Book book = bookDao.findById(id);
    if (null != book) {
      List<Story> stories = storyDao.findByBook(book);
      for (Story s : stories) {
        storyDao.delete(s.getId());
      }
      List<Copy> copies = copyDao.findByBook(book);
      for (Copy c : copies) {
        copyDao.delete(c.getId());
      }
      bookDao.delete(id);
    }
    return bookDao.findAll();
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
    return bookDao.getPopular(firstDate, secondDate);
  }
}
