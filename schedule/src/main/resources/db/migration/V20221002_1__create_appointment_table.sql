create table if not exists appointment(
	id_appointment uuid unique not null primary key,
	"date" date not null,
	start_at time without time zone not null,
	duration integer not null,
	customer_id uuid not null,
	barber_id uuid not null,
	status varchar(16) not null
);

create table if not exists appointment_services(
    "appointment_id" uuid not null,
    service_id integer not null,
    foreign key ("appointment_id") references appointment("id_appointment")
);

