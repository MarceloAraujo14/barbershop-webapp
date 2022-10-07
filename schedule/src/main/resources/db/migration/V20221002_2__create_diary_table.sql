create table if not exists diary(
	"date" date unique not null primary key
);

create table if not exists diary_busy_times(
	"date" date not null,
	busy_times integer,

	foreign key ("date") references diary("date")
);