package team2.spring.library.services;

import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {
  BookDto isBookAvailable(BookDto bookDto);

  List<Book> findAll();

  List<Book> findBooksByAuthor(Author author);
  long getCountOfBookByPeriod(String fromDate, String toDate) throws ParseException;
}
