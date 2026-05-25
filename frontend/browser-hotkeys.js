// F2 F4
// F8 F9

// lookup table for keycodes
var key = { s: 83 };

document.onkeydown = function (e) {
  // normalize event
  e = e || window.event;

  // detecting multiple keys, e.g: Ctrl + S
  if (e.ctrlKey && !e.altKey && e.keyCode === key.s) {
    // prevent default action
    if (e.preventDefault) {
      e.preventDefault();
    }
    // IE
    e.returnValue = false;
  }
};