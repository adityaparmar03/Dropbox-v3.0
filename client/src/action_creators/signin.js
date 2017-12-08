import axios from 'axios';
import {URL} from '../constant';


const headers = {
  'Accept': 'application/json'
};
export function INIT(){
  return  dispatch => {
      
     axios.get(URL+"user")
        .then(function (response) {
           
          
         return dispatch({ type : "SIGNIN_RESULT", payload : response.data } )
  
        })
              
        .catch(function (error) {
          return dispatch({ type : "SIGNIN_ERROR", payload : error } )
        });
       
  }
}
export function SignIn(data){
    return  dispatch => {

          fetch(URL+"user/signin", {
            method: 'POST',
            headers: {
                ...headers,
                'Content-Type': 'application/json'
            },
          
            body: JSON.stringify(data)
        }).then(response => { return response.json(); })
        .then(function(data) {
          if(data.response.status ==='success')
            localStorage.setItem("id",data.users.id);
          dispatch({ type : "SIGNIN_RESULT", payload : data } )
        }).catch(error => {
          return dispatch({ type : "SIGNIN_ERROR", payload : error } )
                
        })}}

