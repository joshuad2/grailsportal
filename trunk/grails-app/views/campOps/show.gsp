                 <table>
                    <thead>
                        <tr>
                            <th>Id </th>
                   	        <th>Product</th>                  	    
                   	        <th>Registrant</th>
                   	        <th>Grade</th>
                   	        <th>Status</th>
                   	        <th>Receipt</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${regEvents}" status="i" var="re">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" controller="registrationEvent" id="${re[0].id}">${fieldValue(bean:re[0], field:'id')}</g:link></td>
                            <td>${fieldValue(bean:re[1], field:'product')}</td>
                            <TD>${fieldValue(bean:re[0],field:'registrationFor')}</td>                        
                            <td>${fieldValue(bean:re[0], field:'campergrade')}</td>
                            <td>${fieldValue(bean:re[0], field:'orderRecord.orderStatus')}</td>
                            <td><div id="linkReg">
                                  <g:link action="showFinish" id="${re[0].id}">Show Receipt</g:link></div></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${regEvents}.count()" />
            </div>