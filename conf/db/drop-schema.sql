alter table if exists Administrador drop constraint if exists FKohscrao0nv2xhkp76206c8xm1
alter table if exists Cidade drop constraint if exists FKsatretdvg03if89kwwmiagnyd
alter table if exists Cliente drop constraint if exists FKbglg1ye0wf48r3l2qbk0nep37
alter table if exists Projeto drop constraint if exists FKkniewetm2ddy7ebuso8q7x7vr
alter table if exists Projeto drop constraint if exists FKbc45rmb2smv4n8spirrqas8fg
alter table if exists Requisicao drop constraint if exists FK33rfvxxe97ix7odoc896akrc2
alter table if exists Requisicao drop constraint if exists FKgxcpqmptxsgj7iu2xklvu7ao4
alter table if exists Requisicao drop constraint if exists FK5qw8nkokfm6ntxuivflaiuk15
alter table if exists RequisicaoAndamento drop constraint if exists FKmapcminuimo1iyavu906kh7no
alter table if exists RequisicaoAndamento drop constraint if exists FKbtrmdu4r22a8b11iqw3sagdku
alter table if exists RequisicaoAnexo drop constraint if exists FK343qfuvdp58igmfebsl1j31b7
alter table if exists RequisicaoProgramada drop constraint if exists FKr099nk83rjsyi3ya7phje1alv
alter table if exists RequisicaoProgramada drop constraint if exists FKssmjptmvq0oixj81fa3exihb6
alter table if exists Usuario drop constraint if exists FK9b5ep6wuhibyj4jvphdn1kx4w
drop table if exists Administrador cascade
drop table if exists Cidade cascade
drop table if exists Cliente cascade
drop table if exists Estado cascade
drop table if exists Pessoa cascade
drop table if exists Projeto cascade
drop table if exists Requisicao cascade
drop table if exists RequisicaoAndamento cascade
drop table if exists RequisicaoAnexo cascade
drop table if exists RequisicaoProgramada cascade
drop table if exists Usuario cascade
drop sequence if exists CidadeId
drop sequence if exists EstadoId
drop sequence if exists PessoaId
drop sequence if exists ProjetoId
drop sequence if exists ReqAnexoId
drop sequence if exists RequisicaoAndamentoId
drop sequence if exists RequisicaoId
drop sequence if exists RequisicaoProgramadaId
