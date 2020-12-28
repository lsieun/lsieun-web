// type: noun,n./verb,v./adjective,adj./quantifier/adverb,adv./preposition(介词)/conjunction,conj.(连词)
let words = [
    {
        "id": "Andalusia",
        "type": "n.",
        "ch": "安达卢西亚〔西班牙南部一区域〕",
        "en": "autonomous region of southern Spain bordered by the Mediterranean Sea and the Atlantic Ocean.",
        "related": "Andalusian"
    },
    {
        "id": "abashed",
        "type": "adj.",
        "ch": "羞愧；窘迫；尴尬",
        "en": "embarrassed and ashamed because of sth that you have done"
    },
    {
        "id": "alchemy",
        "type": "n.",
        "ch": "炼金术（中世纪，企图把普通金属炼成黄金）",
        "en": "a form of chemistry studied in the Middle Ages which involved trying to discover how to change ordinary metals into gold"
    },
    {
        "id": "aroma",
        "type": "n.",
        "ch": "芳香；香味",
        "en": "a pleasant, noticeable smell"
    },
    {
        "id": "avert",
        "type": "v.",
        "ch": "转移目光",
        "en": "to turn your eyes, etc. away from sth that you do not want to see",
        "related": "averting"
    },
    {
        "id": "balk",
        "type": "v.",
        "ch": "畏缩不前",
        "en": "to stop suddenly and refuse to go on, especially when faced with an obstacle",
        "related": "balked"
    },
    {
        "id": "barbarian",
        "type": "n.",
        "ch": "没有文化的人；粗野的人",
        "en": "a person who behaves very badly and has no respect for art, education, etc."
    },
    {
        "id": "bazaar",
        "type": "n.",
        "ch": "（某些东方国家的）集市",
        "en": "(in some Eastern countries) a street or an area of a town where there are many small shops",
        "related": "bazaars"
    },
    {
        "id": "beak",
        "type": "n.",
        "ch": "鸟喙",
        "en": "the hard pointed or curved outer part of a bird's mouth"
    },
    {
        "id": "bleach",
        "type": "v.",
        "ch": "（使）变白，漂白，晒白",
        "en": "to become white or pale by a chemical process or by the effect of light from the sun",
        "related": "bleached"
    },
    {
        "id": "boulder",
        "type": "n.",
        "ch": "（受水或天气侵蚀而成的）巨石；漂砾",
        "en": "a very large rock which has been shaped by water or the weather"
    },
    {
        "id": "brandish",
        "type": "v.",
        "ch": "挑衅地挥舞，激动地挥舞（尤指武器）",
        "en": "to hold or wave sth, especially a weapon, in an aggressive or excited way",
        "related": "brandished"
    },
    {
        "id": "breastplate",
        "type": "n.",
        "ch": "（古时士兵护胸的）胸铠",
        "en": "a piece of armour worn by soldiers in the past to protect the upper front part of the body"
    },
    {
        "id": "bugle",
        "type": "n.",
        "ch": "军号",
        "en": "a musical instrument like a small trumpet , used in the army for giving signals"
    },
    {
        "id": "capricious",
        "type": "adj.",
        "ch": "变化无常的；变幻莫测的；多变的",
        "en": "changing suddenly and quickly"
    },
    {
        "id": "caravan",
        "type": "n.",
        "ch": "（尤指穿越沙漠的）旅行队，车队",
        "en": "a group of people with vehicles or animals who are travelling together, especially across the desert"
    },
    {
        "id": "carpet",
        "type": "n.",
        "ch": "地毯",
        "en": "a thick woven material made of wool, etc. for covering floors or stairs",
        "related": "carpets"
    },
    {
        "id": "chariot",
        "type": "n.",
        "ch": "（古代用于战斗或比赛的）双轮敞篷马车",
        "en": "an open vehicle with two wheels, pulled by horses, used in ancient times in battle and for racing"
    },
    {
        "id": "chieftain",
        "type": "n.",
        "ch": "首领；酋长；（苏格兰的）族长",
        "en": "the leader of a people or a clan in Scotland",
        "related": "chieftains"
    },
    {
        "id": "clover",
        "type": "n.",
        "ch": "三叶草；车轴草",
        "en": "a small wild plant that usually has three leaves on each stem and purple, pink or white flowers that are shaped like balls",
        "related": "clovers"
    },
    {
        "id": "cobbler",
        "type": "n.",
        "ch": "修鞋匠",
        "en": "a person who repairs shoes"
    },
    {
        "id": "colony",
        "type": "n.",
        "ch": "（同地生长的植物或动物）群体，集落，群",
        "en": "a group of plants or animals that live together or grow in the same place",
        "related": ""
    },
    {
        "id": "commission",
        "type": "n.",
        "ch": "佣金；回扣",
        "en": "an amount of money that is paid to sb for selling goods and which increases with the amount of goods that are sold"
    },
    {
        "id": "company",
        "type": "n.",
        "ch": "陪伴；做伴",
        "en": "the fact of being with sb else and not alone"
    },
    {
        "id": "confiscate",
        "type": "v.",
        "ch": "（尤指作为惩罚）没收",
        "en": "to officially take sth away from sb, especially as a punishment",
        "related": "confiscated"
    },
    {
        "id": "conquistador",
        "type": "n.",
        "ch": "（16 世纪侵占墨西哥和秘鲁的）西班牙征服者",
        "en": "one of the Spanish people who took control of Mexico and Peru by force in the 16th century"
    },
    {
        "id": "corral",
        "type": "n.",
        "ch": "（北美农牧场的）畜栏，围栏",
        "en": "(in N America) a fenced area for horses, cows, etc. on a farm or ranch"
    },
    {
        "id": "countryside",
        "type": "n.",
        "ch": "乡村；农村",
        "en": "land outside towns and cities, with fields, woods, etc."
    },
    {
        "id": "cricket",
        "type": "n.",
        "ch": "蟋蟀；蛐蛐",
        "en": "a small brown jumping insect that makes a loud high sound by rubbing its wings together",
        "related": "crickets"
    },
    {
        "id": "crook",
        "type": "n.",
        "ch": "（尤指旧时牧羊人捕羊用的）曲柄杖",
        "en": "a long stick with a hook at one end, used especially in the past by shepherds for catching sheep"
    },
    {
        "id": "dagger",
        "type": "n.",
        "ch": "匕首；短剑",
        "en": "a short pointed knife that is used as a weapon",
        "related": "daggers"
    },
    {
        "id": "dawn",
        "type": "n.",
        "ch": "黎明；拂晓；破晓",
        "en": "the time of day when light first appears"
    },
    {
        "id": "detour",
        "type": "n.",
        "ch": "临时绕行路；临时支路",
        "en": "a road or route that is used when the usual one is closed"
    },
    {
        "id": "devout",
        "type": "adj.",
        "ch": "笃信宗教的；虔诚的",
        "en": "believing strongly in a particular religion and obeying its laws and practices"
    },
    {
        "id": "disembark",
        "type": "v.",
        "ch": "下（车、船、飞机等）",
        "en": "to leave a vehicle, especially a ship or an aircraft, at the end of a journey"
    },
    {
        "id": "dismantle",
        "type": "v.",
        "ch": "拆开，拆卸（机器或结构）",
        "en": "to take apart a machine or structure so that it is in separate pieces",
        "related": "dismantled"
    },
    {
        "id": "divination",
        "type": "n.",
        "ch": "占卜；预测；预言",
        "en": "the act of finding out and saying what will happen in the future"
    },
    {
        "id": "dune",
        "type": "n.",
        "ch": "（风吹积成的）沙丘",
        "en": "a small hill of sand formed by the wind, near the sea or in a desert",
        "related": "dunes"
    },
    {
        "id": "dungeon",
        "type": "n.",
        "ch": "（尤指城堡中的）地牢，土牢",
        "en": "a dark underground room used as a prison, especially in a castle"
    },
    {
        "id": "elixir",
        "type": "n.",
        "ch": "圣水",
        "en": "a magic liquid that is believed to cure illnesses or to make people live for ever"
    },
    {
        "id": "emboss",
        "type": "v.",
        "ch": "压印浮凸字体（或图案）；凹凸印",
        "en": "to put a raised design or piece of writing on paper, leather, etc.",
        "related": "embossed"
    },
    {
        "id": "embroider",
        "type": "v.",
        "ch": "刺绣",
        "en": "to decorate cloth with a pattern of stitches usually using coloured thread",
        "related": "embroidered"
    },
    {
        "id": "emerald",
        "type": "n.",
        "ch": "祖母绿；绿宝石；翡翠",
        "en": "a bright green precious stone",
        "related": "emeralds"
    },
    {
        "id": "encampment",
        "type": "n.",
        "ch": "（常指临时居住的）营房，营地",
        "en": "a group of tents, huts , etc. where people live together, usually for only a short period of time"
    },
    {
        "id": "encrusted",
        "type": "adj.",
        "ch": "硬壳覆盖的；形成硬外层的",
        "en": "covered with a thin hard layer of sth",
    },
    {
        "id": "esperanto",
        "type": "n.",
        "ch": "世界语（1887 年创制的一种人造国际语言，以欧洲主要语言为基础）",
        "en": "an artificial language invented in 1887 as a means of international communication, based on the main European languages but with easy grammar and pronunciation"
    },
    {
        "id": "ewe",
        "type": "n.",
        "ch": "母羊；雌羊；牝羊",
        "en": "a female sheep",
        "related": "ewes"
    },
    {
        "id": "exultant",
        "type": "adj.",
        "ch": "欢欣鼓舞的；兴高采烈的；得意扬扬的",
        "en": "feeling or showing great pride or happiness especially because of sth exciting that has happened"
    },
    {
        "id": "falcon",
        "type": "n.",
        "ch": "隼",
        "en": "a bird of prey(= a bird that kills other creatures for food) with long pointed wings"
    },
    {
        "id": "fidget",
        "type": "v.",
        "ch": "坐立不安；烦躁",
        "en": "to keep moving your body, your hands or your feet because you are nervous, bored, excited, etc.",
        "related": "fidgeted"
    },
    {
        "id": "flask",
        "type": "n.",
        "ch": "烧瓶",
        "en": "a bottle with a narrow top, used in scientific work for mixing or storing chemicals"
    },
    {
        "id": "fleeting",
        "type": "adj.",
        "ch": "短暂的；闪现的",
        "en": "lasting only a short time"
    },
    {
        "id": "fowl",
        "type": "n.",
        "ch": "家禽",
        "en": "a bird that is kept for its meat and eggs, for example a chicken"
    },
    {
        "id": "freight",
        "type": "n.",
        "ch": "（海运、空运或陆运的）货物",
        "en": "goods that are transported by ships, planes, trains or lorries/trucks"
    },
    {
        "id": "fringe",
        "type": "n.",
        "ch": "（地区或群体的）边缘",
        "en": "the outer edge of an area or a group",
        "related": "fringes"
    },
    {
        "id": "furious",
        "type": "adj.",
        "ch": "狂怒的；暴怒的",
        "en": "very angry"
    },
    {
        "id": "gallop",
        "type": "v.",
        "ch": "飞跑；奔跑",
        "en": "to run very quickly",
        "related": "galloped"
    },
    {
        "id": "game",
        "type": "n.",
        "ch": "猎物；野禽；野味",
        "en": "wild animals or birds that people hunt for sport or food"
    },
    {
        "id": "garment",
        "type": "n.",
        "ch": "（一件）衣服",
        "en": "a piece of clothing",
        "related": "garments"
    },
    {
        "id": "genie",
        "type": "n.",
        "ch": "（阿拉伯故事中，尤指瓶子或灯里的）精灵",
        "en": "(in Arabian stories) a spirit with magic powers, especially one that lives in a bottle or a lamp",
        "related": "genies"
    },
    {
        "id": "grove",
        "type": "n.",
        "ch": "树丛；小树林",
        "en": "a small group of trees"
    },
    {
        "id": "grouch",
        "type": "n.",
        "ch": "好抱怨（或发牢骚）的人",
        "en": "a person who complains a lot"
    },
    {
        "id": "gypsy",
        "type": "n.",
        "ch": "吉卜赛人",
        "en": "a member of a race of people, originally from Asia, who traditionally travel around and live in caravans(有篷马车).",
    },
    {
        "id": "hawk",
        "type": "n.",
        "ch": "鹰；隼",
        "en": "a strong fast bird of prey(= a bird that kills other creatures for food).",
        "related": "hawks"
    },
    {
        "id": "herald",
        "type": "v.",
        "ch": "是（某事）的前兆；预示",
        "en": "to be a sign that sth is going to happen",
    },
    {
        "id": "hoof",
        "type": "n.",
        "ch": "（马等动物的）蹄",
        "en": "the hard part of the foot of some animals, for example horses",
        "related": "hooves"
    },
    {
        "id": "hookah",
        "type": "n.",
        "ch": "水烟袋；水烟筒",
        "en": "a long pipe for smoking that passes smoke through a container of water to cool it",
        "related": "hookahs"
    },
    {
        "id": "hunch",
        "type": "n.",
        "ch": "预感；直觉",
        "en": "a feeling that sth is true even though you do not have any evidence to prove it",
        "related": "hunches"
    },
    {
        "id": "incessant",
        "type": "adj.",
        "ch": "不停的；持续不断的",
        "en": "never stopping",
        "related": "incessantly"
    },
    {
        "id": "inconspicuous",
        "type": "adj.",
        "ch": "不引人注目的；不起眼的",
        "en": "not attracting attention; not easy to notice"
    },
    {
        "id": "incredulous",
        "type": "adj.",
        "ch": "不肯相信的；不能相信的；表示怀疑的",
        "en": "not willing or not able to believe sth; showing an inability to believe sth"
    },
    {
        "id": "infidel",
        "type": "n.",
        "ch": "异教徒",
        "en": "an offensive way of referring to sb who does not believe in what the speaker considers to be the true religion",
        "related": "infidels"
    },
    {
        "id": "inscribe",
        "type": "v.",
        "ch": "在…上写（词语、名字等）",
        "en": "to write or cut words, your name, etc. onto sth",
        "related": "inscribed"
    },
    {
        "id": "irritable",
        "type": "adj.",
        "ch": "易怒的；暴躁的",
        "en": "getting annoyed easily"
    },
    {
        "id": "kerchief",
        "type": "n.",
        "ch": "方头巾；方围巾",
        "en": "a square piece of cloth worn on the head or around the neck"
    },
    {
        "id": "knapsack",
        "type": "n.",
        "ch": "小背包",
        "en": "a small rucksack"
    },
    {
        "id": "Koran",
        "type": "n.",
        "ch": "《古兰经》",
        "en": "the holy book of the Islamic religion, written in Arabic, containing the word of Allah"
    },
    {
        "id": "laden",
        "type": "adj.",
        "ch": "载满的；装满的",
        "en": "heavily loaded with sth"
    },
    {
        "id": "lame",
        "type": "adj.",
        "ch": "瘸的；跛的",
        "en": "unable to walk well because of an injury to the leg or foot"
    },
    {
        "id": "lament",
        "type": "v.",
        "ch": "对…感到悲痛；痛惜；对…表示失望",
        "en": "to feel or express great sadness or disappointment about sb/sth",
        "related": "lamenting"
    },
    {
        "id": "leaf",
        "type": "v.",
        "ch": "翻书页；翻…的书页",
        "en": "",
        "related": "leafing"
    },
    {
        "id": "Levant",
        "type": "n.",
        "ch": "地中海东部沿岸诸国和岛屿",
        "en": "the region in the eastern Mediterranean comprising modern-day Lebanon, Israel, and parts of Syria and Turkey"
    },
    {
        "id": "levanter",
        "type": "n.",
        "ch": "(地中海上的)强烈东风",
        "en": "a strong easterly wind that blows in the western Mediterranean area, especially in the late summer"
    },
    {
        "id": "linen",
        "type": "n.",
        "ch": "亚麻布",
        "en": "a type of cloth made from flax , used to make high-quality clothes, sheets, etc."
    },
    {
        "id": "lizard",
        "type": "n.",
        "ch": "蜥蜴",
        "en": "a small reptile with a rough skin, four short legs and a long tail",
        "related": "lizards"
    },
    {
        "id": "mania",
        "type": "n.",
        "ch": "强烈的欲望，狂热，极大的热情",
        "en": "an extremely strong desire or enthusiasm for sth, often shared by a lot of people at the same time"
    },
    {
        "id": "Mecca",
        "type": "n.",
        "ch": "麦加（沙特阿拉伯城市，伊斯兰教圣地，先知穆罕默德出生地）",
        "en": "a city in Saudi Arabia that is the holiest city of Islam, being the place where Muhammad was born"
    },
    {
        "id": "melancholy",
        "type": "adj.",
        "ch": "（令人）悲哀的；（令人）沮丧的",
        "en": "very sad or making you feel sadness"
    },
    {
        "id": "mercury",
        "type": "n.",
        "ch": "水星",
        "en": "the smallest planet in the solar system , nearest to the sun"
    },
    {
        "id": "mineral",
        "type": "n.",
        "ch": "矿物；矿物质",
        "en": "a substance that is naturally present in the earth and is not formed from animal or vegetable matter, for example gold and salt."
    },
    {
        "id": "mint",
        "type": "n.",
        "ch": "薄荷",
        "en": "a plant with dark green leaves that have a fresh smell and taste and are added to food and drinks to give flavour, and used in cooking as a herb and to decorate food"
    },
    {
        "id": "Moor",
        "type": "n.",
        "ch": "摩尔人（居住在非洲西北部的穆斯林，曾于8世纪占领西班牙部分地区）",
        "en": "a member of a race of Muslim people living in NW Africa who entered and took control of part of Spain in the 8th century",
        "related": "Moors"
    },
    {
        "id": "mule",
        "type": "n.",
        "ch": "骡子",
        "en": "an animal that has a horse and a donkey as parents, used especially for carrying loads",
        "related": "mules"
    },

    {
        "id": "mumble",
        "type": "v.",
        "ch": "嘟哝；口齿不清地说",
        "en": "to speak or say sth in a quiet voice in a way that is not clear",
        "related": "mumbling"
    },
    {
        "id": "Muslim",
        "type": "n.",
        "ch": "穆斯林；伊斯兰教信徒",
        "en": "a person whose religion is Islam"
    },
    {
        "id": "nocturnal",
        "type": "adj.",
        "ch": "夜间发生的",
        "en": "happening during the night"
    },
    {
        "id": "nostalgia",
        "type": "n.",
        "ch": "怀旧；念旧",
        "en": "a feeling of sadness mixed with pleasure and affection when you think of happy times in the past"
    },
    {
        "id": "oasis",
        "type": "n.",
        "ch": "（沙漠中的）绿洲",
        "en": "an area in the desert where there is water and where plants grow"
    },
    {
        "id": "obligation",
        "type": "n.",
        "ch": "（已承诺的或法律等规定的）义务，责任",
        "en": "something which you must do because you have promised, because of a law, etc."
    },
    {
        "id": "omen",
        "type": "n.",
        "ch": "预兆；前兆；征兆",
        "en": "a sign of what is going to happen in the future",
        "related": "omens"
    },
    {
        "id": "palm",
        "type": "n.",
        "ch": "棕榈树",
        "en": "a straight tree with a mass of long leaves at the top, growing in tropical countries.",
        "related": "palms"
    },
    {
        "id": "parchment",
        "type": "n.",
        "ch": "羊皮纸",
        "en": "material made from the skin of a sheep or goat, used in the past for writing on",
        "related": "parchments"
    },
    {
        "id": "Persian",
        "type": "n.",
        "ch": "波斯人",
        "en": "a person from ancient Persia, or modern Persia, now called Iran",
    },
    {
        "id": "pharaoh",
        "type": "n.",
        "ch": "法老（古埃及国王）",
        "en": "a ruler of ancient Egypt",
    },
    {
        "id": "pilgrimage",
        "type": "n.",
        "ch": "朝圣之旅",
        "en": "a journey to a holy place for religious reasons"
    },
    {
        "id": "plank",
        "type": "n.",
        "ch": "木板；板条",
        "en": "a long narrow flat piece of wood",
        "related": "planks"
    },
    {
        "id": "platter",
        "type": "n.",
        "ch": "大平盘",
        "en": "a large plate that is used for serving food",
        "related": "platters"
    },
    {
        "id": "plaza",
        "type": "n.",
        "ch": "（尤指西班牙语城镇的）露天广场",
        "en": "a public outdoor square especially in a town where Spanish is spoken"
    },
    {
        "id": "pouch",
        "type": "n.",
        "ch": "小袋子；荷包",
        "en": "a small bag, usually made of leather, and often carried in a pocket or attached to a belt"
    },
    {
        "id": "practiced",
        "type": "adj.",
        "ch": "熟练的；精通的；老练的",
        "en": "expert in doing something because of long experience"
    },
    {
        "id": "prod",
        "type": "v.",
        "ch": "戳；杵；捅",
        "en": "to push sb/sth with your finger or with a pointed object",
        "related": "prodded"
    },
    {
        "id": "prognostication",
        "type": "n.",
        "ch": "预言；预告；预报",
        "en": "a thing that sb says will happen in the future",
        "related": "prognostications"
    },
    {
        "id": "Prophet",
        "type": "n.",
        "ch": "（基督教、犹太教和伊斯兰教的）先知",
        "en": "(in the Christian, Jewish and Muslim religions) a person sent by God to teach the people and give them messages from God"
    },
    {
        "id": "proprietor",
        "type": "n.",
        "ch": "业主；所有人",
        "en": "the owner of a business, a hotel, etc."
    },
    {
        "id": "rabbi",
        "type": "n.",
        "ch": "拉比（犹太教经师或神职人员）",
        "en": "a Jewish religious leader or a teacher of Jewish law"
    },
    {
        "id": "raid",
        "type": "n.",
        "ch": "突然袭击",
        "en": "a short surprise attack on an enemy by soldiers, ships or aircraft"
    },
    {
        "id": "ramadan",
        "type": "n.",
        "ch": "伊斯兰历九月，斋月（斋月期间，穆斯林从破晓到日落禁食）",
        "en": "the 9th month of the Muslim year, when Muslims do not eat or drink between dawn and sunset"
    },
    {
        "id": "ramp",
        "type": "n.",
        "ch": "斜坡；坡道",
        "en": "a slope that joins two parts of a road, path, building, etc. when one is higher than the other"
    },
    {
        "id": "raven",
        "type": "adj.",
        "ch": "乌黑光亮的",
        "en": "shiny and black"
    },
    {
        "id": "ravine",
        "type": "n.",
        "ch": "沟壑；溪谷",
        "en": "a deep, very narrow valley with steep sides",
        "related": "ravines"
    },
    {
        "id": "retort",
        "type": "v.",
        "ch": "（生气或幽默地）反驳，回嘴",
        "en": "to reply quickly to a comment, in an angry, offended or humorous way",
        "related": "retorting"
    },
    {
        "id": "revolver",
        "type": "n.",
        "ch": "左轮手枪",
        "en": "a small gun that has a container for bullets that turns around so that shots can be fired quickly without having to stop to put more bullets in"
    },
    {
        "id": "rueful",
        "type": "adj.",
        "ch": "悲伤的；懊悔的；沮丧的",
        "en": "feeling or showing that you are sad or sorry",
        "related": "ruefully"
    },
    {
        "id": "sack",
        "type": "n.",
        "ch": "麻布（或厚纸、塑料等）大袋",
        "en": "a large bag with no handles, made of strong rough material or strong paper or plastic, used for storing and carrying, for example flour, coal, etc.",
        "related": "sacks"
    },
    {
        "id": "Sacred Heart",
        "type": "n.",
        "ch": "神圣之心；圣心",
        "en": "in the Roman Catholic Church, the heart of Jesus Christ, seen as a symbol of his love"
    },
    {
        "id": "sacristy",
        "type": "n.",
        "ch": "（教堂的）祭衣间",
        "en": "a room in a church where a priest prepares for a service by putting on special clothes"
    },
    {
        "id": "sandal",
        "type": "n.",
        "ch": "凉鞋",
        "en": "a type of light open shoe that is worn in warm weather. The top part consists of leather bands that attach the sole to your foot.",
        "related": "sandals"
    },
    {
        "id": "scabbard",
        "type": "n.",
        "ch": "（刀、剑的）鞘",
        "en": "a cover for a sword that is made of leather or metal"
    },
    {
        "id": "scimitar",
        "type": "n.",
        "ch": "（多为东方人所用的）短弯刀",
        "en": "a short curved sword with one sharp edge, used especially in Eastern countries",
        "related": "scimitars"
    },
    {
        "id": "seer",
        "type": "n.",
        "ch": "（尤指旧时）预言家，先知",
        "en": "(especially in the past) a person who claims that they can see what is going to happen in the future",
        "related": "seers"
    },
    {
        "id": "seminary",
        "type": "n.",
        "ch": "神学院；修院",
        "en": "a college where priests, ministers or rabbis are trained"
    },
    {
        "id": "sentinel",
        "type": "n.",
        "ch": "哨兵",
        "en": "a soldier whose job is to guard sth",
        "related": "sentinels"
    },
    {
        "id": "serpent",
        "type": "n.",
        "ch": "蛇；（尤指）大蛇",
        "en": "a snake, especially a large one"
    },
    {
        "id": "shepherd",
        "type": "n.",
        "ch": "牧羊人；羊倌",
        "en": "a person whose job is to take care of sheep"
    },
    {
        "id": "shovel",
        "type": "n.",
        "ch": "铲；铁铲",
        "en": "a tool with a long handle and a broad blade with curved edges, used for moving earth, snow, sand, etc."
    },
    {
        "id": "shred",
        "type": "n.",
        "ch": "（撕或切的）细条，碎片",
        "en": "a small thin piece that has been torn or cut from sth",
        "related": "shreds"
    },
    {
        "id": "shrub",
        "type": "n.",
        "ch": "灌木",
        "en": "a large plant that is smaller than a tree and that has several stems of wood coming from the ground"
    },
    {
        "id": "smother",
        "type": "v.",
        "ch": "把（火）闷熄",
        "en": "to make a fire stop burning by covering it with sth",
        "related": "smothered"
    },
    {
        "id": "sorcerer",
        "type": "n.",
        "ch": "（故事中的）术士，男巫，巫师",
        "en": "(in stories) a man with magic powers, who is helped by evil spirits",
        "related": "sorcerers"
    },
    {
        "id": "spice",
        "type": "n.",
        "ch": "（调味）香料",
        "en": "one of the various types of powder or seed that come from plants and are used in cooking. Spices have a strong taste and smell",
        "related": "spices"
    },
    {
        "id": "spoil",
        "type": "v.",
        "ch": "变坏；变质；腐败",
        "en": "to become bad so that it can no longer be eaten",
    },
    {
        "id": "squint",
        "type": "v.",
        "ch": "瞇着眼睛看",
        "en": "to look at sth with your eyes partly shut in order to keep out bright light or to see better",
        "related": "squinted"
    },
    {
        "id": "stable",
        "type": "n.",
        "ch": "马厩",
        "en": "a building in which horses are kept"
    },
    {
        "id": "steed",
        "type": "n.",
        "ch": "坐骑",
        "en": "a horse to ride on"
    },
    {
        "id": "suffuse",
        "type": "v.",
        "ch": "布满；弥漫于；充满",
        "en": "to spread all over or through sb/sth",
        "related": "suffused"
    },
    {
        "id": "surveillance",
        "type": "n.",
        "ch": "（对犯罪嫌疑人或可能发生犯罪的地方的）监视",
        "en": "the act of carefully watching a person suspected of a crime or a place where a crime may be committed"
    },
    {
        "id": "sycamore",
        "type": "n.",
        "ch": "西卡莫；（美国）悬铃木",
        "en": "an American plane tree"
    },
    {
        "id": "symphony",
        "type": "n.",
        "ch": "交响乐；交响曲",
        "en": "a long complicated piece of music for a large orchestra , in three or four main parts (called movements)"
    },
    {
        "id": "tablet",
        "type": "n.",
        "ch": "（固定于墙上作纪念的）牌，碑，匾",
        "en": "a flat piece of stone that has words written on it, especially one that has been fixed to a wall in memory of an important person or event"
    },
    {
        "id": "tapestry",
        "type": "n.",
        "ch": "壁毯；挂毯",
        "en": "a picture or pattern that is made by weaving coloured wool onto heavy cloth",
        "related": "tapestries"
    },
    {
        "id": "tether",
        "type": "n.",
        "ch": "（拴牲畜的）拴绳，拴链",
        "en": "a rope or chain used to tie an animal to sth, allowing it to move around in a small area",
        "related": "tethers"
    },
    {
        "id": "theology",
        "type": "n.",
        "ch": "神学；宗教学；宗教信仰学",
        "en": "the study of religion and beliefs"
    },
    {
        "id": "tinge",
        "type": "v.",
        "ch": "（轻微地）给…着色，给…染色",
        "en": "to add a small amount of colour to sth",
        "related": "tinged"
    },
    {
        "id": "tome",
        "type": "n.",
        "ch": "（尤指严肃的）大部头书，巨著",
        "en": "a large heavy book, especially one dealing with a serious topic"
    },
    {
        "id": "townspeople",
        "type": "n.",
        "ch": "镇民；市民；城里人",
        "en": "people who live in towns, not in the countryside"
    },
    {
        "id": "tray",
        "type": "n.",
        "ch": "盘；托盘；碟",
        "en": "a flat piece of wood, metal or plastic with raised edges, used for carrying or holding things, especially food",
        "related": "trays"
    },
    {
        "id": "treacherous",
        "type": "adj.",
        "ch": "有潜在危险的",
        "en": "dangerous, especially when seeming safe"
    },
    {
        "id": "turban",
        "type": "n.",
        "ch": "（穆斯林或锡克教男教徒等用的）包头巾",
        "en": "a long piece of cloth wound tightly around the head, worn, for example, by Muslim or Sikh men",
        "related": "turbans"
    },
    {
        "id": "unsheathe",
        "type": "v.",
        "ch": "拔(剑等)出鞘",
        "en": "to remove a sword from a sheath",
        "related": "unsheathed"
    },
    {
        "id": "vanity",
        "type": "n.",
        "ch": "自负；自大；虚荣；虚荣心",
        "en": "too much pride in your own appearance, abilities or achievements",
        "related": "vanities"
    },
    {
        "id": "village",
        "type": "n.",
        "ch": "村庄；村镇",
        "en": "a very small town located in a country area"
    },
    {
        "id": "wary",
        "type": "adj.",
        "ch": "（对待人或事物时）小心的，谨慎的",
        "en": "careful when dealing with sb/sth because you think that there may be a danger or problem"
    },
    {
        "id": "weep",
        "type": "v.",
        "ch": "（通常因悲伤）哭泣，流泪",
        "en": "to cry, usually because you are sad",
        "related": "wept"
    },
    {
        "id": "whet",
        "type": "v.",
        "ch": "刺激…的欲望；增强…的兴趣",
        "en": "to increase your desire for or interest in sth",
        "related": "whets"
    },
    {
        "id": "zenith",
        "type": "n.",
        "ch": "天顶（太阳或月亮在天空中的最高点）",
        "en": "the highest point that the sun or moon reaches in the sky, directly above you"
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
