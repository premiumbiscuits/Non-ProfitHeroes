<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query("INSERT INTO attendance VALUES("
.$_POST['volunteer_id'].","
.$_POST['event_id'].","
.$_POST['point_multiplier']
.", NOW())");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>