# Tabela Hash em Java

Implementação de tabela hash com encadeamento separado (`LinkedList`) para cadastro, busca e remoção de registros indexados por CPF, com contagem de colisões, comparações e medição de tempo de busca em nanossegundos.

## Estrutura

```text
src/hash/
├── HashTable.java
├── Main.java
├── Pessoa.java
└── ValidadorCPF.java
```

## Funcionalidades

- Carga inicial automática de 30 registros de teste
- Inserção, busca e remoção por CPF (validação de 11 dígitos numéricos)
- Tratamento de colisões via encadeamento separado
- Exibição de métricas: número de colisões, total de comparações e tempo de busca (ns)

## Como executar

Requer JDK 17 ou superior.

```bash
javac -d out src/hash/*.java
java -cp out hash.Main
```
