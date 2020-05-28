<!DOCTYPE html>
<html>
<head>
<title>jQuery Slide Down</title>
<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
<script> 
$(document).ready(function(){
  $("#btn1").click(function(){
    $(".divClass").slideUp("slow",function ()
    {
       $("#textMsg").text("Slide Down completed.");
    });
  });
});
</script>
 
<style> 
.divClass
{
    padding:5px;
    text-align:center;
    background-color:green;
    border:solid 1px;
}
.divClass
{
    padding:50px;
    display:none;
}
</style>
</head>
<body>

<button id="btn1">Click Me to slide down</button>
<div class="divClass"><b>JournalDev</b> <br><br>jQuery SlideDown</div>
<div id="textMsg"></div>

</body>
</html>