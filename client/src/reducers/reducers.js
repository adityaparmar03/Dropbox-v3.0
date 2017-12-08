import {combineReducers} from 'redux'
import signin from './signin';
import signup from './signup';
import home from './home';
import activitylog from './activitylog';
import profile from './profile';

export default combineReducers({

   
    signup,
    signin,
    profile,
    activitylog,
    home
    

});

