/* blogThumbnailUpload.js – clone of editCustomerAvatar.js */
window.onload = function () {

    const dropArea       = document.getElementById('thumb-drop-area');
    const fileElem       = document.getElementById('thumbInput');
    const previewImg     = document.getElementById('thumbPreview');
    const previewBox     = document.getElementById('thumbPreviewBox');
    const removeBtn      = document.getElementById('thumbRemoveBtn');
    const hiddenUrlInput = document.getElementById('thumbnailUrl');

    const maxSize = 10 * 1024 * 1024; // 10 MB

    /* ------- helpers ------- */
    function resetPreview() {
        previewBox.style.display = 'none';
        dropArea.style.display   = 'block';
        previewImg.src           = '';
        hiddenUrlInput.value     = '';
        fileElem.value           = '';
    }


    function processFile(file) {
        if (!file.type.startsWith('image/')) {
            alert('Only image files are allowed!');
            return;
        }

        if (file.size > maxSize) {
            alert('File is too large! Please select a file < 10 MB.');
            return;
        }

        // local preview
        const reader = new FileReader();
        reader.onload = e => {
            previewImg.src         = e.target.result;
            previewBox.style.display = 'block';
            dropArea.style.display   = 'none';
        };
        reader.readAsDataURL(file);
    }

    /* ------- UI events ------- */
    dropArea.addEventListener('click', () => fileElem.click());

    fileElem.onchange = function () {
        if (fileElem.files.length > 0) {
            processFile(fileElem.files[0]);
        }
    };

    /* drag & drop */
    dropArea.addEventListener('dragover', e => {
        e.preventDefault();
        dropArea.classList.add('dragover');
    });
    dropArea.addEventListener('dragleave', e => {
        e.preventDefault();
        dropArea.classList.remove('dragover');
    });
    dropArea.addEventListener('drop', e => {
        e.preventDefault();
        dropArea.classList.remove('dragover');
        if (e.dataTransfer.files && e.dataTransfer.files[0]) {
            processFile(e.dataTransfer.files[0]);
            fileElem.files = e.dataTransfer.files; // so form sees it
        }
    });

    /* remove image */
    removeBtn.addEventListener('click', resetPreview);
};
