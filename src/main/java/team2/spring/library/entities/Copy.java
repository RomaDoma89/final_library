package team2.spring.library.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book_copy")
public class Copy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book", nullable = false)
  private Book book;

  @Column(name = "available", columnDefinition = "BIT", length = 1)
  private boolean available;

  public Copy(Book book, boolean available) {
    this.book = book;
    this.available = available;
  }
}
