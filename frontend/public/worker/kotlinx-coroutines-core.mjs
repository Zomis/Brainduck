import {
  Unit_instance1fbcbse1fwigr as Unit_instance,
  protoOf180f3jzyo7rfj as protoOf,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  Element2gr7ezmxqaln7 as Element,
  Continuation1aa2oekvx7jm7 as Continuation,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  VOID3gxj6tk5isa35 as VOID,
  EmptyCoroutineContext_getInstance31fow51ayy30t as EmptyCoroutineContext_getInstance,
  createCoroutineUnintercepted3gya308dmbbtg as createCoroutineUnintercepted,
  UnsupportedOperationException_init_$Create$1pe732c4s59hc as UnsupportedOperationException_init_$Create$,
  isInterface3d6p8outrmvmk as isInterface,
  toString1pkumu07cwy4m as toString,
  IllegalStateException_init_$Create$2w9444nebyjns as IllegalStateException_init_$Create$,
  toString30pk9tzaqopn as toString_0,
  get_COROUTINE_SUSPENDED3ujt3p13qm4iy as get_COROUTINE_SUSPENDED,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  hashCodeq5arwsb9dgti as hashCode,
  equals2au1ep9vhcato as equals,
  CancellationException_init_$Create$2cv5nayrc39hr as CancellationException_init_$Create$,
  Result__exceptionOrNull_impl_p6xea9ty3elzpd9eo3 as Result__exceptionOrNull_impl_p6xea9,
  _Result___get_value__impl__bjfvqg2ei4op8d4d2m as _Result___get_value__impl__bjfvqg,
  AbstractCoroutineContextKey9xr9r6wlj5bm as AbstractCoroutineContextKey,
  Key_instance17k9ki7fvysxq as Key_instance,
  AbstractCoroutineContextElement2rpehg0hv5szw as AbstractCoroutineContextElement,
  getxe4seun860fg as get,
  minusKey2uxs00uz5ceqp as minusKey,
  ContinuationInterceptor2624y0vaqwxwf as ContinuationInterceptor,
  RuntimeException_init_$Create$518iyh59wo54 as RuntimeException_init_$Create$,
  addSuppressedu5jwjfvsc039 as addSuppressed,
  Enum3alwj03lh1n41 as Enum,
  startCoroutine327fwvtqvedik as startCoroutine,
  noWhenBranchMatchedException2a6r7ubxgky5j as noWhenBranchMatchedException,
  Long2qws0ah9gnpki as Long,
  intercepted2ogpsikxxj4u0 as intercepted,
  ArrayDeque_init_$Create$2333dl090ltjt as ArrayDeque_init_$Create$,
  RuntimeException1r3t0zl97011n as RuntimeException,
  RuntimeException_init_$Init$3m7ccek2krm3f as RuntimeException_init_$Init$,
  captureStack1fzi4aczwc4hg as captureStack,
  Error3ofk6owajcepa as Error_0,
  Error_init_$Init$17pe7jtgoh3ll as Error_init_$Init$,
  StringBuilder_init_$Create$2qsge4ydj6bin as StringBuilder_init_$Create$,
  throwUninitializedPropertyAccessExceptionyynx7gkm73wd as throwUninitializedPropertyAccessException,
  ArrayList_init_$Create$3bxttkj3v1mea as ArrayList_init_$Create$,
  CancellationException3b36o9qz53rgr as CancellationException,
  ArrayList3it5z8td81qkl as ArrayList,
  IllegalStateException_init_$Create$12oloagvd20rx as IllegalStateException_init_$Create$_0,
  plusolev77jfy5r9 as plus,
  get6d5x931vk0s as get_0,
  fold36i9psb7d5v48 as fold,
  minusKeyyqanvso9aovh as minusKey_0,
  anyToString3ho3k49fc56mj as anyToString,
  UnsupportedOperationException2tkumpmhredt3 as UnsupportedOperationException,
  Exceptiondt2hlxn7j7vw as Exception,
  IllegalArgumentException_init_$Create$3ewkh27kzt8z8 as IllegalArgumentException_init_$Create$,
  Companion_instance2oawqq9qiaris as Companion_instance,
  createFailure8paxfkfa5dc7 as createFailure,
  _Result___init__impl__xyqfz83hut4nr3dfvi3 as _Result___init__impl__xyqfz8,
  startCoroutineUninterceptedOrReturnNonGeneratorVersionyfrrvzbtl8bf as startCoroutineUninterceptedOrReturnNonGeneratorVersion,
  getKClassFromExpression3vpejubogshaw as getKClassFromExpression,
  removeFirstOrNull15yg2tczrh8a7 as removeFirstOrNull,
  Collection1k04j3hzsbod0 as Collection,
  KtList3hktaavzmj137 as KtList,
  coerceIn302bduskdb54x as coerceIn,
  CancellationException_init_$Init$1ieejj57c468h as CancellationException_init_$Init$,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  HashSet_init_$Create$1almu45bw06ne as HashSet_init_$Create$,
  RuntimeException_init_$Init$1tdhpyy2sm4eb as RuntimeException_init_$Init$_0,
  LinkedHashSet_init_$Create$3o6z3oewjhki9 as LinkedHashSet_init_$Create$,
} from './kotlin-kotlin-stdlib.mjs';
import {
  atomic$int$11d5swdyn6j0pu as atomic$int$1,
  atomic$ref$130aurmcwdfdf1 as atomic$ref$1,
  atomic$boolean$1iggki4z65a2h as atomic$boolean$1,
} from './kotlinx-atomicfu.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
initMetadataForInterface(ParentJob, 'ParentJob', VOID, VOID, [Element], [0]);
initMetadataForClass(JobSupport, 'JobSupport', VOID, VOID, [Element, ParentJob], [0]);
initMetadataForInterface(CoroutineScope, 'CoroutineScope');
initMetadataForClass(AbstractCoroutine, 'AbstractCoroutine', VOID, JobSupport, [JobSupport, Element, Continuation, CoroutineScope], [0]);
initMetadataForClass(StandaloneCoroutine, 'StandaloneCoroutine', VOID, AbstractCoroutine, VOID, [0]);
initMetadataForClass(LazyStandaloneCoroutine, 'LazyStandaloneCoroutine', VOID, StandaloneCoroutine, VOID, [0]);
initMetadataForInterface(Runnable, 'Runnable');
initMetadataForClass(SchedulerTask, 'SchedulerTask', VOID, VOID, [Runnable]);
initMetadataForClass(DispatchedTask, 'DispatchedTask', VOID, SchedulerTask);
initMetadataForClass(CancellableContinuationImpl, 'CancellableContinuationImpl', VOID, DispatchedTask, [DispatchedTask, Continuation]);
initMetadataForInterface(NotCompleted, 'NotCompleted');
initMetadataForInterface(CancelHandler, 'CancelHandler', VOID, VOID, [NotCompleted]);
initMetadataForObject(Active, 'Active', VOID, VOID, [NotCompleted]);
initMetadataForClass(CompletedContinuation, 'CompletedContinuation');
initMetadataForClass(LockFreeLinkedListNode, 'LockFreeLinkedListNode', LockFreeLinkedListNode);
initMetadataForInterface(Incomplete, 'Incomplete');
initMetadataForClass(JobNode, 'JobNode', VOID, LockFreeLinkedListNode, [LockFreeLinkedListNode, Incomplete]);
initMetadataForClass(ChildContinuation, 'ChildContinuation', VOID, JobNode);
initMetadataForClass(CompletedExceptionally, 'CompletedExceptionally');
initMetadataForClass(CancelledContinuation, 'CancelledContinuation', VOID, CompletedExceptionally);
initMetadataForObject(Key, 'Key', VOID, AbstractCoroutineContextKey);
initMetadataForClass(CoroutineDispatcher, 'CoroutineDispatcher', VOID, AbstractCoroutineContextElement, [AbstractCoroutineContextElement, ContinuationInterceptor]);
initMetadataForObject(Key_0, 'Key');
initMetadataForClass(CoroutineStart, 'CoroutineStart', VOID, Enum);
initMetadataForInterface(Delay, 'Delay', VOID, VOID, VOID, [1]);
initMetadataForClass(EventLoop, 'EventLoop', VOID, CoroutineDispatcher);
initMetadataForObject(ThreadLocalEventLoop, 'ThreadLocalEventLoop');
initMetadataForClass(CompletionHandlerException, 'CompletionHandlerException', VOID, RuntimeException);
initMetadataForClass(CoroutinesInternalError, 'CoroutinesInternalError', VOID, Error_0);
initMetadataForObject(Key_1, 'Key');
initMetadataForObject(NonDisposableHandle, 'NonDisposableHandle');
initMetadataForClass(Empty, 'Empty', VOID, VOID, [Incomplete]);
initMetadataForClass(LockFreeLinkedListHead, 'LockFreeLinkedListHead', LockFreeLinkedListHead, LockFreeLinkedListNode);
initMetadataForClass(NodeList, 'NodeList', NodeList, LockFreeLinkedListHead, [LockFreeLinkedListHead, Incomplete]);
initMetadataForClass(SynchronizedObject, 'SynchronizedObject', SynchronizedObject);
initMetadataForClass(Finishing, 'Finishing', VOID, SynchronizedObject, [SynchronizedObject, Incomplete]);
initMetadataForClass(ChildCompletion, 'ChildCompletion', VOID, JobNode);
initMetadataForClass(InactiveNodeList, 'InactiveNodeList', VOID, VOID, [Incomplete]);
initMetadataForClass(InvokeOnCompletion, 'InvokeOnCompletion', VOID, JobNode);
initMetadataForClass(InvokeOnCancelling, 'InvokeOnCancelling', VOID, JobNode);
initMetadataForClass(ChildHandleNode, 'ChildHandleNode', VOID, JobNode);
initMetadataForClass(IncompleteStateBox, 'IncompleteStateBox');
initMetadataForClass(JobImpl, 'JobImpl', VOID, JobSupport, [JobSupport, Element], [0]);
initMetadataForClass(MainCoroutineDispatcher, 'MainCoroutineDispatcher', VOID, CoroutineDispatcher);
initMetadataForClass(SupervisorJobImpl, 'SupervisorJobImpl', VOID, JobImpl, VOID, [0]);
initMetadataForClass(TimeoutCancellationException, 'TimeoutCancellationException', VOID, CancellationException);
initMetadataForObject(Unconfined, 'Unconfined', VOID, CoroutineDispatcher);
initMetadataForObject(Key_2, 'Key');
initMetadataForClass(ConcurrentLinkedListNode, 'ConcurrentLinkedListNode');
initMetadataForClass(Segment, 'Segment', VOID, ConcurrentLinkedListNode, [ConcurrentLinkedListNode, NotCompleted]);
initMetadataForObject(ExceptionSuccessfullyProcessed, 'ExceptionSuccessfullyProcessed', VOID, Exception);
initMetadataForClass(DispatchedContinuation, 'DispatchedContinuation', VOID, DispatchedTask, [DispatchedTask, Continuation]);
initMetadataForClass(ContextScope, 'ContextScope', VOID, VOID, [CoroutineScope]);
initMetadataForClass(Symbol, 'Symbol');
initMetadataForClass(SetTimeoutBasedDispatcher, 'SetTimeoutBasedDispatcher', VOID, CoroutineDispatcher, [CoroutineDispatcher, Delay], [1]);
initMetadataForObject(NodeDispatcher, 'NodeDispatcher', VOID, SetTimeoutBasedDispatcher, VOID, [1]);
initMetadataForClass(MessageQueue, 'MessageQueue', VOID, VOID, [Collection, KtList]);
initMetadataForClass(ScheduledMessageQueue, 'ScheduledMessageQueue', VOID, MessageQueue);
initMetadataForClass(WindowMessageQueue, 'WindowMessageQueue', VOID, MessageQueue);
initMetadataForClass(UnconfinedEventLoop, 'UnconfinedEventLoop', UnconfinedEventLoop, EventLoop);
initMetadataForObject(SetTimeoutDispatcher, 'SetTimeoutDispatcher', VOID, SetTimeoutBasedDispatcher, VOID, [1]);
initMetadataForClass(ClearTimeout, 'ClearTimeout', VOID, VOID, [CancelHandler]);
initMetadataForClass(WindowClearTimeout, 'WindowClearTimeout', VOID, ClearTimeout);
initMetadataForClass(WindowDispatcher, 'WindowDispatcher', VOID, CoroutineDispatcher, [CoroutineDispatcher, Delay], [1]);
initMetadataForObject(Dispatchers, 'Dispatchers');
initMetadataForClass(JsMainDispatcher, 'JsMainDispatcher', VOID, MainCoroutineDispatcher);
initMetadataForClass(JobCancellationException, 'JobCancellationException', VOID, CancellationException);
initMetadataForClass(DiagnosticCoroutineContextException, 'DiagnosticCoroutineContextException', VOID, RuntimeException);
initMetadataForClass(ListClosed, 'ListClosed', VOID, LockFreeLinkedListNode);
initMetadataForClass(CommonThreadLocal, 'CommonThreadLocal', CommonThreadLocal);
//endregion
function AbstractCoroutine(parentContext, initParentJob, active) {
  JobSupport.call(this, active);
  if (initParentJob) {
    this.e9(parentContext.z4(Key_instance_2));
  }
  this.h9_1 = parentContext.y7(this);
}
protoOf(AbstractCoroutine).s4 = function () {
  return this.h9_1;
};
protoOf(AbstractCoroutine).i9 = function () {
  return this.h9_1;
};
protoOf(AbstractCoroutine).j9 = function () {
  return protoOf(JobSupport).j9.call(this);
};
protoOf(AbstractCoroutine).k9 = function (value) {
};
protoOf(AbstractCoroutine).l9 = function (cause, handled) {
};
protoOf(AbstractCoroutine).m9 = function () {
  return get_classSimpleName(this) + ' was cancelled';
};
protoOf(AbstractCoroutine).n9 = function (state) {
  if (state instanceof CompletedExceptionally) {
    this.l9(state.o9_1, state.q9());
  } else {
    this.k9((state == null ? true : !(state == null)) ? state : THROW_CCE());
  }
};
protoOf(AbstractCoroutine).x4 = function (result) {
  var state = this.r9(toState_0(result));
  if (state === get_COMPLETING_WAITING_CHILDREN())
    return Unit_instance;
  this.s9(state);
};
protoOf(AbstractCoroutine).s9 = function (state) {
  return this.t9(state);
};
protoOf(AbstractCoroutine).u9 = function (exception) {
  handleCoroutineException(this.h9_1, exception);
};
protoOf(AbstractCoroutine).v9 = function () {
  var tmp0_elvis_lhs = get_coroutineName(this.h9_1);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return protoOf(JobSupport).v9.call(this);
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var coroutineName = tmp;
  return '"' + coroutineName + '":' + protoOf(JobSupport).v9.call(this);
};
protoOf(AbstractCoroutine).w9 = function (start, receiver, block) {
  start.z9(block, receiver, this);
};
function launch(_this__u8e3s4, context, start, block) {
  context = context === VOID ? EmptyCoroutineContext_getInstance() : context;
  start = start === VOID ? CoroutineStart_DEFAULT_getInstance() : start;
  var newContext = newCoroutineContext(_this__u8e3s4, context);
  var coroutine = start.xa() ? new LazyStandaloneCoroutine(newContext, block) : new StandaloneCoroutine(newContext, true);
  coroutine.w9(start, coroutine, block);
  return coroutine;
}
function StandaloneCoroutine(parentContext, active) {
  AbstractCoroutine.call(this, parentContext, true, active);
}
protoOf(StandaloneCoroutine).va = function (exception) {
  handleCoroutineException(this.h9_1, exception);
  return true;
};
function LazyStandaloneCoroutine(parentContext, block) {
  StandaloneCoroutine.call(this, parentContext, false);
  this.eb_1 = createCoroutineUnintercepted(block, this, this);
}
protoOf(LazyStandaloneCoroutine).fa = function () {
  startCoroutineCancellable(this.eb_1, this);
};
function invokeOnCancellation(_this__u8e3s4, handler) {
  var tmp;
  if (_this__u8e3s4 instanceof CancellableContinuationImpl) {
    _this__u8e3s4.lb(handler);
    tmp = Unit_instance;
  } else {
    throw UnsupportedOperationException_init_$Create$('third-party implementation of CancellableContinuation is not supported');
  }
  return tmp;
}
function _get_parentHandle__f8dcex($this) {
  return $this.kb_1.kotlinx$atomicfu$value;
}
function _get_stateDebugRepresentation__bf18u4($this) {
  var tmp5_subject = $this.ca();
  var tmp;
  if (!(tmp5_subject == null) ? isInterface(tmp5_subject, NotCompleted) : false) {
    tmp = 'Active';
  } else {
    if (tmp5_subject instanceof CancelledContinuation) {
      tmp = 'Cancelled';
    } else {
      tmp = 'Completed';
    }
  }
  return tmp;
}
function isReusable($this) {
  var tmp;
  if (get_isReusableMode($this.tb_1)) {
    var tmp_0 = $this.gb_1;
    tmp = (tmp_0 instanceof DispatchedContinuation ? tmp_0 : THROW_CCE()).sb();
  } else {
    tmp = false;
  }
  return tmp;
}
function cancelLater($this, cause) {
  if (!isReusable($this))
    return false;
  var tmp = $this.gb_1;
  var dispatched = tmp instanceof DispatchedContinuation ? tmp : THROW_CCE();
  return dispatched.ub(cause);
}
function callSegmentOnCancellation($this, segment, cause) {
  // Inline function 'kotlinx.coroutines.index' call
  var index = $this.ib_1.kotlinx$atomicfu$value & 536870911;
  // Inline function 'kotlin.check' call
  if (!!(index === 536870911)) {
    var message = 'The index for Segment.onCancellation(..) is broken';
    throw IllegalStateException_init_$Create$(toString(message));
  }
  // Inline function 'kotlinx.coroutines.CancellableContinuationImpl.callCancelHandlerSafely' call
  try {
    segment.vb(index, cause, $this.s4());
  } catch ($p) {
    if ($p instanceof Error) {
      var ex = $p;
      handleCoroutineException($this.s4(), new CompletionHandlerException('Exception in invokeOnCancellation handler for ' + $this.toString(), ex));
    } else {
      throw $p;
    }
  }
}
function trySuspend($this) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = $this.ib_1;
  while (true) {
    var cur = this_0.kotlinx$atomicfu$value;
    // Inline function 'kotlinx.coroutines.decision' call
    switch (cur >> 29) {
      case 0:
        // Inline function 'kotlinx.coroutines.index' call

        // Inline function 'kotlinx.coroutines.decisionAndIndex' call

        var tmp$ret$2 = (1 << 29) + (cur & 536870911) | 0;
        if ($this.ib_1.atomicfu$compareAndSet(cur, tmp$ret$2))
          return true;
        break;
      case 2:
        return false;
      default:
        // Inline function 'kotlin.error' call

        var message = 'Already suspended';
        throw IllegalStateException_init_$Create$(toString(message));
    }
  }
}
function tryResume($this) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = $this.ib_1;
  while (true) {
    var cur = this_0.kotlinx$atomicfu$value;
    // Inline function 'kotlinx.coroutines.decision' call
    switch (cur >> 29) {
      case 0:
        // Inline function 'kotlinx.coroutines.index' call

        // Inline function 'kotlinx.coroutines.decisionAndIndex' call

        var tmp$ret$2 = (2 << 29) + (cur & 536870911) | 0;
        if ($this.ib_1.atomicfu$compareAndSet(cur, tmp$ret$2))
          return true;
        break;
      case 1:
        return false;
      default:
        // Inline function 'kotlin.error' call

        var message = 'Already resumed';
        throw IllegalStateException_init_$Create$(toString(message));
    }
  }
}
function installParentHandle($this) {
  var tmp0_elvis_lhs = $this.s4().z4(Key_instance_2);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return null;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var parent = tmp;
  var handle = invokeOnCompletion(parent, VOID, new ChildContinuation($this));
  $this.kb_1.atomicfu$compareAndSet(null, handle);
  return handle;
}
function invokeOnCancellationImpl($this, handler) {
  // Inline function 'kotlinx.coroutines.assert' call
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = $this.jb_1;
  while (true) {
    var state = this_0.kotlinx$atomicfu$value;
    if (state instanceof Active) {
      if ($this.jb_1.atomicfu$compareAndSet(state, handler))
        return Unit_instance;
    } else {
      var tmp;
      if (!(state == null) ? isInterface(state, CancelHandler) : false) {
        tmp = true;
      } else {
        tmp = state instanceof Segment;
      }
      if (tmp) {
        multipleHandlersError($this, handler, state);
      } else {
        if (state instanceof CompletedExceptionally) {
          if (!state.ec()) {
            multipleHandlersError($this, handler, state);
          }
          if (state instanceof CancelledContinuation) {
            var tmp13_safe_receiver = state instanceof CompletedExceptionally ? state : null;
            var cause = tmp13_safe_receiver == null ? null : tmp13_safe_receiver.o9_1;
            if (isInterface(handler, CancelHandler)) {
              $this.bc(handler, cause);
            } else {
              var segment = handler instanceof Segment ? handler : THROW_CCE();
              callSegmentOnCancellation($this, segment, cause);
            }
          }
          return Unit_instance;
        } else {
          if (state instanceof CompletedContinuation) {
            if (!(state.xb_1 == null)) {
              multipleHandlersError($this, handler, state);
            }
            if (handler instanceof Segment)
              return Unit_instance;
            if (!isInterface(handler, CancelHandler))
              THROW_CCE();
            if (state.cc()) {
              $this.bc(handler, state.ac_1);
              return Unit_instance;
            }
            var update = state.dc(VOID, handler);
            if ($this.jb_1.atomicfu$compareAndSet(state, update))
              return Unit_instance;
          } else {
            if (handler instanceof Segment)
              return Unit_instance;
            if (!isInterface(handler, CancelHandler))
              THROW_CCE();
            var update_0 = new CompletedContinuation(state, handler);
            if ($this.jb_1.atomicfu$compareAndSet(state, update_0))
              return Unit_instance;
          }
        }
      }
    }
  }
}
function multipleHandlersError($this, handler, state) {
  // Inline function 'kotlin.error' call
  var message = "It's prohibited to register multiple handlers, tried to register " + toString(handler) + ', already has ' + toString_0(state);
  throw IllegalStateException_init_$Create$(toString(message));
}
function dispatchResume($this, mode) {
  if (tryResume($this))
    return Unit_instance;
  dispatch($this, mode);
}
function resumedState($this, state, proposedUpdate, resumeMode, onCancellation, idempotent) {
  var tmp;
  if (proposedUpdate instanceof CompletedExceptionally) {
    // Inline function 'kotlinx.coroutines.assert' call
    // Inline function 'kotlinx.coroutines.assert' call
    tmp = proposedUpdate;
  } else {
    if (!get_isCancellableMode(resumeMode) && idempotent == null) {
      tmp = proposedUpdate;
    } else {
      var tmp_0;
      var tmp_1;
      if (!(onCancellation == null)) {
        tmp_1 = true;
      } else {
        tmp_1 = isInterface(state, CancelHandler);
      }
      if (tmp_1) {
        tmp_0 = true;
      } else {
        tmp_0 = !(idempotent == null);
      }
      if (tmp_0) {
        tmp = new CompletedContinuation(proposedUpdate, isInterface(state, CancelHandler) ? state : null, onCancellation, idempotent);
      } else {
        tmp = proposedUpdate;
      }
    }
  }
  return tmp;
}
function alreadyResumedError($this, proposedUpdate) {
  // Inline function 'kotlin.error' call
  var message = 'Already resumed, but proposed with update ' + toString_0(proposedUpdate);
  throw IllegalStateException_init_$Create$(toString(message));
}
function detachChildIfNonResuable($this) {
  if (!isReusable($this)) {
    $this.fc();
  }
}
function CancellableContinuationImpl(delegate, resumeMode) {
  DispatchedTask.call(this, resumeMode);
  this.gb_1 = delegate;
  // Inline function 'kotlinx.coroutines.assert' call
  this.hb_1 = this.gb_1.s4();
  var tmp = this;
  // Inline function 'kotlinx.coroutines.decisionAndIndex' call
  var tmp$ret$1 = (0 << 29) + 536870911 | 0;
  tmp.ib_1 = atomic$int$1(tmp$ret$1);
  this.jb_1 = atomic$ref$1(Active_instance);
  this.kb_1 = atomic$ref$1(null);
}
protoOf(CancellableContinuationImpl).gc = function () {
  return this.gb_1;
};
protoOf(CancellableContinuationImpl).s4 = function () {
  return this.hb_1;
};
protoOf(CancellableContinuationImpl).ca = function () {
  return this.jb_1.kotlinx$atomicfu$value;
};
protoOf(CancellableContinuationImpl).da = function () {
  var tmp = this.ca();
  return !(!(tmp == null) ? isInterface(tmp, NotCompleted) : false);
};
protoOf(CancellableContinuationImpl).hc = function () {
  var tmp0_elvis_lhs = installParentHandle(this);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return Unit_instance;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var handle = tmp;
  if (this.da()) {
    handle.ic();
    this.kb_1.kotlinx$atomicfu$value = NonDisposableHandle_instance;
  }
};
protoOf(CancellableContinuationImpl).jc = function () {
  return this.ca();
};
protoOf(CancellableContinuationImpl).kc = function (takenState, cause) {
  var this_0 = this.jb_1;
  while (true) {
    var state = this_0.kotlinx$atomicfu$value;
    if (!(state == null) ? isInterface(state, NotCompleted) : false) {
      // Inline function 'kotlin.error' call
      var message = 'Not completed';
      throw IllegalStateException_init_$Create$(toString(message));
    } else {
      if (state instanceof CompletedExceptionally)
        return Unit_instance;
      else {
        if (state instanceof CompletedContinuation) {
          // Inline function 'kotlin.check' call
          if (!!state.cc()) {
            var message_0 = 'Must be called at most once';
            throw IllegalStateException_init_$Create$(toString(message_0));
          }
          var update = state.dc(VOID, VOID, VOID, VOID, cause);
          if (this.jb_1.atomicfu$compareAndSet(state, update)) {
            state.lc(this, cause);
            return Unit_instance;
          }
        } else {
          if (this.jb_1.atomicfu$compareAndSet(state, new CompletedContinuation(state, VOID, VOID, VOID, cause))) {
            return Unit_instance;
          }
        }
      }
    }
  }
  return Unit_instance;
};
protoOf(CancellableContinuationImpl).mc = function (cause) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = this.jb_1;
  while (true) {
    var tmp0 = this_0.kotlinx$atomicfu$value;
    $l$block: {
      if (!(!(tmp0 == null) ? isInterface(tmp0, NotCompleted) : false))
        return false;
      var tmp;
      if (isInterface(tmp0, CancelHandler)) {
        tmp = true;
      } else {
        tmp = tmp0 instanceof Segment;
      }
      var update = new CancelledContinuation(this, cause, tmp);
      if (!this.jb_1.atomicfu$compareAndSet(tmp0, update)) {
        break $l$block;
      }
      if (isInterface(tmp0, CancelHandler)) {
        this.bc(tmp0, cause);
      } else {
        if (tmp0 instanceof Segment) {
          callSegmentOnCancellation(this, tmp0, cause);
        }
      }
      detachChildIfNonResuable(this);
      dispatchResume(this, this.tb_1);
      return true;
    }
  }
};
protoOf(CancellableContinuationImpl).nc = function (cause) {
  if (cancelLater(this, cause))
    return Unit_instance;
  this.mc(cause);
  detachChildIfNonResuable(this);
};
protoOf(CancellableContinuationImpl).bc = function (handler, cause) {
  // Inline function 'kotlinx.coroutines.CancellableContinuationImpl.callCancelHandlerSafely' call
  try {
    handler.oc(cause);
  } catch ($p) {
    if ($p instanceof Error) {
      var ex = $p;
      handleCoroutineException(this.s4(), new CompletionHandlerException('Exception in invokeOnCancellation handler for ' + this.toString(), ex));
    } else {
      throw $p;
    }
  }
  return Unit_instance;
};
protoOf(CancellableContinuationImpl).pc = function (onCancellation, cause, value) {
  try {
    onCancellation(cause, value, this.s4());
  } catch ($p) {
    if ($p instanceof Error) {
      var ex = $p;
      handleCoroutineException(this.s4(), new CompletionHandlerException('Exception in resume onCancellation handler for ' + this.toString(), ex));
    } else {
      throw $p;
    }
  }
};
protoOf(CancellableContinuationImpl).qc = function (parent) {
  return parent.ga();
};
protoOf(CancellableContinuationImpl).rc = function () {
  var isReusable_0 = isReusable(this);
  if (trySuspend(this)) {
    if (_get_parentHandle__f8dcex(this) == null) {
      installParentHandle(this);
    }
    if (isReusable_0) {
      this.sc();
    }
    return get_COROUTINE_SUSPENDED();
  }
  if (isReusable_0) {
    this.sc();
  }
  var state = this.ca();
  if (state instanceof CompletedExceptionally)
    throw recoverStackTrace(state.o9_1, this);
  if (get_isCancellableMode(this.tb_1)) {
    var job = this.s4().z4(Key_instance_2);
    if (!(job == null) && !job.j9()) {
      var cause = job.ga();
      this.kc(state, cause);
      throw recoverStackTrace(cause, this);
    }
  }
  return this.tc(state);
};
protoOf(CancellableContinuationImpl).sc = function () {
  var tmp = this.gb_1;
  var tmp10_safe_receiver = tmp instanceof DispatchedContinuation ? tmp : null;
  var tmp0_elvis_lhs = tmp10_safe_receiver == null ? null : tmp10_safe_receiver.uc(this);
  var tmp_0;
  if (tmp0_elvis_lhs == null) {
    return Unit_instance;
  } else {
    tmp_0 = tmp0_elvis_lhs;
  }
  var cancellationCause = tmp_0;
  this.fc();
  this.mc(cancellationCause);
};
protoOf(CancellableContinuationImpl).x4 = function (result) {
  return this.vc(toState(result, this), this.tb_1);
};
protoOf(CancellableContinuationImpl).lb = function (handler) {
  return invokeOnCancellationImpl(this, handler);
};
protoOf(CancellableContinuationImpl).wc = function (proposedUpdate, resumeMode, onCancellation) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = this.jb_1;
  while (true) {
    var tmp0 = this_0.kotlinx$atomicfu$value;
    $l$block: {
      if (!(tmp0 == null) ? isInterface(tmp0, NotCompleted) : false) {
        var update = resumedState(this, tmp0, proposedUpdate, resumeMode, onCancellation, null);
        if (!this.jb_1.atomicfu$compareAndSet(tmp0, update)) {
          break $l$block;
        }
        detachChildIfNonResuable(this);
        dispatchResume(this, resumeMode);
        return Unit_instance;
      } else {
        if (tmp0 instanceof CancelledContinuation) {
          if (tmp0.ad()) {
            if (onCancellation == null)
              null;
            else {
              // Inline function 'kotlin.let' call
              this.pc(onCancellation, tmp0.o9_1, proposedUpdate);
            }
            return Unit_instance;
          }
        }
      }
      alreadyResumedError(this, proposedUpdate);
    }
  }
};
protoOf(CancellableContinuationImpl).vc = function (proposedUpdate, resumeMode, onCancellation, $super) {
  onCancellation = onCancellation === VOID ? null : onCancellation;
  var tmp;
  if ($super === VOID) {
    this.wc(proposedUpdate, resumeMode, onCancellation);
    tmp = Unit_instance;
  } else {
    tmp = $super.wc.call(this, proposedUpdate, resumeMode, onCancellation);
  }
  return tmp;
};
protoOf(CancellableContinuationImpl).fc = function () {
  var tmp0_elvis_lhs = _get_parentHandle__f8dcex(this);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return Unit_instance;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var handle = tmp;
  handle.ic();
  this.kb_1.kotlinx$atomicfu$value = NonDisposableHandle_instance;
};
protoOf(CancellableContinuationImpl).bd = function (_this__u8e3s4, value) {
  var tmp = this.gb_1;
  var dc = tmp instanceof DispatchedContinuation ? tmp : null;
  var tmp_0;
  if ((dc == null ? null : dc.nb_1) === _this__u8e3s4) {
    tmp_0 = 4;
  } else {
    tmp_0 = this.tb_1;
  }
  this.vc(value, tmp_0);
};
protoOf(CancellableContinuationImpl).tc = function (state) {
  var tmp;
  if (state instanceof CompletedContinuation) {
    var tmp_0 = state.wb_1;
    tmp = (tmp_0 == null ? true : !(tmp_0 == null)) ? tmp_0 : THROW_CCE();
  } else {
    tmp = (state == null ? true : !(state == null)) ? state : THROW_CCE();
  }
  return tmp;
};
protoOf(CancellableContinuationImpl).cd = function (state) {
  var tmp20_safe_receiver = protoOf(DispatchedTask).cd.call(this, state);
  var tmp;
  if (tmp20_safe_receiver == null) {
    tmp = null;
  } else {
    // Inline function 'kotlin.let' call
    tmp = recoverStackTrace(tmp20_safe_receiver, this.gb_1);
  }
  return tmp;
};
protoOf(CancellableContinuationImpl).toString = function () {
  return this.dd() + '(' + toDebugString(this.gb_1) + '){' + _get_stateDebugRepresentation__bf18u4(this) + '}@' + get_hexAddress(this);
};
protoOf(CancellableContinuationImpl).dd = function () {
  return 'CancellableContinuation';
};
function NotCompleted() {
}
function CancelHandler() {
}
function Active() {
}
protoOf(Active).toString = function () {
  return 'Active';
};
var Active_instance;
function Active_getInstance() {
  return Active_instance;
}
function CompletedContinuation(result, cancelHandler, onCancellation, idempotentResume, cancelCause) {
  cancelHandler = cancelHandler === VOID ? null : cancelHandler;
  onCancellation = onCancellation === VOID ? null : onCancellation;
  idempotentResume = idempotentResume === VOID ? null : idempotentResume;
  cancelCause = cancelCause === VOID ? null : cancelCause;
  this.wb_1 = result;
  this.xb_1 = cancelHandler;
  this.yb_1 = onCancellation;
  this.zb_1 = idempotentResume;
  this.ac_1 = cancelCause;
}
protoOf(CompletedContinuation).cc = function () {
  return !(this.ac_1 == null);
};
protoOf(CompletedContinuation).lc = function (cont, cause) {
  var tmp21_safe_receiver = this.xb_1;
  if (tmp21_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    cont.bc(tmp21_safe_receiver, cause);
  }
  var tmp22_safe_receiver = this.yb_1;
  if (tmp22_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    cont.pc(tmp22_safe_receiver, cause, this.wb_1);
  }
};
protoOf(CompletedContinuation).gd = function (result, cancelHandler, onCancellation, idempotentResume, cancelCause) {
  return new CompletedContinuation(result, cancelHandler, onCancellation, idempotentResume, cancelCause);
};
protoOf(CompletedContinuation).dc = function (result, cancelHandler, onCancellation, idempotentResume, cancelCause, $super) {
  result = result === VOID ? this.wb_1 : result;
  cancelHandler = cancelHandler === VOID ? this.xb_1 : cancelHandler;
  onCancellation = onCancellation === VOID ? this.yb_1 : onCancellation;
  idempotentResume = idempotentResume === VOID ? this.zb_1 : idempotentResume;
  cancelCause = cancelCause === VOID ? this.ac_1 : cancelCause;
  return $super === VOID ? this.gd(result, cancelHandler, onCancellation, idempotentResume, cancelCause) : $super.gd.call(this, result, cancelHandler, onCancellation, idempotentResume, cancelCause);
};
protoOf(CompletedContinuation).toString = function () {
  return 'CompletedContinuation(result=' + toString_0(this.wb_1) + ', cancelHandler=' + toString_0(this.xb_1) + ', onCancellation=' + toString_0(this.yb_1) + ', idempotentResume=' + toString_0(this.zb_1) + ', cancelCause=' + toString_0(this.ac_1) + ')';
};
protoOf(CompletedContinuation).hashCode = function () {
  var result = this.wb_1 == null ? 0 : hashCode(this.wb_1);
  result = imul(result, 31) + (this.xb_1 == null ? 0 : hashCode(this.xb_1)) | 0;
  result = imul(result, 31) + (this.yb_1 == null ? 0 : hashCode(this.yb_1)) | 0;
  result = imul(result, 31) + (this.zb_1 == null ? 0 : hashCode(this.zb_1)) | 0;
  result = imul(result, 31) + (this.ac_1 == null ? 0 : hashCode(this.ac_1)) | 0;
  return result;
};
protoOf(CompletedContinuation).equals = function (other) {
  if (this === other)
    return true;
  if (!(other instanceof CompletedContinuation))
    return false;
  var tmp0_other_with_cast = other instanceof CompletedContinuation ? other : THROW_CCE();
  if (!equals(this.wb_1, tmp0_other_with_cast.wb_1))
    return false;
  if (!equals(this.xb_1, tmp0_other_with_cast.xb_1))
    return false;
  if (!equals(this.yb_1, tmp0_other_with_cast.yb_1))
    return false;
  if (!equals(this.zb_1, tmp0_other_with_cast.zb_1))
    return false;
  if (!equals(this.ac_1, tmp0_other_with_cast.ac_1))
    return false;
  return true;
};
function ChildContinuation(child) {
  JobNode.call(this);
  this.ld_1 = child;
}
protoOf(ChildContinuation).md = function () {
  return true;
};
protoOf(ChildContinuation).oc = function (cause) {
  this.ld_1.nc(this.ld_1.qc(this.rd()));
};
function CompletedExceptionally(cause, handled) {
  handled = handled === VOID ? false : handled;
  this.o9_1 = cause;
  this.p9_1 = atomic$boolean$1(handled);
}
protoOf(CompletedExceptionally).q9 = function () {
  return this.p9_1.kotlinx$atomicfu$value;
};
protoOf(CompletedExceptionally).ec = function () {
  return this.p9_1.atomicfu$compareAndSet(false, true);
};
protoOf(CompletedExceptionally).toString = function () {
  return get_classSimpleName(this) + '[' + this.o9_1.toString() + ']';
};
function CancelledContinuation(continuation, cause, handled) {
  CompletedExceptionally.call(this, cause == null ? CancellationException_init_$Create$('Continuation ' + toString(continuation) + ' was cancelled normally') : cause, handled);
  this.zc_1 = atomic$boolean$1(false);
}
protoOf(CancelledContinuation).ad = function () {
  return this.zc_1.atomicfu$compareAndSet(false, true);
};
function toState(_this__u8e3s4, caller) {
  // Inline function 'kotlin.getOrElse' call
  var exception = Result__exceptionOrNull_impl_p6xea9(_this__u8e3s4);
  var tmp;
  if (exception == null) {
    var tmp_0 = _Result___get_value__impl__bjfvqg(_this__u8e3s4);
    tmp = (tmp_0 == null ? true : !(tmp_0 == null)) ? tmp_0 : THROW_CCE();
  } else {
    tmp = new CompletedExceptionally(recoverStackTrace(exception, caller));
  }
  return tmp;
}
function toState_0(_this__u8e3s4) {
  // Inline function 'kotlin.getOrElse' call
  var exception = Result__exceptionOrNull_impl_p6xea9(_this__u8e3s4);
  var tmp;
  if (exception == null) {
    var tmp_0 = _Result___get_value__impl__bjfvqg(_this__u8e3s4);
    tmp = (tmp_0 == null ? true : !(tmp_0 == null)) ? tmp_0 : THROW_CCE();
  } else {
    tmp = new CompletedExceptionally(exception);
  }
  return tmp;
}
function CoroutineDispatcher$Key$_init_$lambda_akl8b5(it) {
  return it instanceof CoroutineDispatcher ? it : null;
}
function Key() {
  Key_instance_0 = this;
  var tmp = Key_instance;
  AbstractCoroutineContextKey.call(this, tmp, CoroutineDispatcher$Key$_init_$lambda_akl8b5);
}
var Key_instance_0;
function Key_getInstance() {
  if (Key_instance_0 == null)
    new Key();
  return Key_instance_0;
}
function CoroutineDispatcher() {
  Key_getInstance();
  AbstractCoroutineContextElement.call(this, Key_instance);
}
protoOf(CoroutineDispatcher).ae = function (context) {
  return true;
};
protoOf(CoroutineDispatcher).a5 = function (continuation) {
  return new DispatchedContinuation(this, continuation);
};
protoOf(CoroutineDispatcher).b5 = function (continuation) {
  var dispatched = continuation instanceof DispatchedContinuation ? continuation : THROW_CCE();
  dispatched.ce();
};
protoOf(CoroutineDispatcher).toString = function () {
  return get_classSimpleName(this) + '@' + get_hexAddress(this);
};
function handleCoroutineException(context, exception) {
  try {
    var tmp23_safe_receiver = context.z4(Key_instance_1);
    if (tmp23_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      tmp23_safe_receiver.de(context, exception);
      return Unit_instance;
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var t = $p;
      handleUncaughtCoroutineException(context, handlerException(exception, t));
      return Unit_instance;
    } else {
      throw $p;
    }
  }
  handleUncaughtCoroutineException(context, exception);
}
function Key_0() {
}
var Key_instance_1;
function Key_getInstance_0() {
  return Key_instance_1;
}
function handlerException(originalException, thrownException) {
  if (originalException === thrownException)
    return originalException;
  // Inline function 'kotlin.apply' call
  var this_0 = RuntimeException_init_$Create$('Exception while trying to handle coroutine exception', thrownException);
  addSuppressed(this_0, originalException);
  return this_0;
}
function CoroutineScope() {
}
function MainScope() {
  return new ContextScope(SupervisorJob().y7(Dispatchers_getInstance().ie()));
}
var CoroutineStart_DEFAULT_instance;
var CoroutineStart_LAZY_instance;
var CoroutineStart_ATOMIC_instance;
var CoroutineStart_UNDISPATCHED_instance;
var CoroutineStart_entriesInitialized;
function CoroutineStart_initEntries() {
  if (CoroutineStart_entriesInitialized)
    return Unit_instance;
  CoroutineStart_entriesInitialized = true;
  CoroutineStart_DEFAULT_instance = new CoroutineStart('DEFAULT', 0);
  CoroutineStart_LAZY_instance = new CoroutineStart('LAZY', 1);
  CoroutineStart_ATOMIC_instance = new CoroutineStart('ATOMIC', 2);
  CoroutineStart_UNDISPATCHED_instance = new CoroutineStart('UNDISPATCHED', 3);
}
function CoroutineStart(name, ordinal) {
  Enum.call(this, name, ordinal);
}
protoOf(CoroutineStart).z9 = function (block, receiver, completion) {
  var tmp;
  switch (this.z_1) {
    case 0:
      startCoroutineCancellable_0(block, receiver, completion);
      tmp = Unit_instance;
      break;
    case 2:
      startCoroutine(block, receiver, completion);
      tmp = Unit_instance;
      break;
    case 3:
      startCoroutineUndispatched(block, receiver, completion);
      tmp = Unit_instance;
      break;
    case 1:
      tmp = Unit_instance;
      break;
    default:
      noWhenBranchMatchedException();
      break;
  }
  return tmp;
};
protoOf(CoroutineStart).xa = function () {
  return this === CoroutineStart_LAZY_getInstance();
};
function CoroutineStart_DEFAULT_getInstance() {
  CoroutineStart_initEntries();
  return CoroutineStart_DEFAULT_instance;
}
function CoroutineStart_LAZY_getInstance() {
  CoroutineStart_initEntries();
  return CoroutineStart_LAZY_instance;
}
function Delay() {
}
function get_delay(_this__u8e3s4) {
  var tmp = _this__u8e3s4.z4(Key_instance);
  var tmp0_elvis_lhs = (!(tmp == null) ? isInterface(tmp, Delay) : false) ? tmp : null;
  return tmp0_elvis_lhs == null ? get_DefaultDelay() : tmp0_elvis_lhs;
}
function delay(timeMillis, $completion) {
  if (timeMillis.s(new Long(0, 0)) <= 0)
    return Unit_instance;
  var cancellable = new CancellableContinuationImpl(intercepted($completion), 1);
  cancellable.hc();
  if (timeMillis.s(new Long(-1, 2147483647)) < 0) {
    get_delay(cancellable.s4()).je(timeMillis, cancellable);
  }
  return cancellable.rc();
}
function delta($this, unconfined) {
  return unconfined ? new Long(0, 1) : new Long(1, 0);
}
function EventLoop() {
  CoroutineDispatcher.call(this);
  this.le_1 = new Long(0, 0);
  this.me_1 = false;
  this.ne_1 = null;
}
protoOf(EventLoop).oe = function () {
  var tmp0_elvis_lhs = this.ne_1;
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return false;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var queue = tmp;
  var tmp1_elvis_lhs = queue.n7();
  var tmp_0;
  if (tmp1_elvis_lhs == null) {
    return false;
  } else {
    tmp_0 = tmp1_elvis_lhs;
  }
  var task = tmp_0;
  task.ed();
  return true;
};
protoOf(EventLoop).pe = function (task) {
  var tmp0_elvis_lhs = this.ne_1;
  var tmp;
  if (tmp0_elvis_lhs == null) {
    // Inline function 'kotlin.also' call
    var this_0 = ArrayDeque_init_$Create$();
    this.ne_1 = this_0;
    tmp = this_0;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var queue = tmp;
  queue.l7(task);
};
protoOf(EventLoop).qe = function () {
  return this.le_1.s(delta(this, true)) >= 0;
};
protoOf(EventLoop).re = function () {
  var tmp27_safe_receiver = this.ne_1;
  var tmp0_elvis_lhs = tmp27_safe_receiver == null ? null : tmp27_safe_receiver.n();
  return tmp0_elvis_lhs == null ? true : tmp0_elvis_lhs;
};
protoOf(EventLoop).se = function (unconfined) {
  this.le_1 = this.le_1.g1(delta(this, unconfined));
  if (!unconfined)
    this.me_1 = true;
};
protoOf(EventLoop).te = function (unconfined) {
  this.le_1 = this.le_1.h1(delta(this, unconfined));
  if (this.le_1.s(new Long(0, 0)) > 0)
    return Unit_instance;
  // Inline function 'kotlinx.coroutines.assert' call
  if (this.me_1) {
    this.ue();
  }
};
protoOf(EventLoop).ue = function () {
};
function ThreadLocalEventLoop() {
  ThreadLocalEventLoop_instance = this;
  this.ve_1 = commonThreadLocal(new Symbol('ThreadLocalEventLoop'));
}
protoOf(ThreadLocalEventLoop).we = function () {
  var tmp0_elvis_lhs = this.ve_1.ye();
  var tmp;
  if (tmp0_elvis_lhs == null) {
    // Inline function 'kotlin.also' call
    var this_0 = createEventLoop();
    ThreadLocalEventLoop_getInstance().ve_1.ze(this_0);
    tmp = this_0;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  return tmp;
};
var ThreadLocalEventLoop_instance;
function ThreadLocalEventLoop_getInstance() {
  if (ThreadLocalEventLoop_instance == null)
    new ThreadLocalEventLoop();
  return ThreadLocalEventLoop_instance;
}
function CompletionHandlerException(message, cause) {
  RuntimeException_init_$Init$(message, cause, this);
  captureStack(this, CompletionHandlerException);
}
function CoroutinesInternalError(message, cause) {
  Error_init_$Init$(message, cause, this);
  captureStack(this, CoroutinesInternalError);
}
function Key_1() {
}
var Key_instance_2;
function Key_getInstance_1() {
  return Key_instance_2;
}
function ParentJob() {
}
function NonDisposableHandle() {
}
protoOf(NonDisposableHandle).ic = function () {
};
protoOf(NonDisposableHandle).oa = function (cause) {
  return false;
};
protoOf(NonDisposableHandle).toString = function () {
  return 'NonDisposableHandle';
};
var NonDisposableHandle_instance;
function NonDisposableHandle_getInstance() {
  return NonDisposableHandle_instance;
}
function invokeOnCompletion(_this__u8e3s4, invokeImmediately, handler) {
  invokeImmediately = invokeImmediately === VOID ? true : invokeImmediately;
  var tmp;
  if (_this__u8e3s4 instanceof JobSupport) {
    tmp = _this__u8e3s4.ka(invokeImmediately, handler);
  } else {
    var tmp_0 = handler.md();
    tmp = _this__u8e3s4.ja(tmp_0, invokeImmediately, JobNode$invoke$ref(handler));
  }
  return tmp;
}
function JobNode$invoke$ref(p0) {
  var l = function (_this__u8e3s4) {
    p0.oc(_this__u8e3s4);
    return Unit_instance;
  };
  l.callableName = 'invoke';
  return l;
}
function get_COMPLETING_ALREADY() {
  _init_properties_JobSupport_kt__68f172();
  return COMPLETING_ALREADY;
}
var COMPLETING_ALREADY;
function get_COMPLETING_WAITING_CHILDREN() {
  _init_properties_JobSupport_kt__68f172();
  return COMPLETING_WAITING_CHILDREN;
}
var COMPLETING_WAITING_CHILDREN;
function get_COMPLETING_RETRY() {
  _init_properties_JobSupport_kt__68f172();
  return COMPLETING_RETRY;
}
var COMPLETING_RETRY;
function get_TOO_LATE_TO_CANCEL() {
  _init_properties_JobSupport_kt__68f172();
  return TOO_LATE_TO_CANCEL;
}
var TOO_LATE_TO_CANCEL;
function get_SEALED() {
  _init_properties_JobSupport_kt__68f172();
  return SEALED;
}
var SEALED;
function get_EMPTY_NEW() {
  _init_properties_JobSupport_kt__68f172();
  return EMPTY_NEW;
}
var EMPTY_NEW;
function get_EMPTY_ACTIVE() {
  _init_properties_JobSupport_kt__68f172();
  return EMPTY_ACTIVE;
}
var EMPTY_ACTIVE;
function Empty(isActive) {
  this.af_1 = isActive;
}
protoOf(Empty).j9 = function () {
  return this.af_1;
};
protoOf(Empty).sd = function () {
  return null;
};
protoOf(Empty).toString = function () {
  return 'Empty{' + (this.af_1 ? 'Active' : 'New') + '}';
};
function Incomplete() {
}
function NodeList() {
  LockFreeLinkedListHead.call(this);
}
protoOf(NodeList).j9 = function () {
  return true;
};
protoOf(NodeList).sd = function () {
  return this;
};
protoOf(NodeList).ef = function (state) {
  // Inline function 'kotlin.text.buildString' call
  // Inline function 'kotlin.apply' call
  var this_0 = StringBuilder_init_$Create$();
  this_0.e4('List{');
  this_0.e4(state);
  this_0.e4('}[');
  var first = true;
  // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListHead.forEach' call
  var cur = this.td_1;
  while (!equals(cur, this)) {
    var node = cur;
    if (node instanceof JobNode) {
      if (first) {
        first = false;
      } else
        this_0.e4(', ');
      this_0.d4(node);
    }
    cur = cur.td_1;
  }
  this_0.e4(']');
  return this_0.toString();
};
protoOf(NodeList).toString = function () {
  return get_DEBUG() ? this.ef('Active') : protoOf(LockFreeLinkedListHead).toString.call(this);
};
function JobNode() {
  LockFreeLinkedListNode.call(this);
}
protoOf(JobNode).rd = function () {
  var tmp = this.qd_1;
  if (!(tmp == null))
    return tmp;
  else {
    throwUninitializedPropertyAccessException('job');
  }
};
protoOf(JobNode).j9 = function () {
  return true;
};
protoOf(JobNode).sd = function () {
  return null;
};
protoOf(JobNode).ic = function () {
  return this.rd().la(this);
};
protoOf(JobNode).toString = function () {
  return get_classSimpleName(this) + '@' + get_hexAddress(this) + '[job@' + get_hexAddress(this.rd()) + ']';
};
function _set_exceptionsHolder__tqm22h($this, value) {
  $this.jf_1.kotlinx$atomicfu$value = value;
}
function _get_exceptionsHolder__nhszp($this) {
  return $this.jf_1.kotlinx$atomicfu$value;
}
function allocateList($this) {
  return ArrayList_init_$Create$(4);
}
function finalizeFinishingState($this, state, proposedUpdate) {
  // Inline function 'kotlinx.coroutines.assert' call
  // Inline function 'kotlinx.coroutines.assert' call
  // Inline function 'kotlinx.coroutines.assert' call
  var tmp46_safe_receiver = proposedUpdate instanceof CompletedExceptionally ? proposedUpdate : null;
  var proposedException = tmp46_safe_receiver == null ? null : tmp46_safe_receiver.o9_1;
  var wasCancelling;
  // Inline function 'kotlinx.coroutines.internal.synchronized' call
  // Inline function 'kotlinx.coroutines.internal.synchronizedImpl' call
  wasCancelling = state.kf();
  var exceptions = state.lf(proposedException);
  var finalCause = getFinalRootCause($this, state, exceptions);
  if (!(finalCause == null)) {
    addSuppressedExceptions($this, finalCause, exceptions);
  }
  var finalException = finalCause;
  var finalState = finalException == null ? proposedUpdate : finalException === proposedException ? proposedUpdate : new CompletedExceptionally(finalException);
  if (!(finalException == null)) {
    var handled = cancelParent($this, finalException) || $this.va(finalException);
    if (handled) {
      (finalState instanceof CompletedExceptionally ? finalState : THROW_CCE()).ec();
    }
  }
  if (!wasCancelling) {
    $this.sa(finalException);
  }
  $this.n9(finalState);
  var casSuccess = $this.c9_1.atomicfu$compareAndSet(state, boxIncomplete(finalState));
  // Inline function 'kotlinx.coroutines.assert' call
  completeStateFinalization($this, state, finalState);
  return finalState;
}
function getFinalRootCause($this, state, exceptions) {
  if (exceptions.n()) {
    if (state.kf()) {
      // Inline function 'kotlinx.coroutines.JobSupport.defaultCancellationException' call
      return new JobCancellationException(null == null ? $this.m9() : null, null, $this);
    }
    return null;
  }
  var tmp$ret$2;
  $l$block: {
    // Inline function 'kotlin.collections.firstOrNull' call
    var _iterator__ex2g4s = exceptions.e();
    while (_iterator__ex2g4s.f()) {
      var element = _iterator__ex2g4s.g();
      if (!(element instanceof CancellationException)) {
        tmp$ret$2 = element;
        break $l$block;
      }
    }
    tmp$ret$2 = null;
  }
  var firstNonCancellation = tmp$ret$2;
  if (!(firstNonCancellation == null))
    return firstNonCancellation;
  var first = exceptions.o(0);
  if (first instanceof TimeoutCancellationException) {
    var tmp$ret$4;
    $l$block_0: {
      // Inline function 'kotlin.collections.firstOrNull' call
      var _iterator__ex2g4s_0 = exceptions.e();
      while (_iterator__ex2g4s_0.f()) {
        var element_0 = _iterator__ex2g4s_0.g();
        var tmp;
        if (!(element_0 === first)) {
          tmp = element_0 instanceof TimeoutCancellationException;
        } else {
          tmp = false;
        }
        if (tmp) {
          tmp$ret$4 = element_0;
          break $l$block_0;
        }
      }
      tmp$ret$4 = null;
    }
    var detailedTimeoutException = tmp$ret$4;
    if (!(detailedTimeoutException == null))
      return detailedTimeoutException;
  }
  return first;
}
function addSuppressedExceptions($this, rootCause, exceptions) {
  if (exceptions.h() <= 1)
    return Unit_instance;
  var seenExceptions = identitySet(exceptions.h());
  var unwrappedCause = unwrap(rootCause);
  var _iterator__ex2g4s = exceptions.e();
  while (_iterator__ex2g4s.f()) {
    var exception = _iterator__ex2g4s.g();
    var unwrapped = unwrap(exception);
    var tmp;
    var tmp_0;
    if (!(unwrapped === rootCause) && !(unwrapped === unwrappedCause)) {
      tmp_0 = !(unwrapped instanceof CancellationException);
    } else {
      tmp_0 = false;
    }
    if (tmp_0) {
      tmp = seenExceptions.m(unwrapped);
    } else {
      tmp = false;
    }
    if (tmp) {
      addSuppressed(rootCause, unwrapped);
    }
  }
}
function tryFinalizeSimpleState($this, state, update) {
  // Inline function 'kotlinx.coroutines.assert' call
  // Inline function 'kotlinx.coroutines.assert' call
  if (!$this.c9_1.atomicfu$compareAndSet(state, boxIncomplete(update)))
    return false;
  $this.sa(null);
  $this.n9(update);
  completeStateFinalization($this, state, update);
  return true;
}
function completeStateFinalization($this, state, update) {
  var tmp47_safe_receiver = $this.ba();
  if (tmp47_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    tmp47_safe_receiver.ic();
    $this.aa(NonDisposableHandle_instance);
  }
  var tmp48_safe_receiver = update instanceof CompletedExceptionally ? update : null;
  var cause = tmp48_safe_receiver == null ? null : tmp48_safe_receiver.o9_1;
  if (state instanceof JobNode) {
    try {
      state.oc(cause);
    } catch ($p) {
      if ($p instanceof Error) {
        var ex = $p;
        $this.u9(new CompletionHandlerException('Exception in completion handler ' + state.toString() + ' for ' + $this.toString(), ex));
      } else {
        throw $p;
      }
    }
  } else {
    var tmp49_safe_receiver = state.sd();
    if (tmp49_safe_receiver == null)
      null;
    else {
      notifyCompletion($this, tmp49_safe_receiver, cause);
    }
  }
}
function notifyCancelling($this, list, cause) {
  $this.sa(cause);
  list.ff(4);
  // Inline function 'kotlinx.coroutines.JobSupport.notifyHandlers' call
  var exception = null;
  // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListHead.forEach' call
  var cur = list.td_1;
  while (!equals(cur, list)) {
    var node = cur;
    var tmp;
    if (node instanceof JobNode) {
      tmp = node.md();
    } else {
      tmp = false;
    }
    if (tmp) {
      try {
        node.oc(cause);
      } catch ($p) {
        if ($p instanceof Error) {
          var ex = $p;
          var tmp50_safe_receiver = exception;
          var tmp_0;
          if (tmp50_safe_receiver == null) {
            tmp_0 = null;
          } else {
            // Inline function 'kotlin.apply' call
            addSuppressed(tmp50_safe_receiver, ex);
            tmp_0 = tmp50_safe_receiver;
          }
          if (tmp_0 == null) {
            // Inline function 'kotlin.run' call
            exception = new CompletionHandlerException('Exception in completion handler ' + node.toString() + ' for ' + $this.toString(), ex);
          }
        } else {
          throw $p;
        }
      }
    }
    cur = cur.td_1;
  }
  var tmp51_safe_receiver = exception;
  if (tmp51_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    $this.u9(tmp51_safe_receiver);
  }
  cancelParent($this, cause);
}
function cancelParent($this, cause) {
  if ($this.ta())
    return true;
  var isCancellation = cause instanceof CancellationException;
  var parent = $this.ba();
  if (parent === null || parent === NonDisposableHandle_instance) {
    return isCancellation;
  }
  return parent.oa(cause) || isCancellation;
}
function notifyCompletion($this, _this__u8e3s4, cause) {
  _this__u8e3s4.ff(1);
  // Inline function 'kotlinx.coroutines.JobSupport.notifyHandlers' call
  var exception = null;
  // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListHead.forEach' call
  var cur = _this__u8e3s4.td_1;
  while (!equals(cur, _this__u8e3s4)) {
    var node = cur;
    var tmp;
    if (node instanceof JobNode) {
      tmp = true;
    } else {
      tmp = false;
    }
    if (tmp) {
      try {
        node.oc(cause);
      } catch ($p) {
        if ($p instanceof Error) {
          var ex = $p;
          var tmp50_safe_receiver = exception;
          var tmp_0;
          if (tmp50_safe_receiver == null) {
            tmp_0 = null;
          } else {
            // Inline function 'kotlin.apply' call
            addSuppressed(tmp50_safe_receiver, ex);
            tmp_0 = tmp50_safe_receiver;
          }
          if (tmp_0 == null) {
            // Inline function 'kotlin.run' call
            exception = new CompletionHandlerException('Exception in completion handler ' + node.toString() + ' for ' + $this.toString(), ex);
          }
        } else {
          throw $p;
        }
      }
    }
    cur = cur.td_1;
  }
  var tmp51_safe_receiver = exception;
  if (tmp51_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    $this.u9(tmp51_safe_receiver);
  }
}
function startInternal($this, state) {
  if (state instanceof Empty) {
    if (state.af_1)
      return 0;
    if (!$this.c9_1.atomicfu$compareAndSet(state, get_EMPTY_ACTIVE()))
      return -1;
    $this.fa();
    return 1;
  } else {
    if (state instanceof InactiveNodeList) {
      if (!$this.c9_1.atomicfu$compareAndSet(state, state.mf_1))
        return -1;
      $this.fa();
      return 1;
    } else {
      return 0;
    }
  }
}
function promoteEmptyToNodeList($this, state) {
  var list = new NodeList();
  var update = state.af_1 ? list : new InactiveNodeList(list);
  $this.c9_1.atomicfu$compareAndSet(state, update);
}
function promoteSingleToNodeList($this, state) {
  state.yd(new NodeList());
  // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListNode.nextNode' call
  var list = state.td_1;
  $this.c9_1.atomicfu$compareAndSet(state, list);
}
function cancelMakeCompleting($this, cause) {
  // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
  while (true) {
    var state = $this.ca();
    var tmp;
    if (!(!(state == null) ? isInterface(state, Incomplete) : false)) {
      tmp = true;
    } else {
      var tmp_0;
      if (state instanceof Finishing) {
        tmp_0 = state.nf();
      } else {
        tmp_0 = false;
      }
      tmp = tmp_0;
    }
    if (tmp) {
      return get_COMPLETING_ALREADY();
    }
    var proposedUpdate = new CompletedExceptionally(createCauseException($this, cause));
    var finalState = tryMakeCompleting($this, state, proposedUpdate);
    if (!(finalState === get_COMPLETING_RETRY()))
      return finalState;
  }
}
function createCauseException($this, cause) {
  var tmp;
  if (cause == null ? true : cause instanceof Error) {
    var tmp_0;
    if (cause == null) {
      // Inline function 'kotlinx.coroutines.JobSupport.defaultCancellationException' call
      tmp_0 = new JobCancellationException(null == null ? $this.m9() : null, null, $this);
    } else {
      tmp_0 = cause;
    }
    tmp = tmp_0;
  } else {
    tmp = ((!(cause == null) ? isInterface(cause, ParentJob) : false) ? cause : THROW_CCE()).qa();
  }
  return tmp;
}
function makeCancelling($this, cause) {
  var causeExceptionCache = null;
  // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
  while (true) {
    var tmp0 = $this.ca();
    $l$block: {
      if (tmp0 instanceof Finishing) {
        // Inline function 'kotlinx.coroutines.internal.synchronized' call
        // Inline function 'kotlinx.coroutines.internal.synchronizedImpl' call
        if (tmp0.of())
          return get_TOO_LATE_TO_CANCEL();
        var wasCancelling = tmp0.kf();
        if (!(cause == null) || !wasCancelling) {
          var tmp0_elvis_lhs = causeExceptionCache;
          var tmp;
          if (tmp0_elvis_lhs == null) {
            // Inline function 'kotlin.also' call
            var this_0 = createCauseException($this, cause);
            causeExceptionCache = this_0;
            tmp = this_0;
          } else {
            tmp = tmp0_elvis_lhs;
          }
          var causeException = tmp;
          tmp0.pf(causeException);
        }
        // Inline function 'kotlin.takeIf' call
        var this_1 = tmp0.qf();
        var tmp_0;
        if (!wasCancelling) {
          tmp_0 = this_1;
        } else {
          tmp_0 = null;
        }
        var notifyRootCause = tmp_0;
        if (notifyRootCause == null)
          null;
        else {
          // Inline function 'kotlin.let' call
          notifyCancelling($this, tmp0.gf_1, notifyRootCause);
        }
        return get_COMPLETING_ALREADY();
      } else {
        if (!(tmp0 == null) ? isInterface(tmp0, Incomplete) : false) {
          var tmp0_elvis_lhs_0 = causeExceptionCache;
          var tmp_1;
          if (tmp0_elvis_lhs_0 == null) {
            // Inline function 'kotlin.also' call
            var this_2 = createCauseException($this, cause);
            causeExceptionCache = this_2;
            tmp_1 = this_2;
          } else {
            tmp_1 = tmp0_elvis_lhs_0;
          }
          var causeException_0 = tmp_1;
          if (tmp0.j9()) {
            if (tryMakeCancelling($this, tmp0, causeException_0))
              return get_COMPLETING_ALREADY();
          } else {
            var finalState = tryMakeCompleting($this, tmp0, new CompletedExceptionally(causeException_0));
            if (finalState === get_COMPLETING_ALREADY()) {
              // Inline function 'kotlin.error' call
              var message = 'Cannot happen in ' + toString(tmp0);
              throw IllegalStateException_init_$Create$(toString(message));
            } else if (finalState === get_COMPLETING_RETRY()) {
              break $l$block;
            } else
              return finalState;
          }
        } else {
          return get_TOO_LATE_TO_CANCEL();
        }
      }
    }
  }
}
function getOrPromoteCancellingList($this, state) {
  var tmp0_elvis_lhs = state.sd();
  var tmp;
  if (tmp0_elvis_lhs == null) {
    var tmp_0;
    if (state instanceof Empty) {
      tmp_0 = new NodeList();
    } else {
      if (state instanceof JobNode) {
        promoteSingleToNodeList($this, state);
        tmp_0 = null;
      } else {
        var message = 'State should have list: ' + toString(state);
        throw IllegalStateException_init_$Create$(toString(message));
      }
    }
    tmp = tmp_0;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  return tmp;
}
function tryMakeCancelling($this, state, rootCause) {
  // Inline function 'kotlinx.coroutines.assert' call
  // Inline function 'kotlinx.coroutines.assert' call
  var tmp0_elvis_lhs = getOrPromoteCancellingList($this, state);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return false;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var list = tmp;
  var cancelling = new Finishing(list, false, rootCause);
  if (!$this.c9_1.atomicfu$compareAndSet(state, cancelling))
    return false;
  notifyCancelling($this, list, rootCause);
  return true;
}
function tryMakeCompleting($this, state, proposedUpdate) {
  if (!(!(state == null) ? isInterface(state, Incomplete) : false))
    return get_COMPLETING_ALREADY();
  var tmp;
  var tmp_0;
  var tmp_1;
  if (state instanceof Empty) {
    tmp_1 = true;
  } else {
    tmp_1 = state instanceof JobNode;
  }
  if (tmp_1) {
    tmp_0 = !(state instanceof ChildHandleNode);
  } else {
    tmp_0 = false;
  }
  if (tmp_0) {
    tmp = !(proposedUpdate instanceof CompletedExceptionally);
  } else {
    tmp = false;
  }
  if (tmp) {
    if (tryFinalizeSimpleState($this, state, proposedUpdate)) {
      return proposedUpdate;
    }
    return get_COMPLETING_RETRY();
  }
  return tryMakeCompletingSlowPath($this, state, proposedUpdate);
}
function tryMakeCompletingSlowPath($this, state, proposedUpdate) {
  var tmp0_elvis_lhs = getOrPromoteCancellingList($this, state);
  var tmp;
  if (tmp0_elvis_lhs == null) {
    return get_COMPLETING_RETRY();
  } else {
    tmp = tmp0_elvis_lhs;
  }
  var list = tmp;
  var tmp1_elvis_lhs = state instanceof Finishing ? state : null;
  var finishing = tmp1_elvis_lhs == null ? new Finishing(list, false, null) : tmp1_elvis_lhs;
  var notifyRootCause;
  // Inline function 'kotlinx.coroutines.internal.synchronized' call
  // Inline function 'kotlinx.coroutines.internal.synchronizedImpl' call
  if (finishing.nf())
    return get_COMPLETING_ALREADY();
  finishing.rf(true);
  if (!(finishing === state)) {
    if (!$this.c9_1.atomicfu$compareAndSet(state, finishing))
      return get_COMPLETING_RETRY();
  }
  // Inline function 'kotlinx.coroutines.assert' call
  var wasCancelling = finishing.kf();
  var tmp65_safe_receiver = proposedUpdate instanceof CompletedExceptionally ? proposedUpdate : null;
  if (tmp65_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    finishing.pf(tmp65_safe_receiver.o9_1);
  }
  // Inline function 'kotlin.takeIf' call
  var this_0 = finishing.qf();
  var tmp_0;
  if (!wasCancelling) {
    tmp_0 = this_0;
  } else {
    tmp_0 = null;
  }
  notifyRootCause = tmp_0;
  if (notifyRootCause == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    notifyCancelling($this, list, notifyRootCause);
  }
  var child = nextChild($this, list);
  if (!(child == null) && tryWaitForChild($this, finishing, child, proposedUpdate))
    return get_COMPLETING_WAITING_CHILDREN();
  list.ff(2);
  var anotherChild = nextChild($this, list);
  if (!(anotherChild == null) && tryWaitForChild($this, finishing, anotherChild, proposedUpdate))
    return get_COMPLETING_WAITING_CHILDREN();
  return finalizeFinishingState($this, finishing, proposedUpdate);
}
function _get_exceptionOrNull__b3j7js($this, _this__u8e3s4) {
  var tmp67_safe_receiver = _this__u8e3s4 instanceof CompletedExceptionally ? _this__u8e3s4 : null;
  return tmp67_safe_receiver == null ? null : tmp67_safe_receiver.o9_1;
}
function tryWaitForChild($this, state, child, proposedUpdate) {
  var $this_0 = $this;
  var state_0 = state;
  var child_0 = child;
  var proposedUpdate_0 = proposedUpdate;
  $l$1: do {
    $l$0: do {
      var handle = invokeOnCompletion(child_0.wf_1, false, new ChildCompletion($this_0, state_0, child_0, proposedUpdate_0));
      if (!(handle === NonDisposableHandle_instance))
        return true;
      var tmp0_elvis_lhs = nextChild($this_0, child_0);
      var tmp;
      if (tmp0_elvis_lhs == null) {
        return false;
      } else {
        tmp = tmp0_elvis_lhs;
      }
      var nextChild_0 = tmp;
      var tmp0 = $this_0;
      var tmp1 = state_0;
      var tmp3 = proposedUpdate_0;
      $this_0 = tmp0;
      state_0 = tmp1;
      child_0 = nextChild_0;
      proposedUpdate_0 = tmp3;
      continue $l$0;
    }
     while (false);
  }
   while (true);
}
function continueCompleting($this, state, lastChild, proposedUpdate) {
  // Inline function 'kotlinx.coroutines.assert' call
  var waitChild = nextChild($this, lastChild);
  if (!(waitChild == null) && tryWaitForChild($this, state, waitChild, proposedUpdate))
    return Unit_instance;
  state.gf_1.ff(2);
  var waitChildAgain = nextChild($this, lastChild);
  if (!(waitChildAgain == null) && tryWaitForChild($this, state, waitChildAgain, proposedUpdate)) {
    return Unit_instance;
  }
  var finalState = finalizeFinishingState($this, state, proposedUpdate);
  $this.t9(finalState);
}
function nextChild($this, _this__u8e3s4) {
  var cur = _this__u8e3s4;
  $l$loop: while (true) {
    // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListNode.isRemoved' call
    if (!cur.vd_1) {
      break $l$loop;
    }
    // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListNode.prevNode' call
    cur = cur.ud_1;
  }
  $l$loop_0: while (true) {
    // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListNode.nextNode' call
    cur = cur.td_1;
    // Inline function 'kotlinx.coroutines.internal.LockFreeLinkedListNode.isRemoved' call
    if (cur.vd_1)
      continue $l$loop_0;
    if (cur instanceof ChildHandleNode)
      return cur;
    if (cur instanceof NodeList)
      return null;
  }
}
function stateString($this, state) {
  var tmp;
  if (state instanceof Finishing) {
    tmp = state.kf() ? 'Cancelling' : state.nf() ? 'Completing' : 'Active';
  } else {
    if (!(state == null) ? isInterface(state, Incomplete) : false) {
      tmp = state.j9() ? 'Active' : 'New';
    } else {
      if (state instanceof CompletedExceptionally) {
        tmp = 'Cancelled';
      } else {
        tmp = 'Completed';
      }
    }
  }
  return tmp;
}
function Finishing(list, isCompleting, rootCause) {
  SynchronizedObject.call(this);
  this.gf_1 = list;
  this.hf_1 = atomic$boolean$1(isCompleting);
  this.if_1 = atomic$ref$1(rootCause);
  this.jf_1 = atomic$ref$1(null);
}
protoOf(Finishing).sd = function () {
  return this.gf_1;
};
protoOf(Finishing).rf = function (value) {
  this.hf_1.kotlinx$atomicfu$value = value;
};
protoOf(Finishing).nf = function () {
  return this.hf_1.kotlinx$atomicfu$value;
};
protoOf(Finishing).xf = function (value) {
  this.if_1.kotlinx$atomicfu$value = value;
};
protoOf(Finishing).qf = function () {
  return this.if_1.kotlinx$atomicfu$value;
};
protoOf(Finishing).of = function () {
  return _get_exceptionsHolder__nhszp(this) === get_SEALED();
};
protoOf(Finishing).kf = function () {
  return !(this.qf() == null);
};
protoOf(Finishing).j9 = function () {
  return this.qf() == null;
};
protoOf(Finishing).lf = function (proposedException) {
  var eh = _get_exceptionsHolder__nhszp(this);
  var tmp;
  if (eh == null) {
    tmp = allocateList(this);
  } else {
    if (eh instanceof Error) {
      // Inline function 'kotlin.also' call
      var this_0 = allocateList(this);
      this_0.m(eh);
      tmp = this_0;
    } else {
      if (eh instanceof ArrayList) {
        tmp = eh instanceof ArrayList ? eh : THROW_CCE();
      } else {
        var message = 'State is ' + toString_0(eh);
        throw IllegalStateException_init_$Create$(toString(message));
      }
    }
  }
  var list = tmp;
  var rootCause = this.qf();
  if (rootCause == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    list.c2(0, rootCause);
  }
  if (!(proposedException == null) && !equals(proposedException, rootCause)) {
    list.m(proposedException);
  }
  _set_exceptionsHolder__tqm22h(this, get_SEALED());
  return list;
};
protoOf(Finishing).pf = function (exception) {
  var rootCause = this.qf();
  if (rootCause == null) {
    this.xf(exception);
    return Unit_instance;
  }
  if (exception === rootCause)
    return Unit_instance;
  var eh = _get_exceptionsHolder__nhszp(this);
  if (eh == null) {
    _set_exceptionsHolder__tqm22h(this, exception);
  } else {
    if (eh instanceof Error) {
      if (exception === eh)
        return Unit_instance;
      // Inline function 'kotlin.apply' call
      var this_0 = allocateList(this);
      this_0.m(eh);
      this_0.m(exception);
      _set_exceptionsHolder__tqm22h(this, this_0);
    } else {
      if (eh instanceof ArrayList) {
        (eh instanceof ArrayList ? eh : THROW_CCE()).m(exception);
      } else {
        // Inline function 'kotlin.error' call
        var message = 'State is ' + toString_0(eh);
        throw IllegalStateException_init_$Create$(toString(message));
      }
    }
  }
};
protoOf(Finishing).toString = function () {
  return 'Finishing[cancelling=' + this.kf() + ', completing=' + this.nf() + ', rootCause=' + toString_0(this.qf()) + ', exceptions=' + toString_0(_get_exceptionsHolder__nhszp(this)) + ', list=' + this.gf_1.toString() + ']';
};
function ChildCompletion(parent, state, child, proposedUpdate) {
  JobNode.call(this);
  this.cg_1 = parent;
  this.dg_1 = state;
  this.eg_1 = child;
  this.fg_1 = proposedUpdate;
}
protoOf(ChildCompletion).md = function () {
  return false;
};
protoOf(ChildCompletion).oc = function (cause) {
  continueCompleting(this.cg_1, this.dg_1, this.eg_1, this.fg_1);
};
function JobSupport(active) {
  this.c9_1 = atomic$ref$1(active ? get_EMPTY_ACTIVE() : get_EMPTY_NEW());
  this.d9_1 = atomic$ref$1(null);
}
protoOf(JobSupport).v = function () {
  return Key_instance_2;
};
protoOf(JobSupport).aa = function (value) {
  this.d9_1.kotlinx$atomicfu$value = value;
};
protoOf(JobSupport).ba = function () {
  return this.d9_1.kotlinx$atomicfu$value;
};
protoOf(JobSupport).e9 = function (parent) {
  // Inline function 'kotlinx.coroutines.assert' call
  if (parent == null) {
    this.aa(NonDisposableHandle_instance);
    return Unit_instance;
  }
  parent.ea();
  var handle = parent.ra(this);
  this.aa(handle);
  if (this.da()) {
    handle.ic();
    this.aa(NonDisposableHandle_instance);
  }
};
protoOf(JobSupport).ca = function () {
  return this.c9_1.kotlinx$atomicfu$value;
};
protoOf(JobSupport).j9 = function () {
  var state = this.ca();
  var tmp;
  if (!(state == null) ? isInterface(state, Incomplete) : false) {
    tmp = state.j9();
  } else {
    tmp = false;
  }
  return tmp;
};
protoOf(JobSupport).da = function () {
  var tmp = this.ca();
  return !(!(tmp == null) ? isInterface(tmp, Incomplete) : false);
};
protoOf(JobSupport).ea = function () {
  // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
  while (true) {
    var state = this.ca();
    var tmp52_subject = startInternal(this, state);
    if (tmp52_subject === 0)
      return false;
    else if (tmp52_subject === 1)
      return true;
  }
};
protoOf(JobSupport).fa = function () {
};
protoOf(JobSupport).ga = function () {
  var state = this.ca();
  var tmp;
  if (state instanceof Finishing) {
    var tmp54_safe_receiver = state.qf();
    var tmp0_elvis_lhs = tmp54_safe_receiver == null ? null : this.ha(tmp54_safe_receiver, get_classSimpleName(this) + ' is cancelling');
    var tmp_0;
    if (tmp0_elvis_lhs == null) {
      var message = 'Job is still new or active: ' + this.toString();
      throw IllegalStateException_init_$Create$(toString(message));
    } else {
      tmp_0 = tmp0_elvis_lhs;
    }
    tmp = tmp_0;
  } else {
    if (!(state == null) ? isInterface(state, Incomplete) : false) {
      var message_0 = 'Job is still new or active: ' + this.toString();
      throw IllegalStateException_init_$Create$(toString(message_0));
    } else {
      if (state instanceof CompletedExceptionally) {
        tmp = this.ia(state.o9_1);
      } else {
        tmp = new JobCancellationException(get_classSimpleName(this) + ' has completed normally', null, this);
      }
    }
  }
  return tmp;
};
protoOf(JobSupport).ha = function (_this__u8e3s4, message) {
  var tmp0_elvis_lhs = _this__u8e3s4 instanceof CancellationException ? _this__u8e3s4 : null;
  var tmp;
  if (tmp0_elvis_lhs == null) {
    // Inline function 'kotlinx.coroutines.JobSupport.defaultCancellationException' call
    tmp = new JobCancellationException(message == null ? this.m9() : message, _this__u8e3s4, this);
  } else {
    tmp = tmp0_elvis_lhs;
  }
  return tmp;
};
protoOf(JobSupport).ia = function (_this__u8e3s4, message, $super) {
  message = message === VOID ? null : message;
  return $super === VOID ? this.ha(_this__u8e3s4, message) : $super.ha.call(this, _this__u8e3s4, message);
};
protoOf(JobSupport).ja = function (onCancelling, invokeImmediately, handler) {
  var tmp;
  if (onCancelling) {
    tmp = new InvokeOnCancelling(handler);
  } else {
    tmp = new InvokeOnCompletion(handler);
  }
  return this.ka(invokeImmediately, tmp);
};
protoOf(JobSupport).ka = function (invokeImmediately, node) {
  node.qd_1 = this;
  var tmp$ret$0;
  $l$block_1: {
    // Inline function 'kotlinx.coroutines.JobSupport.tryPutNodeIntoList' call
    // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
    while (true) {
      var state = this.ca();
      if (state instanceof Empty) {
        if (state.af_1) {
          if (this.c9_1.atomicfu$compareAndSet(state, node)) {
            tmp$ret$0 = true;
            break $l$block_1;
          }
        } else {
          promoteEmptyToNodeList(this, state);
        }
      } else {
        if (!(state == null) ? isInterface(state, Incomplete) : false) {
          var list = state.sd();
          if (list == null) {
            promoteSingleToNodeList(this, state instanceof JobNode ? state : THROW_CCE());
          } else {
            var tmp;
            if (node.md()) {
              var tmp55_safe_receiver = state instanceof Finishing ? state : null;
              var rootCause = tmp55_safe_receiver == null ? null : tmp55_safe_receiver.qf();
              var tmp_0;
              if (rootCause == null) {
                tmp_0 = list.wd(node, 5);
              } else {
                if (invokeImmediately) {
                  node.oc(rootCause);
                }
                return NonDisposableHandle_instance;
              }
              tmp = tmp_0;
            } else {
              tmp = list.wd(node, 1);
            }
            if (tmp) {
              tmp$ret$0 = true;
              break $l$block_1;
            }
          }
        } else {
          tmp$ret$0 = false;
          break $l$block_1;
        }
      }
    }
  }
  var added = tmp$ret$0;
  if (added)
    return node;
  else if (invokeImmediately) {
    var tmp_1 = this.ca();
    var tmp56_safe_receiver = tmp_1 instanceof CompletedExceptionally ? tmp_1 : null;
    node.oc(tmp56_safe_receiver == null ? null : tmp56_safe_receiver.o9_1);
  }
  return NonDisposableHandle_instance;
};
protoOf(JobSupport).la = function (node) {
  // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
  while (true) {
    var state = this.ca();
    if (state instanceof JobNode) {
      if (!(state === node))
        return Unit_instance;
      if (this.c9_1.atomicfu$compareAndSet(state, get_EMPTY_ACTIVE()))
        return Unit_instance;
    } else {
      if (!(state == null) ? isInterface(state, Incomplete) : false) {
        if (!(state.sd() == null)) {
          node.xd();
        }
        return Unit_instance;
      } else {
        return Unit_instance;
      }
    }
  }
};
protoOf(JobSupport).ma = function () {
  return false;
};
protoOf(JobSupport).m9 = function () {
  return 'Job was cancelled';
};
protoOf(JobSupport).na = function (parentJob) {
  this.pa(parentJob);
};
protoOf(JobSupport).oa = function (cause) {
  if (cause instanceof CancellationException)
    return true;
  return this.pa(cause) && this.ua();
};
protoOf(JobSupport).pa = function (cause) {
  var finalState = get_COMPLETING_ALREADY();
  if (this.ma()) {
    finalState = cancelMakeCompleting(this, cause);
    if (finalState === get_COMPLETING_WAITING_CHILDREN())
      return true;
  }
  if (finalState === get_COMPLETING_ALREADY()) {
    finalState = makeCancelling(this, cause);
  }
  var tmp;
  if (finalState === get_COMPLETING_ALREADY()) {
    tmp = true;
  } else if (finalState === get_COMPLETING_WAITING_CHILDREN()) {
    tmp = true;
  } else if (finalState === get_TOO_LATE_TO_CANCEL()) {
    tmp = false;
  } else {
    this.t9(finalState);
    tmp = true;
  }
  return tmp;
};
protoOf(JobSupport).qa = function () {
  var state = this.ca();
  var tmp;
  if (state instanceof Finishing) {
    tmp = state.qf();
  } else {
    if (state instanceof CompletedExceptionally) {
      tmp = state.o9_1;
    } else {
      if (!(state == null) ? isInterface(state, Incomplete) : false) {
        var message = 'Cannot be cancelling child in this state: ' + toString(state);
        throw IllegalStateException_init_$Create$(toString(message));
      } else {
        tmp = null;
      }
    }
  }
  var rootCause = tmp;
  var tmp0_elvis_lhs = rootCause instanceof CancellationException ? rootCause : null;
  return tmp0_elvis_lhs == null ? new JobCancellationException('Parent job is ' + stateString(this, state), rootCause, this) : tmp0_elvis_lhs;
};
protoOf(JobSupport).r9 = function (proposedUpdate) {
  // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
  while (true) {
    var tmp0 = this.ca();
    $l$block: {
      var finalState = tryMakeCompleting(this, tmp0, proposedUpdate);
      if (finalState === get_COMPLETING_ALREADY())
        throw IllegalStateException_init_$Create$_0('Job ' + this.toString() + ' is already complete or completing, ' + ('but is being completed with ' + toString_0(proposedUpdate)), _get_exceptionOrNull__b3j7js(this, proposedUpdate));
      else if (finalState === get_COMPLETING_RETRY()) {
        break $l$block;
      } else
        return finalState;
    }
  }
};
protoOf(JobSupport).ra = function (child) {
  // Inline function 'kotlin.also' call
  var this_0 = new ChildHandleNode(child);
  this_0.qd_1 = this;
  var node = this_0;
  var tmp$ret$2;
  $l$block_1: {
    // Inline function 'kotlinx.coroutines.JobSupport.tryPutNodeIntoList' call
    // Inline function 'kotlinx.coroutines.JobSupport.loopOnState' call
    while (true) {
      var state = this.ca();
      if (state instanceof Empty) {
        if (state.af_1) {
          if (this.c9_1.atomicfu$compareAndSet(state, node)) {
            tmp$ret$2 = true;
            break $l$block_1;
          }
        } else {
          promoteEmptyToNodeList(this, state);
        }
      } else {
        if (!(state == null) ? isInterface(state, Incomplete) : false) {
          var list = state.sd();
          if (list == null) {
            promoteSingleToNodeList(this, state instanceof JobNode ? state : THROW_CCE());
          } else {
            var addedBeforeCancellation = list.wd(node, 7);
            var tmp;
            if (addedBeforeCancellation) {
              tmp = true;
            } else {
              var addedBeforeCompletion = list.wd(node, 3);
              var latestState = this.ca();
              var tmp_0;
              if (latestState instanceof Finishing) {
                tmp_0 = latestState.qf();
              } else {
                // Inline function 'kotlinx.coroutines.assert' call
                var tmp69_safe_receiver = latestState instanceof CompletedExceptionally ? latestState : null;
                tmp_0 = tmp69_safe_receiver == null ? null : tmp69_safe_receiver.o9_1;
              }
              var rootCause = tmp_0;
              node.oc(rootCause);
              var tmp_1;
              if (addedBeforeCompletion) {
                // Inline function 'kotlinx.coroutines.assert' call
                tmp_1 = true;
              } else {
                return NonDisposableHandle_instance;
              }
              tmp = tmp_1;
            }
            if (tmp) {
              tmp$ret$2 = true;
              break $l$block_1;
            }
          }
        } else {
          tmp$ret$2 = false;
          break $l$block_1;
        }
      }
    }
  }
  var added = tmp$ret$2;
  if (added)
    return node;
  var tmp_2 = this.ca();
  var tmp70_safe_receiver = tmp_2 instanceof CompletedExceptionally ? tmp_2 : null;
  node.oc(tmp70_safe_receiver == null ? null : tmp70_safe_receiver.o9_1);
  return NonDisposableHandle_instance;
};
protoOf(JobSupport).u9 = function (exception) {
  throw exception;
};
protoOf(JobSupport).sa = function (cause) {
};
protoOf(JobSupport).ta = function () {
  return false;
};
protoOf(JobSupport).ua = function () {
  return true;
};
protoOf(JobSupport).va = function (exception) {
  return false;
};
protoOf(JobSupport).n9 = function (state) {
};
protoOf(JobSupport).t9 = function (state) {
};
protoOf(JobSupport).toString = function () {
  return this.wa() + '@' + get_hexAddress(this);
};
protoOf(JobSupport).wa = function () {
  return this.v9() + '{' + stateString(this, this.ca()) + '}';
};
protoOf(JobSupport).v9 = function () {
  return get_classSimpleName(this);
};
function boxIncomplete(_this__u8e3s4) {
  _init_properties_JobSupport_kt__68f172();
  var tmp;
  if (!(_this__u8e3s4 == null) ? isInterface(_this__u8e3s4, Incomplete) : false) {
    tmp = new IncompleteStateBox(_this__u8e3s4);
  } else {
    tmp = _this__u8e3s4;
  }
  return tmp;
}
function InactiveNodeList(list) {
  this.mf_1 = list;
}
protoOf(InactiveNodeList).sd = function () {
  return this.mf_1;
};
protoOf(InactiveNodeList).j9 = function () {
  return false;
};
protoOf(InactiveNodeList).toString = function () {
  return get_DEBUG() ? this.mf_1.ef('New') : anyToString(this);
};
function InvokeOnCompletion(handler) {
  JobNode.call(this);
  this.kg_1 = handler;
}
protoOf(InvokeOnCompletion).md = function () {
  return false;
};
protoOf(InvokeOnCompletion).oc = function (cause) {
  return this.kg_1(cause);
};
function InvokeOnCancelling(handler) {
  JobNode.call(this);
  this.pg_1 = handler;
  this.qg_1 = atomic$boolean$1(false);
}
protoOf(InvokeOnCancelling).md = function () {
  return true;
};
protoOf(InvokeOnCancelling).oc = function (cause) {
  if (this.qg_1.atomicfu$compareAndSet(false, true))
    this.pg_1(cause);
};
function ChildHandleNode(childJob) {
  JobNode.call(this);
  this.wf_1 = childJob;
}
protoOf(ChildHandleNode).md = function () {
  return true;
};
protoOf(ChildHandleNode).oc = function (cause) {
  return this.wf_1.na(this.rd());
};
protoOf(ChildHandleNode).oa = function (cause) {
  return this.rd().oa(cause);
};
function IncompleteStateBox(state) {
  this.rg_1 = state;
}
function handlesExceptionF($this) {
  var tmp = $this.ba();
  var tmp75_safe_receiver = tmp instanceof ChildHandleNode ? tmp : null;
  var tmp0_elvis_lhs = tmp75_safe_receiver == null ? null : tmp75_safe_receiver.rd();
  var tmp_0;
  if (tmp0_elvis_lhs == null) {
    return false;
  } else {
    tmp_0 = tmp0_elvis_lhs;
  }
  var parentJob = tmp_0;
  while (true) {
    if (parentJob.ua())
      return true;
    var tmp_1 = parentJob.ba();
    var tmp76_safe_receiver = tmp_1 instanceof ChildHandleNode ? tmp_1 : null;
    var tmp1_elvis_lhs = tmp76_safe_receiver == null ? null : tmp76_safe_receiver.rd();
    var tmp_2;
    if (tmp1_elvis_lhs == null) {
      return false;
    } else {
      tmp_2 = tmp1_elvis_lhs;
    }
    parentJob = tmp_2;
  }
}
function JobImpl(parent) {
  JobSupport.call(this, true);
  this.e9(parent);
  this.ug_1 = handlesExceptionF(this);
}
protoOf(JobImpl).ma = function () {
  return true;
};
protoOf(JobImpl).ua = function () {
  return this.ug_1;
};
var properties_initialized_JobSupport_kt_5iq8a4;
function _init_properties_JobSupport_kt__68f172() {
  if (!properties_initialized_JobSupport_kt_5iq8a4) {
    properties_initialized_JobSupport_kt_5iq8a4 = true;
    COMPLETING_ALREADY = new Symbol('COMPLETING_ALREADY');
    COMPLETING_WAITING_CHILDREN = new Symbol('COMPLETING_WAITING_CHILDREN');
    COMPLETING_RETRY = new Symbol('COMPLETING_RETRY');
    TOO_LATE_TO_CANCEL = new Symbol('TOO_LATE_TO_CANCEL');
    SEALED = new Symbol('SEALED');
    EMPTY_NEW = new Empty(false);
    EMPTY_ACTIVE = new Empty(true);
  }
}
function MainCoroutineDispatcher() {
  CoroutineDispatcher.call(this);
}
protoOf(MainCoroutineDispatcher).toString = function () {
  var tmp0_elvis_lhs = this.xg();
  return tmp0_elvis_lhs == null ? get_classSimpleName(this) + '@' + get_hexAddress(this) : tmp0_elvis_lhs;
};
protoOf(MainCoroutineDispatcher).xg = function () {
  var main = Dispatchers_getInstance().ie();
  if (this === main)
    return 'Dispatchers.Main';
  var tmp;
  try {
    tmp = main.wg();
  } catch ($p) {
    var tmp_0;
    if ($p instanceof UnsupportedOperationException) {
      var e = $p;
      tmp_0 = null;
    } else {
      throw $p;
    }
    tmp = tmp_0;
  }
  var immediate = tmp;
  if (this === immediate)
    return 'Dispatchers.Main.immediate';
  return null;
};
function SupervisorJob(parent) {
  parent = parent === VOID ? null : parent;
  return new SupervisorJobImpl(parent);
}
function SupervisorJobImpl(parent) {
  JobImpl.call(this, parent);
}
protoOf(SupervisorJobImpl).oa = function (cause) {
  return false;
};
function TimeoutCancellationException() {
}
function Unconfined() {
  Unconfined_instance = this;
  CoroutineDispatcher.call(this);
}
protoOf(Unconfined).ae = function (context) {
  return false;
};
protoOf(Unconfined).be = function (context, block) {
  var yieldContext = context.z4(Key_instance_3);
  if (!(yieldContext == null)) {
    yieldContext.dh_1 = true;
    return Unit_instance;
  }
  throw UnsupportedOperationException_init_$Create$('Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.');
};
protoOf(Unconfined).toString = function () {
  return 'Dispatchers.Unconfined';
};
var Unconfined_instance;
function Unconfined_getInstance() {
  if (Unconfined_instance == null)
    new Unconfined();
  return Unconfined_instance;
}
function Key_2() {
}
var Key_instance_3;
function Key_getInstance_2() {
  return Key_instance_3;
}
function Segment() {
}
function ConcurrentLinkedListNode() {
}
function handleUncaughtCoroutineException(context, exception) {
  var _iterator__ex2g4s = get_platformExceptionHandlers().e();
  while (_iterator__ex2g4s.f()) {
    var handler = _iterator__ex2g4s.g();
    try {
      handler.de(context, exception);
    } catch ($p) {
      if ($p instanceof ExceptionSuccessfullyProcessed) {
        var _unused_var__etf5q3 = $p;
        return Unit_instance;
      } else {
        if ($p instanceof Error) {
          var t = $p;
          propagateExceptionFinalResort(handlerException(exception, t));
        } else {
          throw $p;
        }
      }
    }
  }
  try {
    addSuppressed(exception, new DiagnosticCoroutineContextException(context));
  } catch ($p) {
    if ($p instanceof Error) {
      var e = $p;
    } else {
      throw $p;
    }
  }
  propagateExceptionFinalResort(exception);
}
function ExceptionSuccessfullyProcessed() {
}
function get_UNDEFINED() {
  _init_properties_DispatchedContinuation_kt__tnmqc0();
  return UNDEFINED;
}
var UNDEFINED;
function get_REUSABLE_CLAIMED() {
  _init_properties_DispatchedContinuation_kt__tnmqc0();
  return REUSABLE_CLAIMED;
}
var REUSABLE_CLAIMED;
function _get_reusableCancellableContinuation__9qex09($this) {
  var tmp = $this.rb_1.kotlinx$atomicfu$value;
  return tmp instanceof CancellableContinuationImpl ? tmp : null;
}
function DispatchedContinuation(dispatcher, continuation) {
  DispatchedTask.call(this, -1);
  this.nb_1 = dispatcher;
  this.ob_1 = continuation;
  this.pb_1 = get_UNDEFINED();
  this.qb_1 = threadContextElements(this.s4());
  this.rb_1 = atomic$ref$1(null);
}
protoOf(DispatchedContinuation).s4 = function () {
  return this.ob_1.s4();
};
protoOf(DispatchedContinuation).sb = function () {
  return !(this.rb_1.kotlinx$atomicfu$value == null);
};
protoOf(DispatchedContinuation).eh = function () {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = this.rb_1;
  while (true) {
    if (!(this_0.kotlinx$atomicfu$value === get_REUSABLE_CLAIMED()))
      return Unit_instance;
  }
};
protoOf(DispatchedContinuation).ce = function () {
  this.eh();
  var tmp148_safe_receiver = _get_reusableCancellableContinuation__9qex09(this);
  if (tmp148_safe_receiver == null)
    null;
  else {
    tmp148_safe_receiver.fc();
  }
};
protoOf(DispatchedContinuation).uc = function (continuation) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = this.rb_1;
  while (true) {
    var state = this_0.kotlinx$atomicfu$value;
    if (state === get_REUSABLE_CLAIMED()) {
      if (this.rb_1.atomicfu$compareAndSet(get_REUSABLE_CLAIMED(), continuation))
        return null;
    } else {
      if (state instanceof Error) {
        // Inline function 'kotlin.require' call
        // Inline function 'kotlin.require' call
        if (!this.rb_1.atomicfu$compareAndSet(state, null)) {
          var message = 'Failed requirement.';
          throw IllegalArgumentException_init_$Create$(toString(message));
        }
        return state;
      } else {
        // Inline function 'kotlin.error' call
        var message_0 = 'Inconsistent state ' + toString_0(state);
        throw IllegalStateException_init_$Create$(toString(message_0));
      }
    }
  }
};
protoOf(DispatchedContinuation).ub = function (cause) {
  // Inline function 'kotlinx.atomicfu.loop' call
  var this_0 = this.rb_1;
  while (true) {
    var state = this_0.kotlinx$atomicfu$value;
    if (equals(state, get_REUSABLE_CLAIMED())) {
      if (this.rb_1.atomicfu$compareAndSet(get_REUSABLE_CLAIMED(), cause))
        return true;
    } else {
      if (state instanceof Error)
        return true;
      else {
        if (this.rb_1.atomicfu$compareAndSet(state, null))
          return false;
      }
    }
  }
};
protoOf(DispatchedContinuation).jc = function () {
  var state = this.pb_1;
  // Inline function 'kotlinx.coroutines.assert' call
  this.pb_1 = get_UNDEFINED();
  return state;
};
protoOf(DispatchedContinuation).gc = function () {
  return this;
};
protoOf(DispatchedContinuation).x4 = function (result) {
  var state = toState_0(result);
  if (this.nb_1.ae(this.s4())) {
    this.pb_1 = state;
    this.tb_1 = 0;
    this.nb_1.be(this.s4(), this);
  } else {
    $l$block: {
      // Inline function 'kotlinx.coroutines.internal.executeUnconfined' call
      // Inline function 'kotlinx.coroutines.assert' call
      var eventLoop = ThreadLocalEventLoop_getInstance().we();
      if (false && eventLoop.re()) {
        break $l$block;
      }
      var tmp;
      if (eventLoop.qe()) {
        this.pb_1 = state;
        this.tb_1 = 0;
        eventLoop.pe(this);
        tmp = true;
      } else {
        // Inline function 'kotlinx.coroutines.runUnconfinedEventLoop' call
        eventLoop.se(true);
        try {
          this.s4();
          // Inline function 'kotlinx.coroutines.withCoroutineContext' call
          this.qb_1;
          this.ob_1.x4(result);
          $l$loop: while (eventLoop.oe()) {
          }
        } catch ($p) {
          if ($p instanceof Error) {
            var e = $p;
            this.fd(e);
          } else {
            throw $p;
          }
        }
        finally {
          eventLoop.te(true);
        }
        tmp = false;
      }
    }
  }
};
protoOf(DispatchedContinuation).toString = function () {
  return 'DispatchedContinuation[' + this.nb_1.toString() + ', ' + toDebugString(this.ob_1) + ']';
};
function resumeCancellableWith(_this__u8e3s4, result) {
  _init_properties_DispatchedContinuation_kt__tnmqc0();
  var tmp;
  if (_this__u8e3s4 instanceof DispatchedContinuation) {
    // Inline function 'kotlinx.coroutines.internal.DispatchedContinuation.resumeCancellableWith' call
    var state = toState_0(result);
    if (_this__u8e3s4.nb_1.ae(_this__u8e3s4.s4())) {
      _this__u8e3s4.pb_1 = state;
      _this__u8e3s4.tb_1 = 1;
      _this__u8e3s4.nb_1.be(_this__u8e3s4.s4(), _this__u8e3s4);
    } else {
      $l$block: {
        // Inline function 'kotlinx.coroutines.internal.executeUnconfined' call
        // Inline function 'kotlinx.coroutines.assert' call
        var eventLoop = ThreadLocalEventLoop_getInstance().we();
        if (false && eventLoop.re()) {
          break $l$block;
        }
        var tmp_0;
        if (eventLoop.qe()) {
          _this__u8e3s4.pb_1 = state;
          _this__u8e3s4.tb_1 = 1;
          eventLoop.pe(_this__u8e3s4);
          tmp_0 = true;
        } else {
          // Inline function 'kotlinx.coroutines.runUnconfinedEventLoop' call
          eventLoop.se(true);
          try {
            var tmp$ret$4;
            $l$block_0: {
              // Inline function 'kotlinx.coroutines.internal.DispatchedContinuation.resumeCancelled' call
              var job = _this__u8e3s4.s4().z4(Key_instance_2);
              if (!(job == null) && !job.j9()) {
                var cause = job.ga();
                _this__u8e3s4.kc(state, cause);
                // Inline function 'kotlin.coroutines.resumeWithException' call
                // Inline function 'kotlin.Companion.failure' call
                var tmp$ret$2 = _Result___init__impl__xyqfz8(createFailure(cause));
                _this__u8e3s4.x4(tmp$ret$2);
                tmp$ret$4 = true;
                break $l$block_0;
              }
              tmp$ret$4 = false;
            }
            if (!tmp$ret$4) {
              // Inline function 'kotlinx.coroutines.internal.DispatchedContinuation.resumeUndispatchedWith' call
              _this__u8e3s4.ob_1;
              // Inline function 'kotlinx.coroutines.withContinuationContext' call
              _this__u8e3s4.qb_1;
              _this__u8e3s4.ob_1.x4(result);
            }
            $l$loop: while (eventLoop.oe()) {
            }
          } catch ($p) {
            if ($p instanceof Error) {
              var e = $p;
              _this__u8e3s4.fd(e);
            } else {
              throw $p;
            }
          }
          finally {
            eventLoop.te(true);
          }
          tmp_0 = false;
        }
      }
    }
    tmp = Unit_instance;
  } else {
    _this__u8e3s4.x4(result);
    tmp = Unit_instance;
  }
  return tmp;
}
var properties_initialized_DispatchedContinuation_kt_2siadq;
function _init_properties_DispatchedContinuation_kt__tnmqc0() {
  if (!properties_initialized_DispatchedContinuation_kt_2siadq) {
    properties_initialized_DispatchedContinuation_kt_2siadq = true;
    UNDEFINED = new Symbol('UNDEFINED');
    REUSABLE_CLAIMED = new Symbol('REUSABLE_CLAIMED');
  }
}
function DispatchedTask(resumeMode) {
  SchedulerTask.call(this);
  this.tb_1 = resumeMode;
}
protoOf(DispatchedTask).kc = function (takenState, cause) {
};
protoOf(DispatchedTask).tc = function (state) {
  return (state == null ? true : !(state == null)) ? state : THROW_CCE();
};
protoOf(DispatchedTask).cd = function (state) {
  var tmp151_safe_receiver = state instanceof CompletedExceptionally ? state : null;
  return tmp151_safe_receiver == null ? null : tmp151_safe_receiver.o9_1;
};
protoOf(DispatchedTask).ed = function () {
  // Inline function 'kotlinx.coroutines.assert' call
  var fatalException = null;
  try {
    var tmp = this.gc();
    var delegate = tmp instanceof DispatchedContinuation ? tmp : THROW_CCE();
    var continuation = delegate.ob_1;
    // Inline function 'kotlinx.coroutines.withContinuationContext' call
    delegate.qb_1;
    var context = continuation.s4();
    var state = this.jc();
    var exception = this.cd(state);
    var job = exception == null && get_isCancellableMode(this.tb_1) ? context.z4(Key_instance_2) : null;
    if (!(job == null) && !job.j9()) {
      var cause = job.ga();
      this.kc(state, cause);
      // Inline function 'kotlinx.coroutines.resumeWithStackTrace' call
      // Inline function 'kotlin.Companion.failure' call
      var exception_0 = recoverStackTrace(cause, continuation);
      var tmp$ret$1 = _Result___init__impl__xyqfz8(createFailure(exception_0));
      continuation.x4(tmp$ret$1);
    } else {
      if (!(exception == null)) {
        // Inline function 'kotlin.coroutines.resumeWithException' call
        // Inline function 'kotlin.Companion.failure' call
        var tmp$ret$3 = _Result___init__impl__xyqfz8(createFailure(exception));
        continuation.x4(tmp$ret$3);
      } else {
        // Inline function 'kotlin.coroutines.resume' call
        // Inline function 'kotlin.Companion.success' call
        var value = this.tc(state);
        var tmp$ret$5 = _Result___init__impl__xyqfz8(value);
        continuation.x4(tmp$ret$5);
      }
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var e = $p;
      fatalException = e;
    } else {
      throw $p;
    }
  }
  finally {
    var tmp152_safe_receiver = fatalException;
    if (tmp152_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      this.fd(tmp152_safe_receiver);
    }
  }
};
protoOf(DispatchedTask).fd = function (exception) {
  var reason = new CoroutinesInternalError('Fatal exception in coroutines machinery for ' + toString(this) + '. ' + "Please read KDoc to 'handleFatalException' method and report this incident to maintainers", exception);
  handleCoroutineException(this.gc().s4(), reason);
};
function get_isReusableMode(_this__u8e3s4) {
  return _this__u8e3s4 === 2;
}
function get_isCancellableMode(_this__u8e3s4) {
  return _this__u8e3s4 === 1 || _this__u8e3s4 === 2;
}
function dispatch(_this__u8e3s4, mode) {
  // Inline function 'kotlinx.coroutines.assert' call
  var delegate = _this__u8e3s4.gc();
  var undispatched = mode === 4;
  var tmp;
  var tmp_0;
  if (!undispatched) {
    tmp_0 = delegate instanceof DispatchedContinuation;
  } else {
    tmp_0 = false;
  }
  if (tmp_0) {
    tmp = get_isCancellableMode(mode) === get_isCancellableMode(_this__u8e3s4.tb_1);
  } else {
    tmp = false;
  }
  if (tmp) {
    var dispatcher = delegate.nb_1;
    var context = delegate.s4();
    if (dispatcher.ae(context)) {
      dispatcher.be(context, _this__u8e3s4);
    } else {
      resumeUnconfined(_this__u8e3s4);
    }
  } else {
    resume(_this__u8e3s4, delegate, undispatched);
  }
}
function resumeUnconfined(_this__u8e3s4) {
  var eventLoop = ThreadLocalEventLoop_getInstance().we();
  if (eventLoop.qe()) {
    eventLoop.pe(_this__u8e3s4);
  } else {
    // Inline function 'kotlinx.coroutines.runUnconfinedEventLoop' call
    eventLoop.se(true);
    try {
      resume(_this__u8e3s4, _this__u8e3s4.gc(), true);
      $l$loop: while (eventLoop.oe()) {
      }
    } catch ($p) {
      if ($p instanceof Error) {
        var e = $p;
        _this__u8e3s4.fd(e);
      } else {
        throw $p;
      }
    }
    finally {
      eventLoop.te(true);
    }
  }
}
function resume(_this__u8e3s4, delegate, undispatched) {
  var state = _this__u8e3s4.jc();
  var exception = _this__u8e3s4.cd(state);
  var tmp;
  if (!(exception == null)) {
    // Inline function 'kotlin.Companion.failure' call
    tmp = _Result___init__impl__xyqfz8(createFailure(exception));
  } else {
    // Inline function 'kotlin.Companion.success' call
    var value = _this__u8e3s4.tc(state);
    tmp = _Result___init__impl__xyqfz8(value);
  }
  var result = tmp;
  if (undispatched) {
    // Inline function 'kotlinx.coroutines.internal.DispatchedContinuation.resumeUndispatchedWith' call
    var this_0 = delegate instanceof DispatchedContinuation ? delegate : THROW_CCE();
    this_0.ob_1;
    // Inline function 'kotlinx.coroutines.withContinuationContext' call
    this_0.qb_1;
    this_0.ob_1.x4(result);
  } else {
    delegate.x4(result);
  }
}
function ContextScope(context) {
  this.fh_1 = context;
}
protoOf(ContextScope).i9 = function () {
  return this.fh_1;
};
protoOf(ContextScope).toString = function () {
  return 'CoroutineScope(coroutineContext=' + toString(this.fh_1) + ')';
};
function Symbol(symbol) {
  this.gh_1 = symbol;
}
protoOf(Symbol).toString = function () {
  return '<' + this.gh_1 + '>';
};
function startCoroutineCancellable(_this__u8e3s4, fatalCompletion) {
  // Inline function 'kotlinx.coroutines.intrinsics.runSafely' call
  try {
    var tmp = intercepted(_this__u8e3s4);
    // Inline function 'kotlin.Companion.success' call
    var tmp$ret$0 = _Result___init__impl__xyqfz8(Unit_instance);
    resumeCancellableWith(tmp, tmp$ret$0);
  } catch ($p) {
    if ($p instanceof Error) {
      var e = $p;
      dispatcherFailure(fatalCompletion, e);
    } else {
      throw $p;
    }
  }
  return Unit_instance;
}
function startCoroutineCancellable_0(_this__u8e3s4, receiver, completion) {
  // Inline function 'kotlinx.coroutines.intrinsics.runSafely' call
  try {
    var tmp = intercepted(createCoroutineUnintercepted(_this__u8e3s4, receiver, completion));
    // Inline function 'kotlin.Companion.success' call
    var tmp$ret$0 = _Result___init__impl__xyqfz8(Unit_instance);
    resumeCancellableWith(tmp, tmp$ret$0);
  } catch ($p) {
    if ($p instanceof Error) {
      var e = $p;
      dispatcherFailure(completion, e);
    } else {
      throw $p;
    }
  }
  return Unit_instance;
}
function dispatcherFailure(completion, e) {
  // Inline function 'kotlin.Companion.failure' call
  var tmp$ret$0 = _Result___init__impl__xyqfz8(createFailure(e));
  completion.x4(tmp$ret$0);
  throw e;
}
function startCoroutineUndispatched(_this__u8e3s4, receiver, completion) {
  // Inline function 'kotlinx.coroutines.internal.probeCoroutineCreated' call
  var actualCompletion = completion;
  var tmp;
  try {
    // Inline function 'kotlinx.coroutines.withCoroutineContext' call
    actualCompletion.s4();
    // Inline function 'kotlinx.coroutines.internal.probeCoroutineResumed' call
    // Inline function 'kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn' call
    tmp = startCoroutineUninterceptedOrReturnNonGeneratorVersion(_this__u8e3s4, receiver, actualCompletion);
  } catch ($p) {
    var tmp_0;
    if ($p instanceof Error) {
      var e = $p;
      // Inline function 'kotlin.coroutines.resumeWithException' call
      // Inline function 'kotlin.Companion.failure' call
      var tmp$ret$5 = _Result___init__impl__xyqfz8(createFailure(e));
      actualCompletion.x4(tmp$ret$5);
      return Unit_instance;
    } else {
      throw $p;
    }
  }
  var value = tmp;
  if (!(value === get_COROUTINE_SUSPENDED())) {
    // Inline function 'kotlin.coroutines.resume' call
    // Inline function 'kotlin.Companion.success' call
    var value_0 = (value == null ? true : !(value == null)) ? value : THROW_CCE();
    var tmp$ret$7 = _Result___init__impl__xyqfz8(value_0);
    actualCompletion.x4(tmp$ret$7);
  }
}
function createDefaultDispatcher() {
  var tmp;
  if (isJsdom()) {
    tmp = NodeDispatcher_getInstance();
  } else {
    var tmp_0;
    var tmp_1;
    if (!(typeof window === 'undefined')) {
      // Inline function 'kotlin.js.asDynamic' call
      tmp_1 = window != null;
    } else {
      tmp_1 = false;
    }
    if (tmp_1) {
      // Inline function 'kotlin.js.asDynamic' call
      tmp_0 = !(typeof window.addEventListener === 'undefined');
    } else {
      tmp_0 = false;
    }
    if (tmp_0) {
      tmp = asCoroutineDispatcher(window);
    } else {
      if (typeof process === 'undefined' || typeof process.nextTick === 'undefined') {
        tmp = SetTimeoutDispatcher_getInstance();
      } else {
        tmp = NodeDispatcher_getInstance();
      }
    }
  }
  return tmp;
}
function isJsdom() {
  return !(typeof navigator === 'undefined') && navigator != null && navigator.userAgent != null && !(typeof navigator.userAgent === 'undefined') && !(typeof navigator.userAgent.match === 'undefined') && navigator.userAgent.match('\\bjsdom\\b');
}
var counter;
function get_DEBUG() {
  return DEBUG;
}
var DEBUG;
function get_classSimpleName(_this__u8e3s4) {
  var tmp0_elvis_lhs = getKClassFromExpression(_this__u8e3s4).y5();
  return tmp0_elvis_lhs == null ? 'Unknown' : tmp0_elvis_lhs;
}
function get_hexAddress(_this__u8e3s4) {
  // Inline function 'kotlin.js.asDynamic' call
  var result = _this__u8e3s4.__debug_counter;
  if (!(typeof result === 'number')) {
    counter = counter + 1 | 0;
    result = counter;
    // Inline function 'kotlin.js.asDynamic' call
    _this__u8e3s4.__debug_counter = result;
  }
  return ((!(result == null) ? typeof result === 'number' : false) ? result : THROW_CCE()).toString();
}
function NodeDispatcher() {
  NodeDispatcher_instance = this;
  SetTimeoutBasedDispatcher.call(this);
}
protoOf(NodeDispatcher).jh = function () {
  process.nextTick(this.qh_1.oh_1);
};
var NodeDispatcher_instance;
function NodeDispatcher_getInstance() {
  if (NodeDispatcher_instance == null)
    new NodeDispatcher();
  return NodeDispatcher_instance;
}
function ScheduledMessageQueue$processQueue$lambda(this$0) {
  return function () {
    this$0.uh();
    return Unit_instance;
  };
}
function ScheduledMessageQueue(dispatcher) {
  MessageQueue.call(this);
  this.nh_1 = dispatcher;
  var tmp = this;
  tmp.oh_1 = ScheduledMessageQueue$processQueue$lambda(this);
}
protoOf(ScheduledMessageQueue).vh = function () {
  this.nh_1.jh();
};
protoOf(ScheduledMessageQueue).wh = function () {
  setTimeout(this.oh_1, 0);
};
protoOf(ScheduledMessageQueue).xh = function (timeout) {
  setTimeout(this.oh_1, timeout);
};
function w3cSetTimeout(handler, timeout) {
  return setTimeout(handler, timeout);
}
function WindowMessageQueue$lambda(this$0) {
  return function (event) {
    var tmp;
    if (event.source == this$0.ei_1 && event.data == this$0.fi_1) {
      event.stopPropagation();
      this$0.uh();
      tmp = Unit_instance;
    }
    return Unit_instance;
  };
}
function WindowMessageQueue$schedule$lambda(this$0) {
  return function (it) {
    this$0.uh();
    return Unit_instance;
  };
}
function WindowMessageQueue(window_0) {
  MessageQueue.call(this);
  this.ei_1 = window_0;
  this.fi_1 = 'dispatchCoroutine';
  this.ei_1.addEventListener('message', WindowMessageQueue$lambda(this), true);
}
protoOf(WindowMessageQueue).vh = function () {
  var tmp = Promise.resolve(Unit_instance);
  tmp.then(WindowMessageQueue$schedule$lambda(this));
};
protoOf(WindowMessageQueue).wh = function () {
  this.ei_1.postMessage(this.fi_1, '*');
};
function w3cSetTimeout_0(window_0, handler, timeout) {
  return setTimeout_0(window_0, handler, timeout);
}
function w3cClearTimeout(window_0, handle) {
  return window_0.clearTimeout(handle);
}
function w3cClearTimeout_0(handle) {
  return clearTimeout(handle);
}
function setTimeout_0(window_0, handler, timeout) {
  return window_0.setTimeout(handler, timeout);
}
function asCoroutineDispatcher(_this__u8e3s4) {
  // Inline function 'kotlin.js.asDynamic' call
  var tmp0_elvis_lhs = _this__u8e3s4.coroutineDispatcher;
  var tmp;
  if (tmp0_elvis_lhs == null) {
    // Inline function 'kotlin.also' call
    var this_0 = new WindowDispatcher(_this__u8e3s4);
    // Inline function 'kotlin.js.asDynamic' call
    _this__u8e3s4.coroutineDispatcher = this_0;
    tmp = this_0;
  } else {
    tmp = tmp0_elvis_lhs;
  }
  return tmp;
}
function propagateExceptionFinalResort(exception) {
  console.error(exception.toString());
}
function createEventLoop() {
  return new UnconfinedEventLoop();
}
function UnconfinedEventLoop() {
  EventLoop.call(this);
}
protoOf(UnconfinedEventLoop).be = function (context, block) {
  unsupported();
};
function unsupported() {
  throw UnsupportedOperationException_init_$Create$('runBlocking event loop is not supported');
}
function SetTimeoutDispatcher() {
  SetTimeoutDispatcher_instance = this;
  SetTimeoutBasedDispatcher.call(this);
}
protoOf(SetTimeoutDispatcher).jh = function () {
  this.qh_1.xh(0);
};
var SetTimeoutDispatcher_instance;
function SetTimeoutDispatcher_getInstance() {
  if (SetTimeoutDispatcher_instance == null)
    new SetTimeoutDispatcher();
  return SetTimeoutDispatcher_instance;
}
function SetTimeoutBasedDispatcher$scheduleResumeAfterDelay$lambda($continuation, this$0) {
  return function () {
    // Inline function 'kotlin.with' call
    $continuation.bd(this$0, Unit_instance);
    return Unit_instance;
  };
}
function SetTimeoutBasedDispatcher() {
  CoroutineDispatcher.call(this);
  this.qh_1 = new ScheduledMessageQueue(this);
}
protoOf(SetTimeoutBasedDispatcher).be = function (context, block) {
  this.qh_1.ai(block);
};
protoOf(SetTimeoutBasedDispatcher).je = function (timeMillis, continuation) {
  var handle = w3cSetTimeout(SetTimeoutBasedDispatcher$scheduleResumeAfterDelay$lambda(continuation, this), delayToInt(timeMillis));
  invokeOnCancellation(continuation, new ClearTimeout(handle));
};
function MessageQueue() {
  this.rh_1 = ArrayDeque_init_$Create$();
  this.sh_1 = 16;
  this.th_1 = false;
}
protoOf(MessageQueue).yh = function (element) {
  return this.rh_1.m(element);
};
protoOf(MessageQueue).m = function (element) {
  return this.yh((!(element == null) ? isInterface(element, Runnable) : false) ? element : THROW_CCE());
};
protoOf(MessageQueue).zh = function (elements) {
  return this.rh_1.l(elements);
};
protoOf(MessageQueue).l = function (elements) {
  return this.zh(elements);
};
protoOf(MessageQueue).d2 = function () {
  this.rh_1.d2();
};
protoOf(MessageQueue).w1 = function (index) {
  return this.rh_1.w1(index);
};
protoOf(MessageQueue).o = function (index) {
  return this.rh_1.o(index);
};
protoOf(MessageQueue).n = function () {
  return this.rh_1.n();
};
protoOf(MessageQueue).e = function () {
  return this.rh_1.e();
};
protoOf(MessageQueue).h = function () {
  return this.rh_1.h7_1;
};
protoOf(MessageQueue).ai = function (element) {
  this.yh(element);
  if (!this.th_1) {
    this.th_1 = true;
    this.vh();
  }
};
protoOf(MessageQueue).uh = function () {
  try {
    // Inline function 'kotlin.repeat' call
    var times = this.sh_1;
    var inductionVariable = 0;
    if (inductionVariable < times)
      do {
        var index = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0_elvis_lhs = removeFirstOrNull(this);
        var tmp;
        if (tmp0_elvis_lhs == null) {
          return Unit_instance;
        } else {
          tmp = tmp0_elvis_lhs;
        }
        var element = tmp;
        element.ed();
      }
       while (inductionVariable < times);
  }finally {
    if (this.n()) {
      this.th_1 = false;
    } else {
      this.wh();
    }
  }
};
function WindowClearTimeout($outer, handle) {
  this.ni_1 = $outer;
  ClearTimeout.call(this, handle);
}
protoOf(WindowClearTimeout).ic = function () {
  w3cClearTimeout(this.ni_1.pi_1, this.ri_1);
};
function WindowDispatcher$scheduleResumeAfterDelay$lambda($continuation, this$0) {
  return function () {
    // Inline function 'kotlin.with' call
    $continuation.bd(this$0, Unit_instance);
    return Unit_instance;
  };
}
function WindowDispatcher(window_0) {
  CoroutineDispatcher.call(this);
  this.pi_1 = window_0;
  this.qi_1 = new WindowMessageQueue(this.pi_1);
}
protoOf(WindowDispatcher).be = function (context, block) {
  return this.qi_1.ai(block);
};
protoOf(WindowDispatcher).je = function (timeMillis, continuation) {
  var handle = w3cSetTimeout_0(this.pi_1, WindowDispatcher$scheduleResumeAfterDelay$lambda(continuation, this), delayToInt(timeMillis));
  invokeOnCancellation(continuation, new WindowClearTimeout(this, handle));
};
function delayToInt(timeMillis) {
  return coerceIn(timeMillis, new Long(0, 0), new Long(2147483647, 0)).l1();
}
function ClearTimeout(handle) {
  this.ri_1 = handle;
}
protoOf(ClearTimeout).ic = function () {
  w3cClearTimeout_0(this.ri_1);
};
protoOf(ClearTimeout).oc = function (cause) {
  this.ic();
};
protoOf(ClearTimeout).toString = function () {
  return 'ClearTimeout[' + this.ri_1 + ']';
};
function toDebugString(_this__u8e3s4) {
  return toString(_this__u8e3s4);
}
function get_DefaultDelay() {
  var tmp = Dispatchers_getInstance().ee_1;
  return isInterface(tmp, Delay) ? tmp : THROW_CCE();
}
function newCoroutineContext(_this__u8e3s4, context) {
  var combined = _this__u8e3s4.i9().y7(context);
  return !(combined === Dispatchers_getInstance().ee_1) && combined.z4(Key_instance) == null ? combined.y7(Dispatchers_getInstance().ee_1) : combined;
}
function get_coroutineName(_this__u8e3s4) {
  return null;
}
function Dispatchers() {
  Dispatchers_instance = this;
  this.ee_1 = createDefaultDispatcher();
  this.fe_1 = Unconfined_getInstance();
  this.ge_1 = new JsMainDispatcher(this.ee_1, false);
  this.he_1 = null;
}
protoOf(Dispatchers).ie = function () {
  var tmp0_elvis_lhs = this.he_1;
  return tmp0_elvis_lhs == null ? this.ge_1 : tmp0_elvis_lhs;
};
var Dispatchers_instance;
function Dispatchers_getInstance() {
  if (Dispatchers_instance == null)
    new Dispatchers();
  return Dispatchers_instance;
}
function JsMainDispatcher(delegate, invokeImmediately) {
  MainCoroutineDispatcher.call(this);
  this.ti_1 = delegate;
  this.ui_1 = invokeImmediately;
  this.vi_1 = this.ui_1 ? this : new JsMainDispatcher(this.ti_1, true);
}
protoOf(JsMainDispatcher).wg = function () {
  return this.vi_1;
};
protoOf(JsMainDispatcher).ae = function (context) {
  return !this.ui_1;
};
protoOf(JsMainDispatcher).be = function (context, block) {
  return this.ti_1.be(context, block);
};
protoOf(JsMainDispatcher).toString = function () {
  var tmp0_elvis_lhs = this.xg();
  return tmp0_elvis_lhs == null ? this.ti_1.toString() : tmp0_elvis_lhs;
};
function JobCancellationException(message, cause, job) {
  CancellationException_init_$Init$(message, cause, this);
  captureStack(this, JobCancellationException);
  this.wi_1 = job;
}
protoOf(JobCancellationException).toString = function () {
  return protoOf(CancellationException).toString.call(this) + '; job=' + toString(this.wi_1);
};
protoOf(JobCancellationException).equals = function (other) {
  var tmp;
  if (other === this) {
    tmp = true;
  } else {
    var tmp_0;
    var tmp_1;
    var tmp_2;
    if (other instanceof JobCancellationException) {
      tmp_2 = other.message == this.message;
    } else {
      tmp_2 = false;
    }
    if (tmp_2) {
      tmp_1 = equals(other.wi_1, this.wi_1);
    } else {
      tmp_1 = false;
    }
    if (tmp_1) {
      tmp_0 = equals(other.cause, this.cause);
    } else {
      tmp_0 = false;
    }
    tmp = tmp_0;
  }
  return tmp;
};
protoOf(JobCancellationException).hashCode = function () {
  var tmp = imul(imul(getStringHashCode(ensureNotNull(this.message)), 31) + hashCode(this.wi_1) | 0, 31);
  var tmp0_safe_receiver = this.cause;
  var tmp0_elvis_lhs = tmp0_safe_receiver == null ? null : hashCode(tmp0_safe_receiver);
  return tmp + (tmp0_elvis_lhs == null ? 0 : tmp0_elvis_lhs) | 0;
};
function Runnable() {
}
function SchedulerTask() {
}
function identitySet(expectedSize) {
  return HashSet_init_$Create$(expectedSize);
}
function get_platformExceptionHandlers_() {
  _init_properties_CoroutineExceptionHandlerImpl_kt__37d7wf();
  return platformExceptionHandlers_;
}
var platformExceptionHandlers_;
function get_platformExceptionHandlers() {
  _init_properties_CoroutineExceptionHandlerImpl_kt__37d7wf();
  return get_platformExceptionHandlers_();
}
function DiagnosticCoroutineContextException(context) {
  RuntimeException_init_$Init$_0(toString(context), this);
  captureStack(this, DiagnosticCoroutineContextException);
}
var properties_initialized_CoroutineExceptionHandlerImpl_kt_qhrgvx;
function _init_properties_CoroutineExceptionHandlerImpl_kt__37d7wf() {
  if (!properties_initialized_CoroutineExceptionHandlerImpl_kt_qhrgvx) {
    properties_initialized_CoroutineExceptionHandlerImpl_kt_qhrgvx = true;
    // Inline function 'kotlin.collections.mutableSetOf' call
    platformExceptionHandlers_ = LinkedHashSet_init_$Create$();
  }
}
function LockFreeLinkedListHead() {
  LockFreeLinkedListNode.call(this);
}
function LockFreeLinkedListNode() {
  this.td_1 = this;
  this.ud_1 = this;
  this.vd_1 = false;
}
protoOf(LockFreeLinkedListNode).wd = function (node, permissionsBitmask) {
  var prev = this.ud_1;
  var tmp;
  if (prev instanceof ListClosed) {
    tmp = ((prev.aj_1 & permissionsBitmask) === 0 && prev.wd(node, permissionsBitmask));
  } else {
    node.td_1 = this;
    node.ud_1 = prev;
    prev.td_1 = node;
    this.ud_1 = node;
    tmp = true;
  }
  return tmp;
};
protoOf(LockFreeLinkedListNode).ff = function (forbiddenElementsBit) {
  this.wd(new ListClosed(forbiddenElementsBit), forbiddenElementsBit);
};
protoOf(LockFreeLinkedListNode).xd = function () {
  if (this.vd_1)
    return false;
  var prev = this.ud_1;
  var next = this.td_1;
  prev.td_1 = next;
  next.ud_1 = prev;
  this.vd_1 = true;
  return true;
};
protoOf(LockFreeLinkedListNode).yd = function (node) {
  if (!(this.td_1 === this))
    return false;
  this.wd(node, -2147483648);
  return true;
};
function ListClosed(forbiddenElementsBitmask) {
  LockFreeLinkedListNode.call(this);
  this.aj_1 = forbiddenElementsBitmask;
}
function unwrap(exception) {
  return exception;
}
function recoverStackTrace(exception, continuation) {
  return exception;
}
function SynchronizedObject() {
}
function threadContextElements(context) {
  return 0;
}
function CommonThreadLocal() {
  this.xe_1 = null;
}
protoOf(CommonThreadLocal).ye = function () {
  var tmp = this.xe_1;
  return (tmp == null ? true : !(tmp == null)) ? tmp : THROW_CCE();
};
protoOf(CommonThreadLocal).ze = function (value) {
  this.xe_1 = value;
};
function commonThreadLocal(name) {
  return new CommonThreadLocal();
}
//region block: post-declaration
protoOf(JobSupport).y7 = plus;
protoOf(JobSupport).z4 = get_0;
protoOf(JobSupport).x7 = fold;
protoOf(JobSupport).w7 = minusKey_0;
protoOf(CoroutineDispatcher).z4 = get;
protoOf(CoroutineDispatcher).w7 = minusKey;
//endregion
//region block: init
Active_instance = new Active();
Key_instance_1 = new Key_0();
Key_instance_2 = new Key_1();
NonDisposableHandle_instance = new NonDisposableHandle();
Key_instance_3 = new Key_2();
counter = 0;
DEBUG = false;
//endregion
//region block: exports
export {
  delay as delayolwo40i9ucjz,
  CoroutineScope as CoroutineScopefcb5f5dwqcas,
  MainScope as MainScope1gi1r4abhrtmm,
  launch as launch1c91vkjzdi9sd,
};
//endregion

//# sourceMappingURL=kotlinx-coroutines-core.mjs.map
