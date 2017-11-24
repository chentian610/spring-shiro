$(function () {
    $("#jqGrid").jqGrid({
        url: '../busvoicelist/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '销售员姓名', name: 'salerName', index: 'saler_name', width: 80 }, 			
			{ label: '规则ID', name: 'ruleId', index: 'rule_id', width: 80,hidden:true },
            { label: '项目名称', name: 'rule_name', index: 'rule_name', width: 80},
			{ label: '音频url', name: 'fileUrl', index: 'file_url', width: 80 }, 			
			{ label: '是否已经识别', name: 'isDone', index: 'is_done', width: 80,
				formatter:function(value,options,rowData){
					if( value===0 ){
						return '';
					}else{
						return '已识别';
					}
            	}},
			{ label: '得分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '识别时间', name: 'recgnizeTime', index: 'recgnize_time', width: 80 },
            { label: '识别内容', name: 'recgnizeText', index: 'recgnize_text', width: 80 ,hidden:true}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        showDetail:false,
		showList: true,
		title: null,
		busVoiceList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.busVoiceList = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.busVoiceList.id == null ? "../busvoicelist/save" : "../busvoicelist/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.busVoiceList),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../busvoicelist/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../busvoicelist/info/"+id, function(r){
                vm.busVoiceList = r.busVoiceList;
            });
		},
        recognize: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                alert("没有选择检测的音频......");
                return ;
            }
            for (var i=0;i<ids.length;i++) {
                if ($("#jqGrid").getCell(ids[i],"isDone") == "已识别") {
                    alert("音频已经检测，不能重复检测......");
                    return;
                }
			}
            $("body").mLoading({
                text:"语音质检中，请稍后...",//加载文字，默认值：加载中...
                icon:"../public/images/loading.gif",//加载图标，默认值：一个小型的base64的gif图片
                html:false,//设置加载内容是否是html格式，默认值是false
                content:"",//忽略icon和text的值，直接在加载框中显示此值
                mask:true//是否显示遮罩效果，默认显示
            }).show();
            $.ajax({
                type: "POST",
                url: "../busvoicelist/recognize",
                data: JSON.stringify(ids),
                success: function(r){
                    // $("#btnRecognize").attr("enabled","enabled");
                    // $("#loading").hide();
                    $("body").mLoading("hide");
                    if(r.code == 0){
                        alert('质检已完成', function(index){
                            $("#jqGrid").trigger("reloadGrid");
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        openDetail: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            if ($("#jqGrid").getCell(id,"isDone") ==0) {
            	alert("该音频还未检测，没有明细得分......");
            	return;
            }
            vm.showDetail = true;
            vmDetail.showDetail = true;
            $("#jqGridDetail").jqGrid(
                'setGridParam', {
                    datatype: 'json',
                    postData:{voiceId:id,ruleId:$("#jqGrid").getCell(id,"ruleId")},
                }).trigger('reloadGrid');
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	}
});