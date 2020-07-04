// region canvas
function set_canvas_size(canvas, width, height) {
    canvas.setWidth(width);
    canvas.setHeight(height);
}

function set_canvas_background(canvas, color, img) {
    if (color) {
        canvas.setBackgroundColor(color);
    }
    else {
        canvas.setBackgroundColor('');
    }

    if (img) {
        canvas.setBackgroundColor({
            source: img,
            repeat: 'repeat'
        }, function () {
            canvas.renderAll();
        });
    }
    else {
        canvas.setBackgroundImage('');
    }
    canvas.renderAll();
}
// endregion

// region brush
function set_brush_attr_on_canvas(canvas, color, width) {
    canvas.freeDrawingBrush.color = color;
    canvas.freeDrawingBrush.width = width;
    canvas.renderAll();
}
// endregion

// region add image
function add_image(canvas, img) {
    let obj = new fabric.Image(img);
    add_object_on_canvas(canvas, obj);
}

function add_object_on_canvas(canvas, obj) {
    canvas.add(obj);
    canvas.centerObject(obj);
    obj.setCoords();
    canvas.setActiveObject(obj);
    canvas.renderAll();
}
// endregion


function rgb2hex(rgb) {
    rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
    return (rgb && rgb.length === 4) ? "#" +
        ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
}

function getTimestamp() {
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let day = today.getDate();
    let hour = today.getHours();
    let minute = today.getMinutes();
    let second = today.getSeconds();

    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;
    hour = hour < 10 ? '0' + hour : hour;
    minute = minute < 10 ? '0' + minute : minute;
    second = second < 10 ? '0' + second : second;

    let result = year + "-" +
        month + "-" +
        day + "." +
        hour + "." +
        minute + "." +
        second;
    return result;
}

