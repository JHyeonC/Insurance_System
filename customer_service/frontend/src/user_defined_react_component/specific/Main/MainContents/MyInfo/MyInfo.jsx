import React, {useState} from 'react'
import './MyInfo.css'
import CustomerTable from "./CustomerTable";

export default function MyInfo({customer}) {
    return (
        <div className='my_info'>
            <h2>나의 정보</h2>
            <CustomerTable customer={customer}/>
        </div>
    );
}