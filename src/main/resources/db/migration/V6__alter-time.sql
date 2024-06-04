alter table tasks
    alter column time type time using time::time;