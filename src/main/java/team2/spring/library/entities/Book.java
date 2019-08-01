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
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "title", length = 225, unique = true, nullable = false)
  private String title;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_author",
      joinColumns = {@JoinColumn(name = "id_book", nullable = false)},
      inverseJoinColumns = {@JoinColumn(name = "id_author", nullable = false)})
  private Set<Author> authors = new HashSet<>();

  public Book(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
  }
}
