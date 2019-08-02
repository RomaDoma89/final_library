package team2.spring.library.dao.interfaces;

import team2.spring.library.dao.interfaces.Dao;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReaderDaoInfs extends Dao<Reader> {

  List<Reader> findByName(String name);

  List<Reader> getBlackList();

  Map<Reader, List<Book>> listOfTookBook(String readerName);

  Map<Reader, List<Book>> listOfNotReturnedBook(String readerName);

  Map<Reader, Date> findRegistrationDate(String readerName);

  double getAvgReader();
}
