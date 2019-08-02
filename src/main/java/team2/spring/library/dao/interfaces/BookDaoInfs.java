package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Copy;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookDaoInfs extends Dao<Book> {

  Book findByTitle(String title);

  List<Book> findBooksByAuthor(Author author);

  long isBookAvailable(String title);

  List<Copy> getCopiesInfo(String title);

  long getCountOfBookByPeriod(LocalDate fromDate, LocalDate toDate);

  long getTotalUsageCount(String title);

  Map<Copy, Long> getUsageCountForCopies(String title);

  Double getAvgTimeOfUsage(String title);

  double getReaderAvgByBook(String title);

  Map<Book, Long> getPopular(LocalDate firstPeriod, LocalDate secondPeriod);
}
