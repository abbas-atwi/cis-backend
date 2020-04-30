create table usuario (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    sobrenome varchar (60) not null,
    sexo enum('M','F'),
    data_nascimento date,
    telefone varchar(20),
    email varchar(60) not null,
    status enum('ATIVO','INATIVO') not null,

    primary key (id)
);