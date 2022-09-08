<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

 		<meta charset="utf-8">
        <title>A Simple Page with CKEditor 4</title>
        <!-- Make sure the path to CKEditor is correct. -->
        <script src="ckeditor/ckeditor.js"></script>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="save" class="register-form" id="register-form" onsubmit ="return validateForm()">
							Enter Your Email :	
							<input type = "text" name="email" id="email" required="required"><br><br>
							<textarea name="editor1" id="editor1" rows="10" cols="80">
                				This is my textarea to be replaced with CKEditor 4.
           					 </textarea>
								<input type="submit" name="save" id="signup" class="form-submit" value="save" />
            				<script>
                				// Replace the <textarea id="editor1"> with a CKEditor 4
                				// instance, using default configuration.
                				CKEDITOR.replace( 'editor1' );
           		 			</script>
							
	</form>

</body>
</html>