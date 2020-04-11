$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        calculateData();
    }
});

function calculateData() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/manager/chronic/algorithmicPrediction/calculateData",
        data : $('#signupForm').serialize(),
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code != 0) {
                layer.msg("Error! " + data.msg, {time:2000,icon:5});
                return;
            }
            layer.msg("预测成功！", {time:2000,icon:1});
            ajaxInfoHtml(data);
        }
    });
}

function ajaxInfoHtml(data) {
    var yes = data.yes;
    var no = data.no;
    var result = data.result;
    if (result === "yes") {
        result = "预测患有糖尿病概率大";
    }else {
        result = "预测没有患有糖尿病概率大";
    }
    var htmlText ='<div id="predictResult" class="text-left col-sm-11 excerpt"><h4> 预测结果：'+ result +'</h4><h4> 患糖尿病概率：'+ yes +'</h4><h4> 不患糖尿病概率：'+ no +'</h4></div>';
    $("#predictResult").remove();
    $("#ajaxHtmlResult").append(htmlText);

    var symptomHtmlText = '<h4 class="text-center col-sm-12" id="disease_content_header">其他符合症状可能疾病</h4>';
    var rows = data.symptomResult;
    for (i = 0; i < rows.length; i++) {
        symptomHtmlText += '<div class="excerpt disease_content">';
        symptomHtmlText += '<h4  class="col-sm-11"><a href="#"  onclick="showDetail('+ i +')">';
        symptomHtmlText += rows[i].disease;
        symptomHtmlText += '</a></h4>';
        symptomHtmlText += '<p class="note col-sm-11" id="note_summary_'+ i +'" style="text-overflow: ellipsis;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;">' + rows[i].symptom + '</p>';
        symptomHtmlText += '<div id="disease_content_detail_'+ i + '"  class="col-sm-11" hidden>\n' +
            '                    <p class="note"><strong>科室：</strong>'+ rows[i].field + '</p>\n' +
            '                    <p class="note"><strong>症状：</strong>'+ rows[i].symptom + '</p>\n' +
            '                    <p class="note"><strong>预防：</strong>'+ rows[i].prevention + '</p>\n' +
            '                    <p class="note"><strong>治疗：</strong>'+ rows[i].life + '</p>' +
            '                    <button type="button" class="btn btn-info btn-sm" onclick="hideDetail('+ i +')">--收起--</button>'
            '                </div>';
        symptomHtmlText += '</div>';
    }
    $(".disease_content").remove();
    $("#disease_content_header").remove();
    $("#disease_content_div").append(symptomHtmlText);
}

function showDetail(obj){
    var detailId = "disease_content_detail_" + obj;
    var noteSummaryId = "note_summary_" + obj;
    $("#" + noteSummaryId).hide();
    $("#" + detailId).show();
}

function hideDetail(obj){
    var detailId = "disease_content_detail_" + obj;
    var noteSummaryId = "note_summary_" + obj;
    $("#" + noteSummaryId).show();
    $("#" + detailId).hide();
}

function searchPersonal(){
    layer.open({
        type : 2,
        title : '选择',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/manager/health/searchPersonal'
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        // rules : {
        //     name : {
        //         required : true
        //     }
        // },
        // messages : {
        //     name : {
        //         required : icon + "请输入名字"
        //     }
        // }
    })
}