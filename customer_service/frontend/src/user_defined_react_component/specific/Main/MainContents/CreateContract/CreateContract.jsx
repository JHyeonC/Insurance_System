import React, {useEffect, useState} from 'react';
import axios from "axios";
import './CreateContract.css'
import swal from "sweetalert";
import SelectInput from "../../../../common/SelectInput/SelectInput";
import Button from "../../../../common/Button/Button";
import {MAPPING_POINT_PRODUCT_GET_ALL_PRODUCTS, MAPPING_POINT_CONTRACT_CREATE_CONTRACT} from '../../../../common/MappingPoint/MappingPoint.js'

export default function CreateContract({customer}) {

    const [products, setProducts] = useState(null);
    const [selectedProduct, setSelectedProduct] = useState(null);

    useEffect(() => {
        axios.get(MAPPING_POINT_PRODUCT_GET_ALL_PRODUCTS)
            .then(response => {
                setProducts(response.data);
                setSelectedProduct(response.data[0].name);
            })
            .catch(error => {
                swal({
                    title: '시스템 오류', text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.', icon: 'error', button: '확인',
                });
            });
    }, []);

    function findContractIDByName(name) {
        for (let i = 0; i < products.length; i++)
            if (products[i].name === name) return products[i].id;
        return undefined;
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        axios.post(MAPPING_POINT_CONTRACT_CREATE_CONTRACT, {
            product_id: findContractIDByName(selectedProduct),
            customer_id: customer.id
        })
            .then(response => {
                if (response.data)
                    swal({
                        title: '상품 가입 완료',
                        text: '상품 가입에 성공하였습니다.',
                        icon: 'success',
                        button: '확인',
                    });
                else{
                    swal({
                        title: '상품 가입 실패',
                        text: '이미 가입된 상품입니다.',
                        icon: 'error',
                        button: '확인',
                    });
                }
            })
            .catch(error => {
                swal({
                    title: '시스템 오류', text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.', icon: 'error', button: '확인',
                });
            });
    };

    if (products == null) return null;
    return (
        <div className={'create_contract'}>
            <h2>상품 가입</h2>
            <form onSubmit={handleSubmit}>
                <div className="form">
                    <SelectInput text={'상품 이름'} values={products.map(function (product) {
                        return product.name;
                    })} onChange={(e) => setSelectedProduct(e.target.value)}/>
                </div>
                <Button text={'가입'} type="submit"/>
            </form>
        </div>
    );
}