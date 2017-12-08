import React from 'react';
import createClass from 'create-react-class';
import PropTypes from 'prop-types';
import Select from 'react-select';
import fetch from 'isomorphic-fetch';


const Test = createClass({
	displayName: 'GithubUsers',
	propTypes: {
		label: PropTypes.string,
	},
	getInitialState () {
		return {
            value:[],
            num:0
		};
	},
	onChange (value) {
        console.log(value)
		this.setState({
            value: value,
            num : this.state.num+1
        });
       
	},
	
	getUsers (input) {
		if (!input) {
			return Promise.resolve({ options: [] });
		}

		return fetch('http://localhost:9000/users', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({"index":input})})
            .then((response) => response.json())
		    .then((json) => {
                console.log(JSON.stringify(json))
			    return { options: json.users };
		    });
	},
    display(data,i){
        return <tr key={data._id}>
            <td className="alert alert-info" > <strong>{data.firstname+" "+data.lastname}</strong></td>
            <td>{this.props.shareid}</td>
            <td>kom</td>
            <td>kom</td>

        </tr>
    },
	render () {
		const AsyncComponent = Select.AsyncCreatable
			

		return (
			<div className="section">
			 	<AsyncComponent multi={true}
                 value={this.state.value} 
                 onChange={this.onChange} 
                 valueKey="_id" labelKey="firstname" 
                 loadOptions={this.getUsers} 
                 backspaceRemoves={true} />
                 <table className="table">
                 <thead>
                  <tr>
                      
                         <th  style={{width:"50%"}}>Name</th>
                         <th>Date</th>
                         <th>Member</th>
                         <th></th>
                 </tr>
                 </thead>
                 <tbody>
                  
                   {
                  
                      this.state.value.map((this.display),this)
                       
                   }
                 </tbody>  
                  </table>
                
			</div>
             
		);
	}
});

export default  Test;