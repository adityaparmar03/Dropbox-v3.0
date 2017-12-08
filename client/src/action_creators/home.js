import axios from 'axios';
import {URL} from '../constant';
//const headers = {
//    'Accept': 'application/json'
//};
export function INIT(callback){
    return  dispatch => {
        
      /* axios.get(URL+"user",{withCredentials: true})
          .then(function (response) {
              var userid = response.data.user._id;
              if(userid===undefined || userid===""){
                return dispatch({ type : "HOME_RESULT", payload : response.data } )
              }
              else{
                dispatch({ type : "HOME_RESULT", payload : response.data } )
                axios.post(URL+"home/root",{userid:userid})
                .then(function (response) {
  
                  var rootid = response.data.rootid
                  dispatch({ type : "ROOT_RESULT", payload : response.data } )
                  */
                  axios.get(URL+"folder/load", {"userid":1,"parentfolderid":1})
                  .then((response)=>{
                    callback(1)
                    return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
                   
                  }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                  });
             /*
        
                })
                .catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                });
              }
             
             
           
            })
          .catch(function (error) {
            return dispatch({ type : "HOME_ERROR", payload : error } )
          });*/
         
    }
}
export function LOGOUT(){
  return  dispatch => {
      
      axios.get(URL+"logout",{withCredentials: true})
        .then(function (response) {
         
          return dispatch({ type : "LOGOUT", payload : response.data } )
         
        })
        .catch(function (error) {
          return dispatch({ type : "HOME_ERROR", payload : error } )
        });
       
   }
}


export function UploadFile(payload){
      return  dispatch => {
          
          axios.post(URL+"upload", payload)
            .then(function (response) {
              return dispatch({ type : "UPLOAD_RESULT", payload : response.data } )
            })
            .catch(function (error) {
              return dispatch({ type : "HOME_ERROR", payload : error } )
            });
           
       }
       
}
export function UploadFolder(parentfolderid,foldername,userid){
      if(foldername!=="")
      return  dispatch => {
         
          axios.post(URL+"upload/createfolder", {"parentfolderid":parentfolderid,"foldername":foldername,"userid":userid})
            .then(function (response) {
              return dispatch({ type : "CREARE_FOLDER_RESULT", payload : response.data } )
            })
            .catch(function (error) {
              return dispatch({ type : "CREARE_FOLDER_RESULT", payload : error } )
            });
           
       }
       else{
        return { type : "CREARE_FOLDER_ERROR", payload : {status:'error',msg:'folder name can not be blank.'} } 
         
       }
}
export function LOADFOLDER(userid,parentfolderid){
  return (dispatch) => {
   
    axios.post(URL+"folder/load", {"userid":userid,"parentfolderid":parentfolderid}).then((response)=>{
              return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
         
        }).catch(function (error) {
            return dispatch({ type : "HOME_ERROR", payload : error } )
          });
       
  }
}
export function share(users,userid,contentid,parentfolderid){
  console.log("contentid"+contentid);
  return  dispatch => {
   
      axios.post(URL+"share", {"users":users,"userid":userid,"contentid":contentid})
        .then(function (response) {
           dispatch({ type : "SHARE_RESULT", payload : response.data } )
          
           axios.post(URL+"folder/load", {"userid":userid,"parentfolderid":parentfolderid}).then((response)=>{
            return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
       
              }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
              });

              }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                });
     
      
       
   }
  
}
export function deleteContent(parentfolderid,file,userid){
  
    return  dispatch => {
     
        axios.post(URL+"delete", {"userid":userid,"file":file,"parentfolderid":parentfolderid})
          .then(function (response) {
             dispatch({ type : "DELETE_RESULT", payload : response.data } )
                 
             axios.post(URL+"folder/load", {"userid":userid,"parentfolderid":parentfolderid}).then((response)=>{
              return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
         
                }).catch(function (error) {
                    return dispatch({ type : "HOME_ERROR", payload : error } )
                });
  
                }).catch(function (error) {
                    return dispatch({ type : "HOME_ERROR", payload : error } )
                  });
       
        
         
     }
    
  }
  export function dostar(contentid,value,curentfolderid,userid){
    
    return  dispatch => {
       
        axios.post(URL+"star", {"contentid":contentid,"value":value})
          .then(function (response) {
            dispatch({ type : "STAR_RESULT", payload : response.data } )
            axios.post(URL+"folder/load", {"userid":userid,"parentfolderid":curentfolderid}).then((response)=>{
              return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
         
                }).catch(function (error) {
                    return dispatch({ type : "HOME_ERROR", payload : error } )
                });
          })
          .catch(function (error) {
            return dispatch({ type : "HOME_ERROR", payload : error } )
          });
         
     }
    
  }