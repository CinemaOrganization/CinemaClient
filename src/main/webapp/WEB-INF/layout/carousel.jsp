<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="carouselOnTop" class="carousel slide" data-ride="carousel" data-interval="5000">
       <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
       </ol>

       <div class="carousel-inner">
         <div class="item active">
           <img src="resources/img/carusel/1.jpg" width="1200" height="400" alt="First Slide">
         </div>
         <div class="item">
                <img src="resources/img/carusel/2.jpg" width="1200" height="400" alt="Second Slide">
         </div>
         <div class="item">
                <img src="resources/img/carusel/3.jpg" width="1200" height="400" alt="Third Slide">
         </div>
       </div>

       <!-- Controls -->
   <%--    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
         <span class="glyphicon glyphicon-chevron-left"></span>
       </a>
       <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
         <span class="glyphicon glyphicon-chevron-right"></span>
       </a> --%>
    </div>
