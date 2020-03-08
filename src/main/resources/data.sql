INSERT INTO
	PUBLISHER (publisher_id, name, address)
VALUES
  	(1, 'Test Josi', 'Test str 2'),
  	(2, 'Test Bela', 'Test str 3');

INSERT INTO
	AUTHOR (author_id, first_name, last_name)
VALUES
  	(3, 'Bela', 'Non'),
  	(4, 'Pon', 'Moon');

INSERT INTO
    BOOK (book_id, title, year_of_publication, isbn, category, number_of_pages, short_description, price, author_id, publisher_id)
VALUES
    (1, 'First Book', 1998, 32131312, 'Drama', 211, 'Short description', 2222, 3, 1);