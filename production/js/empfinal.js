
var rootRef=firebase.database().ref().child("emprunte");

   rootRef.on("child_added",snap => {
  var id_emprunte=snap.child("emp_id").val();
  var id_livre=snap.child("id_livre").val();
  var id_emprunteur=snap.child("id_emprunteur").val();
  var date_emp=snap.child("date_emp").val();
  var date_retour=snap.child("date_retour").val();
  var nom_livre=snap.child("nom_livre").val();
   var etat=snap.child("etat").val();
 

  $("#datatable-buttons").DataTable().row.add([
  id_emprunte, id_emprunteur, date_emp, date_retour,nom_livre, etat,
  "<button id=\"up\" value=\"update row\" onclick=\"updateRow(this);\"  class=\"btn btn-success btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Edit\"><i class=\"fa fa-edit\"></i></button>"+
 "    "+"   "+"    "+ "<button id=\"delete\" value=\"Delete row\" onclick=\"deleteRow(this);\" class=\"btn btn-danger btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\"  title=\"Delete\"><i class=\"fa fa-trash\"></i></button>"

  ])

.draw();

});


function deleteRow(el) {
    // while there are parents, keep going until reach TR 
    while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
        el = el.parentNode;
    }
    var table = document.getElementById("datatable-buttons");

    // If el has a parentNode it must be a TR, so delete it
    // Don't delte if only 3 rows left in table
    if (el.parentNode && el.parentNode.rows.length > 0) {
const swalWithBootstrapButtons = Swal.mixin({
  customClass: {
    confirmButton: 'btn btn-success',
    cancelButton: 'btn btn-danger'
  },
  buttonsStyling: false
})

swalWithBootstrapButtons.fire({
  title: 'Are you sure?',
  text: "You won't be able to revert this!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Yes, delete it!',
  cancelButtonText: 'No, cancel!',
  reverseButtons: true
            
}).then((result) => {
  if (result.value) {
    var rowIndex = $(el).closest('tr').index();
               var msg=table.rows[rowIndex+1].cells[0].innerHTML;
               var etat=table.rows[rowIndex+1].cells[5].innerHTML;
               if(etat=="Returned"){
               
                  var rootRef=firebase.database().ref().child("emprunte");
              rootRef.orderByChild('emp_id').equalTo(msg).on("child_added",snap => {
              var childKey = snap.key;
             rootRef.child(childKey).remove();

});
                  el.parentNode.removeChild(el);

        reload_page();

}
else{
   Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Le livre n'est pas encore retourner",
                        });
  }
}

})
            

    }

}

function updateRow(el) {
    // while there are parents, keep going until reach TR 
    while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
        el = el.parentNode;
    }
    var table = document.getElementById("datatable-buttons");

    // If el has a parentNode it must be a TR, so delete it
    // Don't delte if only 3 rows left in table
    if (el.parentNode && el.parentNode.rows.length > 0) {

            Swal.fire({
  title: 'Are you sure?',
  text: "You won't be able to update this!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Yes, update it!'
}).then((result) => {
  if (result.value) {
    var rowIndex = $(el).closest('tr').index();
               var msg=table.rows[rowIndex+1].cells[0].innerHTML;
               var etat=table.rows[rowIndex+1].cells[5].innerHTML;
               console.log(etat);
              var rootRef=firebase.database().ref().child("emprunte");
              if(etat=="notReturned"){
     rootRef.orderByChild('emp_id').equalTo(msg).on("child_added",snap => {
  var ref_id=firebase.database().ref('emprunte/' + msg).child("id_emprunteur");
  var ref_livre=firebase.database().ref('emprunte/' + msg).child("id_livre");
          var nbs;
          var nbe;
        ref_id.on('value', function(snap_id) {
              var idemp = snap_id.val();
              console.log(id_emp);
  var ref_etud=firebase.database().ref('etudiants/' +idemp).child("nb_emp");
          ref_etud.on('value', function(snap_etud) {
            var nbemp = snap_etud.val();
            console.log(nbemp);
            nbe=nbemp+1;
            console.log(nbe);

          });

                var datan = { nb_emp: nbe }
            pathh='etudiants/'+ idemp;
            firebase.database().ref(pathh).update(datan);


});

            ref_livre.on('value', function(snap_id) {
              var id_livre = snap_id.val();
  var ref_livre=firebase.database().ref('stockage/' +id_livre).child("Qte");
          ref_livre.on('value', function(snap_etud) {
            var qte = snap_etud.val();
            nbs=qte+1;
          });

          var data = {Qte: nbs }
                         path='stockage/'+ id_livre;
                     firebase.database().ref(path).update(data);
                     });
         etat="Returned";
         path='emprunte/'+ msg;                    
          const newData ={ etat
             }
     firebase.database().ref(path).update(newData);


            });
   // reload_page();
   
   }

else if(etat=="Returned"){
  Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Le livre est retourne",
                        });
}
        
  }

});

            
    }
}

 

