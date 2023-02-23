curl --location --request POST 'http://localhost:8080/api/v1/authors/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "authorName": "Dzung"
}'

curl --location --request POST 'http://localhost:8080/api/v1/categories/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "categoryName": "Technology"
}'

curl --location --request POST 'http://localhost:8080/api/v1/books/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bookName": "Master Design Patterns",
    "authorId": 1,
    "categoryId": 1
}'

curl --location --request GET 'http://localhost:8080/api/v1/authors'

curl --location --request GET 'http://localhost:8080/api/v1/categories'

curl --location --request GET 'http://localhost:8080/api/v1/books'