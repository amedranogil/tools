<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<Title>Third Party Applications</Title></head>
<body>
<?php

echo <<<EOF
	<div>
		Her er det mulighet til � skrive mer informasjon om hva som st�r p� denne sida.
		
	</div>
EOF;
$projects = simplexml_load_file('projects.xml');

$skyblue = "#76BDDA";
$lightblue = "#ADD8E6";
$counter = 1;
echo '<div style = "width:1000px">';
echo '<table border="1">';

foreach($projects as $project){
	if($counter%2==1){
		$color=$lightblue;
	}else{
		$color=$skyblue;
	}
	$counter++;
	$tags = $project->tags;
	echo <<<EOF
	
		<tr bgcolor=$color><td><a href="$project->url">Name: $project->name</a></td>
		<td>Developer: $project->developer</td>
		<td>Uploaded: $project->date</td>
		<td><a href="$project->svnurl">SVN Repository</a></td></tr>
		<tr bgcolor=$color><td colspan="4">Description: <p>$project->description</p> Tags:
		<ul>
EOF;
	for($i=0; $i<sizeof($tags->tag); $i++){
		echo '<li>';
		echo $tags->tag[$i];
		echo '</li>';
	}
	
	echo '</ul></td></tr>';
}
echo '</table>';
echo '</div>'
?>
</body>
</html>