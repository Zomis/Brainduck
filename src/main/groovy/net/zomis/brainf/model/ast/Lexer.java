package net.zomis.brainf.model.ast;

import net.zomis.brainf.model.classic.BrainFCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class Lexer {

    public static List<Token> tokenize(String s) {
        try {
            return tokenize(new ByteArrayInputStream(s.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read string from bytes: " + Arrays.toString(s.getBytes()), e);
        }
    }

    interface LexerStrategy {
        LexerStrategy parse(char ch);
    }

    private Lexer() {}

    private LexerStrategy current = this::defaultStrategy;
    private TokenInfo tokenInfo = new TokenInfo();
    private List<Token> result = new ArrayList<>();

    private List<Token> process(InputStream stream) throws IOException {
        tokenInfo.length = 1;
        tokenInfo.line = 1;

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        char[] buffer = new char[1024];
        int read;
        while ((read = reader.read(buffer)) > 0) {
            for (int i = 0; i < read; i++) {
                char ch = buffer[i];
                current = current.parse(ch);
                if (ch == '\n') {
                    tokenInfo.line++;
                }
            }
        }
        current.parse((char) 0);
        return result;
    }

    public static List<Token> tokenize(InputStream stream) throws IOException {
        return new Lexer().process(stream);
    }

    private LexerStrategy defaultStrategy(char ch) {
        if (ch == 0) {
            return this::defaultStrategy;
        }
        BrainFCommand bfCommand = BrainFCommand.getCommand(ch);
        if (bfCommand != BrainFCommand.NONE) {
            result.add(new BFToken(tokenInfo, bfCommand));
            tokenInfo.reset();
            return this::defaultStrategy;
        } else if (ch == '$') {
            return this.groovyStart();
        } else {
            return this.comment(ch);
        }
    }

    private LexerStrategy groovyStart() {
        return new LexerStrategy() {
            @Override
            public LexerStrategy parse(char ch) {
                if (ch == 0) {
                    throw new IllegalStateException("Code cannot end with a Groovy start");
                }
                tokenInfo.length++;
                if (ch == '{') {
                    return groovyInside(null, c -> c == '}');
                } else if (ch == ' ') {
                    return this;
                } else {
                    return groovyInside(ch, c -> c == '\n');
                }
            }
        };
    }

    private LexerStrategy groovyInside(Character character, IntPredicate terminate) {
        return new LexerStrategy() {
            StringBuilder str = new StringBuilder(character == null ? "" : character.toString());

            @Override
            public LexerStrategy parse(char ch) {
                if (ch == 0) {
                    throw new IllegalStateException("Groovy section was not closed");
                }
                tokenInfo.length++;
                if (terminate.test(ch)) {
                    result.add(new GroovyToken(tokenInfo, str.toString()));
                    tokenInfo.reset();
                    LexerStrategy next = Lexer.this::defaultStrategy;
                    //next.parse(ch);
                    return next;
                }
                str.append(ch);
                return this;
            }
        };
    }

    private LexerStrategy comment(char ch) {
        return new LexerStrategy() {
            StringBuilder str = new StringBuilder(String.valueOf(ch));

            @Override
            public LexerStrategy parse(char ch) {
                if (ch == 0) {
                    if (tokenInfo.length > 1) {
                        result.add(new CommentToken(tokenInfo, str.toString()));
                        tokenInfo.reset();
                        return Lexer.this::defaultStrategy;
                    }
                }
                BrainFCommand bfCommand = BrainFCommand.getCommand(ch);
                if (bfCommand != BrainFCommand.NONE) {
                    result.add(new CommentToken(tokenInfo, str.toString()));
                    tokenInfo.reset();
                    LexerStrategy next = Lexer.this::defaultStrategy;
                    return next.parse(ch);
                } else if (ch == '$') {
                    result.add(new CommentToken(tokenInfo, str.toString()));
                    tokenInfo.reset();
                    return Lexer.this.groovyStart();
                } else {
                    tokenInfo.length++;
                    str.append(ch);
                    return this;
                }
            }
        };
    }

}
