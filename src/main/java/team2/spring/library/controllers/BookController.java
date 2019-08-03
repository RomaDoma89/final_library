package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team2.spring.library.dto.BookByPeriodDto;
import team2.spring.library.dto.BookDto;
import team2.spring.library.dto.BookStatisticDto;
import team2.spring.library.entities.Author;
import team2.spring.library.entities.Book;
import team2.spring.library.services.BookService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;

/** Controller witch operate all request of book */
@Controller
@AllArgsConstructor
public class BookController {

  private BookService bookService;

  /**
   * @param model set list of book in jsp
   * @return
   */
  @GetMapping("/allBooks")
  public String findAllBook(Model model) {
    model.addAttribute("listBook", bookService.findAll());
    return "booksJsp/allBooks";
  }

  /**
   * @param model prepare dto for Jsp
   * @return page with form of book title
   */
  @GetMapping("/availableBookForm")
  public String availableBookForm(Model model) {
    model.addAttribute("bookDto", new BookDto());
    return "booksJsp/availableBookForm";
  }

  /**
   * @param bookDto from page with input title form
   * @param model set dto in jsp
   * @return page with available book
   */
  @PostMapping("/availableBook")
  public String availableBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, Model model) {
    model.addAttribute("bookDto", bookService.isBookAvailable(bookDto));
    return "booksJsp/availableBook";
  }

  /**
   * @param model prepare author for jsp
   * @return page with form for author name
   */
  @GetMapping("/booksByAuthorForm")
  public String findBooksByAuthorForm(Model model) {
    model.addAttribute("author", new Author());
    return "booksJsp/booksByAuthorForm";
  }
  /**
   * @param author from page with input name form
   * @param model set list of book in jsp
   * @return page with list of books
   */
  @PostMapping("/booksByAuthor")
  public String booksByAuthor(@ModelAttribute("author") Author author, Model model) {
    model.addAttribute("author", author);
    model.addAttribute("listBooks", bookService.findBooksByAuthor(author));
    return "booksJsp/booksByAuthor";
  }

  /**
   * Redirect on page with form for add new book in library
   *
   * @param model set book book entity in jsp page
   * @return page with form gor add new book in library
   */
  @GetMapping("/addBookForm")
  public String addBookForm(Model model) {
    model.addAttribute("book", new Book());
    return "addBookForm";
  }
  /**
   * Return page with recently added book
   *
   * @param book added book
   * @param model return book witch was added on page
   * @return new book on jsp
   */
  @PostMapping("/addBook")
  public String addBook(@ModelAttribute("book") Book book, Model model) {

    model.addAttribute("book", book);
    return "addBook";
  }

  /**
   * @param model
   * @return
   */
  @GetMapping("/getBookStatistic")
  public String getBookStatistic(Model model) {
    model.addAttribute("bookStatisticDto", new BookStatisticDto());
    return "booksJsp/bookStatistic";
  }

  /**
   * @param bookStatisticDto
   * @param model
   * @return
   */
  @PostMapping("/getBookStatistic")
  public String getBookStatistic(
      @Valid @ModelAttribute("bookStatisticDto") BookStatisticDto bookStatisticDto, Model model) {
    System.out.println(bookStatisticDto);
    model.addAttribute("bookStatisticDto", bookService.getBookStatisticDto(bookStatisticDto));
    return "booksJsp/bookStatistic";
  }

  /**
   * @param book
   * @param model
   * @return
   */
  @PostMapping("/getPopularBook")
  public String getPopularBook(@ModelAttribute("book") Book book, Model model) {
    return "getPopularBook";
  }

  /** @return */
  @GetMapping("/getCountBookByPeriodForm")
  public String getCountBookByPeriodForm(Model model) {
    return "/booksJsp/getCountBookByPeriodForm";
  }

  /**
   * @param dateFrom
   * @param dateTo
   * @param model
   * @return
   */
  @PostMapping("/getCountBookByPeriod")
  public String getCountBookByPeriod(
      @RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
    System.out.println(dateFrom);
    System.out.println(dateTo);
    BookByPeriodDto bookByPeriodDto = new BookByPeriodDto();
    bookByPeriodDto.setDateFrom(LocalDate.parse(dateFrom));
    bookByPeriodDto.setDateTo(LocalDate.parse(dateTo));
    try {
      bookByPeriodDto.setCountOfBookByPeriod(bookService.getCountOfBookByPeriod(bookByPeriodDto));
    } catch (ParseException e) {
      e.printStackTrace();
      return "error";
    }
    model.addAttribute("bookByPeriodDto", bookByPeriodDto);
    return "booksJsp/getCountBookByPeriodForm";
  }
}
