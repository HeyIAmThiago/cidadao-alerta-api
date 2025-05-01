# 📢 API de Denúncias de Problemas Urbanos – "Cidadão Alerta"

## Contexto do Projeto:
Moradores de uma cidade querem reportar problemas urbanos (ex: buraco na rua, semáforo quebrado, lixo acumulado) direto pelo celular. A prefeitura (ou empresa fictícia) responde, organiza e resolve os casos.

## Objetivo:
Criar uma API REST com autenticação JWT onde:
- Cidadãos se cadastram e fazem denúncias.
- Auditores (da prefeitura) classificam, atualizam status e respondem.
- Tudo com segurança, histórico e controle de acesso.

## Papéis
- **Cidadão**: faz denúncias, vê suas denúncias e responde comentários.
- **Auditor**: analisa denúncias, define prioridade e responde.
- **Admin**: gerencia auditores.

## Requisitos Funcionais

### Autenticação e Usuários
- Registro com nome, e-mail e senha (cidadão por padrão).
- Login com JWT.
- **Role-based access control**: `ROLE_USER`, `ROLE_AUDITOR`, `ROLE_ADMIN`.

### Denúncias
- Criar denúncia com:
  - Título
  - Descrição
  - Categoria (ex: Iluminação, Pavimentação, Coleta de lixo)
  - Localização (bairro, rua, CEP)
  - Imagem opcional (só guardar path da imagem no back-end)
- Ver lista das próprias denúncias.
- Auditor pode mudar status: `PENDENTE`, `EM ANÁLISE`, `RESOLVIDO`, `ARQUIVADO`.
- Auditor pode adicionar comentário oficial.

### Ideias pra implementar
- Feed público das denúncias (anônimo).
- Like em denúncias para mostrar relevância.
- Comentários em denúncias
- Fotos/Vídeos em denúncias


## Exemplos de Endpoints

| Método | Endpoints                        | Descrição                                |
|--------|---------------------------------|-----------------------------------------|
| POST   |                                 |                                         |
