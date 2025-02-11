import React, {useState} from 'react';
import './Login.css';
import axios from "axios";
import swal from 'sweetalert'
import TextInput from "../../common/TextInput/TextInput";
import Button from "../../common/Button/Button";
import {MAPPING_POINT_CUSTOMER_LOGIN} from '../../common/MappingPoint/MappingPoint.js'

export default function Login({setAuthenticated, setCustomer}) {

    const [userId, setUserId] = useState('');
    const [userPassword, setUserPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        axios.get(MAPPING_POINT_CUSTOMER_LOGIN, {
            params: {
                id: userId,
                password: userPassword,
            }
        })
            .then(response => {
                if (response.data === '')
                    swal({
                        title: '로그인 실패',
                        text: '아이디 및 비밀번호를 다시 확인해주세요.',
                        icon: 'error',
                        button: '확인',
                    });
                else {
                    swal({
                        title: '로그인 성공',
                        text: '환영합니다, ' + response.data.name + '님!',
                        icon: 'success',
                        button: '확인',
                    });
                    setCustomer({
                        "id": response.data.id,
                        "name": response.data.name,
                        "birth": response.data.birth,
                        "gender": response.data.gender,
                        "occupational_hazard": response.data.occupational_hazard,
                        "smoking": response.data.smoking
                    });
                    setAuthenticated(true);
                }
            })
            .catch(e => {
                swal({
                    title: '시스템 오류',
                    text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.',
                    icon: 'error',
                    button: '확인',
                });
            });
    };

    return (
        <body>
        <div className="login-space">
            <h2>우리보험사 로그인</h2>
            <form onSubmit={handleSubmit}>
                <TextInput text={'아이디'} value={userId} onChange={(e) => setUserId(e.target.value)}/>
                <TextInput text={'비밀번호'} type={'password'} value={userPassword}
                           onChange={(e) => setUserPassword(e.target.value)}/>
                <Button text={'로그인'}/>
            </form>
        </div>
        </body>
    );
}