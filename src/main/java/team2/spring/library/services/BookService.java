package team2.spring.library.services;

import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Book;

public interface BookService {
    BookDto isBookAvailable(BookDto bookDto);
}
