create table Author (
    author_id serial primary key,
    bio text,
    institution varchar(100)
);

create table "User" (
    user_id bigserial primary key,
    username varchar(100) not null,
    full_name varchar(100),
    email varchar(100),
    password varchar(100),
    age int,
    author_id int,
    foreign key (author_id) references Author(author_id)
);

create table Paper (
    paper_id bigserial primary key,
    title varchar(255),
    abstract_of_paper text,
    publication_date date,
    status varchar(50),
    file_url varchar(255)
);

create table PaperAndAuthor (
    mapping_id bigserial primary key,
    paper_id int,
    author_id int,
    foreign key (paper_id) references Paper(paper_id),
    foreign key (author_id) references Author(author_id)
);
create table Decision (
    decision_id bigserial primary key,
    decision varchar(100)
);

create table Review (
    review_id bigserial primary key,
    paper_id int,
    reviewer_id int,
    comments text,
    review_date date,
    decision_id int,
    foreign key (paper_id) references Paper(paper_id),
    foreign key (reviewer_id) references "User"(user_id),
    foreign key (decision_id) references Decision(decision_id)
);

