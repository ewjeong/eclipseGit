<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- font-awesom -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

<tiles:insertAttribute name="preScript" />

<style>
	* { padding:0; margin:0;  }
	body{ background: #fefefe; color:#343434; }
	
	/* common css */
	label{ text-align: center; line-height: 32px; }
	#wrapper { padding:0; margin:0; } 
	#header { padding:0;} 
 	#leftSideBar { padding:0;  } 
 	#bodyArea { min-height: 765px; padding:0;}
 	.bodyPadding { padding:30px 30px 30px 50px; } 
 	#rightSideBar { }
 	.paddingNone{ padding:0; }
 	
 	.pageWrap { display:block; margin-left:50%; transform:translate(-50%, 0); }
 	.titleBold { font-weight: bold; color: #007bff;  }
 	
 	/* push */
 	#socketAlert { display:none;  margin:0;}
 	#alertTr:hover { background:#f1f1f1  }
 	.alertCnt {  
 		font-weight:bold; 
 		width:10px; height:10px; 
 		background:red; color: #fff; 
 		font-size: 10px;
 		border-radius: 50%; 
 		padding:0px 5px; margin-left:-5px; 
 	}
 		 
	/* search  */
 	.delBtn > .fa-minus-square{ line-height:30px; }
	.searchWrap { 
		margin-left:-1px;
		border: 1px solid #ccc; 
		border-radius: 5px; 
		border-top-left-radius: 0;
   		border-bottom-left-radius: 0;  
   	}
   	.searchWrap:hover{ background:#f1f1f1; }
	.btnSearch { padding: 2px 12px; }
	.fa-search { line-height: 28px;  } 
	.input-group { padding:0; }
 	
 	/* chatting css */
 	#chatting { padding:0; position:absolute; bottom:10px; left:30px; }

	/* leftSideBar css */
	#leftSideBar { background: #ececec; }
	.lsb_wrap { width:100%; margin-top: 0px;}
	.lsb_li>a:hover { background: #b3d7ff; }
	 
	/* elec common css */
	.compleList{ margin-top: 50px;  }
	.approTable { margin-top: 10px; }
	
	.ap_metaData{ margin-top: 20px;  }
	.ap_mdWrap{ margin-top:10px;  }
	.ap_mainWrap{ border:1px solid #ccc;  border-radius: 5px; margin-top:10px;  }
	.ap_main { border-bottom:1px solid #ccc; height: 800px; }
	.ap_btns { margin-top:20px; }
	.none{ text-align:  center;}
	
	/* elec sangshinForm.jsp and approView.jsp css */
	.approWrap{ overflow: hidden; }
	.approHeader{ width:50%; float: left; }
	.elecLine { overflow: hidden; }
	.ap_linesWrap { border: 1px solid #ccc; margin-bottom: -12px; margin-top:10px; padding:10px 10px 0; overflow: hidden;}
	.lineList { list-style: none; overflow: hidden; float:right; margin-right: 30px; }
	.lineList li { 
		float:left; 
		width:70px;
		height: 90px; 
		border:1px solid #ccc; 
		padding: 5px; 
		text-align: center;
		border-right:none;
	}
	.lineList li:last-child { border-right: 1px solid #ccc; }
	.position { border-bottom : 1px solid #ccc;  }
	.cke_chrome { border : 0; }
/* 	.fa-plus { padding-top: 7px; } */
	
	/* elec file css */
	.fileWrap{ border: 1px solid #ccc; border-top:0; padding: 10px; }
	.noneFile { text-align: center; font-size: 11px; }
	
	/* elec form css */
	.form_metadata { margin: 15px 0; }
	
	/* buyer css */
	.btnCenterWrap{ display: block; margin-left:50%; transform:translate(-50%, 0); }
	
</style>
</head>
<body>
	<div id="wrapper" class="row" >
		<div id="header" class="col-xs-12 col-md-12">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="leftSideBar" class="col-xs-1 col-md-2">
			<tiles:insertAttribute name="leftSideBar"/>
		</div>
		<div id="bodyArea" class="col-xs-10 col-md-9">
			<div class="bodyPadding">
				<tiles:insertAttribute name="body"/>
			</div>
		</div>
		
<!-- 		<div id="rightSideBar" class="col-xs-1 col-md-2"> -->
<%-- 			<tiles:insertAttribute name="rightSideBar"/> --%>
<!-- 		</div> -->
		<tiles:insertAttribute name="postScript"/>
	</div>	
	
</body>
</html>