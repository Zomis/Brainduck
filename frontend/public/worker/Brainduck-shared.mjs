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
  hashCodeq5arwsb9dgti as hashCode,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  VOID3gxj6tk5isa35 as VOID,
  _Char___init__impl__6a9atx2js6krycynjoo as _Char___init__impl__6a9atx,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  IllegalArgumentException_init_$Create$3ewkh27kzt8z8 as IllegalArgumentException_init_$Create$,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
var imul = Math.imul;
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
protoOf(Run).bj = function () {
  return this.all;
};
protoOf(Run).cj = function () {
  throw new NotImplementedError();
};
protoOf(Run).dj = function () {
  return this.stepSyntax;
};
protoOf(Run).ej = function () {
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
  return (new Lexer()).kj(text);
};
protoOf(Brainfuck_0).code = function (text) {
  return this.tokenize(text).mj();
};
var Brainfuck_instance;
function Brainfuck_getInstance() {
  return Brainfuck_instance;
}
function BrainfuckCode(syntax) {
  this.nj_1 = syntax;
}
function LoopSyntax(startToken) {
  this.oj_1 = startToken;
  var tmp = this;
  // Inline function 'kotlin.collections.mutableListOf' call
  tmp.pj_1 = ArrayList_init_$Create$();
}
function withData($this, _this__u8e3s4, data) {
  return new Syntax(_this__u8e3s4, data);
}
function createRepeatSyntax($this, awaitingTokens, diff) {
  var token = first(awaitingTokens);
  var syntaxInfo = new SyntaxInfo(awaitingTokens);
  var tmp0_subject = token.rj_1;
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
  this.lj_1 = tokens;
}
protoOf(TokenizedProgram).mj = function () {
  // Inline function 'kotlin.collections.mutableListOf' call
  var awaitingTokens = ArrayList_init_$Create$();
  var lastToken = null;
  var diff = 0;
  // Inline function 'kotlin.collections.mutableListOf' call
  var depthStack = ArrayList_init_$Create$();
  depthStack.m(new LoopSyntax(null));
  var syntaxes = single(depthStack).pj_1;
  var _iterator__ex2g4s = this.lj_1.e();
  while (_iterator__ex2g4s.f()) {
    var token = _iterator__ex2g4s.g();
    var last_0 = lastToken;
    var tmp;
    if (!equals(last_0 == null ? null : last_0.rj_1, token.rj_1)) {
      // Inline function 'kotlin.collections.isNotEmpty' call
      tmp = !awaitingTokens.n();
    } else {
      tmp = false;
    }
    if (tmp) {
      syntaxes.m(createRepeatSyntax(this, toList(awaitingTokens), diff));
      awaitingTokens.d2();
      diff = 0;
    }
    var syntaxInfo = new SyntaxInfo(listOf(token));
    var tmp1_subject = token.rj_1;
    if (tmp1_subject instanceof Advanced) {
      // Inline function 'kotlin.TODO' call
      throw new NotImplementedError();
    } else {
      if (tmp1_subject instanceof Comment_0)
        syntaxes.m(withData(this, syntaxInfo, Comment_instance));
      else {
        if (equals(tmp1_subject, StartWhile_instance)) {
          depthStack.m(new LoopSyntax(token));
          syntaxes = last(depthStack).pj_1;
        } else {
          if (equals(tmp1_subject, EndWhile_instance_0)) {
            var popped = removeLast(depthStack);
            syntaxes = last(depthStack).pj_1;
            var tmp_0 = listOf(popped.oj_1);
            // Inline function 'kotlin.collections.flatMap' call
            var tmp0 = popped.pj_1;
            // Inline function 'kotlin.collections.flatMapTo' call
            var destination = ArrayList_init_$Create$();
            var _iterator__ex2g4s_0 = tmp0.e();
            while (_iterator__ex2g4s_0.f()) {
              var element = _iterator__ex2g4s_0.g();
              var list = element.uj_1.tj_1;
              addAll(destination, list);
            }
            var tokens = plus_0(plus(tmp_0, destination), token);
            var endWhile = new Syntax(syntaxInfo, EndWhile_instance);
            syntaxes.m(new Syntax(new SyntaxInfo(filterNotNull(tokens)), new WhileNotZero(plus_0(popped.pj_1, endWhile))));
          } else {
            if (equals(tmp1_subject, Read_instance_0))
              syntaxes.m(new Syntax(syntaxInfo, Read_instance));
            else {
              if (equals(tmp1_subject, Write_instance_0))
                syntaxes.m(new Syntax(syntaxInfo, Write_instance));
              else {
                if (tmp1_subject instanceof Repeatable) {
                  awaitingTokens.m(token);
                  diff = diff + token.rj_1.sj_1 | 0;
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
  this.xj_1 = $outer;
  this.wj_1 = StringBuilder_init_$Create$();
}
protoOf(CommentStrategy).yj = function (ch) {
  var token = Companion_instance.zj(ch);
  var tmp;
  if (!(token == null) ? isInterface(token, Instruction) : false) {
    this.xj_1.fj_1.m(nextTokenInfo(this.xj_1, new Comment_0(this.wj_1.toString())));
    tmp = (new DefaultStrategy(this.xj_1)).yj(ch);
  } else {
    this.wj_1.e4(this.wj_1.toString());
    tmp = this;
  }
  return tmp;
};
protoOf(CommentStrategy).bk = function () {
};
function DefaultStrategy($outer) {
  this.ak_1 = $outer;
}
protoOf(DefaultStrategy).yj = function (ch) {
  var bfCommand = Companion_instance.zj(ch);
  var tmp;
  if (!(bfCommand == null)) {
    this.ak_1.fj_1.m(nextTokenInfo(this.ak_1, bfCommand));
    tmp = this;
  } else {
    tmp = (new CommentStrategy(this.ak_1)).yj(ch);
  }
  return tmp;
};
protoOf(DefaultStrategy).bk = function () {
};
function nextTokenInfo($this, data) {
  var tokenLength = 1;
  var info = new TokenInfo($this.gj_1, tokenLength, $this.hj_1, $this.ij_1, $this.jj_1);
  $this.gj_1 = $this.gj_1 + tokenLength | 0;
  $this.ij_1 = $this.ij_1 + 1 | 0;
  return new Token(info, data);
}
function Lexer() {
  var tmp = this;
  // Inline function 'kotlin.collections.mutableListOf' call
  tmp.fj_1 = ArrayList_init_$Create$();
  this.gj_1 = 1;
  this.hj_1 = 1;
  this.ij_1 = 1;
  this.jj_1 = '.';
}
protoOf(Lexer).kj = function (text) {
  var strategy = new DefaultStrategy(this);
  var iterator = lines(text).e();
  var index = 0;
  while (iterator.f()) {
    var lineIndex = index;
    index = index + 1 | 0;
    var lineText = iterator.g();
    this.hj_1 = lineIndex + 1 | 0;
    this.ij_1 = 1;
    var inductionVariable = 0;
    var last = lineText.length;
    while (inductionVariable < last) {
      var c = charCodeAt(lineText, inductionVariable);
      inductionVariable = inductionVariable + 1 | 0;
      strategy = strategy.yj(c);
    }
  }
  strategy.bk();
  return new TokenizedProgram(toList(this.fj_1));
};
function Read() {
}
protoOf(Read).toString = function () {
  return 'Read';
};
protoOf(Read).hashCode = function () {
  return 388409295;
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
protoOf(Write).hashCode = function () {
  return -839200570;
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
  this.ck_1 = delta;
}
protoOf(Move).toString = function () {
  return 'Move(delta=' + this.ck_1 + ')';
};
protoOf(Move).hashCode = function () {
  return this.ck_1;
};
protoOf(Move).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Move))
    return false;
  var tmp0_other_with_cast = other instanceof Move ? other : THROW_CCE();
  if (!(this.ck_1 === tmp0_other_with_cast.ck_1))
    return false;
  return true;
};
function ChangeValue(delta) {
  this.dk_1 = delta;
}
protoOf(ChangeValue).toString = function () {
  return 'ChangeValue(delta=' + this.dk_1 + ')';
};
protoOf(ChangeValue).hashCode = function () {
  return this.dk_1;
};
protoOf(ChangeValue).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof ChangeValue))
    return false;
  var tmp0_other_with_cast = other instanceof ChangeValue ? other : THROW_CCE();
  if (!(this.dk_1 === tmp0_other_with_cast.dk_1))
    return false;
  return true;
};
function WhileNotZero(children) {
  this.ek_1 = children;
}
protoOf(WhileNotZero).toString = function () {
  return 'WhileNotZero(children=' + toString(this.ek_1) + ')';
};
protoOf(WhileNotZero).hashCode = function () {
  return hashCode(this.ek_1);
};
protoOf(WhileNotZero).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof WhileNotZero))
    return false;
  var tmp0_other_with_cast = other instanceof WhileNotZero ? other : THROW_CCE();
  if (!equals(this.ek_1, tmp0_other_with_cast.ek_1))
    return false;
  return true;
};
function EndWhile() {
}
protoOf(EndWhile).toString = function () {
  return 'EndWhile';
};
protoOf(EndWhile).hashCode = function () {
  return -350147377;
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
protoOf(Comment).hashCode = function () {
  return 329501062;
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
  this.fk_1 = children;
}
protoOf(Root).toString = function () {
  return 'Root(children=' + toString(this.fk_1) + ')';
};
protoOf(Root).hashCode = function () {
  return hashCode(this.fk_1);
};
protoOf(Root).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Root))
    return false;
  var tmp0_other_with_cast = other instanceof Root ? other : THROW_CCE();
  if (!equals(this.fk_1, tmp0_other_with_cast.fk_1))
    return false;
  return true;
};
function Syntax(info, data) {
  this.uj_1 = info;
  this.vj_1 = data;
}
protoOf(Syntax).toString = function () {
  return 'Syntax(info=' + this.uj_1.toString() + ', data=' + toString(this.vj_1) + ')';
};
protoOf(Syntax).hashCode = function () {
  var result = this.uj_1.hashCode();
  result = imul(result, 31) + hashCode(this.vj_1) | 0;
  return result;
};
protoOf(Syntax).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Syntax))
    return false;
  var tmp0_other_with_cast = other instanceof Syntax ? other : THROW_CCE();
  if (!this.uj_1.equals(tmp0_other_with_cast.uj_1))
    return false;
  if (!equals(this.vj_1, tmp0_other_with_cast.vj_1))
    return false;
  return true;
};
function SyntaxInfo(tokens) {
  this.tj_1 = tokens;
}
protoOf(SyntaxInfo).toString = function () {
  return 'SyntaxInfo(tokens=' + toString(this.tj_1) + ')';
};
protoOf(SyntaxInfo).hashCode = function () {
  return hashCode(this.tj_1);
};
protoOf(SyntaxInfo).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof SyntaxInfo))
    return false;
  var tmp0_other_with_cast = other instanceof SyntaxInfo ? other : THROW_CCE();
  if (!equals(this.tj_1, tmp0_other_with_cast.tj_1))
    return false;
  return true;
};
function Token(info, data) {
  this.qj_1 = info;
  this.rj_1 = data;
}
protoOf(Token).toString = function () {
  return 'Token(info=' + this.qj_1.toString() + ', data=' + toString(this.rj_1) + ')';
};
protoOf(Token).hashCode = function () {
  var result = this.qj_1.hashCode();
  result = imul(result, 31) + hashCode(this.rj_1) | 0;
  return result;
};
protoOf(Token).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Token))
    return false;
  var tmp0_other_with_cast = other instanceof Token ? other : THROW_CCE();
  if (!this.qj_1.equals(tmp0_other_with_cast.qj_1))
    return false;
  if (!equals(this.rj_1, tmp0_other_with_cast.rj_1))
    return false;
  return true;
};
function Instruction() {
}
function Repeatable(count) {
  this.sj_1 = count;
}
function Advanced() {
}
function Comment_0(text) {
  this.gk_1 = text;
}
protoOf(Comment_0).toString = function () {
  return 'Comment(text=' + this.gk_1 + ')';
};
protoOf(Comment_0).hashCode = function () {
  return getStringHashCode(this.gk_1);
};
protoOf(Comment_0).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof Comment_0))
    return false;
  var tmp0_other_with_cast = other instanceof Comment_0 ? other : THROW_CCE();
  if (!(this.gk_1 === tmp0_other_with_cast.gk_1))
    return false;
  return true;
};
function Write_0() {
}
protoOf(Write_0).toString = function () {
  return 'Write';
};
protoOf(Write_0).hashCode = function () {
  return -1883094452;
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
protoOf(Read_0).hashCode = function () {
  return -1584927351;
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
protoOf(Plus).hashCode = function () {
  return -1584979571;
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
protoOf(Minus).hashCode = function () {
  return -1892592931;
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
protoOf(MoveLeft).hashCode = function () {
  return -957062677;
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
protoOf(MoveRight).hashCode = function () {
  return 401489080;
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
protoOf(StartWhile).hashCode = function () {
  return -400026206;
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
protoOf(EndWhile_0).hashCode = function () {
  return 865404297;
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
protoOf(Companion).zj = function (ch) {
  return ch === _Char___init__impl__6a9atx(43) ? Plus_getInstance() : ch === _Char___init__impl__6a9atx(45) ? Minus_getInstance() : ch === _Char___init__impl__6a9atx(60) ? MoveLeft_getInstance() : ch === _Char___init__impl__6a9atx(62) ? MoveRight_getInstance() : ch === _Char___init__impl__6a9atx(91) ? StartWhile_instance : ch === _Char___init__impl__6a9atx(93) ? EndWhile_instance_0 : ch === _Char___init__impl__6a9atx(46) ? Write_instance_0 : ch === _Char___init__impl__6a9atx(44) ? Read_instance_0 : null;
};
var Companion_instance;
function Companion_getInstance() {
  return Companion_instance;
}
function TokenInfo(position, length, line, column, file) {
  this.hk_1 = position;
  this.ik_1 = length;
  this.jk_1 = line;
  this.kk_1 = column;
  this.lk_1 = file;
}
protoOf(TokenInfo).toString = function () {
  return 'TokenInfo(position=' + this.hk_1 + ', length=' + this.ik_1 + ', line=' + this.jk_1 + ', column=' + this.kk_1 + ', file=' + this.lk_1 + ')';
};
protoOf(TokenInfo).hashCode = function () {
  var result = this.hk_1;
  result = imul(result, 31) + this.ik_1 | 0;
  result = imul(result, 31) + this.jk_1 | 0;
  result = imul(result, 31) + this.kk_1 | 0;
  result = imul(result, 31) + getStringHashCode(this.lk_1) | 0;
  return result;
};
protoOf(TokenInfo).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof TokenInfo))
    return false;
  var tmp0_other_with_cast = other instanceof TokenInfo ? other : THROW_CCE();
  if (!(this.hk_1 === tmp0_other_with_cast.hk_1))
    return false;
  if (!(this.ik_1 === tmp0_other_with_cast.ik_1))
    return false;
  if (!(this.jk_1 === tmp0_other_with_cast.jk_1))
    return false;
  if (!(this.kk_1 === tmp0_other_with_cast.kk_1))
    return false;
  if (!(this.lk_1 === tmp0_other_with_cast.lk_1))
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
  this.mk_1 = steps;
  // Inline function 'kotlin.require' call
  // Inline function 'kotlin.require' call
  if (!(this.mk_1 > 0)) {
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
defineProp(protoOf(Run), 'step', protoOf(Run).cj);
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
