<?PHP
require_once("./include/membersite_config.php");

if(isset($_POST['submitted']))
{
   if($fgmembersite->RegisterUser())
   {
        $fgmembersite->RedirectToURL("thank-you.html");
   }
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
    <title>Register</title>
    <link rel="STYLESHEET" type="text/css" href="style/fg_membersite.css" />
    <script type='text/javascript' src='scripts/gen_validatorv31.js'></script>
    <link rel="STYLESHEET" type="text/css" href="style/pwdwidget.css" />
    <script src="scripts/pwdwidget.js" type="text/javascript"></script>      
</head>
<body>

<!-- Form Code Start -->
<div id='fg_membersite'>
<form id='register' action='<?php echo $fgmembersite->GetSelfScript(); ?>' method='post' accept-charset='UTF-8'>
<fieldset >
        <legend>Register</legend>
        
        <input type='hidden' name='submitted' id='submitted' value='1'/>
        
        <div class='short_explanation'>* required fields</div>
        <input type='text'  class='spmhidip' name='<?php echo $fgmembersite->GetSpamTrapInputName(); ?>' />
        
        <div><span class='error'><?php echo $fgmembersite->GetErrorMessage(); ?></span></div>
        
        <div class='container'>
            <label for='name' >Organization Name*: </label><br/>
            <input type='text' name='name' id='name' value='<?php echo $fgmembersite->SafeDisplay('name') ?>' maxlength="100" /><br/>
            <span id='register_name_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='street_address'>Street Address*:</label><br/>
            <input type='text' name='street_address' id='street_address' value='<?php echo $fgmembersite->SafeDisplay('street_address')?>' maxlength="100" /><br/>
            <span id='register_street_address_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='city'>City*:</label><br/>
            <input type='text' name='city' id='city' value='<?php echo $fgmembersite->SafeDisplay('city')?>' maxlength="20" /><br/>
            <span id='register_city_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='state'>State*:</label><br/>
            <input type='text' name='state' id='state' value='<?php echo $fgmembersite->SafeDisplay('state')?>' maxlength="2" /><br/>
            <span id='register_state_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='zip'>Zip*:</label><br/>
            <input type='text' name='zip' id='zip' value='<?php echo $fgmembersite->SafeDisplay('zip')?>' maxlength="5" /><br/>
            <span id='register_zip_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='phone'>Phone*:</label><br/>
            <input type='text' name='phone' id='phone' value='<?php echo $fgmembersite->SafeDisplay('phone')?>' maxlength="10" /><br/>
            <span id='register_phone_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='email_address' >Email Address*:</label><br/>
            <input type='text' name='email_address' id='email_address' value='<?php echo $fgmembersite->SafeDisplay('email') ?>' maxlength="100" /><br/>
            <span id='register_email_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <label for='username' >UserName*:</label><br/>
            <input type='text' name='username' id='username' value='<?php echo $fgmembersite->SafeDisplay('username') ?>' maxlength="50" /><br/>
            <span id='register_username_errorloc' class='error'></span>
        </div>
        
        <div class='container' style='height:80px;'>
            <label for='password' >Password*:</label><br/>
            <div class='pwdwidgetdiv' id='thepwddiv' ></div>
            <noscript>
            <input type='password' name='password' id='password' maxlength="50" />
            </noscript>    
            <div id='register_password_errorloc' class='error' style='clear:both'></div>
        </div>
        
        <div class='container'>
            <label for='organization_type_id'>Organization Type*:</label><br/>
            <select name='organization_type_id'>
                <?php
                mysql_connect("localhost", "root", "nonprofitheroes");
                mysql_select_db("VolunteerHero");
                $sql = mysql_query("SELECT id, type FROM organization_type");
                while($row = mysql_fetch_array($sql))
                {
                    echo "<option value=" . $row['id'] . ">" . $row['type'] . "</option>"; 
                }
                ?>
            </select>
            <span id='register_organization_type_id_errorloc' class='error'></span>
        </div>
        
        <div class='container'>
            <input type='submit' name='Submit' value='Submit' />
        </div>
        
        </fieldset>
</form>
<!-- client-side Form Validations:
Uses the excellent form validation script from JavaScript-coder.com-->

<script type='text/javascript'>
// <![CDATA[
    var pwdwidget = new PasswordWidget('thepwddiv','password');
    pwdwidget.MakePWDWidget();
    
    var frmvalidator  = new Validator("register");
    frmvalidator.EnableOnPageErrorDisplay();
    frmvalidator.EnableMsgsTogether();
    frmvalidator.addValidation("name","req","Please provide your name");

    frmvalidator.addValidation("email","req","Please provide your email address");

    frmvalidator.addValidation("email","email","Please provide a valid email address");

    frmvalidator.addValidation("username","req","Please provide a username");
    
    frmvalidator.addValidation("password","req","Please provide a password");

// ]]>
</script>

<!--
Form Code End (see html-form-guide.com for more info.)
-->

</body>
</html>