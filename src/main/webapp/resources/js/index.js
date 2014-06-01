window.onload = ajaxCallTable();

function submitForm() {
    $.ajax({
        type: "POST",
        url: "/add",
        data: $("#createRecordForm").serialize(),
        success: function () {
            if (formValidation()) {
                validateEqualNames();
                $("#createRecordForm").each(function () {
                    this.reset();
                });
            }
        },
        error: function (e) {
            toastr.error("Error: " + e);
        }
    });
    return false;
}

function formValidation() {
    if ($('#createRecordForm').validationEngine('validate')) {
        return true;
    }
}

function validateEqualNames() {
    $.ajax({
        type: "GET",
        url: "/validate",
        success: function (boolFlag) {
            console.log("boolFlag is " + boolFlag);
            if (boolFlag === false) {
                toastr.error("A person with this name already exists in the database!");
            } else {
                toastr.success("The record has been added successfully.");
                ajaxCallTable();
            }
        }
    });
}

function ajaxCallTable() {
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            makeTheTable(xmlhttp.responseText);
        }
    };
    xmlhttp.open("GET", "/showData");
    xmlhttp.send();
}

function makeTheTable(incomingJson) {
    var str = ("<table class=\"table table-condensed\" id=\"tablePhones\">" +
        "<thead><tr><td>NAME</td><td>PHONE</td></tr></thead><tbody>");
    var obj = jQuery.parseJSON(incomingJson);
    console.log("obj.length = " + obj.length);
    for (var i = 0; i < obj.length; i++) {
        str += ("<tr>");
        str += ("<td>" + obj[i].name + "</td>");
        str += ("<td>" + obj[i].telephone + "</td>");
        str += ("<td><a href=\"\" class=\"remove\">Remove</a></td>");
        str += ("</tr>");
    }
    str += "</tbody></table>";
    $("#tablePhoneDiv").html(str);
}