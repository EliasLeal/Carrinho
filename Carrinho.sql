/**
 *Atividade de avaliação: Carrinho de compras
 *Ativida 
 *@autor Elias Leal
 */
 
 show databases;
 create database dbcarrinho3;
 use dbcarrinho3;

-- Criar tabela

create table carrinho(
	codigo int primary key auto_increment,
    nome varchar(50) not null,
	quantidade int not null,
    valor decimal(10,2)
);

describe carrinho;
select * from carrinho; 

-- Inserir produtos

insert into carrinho (Nome,quantidade,valor)
values('Call of Duty - MW',2,250.00);

insert into carrinho (Nome,quantidade,valor)
values('Batman Return',1,299.00);

insert into carrinho (Nome,quantidade,valor)
values('GTA V',1,100.00);

insert into carrinho (Nome,quantidade,valor)
values('Word War Z',1,50.00);

insert into carrinho (Nome,quantidade,valor)
values('Mortal Kombat 11',1,150.00);

insert into carrinho (Nome,quantidade,valor)
values('Read Dead',2,250.00);

insert into carrinho (Nome,quantidade,valor)
values('Teste exclusão',1,50.00);

 -- Listar os contatos por ordem alfabetica
 select * from carrinho order by nome;
 
-- Atualizar produto

update carrinho set nome='Read Dead', quantidade=1, 
valor='250.00' where codigo='6';

-- Deletar produto

delete from carrinho where codigo='7';
 
 -- Valor total do carrinho
select sum(valor * quantidade)as Total from carrinho;
 