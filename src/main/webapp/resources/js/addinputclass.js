var elements = document.getElementsByTagName("input");
for (var i=0; i<elements.length; i++)
{
  var el = elements[i];
  if (el.getAttribute("type") != "submit" && el.getAttribute("type") != "checkbox"){
        el.setAttribute( "class", el.getAttribute("class") + " form-control" );
  }
  if (el.getAttribute("type") == "submit"){
          el.setAttribute( "class", el.getAttribute("class") + " btn btn-primary" );
  }
  if (el.getAttribute("type") == "checkbox"){
            el.setAttribute( "class", el.getAttribute("class") + " checkbox" );
    }
}

var elements = document.getElementsByTagName("select");
for (var i=0; i<elements.length; i++)
{
  var el = elements[i];
  el.setAttribute( "class", el.getAttribute("class") + " form-control" );
}

var elements = document.getElementsByTagName("textarea");
for (var i=0; i<elements.length; i++)
{
  var el = elements[i];
  el.setAttribute( "class", el.getAttribute("class") + " form-control" );
}
