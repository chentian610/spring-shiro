var recorder;
var wavData;
var wavName;
function startRecording() {
    HZRecorder.get(function (rec) {
        recorder = rec;
        recorder.start();
        recordingslist.innerHTML="";
        log('录音开始，请对准麦克风说话...');
        btnStart.disabled = true;
        btnStop.disabled = false;
        btnUpload.disabled = true;
    });
}

function stopRecording() {
    recorder.stop();
    log('录音结束，MP3导出中...');
    wavData = recorder.getBlob();
    var url = URL.createObjectURL(wavData);
    var div = document.createElement('div');
    var au = document.createElement('audio');
    var hf = document.createElement('a');

    au.controls = true;
    au.src = url;
    hf.href = url;

    wavName = new Date().getTime().toString() + '.wav';
    hf.download = wavName;
    hf.innerHTML = hf.download;
    div.appendChild(au);
    div.appendChild(hf);
    recordingslist.appendChild(div);
    btnUpload.disabled = false;
    btnStop.disabled = true;
}

function log(str) {
    recordingslist.innerHTML += str + '<br/>';
}



function funUpload() {
    if (!wavData) {
        alert("没有发现音频，请先录音后上传");
        return ;
    }
    var id = getSelectedRow();
    if(id == null){
        return ;
    }

    var fd = new FormData();
    var wavName = encodeURIComponent('audio_recording_' + new Date().getTime() + '.wav');
    fd.append('wavName', wavName);
    fd.append('file', wavData);
    fd.append("ruleId",id);

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            recordingslist.innerHTML += '上传成功：<a href="' + xhr.responseText + '" target="_blank">' + wavName + '</a>';
        }
        btnStart.disabled = false;
        btnStop.disabled = true;
        btnUpload.disabled = false;

    };

    xhr.open('POST', '../buscheck/upload');
    xhr.send(fd);
}

function uploadAudio() {
    //提交到服务器
    recorder.upload("../buscheck/upload", function (state, e) {
        switch (state) {
            case 'uploading':
                //var percentComplete = Math.round(e.loaded * 100 / e.total) + '%';
                break;
            case 'ok':
                //alert(e.target.responseText);
                alert("上传成功");
                break;
            case 'error':
                alert("上传失败");
                break;
            case 'cancel':
                alert("上传被取消");
                break;
        }
    });
}