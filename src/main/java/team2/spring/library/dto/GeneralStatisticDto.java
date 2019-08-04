package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralStatisticDto {
  private long avgDaysOfReading;
  private double avgAgeOfReaders;
  private int avgVisitOfLibrary;
}
