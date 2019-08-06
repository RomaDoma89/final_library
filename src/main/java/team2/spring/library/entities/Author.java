package team2.spring.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "name", length = 100, unique = true, nullable = false)
  private String name;

  @ManyToMany(cascade = {CascadeType.REFRESH}, mappedBy = "authors", fetch = FetchType.LAZY)
  private Set<Book> books = new HashSet<>();

  public Author(String name) {
    this.name = name;
  }

  public Author(int id, String name) {
    this.name = name;
  }
}
