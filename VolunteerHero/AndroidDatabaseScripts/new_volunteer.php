<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query("
INSERT INTO volunteer 
VALUES ("
	.$_POST['first_name'].","
	.$_POST['last_name'].","
	.$_POST['email_address'].","
	.$_POST['street_address'].","
	.$_POST['city'].","
	.$_POST['state'].","
	.$_POST['zip'].","
	.$_POST['phone_number'].","
	.$_POST['device_id']
.")");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>