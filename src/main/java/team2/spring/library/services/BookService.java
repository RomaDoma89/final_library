package team2.spring.library.services;

import team2.spring.library.dto.BookDto;

public interface BookService {
    BookDto isBookAvailable(BookDto bookDto);
}
