package team2.spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.spring.library.entities.Story;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderStatisticDto {
    @NotBlank
    private String name;
    @NotBlank
    private String select;
    private List<Story> stories;
    // todo  change to date or not
    private Story story;
}
