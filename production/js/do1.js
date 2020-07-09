var rootRef=firebase.database() .ref().child("stockage");
   rootRef.on("child_added",snap => {
  var idll=snap.child("id").val();
  console.log(idll);
   var nom_doc=snap.child("nomDoc").val();
  var nom_domain=snap.child("nomDomain").val();
  var dateretour=snap.child("dateEntree").val();
  var dateparution=snap.child("dateParution").val();
  var qte=snap.child("Qte").val();
   var prix=snap.child("Prix").val();
    var resume=snap.child("Resume").val();
     var poss=snap.child("PossAchat").val();
      var collect=snap.child("Collection").val();
       var nbpage=snap.child("nbrePage").val();
        var editeur=snap.child("editeur").val();
        var avis=snap.child("Avis").val();
   $("#datatable-buttons").DataTable().row.add([
 idll,  nom_doc, nom_domain,collect,prix,poss,dateretour,dateparution,qte,editeur,"<button id=\"delete\" value=\"Delete row\" onclick=\"deleteRow(this);\" class=\"btn btn-danger btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\"  title=\"Delete\"><i class=\"fa fa-trash\"></i></button>",nbpage,resume,avis,
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
                             console.log(msg);

              var rootRef=firebase.database().ref().child("stockage");
              rootRef.orderByKey().equalTo(msg).on("child_added",snap => {
              var childKey = snap.key;
                     rootRef.child(childKey).remove();

});
                  el.parentNode.removeChild(el);


}
else{
   Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Le livre n'est pas encore retourner",
                        });
  }


})
            

    }

}



function add() {
        const nom_doc=document.getElementById('nom_doc');
        const database=firebase.database();
          const rootRef=database.ref('stockage');
          var table = $('#datatable-buttons').DataTable(); 
          const iD=table.column(0).data().length;
          rootRef.child(iD).set({
            id:iD,
           nomDoc:document.getElementById('nom_doc').value,
            nomDomain:document.getElementById('nom_domain').value,
            dateEntree:document.getElementById('single_cal3').value,
            dateParution:document.getElementById('datePar').value,
            Qte:document.getElementById('qte').value,
            Prix:document.getElementById('prix').value,
            Resume:document.getElementById('resume').value,
            editeur:document.getElementById('editeur').value,
            Collection:document.getElementById('collection').value,
            PossAchat:document.getElementById('achat').value,
            nbrePage:document.getElementById('page').value,
            Avis:0
             });
         const nomDoc=document.getElementById('nom_doc');
          const  nomDomain=document.getElementById('nom_domain');
          const  dateEntree=document.getElementById('single_cal3');
          const  Qte=document.getElementById('qte');
          const  Prix=document.getElementById('prix');
                      if((nomDoc=="")||(nomDomain=="")||(Qte=="")||(Prix=="")
                        ||(dateEntree=="")){
                        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Verifier vos donnees",
                        });}
                      else{               
                       var updatess = {};
                      updatess['/stockage/' + ud] = daata;
                          firebase.database().ref().update(updatess);}
          /*rootRef.child(nom_doc.value).set({
            nomDoc:document.getElementById('nom_doc').value,
            nomDomain:document.getElementById('nom_domain').value,
            dateEntree:document.getElementById('single_cal3').value,
            Qte:document.getElementById('qte').value,
            Prix:document.getElementById('prix').value,
        });*/
        }
function update() {
  var id_livre= document.getElementById('idl1').value;
   var nom_doc=document.getElementById('nom_doc1').value;
           var nom_domain=document.getElementById('nom_domain1').value;
            var dateentree =document.getElementById('single_cal31').value;
           var  dateparution=document.getElementById('datePar1').value;
         var   qte=document.getElementById('qte1').value;
            var prix=document.getElementById('prix1').value;
            var resume=document.getElementById('resume1').value;
            var eediteur=document.getElementById('editeur1').value;
            var collection=document.getElementById('collection1').value;
            var possaachat=document.getElementById('achat1').value;
            var nbrep=document.getElementById('page1').value;
            database=firebase.database();
           var stockage = firebase.database().ref().child('stockage');
           var l=false; 
            stockage.once('value', function(snapshoot) {
           var idliv;
           snapshoot.forEach(function(childSnapshoot) {
            var idkey = childSnapshoot.key;
             idliv=idkey;
              if(idliv==id_livre) {
                 l=true;          }
                                         });     
     if(l==false){
      Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Livre est incorrect",
                        });}
    else {    
       stockage.orderByChild('id').equalTo(id_livre).on('child_added', function(snap) {
                              path='stockage/'+ id_livre;
                                if(qte!=""){
                                const newData ={ 
            Qte:qte,
                                  }
                            database.ref(path).update(newData);
                                      reload_page();
                                }
                                 if(prix!=""){
                 const newData ={ 
Prix:prix,
           } 
            database.ref(path).update(newData);
                                      reload_page();}
                               if(dateentree!=""){
                                const newData ={ 
             dateEntree:dateentree,
                 }
                                  database.ref(path).update(newData);
                                      reload_page();}
                                  if(dateparution!=""){
                                const newData ={ 
            dateParution:dateparution,
                     }
                                  database.ref(path).update(newData);
                                      reload_page();}
                                  if(resume!=""){
                                const newData ={ 
            Resume:resume,
                                  }
                                  database.ref(path).update(newData);
                                      reload_page();}
                                  if(editeur!=""){
                                const newData ={ 
            editeur:eediteur,
                                  }
                                  database.ref(path).update(newData);
                                      reload_page();}
                                  if(collection!=""){
                                const newData ={ 
            Collection:collection,
            }
            database.ref(path).update(newData);
                                      reload_page();}   
                                    if(possaachat!=""){
                                const newData ={ 
            PossAchat:possaachat,
                                  }
                                  database.ref(path).update(newData);
                                      reload_page();}
                                    if(nbrep!=""){
                                const newData ={ 
            nbrePage:nbrep 
                                  } 
                                  database.ref(path).update(newData);
                                      reload_page();}  
                            });}});}