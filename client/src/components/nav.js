import React, {Component} from 'react';


class Nav extends Component {
   
   
    render() {
        return (
            
                <div style={{boxShadow : '0px 0px 5px #888888'}}>
                
              
                <div className="jumbotron" style={{height:"15px",backgroundColor:"white"}}>
                 
                   <div className="row" style={{marginTop:"-35px"}}>
                   <div className="col-6 col-md-4">  </div>
                    <div className="col-6 col-md-4">
                      <div style={{display:'inline'}}>
                      <img src={require("../images/dropbox_logo.svg")} width="50" height="50" alt=""/>   
                      <b style={{fontSize:'30px', marginLeft:"15px"}}>Dropbox</b>
                      </div>
                      </div>
                     <div className="col-6 col-md-4">  </div> 
                    </div>
                   
                   
                 
              </div>
                
              
             </div>
               
         
          
        );
    }
}



export default Nav;