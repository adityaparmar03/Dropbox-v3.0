import axios from 'axios';

import {URL} from '../constant';

export function SignUp(data){
    return  dispatch => {
        axios.post(URL+"signup", data)
          .then(function (response) {
            return dispatch({ type : "SIGNUP_RESULT", payload : response.data } )
          })
          .catch(function (error) {
            return dispatch({ type : "SIGNUP_ERROR", payload : error } )
          });
         
     }
}
