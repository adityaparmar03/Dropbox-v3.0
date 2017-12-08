import axios from 'axios';

import {URL} from '../constant';

export function INIT(){
  return  dispatch => {
     
    axios.get(URL+"user",{withCredentials: true})
    .then(function (response) {
        //var userid = response.data.user._id;
        console.log(JSON.stringify(response.data.user))
        return dispatch({ type : "PROFILE_RESULT", payload : response.data } )
        })
        .catch(function (error) {
          return dispatch({ type : "PROFILE_ERROR", payload : error } )
        });
       
   }
}
export function UPDATE(email,password,firstname,lastname,aboutme,interests,userid){
 
    return  dispatch => {
       
        axios.post(URL+"profile", {user:{
          email:email,
          password:password,
          firstname:firstname,
          lastname:lastname,
          aboutme:aboutme,
          interests:interests,
          userid : userid,
          all:firstname+" "+lastname+" ("+email+")"
        }})
          .then(function (response) {
            return dispatch({ type : "UPDATE_RESULT", payload : response.data } )
          })
          .catch(function (error) {
            return dispatch({ type : "PROFILE_ERROR", payload : error } )
          });
         
     }
  }


