package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderAvgDto;
import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {
  ReaderDaoInfs readerDaoInfs;
  BookDaoInfs bookDaoInfs;
  AuthorDaoInfs authorDaoInfs;

  /**
   * Finds all readers.
   *
   * @return list of the available readers.
   */
  @Override
  public List<Reader> getAllReaders() {
    return readerDaoInfs.findAll();
  }

  /**
   * Finds a reader by given name.
   *
   * @param name of the reader.
   * @return an object of the found reader.
   */
  @Override
  public List<Reader> findByName(String name) {
    return readerDaoInfs.findByName(name);
  }

  /**
   * Method witch return black list if readers
   *
   * @return return black list of readers
   */
  @Override
  public List<Reader> getBlackList() {
    return readerDaoInfs.getBlackList();
  }

  /**
   * Method witch return information about read book or not returned book or date of reader
   * registration
   *
   * @param readerStatisticDto Name of reader and select with type of returned information
   * @return Dto with certain information depends on select
   */
  @Override
  public ReaderStatisticDto getUserStatistic(ReaderStatisticDto readerStatisticDto) {
    if (readerStatisticDto.getSelect().equals("read")) {
      readerStatisticDto.setReaderListMap(
          readerDaoInfs.listOfTookBook(readerStatisticDto.getName()));
      return readerStatisticDto;
    } else if (readerStatisticDto.getSelect().equals("ordered")) {
      readerStatisticDto.setReaderListMap(
          readerDaoInfs.listOfNotReturnedBook(readerStatisticDto.getName()));
      return readerStatisticDto;
    } else {
      readerStatisticDto.setReaderDateMap(
          readerDaoInfs.findRegistrationDate(readerStatisticDto.getName()));
      return readerStatisticDto;
    }
  }

  /** @return statistics about average age of readers */
  public GeneralStatisticDto getGeneralStatisticDto() {
    GeneralStatisticDto generalStatisticDto = new GeneralStatisticDto();
    generalStatisticDto.setAvgAgeOfReaders(readerDaoInfs.getAvgReader());
    return generalStatisticDto;
  }

  /**
   * @param author object which need for searching average age bu author
   * @return double
   */
  @Override
  public double getAvgAgeByAuthor(Author author) {
    Author authorToFind = authorDaoInfs.findByName(author.getName());
    List<Book> list = bookDaoInfs.findBooksByAuthor(authorToFind);
    return readerDaoInfs.getAvgAgeByAuthor(list);
  }

  /**
   * @param author object which need for searching average age bu author
   * @param book object which need for searching average age bu author
   * @return ReaderAvgDto
   */
  @Override
  public ReaderAvgDto getBothAvg(Author author, Book book) {
    ReaderAvgDto readerAvgDto = new ReaderAvgDto();
    readerAvgDto.setAvgByBook(Math.round(bookDaoInfs.getReaderAvgByBook(book.getTitle())));
    readerAvgDto.setAvgByAuthor(Math.round(getAvgAgeByAuthor(author)));
    return readerAvgDto;
  }
}
