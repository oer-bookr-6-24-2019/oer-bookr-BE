# oer-bookr-BackEnd

### Create user

Endpoint: https://sgs-lambda-bookr.herokuapp.com/createnewuser

Body (application/Json)
```
"username" : "yourUsername"
"password" : "yourPassword"
```

Returns
```
empty []
```
https://sgs-lambda-bookr.herokuapp.com/users/users to list all users (must be admin)


### Login

Endpoint: https://sgs-lambda-bookr.herokuapp.com/oauth/token

Headers:
``` 
Authorization : Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0
Content-Type : application/x-www-form-urlencoded
```
Body (application/x-www-form-urlencoded)
parenthesized values used for testing
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

Documentation: https://sgs-lambda-bookr.herokuapp.com/swagger-ui.html#/books-controller

books/add Example: 
```
{
  "author": "string",
  "booktitle": "string",
  "imageurl": "string",
  "license": "string",
  "publisher": "string",
  "url": "string"
}
```


### Reviews

Documentation: https://sgs-lambda-bookr.herokuapp.com/swagger-ui.html#/reviews-controller

reviews/add/bybookid/{bookid} Example:
```
{
  "rating": 4,
  "review": "string",
  "user": "string"
}
```

reviews/update/byreview/{reviewid}  Example:
```
{
  "rating": 3,
  "review": "changedString",
  "user": "string"*
}
```
*required


