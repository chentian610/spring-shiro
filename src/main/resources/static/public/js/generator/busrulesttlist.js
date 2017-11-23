$(function () {
    $("#jqGridDetail").jqGrid({
        url: '../busrulesttlist/list',
        datatype: "local",
        colModel: [			
			{ label: '内容', name: 'demoText', index: 'demo_text', width: 600 },
			{ label: '权重', name: 'weight', index: 'weight', width: 80 },
			{ label: '得分', name: 'score', index: 'score', width: 80 }, 			
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
        showDetail: false,
		showList: true,
		title: null,
		busRuleSttList: {}
	},
	methods: {
		query: function () {
            vmDetail.reload();
		},
		add: function(){
            vmDetail.showList = false;
            vmDetail.title = "新增";
            vmDetail.busRuleSttList = {};
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
			var url = vmDetail.busRuleSttList.id == null ? "../busrulesttlist/save" : "../busrulesttlist/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vmDetail.busRuleSttList),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../busrulesttlist/delete",
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
			$.get("../busrulesttlist/info/"+id, function(r){
                vmDetail.busRuleSttList = r.busRuleSttList;
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