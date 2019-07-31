package team2.spring.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** */
@Controller
@RequestMapping("/")
public class MainController {
  /** @return */
  @GetMapping("/")
  public String home() {
    return "index";
  }

  /** @return */
  @GetMapping("/error")
  public String error() {
    return "error";
  }
}
