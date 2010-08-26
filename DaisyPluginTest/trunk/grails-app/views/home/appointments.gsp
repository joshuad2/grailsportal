<html>

<head>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type">
<title>Wellness Essentials - Appointments</title>
<script src="../js/dayspa.js" type="text/javascript"></script>
<link href="../css/dayspa.css" rel="stylesheet" type="text/css">
<meta content="main" name="layout" />
</head>

<body>

<div class="main-body-position" style="background-image: url(images/bg-appointments.jpg); background-repeat: no-repeat;">
	<div class="main-body-title-text">
		A SPA THAT GIVES YOU </div>
	<div class="appt-text">
		<div style="direction: ltr;">
			<form action="sendmail-appt" enctype="multipart/form-data" method="POST" name="form1">
				<table align="center" width="80%">
					<tr>
						<td width="35%">Name:</td>
						<td width="65%">
						<input name="name" size="20" type="text"></td>
					</tr>
					<tr>
						<td width="35%">Email Address:</td>
						<td width="65%">
						<input name="email" size="20" type="text"></td>
					</tr>
					<tr>
						<td width="35%">Phone Number:</td>
						<td width="65%">
						<input name="phone" size="20" type="text"></td>
					</tr>
					<tr>
						<td width="35%">Method of Contact:</td>
						<td width="65%">
						<input checked name="method-of-contact" type="radio" value="Email">Email<br>
						<input name="method-of-contact" type="radio" value="Phone">Phone
						</td>
					</tr>
					<tr>
						<td valign="top" width="35%"><br>Select Service:<br><br>
						</td>
						<td width="65%"><br>
						<select id="Service" multiple="multiple" name="service-requested" size="4">
                        ${serviceOptions}
						</select> </td>
					</tr>
					<tr>
						<td width="35%">&nbsp;</td>
						<td width="65%">* Ctrl-Click to select multiple options</td>
					</tr>
					<tr>
						<td width="35%">&nbsp;</td>
						<td width="65%">&nbsp;</td>
					</tr>
					<tr>
						<td width="35%">Date Desired:</td>
						<td width="65%"><select name="month" size="1">
						<option selected="">Month--</option>
						<option>January</option>
						<option>February</option>
						<option>March</option>
						<option>April</option>
						<option>May</option>
						<option>June</option>
						<option>July</option>
						<option>August</option>
						<option>September</option>
						<option>October</option>
						<option>November</option>
						<option>December</option>
						</select> <select name="day" size="1">
						<option selected="">Day--</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
						<option>24</option>
						<option>25</option>
						<option>26</option>
						<option>27</option>
						<option>28</option>
						<option>29</option>
						<option>30</option>
						<option>31</option>
						</select> <select name="year" size="1">
						<option selected="">2009</option>
						<option>2010</option>
						</select> </td>
					</tr>
					<tr>
						<td width="35%">Alternate Date:</td>
						<td width="65%"><select name="alternate-month" size="1">
						<option selected="">Month--</option>
						<option>January</option>
						<option>February</option>
						<option>March</option>
						<option>April</option>
						<option>May</option>
						<option>June</option>
						<option>July</option>
						<option>August</option>
						<option>September</option>
						<option>October</option>
						<option>November</option>
						<option>December</option>
						</select> <select name="alternate-day" size="1">
						<option selected="">Day--</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
						<option>24</option>
						<option>25</option>
						<option>26</option>
						<option>27</option>
						<option>28</option>
						<option>29</option>
						<option>30</option>
						<option>31</option>
						</select> <select name="alternate-year" size="1">
						<option selected="">2009</option>
						<option>2010</option>
						</select> </td>
					</tr>
					<tr>
						<td width="65%">&nbsp;</td>
						<td width="35%">&nbsp;</td>
					</tr>
					<tr>
						<td width="35%">Comments:</td>
						<td width="65%">
						<textarea class="textarea" cols="25" name="Comments" rows="15">
             </textarea> </td>
					</tr>
					<tr>
						<td width="35%">&nbsp;</td>
						<td width="65%">&nbsp;</td>
					</tr>
					<tr>
						<td width="35%">&nbsp;</td>
						<td width="65%">By submitting this form, I agree to the 
						Spa Policies found below:</td>
					</tr>
					<tr>
						<td width="35%">&nbsp;</td>
						<td width="65%"><input type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
			<p align="center"><br></p>
			<p align="center">Spa Policies</p>
			<br>All Appointments Must be Guaranteed With a Credit Card.<br><br>Cancellation 
			Policy: Please notify The Spa of any changes or cancellations 24 hours 
			prior to your appointment. Any cancellations or changes within 24 hours 
			will be subject to a 50% service charge.<br><br>Punctuality: Please 
			arrive 15 minutes prior to your appointment to get prepared for your 
			service. Arriving late may reduce your service time.<br><br>Cellular 
			Phones and Pagers: To maintain a relaxing environment, please turn off 
			all devices while visiting The Spa.<br><br>Please present all Gift Cards 
			and Certificates for validation prior to services rendered.<br><br>All 
			prices are subject to change. Gift Cards expire one year from date of 
			purchase and Certificates expire 3 months from date of purchase. Gift 
			Cards and Gift Certificates can not be redeemed for cash or returned 
			for a refund.<br><br></div>
	</div>
</div>

</body>

</html>
