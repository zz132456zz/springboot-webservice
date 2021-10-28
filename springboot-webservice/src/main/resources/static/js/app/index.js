var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {  // btn-update란 id를 가진 HTML 엘리면트에 click 이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록한다.
            _this.update();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';  // 글 등록이 성공하면 메인페이지(/)로 이동한다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {  // update function
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',  // 여러 HTTP Method 중 PUT 메소드를 선택한다. PostsApiController에 있는 API에서 이미 @PutMapping으로 선언했기 때문에 PUT을 사용해야 한다. 참고로 이는 REST 규약에 맞게 설정된 것이다.
            url: '/api/v1/posts/'+id,  // 어느 게시글을 수정할지 URL Path로 구분하기 위해 Path에 id를 추가한다.
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();