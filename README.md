Brainduck
=========

[![Code Review](http://www.zomis.net/codereview/shield/?qid=61651&mode=views)](http://codereview.stackexchange.com/q/61651/31562)

Inspired by the [Rubberduck](https://github.com/rubberduck-vba/Rubberduck) project for VBA, this project is called Brainduck (it was either that or Rubberf... erhm...)

Brainduck is a tool for Brainfuck written in Java and Groovy

### Features

- Can run Brainfuck programs
- Editor with line numbers
- Step through the code to see what happens
- Code Analysis
- Memory Analysis

### Planned features

- Groovy DSL (Domain-Specific-Language) for writing Brainfuck programs
- Fully-fledged debugger, with breakpoints
- Brainfuck code to Groovy code conversion
- Support for test-cases
- Syntax highlighting
- Showing Errors and Warnings in Editor
- Showing Tips for how to improve the Brainfuck code

### Try it

    git clone git@github.com:Zomis/Brainduck.git
    cd Brainduck
    ./gradlew dist

A `*-all.jar` file can be found in `Brainduck/build/libs/`, run it with `java -jar *-all.jar`

Or download the latest `*-all.jar` version [from my Jenkins](http://stats.zomis.net:53654/job/brainduck/ws/build/libs/)

### See also

[My contributions to the Brainfuck tag on Code Review](http://codereview.stackexchange.com/search?q=user%3A31562+%5Bbrainfuck%5D)
