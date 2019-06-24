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

Returns:
```
{
    "access_token": "ff9803b8-6a3d-4e64-9321-4f34cd84f924",
    "token_type": "bearer",
    "refresh_token": "9fe8bf45-1f3e-405c-9a35-a1d401f7968a",
    "expires_in": 3599,
    "scope": "read write trust"
}
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
