import React, {useEffect, useState} from "react";
import axios from "axios";
import './ViewCustomerStatistics.css'
import {MAPPING_POINT_CUSTOMER_GET_CUSTOMER_STATISTICS, MAPPING_POINT_PRODUCT_GET_ALL_PRODUCTS} from "../../../../common/MappingPoint/MappingPoint";
import swal from "sweetalert";

export default function ViewCustomerStatistics({employee}) {

    const [customerStatistics, setCustomerStatistics] = useState(null);

    useEffect(() => {
            axios.get(MAPPING_POINT_CUSTOMER_GET_CUSTOMER_STATISTICS)
                .then(response => {
                    setCustomerStatistics(response.data);
                })
                .catch(error => {
                    swal({
                        title: '시스템 오류',
                        text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.',
                        icon: 'error',
                        button: '확인',
                    });
                });
        },
        []
    );

    if (customerStatistics === null) return null;
    return (
        <div className={'view_customer_statistics'}>
            <h2>고객 통계</h2>
            <table>
                <tr>
                    <td>평균 연령</td>
                    <td>{customerStatistics.average_age}</td>
                </tr>
            </table>
        </div>
    );
}