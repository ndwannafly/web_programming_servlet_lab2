window.onload = () => {
// signup
    let username = document.getElementById('username_signup');
    let password = document.getElementById('password_signup');
    let signupButton = document.getElementById('signup-button');

    signupButton.addEventListener('click', () => {
        console.log(username.value);
        console.log(password.value);
        fetch(`/lab-2_war/signup-process?username=${username.value}&password=${password.value}`, {
            method: 'GET',
            headers: {
                'Authorization': 'signup'
            },
        }).then(response => {
            if (response.ok) {
                window.location.href = './table.jsp'
            } else {
                alert("Problem in Server!");
            }
        })
    })
}