const API_URL = "https://swapi.dev/api/people/";

const nombre = document.getElementById("nombre");
const world = document.getElementById("world");

const noPeople = document.getElementById("noPeople");

const getPeople = () => {
    fetch(`${API_URL}${noPeople.value}`)
    .then(response => response.json())
    .then(data => {
        nombre.innerHTML = data.name

        fetch(data.homeworld)
        .then(response => response.json())
        .then(data => world.innerHTML = data.name)
        .catch(e => console.log(e));
    })
    .catch(e => console.log(e));
};