var profile_initialstate ={
    email:"",
    password:"",
    firstname:"",
    lastname:"",
    aboutme:"",
    interests:"",
    status:"",
    msg:"",
    userid:""
}
export default function(state=profile_initialstate,action){
    
    if(action.type === "PROFILE_RESULT"){
        return{
            ...state,
            status:action.payload.status,
            msg:action.payload.msg,
            firstname:action.payload.user.firstname,
            lastname:action.payload.user.lastname,
            email:action.payload.user.email,
            password:action.payload.user.password,
            aboutme:action.payload.user.aboutme,
            interests:action.payload.user.interests,
            userid:action.payload.user._id,
                
        }
    }
    if(action.type === "PROFILE_ERROR"){
        return{
            ...state,
            status:"error",
            msg:"something went wrong",
                 
        }
    }
    if(action.type === "UPDATE_RESULT"){
        return{
            ...state,
            status:action.payload.status,
            msg:action.payload.msg,
                 
        }
    }
    
    if(action.type === "LOGOUT"){
        return{
            ...state,
            email:"",
            password:"",
            firstname:"",
            lastname:"",
            aboutme:"",
            interests:"",
            status:"",
            msg:"",
            userid:""
                 
        }
    }
    return state;
}