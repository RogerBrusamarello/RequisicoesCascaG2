create sequence CidadeId start 1 increment 1
create sequence EstadoId start 1 increment 1
create sequence PessoaId start 1 increment 1
create sequence ProjetoId start 1 increment 1
create sequence ReqAnexoId start 1 increment 1
create sequence RequisicaoAndamentoId start 1 increment 1
create sequence RequisicaoId start 1 increment 1
create sequence RequisicaoProgramadaId start 1 increment 1
create table Administrador (id int8 not null, primary key (id))
create table Cidade (id int4 not null, nome varchar(50), version int4, estado_id int4 not null, primary key (id))
create table Cliente (funcao varchar(80), id int8 not null, primary key (id))
create table Estado (id int4 not null, nome varchar(50), uf varchar(2), version int4, primary key (id))
create table Pessoa (id int8 not null, celular varchar(15), cpf varchar(14) not null, email varchar(100) not null, nome varchar(60) not null, outrasInformacoes text, rg varchar(11) not null, senha varchar(255) not null, version int4, primary key (id))
create table Projeto (id int8 not null, descricao text, nome varchar(200) not null, version int4, cliente_id int8 not null, usuario_id int8 not null, primary key (id))
create table Requisicao (id int8 not null, dataCriada date not null, dataFinalizada timestamp, descricao text, horasPrevistas int4 not null check (horasPrevistas>=0), horasRealizadas int4 not null, prazoLimite date, prioridade int4 not null check (prioridade<=10 AND prioridade>=0), titulo varchar(100) not null, version int4, criou_id int8 not null, projeto_id int8 not null, solicitou_id int8 not null, primary key (id))
create table RequisicaoAndamento (id int8 not null, data date not null, descricao text, horasRealizadas int4 not null check (horasRealizadas>=0), status char(1) not null, titulo varchar(100) not null, version int4, pessoa_id int8 not null, requisicao_id int8 not null, primary key (id))
create table RequisicaoAnexo (id int8 not null, arquivo text, descricao text not null, requisicao_id int8 not null, primary key (id))
create table RequisicaoProgramada (id int8 not null, data date not null, dataInicio date, dataTermino date, version int4, pessoa_id int8 not null, requisicao_id int8 not null, primary key (id))
create table Usuario (id int8 not null, primary key (id))
alter table if exists Pessoa add constraint UK_gej40f8jfd5efnwlggtpwjloo unique (cpf)
alter table if exists Pessoa add constraint UK_ip6c031cyuplfacn09ca3twtm unique (email)
alter table if exists Administrador add constraint FKohscrao0nv2xhkp76206c8xm1 foreign key (id) references Pessoa
alter table if exists Cidade add constraint FKsatretdvg03if89kwwmiagnyd foreign key (estado_id) references Estado
alter table if exists Cliente add constraint FKbglg1ye0wf48r3l2qbk0nep37 foreign key (id) references Pessoa
alter table if exists Projeto add constraint FKkniewetm2ddy7ebuso8q7x7vr foreign key (cliente_id) references Cliente
alter table if exists Projeto add constraint FKbc45rmb2smv4n8spirrqas8fg foreign key (usuario_id) references Usuario
alter table if exists Requisicao add constraint FK33rfvxxe97ix7odoc896akrc2 foreign key (criou_id) references Pessoa
alter table if exists Requisicao add constraint FKgxcpqmptxsgj7iu2xklvu7ao4 foreign key (projeto_id) references Projeto
alter table if exists Requisicao add constraint FK5qw8nkokfm6ntxuivflaiuk15 foreign key (solicitou_id) references Pessoa
alter table if exists RequisicaoAndamento add constraint FKmapcminuimo1iyavu906kh7no foreign key (pessoa_id) references Pessoa
alter table if exists RequisicaoAndamento add constraint FKbtrmdu4r22a8b11iqw3sagdku foreign key (requisicao_id) references Requisicao
alter table if exists RequisicaoAnexo add constraint FK343qfuvdp58igmfebsl1j31b7 foreign key (requisicao_id) references Requisicao
alter table if exists RequisicaoProgramada add constraint FKr099nk83rjsyi3ya7phje1alv foreign key (pessoa_id) references Pessoa
alter table if exists RequisicaoProgramada add constraint FKssmjptmvq0oixj81fa3exihb6 foreign key (requisicao_id) references Requisicao
alter table if exists Usuario add constraint FK9b5ep6wuhibyj4jvphdn1kx4w foreign key (id) references Pessoa
