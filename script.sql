create table MAIL(
   id BIGSERIAL PRIMARY KEY ,
   subject  VARCHAR(max),
   body VARCHAR(255),
   from VARCHAR(100) NOT NULL,
   to VARCHAR(100) NOT NULL,
   name VARCHAR(100),
   content longblob,
   PRIMARY KEY (id),
);