package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Author;

import javax.persistence.NoResultException;
import java.util.List;

public interface AuthorDaoInfs extends Dao<Author> {

    Author findByName(String name) throws NoResultException;
}
