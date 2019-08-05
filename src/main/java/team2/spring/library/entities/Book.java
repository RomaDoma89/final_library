package team2.spring.library.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
  @NotBlank
  @Column(name = "title", length = 225, unique = true, nullable = false)
  private String title;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_author",
      joinColumns = {@JoinColumn(name = "id_book", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "id_author", nullable = false, updatable = false)})
  private Set<Author> authors = new HashSet<>();

  public Book(String title) {
    this.title = title;
  }
}
