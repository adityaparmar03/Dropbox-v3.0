var activity_initialstate ={
    activity:"",
    msg:"",
    status:""

}
export default function(state=activity_initialstate,action){
    
    if(action.type === "ACTIVITYLOG_RESULT"){
        return{
            ...state,
            activity:action.payload.activities,  
            status:action.payload.status,
            msg:action.payload.msg
                 
        }
    }
    if(action.type === "ACTIVITYLOG_ERROR"){
        return{
            ...state,
            status:"error",
            msg:"something went wrong",
                 
        }
    }
   
    
    if(action.type === "LOGOUT"){
        return{
            ...state,
            activity:[]
                 
        }
    }
    return state;
}