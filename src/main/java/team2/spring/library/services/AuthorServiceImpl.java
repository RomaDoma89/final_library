package team2.spring.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.spring.library.dao.interfaces.AuthorDaoInfs;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorDaoInfs authorDaoInfs;

}
