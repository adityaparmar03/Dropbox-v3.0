import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as myactions from '../action_creators/profile';
import { withRouter } from 'react-router-dom'
import Menu from './menu'
import Menu2 from './menu2'
import AlertContainer from 'react-alert'


class Profile extends Component {
    constructor(props){
        super(props);
        this.state = 
        {
            email:"",
            password:"",
            firstname:"",
            lastname:"",
            aboutme:"",
            interests:"",
            status:"",
            msg:"",
            userid:"",
         
        }
    }
    alertOptions = {
        offset: 14,
        position: 'top center',
        theme: 'dark',
        time: 5000,
        transition: 'scale'
      }
     errorshowAlert = (msg) => {
        this.msg.show(msg, {
          time: 5000,
          type: 'success',
          icon: <img src={require('../images/error.png')} />
        })
      }
     successshowAlert = (msg) => {
        this.msg.show(msg, {
          time: 5000,
          type: 'success',
          icon: <img src={require('../images/success.png')} />
        })
      }

    updateState(name, value){

                if(name==="firstname")
                this.setState({firstname : value});
                if(name==="lastname")
                this.setState({lastname : value});
                if(name==="aboutme")
                this.setState({aboutme : value});
                if(name==="interests")
                this.setState({interests : value});
                
            
         
    }
    componentWillReceiveProps(nextProps) {
        if (nextProps.profile) {
            if(nextProps.profile.userid === undefined || nextProps.profile.status === 'logout'){
                this.props.history.push('/signin');
          }
          else{

          this.setState({
            email:nextProps.profile.email,
            password:nextProps.profile.password,
            firstname:nextProps.profile.firstname,
            lastname:nextProps.profile.lastname,
            aboutme:nextProps.profile.aboutme,
            interests:nextProps.profile.interests,
            status:nextProps.profile.status,
            msg:nextProps.profile.msg,
            userid:nextProps.profile.userid,
          
          })
        }
        }
        if(nextProps.profile.status ==="success"){
            this.successshowAlert(nextProps.profile.msg)
        }
        else{
            this.errorshowAlert(nextProps.profile.msg)
        }
    }

    componentDidMount() {
       

          this.props.INIT();
                             
        
    }
    
  

    
    
    render() {
        return (
           
            <div className="container-fluid">  
                <div className="row">
                   
                    <div className="col-6 col-md-2">
                       <Menu/>
                </div>
                <div className="col-6 col-md-8">
                <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
       
                    <div style={{paddingTop:"5%"}}> 
                        <h5><b>Personal Account</b></h5>
                        <hr/>
                        <div style={{height:"600px",overflow:"scroll"}}>

                        <div className="form-group">
                        <label htmlFor="email">Email:</label>
                        <input type="email" className="form-control" 
                        value={this.state.email} id="email" disabled/>
                        </div>

                         <div className="form-group">
                        <label  htmlFor="pwd">Password:</label>
                        <input type="password" className="form-control" 
                        value={this.state.password} id="pwd"
                        onChange={(e)=>this.updateState("password",e.target.value)} disabled/>
                        </div>

                        <div className="form-group">
                        <label  htmlFor="fn">First Name:</label>
                        <input type="text" className="form-control"
                        value={this.state.firstname} id="fn"
                        onChange={(e)=>this.updateState("firstname",e.target.value)}/>
                        </div>

                        <div className="form-group">
                        <label  htmlFor="ls">Last Name:</label>
                        <input type="text" className="form-control"
                        value={this.state.lastname} id="ls" 
                        onChange={(e)=>this.updateState("lastname",e.target.value)}/>
                        </div>
                        
                        <div className="form-group">
                        <label  htmlFor="about">About me:</label>
                        <textarea type="text" className="form-control" 
                        value={this.state.aboutme } id="about" 
                        onChange={(e)=>this.updateState("aboutme",e.target.value)}></textarea>
                        </div>

                        <div className="form-group">
                        <label  htmlFor="int">My Interests:</label>
                        <textarea type="text" className="form-control" 
                        value={this.state.interests} id="int" 
                        onChange={(e)=>this.updateState("interests",e.target.value)}></textarea>
                        </div>

                        {console.log(JSON.stringify(this.state))} 
                        <button type="submit" className="btn btn-primary"
                        onClick={()=>this.props.UPDATE(this.state.email,
                            this.state.password,
                            this.state.firstname,
                            this.state.lastname,
                            this.state.aboutme,
                            this.state.interests,
                            this.state.userid,
                            this.state.isPasswordChanged
                        )}>Save</button>
                      
                        </div>
                        
                    </div>    
                </div>
                <div className="col-6 col-md-2">
                      <Menu2/>   
                </div>
            </div>
            </div>
                
               
        );
    }
}
function mapStateToProps(state){
    
     return{
        
         profile : state.profile
     }
 }
 function matchDispatchToProps(dispatch){
     
     return bindActionCreators(myactions,dispatch)
   
     
 }
 
 
 export default withRouter(connect(mapStateToProps,matchDispatchToProps)(Profile));