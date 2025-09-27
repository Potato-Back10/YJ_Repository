document.addEventListener("DOMContentLoaded", function() {
    fetchTodos();

    document.getElementById("todoForm").addEventListener("submit", function(event) {
        event.preventDefault();
        addTodo();
    });
});

function fetchTodos() {
    fetch('/todos')
        .then(response => response.json())
        .then(data => {
            const todoList = document.getElementById('todoList');
            const completedList = document.getElementById('completedList');

            todoList.innerHTML = '';
            completedList.innerHTML = '';

            data.forEach(todo => {
                const li = createTodoListItem(todo);

                if (todo.done) {
                    completedList.appendChild(li);
                } else {
                    todoList.appendChild(li);
                }
            });
        });
}


function createTodoListItem(todo) {
    const li = document.createElement('li');
    li.className = "list-group-item d-flex justify-content-between align-items-center";

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = todo.done;
    checkbox.onclick = () => toggleTodoCompletion(todo.id);

    const taskText = document.createElement('span');
    taskText.textContent = todo.task;

    if (todo.done) {
        taskText.style.textDecoration = 'line-through';
        taskText.style.color = '#6c757d';
    }

    const deleteButton = document.createElement('button');
    deleteButton.textContent = '삭제';
    deleteButton.className = "btn btn-danger btn-sm ml-2";
    deleteButton.onclick = () => deleteTodo(todo.id);

    li.appendChild(checkbox);
    li.appendChild(taskText);
    li.appendChild(deleteButton);

    return li;
}

function addTodo() {
    const taskInput = document.getElementById("task");
    const task = taskInput.value;

    const newTodo = {
        task: task,
        done: false
    };

    fetch('/todos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTodo)
    })
        .then(response => response.json())
        .then(todo => {
            fetchTodos();
            taskInput.value = '';
        });
}

function toggleTodoCompletion(id) {
    fetch(`/todos/${id}/toggle`, {
        method: 'PUT',
    })
        .then(() => {
            fetchTodos();
        });
}

function deleteTodo(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        fetch(`/todos/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                fetchTodos();
            });
    }
}