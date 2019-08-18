$(document).ready(function(){
	getip();
	setInterval(function run(){
		$.get('/FXBlotter/getTradeData', function(responseText) {
			getTradeData(responseText);
		});
	},1000);
});
trade_id=1;
ip="0.0.0.0";
function getip()
{
	$.getJSON('http://gd.geobytes.com/GetCityDetails?callback=?', function(data) {
		  ip=data.geobytesipaddress;
		});
}
map=new Map();
map1=new Map();
function bookedTrade(currency,ask){
	appendRow('Buy',ask,currency);
	if(map.has(currency)){
		map.set(currency,map.get(currency)+1);
		map1.set(currency,map1.get(currency)+((ask*1000)-1000));
	}
	else
	{
		map.set(currency,1);
		map1.set(currency,((ask*1000)-1000));
	}
	updateposition();
}
function sellTrade(currency,bid){
	if(map.has(currency)){
		appendRow('Sell',bid,currency);
		map1.set(currency,map1.get(currency)-((bid*1000)-1000));
		var quantity=map.get(currency)-1;
		if(quantity <= 0){
			map.delete(currency);
		}
		else{
			map.set(currency,quantity);
		}
		updateposition();
	}
	else
	{
		alert("You must buy "+currency+" Trade to sell it");
	}
}

function appendRow(type,value,currency){
	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	var dateTime = date+' '+time;
	$("#tradetable table").append("<tr align='center'><td>"+dateTime+"</td><td>"+ip+"</td><td>"+trade_id+"</td><td>"+currency+"</td><td>"+type+"</td><td>"+value+"</td><td>1000</td></tr>");
	trade_id++;
}
function updateposition()
{
	var iterator=map.entries();
	var iterator1=map1.entries();
	var i=0;
	var tableString = "<table class='table table-bordered'>";
	tableString += "  <thead><tr><th colspan='3'><h1>Trader Position</h1></th></tr><tr><th>Currency</th><th>Quantity</th><th>Profit/Loss</th></tr></thead>";
	
	
	while(i<map.size)
	{
		var temp=iterator.next().value;
		tableString += "<tr>";
		tableString += "<td>"+temp[0]+ "</td>";
		tableString += "<td>"+temp[1]+ "</td>";
		tableString += "<td>"+iterator1.next().value[1] + "</td>";
		tableString += "</tr>";
		i++;
	}
	tableString+="</table>";
	$("#positiontable").html(tableString)
}
function getTradeData(responseText){
	//console.log(responseText);
	var tableString = "<table class='table table-bordered'>";
	tableString += "<tr>";
	tableString += " <thead><tr><th colspan='5'><h1>Market Data Rate</h1></th></tr><th>Currency</th><th>Bid</th><th>Ask</th><th>Buy</th><th>Sell</th>"
	tableString += "</tr>";
	for(var i=0;i<Object.keys(responseText).length;i++){
		var data=Object.values(responseText)[i];
		var currency=Object.keys(responseText)[i];
		tableString += "<tr>";
		tableString += "<td>"+currency+ "</td>";
		tableString += "<td>"+data.bid + "</td>";
		tableString += "<td>"+data.ask + "</td>";
		tableString += "<td><button onClick='bookedTrade(\""+currency+"\","+data.ask+")'>Book</button></td>";
		tableString += "<td><button onClick='sellTrade(\""+currency+"\","+data.bid+")'>Sell</button></td>";

		tableString += "</tr>";
	}
	tableString+="</table>";
	$("#table").html(tableString);
}