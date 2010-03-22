	<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
		<g:javascript>
		function showSpinner(){
		  var el = new YAHOO.util.Element('campers');
		  el.setStyle('cursor','wait');
		  }
		function showRegular(){
		  var el= new YAHOO.util.Element('campers');
		  el.setStyle('cursor','default');
		  }
		</g:javascript>
    </head>
    <body>
        <div class="nav">
          <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
        </div>
        <div class="body">
        <div id="ops">
        <h1 style="margin-left:20px;">West Orlando Arts Foundation Operations</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="listProducts">
                <table>
                    <thead>
                        <tr>
                   	        <th>Code</th>
                   	        <th>Description</th>
                   	        <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                                <div id="linkProduct">
                                   <g:remoteLink action="showRemote" id="${productInstance.id}" update="campers"  onLoading="showSpinner();" onLoaded="showRegular();">${fieldValue(bean:productInstance, field:'cd')}</g:remoteLink>
                                </div>
                            </td>
                            <td>${fieldValue(bean:productInstance, field:'dsc')}</td>
                            <td>${fieldValue(bean:productInstance, field:'name')}</td>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Product}.count()" />
            </div>
        </div>
        </br>
          <gui:dialog title="Registrants" 
           modal="true" form="false"
           triggers="[show:[id:'listProducts', on:'click']]" 
           fixedCenter="true">
            <div class="list" id="campers" style="width:800px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>