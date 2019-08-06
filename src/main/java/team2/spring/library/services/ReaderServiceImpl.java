package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;
import team2.spring.library.dao.interfaces.BookDaoInfs;
import team2.spring.library.dao.interfaces.ReaderDaoInfs;
import team2.spring.library.dao.interfaces.StoryDaoInfs;
import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderAvgDto;
import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.entities.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {
  ReaderDaoInfs readerDao;
  BookDaoInfs bookDao;
  AuthorDaoInfs authorDao;
  StoryDaoInfs storyDao;

  /**
   * Finds all readers.
   *
   * @return list of the available readers.
   */
  @Override
  public List<Reader> getAllReaders() {
    return readerDao.findAll();
  }

  /**
   * Finds a reader by given name.
   *
   * @param name of the reader.
   * @return an object of the found reader.
   */
  @Override
  public List<Reader> findByName(String name) {
    return readerDao.findByName(name);
  }

  /**
   * Method witch return black list if readers
   *
   * @return return black list of readers
   */
  @Override
  public List<Reader> getBlackList() {
    return readerDao.getBlackList();
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
          readerDao.listOfTookBook(readerStatisticDto.getName()));
      return readerStatisticDto;
    } else if (readerStatisticDto.getSelect().equals("ordered")) {
      readerStatisticDto.setReaderListMap(
          readerDao.listOfNotReturnedBook(readerStatisticDto.getName()));
      return readerStatisticDto;
    } else {
      readerStatisticDto.setReaderDateMap(
          readerDao.findRegistrationDate(readerStatisticDto.getName()));
      return readerStatisticDto;
    }
  }

  /** @return statistics about average age of readers */
  public GeneralStatisticDto getGeneralStatisticDto(GeneralStatisticDto generalStatisticDto)
      throws ParseException {
    LocalDate localDateNow = LocalDate.now();
    if (generalStatisticDto.getDateFrom().compareTo(generalStatisticDto.getDateTo()) > 0
        || generalStatisticDto.getDateFrom().compareTo(generalStatisticDto.getDateTo()) == 0) {
      throw new ParseException("date to is lover then date from ", 0);
    }
    Map<Reader, LocalDate> readerLocalDateMap = readerDao.getUsingPeriod();
    Map<Reader, Long> readerLongMap =new HashMap<>();
    readerLocalDateMap.forEach(
        (reader, localDate) ->
            readerLongMap.put(reader, ChronoUnit.DAYS.between(localDate, localDateNow)));
    generalStatisticDto.setAvgVisitOfLibrary(readerLongMap);

    generalStatisticDto.setAvgAgeOfReaders(readerDao.getAvgReader());
    generalStatisticDto.setAvgDaysOfReading(
        readerDao.getCountOfVisiting(
            generalStatisticDto.getDateFrom(), generalStatisticDto.getDateTo()));
    return generalStatisticDto;
  }

  /**
   * @param author object which need for searching average age bu author
   * @return double
   */
  @Override
  public double getAvgAgeByAuthor(Author author) {
    Author authorToFind = authorDao.findByName(author.getName());
    List<Book> list = bookDao.findBooksByAuthor(authorToFind);
    return readerDao.getAvgAgeByAuthor(list);
  }

  /**
   * @param author object which need for searching average age bu author
   * @param book object which need for searching average age bu author
   * @return ReaderAvgDto
   */
  @Override
  public ReaderAvgDto getBothAvg(Author author, Book book) {
    ReaderAvgDto readerAvgDto = new ReaderAvgDto();
    readerAvgDto.setAvgByBook(Math.round(bookDao.getReaderAvgByBook(book.getTitle())));
    readerAvgDto.setAvgByAuthor(Math.round(getAvgAgeByAuthor(author)));
    return readerAvgDto;
  }

  /**
   * Tries to delete a reader by id.
   *
   * @param id of the reader.
   * @return list of the existed reader after the deletion.
   * @throws IllegalArgumentException if a reader with the id is not exists.
   * @throws DataIntegrityViolationException if a reader with the id is not exists.
   */
  @Override
  public List<Reader> deleteReader(int id) throws IllegalArgumentException, DataIntegrityViolationException {
    Reader reader = readerDao.findById(id);
    if (null != reader) {
      List<Story> stories = storyDao.findByReader(reader);
      for (Story s : stories) {
        storyDao.delete(s.getId());
      }
      readerDao.delete(id);
    }
    return readerDao.findAll();
  }
}
