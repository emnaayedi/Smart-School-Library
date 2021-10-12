var rootRef=firebase.database()	.ref().child("emprunte");

   rootRef.on("child_added",snap => {
  var id_emprunte=snap.child("emp_id").val();
  var id_livre=snap.child("id_livre").val();
  var id_emprunteur=snap.child("id_emprunteur").val();
  var date_emp=snap.child("date_emp").val();
  var date_retour=snap.child("date_retour").val();
  var nom_livre=snap.child("nom_livre").val();
   var etat=snap.child("etat").val();
  $("#table_body").append("<tr><th>"+ id_emprunte+"</th><th>"+ id_livre+"</th><th>"+id_emprunteur+
  	"</th><th>"+date_emp+"</th><th>"+ date_retour+"</th><th>"+ nom_livre+
  	"</th><th>"+etat+"</th></tr>");

});
 

function add() {
          const emp_id=document.getElementById('emp');
          const a="notRuturned";
          const database=firebase.database();
          const rootRef=database.ref('emprunte');
          rootRef.child(emp_id.value).set({
          	emp_id:document.getElementById('emp').value,
            id_livre:document.getElementById('id_liv').value,
            id_emprunteur:document.getElementById('id_emp').value,
            date_emp:document.getElementById('date_emp').value,
            date_retour:document.getElementById('date_retour').value,
            nom_livre:document.getElementById('nom_livre').value,
            domaine:document.getElementById('domaine').value,
            etat:a
        });
        }


function update() {
        const empd=document.getElementById('idemp');
        const database=firebase.database();
        const rootRef=database.ref('emprunte');
        etat="Returned";
        const newData ={ etat
          }

        rootRef.child(empd.value).update(newData);
        
        }