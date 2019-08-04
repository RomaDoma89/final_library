package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderAvgDto {
  private Author author;
  private Book book;
  private double avgByAuthor;
  private double AvgByBook;
}
