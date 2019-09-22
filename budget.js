$(document).ready(function () {

    $("#download-btn").click(function () {
        
        let transactions = getTransactions();

        $.ajax({
            type: "POST",
            url: "http://localhost:8083/budget-app/save",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(transactions),
            success: function(result){
                console.log(result);
            },
            dataType:"text"
          });
 
    });

    $("#log-in").click(function () {
        alert("Download Button Clicked!")
    });

});

function getTransactions() {
    let tnxList = [];
    $('.income__list').children('div').each(function () {
        let id = $(this).attr('id');
        if (id.startsWith("inc-")) {
            let tnx = createTransaction(id);
            tnxList.push(tnx);
        }
    });
    $('.expenses__list').children('div').each(function () {
        let id = $(this).attr('id');
        if (id.startsWith("exp-")) {
            let tnx = createTransaction(id);
            tnxList.push(tnx);
        }
    });
    return tnxList;
}

function createTransaction(id) {
        let type = getType(id);
        let name = $("#" + id).find('.item__description').html();
        let value = $("#" + id).find('.item__value').html();
        return new Transaction(type, name, value);
}

function getType(id) {
    if (id.startsWith("inc-")) {
        return "income";
    } else if (id.startsWith("exp-")) {
        return "expense";
    }
}

function Transaction(type, name, value) {
    this.type = type;
    this.name = name;
    this.value = value;
}