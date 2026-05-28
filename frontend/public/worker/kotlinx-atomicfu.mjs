import {
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  toString30pk9tzaqopn as toString,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
initMetadataForClass(atomicfu$TraceBase, 'TraceBase');
initMetadataForObject(None, 'None', VOID, atomicfu$TraceBase);
initMetadataForClass(AtomicRef, 'AtomicRef');
initMetadataForClass(AtomicBoolean, 'AtomicBoolean');
initMetadataForClass(AtomicInt, 'AtomicInt');
//endregion
function None() {
  None_instance = this;
  atomicfu$TraceBase.call(this);
}
var None_instance;
function None_getInstance() {
  if (None_instance == null)
    new None();
  return None_instance;
}
function atomicfu$TraceBase() {
}
protoOf(atomicfu$TraceBase).atomicfu$Trace$append$1 = function (event) {
};
protoOf(atomicfu$TraceBase).atomicfu$Trace$append$2 = function (event1, event2) {
};
protoOf(atomicfu$TraceBase).atomicfu$Trace$append$3 = function (event1, event2, event3) {
};
protoOf(atomicfu$TraceBase).atomicfu$Trace$append$4 = function (event1, event2, event3, event4) {
};
function AtomicRef(value) {
  this.kotlinx$atomicfu$value = value;
}
protoOf(AtomicRef).y8 = function (_set____db54di) {
  this.kotlinx$atomicfu$value = _set____db54di;
};
protoOf(AtomicRef).z8 = function () {
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicRef).atomicfu$compareAndSet = function (expect, update) {
  if (!(this.kotlinx$atomicfu$value === expect))
    return false;
  this.kotlinx$atomicfu$value = update;
  return true;
};
protoOf(AtomicRef).atomicfu$getAndSet = function (value) {
  var oldValue = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = value;
  return oldValue;
};
protoOf(AtomicRef).toString = function () {
  return toString(this.kotlinx$atomicfu$value);
};
function atomic$ref$1(initial) {
  return atomic$ref$(initial, None_getInstance());
}
function AtomicBoolean(value) {
  this.kotlinx$atomicfu$value = value;
}
protoOf(AtomicBoolean).a9 = function (_set____db54di) {
  this.kotlinx$atomicfu$value = _set____db54di;
};
protoOf(AtomicBoolean).z8 = function () {
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicBoolean).atomicfu$compareAndSet = function (expect, update) {
  if (!(this.kotlinx$atomicfu$value === expect))
    return false;
  this.kotlinx$atomicfu$value = update;
  return true;
};
protoOf(AtomicBoolean).atomicfu$getAndSet = function (value) {
  var oldValue = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = value;
  return oldValue;
};
protoOf(AtomicBoolean).toString = function () {
  return this.kotlinx$atomicfu$value.toString();
};
function atomic$boolean$1(initial) {
  return atomic$boolean$(initial, None_getInstance());
}
function AtomicInt(value) {
  this.kotlinx$atomicfu$value = value;
}
protoOf(AtomicInt).b9 = function (_set____db54di) {
  this.kotlinx$atomicfu$value = _set____db54di;
};
protoOf(AtomicInt).z8 = function () {
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicInt).atomicfu$compareAndSet = function (expect, update) {
  if (!(this.kotlinx$atomicfu$value === expect))
    return false;
  this.kotlinx$atomicfu$value = update;
  return true;
};
protoOf(AtomicInt).atomicfu$getAndSet = function (value) {
  var oldValue = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = value;
  return oldValue;
};
protoOf(AtomicInt).atomicfu$getAndIncrement = function () {
  var _unary__edvuaz = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = _unary__edvuaz + 1 | 0;
  return _unary__edvuaz;
};
protoOf(AtomicInt).atomicfu$getAndDecrement = function () {
  var _unary__edvuaz = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = _unary__edvuaz - 1 | 0;
  return _unary__edvuaz;
};
protoOf(AtomicInt).atomicfu$getAndAdd = function (delta) {
  var oldValue = this.kotlinx$atomicfu$value;
  this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + delta | 0;
  return oldValue;
};
protoOf(AtomicInt).atomicfu$addAndGet = function (delta) {
  this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + delta | 0;
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicInt).atomicfu$incrementAndGet = function () {
  this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + 1 | 0;
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicInt).atomicfu$decrementAndGet = function () {
  this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value - 1 | 0;
  return this.kotlinx$atomicfu$value;
};
protoOf(AtomicInt).toString = function () {
  return this.kotlinx$atomicfu$value.toString();
};
function atomic$int$1(initial) {
  return atomic$int$(initial, None_getInstance());
}
function atomic$ref$(initial, trace) {
  trace = trace === VOID ? None_getInstance() : trace;
  return new AtomicRef(initial);
}
function atomic$boolean$(initial, trace) {
  trace = trace === VOID ? None_getInstance() : trace;
  return new AtomicBoolean(initial);
}
function atomic$int$(initial, trace) {
  trace = trace === VOID ? None_getInstance() : trace;
  return new AtomicInt(initial);
}
//region block: exports
export {
  atomic$boolean$1 as atomic$boolean$1iggki4z65a2h,
  atomic$ref$1 as atomic$ref$130aurmcwdfdf1,
  atomic$int$1 as atomic$int$11d5swdyn6j0pu,
};
//endregion

//# sourceMappingURL=kotlinx-atomicfu.mjs.map
