alter table tasks
    alter column status drop default;
drop type task_status;
