INSERT INTO book (id, title) values (1, 'Code Complete');
INSERT INTO book (id, title) values (2, 'Hooked on Java');
INSERT INTO book (id, title) values (3, 'Java Phrasebook');
INSERT INTO book (id, title) values (4, 'Java: Just in Time');
INSERT INTO book (id, title) values (5, 'Pattern Calculus: Computing with Functions and Structures');
INSERT INTO book (id, title) values (6, 'The Tao of Programming');
INSERT INTO book (id, title) values (7, 'Effective Java');
INSERT INTO book (id, title) values (8, 'The Art of Computer Programming');
INSERT INTO book (id, title) values (9, 'Pro Spring 5: An In-Depth Guide to the Spring Framework and Its Tools 5th ed. Edition');
INSERT INTO book (id, title) values (10, 'Java Concurrency in Practice');


INSERT INTO author (id, name) values (1, 'Steve McConnell');
INSERT INTO author (id, name) values (2, 'Sami Shaio');
INSERT INTO author (id, name) values (3, 'Orca Starbuck');
INSERT INTO author (id, name) values (4, 'Arthur van Hoff');
INSERT INTO author (id, name) values (5, 'Timothy Fisher');
INSERT INTO author (id, name) values (6, 'John Latham');
INSERT INTO author (id, name) values (7, 'Barry Jay');
INSERT INTO author (id, name) values (8, 'Geoffrey James');
INSERT INTO author (id, name) values (9, 'Joshua Bloch');
INSERT INTO author (id, name) values (10, 'Donald Knuth');
INSERT INTO author (id, name) values (11, 'Iuliana Cosmina');
INSERT INTO author (id, name) values (12, 'Rob Harrop');
INSERT INTO author (id, name) values (13, 'Chris Schaefer');
INSERT INTO author (id, name) values (14, 'Clarence Ho');


INSERT INTO book_author (id_book, id_author) values (1, 1);
INSERT INTO book_author (id_book, id_author) values (2, 2);
INSERT INTO book_author (id_book, id_author) values (2, 3);
INSERT INTO book_author (id_book, id_author) values (2, 4);
INSERT INTO book_author (id_book, id_author) values (3, 5);
INSERT INTO book_author (id_book, id_author) values (4, 6);
INSERT INTO book_author (id_book, id_author) values (5, 7);
INSERT INTO book_author (id_book, id_author) values (6, 8);
INSERT INTO book_author (id_book, id_author) values (7, 9);
INSERT INTO book_author (id_book, id_author) values (8, 10);
INSERT INTO book_author (id_book, id_author) values (9, 11);
INSERT INTO book_author (id_book, id_author) values (9, 12);
INSERT INTO book_author (id_book, id_author) values (9, 13);
INSERT INTO book_author (id_book, id_author) values (9, 14);
INSERT INTO book_author (id_book, id_author) values (10, 9);


INSERT INTO book_copy (book, id, available) values (1, 1, true);
INSERT INTO book_copy (book, id, available) values (1, 2, true);
INSERT INTO book_copy (book, id, available) values (1, 3, false);
INSERT INTO book_copy (book, id, available) values (2, 4, false);
INSERT INTO book_copy (book, id, available) values (2, 5, true);
INSERT INTO book_copy (book, id, available) values (2, 6, false);
INSERT INTO book_copy (book, id, available) values (3, 7, true);
INSERT INTO book_copy (book, id, available) values (3, 8, false);
INSERT INTO book_copy (book, id, available) values (3, 9, true);
INSERT INTO book_copy (book, id, available) values (4, 10, true);
INSERT INTO book_copy (book, id, available) values (4, 11, false);
INSERT INTO book_copy (book, id, available) values (4, 12, true);
INSERT INTO book_copy (book, id, available) values (4, 13, false);
INSERT INTO book_copy (book, id, available) values (5, 14, true);
INSERT INTO book_copy (book, id, available) values (5, 15, false);
INSERT INTO book_copy (book, id, available) values (6, 16, true);
INSERT INTO book_copy (book, id, available) values (6, 17, false);
INSERT INTO book_copy (book, id, available) values (7, 18, true);
INSERT INTO book_copy (book, id, available) values (7, 19, true);
INSERT INTO book_copy (book, id, available) values (7, 20, true);
INSERT INTO book_copy (book, id, available) values (8, 21, false);
INSERT INTO book_copy (book, id, available) values (8, 22, false);
INSERT INTO book_copy (book, id, available) values (8, 23, true);
INSERT INTO book_copy (book, id, available) values (9, 24, false);
INSERT INTO book_copy (book, id, available) values (9, 25, false);
INSERT INTO book_copy (book, id, available) values (9, 26, false);
INSERT INTO book_copy (book, id, available) values (10, 27, true);
INSERT INTO book_copy (book, id, available) values (10, 28, true);


INSERT INTO reader (id, name, birthday) values (1, 'Jack Edwards', '1981-04-21');
INSERT INTO reader (id, name, birthday) values (2, 'Harry Ortiz', '1999-01-29');
INSERT INTO reader (id, name, birthday) values (3, 'Mason Gardner', '2002-11-09');
INSERT INTO reader (id, name, birthday) values (4, 'Liam Pacheco', '2005-01-19');
INSERT INTO reader (id, name, birthday) values (5, 'William Sutton', '2007-07-07');
INSERT INTO reader (id, name, birthday) values (6, 'Charlie Vaughan', '1995-04-22');
INSERT INTO reader (id, name, birthday) values (7, 'Emily Simmons', '1989-06-19');
INSERT INTO reader (id, name, birthday) values (8, 'Susan Goodwin', '1999-07-10');
INSERT INTO reader (id, name, birthday) values (9, 'Margaret Watkins', '2002-08-20');
INSERT INTO reader (id, name, birthday) values (10, 'Oscar Duran', '2003-09-16');


INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (1, 1, 1, 3,  '2019-03-28', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (2, 2, 2, 4, '2019-04-21', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (3, 3, 2, 6, '2019-02-20', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (4, 9, 3, 8, '2019-01-19', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (5, 2, 4, 11, '2019-06-05', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (6, 6, 4, 13, '2019-07-02', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (7, 7, 5, 15, '2019-03-12', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (8, 8, 6, 17, '2019-04-11', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (9, 9, 8, 21, '2019-07-10', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (10, 3, 8, 22, '2019-01-10', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (11, 4, 9, 24, '2019-02-13', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (12, 1, 9, 25, '2019-03-12', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (13, 10, 9, 26, '2019-04-11', null);
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (14, 1, 3, 9, '2015-01-19', '2015-02-10');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (15, 6, 3, 7, '2016-02-01', '2016-03-28');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (16, 10, 2, 5, '2018-03-02', '2018-04-07');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (17, 4, 1, 1, '2005-04-08', '2005-05-09');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (18, 5, 2, 6, '2017-05-11', '2017-06-21');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (19, 2, 2, 6, '2016-06-21', '2016-07-10');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (20, 10, 1, 3, '2018-07-15', '2018-08-19');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (21, 9, 9, 25, '2018-07-16', '2018-08-20');

INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (22, 6, 8, 21, '2018-07-16', '2018-08-20');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (23, 5, 6, 16, '2018-07-16', '2018-08-20');
INSERT INTO reader_story (id, reader, book, copy, time_take, time_return) values (24, 3, 4, 12, '2018-07-16', '2018-08-20');