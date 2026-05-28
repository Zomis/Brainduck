import {
  MainScope1gi1r4abhrtmm as MainScope,
  CoroutineScopefcb5f5dwqcas as CoroutineScope,
  delayolwo40i9ucjz as delay,
  launch1c91vkjzdi9sd as launch,
} from './kotlinx-coroutines-core.mjs';
import {
  CoroutineImpl2sn3kjnwmfr10 as CoroutineImpl,
  Unit_instance1fbcbse1fwigr as Unit_instance,
  protoOf180f3jzyo7rfj as protoOf,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  isInterface3d6p8outrmvmk as isInterface,
  until1jbpn0z3f8lbg as until,
  toString30pk9tzaqopn as toString,
  Long2qws0ah9gnpki as Long,
  get_COROUTINE_SUSPENDED3ujt3p13qm4iy as get_COROUTINE_SUSPENDED,
  initMetadataForLambda3af3he42mmnh as initMetadataForLambda,
  VOID3gxj6tk5isa35 as VOID,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
initMetadataForLambda(main$lambda$slambda, CoroutineImpl, VOID, [1]);
//endregion
function main() {
  var scope = MainScope();
  var self_0 = self;
  self_0.onmessage = main$lambda(scope, self_0);
}
function main$lambda$slambda($self, $e, resultContinuation) {
  this.vk_1 = $self;
  this.wk_1 = $e;
  CoroutineImpl.call(this, resultContinuation);
}
protoOf(main$lambda$slambda).el = function ($this$launch, $completion) {
  var tmp = this.fl($this$launch, $completion);
  tmp.o4_1 = Unit_instance;
  tmp.p4_1 = null;
  return tmp.u4();
};
protoOf(main$lambda$slambda).e5 = function (p1, $completion) {
  return this.el((!(p1 == null) ? isInterface(p1, CoroutineScope) : false) ? p1 : THROW_CCE(), $completion);
};
protoOf(main$lambda$slambda).u4 = function () {
  var suspendResult = this.o4_1;
  $sm: do
    try {
      var tmp = this.m4_1;
      switch (tmp) {
        case 0:
          this.n4_1 = 4;
          var tmp_0 = this;
          tmp_0.yk_1 = 3;
          this.zk_1 = this.yk_1;
          this.al_1 = until(0, this.zk_1).e();
          this.m4_1 = 1;
          continue $sm;
        case 1:
          if (!this.al_1.f()) {
            this.m4_1 = 3;
            continue $sm;
          }

          this.bl_1 = this.al_1.g();
          var tmp_1 = this;
          tmp_1.cl_1 = this.bl_1;
          this.dl_1 = this.cl_1;
          this.vk_1.postMessage(toString(this.wk_1.data) + this.dl_1);
          this.m4_1 = 2;
          suspendResult = delay(new Long(1000, 0), this);
          if (suspendResult === get_COROUTINE_SUSPENDED()) {
            return suspendResult;
          }

          continue $sm;
        case 2:
          this.m4_1 = 1;
          continue $sm;
        case 3:
          return Unit_instance;
        case 4:
          throw this.p4_1;
      }
    } catch ($p) {
      var e = $p;
      if (this.n4_1 === 4) {
        throw e;
      } else {
        this.m4_1 = this.n4_1;
        this.p4_1 = e;
      }
    }
   while (true);
};
protoOf(main$lambda$slambda).fl = function ($this$launch, completion) {
  var i = new main$lambda$slambda(this.vk_1, this.wk_1, completion);
  i.xk_1 = $this$launch;
  return i;
};
function main$lambda$slambda_0($self, $e, resultContinuation) {
  var i = new main$lambda$slambda($self, $e, resultContinuation);
  var l = function ($this$launch, $completion) {
    return i.el($this$launch, $completion);
  };
  l.$arity = 1;
  return l;
}
function main$lambda($scope, $self) {
  return function (e) {
    return launch($scope, VOID, VOID, main$lambda$slambda_0($self, e, null));
  };
}
function mainWrapper() {
  main();
}
mainWrapper();

//# sourceMappingURL=Brainduck-worker.mjs.map
