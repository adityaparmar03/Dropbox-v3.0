import axios from 'axios';
import {URL} from '../constant';
//const headers = {
//    'Accept': 'application/json'
//};
export function INIT(callback,user){
    return  dispatch => {
        
      axios.post(URL+"user/checksession",user)
          .then(function (response) {

                dispatch({ type : "HOME_RESULT", payload : response.data } )
               
                axios.post(URL+"content/root",user)
                .then(function (response) {
  
                  var rootid = response.data.rootid
                  dispatch({ type : "ROOT_RESULT", payload : response.data } )
                  
                  axios.post(URL+"content/load", {"userid":user.id,"contentid":rootid})
                  .then((response)=>{
                    callback(1)
                    return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
                   
                  }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                  });
             
        
                })
                .catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                });
              
             
             
            
            })
          .catch(function (error) {
            return dispatch({ type : "HOME_ERROR", payload : error } )
          });
         
    }
}
export function LOGOUT(){
  return  dispatch => {
  
}
 // return  dispatch => {
    
      /*axios.get(URL+"logout",{withCredentials: true})
        .then(function (response) {
         
        
         
        })
        .catch(function (error) {
          return dispatch({ type : "HOME_ERROR", payload : error } )
        });
       
   }*/
}


export function UploadFile(payload){
      return  dispatch => {
          
          axios.post(URL+"content/upload", payload)
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
         
          axios.post(URL+"content/createfolder", {"contentid":parentfolderid,"foldername":foldername,"userid":userid})
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
   
    axios.post(URL+"content/load", {"userid":userid,"contentid":parentfolderid})
    .then((response)=>{
     
      return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
     
    }).catch(function (error) {
    return dispatch({ type : "HOME_ERROR", payload : error } )
    });
  }
}
export function share(users,userid,contentid,parentfolderid){
  console.log("contentid"+contentid);
  return  dispatch => {
     

      axios.post(URL+"content/share", {"users":users,"content":contentid,userid})
        .then(function (response) {
           dispatch({ type : "SHARE_RESULT", payload : response.data } )
          
           axios.post(URL+"content/load", {"userid":userid,"contentid":parentfolderid})
           .then((response)=>{
            return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
       
              }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
              });

              }).catch(function (error) {
                  return dispatch({ type : "HOME_ERROR", payload : error } )
                });
     
      
       
   }
  
}
export function deleteContent(content,userid,parentfolderid){
  
    return  dispatch => {
     
        axios.post(URL+"content/delete", {content,userid})
          .then(function (response) {
             dispatch({ type : "DELETE_RESULT", payload : response.data } )
                 
             axios.post(URL+"content/load", {"userid":userid,"contentid":parentfolderid})
             .then((response)=>{
             
               return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
              
             }).catch(function (error) {
             return dispatch({ type : "HOME_ERROR", payload : error } )
             });
        
  
                }).catch(function (error) {
                    return dispatch({ type : "HOME_ERROR", payload : error } )
                  });
       
        
         
     }
    
  }

 

  export function dostar(file,userid,parentfolderid){
    
    return  dispatch => {
       
        axios.post(URL+"content/dostar", file)
          .then(function (response) {
            dispatch({ type : "STAR_RESULT", payload : response.data } )
            axios.post(URL+"content/load", {"userid":userid,"contentid":parentfolderid})
            .then((response)=>{
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

  export function deleteMember(contentid,muserid, userid, parentfolderid){
  
     return  dispatch => {
      
         axios.post(URL+"content/removemember", {"data1":contentid,"data2":muserid})
           .then(function (response) {
              dispatch({ type : "DELETE_RESULT", payload : response.data } )
             
              axios.post(URL+"content/load", {"userid":userid,"contentid":parentfolderid})
              .then((response)=>{
               return dispatch({ type : "FOLDER_RESULT", payload : response.data } )
          
                 }).catch(function (error) {
                     return dispatch({ type : "HOME_ERROR", payload : error } )
                 });
   
                  }).catch(function (error) {
                     return dispatch({ type : "HOME_ERROR", payload : error } )
                   });
        
         
          
      }
     
   }