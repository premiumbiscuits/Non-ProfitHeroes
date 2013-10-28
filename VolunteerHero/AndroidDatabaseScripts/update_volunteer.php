<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query("UPDATE volunteer SET ".$_POST['column']."=".$_POST['value']." WHERE device_id=".$_POST['device_id']);
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>