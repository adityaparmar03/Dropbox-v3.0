import axios from 'axios';
import {URL} from '../constant';


   export function INIT(user){
    return  dispatch => {
       
      axios.post(URL+"user/checksession",user)
      .then(function (response) {
     //var userid = response.data.user._id;
          console.log(JSON.stringify(response.data))
          var users = response.data.users
          dispatch({ type : "PROFILE_RESULT", payload : response.data } )
            axios.post(URL+"user/activities",users)
            .then(function (response) {
              return dispatch({ type : "ACTIVITYLOG_RESULT", payload : response.data } )
            })
            .catch(function (error) {
              return dispatch({ type : "ACTIVITYLOG_ERROR", payload : error } )
            });
    
            })
          .catch(function (error) {
            return dispatch({ type : "PROFILE_ERROR", payload : error } )
          });
         
     }
            
         
          
  }  
       
   

 


export function LOGOUT(token){
  
    localStorage.clear();
    sessionStorage.clear();
    return ({ type : "LOGOUT", payload : "" } )
     
         
     
  }