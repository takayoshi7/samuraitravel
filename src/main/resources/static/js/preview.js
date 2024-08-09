const imageInput = document.getElementById('imageFile');
const imagePreview = document.getElementById('imagePreview');

// imageFileを変更したらイベント処理
imageInput.addEventListener('change', () => {
    if (imageInput.files[0]) {
        // 画像があったらファイル読み込み
        let fileReader = new FileReader();
        fileReader.onload = () => {
        // image要素を挿入
        imagePreview.innerHTML = `<img src="${fileReader.result}" class="mb-3">`;
        }
        fileReader.readAsDataURL(imageInput.files[0]);
    } else {
        // 画像ファイルが無ければ空に
        imagePreview.innerHTML = '';
    }
})