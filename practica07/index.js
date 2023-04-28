const nombre = document.getElementById("nombre");
const world = document.getElementById("world");

fetch("https://swapi.dev/api/people/1")
    .then(response => response.json())
    .then(data => {
        nombre.innerHTML = data.name

        fetch(data.homeworld)
        .then(response => response.json())
        .then(data => world.innerHTML = data.name)
        .catch(e => console.log(e));
    })
    .catch(e => console.log(e));