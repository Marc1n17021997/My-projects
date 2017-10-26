$(document).ready(function()
{
   $.ajax
   ({
       type: "POST",
       url: "displayStudents.php" , 
       success: function(response)
       {
           $("#display").html(response);
       },
       
       error: function()
       {
           $("#display").html("There was an error with Ajax");
       }
   }); 
});