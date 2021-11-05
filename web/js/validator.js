window.onload = () => {
    function fetchMe(){
        console.log('fetching.... ')
        fetch('/lab-2_war/data-process', {
            method: 'GET',
            headers: {
                'Authorization': 'Pizdets'
            },
        }) . then(response => {
            if(!response.ok){
                window.location.href = './login.jsp'
            }
        })
    }
    fetchMe();

    function isNumeric(str) {
        if (typeof str != "string") return false;
        return !isNaN(str) && !isNaN(parseFloat(str));
    }

//init board
    let now;
    let board = JXG.JSXGraph.initBoard('box1', {
        axis: true,
        boundingbox: [-7.0, 7.0, 7.0, -7.0]
    });

//sliders
    let r = board.create('slider', [[1, 4], [5, 4], [1, 5, 5]], {name: 'R', snapWidth: 1});
    let R = () => r.Value();
    let minusR = () => -r.Value();
    let halfR = () => r.Value() / 2;


    function redirect(body, url) {

        fetch(url, {
            method: 'GET',
        }).then(response => {
            if(response.ok){
                board.create('point', [body.x, body.y], {
                    color: 'green',
                    label: {visible: false}
                })
                window.location.href = "./result.jsp";
            } else{
                board.create('point', [body.x, body.y], {
                    color: 'red',
                    label: {visible: false}
                })
                if(response.status === 500) window.location.href = `./data-process?x=${body.x}&y=${body.y}&r=${body.r}`;
                else window.location.href = "./result.jsp";
            }
        })
        /*$.ajax({
            type: "GET",
            url: url,
            success: () => {
                board.create('point', [body.x, body.y], {
                    color: 'green',
                    label: {visible: false}
                })
                window.location.href = "./result.jsp";
            },
            error: () => {
                board.create('point', [body.x, body.y], {
                    color: 'red',
                    label: {visible: false}
                })
                window.location.href = `./data-process?x=${body.x}&y=${body.y}&r=${body.r}`;
            }
        })*/
    }

    const getXY = (e, i) => {
        let currentPos = board.getCoordsTopLeftCorner(e, i),
            absolutePos = JXG.getPosition(e, i),
            dx = absolutePos[0] - currentPos[0],
            dy = absolutePos[1] - currentPos[1];

        let jxgCoordinate = new JXG.Coords(JXG.COORDS_BY_SCREEN, [dx, dy], board);
        let x = jxgCoordinate.usrCoords[1].toFixed(2);
        let y = jxgCoordinate.usrCoords[2].toFixed(2);
        return {x, y};
    }

    let current = CodeMirror.fromTextArea(document.getElementById('current'), {
        lineNumbers: true,
        styleActiveSelected: true,
        mode: 'text',
        readOnly: true
    })


    current.setSize(null, 30);
    current.setValue('(0, 0)');


// second corner (triangle)
    board.create('polygon', [[0, 0], [halfR, 0], [0, minusR]], {
        fillColor: '#e74c3c',
        strokeColor: 'none',
        withLines: false,
        vertices: {visible: false}
    });

//third corner (square)
    board.create('polygon', [[0, 0], [minusR, 0], [minusR, R], [0, R]], {
        fillColor: '#3498db',
        strokeColor: 'none',
        withLines: false,
        vertices: {visible: false}
    });

//quarter corner (quarter circle)
    let quarterCircle = X => Math.sqrt((r.Value() / 2 - X) * (r.Value() / 2 + X));
    board.create('functiongraph', [quarterCircle, 0, halfR], {
        fillColor: '#16a085',
        fillOpacity: 0.3,
        strokeColor: 'none',
        highlight: false,
        withLines: false,
        vertices: {visible: false}
    });

    board.create('polygon', [[0, 0], [halfR, 0], [0, halfR]], {
        fillColor: '#16a085',
        strokeColor: 'none',
        withLines: false,
        vertices: {visible: false}
    })

    document.getElementById('box1').addEventListener('mousemove', (e, i) => {
        let {x, y} = getXY(e, i);
        current.setValue('(' + x + ', ' + y + ')');
    });

    document.getElementById('box1').addEventListener('mousedown', (e, i) => {
        let {x, y} = getXY(e, i);
        now = new Date().toString().substr(15, 9);
        redirect({x: x, y: y, r: r.Value()}, `/lab-2_war/data-process?x=${x}&y=${y}&r=${r.Value()}`);
    });

    document.getElementById('submit-button').addEventListener('click', () => {

        let yValueList = document.getElementsByName('y_value');
        let xValue = document.getElementById('x_value');
        let rValueList = document.getElementsByName('r_value');


        if (!isNumeric(xValue.value)) {
            alert('Please enter X in correct format!');
            return;
        }

        /*       if(xValue.value === "" || xValue.value < -3.0 || xValue.value > 5.0){
                   alert('Please enter X with value from -3.0 to 5.0');
                   return ;
               }*/

        let x = xValue.value;
        let y;
        let r;

        for (let i = 0; i < yValueList.length; i++) {
            if (yValueList[i].checked) {
                y = yValueList[i].value;
            }
        }

        for (let i = 0; i < rValueList.length; i++) {
            if (rValueList[i].checked) {
                r = rValueList[i].value;
            }
        }

        now = new Date().toString().substr(15, 9);
        redirect({x: x, y: y, r: r}, `/lab-2_war/data-process?x=${x}&y=${y}&r=${r}`);
    })


    let overviewInput = document.getElementById('overview');
    let constraintInput = document.getElementById('constraint');
    let notationInput = document.getElementById('notation');

    let overviewContent = document.getElementById('overviewContent');
    let constraintContent = document.getElementById('constraintContent');
    let notationContent = document.getElementById('notationContent');

    overviewInput.addEventListener('click', () => {
        if (!overviewInput.classList.contains('checked')) {
            overviewInput.classList.toggle('checked');
            overviewContent.style.display = 'block';
        }
        if (constraintInput.classList.contains('checked')) {
            constraintInput.classList.toggle('checked');
            constraintContent.style.display = 'none';

        }
        if (notationInput.classList.contains('checked')) {
            notationInput.classList.toggle('checked');
            notationContent.style.display = 'none';

        }
    })

    constraintInput.addEventListener('click', () => {
        if (overviewInput.classList.contains('checked')) {
            overviewInput.classList.toggle('checked');
            overviewContent.style.display = 'none';
        }
        if (!constraintInput.classList.contains('checked')) {
            constraintInput.classList.toggle('checked');
            constraintContent.style.display = 'block';

        }
        if (notationInput.classList.contains('checked')) {
            notationInput.classList.toggle('checked');
            notationContent.style.display = 'none';
        }
    })

    notationInput.addEventListener('click', () => {
        if (overviewInput.classList.contains('checked')) {
            overviewInput.classList.toggle('checked');
            overviewContent.style.display = 'none';

        }
        if (constraintInput.classList.contains('checked')) {
            constraintInput.classList.toggle('checked');
            constraintContent.style.display = 'none';

        }
        if (!notationInput.classList.contains('checked')) {
            notationInput.classList.toggle('checked');
            notationContent.style.display = 'block';
        }
    })
}