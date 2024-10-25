create table author (
    author_id bigserial primary key,
    bio text,
    institution varchar(100)
);

create table user_table (
    user_id bigserial primary key,
    user_name varchar(100) not null,
    full_name varchar(100),
    email varchar(100),
    password varchar(100),
    age int,
    author_id int,
    foreign key (author_id) references author(author_id)
);

create table paper (
    paper_id bigserial primary key,
    title varchar(255),
    abstract_of_paper text,
    publication_date date,
    status varchar(50),
    file_url varchar(255)
);

create table paper_and_author (
    mapping_id bigserial primary key,
    paper_id int,
    author_id int,
    foreign key (paper_id) references paper(paper_id),
    foreign key (author_id) references author(author_id)
);
create table decision (
    decision_id bigserial primary key,
    decision varchar(100)
);

create table review (
    review_id bigserial primary key,
    paper_id int,
    reviewer_id int,
    comments text,
    review_date date,
    decision_id int,
    foreign key (paper_id) references paper(paper_id),
    foreign key (reviewer_id) references user_table(user_id),
    foreign key (decision_id) references decision(decision_id)
);

