<?php
mysql_connect("localhost","root","nonprofitheroes");
mysql_select_db("VolunteerHero");
$volunteer_sql = mysql_query("
	SELECT id 
	FROM volunteer 
	WHERE device_id = ".$_POST['device_id']);
$volunteer = mysql_fetch_assoc($volunteer_sql);

$sql=mysql_query("
	INSERT INTO volunteer_skill 
	VALUES("
		.$volunteer['id'].","
		.$_POST['skill_type_id'].")");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output)); // this will print the output in json
mysql_close();
?>