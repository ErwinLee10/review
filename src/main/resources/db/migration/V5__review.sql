 CREATE TABLE reviews
   (
      id          BIGSERIAL    	NOT NULL PRIMARY KEY,
      userId    BIGSERIAL    	NOT NULL,
      sellerId    BIGSERIAL   NOT NULL,
      starRating    BIGSERIAL    	NOT NULL,
      productId BIGSERIAL        NOT NULL,
      remarks		VARCHAR(255),
      created_by  VARCHAR(255),
      created_dt    DATE,
      last_updated_by 	VARCHAR(255),
      last_updated_dt	DATE
  );