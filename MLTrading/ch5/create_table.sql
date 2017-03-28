create table codes (
       id integer unsigned not null auto_increment,
       last_update varchar(8) not null,
       code varchar(200) not null,
       full_code varchar(200) not null,
       market_type int(2) not null,
       company varchar(200) not null,
       primary key (id),
       unique key ix_code (code)
);

create table prices (
       id integer unsigned not null auto_increment,
       last_update varchar(8) not null,
       price_date datetime not null,
       code varchar(200) not null,
       price_open integer unsigned not null,
       price_close integer unsigned not null,
       price_high integer unsigned not null,
       price_low integer unsigned not null,
       price_adj_close integer unsigned not null,
       volume integer unsigned not null,
       primary key (id),
       unique key ix_code_date (code, price_date)
);