function reload_page() { 
window.location.reload();     
}



function update() {

  var id_livre= document.getElementById('idl').value;
  var id_etudiant=document.getElementById('ide').value;
  const empd=document.getElementById('idemp').value;
database=firebase.database();
  var stockage = firebase.database().ref().child('stockage');
  var etudiant = firebase.database().ref().child('etudiants');
  var e=firebase.database().ref().child('emprunte');

e.once('value', function(snapshot) {
  var a;
  var l=false;
  var ee=false;
  var et=false;

    snapshot.forEach(function(childSnapshot) {
        var id = childSnapshot.key;
           a=id;
           if(a==empd) {
            ee=true;           }
});
        
    stockage.once('value', function(snapshoot) {
        var idliv;
        snapshoot.forEach(function(childSnapshoot) {
            var idkey = childSnapshoot.key;
              idliv=idkey;
              if(idliv==id_livre) {
                 l=true;          }
                                         }); 
        etudiant.once('value', function(snapshooot) {
        var idetud;
        snapshooot.forEach(function(childSnapshooot) {
            var idkeyl = childSnapshooot.key;
              idetud=idkeyl;
              if(idetud==id_etudiant) {
                 et=true;          }
                                         });

      if((ee==false)&&(l==false)&&(et==false)){
            Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunteur,id-Livre et id-Emprunte sont incorrects",
                        });
            }

    else if((ee==false)&&(l==false)&&(et==true)){
        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunte et id-livre sont incorrects",
                        });
    }else if((ee==false)&&(et==false)&&(l==true)){
        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunte et id-Emprunteur sont incorrects",
                        });
    }else if((l==false)&&(et==false)&&(ee==true)){
        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Livre et id-Emprunteur sont incorrects",
                        });
    }else if(ee==false){
        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunte est incorrect",
                        });
    }
    
    else if(l==false){
      Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Livre est incorrect",
                        });}
    else if(et==false){
      Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunteur est incorrect",
                        });}
    

    else if((ee==true)&&(et==true)&&(l==true)){                                     
                                                          
  e.on('child_added',function(snapshot){
    id=snapshot.key;
    if(id==empd){
      var ch=firebase.database().ref('emprunte/' + empd).child("etat");
      var etat;
      ch.on('value', function(sna) {
              var etat_dispo = sna.val();  
              etat=etat_dispo;}); 
            console.log(etat) ;
        if(etat!="Returned"){      
      etudiant.orderByChild('login').equalTo(id_etudiant).on('child_added', function(snap) {
          var reff=firebase.database().ref('etudiants/' + id_etudiant).child("nb_emp");
          var b;        var a;
          reff.on('value', function(snaps) {
                var nbemp = snaps.val();
                b=nbemp+1});

          stockage.orderByChild('id').equalTo(id_livre).on('child_added', function(snap) {
          var ref=firebase.database().ref('stockage/' + id_livre).child("Qte");
          ref.on('value', function(snapshot) {
              var uid = snapshot.val();          
              a=uid+1;
                });

                                console.log(id);
                              etat="Returned";
                              path='emprunte/'+ empd;
                                
                                const newData ={ etat
                                  }

                                database.ref(path).update(newData);

                               var data = {Qte: a  }
                                path='stockage/'+ id_livre;
                                firebase.database().ref(path).update(data);

                                    var datanew = { nb_emp: b }
                                    path='etudiants/'+ id_etudiant;
                                      firebase.database().ref(path).update(datanew);
                                      reload_page();
                           });
   });
        } 
        else {
          Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "le livre est retourne",
                        });
        }
      } });
}
 });});});

}
       
   


 







// var tblUsers = document.getElementById('tbl_users_list');
//   var databaseRef = firebase.database().ref('emprunte/');
//   var rowIndex = 1;
  
//   databaseRef.once('value', function(snapshot) {
//     snapshot.forEach(function(childSnapshot) {
//    var childKey = childSnapshot.key;
//    var childData = childSnapshot.val();
   
//    var row = tblUsers.insertRow(rowIndex);
//    var cellId = row.insertCell(0);
//   var id_livre = row.insertCell(1);
//   var id_emprunteur = row.insertCell(2);
//    var date_emp = row.insertCell(3);
//    var date_retour = row.insertCell(4);
//     var nom_livre = row.insertCell(5);
//    var etat = row.insertCell(6);
//    cellId.appendChild(document.createTextNode(childKey));
//    id_emprunteur.appendChild(document.createTextNode(childData.id_emprunteur));
//    id_livre.appendChild(document.createTextNode(childData.id_livre));
//    date_emp.appendChild(document.createTextNode(childData.date_emp));
//    date_retour.appendChild(document.createTextNode(childData.date_retour));
//    nom_livre.appendChild(document.createTextNode(childData.nom_livre));
//    etat.appendChild(document.createTextNode(childData.etat));
   
