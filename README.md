-- O que precisa para rodar kubernetes ?

Stateful postgres com persistance 
Stateful redis com persistance volátil (?)
Stateless app
Stateless nginx

Configurar autoscale para aplicações que usem
Configurar health check.

Event source 

Salvar evento em event store
versionamento de evento

snapshot para otimizar leitura de estado atual -> fazer dia 22/01/26
lógica de read model com projection -> fazer dia 22/01/26
desenho no excalidraw 
lógica para receber eventos externos ez - não vou fazer agora

**Arquitetura completa:**
```
Serviços Externos 
    ↓
Tópico SNS/EventBridge
    ↓
SQS FIFO (com MessageGroupId = externalId)
    ↓
Consumer (processa 1 por vez POR externalId)
    ↓
┌─────────────────┴──────────────┐
↓                                ↓
Event Store                 Read Model
(append com version check)   (projeções)