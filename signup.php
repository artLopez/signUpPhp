<!DOCTYPE html>
<html>
<head>
    <title>Confirmation</title>
    <meta charset  = "utf-8"/>
    <link href="css/styles.css" rel="stylesheet">
    
    <?php
    	function displayAllRecords(){
        	$dbConn = getConnection();
        	$sql = "SELECT userId,firstName,lastName,email,college,gender, year, appTypes,donation FROM iom_participant ORDER BY lastName";
        	$stmt = $dbConn->query($sql);
        	return $stmt;
        }
		
		function appType($id){
			$dbConn = getConnection();
        	$sql = "SELECT q.appType FROM `iom_user_appTypes` p JOIN `iom_appType` q ON p.appTypeId = q.appID WHERE p.userId = ".$id."";
        	$stmt = $dbConn->query($sql);
        	return $stmt;
		}
		
	?>
	
</head>
    
<body>
    <h1>Confirmation Page</h1>
<?php
        //creating database connection
        require '../../includes/hackathon/dbConnection.php';
		$dbConn = getConnection();
        $hasApp = false;
		
        //this should not be used because of sql injection
        //$dbConn -> query ($sql);
        
        echo " <h2>Thank you for signing up ".$_GET['fName']. " " . $_GET['lName'] .". Friendly reminder, Ideas of March will be on March 13-15.</h2>";
        
        echo "<table align = 'center'>";
        echo "<tr> <td colspan = '2' style = 'padding:10px; background-color: #00ff65;' > <b> Here is the information you submitted </b> </td></tr>";
        echo "<tr> <td>Email</td><td><br>"          .$_GET['email'].   "</td> </tr>";
        echo "<tr> <td>College</td><td><br>"        .$_GET['college']. "</td> </tr>";
        echo "<tr> <td>College Level</td> <td><br>" .$_GET['level'].   "</td> </tr>";
        echo "<tr> <td>Gender</td> <td><br>"        .$_GET['gender'].  "</td> </tr>";   
 
        if(isset($_GET['appType'])) 
        {
            $appArray = $_GET['appType'];
            $size = count($appArray);
            $hasApp = true;
            echo "<tr> <td colspan = '2'><br>You selected " . $size . " app types: <br><br> </td></tr>";
            $size++;
            echo "<tr ><td  rowspan = ".$size. ">App Types</td>";
            foreach($appArray as $app)
                echo "<tr><td><br/> $app</td></tr>";
        }
        echo "<tr> <td>Donations</td> <td><br>"  .$_GET['donations'].  "</td> </tr>";
        echo "</table>";

        if($hasApp)
        {
            $sql = "INSERT INTO iom_participant (firstName,lastName,email,college,gender,year,appTypes,donation) VALUES (:fName,:lName,:email,:college,:gender,:level,:appTypes,:donations)";
            $stmt = $dbConn -> prepare($sql);
            $stmt -> execute(array(  ":fName" => $_GET['fName'],
                                     ":lName" => $_GET['lName'],
                                     ":level" => $_GET['level'],
                                     ":email" =>$_GET['email'],
                                     ":college"  => $_GET['college'],
                                     ":gender"   => $_GET['gender'],
                                     ":level"    => $_GET['level'],
                                     ":appTypes" => implode(" ",$_GET['appType']),
                                     ":donations" => $_GET['donations']
                                    ));
          $participantID = $dbConn->lastInsertId();
		  $appTypes = $_GET['appType']; 
		  
		  
		  foreach($appTypes as $app){
		  	$sql = "INSERT INTO iom_user_appTypes VALUES ($participantID, $app)";
			$stmt = $dbConn->prepare($sql);
			$stmt->execute();
		  }
        }
        else{
            $sql = "INSERT INTO iom_participant (firstName,lastName,email,college,gender,year,donation) VALUES (:fName,:lName,:email,:college,:gender,:level,:donations)";
            $stmt = $dbConn -> prepare($sql);
            $stmt -> execute(array(  ":fName"    => $_GET['fName'],
                                 	 ":lName"    => $_GET['lName'],
                                 	 ":level"    => $_GET['level'],
                                 	 ":email"    =>$_GET['email'],
                                 	 ":college"  => $_GET['college'],
                                 	 ":gender"   => $_GET['gender'],
                                 	 ":level"    => $_GET['level'],
									 ":donations" => $_GET['donations']
									 ));
        }
		?>
	
		<h1>Hackathon Participants</h1>
		
		<table  align = 'center'>
			<tr><td>First Name</td> <td>Last Name</td> <td>Email</td> <td>College</td> <td>Gender</td> <td>Year</td> <td>AppTypes</td><td>Donations</td>
			<?php
			$allParticipants = displayAllRecords();
			
			foreach($allParticipants as $p)
			{
				$appNames = appType(intval($p['userId']));
				
				echo "<tr><td>" . $p['firstName'] . "</td><td>". $p['lastName'] . "</td><td>" . $p['email'] . "</td><td>" . $p['college'] . "</td> 
				<td>" . $p['gender'] . "</td><td>" . $p['year'] . "</td><td>";
				
				foreach($appNames as $type){
					echo " ".$type['appType']. " ";
					
				}
				echo "</td><td>$ " . $p['donation'] ."";
			}
			 
			?>
		</table>
    
        <form action="index.php">
            <div class = "button1" >
                <input id = "btn" type="submit" value="Return to Registration Page" />
            </div>
        </form>
    </body>
</html>