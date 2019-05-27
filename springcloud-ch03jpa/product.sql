create table product
(
	sku serial not null
		constraint product_pkey
			primary key,
	name varchar,
	price double precision
)
;

alter table product owner to postgres
;

INSERT INTO "public"."product" ("sku", "name", "price") VALUES ('1', '上帝怀中的羔羊', '31.8'),
('2', '无端欢喜', '30.2'),
('3', '大英烦事多', '36');
