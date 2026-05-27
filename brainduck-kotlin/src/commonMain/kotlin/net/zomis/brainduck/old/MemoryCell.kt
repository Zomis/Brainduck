package net.zomis.brainduck.old

interface MemoryCell {
    val address: Int
    var value: Int
    var labels: MutableMap<String, Int>
}



/*
Prio:
x GUI - RUN ALL
x Simple code run
x AST Transformation
- GUI - Stop run
- GUI - Run to cursor
- GUI - Other run modes (step out, step in, next loop...)
- Analysis
  - Code usage (runtime performed + code instructions in source)
  - Cell IO (which memory is read and printed)
  - ... (see old source)
- Mark memory cells with identifiers
- Cell data generator
- Text printer generator

Compose Editor
- step-by-step running
- run to cursor
- step in/out, run next loop, etc.
- show errors and warnings from analysis

Analysis
- requires code to be parsed and processed as an Abstract Syntax Tree
- input code + optional example inputs
- output to JSON, or to compose editor

Other features
- memory cells data generator
- text printer generator (start from 0 or start from some other memory cell state)

$ inline advanced features
- name cells
- assert values / cell names


Simple console run
- code input
- std in (bytes)
- std out (bytes)

*/