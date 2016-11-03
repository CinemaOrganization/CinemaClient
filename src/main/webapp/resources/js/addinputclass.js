var elements = document.getElementsByTagName("input");
for (var i=0; i<elements.length; i++)
{
  var el = elements[i];
  if (el.getAttribute("type") == "text"){
        el.setAttribute( "class", el.getAttribute("class") + " form-control" );
  }
}