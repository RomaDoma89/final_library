package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team2.spring.library.dto.GeneralStatisticDto;
import team2.spring.library.dto.ReaderAvgDto;
import team2.spring.library.dto.ReaderDto;
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
   * Get all readers
   *
   * @param model set list of all readers in jsp
   * @return page with list of all readers
   */
  @GetMapping("/allReaders")
  public String getAllReaders(Model model) {
    model.addAttribute("listReader", readerService.getAllReaders());
    model.addAttribute("readerDto", new ReaderDto());
    return "readersJsp/allReaders";
  }

  /**
   * Get list readers from library black list
   *
   * @param model set black list of reader in jsp
   * @return page with black list of reader
   */
  @GetMapping("/getBlackList")
  public String getBlackList(Model model) {
    model.addAttribute("readerBlackList", readerService.getBlackList());
    return "readersJsp/getBlackList";
  }

  /**
   * Prepare form for input name
   *
   * @param model set readerStatisticDto in jsp
   * @return page with form
   */
  @GetMapping("/readerStatisticForm")
  public String findUserStatisticForm(Model model) {
    model.addAttribute("readerStatisticDto", new ReaderStatisticDto());
    return "readersJsp/readerStatisticForm";
  }

  /**
   * Get reader statistic information
   *
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
   * Prepare page with jsp for input date
   *
   * @return page with form
   */
  @GetMapping("/generalStatisticForm")
  public String generalStatisticForm() {
    return "readersJsp/generalStatistic";
  }

  /**
   * Return general statistic by entering period
   *
   * @param model set data in jsp page
   * @return representation of general statistic
   */
  @PostMapping("/generalStatistic")
  public String getGeneralStatistic(
      @RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
    GeneralStatisticDto generalStatisticDto = new GeneralStatisticDto();
    generalStatisticDto.setDateFrom(LocalDate.parse(dateFrom));
    generalStatisticDto.setDateTo(LocalDate.parse(dateTo));
    try {
      generalStatisticDto = readerService.getGeneralStatisticDto(generalStatisticDto);
    } catch (ParseException e) {
      e.printStackTrace();
      return "error";
    }
    model.addAttribute("generalStatisticDto", generalStatisticDto);
    return "readersJsp/generalStatistic";
  }

  /**
   * Prepare jsp page with form to set name of reader
   *
   * @param model set ReaderAvgDto in jsp page
   * @return page with form
   */
  @GetMapping("/readerAvgForm")
  public String getReaderAvgForm(Model model) {
    model.addAttribute("readerAvgDto", new ReaderAvgDto());
    return "readersJsp/readerAvgForm";
  }

  /**
   * Get average age of reader by name
   *
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

  /**
   * @param readerDto from page with input title form
   * @param model set dto in jsp
   * @return page with available book
   */
  @PostMapping("/deleteReader")
  public String deleteBook(@ModelAttribute("readerDto") ReaderDto readerDto, Model model) {
    try {
      model.addAttribute("listReader", readerService.deleteReader(readerDto.getId()));
    } catch (IllegalArgumentException | DataIntegrityViolationException e) {
      model.addAttribute("listReader", readerService.getAllReaders());
      return "readersJsp/allReaders";
    }
    return "readersJsp/allReaders";
  }
}
