const dropArea = document.getElementById('drop-area');
const avatarPreview = document.getElementById('avatarPreview');

dropArea.addEventListener('dragover', function(e) {
  e.preventDefault();
  dropArea.classList.add('dragover');
});
dropArea.addEventListener('dragleave', function(e) {
  dropArea.classList.remove('dragover');
});
dropArea.addEventListener('drop', function(e) {
  e.preventDefault();
  dropArea.classList.remove('dragover');
  if (e.dataTransfer.files && e.dataTransfer.files[0]) {
    handleFiles(e.dataTransfer.files);
  }
});

function handleFiles(files) {
  if (!files.length) return;
  const file = files[0];
  const reader = new FileReader();
  reader.onload = function(e) {
    avatarPreview.src = e.target.result;
  };
  reader.readAsDataURL(file);
}
