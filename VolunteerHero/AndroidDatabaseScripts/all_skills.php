<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$sql=mysql_query("SELECT * FROM skill_type ORDER BY type ASC");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>