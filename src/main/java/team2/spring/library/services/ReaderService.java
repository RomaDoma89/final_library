package team2.spring.library.services;

import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderAvgDto;
import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.entities.Reader;

import java.text.ParseException;
import java.util.List;

public interface ReaderService {
  /**
   * Finds all readers.
   *
   * @return list of the available readers.
   */
  List<Reader> getAllReaders();

  /**
   * Finds a reader by given name.
   *
   * @param name of the reader.
   * @return an object of the found reader.
   */
  List<Reader> findByName(String name);

  /**
   * Method witch return black list if readers
   *
   * @return return black list of readers
   */
  List<Reader> getBlackList();

  /**
   * Method witch return information about read book or not returned book or date of reader
   * registration
   *
   * @param readerStatisticDto Name of reader and select with type of returned information
   * @return Dto with certain information depends on select
   */
  ReaderStatisticDto getUserStatistic(ReaderStatisticDto readerStatisticDto);

  /**
   * @param generalStatisticDto retrieve GeneralStatisticDto with start and end of chosen statistic
   *     period
   * @return GeneralStatisticDto with all required data
   * @throws ParseException throw exception that input date is not correct
   */
  GeneralStatisticDto getGeneralStatisticDto(GeneralStatisticDto generalStatisticDto)
      throws ParseException;

  /**
   * @param author object which need for searching average age bu author
   * @return double
   */
  double getAvgAgeByAuthor(Author author);

  /**
   * @param author object which need for searching average age bu author
   * @param book object which need for searching average age bu author
   * @return ReaderAvgDto - object which contains average age by author and by book
   */
  ReaderAvgDto getBothAvg(Author author, Book book);
}
