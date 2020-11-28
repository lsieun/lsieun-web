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

<div class="w3-panel w3-card w3-light-grey">
    <p onclick="$(this).next().toggle()">Example</p>
    <div class="w3-code notranslate" style="display: block;text-align: left; line-height: 18px; font-size: 13px; font-family:'Courier New', Courier, monospace; overflow: auto;">
        hello world
    </div>
</div>

<div class="w3-panel w3-card w3-light-grey">
    <p onclick="$(this).next().toggle()">secp256r1 and secp384r1</p>
    <div class="w3-code notranslate" style="display: block;">
        <span class="w3-tooltip w3-hover-blue">00 0A<span style="position:absolute;left:0;bottom:18px" class="w3-text w3-tag w3-round-xlarge">extension type(10)</span></span>
        <span class="w3-tooltip w3-hover-blue">00 06<span style="position:absolute;left:0;bottom:18px" class="w3-text w3-tag w3-round-xlarge">extension length</span></span>
        <span class="w3-tooltip w3-hover-blue">00 04<span style="position:absolute;left:0;bottom:18px" class="w3-text w3-tag w3-round-xlarge">Supported Group List Length</span></span>
        <span class="w3-tooltip w3-hover-blue">00 17<span style="position:absolute;left:0;bottom:18px" class="w3-text w3-tag w3-round-xlarge">secp256r1</span></span>
        <span class="w3-tooltip w3-hover-blue">00 18<span style="position:absolute;left:0;bottom:18px" class="w3-text w3-tag w3-round-xlarge">secp384r1</span></span>
    </div>
</div>
```

```html
<div id="form" style="width:350px;">
    <fieldset>
        <legend style="color:blue;font-weight:bold;">General Information</legend>
        <table>
            <tr>
                <td><span style="text-decoration:underline">C</span>hange Password To:</td>
                <td><input type="text"/></td>
            </tr>
            <tr>
                <td><span style="text-decoration:underline">C</span>onfirm Password:</td>
                <td><input type="text"/></td>
            </tr>
        </table>
    </fieldset>
</div>
```

How the Java virtual machine handles method invocation and return

## Image

图片居中显示

```html
<div class="w3-display-container w3-center">
    <img class="w3-image w3-center" src="/images/crypto/aes/aes_rotation.png" alt="Rotation"/>
    <div class="w3-display-bottomright w3-container"><p>Bottom Right</p></div>
</div>

<div class="w3-display-container w3-center">
    <img class="w3-image w3-center" src="/images/java/classfile/aes_rotation.png" alt="Rotation"/>
</div>

<div class="w3-display-container w3-center">
    <a href="javascript:void(0);" onclick="$(this).next().toggle()">查看图片</a>
    <img style="display: none;" class="w3-image w3-center" src="/images/java/jvm/jvm_runtime_memory.png" alt="JVM runtime memory"/>
</div>

<img class="w3-image" src="/images/mountains.jpg" alt="the Statue of Liberty" onclick="display_img(this)"/>

<img class="w3-image" src="/images/mountains.jpg" alt="the Statue of Liberty"/>

<img width="800" src="/images/crypto/rsa/rsa_padding.png" alt="RSA Padding"/>
```

## Color

```html
<sub class="my_note" style="color:red">笔记：</sub>
<sub class="my_note" style="color:blue">笔记：</sub>
<sub class="my_note" style="color:green">笔记：</sub>
<sub class="my_note" style="color:green"></sub>
In the same vein<sub class="my_note" style="color:green">以同样的方式</sub>
<code style="color:blue"></code>
<code class="w3-codespan"></code>
<sub class="my_note" style="color:blue">笔记：</sub>
<img src="/images/note.png" onclick="toggle_paragraph_sub_note(this)"/>

<div title="HelloWorld.java" class="javacode">

</div>

<div title="" hidden class="plaintext">

</div>

<div title="" class="plaintext">

</div>


<p class="indented">

</p>
```

Code

```html
<div class="w3-panel w3-card w3-light-grey">
  <h3>Example</h3>
  <div class="w3-code notranslate">
    fruits[0] = "Banana";<br>
    fruits[1] = "Apple";<br>
    fruits[2] = "Mango";<br>
    fruits[3] = "Plum";<br>
  </div>
</div>
```

选用颜色的原则：

- 以温和颜色为主，尽量避免使用太鲜艳的颜色，否则容易太刺眼。
- 使用的颜色的种类控制在三、四个，太多的颜色容易让人眼花，显得花里胡哨。

对于不同颜色的使用：

- 黑色：正文颜色
- 蓝色：天空是蓝色的。蓝色是高高的天空所展现出的颜色，所以我想用“蓝色”表示“格调较高”的文字，可能是名人说意味深远的话，也可以作为提醒。有人说，蓝色有助於加强记忆力。蓝色用来做比较重要的。
- 绿色：大地上的树木是绿色的。绿色，是树木的颜色，更加接近于大地，所以我想用绿色来表示自己的观点。
- 红色：红色一般用来标注重要的知识点，以及容易遗漏，容易出错的地方。因为红色较为醒目，所以不应太常使用红色，以免对红色产生视觉疲劳。

## Table

```html
<table class="w3-table-all w3-hoverable w3-centered">
<caption>Title</caption>
<thead onclick="$(this).next().toggle()">
<tr class="w3-green">
  <th>First Name</th>
  <th>Last Name</th>
  <th>Points</th>
</tr>
</thead>
<tbody style="display: none;">
<tr class="w3-hover-blue">
  <td>Jill</td>
  <td>Smith</td>
  <td>50</td>
</tr>
</tbody>
</table>
```

添加注意事项的例子：

```html
<table class="lamp">
    <tbody>
    <tr>
    <th style="width:34px">
        <img src="/images/lamp.jpg" alt="Note" style="height:32px;width:32px">
    </th>
    <td>
        <b>Note:</b> For W3C compliant CSS: If you define the <code>color</code> property, you must also define the <code>background-color</code>
        property.
    </td>

    </tr>
    </tbody>
</table>
<hr>
```

## Reference

```html
<h2>References</h2>

<ul>
    <li><a class="external" href="https://en.wikipedia.org/wiki/American_Dream" target="_blank">Wiki: American Dream</a></li>
</ul>
```
