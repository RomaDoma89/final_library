package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Story;

import java.time.LocalDate;

public interface StoryDaoInfs extends Dao<Story> {
    Long getCountOfVisiting(LocalDate firstPeriod, LocalDate secondPeriod);
}
