package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.spring.library.entities.Reader;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralStatisticDto {
  private LocalDate dateFrom;
  private LocalDate  dateTo;
  private long avgDaysOfReading;
  private double avgAgeOfReaders;
  private Map<Reader, Long> avgVisitOfLibrary;
}
