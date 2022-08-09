const loadPaintings = document.getElementById('loadPaintings')

loadPaintings.addEventListener('click', onLoadPaintings);

function onLoadPaintings(e) {
e.preventDefault();
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    const paintingsDiv = document.getElementById('paintings');
    paintingsDiv.innerHTML = '';

    fetch("http://localhost:8080/api/paintings/", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(painting => {
            const aEl = document.createElement('a');
            aEl.textContent = painting.title;
            aEl.classList.add("dropdown-item");
            let link = "http://localhost:8080/paintings/details/" + painting.paintingId;
            aEl.href = link;
            paintingsDiv.appendChild(aEl);
        }))
        .catch(error => console.log('error', error));
}