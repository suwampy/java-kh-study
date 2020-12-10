(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("search");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("ds_result", this);
            obj._setContents("");
            this.addChild(obj.name, obj);


            obj = new Dataset("ds_emp", this);
            obj._setContents("<ColumnInfo><Column id=\"EMPL_ID\" type=\"STRING\" size=\"256\"/><Column id=\"FULL_NAME\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row/></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Div("Div00","62","33","695","321",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_background("lightpink");
            this.addChild(obj.name, obj);

            obj = new Static("Static00","56","48","164","56",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("0");
            obj.set_text("EMPL_ID");
            obj.set_font("normal 20pt/normal \"Arial\"");
            this.Div00.addChild(obj.name, obj);

            obj = new Static("Static01","54","147","164","56",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("1");
            obj.set_text("FULL_NAME");
            obj.set_font("normal 20pt/normal \"Arial\"");
            this.Div00.addChild(obj.name, obj);

            obj = new MaskEdit("MaskEdit00","298","59","293","63",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("2");
            obj.set_type("string");
            obj.set_format("AA-###");
            this.Div00.addChild(obj.name, obj);

            obj = new Edit("Edit00","298","153","294","56",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("3");
            this.Div00.addChild(obj.name, obj);

            obj = new Button("Button00","229","246","181","53",null,null,null,null,null,null,this.Div00.form);
            obj.set_taborder("4");
            obj.set_text("search");
            this.Div00.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1280,720,this,function(p){});
            obj.set_mobileorientation("landscape");
            this.addLayout(obj.name, obj);
            
            // BindItem Information
            obj = new BindItem("item0","Div00.form.MaskEdit00","value","ds_emp","EMPL_ID");
            this.addChild(obj.name, obj);
            obj.bind();

            obj = new BindItem("item1","Div00.form.Edit00","value","ds_emp","FULL_NAME");
            this.addChild(obj.name, obj);
            obj.bind();
        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("search.xfdl", function() {

        this.Div00_Button00_onclick = function(obj,e)
        {
        	// 1. variable을 이용해서 값을 전달하는 방법
        // 	var emplId = this.Div00.form.MaskEdit00.value;
        // 	var fullName = this.Div00.form.Edit00.value;
        //
        // 	alert(emplId + "," + fullName);
        //
        // 	this.transaction(
        // 		"search",
        // 		"http://localhost:8800/testCRUD/searchEmployee",
        // 		"",
        // 		"ds_result=out_ds",
        // 		"emplId="+emplId+" fullName="+fullName,
        // 		"tr_callbackFunc"
        // 	);

        // 2. Dataset으로 바인딩하여 전송
        	this.transaction(
        		"search",
        		"http://localhost:8800/testCRUD/searchEmployee",
        		"in_ds=ds_emp",
        		"ds_result=out_ds",
        		"",
        		"tr_callbackFunc"
        	);

        };

        this.tr_callbackFunc = function(id,errCd,errMsg){

        	alert("id : " + id + ", errCd : " + errCd + ", errMsg : " + errMsg);
        	alert("code : " + this.ds_result.getColumn(0, 'code')
        		+ " msg : " + this.ds_result.getColumn(0, 'msg'));

        }




        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Div00.form.MaskEdit00.addEventHandler("onchanged",this.Div00_MaskEdit00_onchanged,this);
            this.Div00.form.Button00.addEventHandler("onclick",this.Div00_Button00_onclick,this);
        };

        this.loadIncludeScript("search.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
