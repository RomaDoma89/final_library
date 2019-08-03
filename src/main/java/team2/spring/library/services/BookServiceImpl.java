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

import java.text.ParseException;
import java.util.List;

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
    System.out.println(bookByPeriodDto);
    if (bookByPeriodDto.getDateFrom().compareTo(bookByPeriodDto.getDateTo()) > 0 || bookByPeriodDto.getDateFrom().compareTo(bookByPeriodDto.getDateTo()) == 0) {
      throw new ParseException("date to is lover then date from ", 0);
    }
    //Dont be angry  ,I`m working in them
//    bookByPeriodDto.setCountOfBookByPeriod(bookDaoInfs.getCountOfBookByPeriod(bookByPeriodDto.getDateFrom(),bookByPeriodDto.getDateTo()));
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
}
