package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Book;
import team2.spring.library.entities.Story;

import java.time.LocalDate;
import java.util.List;

public interface StoryDaoInfs extends Dao<Story> {

  List<Story> findByBook(Book book);

  Long getCountOfVisiting(LocalDate firstPeriod, LocalDate secondPeriod);
}
