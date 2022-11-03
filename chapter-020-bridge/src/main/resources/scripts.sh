curl --location --request POST 'http://localhost:8080/api/v1/authors/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "authorName": "Dzung"
}'

curl --location --request POST 'http://localhost:8080/api/v1/books/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bookName": "Master Design Patterns",
    "authorId": 56,
    "categoryId": 9
}'