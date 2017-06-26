-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 9.2
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.books | type: TABLE --
CREATE TABLE public.books(
	product_id bigserial,
	type varchar(1000) NOT NULL,
	publisher bigserial,
	publisher_local bigserial,
	"ISBN" varchar(1000),
	synopsis_text text,
	format varchar(1000),
	pages_count bigint,
	CONSTRAINT "pk_id_Book" PRIMARY KEY (product_id),
	CONSTRAINT "un_ISBN" UNIQUE ("ISBN")

);
-- ddl-end --
COMMENT ON COLUMN public.books.product_id IS 'ID Книги';
-- ddl-end --
COMMENT ON COLUMN public.books.type IS 'Категория книги';
-- ddl-end --
COMMENT ON COLUMN public.books.publisher IS 'Издатель';
-- ddl-end --
COMMENT ON COLUMN public.books.publisher_local IS 'Издатель в России';
-- ddl-end --
COMMENT ON COLUMN public.books."ISBN" IS 'ISBN-кодировка книги';
-- ddl-end --
COMMENT ON COLUMN public.books.synopsis_text IS 'Описание';
-- ddl-end --
COMMENT ON COLUMN public.books.format IS 'формат';
-- ddl-end --
COMMENT ON COLUMN public.books.pages_count IS 'Количество страниц';
-- ddl-end --
COMMENT ON CONSTRAINT "pk_id_Book" ON public.books IS 'Id_Book';
-- ddl-end --
-- ddl-end --

-- object: public."Orders" | type: TABLE --
CREATE TABLE public."Orders"(
	order_id bigserial,
	user_id bigserial,
	quantity bigint NOT NULL,
	date_of_creation date NOT NULL,
	status varchar(1000) NOT NULL,
	note text,
	CONSTRAINT pk_order_id PRIMARY KEY (order_id)

);
-- ddl-end --
-- object: public."Users" | type: TABLE --
CREATE TABLE public."Users"(
	user_id bigserial,
	login varchar(1000),
	password_hash varchar(1000),
	name varchar(1000),
	surname varchar(1000),
	phone_number varchar(1000),
	ip_address varchar(1000) NOT NULL,
	session_key varchar(1000) NOT NULL,
	last_visited_date date NOT NULL,
	role varchar(1000) NOT NULL,
	registration_date date,
	CONSTRAINT pk_user_id PRIMARY KEY (user_id)

);
-- ddl-end --
-- object: public."Order_Items" | type: TABLE --
CREATE TABLE public."Order_Items"(
	order_item_id bigserial,
	order_id bigserial,
	product_id bigserial,
	amount bigint,
	CONSTRAINT pk_order_item_id PRIMARY KEY (order_item_id)

);
-- ddl-end --
-- object: public.attributes | type: TABLE --
CREATE TABLE public.attributes(
	product_id bigserial,
	type varchar(1000) NOT NULL,
	height bigint,
	manufacturer bigint,
	series bigint,
	material varchar(1000),
	description text,
	CONSTRAINT pk_id_attributes PRIMARY KEY (product_id)

);
-- ddl-end --
COMMENT ON COLUMN public.attributes.product_id IS 'ID Книги';
-- ddl-end --
COMMENT ON CONSTRAINT pk_id_attributes ON public.attributes IS 'Id_Book';
-- ddl-end --
-- ddl-end --

-- object: public.products | type: TABLE --
CREATE TABLE public.products(
	product_id bigserial,
	title varchar NOT NULL,
	price money,
	remainder bigint,
	CONSTRAINT pk_product_id_products PRIMARY KEY (product_id),
	CONSTRAINT un_title UNIQUE (title)

);
-- ddl-end --
COMMENT ON COLUMN public.products.product_id IS 'ID Книги';
-- ddl-end --
COMMENT ON COLUMN public.products.title IS 'Название книги';
-- ddl-end --
COMMENT ON CONSTRAINT pk_product_id_products ON public.products IS 'Id_Book';
-- ddl-end --
-- ddl-end --

-- object: public.subjects | type: TABLE --
CREATE TABLE public.subjects(
	subject_id bigserial,
	name varchar(1000) NOT NULL,
	description text,
	CONSTRAINT pk_subject_id PRIMARY KEY (subject_id)

);
-- ddl-end --
-- object: public.products_authors | type: TABLE --
CREATE TABLE public.products_authors(
	product_id bigserial,
	subject_id bigserial
);
-- ddl-end --
-- object: public.products_artists | type: TABLE --
CREATE TABLE public.products_artists(
	product_id bigserial,
	subject_id bigserial
);
-- ddl-end --
-- object: public.products_tags | type: TABLE --
CREATE TABLE public.products_tags(
	product_id bigserial,
	tag_id bigserial,
	CONSTRAINT pk_product_id_tag_id_products_tags PRIMARY KEY (product_id,tag_id)

);
-- ddl-end --
-- object: public.tags | type: TABLE --
CREATE TABLE public.tags(
	tag_id serial,
	title varchar(1000) NOT NULL,
	CONSTRAINT pk_tag_id PRIMARY KEY (tag_id)

);
-- ddl-end --
-- object: public.pictures | type: TABLE --
CREATE TABLE public.pictures(
	picture_id bigserial,
	product_id bigserial,
	show_order varchar(1000),
	CONSTRAINT pk_picture_id PRIMARY KEY (picture_id)

);
-- ddl-end --
-- object: fk_product_id_books | type: CONSTRAINT --
ALTER TABLE public.books ADD CONSTRAINT fk_product_id_books FOREIGN KEY (product_id)
REFERENCES public.products (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_user_id | type: CONSTRAINT --
ALTER TABLE public."Orders" ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES public."Users" (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_order_id | type: CONSTRAINT --
ALTER TABLE public."Order_Items" ADD CONSTRAINT fk_order_id FOREIGN KEY (order_item_id)
REFERENCES public."Orders" (order_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_product_id_order_items | type: CONSTRAINT --
ALTER TABLE public."Order_Items" ADD CONSTRAINT fk_product_id_order_items FOREIGN KEY (product_id)
REFERENCES public.products (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_product_id_attributes | type: CONSTRAINT --
ALTER TABLE public.attributes ADD CONSTRAINT fk_product_id_attributes FOREIGN KEY (product_id)
REFERENCES public.products (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_product_id | type: CONSTRAINT --
ALTER TABLE public.products_authors ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
REFERENCES public.books (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_subject_id | type: CONSTRAINT --
ALTER TABLE public.products_authors ADD CONSTRAINT fk_subject_id FOREIGN KEY (subject_id)
REFERENCES public.subjects (subject_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_product_id | type: CONSTRAINT --
ALTER TABLE public.products_artists ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
REFERENCES public.books (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_subject_id | type: CONSTRAINT --
ALTER TABLE public.products_artists ADD CONSTRAINT fk_subject_id FOREIGN KEY (subject_id)
REFERENCES public.subjects (subject_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_product_id | type: CONSTRAINT --
ALTER TABLE public.products_tags ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
REFERENCES public.products (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: fk_tag_id | type: CONSTRAINT --
ALTER TABLE public.products_tags ADD CONSTRAINT fk_tag_id FOREIGN KEY (tag_id)
REFERENCES public.tags (tag_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --



