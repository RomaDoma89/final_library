package team2.spring.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Main controller */
@Controller
@RequestMapping("/")
public class MainController {
  /** @return home page */
  @GetMapping("/")
  public String home() {
    return "index";
  }

  /** @return error page */
  @GetMapping("/error")
  public String error() {
    return "error";
  }
}
