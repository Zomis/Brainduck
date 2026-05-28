
import {
  println2shhhgwwt4c61 as println,
  toString30pk9tzaqopn as toString,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  getKClassFromExpression3vpejubogshaw as getKClassFromExpression,
  Unit_instance1fbcbse1fwigr as Unit_instance,
} from './kotlin-kotlin-stdlib.mjs';

//region block: imports
//endregion
//region block: pre-declaration
//endregion
function main() {
  var self_0 = self;
  console.log('Worker init!');
  self_0.onmessage = main$lambda(self_0);
}
function main$lambda($self) {
  return function (e) {
    console.log('Worker got message: ' + toString(e.data));
    var tmp;
    if (!(e.data == null)) {
      console.log('Worker message is of type: ' + getKClassFromExpression(ensureNotNull(e.data)).i2());
      tmp = Unit_instance;
    }
    return $self.postMessage(e.data);
  };
}
function mainWrapper() {
  main();
}
mainWrapper();

//# sourceMappingURL=Brainduck-worker.mjs.map
