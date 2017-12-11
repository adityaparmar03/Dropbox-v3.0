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
            status:action.payload.response.status,
            msg:action.payload.response.msg,
            firstname:action.payload.users.firstname,
            lastname:action.payload.users.lastname,
            email:action.payload.users.email,
            password:action.payload.users.password,
            aboutme:action.payload.users.aboutme,
            interests:action.payload.users.interests,
            userid:action.payload.users.id,
                
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