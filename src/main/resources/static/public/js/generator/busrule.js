$(function () {
    $("#jqGrid").jqGrid({
        url: '../busrule/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 10, key: true },
			{ label: '保险名称', name: 'insuranceType', index: 'insurance_type', width: 30 },
			{ label: '规则', name: 'ruleText', index: 'rule_text', width: 80 },
            { label: '例子', name: 'demoText', index: 'demo_text', width: 80 }
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
		busRule: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.busRule = {};
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
			var url = vm.busRule.id == null ? "../busrule/save" : "../busrule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.busRule),
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
				    url: "../busrule/delete",
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
			$.get("../busrule/info/"+id, function(r){
                vm.busRule = r.busRule;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        openDetail: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showDetail = true;
            vmDetail.showDetail = true;
            $("#jqGridDetail").jqGrid(
                'setGridParam', {
                    datatype: 'json',
                    postData:{ruleId:id},
                }).trigger('reloadGrid');
        }
	}
});
