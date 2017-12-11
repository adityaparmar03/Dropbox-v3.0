import axios from 'axios';

import {URL} from '../constant';

export function INIT(user){
  return  dispatch => {
     
    axios.post(URL+"user/checksession",user)
    .then(function (response) {
   //var userid = response.data.user._id;
        console.log(JSON.stringify(response.data))
        return dispatch({ type : "PROFILE_RESULT", payload : response.data } )
        })
        .catch(function (error) {
          return dispatch({ type : "PROFILE_ERROR", payload : error } )
        });
       
   }
}
export function UPDATE(email,password,firstname,lastname,aboutme,interests,userid){
 
    return  dispatch => {
       
        axios.post(URL+"user/update", {
          id:userid,
          firstname:firstname,
          lastname:lastname,
          email:email,
          password:password,
          aboutme:aboutme,
          interests:interests,
          all:firstname+" "+lastname+" ("+email+")"
          })
          .then(function (response) {
            return dispatch({ type : "PROFILE_RESULT", payload : response.data } )
          })
          .catch(function (error) {
            return dispatch({ type : "PROFILE_ERROR", payload : error } )
          });
         
     }
  }


