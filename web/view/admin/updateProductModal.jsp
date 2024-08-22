<%-- 
    Document   : updateProductModal
    Created on : Jun 19, 2024, 3:13:31 PM
    Author     : PMQUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="updateProductModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateBookModalLabel">Update Book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="updateBookForm" action="product?action=update" method="POST" enctype="multipart/form-data">
                    <!--id-->
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idUpdateInput" name="id">
                    </div>
                    <!--Name-->
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="nameUpdateInput" name="name">
                        <div id="nameUpdateError" class="error"></div>
                    </div>
                    <!--Price-->
                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="text" class="form-control" id="priceUpdateInput" name="price">
                        <div id="priceUpdateError" class="error"></div>
                    </div>
                    <!--Quantity-->
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="text" class="form-control" id="quantityUpdateInput" name="quantity">
                        <div id="quantityUpdateError" class="error"></div>
                    </div>
                    <!--Category-->
                    <div class="form-group">
                        <label for="category">Category: </label>
                        <div class="input-group">
                            <select class="custom-select" id="categoryUpdateInput" name="category_id">
                                <c:forEach items="${vectorCategory}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button">Category</button>
                            </div>
                        </div>
                    </div>
                    <!--Image-->
                    <div class="form-group">
                        <label for="image">Image: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Upload</span>
                            </div>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="imageUpdate" name="image"
                                       onchange="displayImage2(this)">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage2" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Preview"
                             style="display: none; max-width: 300px; max-height: 300px;">
                        <input type="hidden" id="currentImage" name="currentImage" value="">
                    </div>
                    <!--Description-->
                    <div class="form-group">
                        <label for="descriptionUpdateInput">Description:</label>
                        <textarea class="form-control" id="descriptionUpdate" name="description"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="updateBookForm"
                        onclick="validateForm2()">Update</button>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm2() {
        let name = $('#nameUpdateInput').val();
        let price = $('#priceUpdateInput').val();
        let quantity = $('#quantityUpdateInput').val();

        //xoá thông báo lỗi hiện tại
        $('.error').html('');

        if (name === '') {
            $('#nameUpdateError').html('Tên sách không được để trống');
        }

        if (price === '') {
            $('#priceUpdateError').html('Giá của quyển sách không được để trống');
        } else if (!$.isNumeric(price) || parseFloat(price) < 0) {
            $('#priceUpdateError').html('Giá của quyển sách phải là số và không được nhỏ hơn 0');
        }

        if (quantity === '') {
            $('#quantityUpdateError').html('Số lượng sách không được để trống');
        } else if (!$.isNumeric(quantity) || parseInt(price) < 0) {
            $('#priceUpdateError').html('Số lượng của quyển sách phải là số và không được nhỏ hơn 0');
        }

        // Kiểm tra nếu không có lỗi thì submit form
        let error = '';
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#updateBookForm').submit();
        } else {
            event.preventDefault();
        }
    }

    function displayImage2(input) {
        var previewImage = document.getElementById("previewImage2");
        var file = input.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        }

        reader.readAsDataURL(file);
    }

    <!-- function updateProductModal(id, name, description, author, price, quantity, image, categoryId) { -->
    function updateProductModal(button) {
        let id = $(button).closest('tr').find('td[name="id"]').text().trim();
        let name = $(button).closest('tr').find('td[name="name"]').text().trim();
        let author = $(button).closest('tr').find('td[name="author"]').text().trim();
        let price = $(button).closest('tr').find('td[name="price"]').text().trim().split('$')[0];
        console.log(price);
        let quantity = $(button).closest('tr').find('td[name="quantity"]').text().trim();
        let categoryText = $(button).closest('tr').find('td[name="category_id"]').text().trim();
        let description = $(button).closest('tr').find('td[name="description"]').text().trim();
        let image = $(button).closest('tr').find('td[name="image"]').find('img').attr('src');
        

        $('#idUpdateInput').val(id);
        $('#nameUpdateInput').val(name);
        $('#authorUpdateInput').val(author);
        $('#priceUpdateInput').val(price);
        $('#quantityUpdateInput').val(quantity);
        //loop through category list to select the category
        $('#categoryUpdateInput option').each(function() {
            if ($(this).text() === categoryText) {
                $(this).prop('selected', true);
            }
        });
        $('#descriptionUpdate').val(description);
        $('#previewImage2').attr('src', image);
        $('#previewImage2').css('display', 'block');
        $('#currentImage').val(image.substring(19));
    }

</script>
