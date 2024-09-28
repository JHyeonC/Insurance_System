import React from 'react'
import './CustomerTable.css'

function TableRow({name, content}) {
    return (
        <tr>
            <td>
                {name}
            </td>
            <td>
                {content}
            </td>
        </tr>
    );
}

export default function CustomerTable({customer}) {
    return (
        <table className='customer_table'>
            <TableRow name={'이름'} content={customer.name}/>
            <TableRow name={'성별'} content={customer.gender === 0 ? '남' : '여'}/>
            <TableRow name={'위험직업군'} content={customer.occupational_hazard === 1 ? '네' : '아니오'}/>
            <TableRow name={'흡연여부'} content={customer.smoking === 1 ? '네' : '아니오'}/>
        </table>
    );
}