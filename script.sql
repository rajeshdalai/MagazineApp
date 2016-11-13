create table MAIL(
   id BIGSERIAL PRIMARY KEY ,
   subject  VARCHAR(255),
   body VARCHAR(10000),
   sender_email VARCHAR(100) NOT NULL,
   receiver_email VARCHAR(100) NOT NULL,
   status VARCHAR(20),
   name VARCHAR(100),
   content BYTEA
);