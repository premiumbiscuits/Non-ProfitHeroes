<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query("SELECT * FROM organization WHERE name=".$_POST['organization_name']);
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>