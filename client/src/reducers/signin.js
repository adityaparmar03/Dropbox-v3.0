
var signin_initialstate ={
    status:"",
    msg:"",
    id:"",
    currentfolderid:""

}
export default function(state=signin_initialstate,action){
    
    if(action.type === "SIGNIN_RESULT"){
        return{
            ...state,
            status:action.payload.response.status,
            msg:action.payload.response.msg,
            id:action.payload.users.id
        }
    }
    if(action.type === "SIGNIN_ERROR"){
        return{
            ...state,
            status:"error",
            msg:"something went wrong",
                
        }
    }
    if(action.type === "ROOT_RESULT"){
        return{
            ...state,
            currentfolderid:action.payload.rootid,
            status:action.payload.status,
            msg:action.payload.msg
                 
        }
    }
    if(action.type === "LOGOUT"){
        return{
            ...state,
            status:"",
            msg:"",
            currentfolderid:""
           
                 
        }
    }
    return state;
}