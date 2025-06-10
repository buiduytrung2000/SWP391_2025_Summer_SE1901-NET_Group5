window.onload = function () {
    var dropArea = document.getElementById('drop-area');
    var fileElem = document.getElementById('fileElem');
    var avatarPreview = document.getElementById('avatarPreview');
    var maxSize = 10 * 1024 * 1024; // 10MB

    // Event for selecting file via input
    fileElem.onchange = function () {
        if (fileElem.files.length > 0) {
            processFile(fileElem.files[0]);
        }
    };

    // Drag file into the drop area
    dropArea.addEventListener('dragover', function (e) {
        e.preventDefault();
        dropArea.classList.add('dragover');
    });

    dropArea.addEventListener('dragleave', function (e) {
        e.preventDefault();
        dropArea.classList.remove('dragover');
    });

    dropArea.addEventListener('drop', function (e) {
        e.preventDefault();
        dropArea.classList.remove('dragover');
        if (e.dataTransfer.files && e.dataTransfer.files[0]) {
            processFile(e.dataTransfer.files[0]);
            fileElem.files = e.dataTransfer.files;
        }
    });

    function processFile(file) {
        // Check file type
        if (!file.type.startsWith('image/')) {
            alert("Only image files are allowed!");
            return;
        }
        // Check file size
        if (file.size > maxSize) {
            alert("File is too large! Please select a file smaller than 10MB.");
            return;
        }

        var reader = new FileReader();
        reader.onload = function (e) {
            avatarPreview.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
};
