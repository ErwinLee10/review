 CREATE TABLE reviews
   (
      id BIGSERIAL   NOT NULL PRIMARY KEY,
      user_id   BIGSERIAL   NOT NULL,
      seller_id BIGSERIAL   NOT NULL,
      product_id   BIGSERIAL   NOT NULL,
      star_rating    BIGSERIAL NOT NULL,

      remarks  VARCHAR(255),
      created_by  VARCHAR(255),
      created_dt  DATE,
      last_updated_by   VARCHAR(255),
      last_updated_dt   DATE
  );