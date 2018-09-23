/**
 	파일 명: hbs.js
	설명: type이 text/x-handlebars-template인 script 들을 찾아서 모두 컴파일해서 Template으로 만든다.
	그리고 원하는 Template에 Json을 데이터를 넣어서 html로 바꾸어준다.
*/

	const Templates = {};
	let $scripts = $('script[type="text/x-handlebars-template"]');

	$scripts.each((idx, script) =>{
		$script = $(script);
		let scriptId = $script.attr('id');
		Templates[scriptId] = Handlebars.compile($script.html()); 
	})

	let renderHbs = ((templateId, jsonData, tag) =>{
		console.info(Templates);
		tag = tag || 'div';
		let $template = $('#'+templateId);
		let html = Templates[templateId](jsonData);
		let templateClass = $template.attr('class') || '';
		$template.replaceWith(`<${tag} id="${templateId}" class="${templateClass}">` + html + `</${tag}>`);
	});

	Handlebars.registerHelper('eq',function(a,b){
		return a==b;
	})

	moment.locale('ko');
	Handlebars.registerHelper('fromNow', function(dt){
		return moment(dt).fromNow();
	})

	Handlebars.registerHelper('fullTime', function(dt){
		return moment(dt).format('llll');
	})

	Handlebars.registerHelper('transHtml', function(str){
		if(!str)
			return str;
		return str.replace(/[\r\n]/g,'<br>');
	})