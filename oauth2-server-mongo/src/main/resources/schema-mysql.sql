DROP TABLE IF EXISTS user_info;
create table user_info(
   id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(10) NOT NULL,
   email VARCHAR(255) NOT NULL ,
   password VARCHAR(32) NOT NULL,
   mobile VARCHAR(15) NOT NULL,
   urls VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL,
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8 auto_increment=1;