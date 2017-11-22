$(function () {
    $("#jqGridDetail").jqGrid({
        url: '../busrulelist/list',
        datatype: "local",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true ,hidden : true},
			{ label: '规则ID', name: 'ruleId', index: 'rule_id', width: 80 }, 			
			{ label: '规则文本', name: 'ruleText', index: 'rule_text', width: 300 },
			{ label: '例子', name: 'demoText', index: 'demo_text', width: 300 },
			{ label: '权重', name: 'weight', index: 'weight', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPagerDetail",
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
        	$("#jqGridDetail").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vmDetail = new Vue({
	el:'#rrappDetail',
	data:{
        showDetail:false,
		showList: true,
		title: null,
		busRuleList: {}
	},
	methods: {
		query: function () {
			vmDetail.reload();
		},
		add: function(){
			vmDetail.showList = false;
			vmDetail.title = "新增";
			vmDetail.busRuleList = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vmDetail.showList = false;
            vmDetail.title = "修改";
            
            vmDetail.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vmDetail.busRuleList.id == null ? "../busrulelist/save" : "../busrulelist/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vmDetail.busRuleList),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vmDetail.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRowsByGridName("jqGridDetail");
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../busrulelist/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGridDetail").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../busrulelist/info/"+id, function(r){
                vmDetail.busRuleList = r.busRuleList;
            });
		},
		reload: function (event) {
			vmDetail.showList = true;
			var page = $("#jqGridDetail").jqGrid('getGridParam','page');
			$("#jqGridDetail").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		},
        backMain: function (event) {
            vm.showDetail = false;
            vmDetail.showDetail = false;
        }
	}
});