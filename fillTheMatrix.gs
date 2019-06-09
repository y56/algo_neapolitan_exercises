function myFunction() {
  var ss = SpreadsheetApp.getActiveSpreadsheet();  // the current spread sheet
  var sheet = ss.getSheets()[0];                   // the current sheet page
  var i;
  for (i = 2; i <= 28; i++) {
    var j;                          
    for (j = i; j <= 28; j++) {
      var range = sheet.getRange(i, j);  
      var x = range.getValues();     
      sheet.getRange(j, i).setValue(x);   // set new node-ID on the newly generated row
    }
  }
  for (var k = 1; k <= 28; k++) {
    sheet.getRange(k, k).setValue(0);
  }
}
