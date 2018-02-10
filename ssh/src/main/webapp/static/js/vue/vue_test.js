$(function () {
    Vue.component('todo-item', {
        props: ['todo'],
        template: '<li>{{ todo.text }}</li>'
    })
    var app = new Vue({
        el: '#app',
        components: {
            // <my-component> 将只在父组件模板中可用
            'my-component': {
                template: '<div>A custom component!</div>'
            }
        },
        data: {
            message: '这是一段文本',
            seen: true,
            todos: [
                {text: '学习 JavaScript'},
                {text: '学习 Vue'},
                {text: '整个牛项目'}
            ],
            groceryList: [
                {id: 0, text: '蔬菜'},
                {id: 1, text: '奶酪'},
                {id: 2, text: '随便其它什么人吃的东西'}
            ]
        },
        watch: {
            // 如果 `question` 发生改变，这个函数就会运行
            message: function (newQuestion, oldQuestion) {
                this.answer = 'Waiting for you to stop typing...'
                this.getAnswer()
            }
        },
        methods: {
            reverseMessage: function (e) {
                console.log(e.target.innerHTML);
                console.log(this);
                this.message = this.message.split('').reverse().join('')
            },
            reversedMessage: function () {
                return this.message.split('').reverse().join('')
            }
        },
        computed: {
            reversedMessage: function () {
                return this.message.split('').reverse().join('')
            }
        },
        created: function () {
            console.log('vue实例化后   message is: ' + this.message)
        },
    })
    setTimeout(function () {
        app.message = "sssxxx";
        app.seen = false;
        app.todos.push({text: '新项目'})
    }, 5000)

});

