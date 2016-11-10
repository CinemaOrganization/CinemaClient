<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="carouselOnTop" class="carousel slide" data-ride="carousel" data-interval="5000">
       <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
       </ol>

       <div class="carousel-inner myInner">
         <div class="item active ">
                <img class="slider" src="/resources/img/carusel/1.jpg" alt="First Slide">
         </div>
         <div class="item ">
                <img class="slider" src="/resources/img/carusel/2.jpg"  alt="Second Slide">
         </div>
         <div class="item ">
                <img class="slider" src="/resources/img/carusel/3.jpg"  alt="Third Slide">
         </div>
       </div>

       <!-- Controls -->
       <a class="left carousel-control" href="#carouselOnTop" data-slide="prev">
         <span class="glyphicon glyphicon-chevron-left"></span>
       </a>
       <a class="right carousel-control" href="#carouselOnTop" data-slide="next">
         <span class="glyphicon glyphicon-chevron-right"></span>
       </a>
    </div>
