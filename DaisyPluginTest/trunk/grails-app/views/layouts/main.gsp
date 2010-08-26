<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <title><g:layoutTitle default="Wellness Essentials" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" src="../js/dayspa.js"></script>
        <link href="../css/dayspa.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="${createLinkTo(dir:'../images',file:'favicon.ico')}" type="image/x-icon" />
        <meta http-equiv="Keywords" name="Keywords" content="day spa, spa, orlando day spa, orlando spa, day spa at ashley park, day spa at health central, day spa orlando, ashley park, health central, wellness essentials, central florida day spa, central florida spa, manicure,  pedicure, hair design, chiropractic, acupuncture, swedish massage, deep tissue massage, hot stone massage, pre-natal massage, body wraps, facials, european deep cleansing massage, shiatsu pressure point massage, bridal packages, glycolic and enzyme peels, microdermabrasion, echo2 oxygen, light therapy, ultra sound, anti-aging, oxygen bars, aromatherapy, airbrush tanning, massage, massage therapy, waxing and threading, nutrition, nutritional counseling, weight management, aesthetic medical body services, aesthetic medical facial services, chinese and western herbal medicines, cellulite treatment, gift cards, gift packages">
        <meta name="Description" content="A Winning Combination of Wellness, Rejuvination and Pampering for everyone to enjoy. Wellness Essentials">
        <meta name="robots" content="index, follow"> 
        <g:layoutHead/>
    </head>
 <body class="body">
    <div id="theFrame" style="position:absolute; width:1050px; height:710px; left: 0px; top:0px;" class="frame">
      <img src="../images/dec1.jpg" alt="Wellness Essentials Background" width="1050" height="710"/>
      <div id="Layer10" style="position:absolute; width:90px; height:129px; z-index:10; left: 20px; top: 20px;" class="frame"><img src="../images/index-thumb.jpg" alt="Day Spa Face" width="90" height="129"/></div>
      <div id="Layer11" style="position:absolute; width:90px; height:129px; z-index:10; left: 143px; top: 20px;" class="frame"><img src="../images/p4.jpg" alt="Day Spa Fountain" width="90" height="129"/></div>
      <div id="Layer12" style="position:absolute; width:90px; height:129px; z-index:11; left: 266px; top: 20px;" class="frame"><img src="../images/p6.jpg" alt="Day Spa Green Eyes" width="90" height="129"/></div>
      <div id="Layer13" style="position:absolute; width:90px; height:129px; z-index:12; left: 389px; top: 20px;" class="frame"><img src="../images/stones-thumb.jpg" alt="Day Spa Hot Stone Massage" width="90" height="129"/></div>
      <div id="Layer14" style="position:absolute; width:90px; height:129px; z-index:13; left: 512px; top: 20px;" class="frame"><img src="../images/p3.jpg" alt="Day Spa Waterfall" width="90" height="129"/></div>
      <div id="Layer15" style="position:absolute; width:90px; height:129px; z-index:14; left: 635px; top: 20px;" class="frame"><img src="../images/retail-thumb.jpg" alt="Day Spa Retail Products" width="90" height="129"/></div>
      <div id="Layer16" style="position:absolute; width:90px; height:129px; z-index:13; left: 758px; top: 20px;" class="frame"><img src="../images/lounge-thumb.jpg" alt="Day Spa Lobby" width="90" height="129"/></div>
      <div id="Layer17" style="position:absolute; width:90px; height:129px; z-index:14; left: 879px; top: 20px;" class="frame"><img src="../images/front-thumb.jpg" alt="Day Spa Main Entrance" width="90" height="129"/></div>
 
      <div id="boundary">
	    <ul id="nav">
	    
		 <li>
	     <g:if test="${screen == 'index'}">
	       <li class="cur"><strong>HOME</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="index">HOME</g:link>
	     </g:else>
	      </li>
	     <li>
	     <g:if test="${screen == 'spa'}">
	       <li class="cur"><strong>Spa</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="wellnessEssentials">Spa</g:link>
	     </g:else>
	      </li>
	     <li>
	     <g:if test="${screen == 'dayspa'}">
	       <li class="cur"><strong>The Day Spa</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="dayspa">The Day Spa</g:link>
	     </g:else>
	      </li>
	     <li>
	     <g:if test="${screen == 'club'}">
	       <li class="cur"><strong>W E Club</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="club">W E Club</g:link>
	     </g:else>
	      </li>
	     <li>
	     <g:if test="${screen == 'specials'}">
	       <li class="cur"><strong>Spa Specials</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="specials">Spa Specials</g:link>
	     </g:else>
	      </li>
	      
	     <li>
	     <g:if test="${screen == 'retail'}">
	       <li class="cur"><strong>Retail</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="retail">Retail</g:link>
	     </g:else>
	      </li>
	      
	     <li>
	     <g:if test="${screen == 'appointments'}">
	       <li class="cur"><strong>Appointments</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="appointments">Appointments</g:link>
	     </g:else>
	      </li>
	      
	     <li>
	     <g:if test="${screen == 'contact'}">
	       <li class="cur"><strong>Contact Us</strong></li>
	     </g:if>
	     <g:else>
	       <g:link controller="home" action="contact">Contact Us</g:link>
	     </g:else>
	      </li>
	
	    </ul>
      </div>
      <g:layoutBody/>
      <div class="right-body-contents">
        <div class="right-body-more">MORE</div>
        <div class="right-body-all-in-one">ALL IN ONE PLACE</div>
        <div class="right-body-slide">
          <object type="application/x-shockwave-flash" data="../swf/DaySpa.swf" width="200" height="214">
            <param name="movie" value="../swf/DaySpa.swf" />
            <img src="../images/slide1.jpg" width="200" height="100" alt="" />
          </object>
        </div>
      </div>
 
      <div id="Layer2" style="position:absolute; width:366px; height:19px; z-index:16; left: 281px; top: 627px;" class="copyright">Copyright 2009 Wellness Essentials All rights reserved.</div>
      <div id="Layer3" style="position:absolute; width:700px; height:21px; z-index:17; left: 150px; top: 662px;" class="links">
        <div align="center">
          <g:link controller="home" action="home">Home</g:link>
          <g:link controller=="home" action="wellnessEssentials">Wellness Essentials Spa</g:link>
          <g:link controller="home" action="dayspa">The Day Spa</g:link>
          <g:link controller="home" action="club">Club Wellness Essentials</g:link>
          <g:link controller="home" action="specials">Spa Specials</g:link>
          <g:link controller="home" action="retail">Retail</g:link> 
          <g:link controller="home" action="appointments">Appointments</g:link> 
          <g:link contoller="home" action="contact">Contact</g:link>
          <g:link controller="home" action="policies" target="_blank">Spa Policies</g:link>
  <br>324 Moore Road
  Ocoee, FL 34761
  (407) 877-8707 
  (407) 877-7464 (fax) 
        </div><!-- end of div align="center" -->
      </div><!-- end of Layer3 -->

      <div id="Layer1" style="position:absolute; width:88px; height:35px; z-index:18; left: 22px; top: 624px;">
        <p>
          <a href="http://validator.w3.org/check?uri=referer">
          <img src="../images/valid-html401.gif" alt="Valid HTML" border="0"/>
        </p>
      </div>
   </div>
</body>
</html>
