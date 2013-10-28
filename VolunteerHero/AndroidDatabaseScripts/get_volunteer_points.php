<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query(
."SELECT organization.name, SUM(event.point_value * attendance.point_multiplier)"
."FROM attendance"
."INNER JOIN event ON event.id = attendance.event_id"
."INNER JOIN organization ON organization.id = event.organization_id"
."WHERE attendance.volunteer_id = ".$_POST['volunteer_id']
."GROUP BY event.organization_id");

while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>
