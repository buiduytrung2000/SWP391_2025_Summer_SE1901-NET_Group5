const modal = document.getElementById("addProductModal");
    document.getElementById("add").onclick = function() {
        modal.style.display = "flex";
    };
    function closeAddModal() {
        modal.style.display = "none";
    }
    // Đóng form khi click nền ngoài form
    modal.onclick = function(e) {
        if(e.target === modal) closeAddModal();
    }