const categoryUrl = 'http://localhost:8083/budget-app/category';
const saveBudgetUrl = 'http://localhost:8083/budget-app/save';

var displayLoader = function () {
    document.getElementById('ajaxLoader').style.display = 'block';
    document.getElementById('loaderSpinner').style.display = 'inline-block';
}

var hideLoader = function () {
    myVar = setTimeout(function () {
        document.getElementById('ajaxLoader').style.display = 'none';
    }, 1000);
}

var displayStatusMessages = function (type, message) {
    let textMessageDOM = document.getElementById('textMessage');
    document.getElementById('statusMessages').style.display = 'block';
    textMessageDOM.innerText = message;
    textMessageDOM.style.display = 'inline-block';
    if(type ===  'error'){
        textMessageDOM.style.color = 'red';
    } else {
        textMessageDOM.style.color = 'green';
    }
}

var transactions = {
    data: {
        expenses: [],
        incomes: []
    },
    budget: {
        budgetValue: 0,
        expensePercentage: - 1,
        incomeSum: 0,
        expenseSum: 0
    }
}

var budgetController = (function () {

    var addItem = function (item) {

        if (item.type === 'income') {
            var id = 0;
            if (transactions.data.incomes.length === 0) {
                id = 1;
            } else {
                var incomeArray = transactions.data.incomes;
                var incomeLength = transactions.data.incomes.length;
                var latestId = incomeArray[incomeLength - 1].id;
                id = latestId + 1;
            }
            item.id = id;
            transactions.data.incomes.push(item);
        } else if (item.type === 'expense') {
            var id = 0;
            if (transactions.data.expenses.length === 0) {
                id = 1;
            } else {
                var expenseArray = transactions.data.expenses;
                var expenseLength = transactions.data.expenses.length;
                var latestId = expenseArray[expenseLength - 1].id;
                id = latestId + 1;
            }
            item.id = id;
            transactions.data.expenses.push(item);
        }
        return item;
    }

    function getSum(values) {
        var sum = 0;
        values.forEach(function (value) {
            sum += value.value;
        })
        return sum;
    }

    var calculateBudget = function () {

        var incomeSum = getSum(transactions.data.incomes);
        var expenseSum = getSum(transactions.data.expenses);

        transactions.budget.budgetValue = (incomeSum - expenseSum);
        if (incomeSum > 0) {
            transactions.budget.expensePercentage = parseFloat((expenseSum / incomeSum) * 100).toFixed(2);
        } else {
            transactions.budget.expensePercentage = 0.00;
        }
        transactions.budget.incomeSum = incomeSum;
        transactions.budget.expenseSum = expenseSum;

        return transactions.budget;

    }

    var calculatePercentages = function () {
        if (transactions.budget.incomeSum > 0) {
            transactions.data.expenses.forEach(calculatePercentage);
        } else {

        }
    }

    var calculatePercentage = function (currentExpense, index, expenseArray) {
        var currExpPercent = parseFloat((currentExpense.value / transactions.budget.incomeSum) * 100).toFixed(2);
        currentExpense.item__percentage = currExpPercent;
    }

    var deleteItem = function (item) {
        
        var deleteItems = item.split('-');
        var deleteType = deleteItems[0];
        var deleteId = deleteItems[1];

        if (deleteType === 'income') {
            var newIncomeArray = transactions.data.incomes.filter(item => item.id != deleteId);
            transactions.data.incomes = newIncomeArray;
        } else if (deleteType === 'expense') {
            var newExpenseArray = transactions.data.expenses.filter(item => item.id != deleteId);
            transactions.data.expenses = newExpenseArray;
        }
    }

    return {
        addItem: addItem,
        calculateBudget: calculateBudget,
        deleteItem: deleteItem,
        calculatePercentages: calculatePercentages
    }

}());

