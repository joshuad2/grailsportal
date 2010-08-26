function rollover()
{
	if(!document.getElementById || !document.createTextNode){return;}
	var n=document.getElementById('nav');
	if(!n){return;}
	var lis=n.getElementsByTagName('li');
	for (var i=0;i<lis.length;i++)
	{
		lis[i].onmouseover=function()
		{
			this.className=this.className?'cur':'over';
		}
		lis[i].onmouseout=function()
		{
			this.className=this.className=='cur'?'cur':'';
		}
	}
}
window.onload=rollover;

