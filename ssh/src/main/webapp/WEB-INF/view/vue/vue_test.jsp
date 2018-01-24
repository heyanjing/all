<%@page language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>vuetitle中的内容</title>
    <meta name='description' content='vuehead中的内容'>
    <link rel="stylesheet" type="text/css" href="${CSS}/index.css?${V}"/>
</head>
<body>

<div id="app">
    <div v-bind:title="message">{{ message }}</div>
    <div v-bind:title="message">{{ reversedMessage}}</div>
    <div v-if="seen">现在你看到我了</div>
    <ul>
        <li v-for="todo in todos">{{todo.text}}</li>
    </ul>
    <button v-on:click="reverseMessage">逆转消息</button>
    <div><input v-model="message"></div>
    <ul>
        <todo-item v-for="item in groceryList" v-bind:key="item.id" v-bind:todo="item" v-bind:title="'x-'+item.id" ></todo-item>
    </ul>
</div>

<script type="text/javascript" src="${JS}/vue/vue_test.js?${V}"></script>

</body>
</html>