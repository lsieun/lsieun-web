# How to Write

## title

```html
<!-- 标题 -->
<h1 class="w3-center w3-padding-64 w3-tangerine"><span class="w3-tag w3-wide">文章の标题</span></h1>
<!-- 内容 -->

<div style="text-align: right">
    <a title="Table Of Content" href="/archive/1984/index.html">TOC</a>
    <a href="javascript:void(0);" onclick="toggle_all_sub_note()">Toggle Commentary</a>
</div>

<p>
    <a href="javascript:void(0);" style="color:gray" onclick="toggle_paragraph_sub_note(this)"><b>#Click to Toggle Commentary#</b></a>
    <img src="/images/note.png" onclick="toggle_paragraph_sub_note(this)"/>
</p>
```

```javascript
// Javascript inside this file
function toggle_all_sub_note() {
    $('#main_content sub.my_note').toggle();
}
```

## Content

```html
<div class="w3-panel w3-leftbar w3-light-grey">
    <p>
        这里可以写整段的文字，可以是备注之类的内容。
    </p>
</div>
```

## Image

图片居中显示

```html
<div style="text-align: center">
    <img src="/images/usa/statue_of_liberty.jpeg" alt="the Statue of Liberty"/>
</div>
```

## Color

```html
<sub class="my_note" style="color:red">笔记：</sub>
<sub class="my_note" style="color:blue">笔记：</sub>
<sub class="my_note" style="color:green">笔记：</sub>
<sub class="my_note" style="color:green"></sub>
In the same vein<sub class="my_note" style="color:green">以同样的方式</sub>
```

选用颜色的原则：

- 以温和颜色为主，尽量避免使用太鲜艳的颜色，否则容易太刺眼。
- 使用的颜色的种类控制在三、四个，太多的颜色容易让人眼花，显得花里胡哨。

对于不同颜色的使用：

- 黑色：正文颜色
- 蓝色：天空是蓝色的。蓝色是高高的天空所展现出的颜色，所以我想用“蓝色”表示“格调较高”的文字，可能是名人说意味深远的话，也可以作为提醒。有人说，蓝色有助於加强记忆力。蓝色用来做比较重要的。
- 绿色：大地上的树木是绿色的。绿色，是树木的颜色，更加接近于大地，所以我想用绿色来表示自己的观点。
- 红色：红色一般用来标注重要的知识点，以及容易遗漏，容易出错的地方。因为红色较为醒目，所以我不太常使用红色，以免对红色产生视觉疲劳。

## Reference

```html
<p>
    参考
</p>

<ul>
    <li><a href="https://en.wikipedia.org/wiki/American_Dream">Wiki: American Dream</a></li>
</ul>
```