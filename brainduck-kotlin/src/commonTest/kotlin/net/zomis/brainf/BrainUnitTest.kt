package net.zomis.brainf

import kotlin.test.BeforeTest
import kotlin.test.Test

class BrainUnitTest {
    // TODO: Requires advanced commands support

/*
    BrainfuckRunner runner
    StringBuilder output

    @BeforeTest
    void setup() {
        output = new StringBuilder()
        runner = new BrainfuckRunner(new BrainfuckMemory(),
                new BrainfuckCode(), new NoInput(), new StringBuilderOutput(output))
    }

    @Test
    void assertingValue() {
        addCommands('''
            ++++
            $ assert value == 4
            $ assert value == 5
        ''')
        expectFailure('value == 5')
    }

    @Test
    void assertingPosition() {
        addCommands('''
            >>
            $ assert position == 2
            $ assert position == 1
        ''')
        expectFailure('position == 1')
    }

    @Test
    void assertingMemoryArray() {
        addCommands('''
             +
            >++
            >+++
            >++++
            >+++++
            >+++++ +
            $ {
                def arr = memory 6 offsetBackward 5
                def exp = values 1 2 3 4 5 6
                assert arr == exp
                assert false
            }
        ''')
        expectFailure('assert false')
    }

    void addCommands(String code) {
        runner.code.source = ListCode.create(code)
        runner.code.rootTree = new Parser(new GroovyBFContext())
            .parse(Lexer.tokenize(code))
    }

    void expectFailure(String expectedContains) {
        try {
            runner.run(new UntilEndStrategy())
            assert false : 'No assertion error was thrown'
        } catch (AssertionError error) {
            assert error.getMessage().contains(expectedContains)
        }
    }
*/
}
