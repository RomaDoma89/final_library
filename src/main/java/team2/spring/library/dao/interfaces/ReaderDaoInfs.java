package team2.spring.library.dao.interfaces;

import team2.spring.library.entities.Reader;

import javax.persistence.NoResultException;
import java.util.List;

public interface ReaderDaoInfs extends Dao<Reader> {

    List<Reader> findByName(String name) throws NoResultException;

    List<Reader> getBlackList() throws NoResultException;
}
