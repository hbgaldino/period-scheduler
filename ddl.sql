CREATE TABLE user (
	id int not null primary key AUTO_INCREMENT,
	username varchar(300) not null,
	password  varchar(300) not null
);

CREATE TABLE task (
	id bigint not null primary key AUTO_INCREMENT,
	scheduled_time datetime not null,
	active bool default false not null,
	log_message varchar(100) null,
	user_id int not null,
	FOREIGN KEY (user_id) REFERENCES user(id)
);