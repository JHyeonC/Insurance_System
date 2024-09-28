import React, {useState} from 'react';
import MainContents from './MainContents/MainContents'
import MenuButton from '../../common/MenuButton/MenuButton'
import './Main.css';

export default function Main({customer}) {

    const [mainContents, setMainContents] = useState('MyInfo');

    return (
        <div className='main'>
            <div className="menu">
                <ul>
                    <MenuButton text={'나의 정보'} name={'MyInfo'} setMainContents={setMainContents}></MenuButton>
                    <MenuButton text={'가입 상품 조회'} name={'ViewContracts'} setMainContents={setMainContents}></MenuButton>
                    <MenuButton text={'보상금 청구'} name={'CreateClaim'} setMainContents={setMainContents}></MenuButton>
                    <MenuButton text={'문의 조회'} name={'ViewBoard'} setMainContents={setMainContents}></MenuButton>
                    <MenuButton text={'상품 가입'} name={'CreateContract'} setMainContents={setMainContents}></MenuButton>
                </ul>
            </div>
            <div className="main_contents">
                <MainContents panelName={mainContents} customer={customer}/>
            </div>
        </div>
    );
}