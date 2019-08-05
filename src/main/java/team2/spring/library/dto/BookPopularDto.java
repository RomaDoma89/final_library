package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.spring.library.entities.Book;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPopularDto {
  TreeMap<Long, Book> map;
}
