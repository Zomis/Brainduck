import {
  protoOf180f3jzyo7rfj as protoOf,
  NotImplementedErrorfzlkpv14xxr8 as NotImplementedError,
  defineProp3hxgpk2knu2px as defineProp,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  ArrayList_init_$Create$149jv2ovkkvnt as ArrayList_init_$Create$,
  first58ocm7j58k3q as first,
  toString1pkumu07cwy4m as toString,
  IllegalStateException_init_$Create$2w9444nebyjns as IllegalStateException_init_$Create$,
  equals2au1ep9vhcato as equals,
  Unit_instance1fbcbse1fwigr as Unit_instance,
  singleo93pzdgfc557 as single,
  toList3jhuyej2anx2q as toList,
  listOfvhqybd2zx248 as listOf,
  noWhenBranchMatchedException2a6r7ubxgky5j as noWhenBranchMatchedException,
  removeLast3759euu1xvfa3 as removeLast,
  last1vo29oleiqj36 as last,
  addAll1k27qatfgp3k5 as addAll,
  plus310ted5e4i90h as plus,
  plus20p0vtfmu0596 as plus_0,
  filterNotNull3qfgcwmxhwfxe as filterNotNull,
  StringBuilder_init_$Create$2qsge4ydj6bin as StringBuilder_init_$Create$,
  isInterface3d6p8outrmvmk as isInterface,
  lines3g90sq0zeq43v as lines,
  charCodeAt1yspne1d8erbm as charCodeAt,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  VOID3gxj6tk5isa35 as VOID,
  _Char___init__impl__6a9atx2js6krycynjoo as _Char___init__impl__6a9atx,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  IllegalArgumentException_init_$Create$3ewkh27kzt8z8 as IllegalArgumentException_init_$Create$,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
initMetadataForObject(Run, 'Run');
initMetadataForObject(Brainfuck_0, 'Brainfuck');
initMetadataForClass(BrainfuckCode, 'BrainfuckCode');
initMetadataForClass(LoopSyntax, 'LoopSyntax');
initMetadataForClass(TokenizedProgram, 'TokenizedProgram');
initMetadataForClass(CommentStrategy, 'CommentStrategy');
initMetadataForClass(DefaultStrategy, 'DefaultStrategy');
initMetadataForClass(Lexer, 'Lexer', Lexer);
initMetadataForObject(Read, 'Read');
initMetadataForObject(Write, 'Write');
initMetadataForClass(Move, 'Move');
initMetadataForClass(ChangeValue, 'ChangeValue');
initMetadataForClass(WhileNotZero, 'WhileNotZero');
initMetadataForObject(EndWhile, 'EndWhile');
initMetadataForObject(Comment, 'Comment');
initMetadataForClass(Root, 'Root');
initMetadataForClass(Syntax, 'Syntax');
initMetadataForClass(SyntaxInfo, 'SyntaxInfo');
initMetadataForClass(Token, 'Token');
initMetadataForInterface(Instruction, 'Instruction');
initMetadataForClass(Repeatable, 'Repeatable');
initMetadataForClass(Advanced, 'Advanced');
initMetadataForClass(Comment_0, 'Comment');
initMetadataForObject(Write_0, 'Write', VOID, VOID, [Instruction]);
initMetadataForObject(Read_0, 'Read', VOID, VOID, [Instruction]);
initMetadataForObject(Plus, 'Plus', VOID, Repeatable, [Instruction, Repeatable]);
initMetadataForObject(Minus, 'Minus', VOID, Repeatable, [Instruction, Repeatable]);
initMetadataForObject(MoveLeft, 'MoveLeft', VOID, Repeatable, [Instruction, Repeatable]);
initMetadataForObject(MoveRight, 'MoveRight', VOID, Repeatable, [Instruction, Repeatable]);
initMetadataForObject(StartWhile, 'StartWhile', VOID, VOID, [Instruction]);
initMetadataForObject(EndWhile_0, 'EndWhile', VOID, VOID, [Instruction]);
initMetadataForCompanion(Companion);
initMetadataForClass(TokenInfo, 'TokenInfo');
initMetadataForObject(LoopStartRunner, 'LoopStartRunner');
initMetadataForClass(StepRunner, 'StepRunner', StepRunner);
initMetadataForObject(UntilEnd, 'UntilEnd');
//endregion
function Run() {
  Run_instance = this;
  this.all = UntilEnd_instance;
  this.stepSyntax = new StepRunner(1);
  this.loopStart = LoopStartRunner_instance;
}
protoOf(Run).c4 = function () {
  return this.all;
};
protoOf(Run).d4 = function () {
  throw new NotImplementedError();
};
protoOf(Run).e4 = function () {
  return this.stepSyntax;
};
protoOf(Run).f4 = function () {
  return this.loopStart;
};
protoOf(Run).steps = function (steps) {
  return new StepRunner(steps);
};
var Run_instance;
function Run_getInstance() {
  if (Run_instance == null)
    new Run();
  return Run_instance;
}
function Brainfuck_0() {
}
protoOf(Brainfuck_0).tokenize = function (text) {
  return (new Lexer()).l4(text);
};
protoOf(Brainfuck_0).code = function (text) {
  return this.tokenize(text).n4();
};
var Brainfuck_instance;
function Brainfuck_getInstance() {
  return Brainfuck_instance;
}
function BrainfuckCode(syntax) {
  this.o4_1 = syntax;
}
function LoopSyntax(startToken) {
  this.p4_1 = startToken;
  var tmp = this;
  // Inline function 'kotlin.collections.mutableListOf' call
  tmp.q4_1 = ArrayList_init_$Create$();
}
function withData($this, _this__u8e3s4, data) {
  return new Syntax(_this__u8e3s4, data);
}
function createRepeatSyntax($this, awaitingTokens, diff) {
  var token = first(awaitingTokens);
  var syntaxInfo = new SyntaxInfo(awaitingTokens);
  var tmp0_subject = token.s4_1;
  var tmp;
  if (equals(tmp0_subject, Plus_getInstance()) || equals(tmp0_subject, Minus_getInstance())) {
    tmp = new Syntax(syntaxInfo, new ChangeValue(diff));
  } else if (equals(tmp0_subject, MoveRight_getInstance()) || equals(tmp0_subject, MoveLeft_getInstance())) {
    tmp = new Syntax(syntaxInfo, new Move(diff));
  } else {
    var message = 'createRepeatSyntax should only be used for repeatable tokens: ' + toString(awaitingTokens) + ' ' + diff;
    throw IllegalStateException_init_$Create$(toString(message));
  }
  return tmp;
}
function TokenizedProgram(tokens) {
  this.m4_1 = tokens;
}
protoOf(TokenizedProgram).n4 = function () {
  // Inline function 'kotlin.collections.mutableListOf' call
  var awaitingTokens = ArrayList_init_$Create$();
  var lastToken = null;
  var diff = 0;
  // Inline function 'kotlin.collections.mutableListOf' call
  var depthStack = ArrayList_init_$Create$();
  depthStack.m(new LoopSyntax(null));
  var syntaxes = single(depthStack).q4_1;
  var _iterator__ex2g4s = this.m4_1.e();
  while (_iterator__ex2g4s.f()) {
    var token = _iterator__ex2g4s.g();
    var last_0 = lastToken;
    var tmp;
    if (!equals(last_0 == null ? null : last_0.s4_1, token.s4_1)) {
      // Inline function 'kotlin.collections.isNotEmpty' call
      tmp = !awaitingTokens.n();
    } else {
      tmp = false;
    }
    if (tmp) {
      syntaxes.m(createRepeatSyntax(this, toList(awaitingTokens), diff));
      awaitingTokens.s1();
      diff = 0;
    }
    var syntaxInfo = new SyntaxInfo(listOf(token));
    var tmp1_subject = token.s4_1;
    if (tmp1_subject instanceof Advanced) {
      // Inline function 'kotlin.TODO' call
      throw new NotImplementedError();
    } else {
      if (tmp1_subject instanceof Comment_0)
        syntaxes.m(withData(this, syntaxInfo, Comment_instance));
      else {
        if (equals(tmp1_subject, StartWhile_instance)) {
          depthStack.m(new LoopSyntax(token));
          syntaxes = last(depthStack).q4_1;
        } else {
          if (equals(tmp1_subject, EndWhile_instance_0)) {
            var popped = removeLast(depthStack);
            syntaxes = last(depthStack).q4_1;
            var tmp_0 = listOf(popped.p4_1);
            // Inline function 'kotlin.collections.flatMap' call
            var tmp0 = popped.q4_1;
            // Inline function 'kotlin.collections.flatMapTo' call
            var destination = ArrayList_init_$Create$();
            var _iterator__ex2g4s_0 = tmp0.e();
            while (_iterator__ex2g4s_0.f()) {
              var element = _iterator__ex2g4s_0.g();
              var list = element.v4_1.u4_1;
              addAll(destination, list);
            }
            var tokens = plus_0(plus(tmp_0, destination), token);
            var endWhile = new Syntax(syntaxInfo, EndWhile_instance);
            syntaxes.m(new Syntax(new SyntaxInfo(filterNotNull(tokens)), new WhileNotZero(plus_0(popped.q4_1, endWhile))));
          } else {
            if (equals(tmp1_subject, Read_instance_0))
              syntaxes.m(new Syntax(syntaxInfo, Read_instance));
            else {
              if (equals(tmp1_subject, Write_instance_0))
                syntaxes.m(new Syntax(syntaxInfo, Write_instance));
              else {
                if (tmp1_subject instanceof Repeatable) {
                  awaitingTokens.m(token);
                  diff = diff + token.s4_1.t4_1 | 0;
                } else {
                  noWhenBranchMatchedException();
                }
              }
            }
          }
        }
      }
    }
    lastToken = token;
  }
  // Inline function 'kotlin.collections.isNotEmpty' call
  if (!awaitingTokens.n()) {
    syntaxes.m(createRepeatSyntax(this, toList(awaitingTokens), diff));
  }
  return new BrainfuckCode(new Root(syntaxes));
};
function CommentStrategy($outer) {
  this.y4_1 = $outer;
  this.x4_1 = StringBuilder_init_$Create$();
}
protoOf(CommentStrategy).z4 = function (ch) {
  var token = Companion_instance.a5(ch);
  var tmp;
  if (!(token == null) ? isInterface(token, Instruction) : false) {
    this.y4_1.g4_1.m(nextTokenInfo(this.y4_1, new Comment_0(this.x4_1.toString())));
    tmp = (new DefaultStrategy(this.y4_1)).z4(ch);
  } else {
    this.x4_1.o3(this.x4_1.toString());
    tmp = this;
  }
  return tmp;
};
protoOf(CommentStrategy).c5 = function () {
};
function DefaultStrategy($outer) {
  this.b5_1 = $outer;
}
protoOf(DefaultStrategy).z4 = function (ch) {
  var bfCommand = Companion_instance.a5(ch);
  var tmp;
  if (!(bfCommand == null)) {
    this.b5_1.g4_1.m(nextTokenInfo(this.b5_1, bfCommand));
    tmp = this;
  } else {
    tmp = (new CommentStrategy(this.b5_1)).z4(ch);
  }
  return tmp;
};
protoOf(DefaultStrategy).c5 = function () {
};
function nextTokenInfo($this, data) {
  var tokenLength = 1;
  var info = new TokenInfo($this.h4_1, tokenLength, $this.i4_1, $this.j4_1, $this.k4_1);
  $this.h4_1 = $this.h4_1 + tokenLength | 0;
  $this.j4_1 = $this.j4_1 + 1 | 0;
  return new Token(info, data);
}
function Lexer() {
  var tmp = this;
  // Inline function 'kotlin.collections.mutableListOf' call
  tmp.g4_1 = ArrayList_init_$Create$();
  this.h4_1 = 1;
  this.i4_1 = 1;
  this.j4_1 = 1;
  this.k4_1 = '.';
}
protoOf(Lexer).l4 = function (text) {
  var strategy = new DefaultStrategy(this);
  var iterator = lines(text).e();
  var index = 0;
  while (iterator.f()) {
    var lineIndex = index;
    index = index + 1 | 0;
    var lineText = iterator.g();
    this.i4_1 = lineIndex + 1 | 0;
    this.j4_1 = 1;
    var inductionVariable = 0;
    var last = lineText.length;
    while (inductionVariable < last) {
      var c = charCodeAt(lineText, inductionVariable);
      inductionVariable = inductionVariable + 1 | 0;
      strategy = strategy.z4(c);
    }
  }
  strategy.c5();
  return new TokenizedProgram(toList(this.g4_1));
};
function Read() {
}
protoOf(Read).toString = function () {
  return 'Read';
};
protoOf(Read).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Read))
    return false;
  other instanceof Read || THROW_CCE();
  return true;
};
var Read_instance;
function Read_getInstance() {
  return Read_instance;
}
function Write() {
}
protoOf(Write).toString = function () {
  return 'Write';
};
protoOf(Write).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Write))
    return false;
  other instanceof Write || THROW_CCE();
  return true;
};
var Write_instance;
function Write_getInstance() {
  return Write_instance;
}
function Move(delta) {
  this.d5_1 = delta;
}
protoOf(Move).toString = function () {
  return 'Move(delta=' + this.d5_1 + ')';
};
protoOf(Move).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Move))
    return false;
  var tmp0_other_with_cast = other instanceof Move ? other : THROW_CCE();
  if (!(this.d5_1 === tmp0_other_with_cast.d5_1))
    return false;
  return true;
};
function ChangeValue(delta) {
  this.e5_1 = delta;
}
protoOf(ChangeValue).toString = function () {
  return 'ChangeValue(delta=' + this.e5_1 + ')';
};
protoOf(ChangeValue).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof ChangeValue))
    return false;
  var tmp0_other_with_cast = other instanceof ChangeValue ? other : THROW_CCE();
  if (!(this.e5_1 === tmp0_other_with_cast.e5_1))
    return false;
  return true;
};
function WhileNotZero(children) {
  this.f5_1 = children;
}
protoOf(WhileNotZero).toString = function () {
  return 'WhileNotZero(children=' + toString(this.f5_1) + ')';
};
protoOf(WhileNotZero).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof WhileNotZero))
    return false;
  var tmp0_other_with_cast = other instanceof WhileNotZero ? other : THROW_CCE();
  if (!equals(this.f5_1, tmp0_other_with_cast.f5_1))
    return false;
  return true;
};
function EndWhile() {
}
protoOf(EndWhile).toString = function () {
  return 'EndWhile';
};
protoOf(EndWhile).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof EndWhile))
    return false;
  other instanceof EndWhile || THROW_CCE();
  return true;
};
var EndWhile_instance;
function EndWhile_getInstance() {
  return EndWhile_instance;
}
function Comment() {
}
protoOf(Comment).toString = function () {
  return 'Comment';
};
protoOf(Comment).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Comment))
    return false;
  other instanceof Comment || THROW_CCE();
  return true;
};
var Comment_instance;
function Comment_getInstance() {
  return Comment_instance;
}
function Root(children) {
  this.g5_1 = children;
}
protoOf(Root).toString = function () {
  return 'Root(children=' + toString(this.g5_1) + ')';
};
protoOf(Root).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Root))
    return false;
  var tmp0_other_with_cast = other instanceof Root ? other : THROW_CCE();
  if (!equals(this.g5_1, tmp0_other_with_cast.g5_1))
    return false;
  return true;
};
function Syntax(info, data) {
  this.v4_1 = info;
  this.w4_1 = data;
}
protoOf(Syntax).toString = function () {
  return 'Syntax(info=' + this.v4_1.toString() + ', data=' + toString(this.w4_1) + ')';
};
protoOf(Syntax).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Syntax))
    return false;
  var tmp0_other_with_cast = other instanceof Syntax ? other : THROW_CCE();
  if (!this.v4_1.equals(tmp0_other_with_cast.v4_1))
    return false;
  if (!equals(this.w4_1, tmp0_other_with_cast.w4_1))
    return false;
  return true;
};
function SyntaxInfo(tokens) {
  this.u4_1 = tokens;
}
protoOf(SyntaxInfo).toString = function () {
  return 'SyntaxInfo(tokens=' + toString(this.u4_1) + ')';
};
protoOf(SyntaxInfo).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof SyntaxInfo))
    return false;
  var tmp0_other_with_cast = other instanceof SyntaxInfo ? other : THROW_CCE();
  if (!equals(this.u4_1, tmp0_other_with_cast.u4_1))
    return false;
  return true;
};
function Token(info, data) {
  this.r4_1 = info;
  this.s4_1 = data;
}
protoOf(Token).toString = function () {
  return 'Token(info=' + this.r4_1.toString() + ', data=' + toString(this.s4_1) + ')';
};
protoOf(Token).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Token))
    return false;
  var tmp0_other_with_cast = other instanceof Token ? other : THROW_CCE();
  if (!this.r4_1.equals(tmp0_other_with_cast.r4_1))
    return false;
  if (!equals(this.s4_1, tmp0_other_with_cast.s4_1))
    return false;
  return true;
};
function Instruction() {
}
function Repeatable(count) {
  this.t4_1 = count;
}
function Advanced() {
}
function Comment_0(text) {
  this.h5_1 = text;
}
protoOf(Comment_0).toString = function () {
  return 'Comment(text=' + this.h5_1 + ')';
};
protoOf(Comment_0).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Comment_0))
    return false;
  var tmp0_other_with_cast = other instanceof Comment_0 ? other : THROW_CCE();
  if (!(this.h5_1 === tmp0_other_with_cast.h5_1))
    return false;
  return true;
};
function Write_0() {
}
protoOf(Write_0).toString = function () {
  return 'Write';
};
protoOf(Write_0).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Write_0))
    return false;
  other instanceof Write_0 || THROW_CCE();
  return true;
};
var Write_instance_0;
function Write_getInstance_0() {
  return Write_instance_0;
}
function Read_0() {
}
protoOf(Read_0).toString = function () {
  return 'Read';
};
protoOf(Read_0).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Read_0))
    return false;
  other instanceof Read_0 || THROW_CCE();
  return true;
};
var Read_instance_0;
function Read_getInstance_0() {
  return Read_instance_0;
}
function Plus() {
  Plus_instance = this;
  Repeatable.call(this, 1);
}
protoOf(Plus).toString = function () {
  return 'Plus';
};
protoOf(Plus).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Plus))
    return false;
  other instanceof Plus || THROW_CCE();
  return true;
};
var Plus_instance;
function Plus_getInstance() {
  if (Plus_instance == null)
    new Plus();
  return Plus_instance;
}
function Minus() {
  Minus_instance = this;
  Repeatable.call(this, -1);
}
protoOf(Minus).toString = function () {
  return 'Minus';
};
protoOf(Minus).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Minus))
    return false;
  other instanceof Minus || THROW_CCE();
  return true;
};
var Minus_instance;
function Minus_getInstance() {
  if (Minus_instance == null)
    new Minus();
  return Minus_instance;
}
function MoveLeft() {
  MoveLeft_instance = this;
  Repeatable.call(this, -1);
}
protoOf(MoveLeft).toString = function () {
  return 'MoveLeft';
};
protoOf(MoveLeft).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof MoveLeft))
    return false;
  other instanceof MoveLeft || THROW_CCE();
  return true;
};
var MoveLeft_instance;
function MoveLeft_getInstance() {
  if (MoveLeft_instance == null)
    new MoveLeft();
  return MoveLeft_instance;
}
function MoveRight() {
  MoveRight_instance = this;
  Repeatable.call(this, 1);
}
protoOf(MoveRight).toString = function () {
  return 'MoveRight';
};
protoOf(MoveRight).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof MoveRight))
    return false;
  other instanceof MoveRight || THROW_CCE();
  return true;
};
var MoveRight_instance;
function MoveRight_getInstance() {
  if (MoveRight_instance == null)
    new MoveRight();
  return MoveRight_instance;
}
function StartWhile() {
}
protoOf(StartWhile).toString = function () {
  return 'StartWhile';
};
protoOf(StartWhile).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof StartWhile))
    return false;
  other instanceof StartWhile || THROW_CCE();
  return true;
};
var StartWhile_instance;
function StartWhile_getInstance() {
  return StartWhile_instance;
}
function EndWhile_0() {
}
protoOf(EndWhile_0).toString = function () {
  return 'EndWhile';
};
protoOf(EndWhile_0).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof EndWhile_0))
    return false;
  other instanceof EndWhile_0 || THROW_CCE();
  return true;
};
var EndWhile_instance_0;
function EndWhile_getInstance_0() {
  return EndWhile_instance_0;
}
function Companion() {
}
protoOf(Companion).a5 = function (ch) {
  return ch === _Char___init__impl__6a9atx(43) ? Plus_getInstance() : ch === _Char___init__impl__6a9atx(45) ? Minus_getInstance() : ch === _Char___init__impl__6a9atx(60) ? MoveLeft_getInstance() : ch === _Char___init__impl__6a9atx(62) ? MoveRight_getInstance() : ch === _Char___init__impl__6a9atx(91) ? StartWhile_instance : ch === _Char___init__impl__6a9atx(93) ? EndWhile_instance_0 : ch === _Char___init__impl__6a9atx(46) ? Write_instance_0 : ch === _Char___init__impl__6a9atx(44) ? Read_instance_0 : null;
};
var Companion_instance;
function Companion_getInstance() {
  return Companion_instance;
}
function TokenInfo(position, length, line, column, file) {
  this.i5_1 = position;
  this.j5_1 = length;
  this.k5_1 = line;
  this.l5_1 = column;
  this.m5_1 = file;
}
protoOf(TokenInfo).toString = function () {
  return 'TokenInfo(position=' + this.i5_1 + ', length=' + this.j5_1 + ', line=' + this.k5_1 + ', column=' + this.l5_1 + ', file=' + this.m5_1 + ')';
};
protoOf(TokenInfo).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof TokenInfo))
    return false;
  var tmp0_other_with_cast = other instanceof TokenInfo ? other : THROW_CCE();
  if (!(this.i5_1 === tmp0_other_with_cast.i5_1))
    return false;
  if (!(this.j5_1 === tmp0_other_with_cast.j5_1))
    return false;
  if (!(this.k5_1 === tmp0_other_with_cast.k5_1))
    return false;
  if (!(this.l5_1 === tmp0_other_with_cast.l5_1))
    return false;
  if (!(this.m5_1 === tmp0_other_with_cast.m5_1))
    return false;
  return true;
};
function LoopStartRunner() {
}
var LoopStartRunner_instance;
function LoopStartRunner_getInstance() {
  return LoopStartRunner_instance;
}
function StepRunner(steps) {
  steps = steps === VOID ? 1 : steps;
  this.n5_1 = steps;
  // Inline function 'kotlin.require' call
  // Inline function 'kotlin.require' call
  if (!(this.n5_1 > 0)) {
    var message = 'Failed requirement.';
    throw IllegalArgumentException_init_$Create$(toString(message));
  }
}
function UntilEnd() {
}
var UntilEnd_instance;
function UntilEnd_getInstance() {
  return UntilEnd_instance;
}
//region block: post-declaration
defineProp(protoOf(Run), 'step', protoOf(Run).d4);
//endregion
//region block: init
Brainfuck_instance = new Brainfuck_0();
Read_instance = new Read();
Write_instance = new Write();
EndWhile_instance = new EndWhile();
Comment_instance = new Comment();
Write_instance_0 = new Write_0();
Read_instance_0 = new Read_0();
StartWhile_instance = new StartWhile();
EndWhile_instance_0 = new EndWhile_0();
Companion_instance = new Companion();
LoopStartRunner_instance = new LoopStartRunner();
UntilEnd_instance = new UntilEnd();
//endregion
//region block: exports
var Brainfuck = {getInstance: Brainfuck_getInstance};
defineProp(Brainfuck_0.prototype, 'Run', Run_getInstance);
export {
  Brainfuck as Brainfuck,
};
//endregion

//# sourceMappingURL=Brainduck-shared.mjs.map
