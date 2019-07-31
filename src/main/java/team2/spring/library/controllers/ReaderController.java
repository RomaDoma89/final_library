package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    return "getBlackList";
  }
  
  @GetMapping("/userStatisticForm")
    public String findUserStatisticForm(Model model){
        model.addAttribute("readerStatisticDto", new ReaderStatisticDto());
        return "userStatisticForm";
    }

    @PostMapping("/userStatistic")
    public String getUserStatistic (@Valid@ModelAttribute("readerStatisticDto")ReaderStatisticDto readerStatisticDto,Model model){
       model.addAttribute("readerStatisticDto",readerService.getUserStatistic(readerStatisticDto));
        return "userStatistic";
    }
}
