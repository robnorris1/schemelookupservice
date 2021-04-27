async function findAll() {
    let policyType = document.getElementsByName("policyType")[0].options[document.getElementsByName("policyType")[0].selectedIndex].value;
    let repsonse = await fetch("/listScheme?" + "policyType=" + policyType)
        .then(response => response.json().then(repsonse => constructTable(repsonse)))

}

async function findBySchemeCode(){
    let policyType = document.getElementsByName("policyType")[0].options[document.getElementsByName("policyType")[0].selectedIndex].value;
    let schemeCode = document.getElementsByName("schemeCode")[0].value;
    let response = await fetch("/findScheme?" + "policyType=" + policyType + "&schemeCode=" + schemeCode)
        .then(response => response.json().then(repsonse => constructTable(repsonse)))
}


function clearTable(){
    let rows = document.getElementsByClassName("table")[0].tBodies[0].rows
    for(let i = rows.length-1; i >= 0 ; i--){
        document.getElementsByClassName("table")[0].tBodies[0].deleteRow(i);
    }
}

function constructTable(jsonObject) {
    console.log("function entered");
    clearTable();
    let schemes = jsonObject.schemes;
    console.log(schemes)
    schemes.forEach(scheme => {
        let row = document.getElementsByClassName("table")[0].tBodies[0].insertRow()
        for (key in scheme) {
            if (key != "enrichments") {
                row.insertCell().innerText = scheme[key]
            }
        }
    })
}