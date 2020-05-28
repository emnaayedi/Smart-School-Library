
var rootRef=firebase.database().ref().child("horaires");

   rootRef.on("child_added",snap => {
  var jour=snap.child("jour").val();
  var debut=snap.child("debut").val();
  var fin=snap.child("fin").val();

  $("#table_body").append("<tr><th>"+ jour+"</th><th>"+debut+"</th><th>"+fin+"</th></tr>");

});
 

function addh() {
          const jour=document.getElementById('jour');
          const database=firebase.database();
          const rootRef=database.ref('horaires');
          rootRef.child(jour.value).set({
            jour:document.getElementById('jour').value,
            debut:document.getElementById('debut').value,
           fin:document.getElementById('fin').value
        });
        }