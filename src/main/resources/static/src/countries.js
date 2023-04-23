document.addEventListener("DOMContentLoaded", function () {
    const submitBtn = document.getElementById("submit-btn");
    submitBtn.addEventListener("click", fetchCountries);
})

async function fetchCountries() {
    const continent = document.getElementById("continent").value;
    const numCountries = document.getElementById("num-countries").value;
    const url = `http://localhost:8080/countries/details/random?continent=${continent}&numRandom=${numCountries}`;

    const loadingDiv = document.getElementById("loading-overlay");
    loadingDiv.classList.remove("d-none");

    const errorDiv = document.getElementById("error");
    errorDiv.classList.add("d-none");

    try {
        const response = await fetch(url);
        const countries = await response.json();

        // Sortowanie krajów alfabetycznie po nazwie
        countries.sort((a, b) => a.name.localeCompare(b.name));

        console.log(countries)

        const table = document.getElementById("countries-table");
        const tbody = table.getElementsByTagName("tbody")[0];
        tbody.innerHTML = "";

        countries.forEach((country) => {
            const tr = document.createElement("tr");
            const nameTd = document.createElement("td");
            const nativeNameTd = document.createElement("td");
            const capitalTd = document.createElement("td");
            const currencyTd = document.createElement("td");
            const populationTd = document.createElement("td");
            const subregionTd = document.createElement("td");
            const languagesTd = document.createElement("td");

            nameTd.innerText = country.name;
            nativeNameTd.innerText = country.nativeName;
            capitalTd.innerText = country.capital;
            currencyTd.innerText = country.currencies;
            populationTd.innerText = country.population;
            subregionTd.innerText = country.subregion;

            const languagesArray = country.languages.split(",");
            languagesTd.innerText = languagesArray.join(", ");

            tr.appendChild(nameTd);
            tr.appendChild(nativeNameTd);
            tr.appendChild(capitalTd);
            tr.appendChild(currencyTd);
            tr.appendChild(populationTd);
            tr.appendChild(subregionTd);
            tr.appendChild(languagesTd);
            tbody.appendChild(tr);
        });

        table.classList.remove("d-none");
    } catch (error) {
        console.error(error);
        const table = document.getElementById("countries-table");
        const errorDiv = document.getElementById("error");
        errorDiv.classList.remove("d-none");
        table.classList.add("d-none");
    }

    loadingDiv.classList.add("d-none");
}
