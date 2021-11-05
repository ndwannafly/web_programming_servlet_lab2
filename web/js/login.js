window.onload = () => {
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    let submitButton = document.getElementById('login-button');


    submitButton.addEventListener('click', () => {
        console.log(username.value);
        console.log(password.value);
        fetch(`/lab-2_war/login-process?username=${username.value}&password=${password.value}`, {
            method: 'GET',
            headers: {
                'Authorization': 'login'
            },
        }) . then(response => {
            if(response.ok){
                window.location.href = './table.jsp'
            } else{
                alert("Wrong username or password!");
            }
        })
    })
}

