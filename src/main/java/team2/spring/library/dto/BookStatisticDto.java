package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.spring.library.entities.Copy;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStatisticDto {
  @NotBlank
  private String title;
  private long totalUsageCount;
  private Map<Copy, Long> getUsageCountForCopies;
  private double getAvgTimeOfUsage;
}
