DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS app_user;

-- Create app_user table
CREATE TABLE IF NOT EXISTS app_user (
  id UUID PRIMARY KEY,
  username VARCHAR(100),
  email VARCHAR(100)
);

-- Create book table
CREATE TABLE IF NOT EXISTS book (
  id UUID PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255),
  available BOOLEAN,
  borrowed_by UUID,
  CONSTRAINT fk_borrowed_by FOREIGN KEY (borrowed_by) REFERENCES app_user(id)
);

-- Users
INSERT INTO app_user (id, username, email) VALUES
  ('11111111-1111-1111-1111-111111111111', 'alice', 'alice@example.com'),
  ('22222222-2222-2222-2222-222222222222', 'bob', 'bob@example.com'),
  ('33333333-3333-3333-3333-333333333333', 'carol', 'carol@example.com'),
  ('44444444-4444-4444-4444-444444444444', 'dave', 'dave@example.com'),
  ('55555555-5555-5555-5555-555555555555', 'eve', 'eve@example.com'),
  ('66666666-6666-6666-6666-666666666666', 'frank', 'frank@example.com'),
  ('77777777-7777-7777-7777-777777777777', 'grace', 'grace@example.com'),
  ('88888888-8888-8888-8888-888888888888', 'heidi', 'heidi@example.com'),
  ('99999999-9999-9999-9999-999999999999', 'ivan', 'ivan@example.com');

-- Books
INSERT INTO book (id, title, author, available, borrowed_by) VALUES
  ('b1b1b1b1-b1b1-b1b1-b1b1-b1b1b1b1b1b1', '1984', 'George Orwell', true, NULL),
  ('b2b2b2b2-b2b2-b2b2-b2b2-b2b2b2b2b2b2', 'To Kill a Mockingbird', 'Harper Lee', true, NULL),
  ('b3b3b3b3-b3b3-b3b3-b3b3-b3b3b3b3b3b3', 'The Great Gatsby', 'F. Scott Fitzgerald', true, NULL),
  ('b4b4b4b4-b4b4-b4b4-b4b4-b4b4b4b4b4b4', 'Moby-Dick', 'Herman Melville', true, NULL),
  ('b5b5b5b5-b5b5-b5b5-b5b5-b5b5b5b5b5b5', 'Pride and Prejudice', 'Jane Austen', true, NULL),
  ('b6b6b6b6-b6b6-b6b6-b6b6-b6b6b6b6b6b6', 'War and Peace', 'Leo Tolstoy', true, NULL),
  ('b7b7b7b7-b7b7-b7b7-b7b7-b7b7b7b7b7b7', 'Crime and Punishment', 'Fyodor Dostoevsky', true, NULL),
  ('b8b8b8b8-b8b8-b8b8-b8b8-b8b8b8b8b8b8', 'Brave New World', 'Aldous Huxley', true, NULL),
  ('b9b9b9b9-b9b9-b9b9-b9b9-b9b9b9b9b9b9', 'Jane Eyre', 'Charlotte Brontë', true, NULL),
  ('c1c1c1c1-c1c1-c1c1-c1c1-c1c1c1c1c1c1', 'The Catcher in the Rye', 'J.D. Salinger', false, '22222222-2222-2222-2222-222222222222'),
  ('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', 'The Hobbit', 'J.R.R. Tolkien', false, '33333333-3333-3333-3333-333333333333'),
  ('c3c3c3c3-c3c3-c3c3-c3c3-c3c3c3c3c3c3', 'The Odyssey', 'Homer', false, '44444444-4444-4444-4444-444444444444'),
  ('c4c4c4c4-c4c4-c4c4-c4c4-c4c4c4c4c4c4', 'The Iliad', 'Homer', false, '55555555-5555-5555-5555-555555555555'),
  ('c5c5c5c5-c5c5-c5c5-c5c5-c5c5c5c5c5c5', 'Fahrenheit 451', 'Ray Bradbury', false, '66666666-6666-6666-6666-666666666666'),
  ('c6c6c6c6-c6c6-c6c6-c6c6-c6c6c6c6c6c6', 'Slaughterhouse-Five', 'Kurt Vonnegut', false, '77777777-7777-7777-7777-777777777777'),
  ('c7c7c7c7-c7c7-c7c7-c7c7-c7c7c7c7c7c7', 'The Alchemist', 'Paulo Coelho', false, '22222222-2222-2222-2222-222222222222'),
  ('c8c8c8c8-c8c8-c8c8-c8c8-c8c8c8c8c8c8', 'The Road', 'Cormac McCarthy', true, NULL),
  ('c9c9c9c9-c9c9-c9c9-c9c9-c9c9c9c9c9c9', 'Dracula', 'Bram Stoker', true, NULL),
  ('d1d1d1d1-d1d1-d1d1-d1d1-d1d1d1d1d1d1', 'Frankenstein', 'Mary Shelley', true, NULL),
  ('d2d2d2d2-d2d2-d2d2-d2d2-d2d2d2d2d2d2', 'Wuthering Heights', 'Emily Brontë', true, NULL);