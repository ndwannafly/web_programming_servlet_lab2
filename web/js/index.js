window.onload = () => {
    function fetchMe(){
        console.log('fetching.... ')
        fetch('/lab-2_war/data-process', {
            method: 'GET',
            headers: {
                'Authorization': 'Pizdets'
            },
        }) . then(response => {
            if(response.ok){
                window.location.href = './table.jsp'
            } else{
                window.location.href = './login.jsp'
            }
        })
    }
    fetchMe();
}