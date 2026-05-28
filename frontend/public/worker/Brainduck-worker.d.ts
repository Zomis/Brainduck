type Nullable<T> = T | null | undefined
declare function KtSingleton<T>(): T & (abstract new() => any);
export declare abstract class Brainfuck {
    static readonly getInstance: () => typeof Brainfuck.$metadata$.type;
    private constructor();
}
/** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
export declare namespace Brainfuck.$metadata$ {
    abstract class type extends KtSingleton<constructor>() {
        private constructor();
    }
    namespace type {
        abstract class Run extends KtSingleton<Run.$metadata$.constructor>() {
            private constructor();
        }
        /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
        namespace Run.$metadata$ {
            abstract class constructor {
                get all(): any/* typeof UntilEnd.$metadata$.type */;
                get step(): any/* Runner */;
                get stepSyntax(): any/* StepRunner */;
                get loopStart(): any/* typeof LoopStartRunner.$metadata$.type */;
                steps(steps: number): any/* StepRunner */;
                private constructor();
            }
        }
    }
    abstract class constructor {
        tokenize(text: string): any/* TokenizedProgram */;
        code(text: string): any/* BrainfuckCode */;
        private constructor();
    }
}