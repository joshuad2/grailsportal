<div id="remoteAttributeType">
  <div class="nav" style="width:400px">
            <span class="menuButton"><g:link class="create" action="create">New Attribute Type</g:link></span>
  </div>
  <div id="ops">
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div id="attributeType" style="width:410px">
      <table>
        <thead>
          <tr>
            <g:sortableColumn property="name" title="Name" />
            <th>Category</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${attributeTypeInstanceList}" status="i" var="attributeTypeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td style="width:20%" id="attrTypeShow"><g:remoteLink controller="attributeType" action="remoteShow" update="attrType"  onLoading="showSpinner('attrType');" onLoaded="showRegular('attrType');" id="${attributeTypeInstance.id}">${fieldValue(bean:attributeTypeInstance, field:'name')}</g:remoteLink></td>         
              <td style="width:80%" >${fieldValue(bean:attributeTypeInstance, field:'category')}</td>
            </tr>
          </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons" style="width:400px">
      <g:paginate total="${AttributeType}.count()" />
    </div>
    <gui:dialog title="Attribute"
           modal="true" form="false"
           triggers="[show:[id:'attrTypeShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="attrType" style="width:400px;height:300px;overflow:auto">
           </div>
    </gui:dialog>
  </div>
</div>
