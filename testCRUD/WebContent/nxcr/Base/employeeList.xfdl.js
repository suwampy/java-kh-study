(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("employeeList");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("ds_emp", this);
            obj._setContents("<ColumnInfo><Column id=\"EMPL_ID\" type=\"STRING\" size=\"10\"/><Column id=\"FULL_NAME\" type=\"STRING\" size=\"50\"/><Column id=\"DEPT_CD\" type=\"STRING\" size=\"10\"/><Column id=\"POS_CD\" type=\"STRING\" size=\"10\"/><Column id=\"HIRE_DATE\" type=\"DATE\" size=\"10\"/><Column id=\"SALARY\" type=\"INT\" size=\"10\"/><Column id=\"GENDER\" type=\"STRING\" size=\"10\"/><Column id=\"MARRIED\" type=\"STRING\" size=\"10\"/><Column id=\"MEMO\" type=\"STRING\" size=\"10\"/></ColumnInfo><Rows><Row><Col id=\"EMPL_ID\">AA001</Col><Col id=\"FULL_NAME\">Olivia</Col><Col id=\"DEPT_CD\">01</Col><Col id=\"POS_CD\">03</Col><Col id=\"HIRE_DATE\">20101003</Col><Col id=\"SALARY\">83000</Col><Col id=\"GENDER\">W</Col><Col id=\"MARRIED\">true</Col><Col id=\"MEMO\">ivory</Col></Row><Row><Col id=\"EMPL_ID\">AA002</Col><Col id=\"FULL_NAME\">John</Col><Col id=\"DEPT_CD\">02</Col><Col id=\"POS_CD\">04</Col><Col id=\"HIRE_DATE\">20051011</Col><Col id=\"SALARY\">76000</Col><Col id=\"GENDER\">M</Col><Col id=\"MARRIED\">false</Col><Col id=\"MEMO\">greenyellow</Col></Row><Row><Col id=\"EMPL_ID\">BB001</Col><Col id=\"FULL_NAME\">Jackson</Col><Col id=\"DEPT_CD\">03</Col><Col id=\"POS_CD\">03</Col><Col id=\"HIRE_DATE\">20070206</Col><Col id=\"SALARY\">95000</Col><Col id=\"GENDER\">M</Col><Col id=\"MARRIED\">true</Col><Col id=\"MEMO\">aliceblue</Col></Row><Row><Col id=\"EMPL_ID\">BB002</Col><Col id=\"FULL_NAME\">Maia</Col><Col id=\"DEPT_CD\">04</Col><Col id=\"POS_CD\">02</Col><Col id=\"HIRE_DATE\">20090512</Col><Col id=\"SALARY\">60000</Col><Col id=\"GENDER\">W</Col><Col id=\"MARRIED\">false</Col><Col id=\"MEMO\">ivory</Col></Row><Row><Col id=\"EMPL_ID\">CC001</Col><Col id=\"FULL_NAME\">Adam</Col><Col id=\"DEPT_CD\">01</Col><Col id=\"POS_CD\">04</Col><Col id=\"HIRE_DATE\">20010109</Col><Col id=\"SALARY\">88000</Col><Col id=\"GENDER\">M</Col><Col id=\"MARRIED\">true</Col><Col id=\"MEMO\">greenyellow</Col></Row><Row><Col id=\"EMPL_ID\">DD001</Col><Col id=\"FULL_NAME\">Ray</Col><Col id=\"DEPT_CD\">02</Col><Col id=\"POS_CD\">03</Col><Col id=\"HIRE_DATE\">20160202</Col><Col id=\"SALARY\">60000</Col><Col id=\"GENDER\">M</Col><Col id=\"MARRIED\">true</Col><Col id=\"MEMO\">lightpink</Col></Row></Rows>");
            this.addChild(obj.name, obj);


            obj = new Dataset("ds_result", this);
            obj._setContents("");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Div("Div00","29","32","1191","328",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_background("skyblue");
            this.addChild(obj.name, obj);

            obj = new Grid("Grid00","28","29","1134","189",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("0");
            obj.set_binddataset("ds_emp");
            obj.set_autofittype("col");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"48\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"NO\"/><Cell col=\"1\" text=\"EMPL_ID\"/><Cell col=\"2\" text=\"FULL_NAME\"/><Cell col=\"3\" text=\"DEPT_CD\"/><Cell col=\"4\" text=\"POS_CD\"/><Cell col=\"5\" text=\"HIRE_DATE\"/><Cell col=\"6\" text=\"SALARY\"/><Cell col=\"7\" text=\"GENDER\"/><Cell col=\"8\" text=\"MARRIED\"/><Cell col=\"9\" text=\"MEMO\"/></Band><Band id=\"body\"><Cell text=\"expr:currow + 1\"/><Cell col=\"1\" text=\"bind:EMPL_ID\"/><Cell col=\"2\" text=\"bind:FULL_NAME\"/><Cell col=\"3\" text=\"bind:DEPT_CD\"/><Cell col=\"4\" text=\"bind:POS_CD\"/><Cell col=\"5\" text=\"bind:HIRE_DATE\"/><Cell col=\"6\" text=\"bind:SALARY\"/><Cell col=\"7\" text=\"bind:GENDER\"/><Cell col=\"8\" text=\"bind:MARRIED\"/><Cell col=\"9\" text=\"bind:MEMO\"/></Band></Format></Formats>");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button00","36","239","195","42",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("1");
            obj.set_text("추가하기");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button01","252","238","209","45",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("2");
            obj.set_text("삭제하기");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button02","478","237","213","46",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("3");
            obj.set_text("저장하기");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button03","721","239","248","44",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("4");
            obj.set_text("저장 된 데이터 불러오기");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button04","999","238","172","49",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("5");
            obj.set_text("페이지 이동");
            this.Div00.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1280,720,this,function(p){});
            obj.set_mobileorientation("landscape");
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("employeeList.xfdl", function() {
        // dataset에 row 추가
        this.Div00_Button00_onclick = function(obj,e)
        {
        	var nRow = this.ds_emp.addRow(); // 몇번째 행인지 리턴 된다

        	// 값 지정
        	this.ds_emp.setColumn(nRow, "EMPL_ID", "EE-001");
        	this.ds_emp.setColumn(nRow, "FULL_NAME", "BYEOLLIM");
        	this.ds_emp.setColumn(nRow, "DEPT_CD", "01");
        	this.ds_emp.setColumn(nRow, "POS_CD", "01");

        	// 오늘 날짜 구하기
        	var day = new Date();
        	var yyyy = day.getFullYear();
        	var mm = day.getMonth() + 1;
        	var dd = day.getDate();
        	mm = (mm < 9 ? '0' : '') + mm;
        	dd = (dd < 9 ? '0' : '') + dd;

        	this.ds_emp.setColumn(nRow, "HIRE_DATE", yyyy+mm+dd);

        	this.ds_emp.setColumn(nRow, "SALARY", 100000);
        	this.ds_emp.setColumn(nRow, "GENDER", "W");
        	this.ds_emp.setColumn(nRow, "MARRIED", "true");
        	this.ds_emp.setColumn(nRow, "MEMO", "red");
        };

        // 선택한 행 삭제
        this.Div00_Button01_onclick = function(obj,e)
        {
        	// deleteRow()의 인자로 rowposition 전달
        	this.ds_emp.deleteRow(this.ds_emp.rowposition);
        };

        // 서버단으로 전송하기 위해서는 transaction을 이용한다
        /*
        	this.transaction(
        		"strSvcID", // 트랜잭션 ID
        		"strURL", // 요청 주소
        		"strInDatasets", // 서버에 전달할 데이터 셋
        		EX) "in_ds=ds_emp:A in_ds2=ds_dept:U"
        		A: all, U : update, N : 삭제되지 않은 정보
        		"strOutDatasets", // 서버로부터 받을 데이터 셋
        		EX) "ds_emp=out_emp" 서버로부터 받을 datasetID = 서버에서 전송한 datasetID
        		"strArgument", // 트랜잭션 간 전달할 변수
        		"strCallbackFunc" // 서버 측 통신이 끝난 후 호출하는 함수
        	);
        */

        // 저장하기
        this.Div00_Button02_onclick = function(obj,e)
        {
        	this.transaction(
        		"save", // 트랜잭션 ID
        		"http://localhost:8800/testCRUD/saveEmployee", // 요청 주소
        		"in_ds=ds_emp:A", // ds_emp의 모든 값 서버로 전송
        		"ds_result=out_ds",
        		// 서버로부터 전달 받을 값이 없으면 공란으로 둔다
        		// form에서 가지고 있는 dataset = 서버에서 전달 된 dataset
        		"testVal=hello testVal2=nexacro",
        		// 전달할 변수 값이 없다면 공란으로 둔다
        		// 전달할 값이 있다면 키=값 키=값 ...
        		"tr_callbackFunc"
        		// 일단 콜백함수도 공란으로 둔다
        		// 서버에서 수행을 마치고 실행되는 함수
        	);
        };

        // 트랜잭션 이후 콜백함수
        this.tr_callbackFunc = function(id,errCd,errMsg){
        	alert("id : " + id + ", errCd : " + errCd + ", errMsg : " + errMsg);

        	alert("code : " + this.ds_result.getColumn(0, 'code')
        	+ "msg : " + this.ds_result.getColumn(0, 'msg'));
        }

        // 저장 된 데이터 불러오기
        this.Div00_Button03_onclick = function(obj,e)
        {
        	this.transaction(
        		"select", // 해당 트랜잭션의 아이디
        		"http://localhost:8800/testCRUD/selectEmployee", // 요청 주소
        		"", // 서버 전송 ds 없으므로 공란
        		"ds_emp=out_ds ds_result=out_ds2", // 서버에서 전달한 emp 목록으로 변경하기
        		"", // 전달할 변수 없으므로 공란
        		"tr_callbackFunc"
        	);
        };

        // 실습
        // 1. search라는 이름의 form 만들기
        // 2. 현재 페이지에서 "페이지 이동" 버튼을 클릭하면 해당 form으로 이동하기
        // 3. search 페이지에서 empl_id, full_name 입력 받고 검색(search)버튼 누르기 (서버 전송)
        // 4. 서버로 전송 된 데이터가 list의 데이터와 동일하면
        // "존재하는 사원입니다" 동일한 값이 없으면 "존재하지 않는 사원입니다"
        // 라는 메세지를 alert 출력

        this.Div00_Button04_onclick = function(obj,e)
        {
        	var frame = nexacro.getApplication().mainframe.ChildFrame00;
        	frame.set_formurl("Base::search.xfdl");
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Div00.form.Button00.addEventHandler("onclick",this.Div00_Button00_onclick,this);
            this.Div00.form.Button01.addEventHandler("onclick",this.Div00_Button01_onclick,this);
            this.Div00.form.Button02.addEventHandler("onclick",this.Div00_Button02_onclick,this);
            this.Div00.form.Button03.addEventHandler("onclick",this.Div00_Button03_onclick,this);
            this.Div00.form.Button04.addEventHandler("onclick",this.Div00_Button04_onclick,this);
        };

        this.loadIncludeScript("employeeList.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
