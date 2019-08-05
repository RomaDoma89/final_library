package team2.spring.library.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reader_story")
public class Story {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @ManyToOne
  @JoinColumn(name = "reader", nullable = false)
  private Reader reader;

  @ManyToOne
  @JoinColumn(name = "copy", nullable = false)
  private Copy copy;

  @ManyToOne
  @JoinColumn(name = "book", nullable = false)
  private Book book;

  @Column(name = "time_take", nullable = false)
  private LocalDate timeTake;

  @Column(name = "time_return")
  private LocalDate timeReturn;

  public Story(Reader reader, Book book, Copy copy, LocalDate timeTake) {
    this.reader=reader;
    this.book = book;
    this.copy = copy;
    this.timeTake = timeTake;
  }
}
