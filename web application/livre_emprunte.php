<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="images/favicon.ico" type="image/ico" />

        <script src='https://kit.fontawesome.com/a076d05399.js'></script>

<link rel="stylesheet" type="text/css" href="">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
        <!-- TODO: Se passer de FontAwesome. -->
    <title>Smart Library </title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
     <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- bootstrap-datetimepicker -->
    <link href="../vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- bootstrap-datetimepicker -->
    <link href="../vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
       <!-- Datatables -->
    
    <link href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
    <!-- Ion.RangeSlider -->
    <link href="../vendors/normalize-css/normalize.css" rel="stylesheet">
    <link href="../vendors/ion.rangeSlider/css/ion.rangeSlider.css" rel="stylesheet">
    <link href="../vendors/ion.rangeSlider/css/ion.rangeSlider.skinFlat.css" rel="stylesheet">
    <!-- Bootstrap Colorpicker -->
    <link href="../vendors/mjolnic-bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col menu_fixed mCustomScrollbar _mCS_1 mCS-autoHide" style="overflow: visible;">
          <div class="left_col">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.php" class="site_title"><i class="fa fa-paw"></i> <span>Smart Libray</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/logo1.jpg"  class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <br>
                <span><h4>Welcome</h4></span>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
           <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
                  <li ><a href="index.php" ><i class="fa fa-home"></i> Home </a>
                    
                  </li>
                  <li><a><i class="fa fa-edit"></i> Bibliotheque <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a><strong>Salle de Lecture</strong> <i class="fa fa-plus"></i></a>
                      <ul id="fct" class="nav child_menu">
                      <li><a href="#etat">Etat</a></li>
                      <li><a href="#calendrier">Regles</a></li>

                    </ul>
                  </li>
                      <li><a><strong>Librairie</strong> <i class="fa fa-plus"></i></a>
                      <ul id="fct" class="nav child_menu">
                      <li><a href="document.php">Documents</a></li>
                      <li><a href="livre_emprunte.php">Empruntes</a></li>

                 
                    </ul>
                  </li>
                      
                    </ul>
                  </li>
                   <li ><a href="reclamation.php" ><i class="fa big-icon fa-envelope icon-wrap"></i> Reclamation </a>
                    
                  </li>
                      
                  
                 <!--  <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="general_elements.html">General Elements</a></li>
                      <li><a href="media_gallery.html">Media Gallery</a></li>
                      <li><a href="typography.html">Typography</a></li>
                      <li><a href="icons.html">Icons</a></li>
                      <li><a href="glyphicons.html">Glyphicons</a></li>
                      <li><a href="widgets.html">Widgets</a></li>
                      <li><a href="invoice.html">Invoice</a></li>
                      <li><a href="inbox.html">Inbox</a></li>
                      <li><a href="calendar.html">Calendar</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>

                   <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="projects.html">Projects</a></li>
                      <li><a href="project_detail.html">Project Detail</a></li>
                      <li><a href="contacts.html">Contacts</a></li>
                      <li><a href="profile.html">Profile</a></li>
                    </ul>
                  </li>

                  <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="login.php">Login Page</a></li>
                      <li><a href="pricing_tables.html">Pricing Tables</a></li>
                    </ul>
                  </li> -->
                </ul>
              </div>
                    </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons >
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" >
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            </menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">

              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              <nav class="nav navbar-nav">
              <ul class=" navbar-right">
                <li class="nav-item dropdown open" style="padding-left: 15px;">
                  <a href="javascript:;" class="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                    <img src="images/logo1.jpg" alt="">Log Out
                  </a>
                  <div class="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                 <a class="dropdown-item"  onclick="signout()"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                  </div>
                </li>

         <li role="presentation" class="nav-item dropdown open">
                  <a href="javascript:;" class="dropdown-toggle info-number" id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">
      <i class="fa fa-bell-o icon-wrap"></i>
                    <span  id="number_msg" > </span>
                  </a>
                  <ul class="dropdown-menu list-unstyled msg_list" role="menu" aria-labelledby="navbarDropdown1" id="ida">   
                    
                </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
          <div class="row" style="display: inline-block;" >
          <div class="tile_count"style="width: 1200px;">
   <div class="col-md-3 col-sm-5  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-user" aria-hidden="true" style="margin-right: 5px;"></i>Nombre d'etudiants </span>
              <div class="count" id="nb_etud"></div>
                <button type="button" class="btn btn-success btn-xs"><a href="contacts.php" style="color: white;">modifier</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>
            
            
           
            <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fas fa-chair"  style="margin-right: 3px;"></i> Nombre de chaises</span>
              <div class="count" id="nb_chaises"></div>
              <button type="button" class="btn btn-info">modifier<span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
              
            </div>
             <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-book"style="margin-right: 5px;" ></i>Documents </span>
              <div class="count" id="document"></div>
                <button type="button" class="btn btn-success btn-xs"><a href="document.php" style="color: white;">plus d'infos</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>

            <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-book"style="margin-right: 5px;"></i>Livre Emprunte </span>
              <div class="count" id="liv_emp"></div>
                <button type="button" class="btn btn-info"><a href="livre_emprunte.php" style="color: white;">Ajouter</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>
             </div>
          </div>
        <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2><i  class="fa fa-book" aria-hidden="true" style="margin-right: 20px;"></i>Documents<small>Stockage et empruntes</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                   
