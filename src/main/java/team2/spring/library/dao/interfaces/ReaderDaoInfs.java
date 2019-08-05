package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReaderDaoInfs extends Dao<Reader> {

  List<Reader> findByName(String name);

  List<Reader> getBlackList();

  Map<Reader, List<Book>> listOfTookBook(String readerName);

  Map<Reader, List<Book>> listOfNotReturnedBook(String readerName);

  Map<Reader, LocalDate> findRegistrationDate(String readerName);

  double getAvgReader();

  long getCountOfVisiting(LocalDate firstPeriod, LocalDate secondPeriod);

  double getAvgAgeByAuthor(List<Book> books) throws PersistenceException;

  Map<Reader, LocalDate> getUsingPeriod();
}
