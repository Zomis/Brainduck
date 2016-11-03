package net.zomis.brainf

import net.zomis.brainf.model.ast.BFToken
import net.zomis.brainf.model.ast.CommentToken
import net.zomis.brainf.model.ast.GroovyToken
import net.zomis.brainf.model.ast.Lexer
import net.zomis.brainf.model.ast.Token
import net.zomis.brainf.model.classic.BrainFCommand
import org.junit.After
import org.junit.Before
import org.junit.Test

class LexerTest {

    private List<Token> tokens;
    private String bfCode;

    void createTokensFromCode(String code) {
        bfCode = code
        tokens = Lexer.tokenize(code)
    }

    @Before
    public void clear() {
        tokens = null
        bfCode = null
    }

    @After
    public void verify() {
        int pos = 0
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens[i]
            assert token.info.position == pos
            pos += token.info.length
        }

        assert bfCode.length() == tokens.stream().mapToInt({it.info.length}).sum()
    }

    @Test
    public void simple() {
        createTokensFromCode("+-[]<>.,");
        assert tokens.size() == 8
        assert (tokens[0] as BFToken).command == BrainFCommand.ADD
        assert (tokens[1] as BFToken).command == BrainFCommand.SUBTRACT
        assert (tokens[2] as BFToken).command == BrainFCommand.WHILE
        assert (tokens[3] as BFToken).command == BrainFCommand.END_WHILE
        assert (tokens[4] as BFToken).command == BrainFCommand.PREVIOUS
        assert (tokens[5] as BFToken).command == BrainFCommand.NEXT
        assert (tokens[6] as BFToken).command == BrainFCommand.WRITE
        assert (tokens[7] as BFToken).command == BrainFCommand.READ
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens[i]
            assert token.info.length == 1
            assert token.info.position == i
            assert token.info.line == 1
            // assert token.info.column == i + 1
        }
    }

    @Test
    public void smallComment() {
        createTokensFromCode("+Hello-");
        assert tokens.size() == 3
        assert (tokens[0] as BFToken).command == BrainFCommand.ADD
        assert (tokens[1] as CommentToken).text == 'Hello'
        assert tokens[1].info.length == 5
        assert (tokens[2] as BFToken).command == BrainFCommand.SUBTRACT
        assert tokens[2].info.position == 6
        // assert tokens[2].info.column == 7
    }

    @Test
    public void withEndOfLineGroovy() {
        createTokensFromCode('+> $ test\nline');
        // 14 length: 1, 1, 1, 7, 4
        assert tokens.size() == 5
        assert (tokens[0] as BFToken).command == BrainFCommand.ADD
        assert (tokens[1] as BFToken).command == BrainFCommand.NEXT
        assert (tokens[2] as CommentToken).text == ' '
        assert (tokens[3] as GroovyToken).code == 'test'
        assert tokens[3].info.length == '$ test\n'.length()
        assert (tokens[4] as CommentToken).text == 'line'
        assert tokens[4].info.position == 10
        assert tokens[4].info.length == 4
        assert tokens[4].info.line == 2
    }

    @Test
    public void withInlineGroovy() {
        createTokensFromCode('+++ ${add three} +++')
        assert tokens.size() == 9
        assert (tokens[0] as BFToken).command == BrainFCommand.ADD
        assert (tokens[1] as BFToken).command == BrainFCommand.ADD
        assert (tokens[2] as BFToken).command == BrainFCommand.ADD
        assert (tokens[3] as CommentToken).text == ' '
        assert (tokens[4] as GroovyToken).code == 'add three'
        assert tokens[4].info.length == 'add three'.length() + 3
        assert (tokens[5] as CommentToken).text == ' '
        assert (tokens[6] as BFToken).command == BrainFCommand.ADD
        assert tokens[6].info.position == bfCode.indexOf(' +') + 1
        // assert tokens[6].info.column == bfCode.indexOf(' +') + 2
        assert (tokens[7] as BFToken).command == BrainFCommand.ADD
        assert (tokens[8] as BFToken).command == BrainFCommand.ADD
    }

    @Test
    public void withMultilineGroovy() {
        createTokensFromCode('''+++> $ {
some code here
}''');
        assert tokens.size() == 6
        assert (tokens[0] as BFToken).command == BrainFCommand.ADD
        assert (tokens[1] as BFToken).command == BrainFCommand.ADD
        assert (tokens[2] as BFToken).command == BrainFCommand.ADD
        assert (tokens[3] as BFToken).command == BrainFCommand.NEXT
        assert (tokens[4] as CommentToken).text == ' '
        assert (tokens[5] as GroovyToken).code == '\nsome code here\n'
    }

}
