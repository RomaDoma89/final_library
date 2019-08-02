package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookByPeriodDto {

  private String dateFrom;
  private String dateTo;
  private long countOfBookByPeriod;
}
