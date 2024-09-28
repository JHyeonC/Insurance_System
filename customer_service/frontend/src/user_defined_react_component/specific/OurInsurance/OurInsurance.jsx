import React, {useState} from 'react';
import Login from '../Login/Login';
import Main from '../Main/Main';


export default function OurInsurance() {

    const [authenticated, setAuthenticated] = useState(false);
    const [customer, setCustomer] = useState(null);

    return (
        authenticated ? <Main customer={customer}/> : <Login setAuthenticated={setAuthenticated} setCustomer={setCustomer}/>
    );
}