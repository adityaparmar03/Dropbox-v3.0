import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as myactions from '../action_creators/signin';
 
import { withRouter } from 'react-router-dom'
import AlertContainer from 'react-alert'

class SignIn extends Component {
    constructor(props){
        super(props);
        this.state = 
        {
           
                email:"",
                password:"",
                status:"",
                msg:"",
                validation_errors:[]
            
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
    componentDidMount() {
        this.props.INIT()
       
    }
    
    componentWillReceiveProps(nextProps) {
        if (nextProps.signin) {
          if(nextProps.signin.status === 'success'){

             this.props.history.push('/home')
            /* this.setState({
                status:nextProps.signin.status,
                msg:nextProps.signin.msg
              })*/
          }
          else{
            this.setState({
                status:nextProps.signin.status,
                msg:nextProps.signin.msg
              })
          }  
         
        }
    }
    handleSubmit(){
      
        var email = this.refs.email.value;
        var password = this.refs.password.value;
        var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        var errors =[];

        if(email.length===0){
             errors.push("Email can't be empty.")
        }else if(!regex.test(email)){
             errors.push("Invalid Email.")
        }
        
        if(password.length===0){
             errors.push("Password can't be empty.")
        }
        else if(password.length < 3 || password.length > 8){
             errors.push("Password should between 3 to 8 characters.")
        }
        
        if(errors.length === 0){
            this.setState({validation_errors:errors})
            this.props.SignIn({
                email:email,
                password:password

            })
        }else{
            this.setState({validation_errors:errors})
            this.setState({status:"validationerror"})
        } 
       
    }
    
    
    display_msg(){
         console.log(this.state)
         if(this.state.status==="error"){
            return (<div className="alert alert-danger" role="alert">
                {this.props.signin.msg}
          </div>)
        }
        else if(this.state.status==="validationerror"){
            return (<div>
                     {console.log(this.state)}    
                     {this.state.validation_errors.map((item,index)=><div key={index} className="alert alert-danger" role="alert">{item}</div>)}
            </div>)
        }
        else{
            <div></div>
        }
    }
    
    render() {
        return (
            <div style={{backgroundColor:"white",width:"70%"}}>
             <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
                  <input type="email" className="form-control" placeholder="Email" ref="email"/><br/>
                  <input type="password" className="form-control" placeholder="Password" ref="password" /><br/>
                  <button className="btn btn-primary" onClick={()=>this.handleSubmit()}> Sign in</button>
                  <br/>
                  <br/>
                   {this.display_msg()}
            </div>
          
        );
    }
}
function mapStateToProps(state){
    
     return{
        
         signin : state.signin
     }
 }
 function matchDispatchToProps(dispatch){
     
     return bindActionCreators(myactions,dispatch)
   
     
 }
 
 
 export default withRouter(connect(mapStateToProps,matchDispatchToProps)(SignIn));