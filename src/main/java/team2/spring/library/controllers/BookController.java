package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team2.spring.library.dto.BookByPeriodDto;
import team2.spring.library.dto.BookDto;
import team2.spring.library.dto.BookPopularDto;
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
   * Prepare jsp with form for entering book title
   *
   * @param model set list of book in jsp
   * @return page with list of books
   */
  @GetMapping("/allBooks")
  public String findAllBook(Model model) {
    model.addAttribute("listBook", bookService.findAll());
    model.addAttribute("bookDto", new BookDto());
    return "booksJsp/allBooks";
  }

  /**
   * Get information if available book by title
   *
   * @param model prepare dto for Jsp
   * @return page with form of book title
   */
  @GetMapping("/availableBookForm")
  public String availableBookForm(Model model) {
    model.addAttribute("bookDto", new BookDto());
    return "booksJsp/availableBookForm";
  }

  /**
   * Get list with all books that contain library
   *
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
   * Prepare jsp with form for get name of author
   *
   * @param model prepare author for jsp
   * @return page with form for author name
   */
  @GetMapping("/booksByAuthorForm")
  public String findBooksByAuthorForm(Model model) {
    model.addAttribute("author", new Author());
    return "booksJsp/booksByAuthorForm";
  }

  /**
   * Get list of book by author name
   *
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
   * @param model set book entity in jsp page
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
   * Get statistic about book by title
   *
   * @param model prepare dto for Jsp
   * @return jsp with form for input
   */
  @GetMapping("/getBookStatistic")
  public String getBookStatistic(Model model) {
    model.addAttribute("bookStatisticDto", new BookStatisticDto());
    return "booksJsp/bookStatistic";
  }

  /**
   * Find statistic about special book
   *
   * @param bookStatisticDto retrieve dto with title
   * @param model set BookStatisticDto with data
   * @return book statistic jsp
   */
  @PostMapping("/getBookStatistic")
  public String getBookStatistic(
      @Valid @ModelAttribute("bookStatisticDto") BookStatisticDto bookStatisticDto, Model model) {
    model.addAttribute("bookStatisticDto", bookService.getBookStatisticDto(bookStatisticDto));
    return "booksJsp/bookStatistic";
  }

  /**
   * Find popular input form
   *
   * @param model set data in jsp page
   * @return jsp page
   */
  @GetMapping("/getPopularBook")
  public String getPopularBook(Model model) {
    return "/booksJsp/popularBookForm";
  }

  /**
   * Find popular jsp page
   *
   * @param dateFrom start date
   * @param dateTo end date
   * @param model set data in jsp page
   * @return jsp page
   */
  @PostMapping("/getPopularBook")
  public String getPopularBook(
      @RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
    BookPopularDto bookPopularDto = new BookPopularDto();
    try {
      bookPopularDto.setMap(
          bookService.getPopular(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
    } catch (ParseException e) {
      return "error";
    }
    model.addAttribute("map", bookPopularDto);
    return "/booksJsp/popular";
  }

  /** @return */
  @GetMapping("/getCountBookByPeriodForm")
  public String getCountBookByPeriodForm(Model model) {
    return "/booksJsp/getCountBookByPeriodForm";
  }

  /**
   * Count amount of ordered book by entered period
   *
   * @param dateFrom start date of entered period
   * @param dateTo end date of entered period
   * @param model trans transmit bookByPeriodDto in jsp
   * @return jsp with books ordered amount
   */
  @PostMapping("/getCountBookByPeriod")
  public String getCountBookByPeriod(
      @RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
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

  /**
   * Prepare page with form to get info about copies of book
   *
   * @param model set data in jsp page
   * @return jsp page with form
   */
  @GetMapping("/getCopiesInfoForm")
  public String getCopiesInfoForm(Model model) {
    model.addAttribute("book", new Book());
    return "booksJsp/copyInfoForm";
  }

  /**
   * Get info about copies of book
   *
   * @param book contains info which need for response
   * @param model set data in jsp page
   * @return jsp page with response
   */
  @PostMapping("/getCopiesInfo")
  public String getCopiesInfo(@Valid @ModelAttribute("book") Book book, Model model) {
    model.addAttribute("list", bookService.getCopiesInfo(book));
    return "booksJsp/copyInfo";
  }

  /**
   * Delete book from db
   *
   * @param bookDto from page with input title form
   * @param model set dto in jsp
   * @return page with available book
   */
  @PostMapping("/deleteBook")
  public String deleteBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, Model model) {
    try {
      model.addAttribute("listBook", bookService.deleteBook(bookDto.getId()));
    } catch (IllegalArgumentException | DataIntegrityViolationException e) {
      model.addAttribute("listBook", bookService.findAll());
      return "booksJsp/allBooks";
    }
    return "booksJsp/allBooks";
  }
  @PostMapping("/updateBookForm")
  public String updateBookForm (@ModelAttribute ("bookDto") BookDto bookDto  , Model model){
    model.addAttribute("bookDto",bookDto);
    return "booksJsp/updateBook";
  }
  @PostMapping("/updateBook")
  public String updateBook (@ModelAttribute("bookDto")BookDto bookDto,Model model){
    model.addAttribute("book",bookService.update(bookDto));
      return "booksJsp/updateBook";
  }
}