//    rowIndex = rowIndex + 1;
//     });
//   });

//     function add(){
  
  
//    var uid = firebase.database().ref().child('emprunte').push().key;
   
//    var data = {
//             emp_id:uid,
//             id_livre:document.getElementById('id_liv').value,
//             id_emprunteur:document.getElementById('id_emp').value,
//             date_emp:document.getElementById('date_emp').value,
//             date_retour:document.getElementById('date_retour').value,
//             nom_livre:document.getElementById('nom_livre').value,
//             domaine:document.getElementById('domaine').value,
//             etat:"notReturned"  }
   
//    var updates = {};
//    updates['/emprunte/' + uid] = data;
//    firebase.database().ref().update(updates);
   
//    reload_page();
//   }




 // function reload_page(){
 //   window.location.reload();
 //  }

// function update() {
//   const empd=document.getElementById('idemp').value;
// const database=firebase.database();

//   var e=firebase.database().ref().child('emprunte/');
//   e.on('child_added',function(snapshot){
//     id=snapshot.key;
//     message=snapshot.val();
//     if(id==empd){
//       etat="Returned";
//       path='emprunte/'+ empd;
        
//         const newData ={ etat
//           }

//         database.ref(path).update(newData);
//         reload_page();
//     }

   
//   });

 
//         //    const empd=document.getElementById('idemp').value;
//         // const database=firebase.database();
//         // const rootRef=database.ref('emprunte');
//         // etat="Returned";
//         // path='emprunte/'+ empd;
//         // if(!path) return;
        
//         // const newData ={ etat
//         //   }

//         // database.ref(path).update(newData);
//         //    reload_page();

//         }






 //var uid = firebase.database().ref().child('emprunte').push().key;

// function add() {

//           var d=new Date();
//           var d1=new Date();
//           var d2=d1.addDays(14);
//          var date_emp = d.getDate()+'/'+(d.getMonth()+1)+'/'+d.getFullYear();
//          var date_ret = d2.getDate()+'/'+(d2.getMonth()+1)+'/'+d2.getFullYear();

//   var id_livre= document.getElementById('id_liv').value;
//   var id_etudiant=document.getElementById('id_emp').value;
//   var livre = firebase.database().ref().child('livres');
//   var etudiant = firebase.database().ref().child('etudiants');
// var ok;

//     etudiant.orderByChild('id').equalTo(id_etudiant).on('child_added', function(snap) {
//       var reff=firebase.database().ref('etudiants/' + id_etudiant).child("nb_emp");
//           var b;
//           reff.on('value', function(snaps) {
//               var nbemp = snaps.val();
//               var nb = snaps.val();
//               if((nbemp==1)&&(nbemp==2)){
//                 b=nbemp-1;
//                 var ok=true
//               }
//               else if(nbemp==0){
//                  Swal.fire({
//                     icon: 'error',
//                     title: 'Oops...',
//                     text: "le nombre de possibilite devient egale 0",
//                         });

                
//               }
//               return ok;
//             });

//                 var datanew = { nb_emp: b }
//                   path='etudiants/'+ id_etudiant;
//                firebase.database().ref(path).update(datanew);
//       if(ok==true){
//       livre.orderByChild('id').equalTo(id_livre).on('child_added', function(snap) {
//           var ref=firebase.database().ref('livres/' + id_livre).child("qte_dispo");
//           var a;
//           ref.on('value', function(snapshot) {
//               var uid = snapshot.val();
//               if((uid==2)&&(uid>2)){
//                 a=uid-1;

//                 var ud = firebase.database().ref().child('emprunte').push().key;
   
//               var daata = {
//             emp_id:ud,
//             id_livre:document.getElementById('id_liv').value,
//             id_emprunteur:document.getElementById('id_emp').value,
//             date_emp: date_emp,
//             date_retour:date_ret,
//             nom_livre:document.getElementById('nom_livre').value,
//             domaine:document.getElementById('domaine').value,
//             etat:"notReturned"  }
   
//                 var updatess = {};
//                       updatess['/emprunte/' + ud] = daata;
//                           firebase.database().ref().update(updatess);
   
  
//               }
//               else if(uid==1){
//                 Swal.fire({
//                     icon: 'error',
//                     title: 'Oops...',
//                     text: "depasser le nombre possible d'emprunt",
//                         });
//               }
//             });
//              console.log(a);

//                 var data = { qte_dispo : a  }
//                   path='livres/'+ id_livre;
//                firebase.database().ref(path).update(data);

//            });
//   }


//   });
//            }


  
          
