import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as myactions from '../action_creators/home';
import { withRouter } from 'react-router-dom'
import  '../css/vericalmenu.css'


class Menu2 extends Component {
  
    
    render() {
        return (
            <div className="dropdown" style={{marginLeft:"60%",marginTop:"10%"}}>
                 <button className="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">
                    <img src={require("../images/faceholder.png")}/>
                 </button>
                    <ul className="dropdown-menu">
                    <li><a href="">{this.props.home.firstname+" "+this.props.home.lastname}</a></li>    
                    <li className="divider"></li>
                    <li><a href="/home">Home</a></li>
                    <li><a href="/profile">Profile</a></li>
                    <li><a href="/activitylog">Activity Log</a></li>
                    <li className="divider"></li>
                    <li onClick={()=>this.props.LOGOUT()}><a href="">Sign out</a></li>
                    </ul>
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
 
 
 export default withRouter(connect(mapStateToProps,matchDispatchToProps)(Menu2));