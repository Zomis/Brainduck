package net.zomis.brainf.model.ast;

import net.zomis.brainf.model.classic.BrainFCommand

class Lexer private constructor() {

    companion object {
        fun tokenize(s: String): List<Token> {
            try {
                return tokenize(s.encodeToByteArray())
            } catch (e: Exception) {
                throw RuntimeException("Unable to read string from bytes: " + s.encodeToByteArray().contentToString(), e)
            }
        }
        fun tokenize(stream: ByteArray): List<Token> {
            return Lexer().process(stream)
        }
    }

    fun interface LexerStrategy {
        fun parse(ch: Char): LexerStrategy
    }

    val defaultStrategy: LexerStrategy get() = object : LexerStrategy {
        override fun parse(ch: Char): LexerStrategy {
            if (ch == 0.toChar()) {
                return defaultStrategy
            }
            val bfCommand = BrainFCommand.getCommand(ch)
            if (bfCommand != BrainFCommand.NONE) {
                result.add(BFToken(tokenInfo, bfCommand))
                tokenInfo.reset();
                return defaultStrategy
            } else if (ch == '$') {
                TODO("Brainduck-specific commands not supported yet")
//            return this.groovyStart();
            } else {
                return comment(ch)
            }
        }
    }

    private var current: LexerStrategy = defaultStrategy
    private var tokenInfo = TokenInfo.createNew()
    private val result = mutableListOf<Token>()

    private fun process(stream: ByteArray): List<Token> {
        tokenInfo.length = 1
        tokenInfo.line = 1

        for (b in stream) {
            val ch = b.toChar()
            current = current.parse(ch);
            if (ch == '\n') {
                tokenInfo.line++;
            }
        }
        current.parse(0.toChar());
        return result;
    }

//    private fun groovyStart() {
//        return new LexerStrategy() {
//            @Override
//            public LexerStrategy parse(char ch) {
//                if (ch == 0) {
//                    throw new IllegalStateException("Code cannot end with a Groovy start");
//                }
//                tokenInfo.length++;
//                if (ch == '{') {
//                    return groovyInside(null, c -> c == '}');
//                } else if (ch == ' ') {
//                    return this;
//                } else {
//                    return groovyInside(ch, c -> c == '\n');
//                }
//            }
//        };
//    }

//    private LexerStrategy groovyInside(Character character, IntPredicate terminate) {
//        return new LexerStrategy() {
//            StringBuilder str = new StringBuilder(character == null ? "" : character.toString());
//
//            @Override
//            public LexerStrategy parse(char ch) {
//                if (ch == 0) {
//                    throw new IllegalStateException("Groovy section was not closed");
//                }
//                tokenInfo.length++;
//                if (terminate.test(ch)) {
//                    result.add(new GroovyToken(tokenInfo, str.toString()));
//                    tokenInfo.reset();
//                    LexerStrategy next = Lexer.this::defaultStrategy;
//xxxxxxxxxxx Was commented before                    next.parse(ch);
//                    return next;
//                }
//                str.append(ch);
//                return this;
//            }
//        };
//    }

    private fun comment(ch: Char): LexerStrategy {
        return object : LexerStrategy {
            private val str = StringBuilder(ch.toString())

            override fun parse(ch: Char): LexerStrategy {
                if (ch == 0.toChar()) {
                    if (tokenInfo.length > 1) {
                        result.add(CommentToken(tokenInfo, str.toString()))
                        tokenInfo.reset();
                        return defaultStrategy
                    }
                }
                val bfCommand = BrainFCommand.getCommand(ch)
                if (bfCommand != BrainFCommand.NONE) {
                    result.add(CommentToken(tokenInfo, str.toString()));
                    tokenInfo.reset();
                    val next = defaultStrategy
                    return next.parse(ch)
                } else if (ch == '$') {
                    TODO()
//                    result.add(CommentToken(tokenInfo, str.toString()))
//                    tokenInfo.reset();
//                    return this@Lexer.groovyStart();
                } else {
                    tokenInfo.length++;
                    str.append(ch);
                    return this;
                }
            }
        }
    }

}
