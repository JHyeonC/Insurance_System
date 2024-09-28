import React, {useEffect, useState} from "react";
import axios from "axios";
import './ViewContracts.css'
import swal from "sweetalert";
import {MAPPING_POINT_CONTRACT_GET_ALL_CONTRACT_BY_CUSTOMER_ID} from '../../../../common/MappingPoint/MappingPoint.js'

export default function ViewContracts({customer}) {

    const [contracts, setContracts] = useState(null);

    useEffect(() => {
        axios.get(MAPPING_POINT_CONTRACT_GET_ALL_CONTRACT_BY_CUSTOMER_ID, {params: {id: customer.id}})
            .then(response => {
                setContracts(response.data);
            })
            .catch(e => {
                swal({
                    title: '시스템 오류', text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.', icon: 'error', button: '확인',
                });
            });
    }, []);

    if (contracts === null) return null;
    return (<>
        <div className={'view_contract'}>
            <h2>가입 상품 조회</h2>
            <table>
                <tr>
                    <th>상품 이름</th>
                    <th>보험료</th>
                </tr>
                {contracts.map(contract => <tr>
                    <td>{contract.product_name}</td>
                    <td>{contract.premium}원</td>
                </tr>)}
            </table>
        </div>
    </>);
}