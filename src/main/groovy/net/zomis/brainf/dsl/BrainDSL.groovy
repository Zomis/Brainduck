package net.zomis.brainf.dsl

import org.codehaus.groovy.control.CompilerConfiguration;

class BrainDSL {

    private final StringBuilder code = new StringBuilder()

    BrainDSL runScript(File file) {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(cc)
        DelegatingScript script = (DelegatingScript) sh.parse(file)
        script.setDelegate(this)
        script.run()
        this
    }

    BrainDSL runScript(String text) {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(cc)
        DelegatingScript script = (DelegatingScript) sh.parse(text)
        script.setDelegate(this)
        script.run()
        this
    }

    BrainDSL right() {
        right 1
    }

    BrainDSL right(int steps) {
        assert steps >= 0: 'steps cannot be negative'
        addCode('>' * steps)
    }

    BrainDSL left() {
        left 1
    }

    BrainDSL left(int steps) {
        assert steps >= 0: 'steps cannot be negative'
        addCode('<' * steps)
    }

    BrainDSL inc(int count) {
        assert count >= 0: 'Count cannot be negative'
        addCode('+' * count)
    }

    BrainDSL dec(int count) {
        assert count >= 0: 'Count cannot be negative'
        addCode('-' * count)
    }

    BrainDSL change(int count) {
        return count >= 0 ? inc(count) : dec(-count)
    }

    BrainDSL reset() {
        addCode('[-]')
    }

    BrainDSL loop(Closure closure) {
        addCode('[')
        closure.setDelegate(this)
        closure.call()
        addCode(']')
    }

    BrainDSL set(Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                addCode('>')
            }
            println 'set ' + args[i]
            set(args[i])
        }
        this
    }

    BrainDSL set(Object text) {
        if (text == null) {
            throw new IllegalArgumentException('text cannot be null')
        }
        println 'set ' + text + ' of type ' + text.class
        if (text instanceof Integer) {
            set(text as int)
        } else if (text instanceof String) {
            set text as String
        } else if (text instanceof Character) {
            set String.valueOf(text)
        } else {
            throw new IllegalArgumentException('Invalid type: ' + text)
        }
        this
    }

    BrainDSL set(String text) {
        println 'set text ' + text + ' length ' + text.length()
        for (int i = 0; i < text.length(); i++) {
            if (i > 0) {
                addCode('>')
            }
            char ch = text.charAt(i)
            set(ch as int)
        }
        this
    }

    BrainDSL set(int value) {
        println 'set int ' + value
        addCode('[-]')
        if (value > 0) {
            addCode('+' * value)
        } else {
            addCode('-' * -value)
        }
    }

    BrainDSL print() {
        addCode('.')
    }

    BrainDSL read() {
        addCode(',')
    }

    BrainDSL addCode(String s) {
        code.append(s)
        this
    }

    String getCode() {
        code.toString()
    }

    BrainDSL printNumber() {
        addCode('''
Print Number Algorithm
>++++++++++<<[->+>-[>+>>]>[+[-<+>]>+>>]<<<<<<]>>[-]>>>++++++++++<[->-[>+>>]>[+[-
<+>]>+>>]<<<<<]>[-]>>[>++++++[-<++++++++>]<.<<+>+>[-]]<[<[->-<]++++++[->++++++++
<]>.[-]]<<++++++[-<++++++++>]<.[-]<<[-<+>]
Print Number Algorithm END
''')
    }

}