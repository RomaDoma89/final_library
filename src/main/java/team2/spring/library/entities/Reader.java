package team2.spring.library.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "reader")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "name", length = 100, unique = true, nullable = false)
  private String name;

  @Column(name = "birthday", nullable = false)
  private LocalDate birthday;

  public Reader(String name, LocalDate birthday) {
    this.name = name;
    this.birthday = birthday;
  }

  //TODO override by @Data
}
