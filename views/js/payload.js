var payload={};
function Onchange(gp,val){
    payload[gp]=val;
}
function createPayload(url){
    console.log(JSON.stringify(payload) )
    fetch(url, {
    method: 'POST', 
    credentials: 'same-origin', 
    headers: {
    'Content-Type': 'application/json'
    },
    referrerPolicy: 'no-referrer', 
    body: JSON.stringify(payload) 
   }).then(response => response.json())
     .then(data =>{
     if(data=="SUCCESS"){
      window.location='/';
     }    
     else{
       setErrorMessage(JSON.stringify(data));
     }
     payload={};
    });
  }
function setErrorMessage(data){
    document.getElementById("mod").innerText=data;
    $('#myModal').modal('show'); 
  }

function toggle(i){
  document.getElementById(i=="A" ? "S" : "A").checked=false;
}
