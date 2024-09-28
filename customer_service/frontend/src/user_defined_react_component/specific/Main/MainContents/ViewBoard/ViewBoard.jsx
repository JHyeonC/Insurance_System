import React, {useEffect, useState} from "react";
import axios from "axios";
import './ViewBoard.css'
import {MAPPING_POINT_BOARD_GET_ALL_BOARD, MAPPING_POINT_BOARD_CREATE_BOARD} from '../../../../common/MappingPoint/MappingPoint.js'
import swal from "sweetalert";
import Button from "../../../../common/Button/Button";
import TextInput from "../../../../common/TextInput/TextInput";

export default function ViewBoard({customer}) {

    const [writeMode, setWriteMode] = useState(false);
    const [boards, setBoards] = useState(null);

    function updateBoards() {
        axios.get(MAPPING_POINT_BOARD_GET_ALL_BOARD)
            .then(response => {
                setBoards(response.data);
            })
            .catch(error => {
                swal({
                    title: '시스템 오류', text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.', icon: 'error', button: '확인',
                });
            });
    }

    useEffect(() => {
        updateBoards();
    }, []);


    if (!writeMode) return <BoardTable boards={boards} setWriteMode={setWriteMode}/>;
    return <WriteBoard customer={customer} setWriteMode={setWriteMode} updateBoards={updateBoards}/>
}

function BoardTable({boards, setWriteMode}) {

    let boardTableRows;

    if (boards !== null) {
        boardTableRows = boards.map(board => <tr>
            <td>{board.title}</td>
            <td>{board.author}</td>
            <td>{board.content}</td>
            <td>{board.answer}</td>
            <td>{board.answerer}</td>
        </tr>);
    } else boardTableRows = "loading...";

    return (
        <div className={'board_table'}>
            <h2>문의 게시판</h2>
            <table>
                <tr>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>내용</th>
                    <th>답변</th>
                    <th>답변자</th>
                </tr>
                {boardTableRows}
            </table>
            <Button text={'새 문의 작성'} onClick={e => setWriteMode(true)}/>
        </div>
    );
}

function WriteBoard({customer, setWriteMode, updateBoards}) {

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    function handleSubmit(e) {
        e.preventDefault();
        axios.post(MAPPING_POINT_BOARD_CREATE_BOARD, {
            author: customer.id,
            title: title,
            content: content
        })
            .then(response => {
                swal({
                    title: '문의 작성 완료',
                    text: '문의가 작성되었습니다.',
                    icon: 'success',
                    button: '확인',
                });
                updateBoards();
            })
            .catch(error => {
                swal({
                    title: '시스템 오류', text: '잠시후 다시 시도해주세요. 불편을 드려 죄송합니다.', icon: 'error', button: '확인',
                });
            });
        setWriteMode(false);
    }

    return (
        <div className={'board_table'}>
            <form onSubmit={handleSubmit}>
                <TextInput text={'제목'} value={title} onChange={(e) => setTitle(e.target.value)}/>
                <TextInput text={'내용'} value={content} onChange={(e) => setContent(e.target.value)}/>
                <Button text={'제출'}/>
            </form>
        </div>
    );
}