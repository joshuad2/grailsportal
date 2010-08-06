<html>

<head>
<title><g:layoutTitle default="Portal" /></title>
<gui:resources components="dialog" />
<g:javascript library="prototype" />
<g:javascript>
		function showSpinner(String elementName){
		  var el = new YAHOO.util.Element(elementName);
		  el.setStyle('cursor','wait');
		  }
		function showRegular(String elementName){
		  var el= new YAHOO.util.Element(elementName);
		  el.setStyle('cursor','default');
		  }
  </g:javascript>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}
</style>
<link href="${resource(dir:'css',file:'main.css')}" rel="stylesheet" />
<link href="${resource(dir:'css',file:'cas-main.css')}" rel="stylesheet" />
<g:javascript library="yui" />
<gui:resources components="['menu']" />
<link href="${resource(dir:'images',file:'favicon.ico')}"
	rel="shortcut icon" type="image/x-icon" />
<menu:menuSetup />
<g:layoutHead />
<g:javascript library="application" />
</head>

<body class="yui-skin-sam">
<portal:heading portalName="West Orlando Arts Foundation">
	<shiro:isLoggedIn>
		<br>
		<div>Logged in as: <shiro:principal />( <g:link
			controller="auth" action="signOut">
                        sign out
                    </g:link>)</div>
	</shiro:isLoggedIn>
	<shiro:isNotLoggedIn>
		<br>
		<div id="login"><g:remoteLink controller="auth" action="doLogin"
			update="LoginDialog">(Log in...)</g:remoteLink></div>
	</shiro:isNotLoggedIn>
</portal:heading>
<menu:menuBody>
	<menu:menuContainer>
		<ul class="first-of-type">
		    <shiro:notUser>
			<menu:mainItem itemLabel="signup" text="Signup">
				<menu:subItem controller="auth" action="register"
					text="Register for this site" />
			</menu:mainItem>
			</shiro:notUser>
			<shiro:user>
				<menu:mainItem itemLabel="manageProfile" text="Profile">
					<menu:subItem controller="auth" action="edit" text="Edit Profile" />
				</menu:mainItem>
				<shiro:hasAllRoles in="['Administrator']">
                   <menu:adminMenu portalConfigId="1"/>
				</shiro:hasAllRoles>
				<shiro:hasAllRoles in="['User']">
				<menu:mainItem itemLabel="setup" text="Setup">
						<menu:subItem controller="registration" action="index" text="2010 Registration" />
			    </menu:mainItem>
				</shiro:hasAllRoles>
			</shiro:user>
	</menu:menuContainer>
</menu:menuBody>
<div id="content"
	style="float: center; position: absolute; top: 160px; left: 150px; width: 740px; height: 800px;">
<g:layoutBody>
</g:layoutBody> <gui:dialog title="Attribute Types" modal="true" form="false"
	triggers="[show:[id:'attributeTypeLink', on:'click']]"
	fixedCenter="true">
	<div class="list" id="attributeTypes"
		style="width: 400px; height: 300px; overflow: auto"></div>
</gui:dialog> <gui:dialog title="Login" modal="true" form="false"
	triggers="[show:[id:'login', on:'click']]" fixedCenter="true">
	<div class="dialog" id="LoginDialog"
		style="width: 400px; height: 300px; overflow: auto"></div>
</gui:dialog></div>
</div>
</body>

</html>