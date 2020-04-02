import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Button} from 'antd';

function App() {
    const [data, setData] = useState({data: "您好"});

    const getData = () => {
        return fetch("/api/test/hello").then(response => response.json()).then(data => {
            console.log(data);
            return data;
        })
    }
    useEffect( () => {
        getData().then(setData);
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    {data.data}
                </p>
                <Button>1111</Button>
            </header>
        </div>
    );
}

export default App;
