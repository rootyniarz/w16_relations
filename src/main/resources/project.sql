CREATE TABLE project
(
  project_id SERIAL NOT NULL,
  name VARCHAR(64) NOT NULL,
  description TEXT NOT NULL,
  deadline TIMESTAMP WITH TIME ZONE NOT NULL,
  PRIMARY KEY (project_id),
  UNIQUE (name)
);