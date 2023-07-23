function showAdminField() {
    var checkBox = document.getElementById("admin_permission");
    var adminKeyField = document.getElementById("admin_key_field");

    if (checkBox.checked == true){
        adminKeyField.style.display = "block";
    } else {
        adminKeyField.style.display = "none";
    }
}

function verifyAdminKey() {
    var adminKey = document.getElementById("admin_key").value;
    // 여기에 키 값을 검증하는 코드를 추가하실 수 있습니다.
    alert('Admin 키: ' + adminKey);
}

