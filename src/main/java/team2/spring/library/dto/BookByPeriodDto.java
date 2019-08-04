package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookByPeriodDto {

  private LocalDate dateFrom;
  private LocalDate  dateTo;
  private long countOfBookByPeriod;
}
