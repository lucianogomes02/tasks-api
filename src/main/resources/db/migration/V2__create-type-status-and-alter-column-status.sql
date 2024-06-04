CREATE TYPE task_status AS ENUM ('PENDING', 'STARTED', 'DONE');

ALTER TABLE tasks
    ALTER COLUMN status TYPE task_status USING status::task_status,
    ALTER COLUMN status SET DEFAULT 'PENDING'::task_status;
