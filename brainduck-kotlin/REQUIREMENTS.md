### Architecture

- Vue frontend
- Things running as WebWorkers and communicating with typed messages
  - Probably DO NOT USE anything like https://www.npmjs.com/package/simple-web-worker (uses dirty tricks)

### Editor

- Syntax highlighting: Code purple, comments gray, loops blue
- Mark matching [  ] with yellow background when cursor is adjacent to them
- Mark hints, warnings and errors with blue, yellow and red squiggly underlines

### Running

- Run to cursor
- Run rest of program
- Step in (run past the next matching '[')
- Loop step (run to the next iteration of the current matching '[]' or to the end of it)
- Step out (run past the current matching ']')

### Options

- Cell range: 8-bit, 16-bit
- Memory size, memory wrap around effect (wrap around, no wrap, crash)
- Use negative numbers (wrap around, no wrap, crash)
- End of input read as: 0, leave unchanged...
- Enter in editor read as: 0, 10, 13+10...

### Analysis

- Mark loop names
- Mark cell names, e.g. "fibonnaci_digit", "digit1", "countdown", "count_up"
  - give dynamic names (e.g. "digit1") by using an incremental counter and assigning if it doesn't have, reusing if it does have
- Memory analysis: Times read and written for each cell
- Memory analysis: Check which cells are being used by IO operations . and ,
- Which code is using which cells, and which cells are being used by which code
  - When mode is active, mark a cell to see which code positions are accessing that cell
  - When mode is active, navigate code to see which memory positions are being accessed by that cell
- Count commands and runtime operations

### Tools

- Text generator
- Memory refactoring, mark which memory is used for what and then drag/reposition to change the memory positions
