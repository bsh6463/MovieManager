(function ($) {

    // 검색 결과 vue object
    var search_result = new Vue({
        el: '#search-result', //어떤 html id와 연동? 종속될 것인가?
        data: {
            search_result : {}
        },
        method: {
            addButton: function (event) {
                console.log("add");
            }
        }
    });

    // 맛집 목록 vue object
    var movie_list = new Vue({
        el: '#movie-list',
        data: {
            movie_list : {}
        },
        methods: {

            deleteMovie: function (id) {
                $.ajax({
                    type: "DELETE" ,
                    async: true ,
                    url: `/api/delete/movie/${id}`,
                    timeout: 3000,
                    error: function (request, status, error) {

                    },
                    success: function (response, status, request) {
                        getMovieList();
                    }
                });
                //getMovieList();
            },

            addComment: function (id) {
                const content = $("#commentBox").val();
                $.ajax({
                    type: "POST" ,
                    async: true ,
                    url: `/api/comment/${id}?content=${content}`,
                    timeout: 3000,
                    error: function (request, status, error) {

                    },
                    success: function (response, status, request) {
                        getMovieList();
                    }
                });
                //getMovieList();
            }

        }
    });




    // search
    $("#searchButton").click(function () {
        const query = $("#searchBox").val();
        $.get(`/api/search?query=${query}`, function (response) {
            search_result.search_result = response;
            $('#search-result').attr('style','visible');
        });
    });

    // Enter
    $("#searchBox").keydown(function(key) {
        if (key.keyCode === 13) {
            const query = $("#searchBox").val();
            $.get(`/api/search?query=${query}`, function (response) {
                search_result.search_result = response;
                $('#search-result').attr('style','visible');
            });
        }
    });



    $("#addButton").click(function () {
        $.ajax({
            type: "POST" ,
            async: true ,
            url: "/api/add",
            timeout: 3000,
            data: JSON.stringify(search_result.search_result),
            contentType: "application/json",
            error: function (request, status, error) {

            },
            success: function (response, status, request) {
                getMovieList();
            }
        });
    });

    function getMovieList(){
        $.get(`/api/get/all`, function (response) {
            movie_list.movie_list = response;
        });
    }

    $(document).ready(function () {
        console.log("init")
    });

})(jQuery);