<li><a class="close-link" style="margin-left: 15px"><i class="fa fa-times" ></i></a>                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                          <div class="card-box table-responsive">
                          <div id="datatable-buttons_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap no-footer">
                  <table id="datatable-buttons" class="table table-striped table-bordered dataTable no-footer dtr-inline" style="width: 100%;" role="grid" aria-describedby="datatable-buttons_info">
                                        <div class="row">
                  <div class="col-sm-6">
                    <div class="mb-md">
                      <button type="button" id="add_button" data-toggle="modal" data-target="#userModal" class="btn btn-info btn-lg"><i class="fa fa-plus" style="margin-right: 10px;" ></i>Ajouter</button>

                      <!-- <button type="button" id="add_button" data-toggle="modal" data-target="#userModalReturn"  class="btn btn-secondary btn-lg btn-danger"><i class="fas fa-minus" style="margin-right: 10px;" ></i>Return</button> -->
                    </div>
                  </div>
                </div>
    <div id="userModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal">&times;</button>
       <h4 class="modal-title" id="myModalLabel" >Ajouter une emprunte</h4>
      </div>
      <div class="modal-body">
       
       <label>id-Livre</label>
       <input type="text" name="reference" id="id_liv" class="form-control"  />
       <br />
        <label>id-Emprunteur</label>
       <input type="text" name="reference" id="id_emp" class="form-control"  />
       <br />
        
        
       
      </div>
      <div class="modal-footer">
       <input type="hidden" name="id-Emprunteur" id="id-Emprunteur" />
       <input type="hidden" name="operation" id="operation" />
      

        <button id="addid" type="button"  data-dismiss="modal" class="btn btn-success" onclick="adde()">Add</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
       </div>
     </div>
      </div>

