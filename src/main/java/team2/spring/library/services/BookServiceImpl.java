package team2.spring.library.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import sun.util.resources.LocaleData;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

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

  @Override
  public long getCountOfBookByPeriod(String fromDate, String toDate) throws ParseException {
    Date dateFrom=new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
    Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
    if(dateFrom.compareTo(dateTo)>0||dateFrom.compareTo(dateTo)==0){
      throw new ParseException("date to is lover then date from ",0);
    }
    return bookDaoInfs.getCountOfBookByPeriod(dateFrom, dateTo);
  }
}
