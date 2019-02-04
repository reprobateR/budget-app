var loadExpenseCategoriesFromService = function () {
    displayLoader();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log('Successful response ');
            let categories = captureCategoryResponse(this);
            console.log(categories);
            document.getElementById('categoryListId').appendChild(makeUL(categories));
            hideLoader();
        } else if (this.readyState === 4 && this.status != 200) {
            console.log(this.status);
            displayStatusMessages('error', 'Expense Category load failed, check server status!');
            hideLoader();
        }
    };
    xhttp.open("GET", categoryUrl, true);
    xhttp.send();
}

document.onreadystatechange = function (e) {
    if (document.readyState === 'complete') {
        loadExpenseCategoriesFromService();
    }
};

var descriptionDOM = document.querySelector('.add__description');

document.querySelector('.add__type').addEventListener('change', function (event) {

    if (event.target.value === 'expense') {
        descriptionDOM.value = 'Select Expense From Clipboard';
        descriptionDOM.style.color = 'red';
        descriptionDOM.disabled = 'true';
        document.querySelector('.add__btn').disabled = true;
    } else if (event.target.value === 'income') {
        descriptionDOM.value = '';
        descriptionDOM.disabled = false;
        descriptionDOM.style.color = 'black';
        document.querySelector('.add__btn').disabled = false;
    }
    var fields = document.querySelectorAll('.add__type' + ','
        + '.add__description' + ',' + '.add__value');
    fields.forEach(e => e.classList.toggle('red-focus'));

    var buttons = document.querySelectorAll('.add__btn' + ',' + '.custom-select');
    buttons.forEach(e => e.classList.toggle('red'));
});

var captureCategoryResponse = function (xhttp) {
    let categoryList = JSON.parse(xhttp.responseText);
    let categories = categoryList.map(cats => cats.category);
    return categories;
}

var modal = document.getElementById('categoryModal');
var btn = document.getElementById("categoryButton");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
}

function selectCategory(event) {
    var categoryValue = event.target.textContent;
    descriptionDOM.value = categoryValue;
    descriptionDOM.style.color = 'red';
    document.querySelector('.add__btn').disabled = false;
    modal.style.display = "none";
}

function makeUL(array) {
    var list = document.createElement('ul');
    list.setAttribute("class", "categoryList");
    for (var i = 0; i < array.length; i++) {
        var item = document.createElement('li');
        var fakeLink = document.createElement("span");
        fakeLink.textContent = array[i];
        fakeLink.setAttribute('class', 'fakeLinkStyle');
        fakeLink.setAttribute('onclick', 'selectCategory(event)');
        item.appendChild(fakeLink);
        list.appendChild(item);
    }
    return list;
}