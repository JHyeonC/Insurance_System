import React, {useState} from 'react'
import MyInfo from './MyInfo/MyInfo'
import ViewContracts from './ViewContracts/ViewContracts'
import CreateClaim from "./CreateClaim/CreateClaim";
import ViewBoard from "./ViewBoard/ViewBoard";
import CreateContract from "./CreateContract/CreateContract";

export default function MainContents({panelName, customer}) {
    if (panelName === 'MyInfo') return (<MyInfo customer={customer}/>)
    else if (panelName === 'ViewContracts') return (<ViewContracts customer={customer}/>)
    else if (panelName === 'CreateClaim') return (<CreateClaim customer={customer}/>)
    else if (panelName === 'ViewBoard') return (<ViewBoard customer={customer}/>)
    else if (panelName === 'CreateContract') return (<CreateContract customer={customer} />)
}