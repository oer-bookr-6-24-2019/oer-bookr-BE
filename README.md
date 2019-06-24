# oer-bookr-BackEnd



### Login
## https://sgs-lambda-bookr.herokuapp.com/oauth/token

Headers:
``` 
Authorization : Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0
Content-Type : application/x-www-form-urlencoded
```
Body (application/x-www-form-urlencoded)
parenthesized parameters used for testing
```
grant_type : password
username : yourUsername (admin)
password : yourPassword (password)
```


### Books
## https://sgs-lambda-bookr.herokuapp.com/book/books

Returns:
```
[
  {
    "author": "string",
    "bookid": 0,
    "booktitle": "string",
    "imageurl": "string",
    "license": "string",
    "publisher": "string",
    "reviews": [
      {
        "rating": 0,
        "review": "string",
        "reviewid": 0,
        "user": "string"
      }
    ],
    "url": "string"
  }
]
```
