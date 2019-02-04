<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:100,300,400,600" rel="stylesheet" type="text/css">
    <link href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
    <title>Budget App Home</title>
</head>

<body>

    <div class="top">
        <div class="budget">
            <div class="budget__title">
                Available Budget in <span class="budget__title--month">%Month%</span>:
            </div>

            <div class="budget__value">+ 2,345.64</div>

            <div class="budget__income clearfix">
                <div class="budget__income--text">Income</div>
                <div class="right">
                    <div class="budget__income--value">+ 4,300.00</div>
                    <div class="budget__income--percentage">&nbsp;</div>
                </div>
            </div>

            <div class="budget__expenses clearfix">
                <div class="budget__expenses--text">Expenses</div>
                <div class="right clearfix">
                    <div class="budget__expenses--value">- 1,954.36</div>
                    <div class="budget__expenses--percentage">45%</div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom">
        <div class="add">
            <div class="add__container">
                <select class="add__type">
                    <option value="income" selected>+</option>
                    <option value="expense">-</option>
                </select>
                <input type="text" class="add__description" placeholder="Add description">
                <input type="number" class="add__value" placeholder="Value">
                <button class="add__btn"><i class="ion-ios-checkmark-outline"></i></button>
                <!--Expense Categories-->
                <button id="categoryButton" class="custom-select"><i class="icon ion-clipboard"></i></button>
                <button class="save_budget"><i class="ion-ios-download"></i></button>
                <button class="output_budget"><i class="icon ion-android-cloud-done"></i></button>
            </div>
        </div>

        <!-- The Expense Cateogry Modal -->
        <div id="categoryModal" class="modal">
            <!-- Expense Modal content -->
            <div class="modal-content">
                <span class="close"><i class="icon ion-close-circled"></i></span>
                <div id="categoryListId"></div>
            </div>

        </div>

        <div id="ajaxLoader" style="display:none" class="ajaxLoaderClass">
            <i class="fa fa-spinner fa-spin" id="loaderSpinner" style="font-size:240px;color:#28B9B5"></i>
        </div>

        <div id="statusMessages" class="statusMessageClass"><p id="textMessage"></p></div>

        <div class="container clearfix">

            <div class="income">
                <h2 class="icome__title">Income</h2>
                <div class="income__list">
                </div>
            </div>

            <div class="expenses">
                <h2 class="expenses__title">Expenses</h2>

                <div class="expenses__list">

                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <script src="${pageContext.request.contextPath}/js/custom.js"></script>
</body>

</html>