package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Author;

public interface AuthorDaoInfs extends Dao<Author> {

  Author findByName(String name);
}
