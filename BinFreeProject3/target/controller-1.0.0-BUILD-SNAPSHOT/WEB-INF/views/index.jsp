<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%-- <% UserVO userVO = session.getAttribute("userVO"); %> --%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="keywords" content="Bootstrap, Landing page, Template, Registration, Landing">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="author" content="Grayrids">
  <title>Bin-Free Homepage</title>

  <!-- Bootstrap CSS -->
  <!-- css -->
  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/line-icons.css">
  <link rel="stylesheet" href="/resources/css/owl.carousel.css">
  <link rel="stylesheet" href="/resources/css/owl.theme.css">
  <link rel="stylesheet" href="/resources/css/nivo-lightbox.css">
  <link rel="stylesheet" href="/resources/css/magnific-popup.css">
  <link rel="stylesheet" href="/resources/css/animate.css">
  <link rel="stylesheet" href="/resources/css/menu_sideslide.css">
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/responsive.css">

  <script src="https://code.jquery.com/jquery-latest.js"></script>
  <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
  <script>
    componentDidMount(){
      Kakao.init('2a351a8d6b54e936968fb3673fae2a42');
    }
  </script>

</head>

<body>
  <!-- Header Section Start -->
  <header id="slider-area">
    <nav class="navbar navbar-expand-md fixed-top scrolling-navbar bg-white">
      <div class="container">
        <a class="navbar-brand" href="#"><span><img src="/resources/img/logo.png"></span></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
          aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <i class="lni-menu"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto w-100 justify-content-end">
            <li class="nav-item">
              <a class="nav-link page-scroll" href="/review/subscribe">??????</a>
            </li>
            <li class="nav-item">
              <a class="nav-link page-scroll" href="buddy.html">??????</a>
            </li>
            <li class="nav-item">
              <a class="nav-link page-scroll" href="service.html">????????????</a>
            </li>
           
			<c:choose>
				<c:when test="${loginUserVO.userLogin == true}">
		            <li class="nav-item">
		              <a class="nav-link page-scroll" href="user/mypage">${loginUserVO.name} ??? ???????????????</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link page-scroll" href="user/logout">????????????</a>
		            </li>
           		 </c:when>
	            <c:otherwise> 
		            <li class="nav-item">
		              <a class="nav-link page-scroll" href="user/login">?????????</a>
		            </li>
	            </c:otherwise>
            </c:choose>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Main Carousel Section -->
    <div id="carousel-area" style="max-width:100%; height: auto;">
      <div class="carousel-inner" role="listbox">
        <div class="carousel-item active">
          <img src="/resources/img/slider/Recycling_Bin_GIF_Header.gif" width="150%" height="100%" style="padding-top: 60px;" alt="">
          <div class="carousel-caption text-left">
            <h3 class="wow fadeInRight" data-wow-delay="0.2s">
              </h1>
              <h2 class="wow fadeInRight" data-wow-delay="0.4s">????????????</h2>
              <h4 class="wow fadeInRight" data-wow-delay="0.6s">????????? ????????? ??????????????? ????????? ?????? ??? ?????????.</h4>
              <a href="friends.html" class="btn btn-lg btn-common btn-effect wow fadeInRight"
                data-wow-delay="0.9s">????????????</a>
              <a href="buddy.html" class="btn btn-lg btn-border wow fadeInRight" data-wow-delay="1.2s">?????? ????????????</a>
          </div>
        </div>
      </div>
    </div>

  </header>
  <!-- Header Section End -->

  <!-- Services Section Start -->
  <section id="services" class="section">
    <div class="container">
      <div class="item-boxes">
        <div class="row">
          <div class="col-md-6">
            <img src="/resources/img/main/earth1.jpg" style="max-width:100%; height: auto;">
          </div>
          <div class="col-md">
          </div>
          <div class="col-md-4">
            <br><br>
            <h4 style="font-size: 500%; font-family: ????????????; font-weight: bold;">For Earth</h4>
            <br><br>
            <p style="font-size:180%; line-height: 30px; color: gray;"> "????????? ???????????????"</p>
            <p style="font-size:180%; line-height: 50px; color: gray;">???????????? ????????? ?????? ????????? ???!</p><br><br><br>
            <h4 style="font-size:180%; line-height: 10px;">#?????????</h4>
            <p style="font-size:120%; line-height: 30px;">????????? ??????????????? ??????????????? ??? ??? ????????? ??????!<br>?????? ????????? ??????????????? ????????????.</p>
          </div>
          <div class="col-md">
          </div>
        </div>
      </div>
      <div class="item-boxes">
        <div class="row">
          <div class="col-md">
          </div>
          <div class="col-md-4">
            <br><br><br>
            <h4 style="font-size: 500%; font-family: ????????????; font-weight: bold;">For Local</h4>
            <p style="font-size:180%; line-height: 30px; color:gray;">"?????? ????????? ???????????????"</p><br><br><br>
            <h4 style="font-size:180%; line-height: 10px;">#?????????</h4>
            <p style="font-size:120%; line-height: 30px;">?????????19 ????????? ??????????????? ????????? ???????????????????<br> ????????? ????????? ????????? ????????? ?????????.</p>
          </div>
          <div class="col-md">
          </div>
          <div class="col-md-6">
            <img src="/resources/img/main/senior.jpeg" style="max-width:100%; height:auto;">
          </div>
        </div>
      </div>
      <div class="item-boxes">
        <div class="row">
          <div class="col-md-6">
            <img src="/resources/img/main/4.jpeg" style="max-width:100%; height:auto;">
          </div>
          <div class="col-md">
          </div>
          <div class="col-md-4">
            <br><br><br>
            <h4 style="font-size: 500%; font-family: ????????????; font-weight: bold;">For Life</h4>
            <p style="font-size: 180%;line-height: 30px; color:gray;">"????????? ???????????? ???????????????"</p><br><br><br>
            <h4 style="font-size: 180%;">#???????????? #????????????</h4>
            <p style="font-size: 120%; line-height: 30px;">????????? ?????????????????? ???????????? ????????? ?????? ?????????. ???????????? ????????? ?????? ????????? ???!</p>
          </div>
          <div class="col-md">
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Services Section End -->

  <!-- Start Video promo Section -->
  <section class="video-promo section">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-sm-12">
          <div class="video-promo-content text-center">
            <a href="https://www.youtube.com/embed/rU5nV_jmZlg" class="video-popup"><i class="lni-film-play"></i></a>
            <h2 class="wow zoomIn" data-wow-duration="1000ms" data-wow-delay="100ms">B free??? ?????? ???????????? ??????!</h2>
            <p class="wow zoomIn" data-wow-duration="1000ms" data-wow-delay="100ms">??? ????????? ????????? ????????? ?????????.</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Video Promo Section -->

  <!-- Counter Section Start -->
  <div class="counters section bg-defult">
    <div class="container">
      <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-3">
          <div class="facts-item">
            <div class="icon">
              <i class="lni-users"></i>
            </div>
            <div class="fact-count">
              <h3><span class="counter">105</span></h3>
              <h4>?????? ?????? ?????????</h4>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">
          <div class="facts-item">
            <div class="icon">
              <i class="lni-write"></i>
            </div>
            <div class="fact-count">
              <h3><span class="counter">63</span></h3>
              <h4>?????? ????????? ?????????</h4>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">
          <div class="facts-item">
            <div class="icon">
              <i class="lni-emoji-smile"></i>
            </div>
            <div class="fact-count">
              <h3><span class="counter">160</span></h3>
              <h4>???????????? ?????? ?????????</h4>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">
          <div class="facts-item">
            <div class="icon">
              <i class="lni-heart"></i>
            </div>
            <div class="fact-count">
              <h3><span class="counter">98</span>%</h3>
              <h4>?????? ?????????</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Counter Section End -->

  <!-- Testimonial Section Start -->
  <section class="testimonial section">
    <div style="text-align: center; margin-bottom: 40px;">
      <h2 style="font-family: Malgun Gothic ;">B-Family ??????</h2>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
          <div id="testimonials" class="touch-slider owl-carousel">
            <div class="item">
              <div class="testimonial-item">
                <div class="author">
                  <div class="author-info">
                    <h2>?????????</h2>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <!-- <span>Marketing Head Matrix media</span> -->
                  </div>
                </div>
                <div class="content-inner">
                  <p class="description">????????? ???????????? ?????? ??????...</p>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="testimonial-item">
                <div class="author">
                  <div class="img-thumb">
                  </div>
                  <div class="author-info">
                    <h2>??????</h2>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <!-- <span>Marketing Head Matrix media</span> -->
                  </div>
                </div>
                <div class="content-inner">
                  <p class="description">?????? ????????????... ????????? ?????? ?????? ???????????? ?????? ???????????????????????? ??????</p>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="testimonial-item">
                <div class="author">
                  <div class="img-thumb">
                  </div>
                  <div class="author-info">
                    <h2>???????????? ?????? ??????</h2>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star"></i></span>
                    <span><i class="lni-star"></i></span>
                    <!-- <span>Marketing Head Matrix media</span> -->
                  </div>
                </div>
                <div class="content-inner">
                  <p class="description">?????? ???????????? ?????? ????????? ???????????? ??????????????? ??????????????? ?????? ???????????? ?????? ????????? ?????? ?????? </p>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="testimonial-item">
                <div class="author">
                  <div class="img-thumb">
                  </div>
                  <div class="author-info">
                    <h2>?????????</h2>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star"></i></span>
                    <!-- <span>Marketing Head Matrix media</span> -->
                  </div>
                </div>
                <div class="content-inner">
                  <p class="description">?????? ?????? ????????? ??????????????? ????????? ???????????? ???????????? ??? ??? ???????????? ????????????. ????????? ?????????????????? ????????? ???????????? ???????????? ????????? ??????!
                  </p>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="testimonial-item">
                <div class="author">
                  <div class="img-thumb">
                  </div>
                  <div class="author-info">
                    <h2>????????????</h2>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star-filled"></i></span>
                    <span><i class="lni-star"></i></span>
                    <!-- <span>Marketing Head Matrix media</span> -->
                  </div>
                </div>
                <div class="content-inner">
                  <p class="description">???????????? ??????????????? ????????? ????????????????????? ??? ??? 3,900????????? ?????? ?????? ????????????</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Testimonial Section End -->

  <!-- Footer Section Start -->
  <footer>
    <!-- Footer Area Start -->
    <section class="footer-Content">
      <div class="container">
        <div class="row">      
          <div class="col-sm-12">
            <div class="widget">
              <br>
              <h3 class="block-title">Bin-Free</h3>
              <ul class="contact-footer">
                <li>
                  <span>????????? : ???????????? D?????? | ????????? ???????????? : 123-45-67890</span>
                  <span>??????????????? ???????????? : 2021-????????????-0524</span>
                  <span>????????? : D?????? | ????????? : ??????????????? ????????? ???????????? 459(???????????? ??????)</span>
                  <span>?????? : bitcamp@test.com</span>
                </li>
                <li>
                  <span>Copyright??2021 BFree. All rights reserved.</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Footer area End -->

    <!-- Copyright Start  -->
    <div id="copyright">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="site-info float-left">
              <p>Crafted by <a href="#" rel="nofollow">D??????</a></p>
            </div>
            <div class="float-right">
              <ul class="nav nav-inline">
                <li class="nav-item">
                  <a class="nav-link" href="#">????????????</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">?????????????????????</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">????????????</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">????????????????????????</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Copyright End -->

  </footer>
  <!-- Footer Section End -->

  <!-- Go To Top Link -->
  <a href="#" class="back-to-top">
    <i class="lni-arrow-up"></i>
  </a>

  <div id="loader">
    <div class="spinner">
      <div class="double-bounce1"></div>
      <div class="double-bounce2"></div>
    </div>
  </div>

  <!-- jQuery first, then Tether, then Bootstrap JS. -->
  <script src="/resources/js/jquery-min.js"></script>
  <script src="/resources/js/popper.min.js"></script>
  <script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/js/classie.js"></script>
  <!-- <script src="js/color-switcher.js"></script> -->
  <script src="/resources/js/jquery.mixitup.js"></script>
  <script src="/resources/js/nivo-lightbox.js"></script>
  <script src="/resources/js/owl.carousel.js"></script>
  <script src="/resources/js/jquery.stellar.min.js"></script>
  <script src="/resources/js/jquery.nav.js"></script>
  <script src="/resources/js/scrolling-nav.js"></script>
  <script src="/resources/js/jquery.easing.min.js"></script>
  <script src="/resources/js/wow.js"></script>
  <script src="/resources/js/jquery.vide.js"></script>
  <script src="/resources/js/jquery.counterup.min.js"></script>
  <script src="/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="/resources/js/waypoints.min.js"></script>
  <script src="/resources/js/form-validator.min.js"></script>
  <script src="/resources/js/contact-form-script.js"></script>
  <script src="/resources/js/main.js"></script>

</body>

</html>