var uiController = (function () {

    var Item = function (type, description, value, id, item__percentage) {
        this.type = type;
        this.description = description;
        this.value = value;
        this.id = id;
        this.item__percentage = item__percentage;
    }

    var getInput = function () {

        var type = document.querySelector('.add__type').value;
        var description = document.querySelector('.add__description').value;
        var value = parseFloat(document.querySelector('.add__value').value);
        var item = new Item(type, description, value);

        return item;
    }


    var addListItem = function (type, data) {

        var htmlTemplate, finalHtml;
        var domElement;

        if (type === 'income') {

            domElement = '.income__list';
            htmlTemplate = '<div class="item clearfix" id="income-%id%"><div class="item__description">%description%</div><div class="right clearfix"><div class="item__value">+ %value%</div><div class="item__delete"><button class="item__delete--btn"><i class="ion-ios-close-outline"></i></button> </div></div></div>';

        } else if (type === 'expense') {
            domElement = '.expenses__list';
            htmlTemplate = '<div class="item clearfix" id="expense-%id%"><div class="item__description">%description%</div><div class="right clearfix"><div class="item__value">- %value%</div><div class="item__percentage">%N%</div><div class="item__delete"><button class="item__delete--btn"><i class="ion-ios-close-outline"></i></button></div></div></div>';
        }

        finalHtml = htmlTemplate.replace('%id%', data.id);
        finalHtml = finalHtml.replace('%description%', data.description);
        finalHtml = finalHtml.replace('%value%', data.value);

        var domParent = document.querySelector(domElement);
        domParent.insertAdjacentHTML('beforeend', finalHtml);

    }

    var clearFields = function (type) {
        var descriptionDOM = document.querySelector('.add__description');
        if (type === 'income') {
            document.querySelector('.add__description').value = '';
            document.querySelector('.add__value').value = '';
            document.querySelector('.add__description').focus();
        } else if (type === 'expense') {
            descriptionDOM.value = 'Select Expense From Clipboard';
            descriptionDOM.style.color = 'red';
            descriptionDOM.disabled = 'true';
            document.querySelector('.add__value').value = '';
            document.querySelector('.add__btn').disabled = true;
        }
    }

    var getMonth = function () {
        var d = new Date();
        var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        document.querySelector('.budget__title--month').innerHTML = months[d.getMonth()];
    }

    var displayBudget = function (budget) {
        document.querySelector('.budget__value').innerHTML = budget.budgetValue;
        document.querySelector('.budget__income--value').innerHTML = '+ ' + budget.incomeSum;
        document.querySelector('.budget__expenses--value').innerHTML = '- ' + budget.expenseSum;
        document.querySelector('.budget__expenses--percentage').innerHTML = budget.expensePercentage;
    }

    var displayPercentages = function () {
        var expenseListId = transactions.data.expenses.map(e => e.type + '-' + e.id);
        for (var i = 0; i < expenseListId.length; i++) {
            var expenseHtml = document.getElementById(expenseListId[i]);
            expenseHtml.querySelector('.item__percentage').innerHTML = transactions.data.expenses[i].item__percentage;
        }
    }

    var deleteListItem = function (deleteId) {
        var deleteListItemElement = document.getElementById(deleteId);
        deleteListItemElement.parentNode.removeChild(deleteListItemElement);
    }

    var formatNumbers = function(number, type){

        
    }

    return {
        getInput: getInput,
        getMonth: getMonth,
        addListItem: addListItem,
        clearFields: clearFields,
        displayBudget: displayBudget,
        deleteListItem: deleteListItem,
        displayPercentages: displayPercentages
    }

}());

var appController = (function (budgetCntrl, uiCntrl) {

    function setupEventListeners() {
        document.querySelector('.add__btn').addEventListener("click", cntrlAddItem);
        document.addEventListener("keyup", function (event) {
            if (event.keyCode === 13 || event.which === 13) {
                cntrlAddItem();
            }
        });
        document.querySelector('.container').addEventListener('click', cntrlDeleteItem);
        document.querySelector('.save_budget').addEventListener('click', saveBudgetData);
        document.querySelector('.output_budget').addEventListener('click', function () {
            window.location="output";
        });
    }

    var cntrlAddItem = function () {

        var item = uiCntrl.getInput();

        if (!isNaN(item.value) && item.value !== 0 && item.description != '' && item.description != null) {

            var data = budgetCntrl.addItem(item);
            uiCntrl.addListItem(data.type, data);
            uiCntrl.clearFields(data.type);
            updateBudget();
            updatePercentages();

        }
    }

    var cntrlDeleteItem = function (event) {
        if (transactions.data.expenses.length > 0 | transactions.data.incomes.length > 0) {
            var item;
            var node = event.target;
            item = node.parentNode.parentNode.parentNode.parentNode.id;
            budgetCntrl.deleteItem(item);
            uiCntrl.deleteListItem(item);
            updateBudget();
        }
    }

    var saveBudgetData = function () {
        // console.log(JSON.stringify(transactions));
        // var xhttp = new XMLHttpRequest();
        // xhttp.onreadystatechange = function () {
        //     if (this.status == 200) {
        //         console.log(this.responseText);
        //     }
        // };
        // xhttp.open("POST", saveBudgetUrl, true);
        // xhttp.setRequestHeader("Content-type", "application/json");
        // xhttp.send(JSON.stringify(transactions));    
        displayLoader();
        hideLoader();
    }

    var updateBudget = function () {
        var budget = budgetCntrl.calculateBudget();
        uiCntrl.displayBudget(budget);
    }

    var updatePercentages = function () {
        budgetCntrl.calculatePercentages();
        if (transactions.budget.incomeSum > 0) {
            uiCntrl.displayPercentages();
        }
    }

    return {
        init: function () {
            setupEventListeners();
            uiCntrl.getMonth();
            uiCntrl.displayBudget({
                budgetValue: 0,
                expensePercentage: - 1,
                incomeSum: 0,
                expenseSum: 0
            });
        }
    }

}(budgetController, uiController));

appController.init();

