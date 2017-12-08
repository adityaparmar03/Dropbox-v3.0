import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as myactions from '../action_creators/signup';
import AlertContainer from 'react-alert'



class SignUp extends Component {
    constructor(props){
        super(props);
        this.state = 
        {
          
                firstname:"",
                lastname:"",
                email:"",
                password:"",
                status:"",
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
    handleSubmit(){
        
          var firstname = this.refs.firstname.value;
          var lastname = this.refs.lastname.value;
          var email = this.refs.email.value;
          var password = this.refs.password.value;
          
          var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
          var firstname_regex=/[0-9]/g;
          var lastname_regex=/[0-9]/g;
          var errors =[];
  
          if(firstname.length === 0){
            errors.push("Firstname can't be empty.")
          }else if(firstname_regex.test(firstname)){
            errors.push("Invalid Firstname.")
          }else if(firstname.length>45){
            errors.push("Firstname is very long.")
          }

          if(lastname.length === 0){
            errors.push("Lastname can't be empty.")
          }else if(lastname_regex.test(lastname)){
            errors.push("Invalid Lastname.")
          }else if(lastname.length>45){
            errors.push("Lastname is very long.")
          }

          if(email.length===0){
               errors.push("Email can't be empty.")
          }else if(!email_regex.test(email)){
              errors.push("Invalid Email.")
          }else if(email.length>45){
            errors.push("Email is very long.")
          }
          
          if(password.length===0){
               errors.push("Password can't be empty.")
          }
          else if(password.length < 3 || password.length > 8){
               errors.push("Password should between 3 to 8 characters.")
          }
          
          if(errors.length === 0){
              this.setState({validation_errors:errors})
              //call submit
              this.props.SignUp({
                  firstname:firstname,
                  lastname:lastname,
                  email:email,
                  password:password
                })
          }else{
              this.setState({validation_errors:errors})
          } 
         
      }
    display_msg(){
        if(this.props.signup.status==="success"){
            return (<div className="alert alert-success" role="alert">
           {this.props.signup.msg}
          </div>)
        }else if(this.props.signup.status==="error"){
            return (<div className="alert alert-danger" role="alert">
                {this.props.signup.msg}
          </div>)
        }
        else{
            return (<div>  
            {this.state.validation_errors.map((item,index)=><div key={index} className="alert alert-danger" role="alert">{item}</div>)}
            </div>)
        }
    }
    
    render() {
        return (
            <div style={{backgroundColor:"white",width:"70%"}}>
             <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
                    <input type="text" className="form-control" placeholder="First name"
                    ref="firstname"/><br/>
                    <input type="text" className="form-control" placeholder="Last name" 
                    ref="lastname"/><br/>
                    <input type="email" className="form-control" placeholder="Email" 
                    ref="email"/><br/>
                    <input type="password" className="form-control" placeholder="Password" 
                    ref="password"/><br/>
                    <button className="btn btn-primary" onClick={()=>this.handleSubmit()}> Create an Account</button>
                    <br/>
                    <br/>
                    {this.display_msg()}
            </div>
          
        );
    }
}
function mapStateToProps(state){
   
    return{
       
        signup : state.signup
    }
}
function matchDispatchToProps(dispatch){
    
    return bindActionCreators(myactions,dispatch)
  
    
}


export default connect(mapStateToProps,matchDispatchToProps)(SignUp);