insert into STORE_ENTITY (category, item, stock) values('books', 'ISBN1', 42)
insert into STORE_ENTITY (category, item, stock) values('books', 'ISBN2', 2)
insert into STORE_ENTITY (category, item, stock) values('books', 'ISBN3', 4)
insert into STORE_ENTITY (category, item, stock) values('books', 'ISBN4', 422)
insert into STORE_ENTITY (category, item, stock) values('books', 'ISBN5', 1)
insert into BOOK (isbn, title, price) values('ISBN1', 'Title1', 10)
insert into BOOK (isbn, title, price) values('ISBN2', 'Title2', 10)
insert into BOOK (isbn, title, price) values('ISBN3', 'Title3', 10)
insert into BOOK (isbn, title, price) values('ISBN4', 'Title4', 10)
insert into BOOK (isbn, title, price) values('ISBN5', 'Title5', 10)
insert into BOOK (isbn, title, price) values('ISBN6', 'Title6', 10)
insert into BOOK (isbn, title, price) values('ISBN7', 'Title7', 10)
insert into BOOK (isbn, title, price) values('ISBN8', 'Title8', 10)
insert into BOOK (isbn, title, price) values('ISBN9', 'Title9', 10)
insert into BOOK (isbn, title, price) values('ISBN10', 'Title10', 10)
drop table ISBN if exists
create table ISBN(isbn integer)
insert into ISBN values(0)