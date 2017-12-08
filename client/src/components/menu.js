import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as myactions from '../action_creators/home';
import { withRouter } from 'react-router-dom'
import  '../css/vericalmenu.css'


class Menu extends Component {
  
    
    render() {
        return (
            <div className="jumbotron" style={{height:"720px",marginLeft:"-12px"}}>
                       <div className="vertical-menu">
                            <a onClick={()=>this.props.history.push("/home")}>
                            <img src={require("../images/dropbox_logo.svg")} width="50" height="50" alt=""/>   
                            </a>
                            <a onClick={()=>this.props.history.push("/home")}>My files</a>
                            <a onClick={()=>this.props.history.push("/activitylog")}>Activity</a>
                           
                            <a onClick={()=>this.props.history.push("/profile")}>Profile</a>
                     
            </div>

            </div>
                
               
        );
    }
}
function mapStateToProps(state){
    
     return{
        
         home : state.home
     }
 }
 function matchDispatchToProps(dispatch){
     
     return bindActionCreators(myactions,dispatch)
   
     
 }
 
 
 export default withRouter(connect(mapStateToProps,matchDispatchToProps)(Menu));