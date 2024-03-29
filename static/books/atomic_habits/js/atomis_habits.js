// type: noun,n./verb,v./adjective,adj./quantifier/adverb,adv./preposition(介词)/conjunction,conj.(连词)
let words = [
    {
        "id": "artery",
        "type": "n.",
        "ch": "动脉",
        "en": "any of the tubes that carry blood from the heart to other parts of the body"
    },
    {
        "id": "biceps",
        "type": "n.",
        "ch": "二头肌（上臂前侧的主要肌肉）",
        "en": "the main muscle at the front of the top part of the arm"
    },
    {
        "id": "clutter",
        "type": "n.",
        "ch": "杂乱的东西（尤指不需要的或无用的）；杂乱",
        "en": "a lot of things in an untidy state, especially things that are not necessary or are not being used; a state of confusion"
    },
    {
        "id": "curator",
        "type": "n.",
        "ch": "（博物馆或美术馆等的）馆长，负责人",
        "en": "a person whose job is to be in charge of the objects or works of art in a museum or art gallery , etc.",
        "related": "curators"
    },
    {
        "id": "dart",
        "type": "v.",
        "ch": "猛冲；突进；飞奔",
        "en": "to move suddenly and quickly in a particular direction"
    },
    {
        "id": "dichotomy",
        "type": "n.",
        "ch": "一分为二；二分法",
        "en": "the separation that exists between two groups or things that are completely opposite to and different from each other"
    },
    {
        "id": "exhaustive",
        "type": "adj.",
        "ch": "详尽的；彻底的；全面的",
        "en": "including everything possible; very thorough or complete"
    },
    {
        "id": "geological",
        "type": "adj.",
        "ch": "地质的",
        "en": "relating to geology, or to the features of the Earth's surface"
    },
    {
        "id": "groove",
        "type": "n.",
        "ch": "沟；槽；辙；纹",
        "en": "a long narrow cut in the surface of sth hard",
        "related": "grooves"
    },
    {
        "id": "hairdresser",
        "type": "n.",
        "ch": "理发师；美发师；发型师",
        "en": "a person whose job is to cut, wash and shape hair",
        "related": "hairdressers"
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
        "id": "manicure",
        "type": "n.",
        "ch": "修剪指甲；指甲护理",
        "en": "the care and treatment of a person's hands and nails"
    },
    {
        "id": "neurological",
        "type": "adj.",
        "ch": "神经系统的；神经（病）学的",
        "en": "relating to nerves or to the science of neurology"
    },
    {
        "id": "paradox",
        "type": "n.",
        "ch": "矛盾的人（或事物、情况）",
        "en": "a statement, proposition, or situation that seems to be absurd or contradictory, but in fact is or may be true"
    },
    {
        "id": "paramedic",
        "type": "n.",
        "ch": "急救医士；护理人员；医务辅助人员",
        "en": "a person whose job is to help people who are sick or injured, but who is not a doctor or a nurse"
    },
    {
        "id": "pigeonhole",
        "type": "vt.",
        "ch": "将（某人）轻率分类",
        "en": "to decide that sb belongs to a particular group or type without thinking deeply enough about it and considering what other qualities they might have"
    },
    {
        "id": "rudder",
        "type": "n.",
        "ch": "（船的）舵",
        "en": "a piece of wood or metal at the back of a boat or an aircraft that is used for controlling its direction"
    },
    {
        "id": "slumber",
        "type": "n.",
        "ch": "睡眠",
        "en": "sleep; a time when sb is asleep"
    },
    {
        "id": "tectonic",
        "type": "adj.",
        "ch": "地壳构造的",
        "en": "connected with the structure of the earth's surface"
    },
    {
        "id": "unanimous",
        "type": "adj.",
        "ch": "（决定或意见）一致的",
        "en": "if a decision or an opinion is unanimous, it is agreed or shared by everyone in a group"
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
