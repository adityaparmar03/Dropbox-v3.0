import axios from 'axios';
import {URL} from '../constant';


   export function INIT(){
    return  dispatch => {
       
      axios.get(URL+"user",{withCredentials: true})
      .then(function (response) {
          //var userid = response.data.user._id;
          console.log(JSON.stringify(response.data.user))
           dispatch({ type : "PROFILE_RESULT", payload : response.data } )
              axios.post(URL+"activitylog",{userid:response.data.user._id})
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