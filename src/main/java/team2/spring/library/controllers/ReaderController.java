package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderAvgDto;
import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.services.ReaderService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class ReaderController {
  private ReaderService readerService;

  /**
   * @param model set black list of reader in jsp
   * @return page with black list of reader
   */
  @GetMapping("/getBlackList")
  public String getBlackList(Model model) {
    model.addAttribute("readerBlackList", readerService.getBlackList());
    return "/readersJsp/getBlackList";
  }

  /**
   * @param model set readerStatisticDto in jsp
   * @return page with form
   */
  @GetMapping("/readerStatisticForm")
  public String findUserStatisticForm(Model model) {
    model.addAttribute("readerStatisticDto", new ReaderStatisticDto());
    return "readersJsp/readerStatisticForm";
  }

  /**
   * @param readerStatisticDto return name of reader
   * @param model return statistic of reader
   * @return page with user statistic
   */
  @PostMapping("/readerStatistic")
  public String getUserStatistic(
      @Valid @ModelAttribute("readerStatisticDto") ReaderStatisticDto readerStatisticDto,
      Model model) {
    model.addAttribute("readerStatisticDto", readerService.getUserStatistic(readerStatisticDto));
    return "readersJsp/readerStatistic";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/generalStatisticForm")
  public String generalStatisticForm(Model model) {
    return "readersJsp/generalStatistic";
  }
  /**
   * @param model set data in jsp page
   * @return representation of general statistic
   */
  @PostMapping("/generalStatistic")
  public String getGeneralStatistic(@RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
    GeneralStatisticDto generalStatisticDto=new GeneralStatisticDto();
    generalStatisticDto.setDateFrom(LocalDate.parse(dateFrom));
    generalStatisticDto.setDateTo(LocalDate.parse(dateTo));
    try {
      generalStatisticDto=readerService.getGeneralStatisticDto(generalStatisticDto);
    } catch (ParseException e) {
      e.printStackTrace();
      return "error";
    }
    model.addAttribute("generalStatisticDto", generalStatisticDto);
    return "readersJsp/generalStatistic";
  }

  /**
   * @param model set ReaderAvgDto in jsp page
   * @return page with form
   */
  @GetMapping("/readerAvgForm")
  public String getReaderAvgForm(Model model) {
    model.addAttribute("readerAvgDto", new ReaderAvgDto());
    return "readersJsp/readerAvgForm";
  }

  /**
   * @param readerAvgDto object with data
   * @param model set ReaderAvgDto in jsp page
   * @return page with response
   */
  @PostMapping("/readerAvg")
  public String getReaderAvg(
      @Valid @ModelAttribute("readerAvgDto") ReaderAvgDto readerAvgDto, Model model) {
    model.addAttribute(
        "readerAvgDto", readerService.getBothAvg(readerAvgDto.getAuthor(), readerAvgDto.getBook()));
    return "readersJsp/readerAvg";
  }
}
