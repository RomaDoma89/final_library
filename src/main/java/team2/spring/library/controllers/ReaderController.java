package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.services.ReaderService;

import javax.validation.Valid;

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
   * @param model  set readerStatisticDto in jsp
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
   * @param model set data in  jsp  page
   * @return representation of general statistic
   */
  @GetMapping("/generalStatistic")
  public String getGeneralStatistic(Model model) {
    model.addAttribute("generalStatisticDto", readerService.getGeneralStatisticDto());
    return "readersJsp/generalStatistic";
  }

}
