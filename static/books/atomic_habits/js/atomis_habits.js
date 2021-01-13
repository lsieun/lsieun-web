// type: noun,n./verb,v./adjective,adj./quantifier/adverb,adv./preposition(介词)/conjunction,conj.(连词)
let words = [
    {
        "id": "clutter",
        "type": "n.",
        "ch": "杂乱的东西（尤指不需要的或无用的）；杂乱",
        "en": "a lot of things in an untidy state, especially things that are not necessary or are not being used; a state of confusion"
    },
    {
        "id": "geological",
        "type": "adj.",
        "ch": "地质的",
        "en": "relating to geology, or to the features of the Earth’s surface"
    },
    {
        "id": "hallmark",
        "type": "n.",
        "ch": "特征；特点",
        "en": "a feature or quality that is typical of sb/sth"
    },
    {
        "id": "hassle",
        "type": "n.",
        "ch": "困难；麻烦",
        "en": "a situation that is annoying because it involves doing sth difficult or complicated that needs a lot of effort"
    },
    {
        "id": "tectonic",
        "type": "adj.",
        "ch": "地壳构造的",
        "en": "connected with the structure of the earth's surface"
    },
    {
        "id": "",
        "type": "",
        "ch": "",
        "en": ""
    },
];

function getVocabulary(vocabulary) {
    let length = words.length;
    for (let i = 0; i < length; i++) {
        let word = words[i];
        if (vocabulary === word.id) {
            return word;
        }
    }
    return null;
}

function getVocabulary2(vocabulary) {
    let str = vocabulary.toLowerCase();
    let word_length = words.length;
    for (let i = 0; i < word_length; i++) {
        let word = words[i];
        let id = word.id.toLowerCase();
        if (str === id) {
            return word;
        }
        let flag = 'related' in word;
        if (flag) {
            let related = word.related;
            let array = related.split(",");
            let length = array.length;
            for (let j = 0; j < length; j++) {
                let item = array[j].toLowerCase();
                if (item === str) {
                    return word;
                }
            }
        }
    }
    return null;
}

$(document).ready(function () {
    $("span.tooltip").each(function(index, item){
        let vocabulary = $(item).attr("lookup");
        if (vocabulary !== undefined) {
            let word = getVocabulary(vocabulary);
            if (word !== null) {
                let text = $(item).text();
                let html = '';
                html += text;
                html += '<span class="tooltiptext">';
                html += word.id + '<br/>';
                html += word.type + ' ' + word.ch + '<br/>';
                html += word.en;
                html += '</span>';
                $(item).html(html);
            }
        }
    });

    $('#main p').each(function (index, item) {
        let text = $(item).text();
        let array = text.split(/\W+/);
        let length = array.length;
        let myMap = new Map();
        for (let i = 0; i < length; i++) {
            let vocabulary = array[i];
            if (vocabulary === "") continue;
            let word = getVocabulary2(vocabulary);
            if (word !== null) {
                myMap.set(vocabulary, word);
            }
        }

        let myArray = [];
        for (let [key, value] of myMap) {
            myArray.push(key);
        }
        myArray.sort();

        let html = $(item).html();
        // for (let [key, value] of myMap) {
        for (let key of myArray) {
            let value = myMap.get(key);
            let tooltip = '';
            tooltip += '<span class="tooltip">' + key;
            tooltip += '<span class="tooltiptext">';
            tooltip += value.id + '<br/>';
            tooltip += value.type + ' ' + value.ch + '<br/>';
            tooltip += value.en;
            tooltip += '</span>';
            tooltip += '</span>';
            let regexp = new RegExp('\\b' + key + '\\b', 'g');
            html = html.replace(regexp, tooltip);
        }
        $(item).html(html);
    });
});
