import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Index from './components/index';
import Home from './components/home'
import Activitylog from './components/activitylog';
import Profile from './components/profile'

import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route } from 'react-router-dom';
//import createBrowserHistory from 'history/createBrowserHistory' 
import store from './store/index'

//var history = createBrowserHistory();

ReactDOM.render(
    <Provider store={store}>
        <Router>
         <div>
         <Route exact path="/" component={Home}/>
         <Route  path="/home" component={Home}/>
         <Route path="/signin" component={Index} />
         <Route path="/activitylog" component={Activitylog} />
         <Route path="/profile" component={Profile} />
        
         </div>
        
     </Router>
     </Provider>,
     document.getElementById('root'));