<!--       <div id="userModalReturn" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal">&times;</button>
       <h4 class="modal-title" id="myModalLabel" >Retourner une emprunte</h4>
      </div>
      <div class="modal-body">
       
       <label>id-Emprunte</label>
       <input type="text" name="reference" id="idemp" class="form-control"  />
       <br />
       
       <label>id-livre</label>
       <input type="text"  id="idl" class="form-control"  />
       <br /> 
       
       <label>id-Emprunteur</label>
       <input type="text"  id="ide" class="form-control"  />
       <br />     
       
       
      </div>
      <div class="modal-footer">
       <input type="hidden" name="id-Emprunteur" id="id-Emprunteur" />
       <input type="hidden" name="operation" id="operation" />
      

        <button type="button"  data-dismiss="modal" class="btn btn-success" onclick="update()">Return</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
       </div>
     </div>
      </div> -->


      
      <thead >
         <tr role="row">
            
            
            <th class="sorting_asc" tabindex="0"  rowspan="1" colspan="1" style="width: 120px;" aria-sort="ascending" aria-label="Name: activate to sort column descending">id-Emprunte</th>
            
          

            <th class="sorting_asc" tabindex="0"  rowspan="1" colspan="1" style="width: 110px;" aria-sort="ascending" aria-label="Name: activate to sort column descending">id-Emprunteur</th>

            <!-- <th class="sorting" tabindex="0" aria-controls="datatable-buttons" rowspan="1" colspan="1" style="width: 60px;" aria-label="Position: activate to sort column ascending">Nom-Emprunteur</th> -->

            <th class="sorting" tabindex="0"  rowspan="1" colspan="1" style="width: 50px;" aria-label="Office: activate to sort column ascending">Date-Emprunte</th>         

           <th class="sorting" tabindex="0"  rowspan="1" colspan="1" style="width: 50px;" aria-label="Office: activate to sort column ascending">Date-retour</th>


            <th class="sorting" tabindex="0"  rowspan="1" colspan="1" style="width: 52px;" aria-label="Start date: activate to sort column ascending">Nom-livre</th>

            <th class="sorting" tabindex="0"  rowspan="1" colspan="1" style="width: 52px;" aria-label="Start date: activate to sort column ascending">Etat</th>

            <th class="sorting" tabindex="0"  rowspan="1" colspan="1" style="width: 52px;" aria-label="Start date: activate to sort column ascending">Action</th>



            
          </tr>
                      </thead>
                      <tbody >
                     
                      </tbody>
                    </table>
                  </div></div></div></div></div><div class="dataTables_paginate paging_simple_numbers" id="datatable-buttons_paginate"></div></div>
                  </div>
                </div>
              </div>
            </div>
                </div>
              </div></div></div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

      <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

       <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase.js"></script>

      <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase-database.js"></script>

      <script>
        // Your web app's Firebase configuration
        var firebaseConfig = {
              apiKey: "AIzaSyBiUHy07aEruI_ZgBmg-A8ez6bBc-W1gtw",
              authDomain: "smart-library1.firebaseapp.com",
              databaseURL: "https://smart-library1.firebaseio.com",
              projectId: "smart-library1",
              storageBucket: "smart-library1.appspot.com",
              messagingSenderId: "215824460430",
              appId: "1:215824460430:web:6d9b13ab3c7629b377b7df",
              measurementId: "G-BC9C4EV24W"
        };
        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);
        

 var nb_etud=firebase.database().ref('biblio/').child("nb_etud");
      nb_etud.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_etud").append("<div>"+ etat_dispo+"</div>");
            }); 

   var nb_ch=firebase.database().ref('biblio/').child("nb_chaise");
      nb_ch.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_chaises").append("<div>"+ etat_dispo+"</div>");
            });

       var doc=firebase.database().ref('stockage/');
      doc.on('value', function(sna) {
              var etat_dispo = sna.numChildren(); 
              $("#document").append("<div>"+ etat_dispo+"</div>");
    
            });

       var liv_emp=firebase.database().ref('emprunte/');
      liv_emp.on('value', function(sna) {
              var etat_dispo = sna.numChildren(); 

              $("#liv_emp").append("<div>"+ etat_dispo+"</div>");

            });



      var test=firebase.database().ref().child("emprunte");
  test.on("value", function(snapshot){
          var nb=0;
          var t=firebase.database().ref().child("emprunte");
          t.on("child_added",snap => {
             var id_emprunte=snap.child("emp_id").val();
             var date=snap.child("date_retour").val();
             var test=new Date(date);
             var j_emp=test.getDate(); var m_emp=test.getMonth()+1; var y_emp=test.getFullYear();
             var etat=snap.child("etat").val();
             var d=new Date();
            // var j=d.getDate(); var m=d.getMonth()+1; var y=d.getFullYear();
       var j=1;var m=6;var y=2020;
            if ((((j_emp<d)&&((m==m_emp)||(m_emp<m))&&(y==y_emp)))&&(etat=="notReturned")) {
                  nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if((j_emp==31)&&((m_emp==1)||(m_emp==3)||(m_emp==5)||(m_emp==7)||(m_emp==8)||(m_emp==10)||(m_emp==12))&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if((j_emp==30)&&((m_emp==4)||(m_emp==6)||(m_emp==9)||(m_emp==11))&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if(((j_emp==28)||(j_emp==29))&&(m_emp==2)&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
 }
    
});
      
             $("#number_msg").append("<span class=\"badge bg-green\">" + nb + "</span>");

});




  function adde(){

          var d=new Date();
          var d1=new Date();
          var d2=d1.addDays(14);
          var j=d.getDate(); var m=d.getMonth()+1; 
             if (j < 10) { 
                 j ="0" + j; 
                          } 
                if (m < 10) { 
                 m = "0" + m; 
                  } 
             var date_emp = j+'/'+m+'/'+d.getFullYear();

           var jj=d2.getDate(); var mm=d2.getMonth()+1; 
             if (jj < 10) { 
                 jj = "0" + jj; 
                          } 
                if (mm < 10) { 
                 mm = "0" + mm; 
                  } 
           var date_ret = jj+'/'+mm+'/'+d2.getFullYear();


  var id_livre= document.getElementById('id_liv').value;
  var id_etudiant=document.getElementById('id_emp').value;

  var stockage = firebase.database().ref().child('stockage');
  var etudiant = firebase.database().ref().child('etudiants');
    var ok=false;

var testt=firebase.database().ref('stockage/');
var test=firebase.database().ref('etudiants/');
test.once('value', function(snapshot) {
  var a;
  var b=false;
  var bb=false;

    snapshot.forEach(function(childSnapshot) {
        var id = childSnapshot.key;
           a=id;
           if(a==id_etudiant) {
                 b=true;           }
});
        
    testt.once('value', function(snapshoot) {
        var idliv;
        snapshoot.forEach(function(childSnapshoot) {
            var idkey = childSnapshoot.key;
              idliv=idkey;
              if(idliv==id_livre) {
                 bb=true;          


               }
                                                  });
                                            
     if((b==false)&&(bb==false)){
            Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunteur et id-Livre sont incorrects",
                        });
            }
    else if(b==false){
        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Emprunteur est incorrect",
                        });
    }
    
    else if(bb==false){
      Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Livre est incorrect",
                        });}
   
    

    else if((b==true)&&(bb==true)){


    etudiant.orderByChild('login').equalTo(id_etudiant).on('child_added', function(snap) {
      var reff=firebase.database().ref('etudiants/' + id_etudiant).child("nb_emp");
          var b;
          reff.on('value', function(snaps) {
              var nbemp = snaps.val();
              b=nbemp});
            if(b==0)  {
                   Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "le nombre de possibilite etudiants egale 0",
                        });}
            else if((b==1)||(b==2)){
                b=b-1;
                ok=true;
            }
            

      if(ok==true){
 stockage.orderByChild('id').equalTo(id_livre).on('child_added', function(snap) {
          var ref=firebase.database().ref('stockage/' + id_livre).child("Qte");
          var ref1=firebase.database().ref('stockage/' + id_livre).child("nomDoc");
          var a;
          var nom;
          ref.on('value', function(snapshot) {
              var uid = snapshot.val();          
              a=uid;
                });
          ref1.on('value', function(snapshot) {
              var nom1 = snapshot.val();          
              nom=nom1;
              console.log(nom);
                });
             if(a==1){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "le nombre de livres disponibles egale à 1",
                        });
              }


            else  if(a>1){
                a=a-1;

                var ud = firebase.database().ref().child('emprunte').push().key;
   
              var daata = {
            emp_id:ud,
            id_livre:document.getElementById('id_liv').value,
            id_emprunteur:document.getElementById('id_emp').value,
            date_emp:date_emp,
            date_retour:date_ret,
            nom_livre:nom,
            etat:"notReturned"  }
   
                var updatess = {};
                      updatess['/emprunte/' + ud] = daata;
                          firebase.database().ref().update(updatess);
   
  
              
              
             console.log(a);

                var data = { Qte : a ,
                }
                  path='stockage/'+ id_livre;
               firebase.database().ref(path).update(data);

            var datanew = { nb_emp: b }
                  path='etudiants/'+ id_etudiant;
               firebase.database().ref(path).update(datanew);}
           });
  }


  });
           }

    
   
});

});
}


      
</script>

  <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script src="js/empfinal.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="../vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="../vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="../vendors/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="../vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="../vendors/Flot/jquery.flot.js"></script>
    <script src="../vendors/Flot/jquery.flot.pie.js"></script>
    <script src="../vendors/Flot/jquery.flot.time.js"></script>
    <script src="../vendors/Flot/jquery.flot.stack.js"></script>
    <script src="../vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="../vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="../vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="../vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
      <!-- jQuery Knob -->
    <script src="../vendors/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- bootstrap-datetimepicker -->    
    <script src="../vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>
     <!-- Ion.RangeSlider -->
    <script src="../vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
     <!-- Datatables -->
    <script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="../vendors/jszip/dist/jszip.min.js"></script>
    <script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="../vendors/pdfmake/build/vfs_fonts.js"></script>
     <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
     <!-- ECharts -->
    <script src="../vendors/echarts/dist/echarts.min.js"></script>
    <script src="../vendors/echarts/map/js/world.js"></script>
    <!-- morris.js -->
    <script src="../vendors/raphael/raphael.min.js"></script>
    <script src="../vendors/morris.js/morris.min.js"></script>

     <!-- Bootstrap Colorpicker -->
    <script src="../vendors/mjolnic-bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
  <script  type="text/javascript">
   $(function () {
                $('#myDatepicker').datetimepicker();
            });
    
    $('#myDatepicker2').datetimepicker({
        format: 'DD'
    });
    
    $('#myDatepicker3').datetimepicker({
        format: 'hh:mm A'
    });
    
    $('#myDatepicker4').datetimepicker({
        ignoreReadonly: true,
        allowInputToggle: true
    });

    $('#datetimepicker6').datetimepicker();
    
    $('#datetimepicker7').datetimepicker({
        useCurrent: false
    });
    
    $("#datetimepicker6").on("dp.change", function(e) {
        $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
    });
    
    $("#datetimepicker7").on("dp.change", function(e) {
        $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
    });
</script>
  </body>
</html>



