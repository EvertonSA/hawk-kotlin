# hawk-kotlin

## Description
This repo suposed to be the Twitter Consumer but due to complications with the ammount of time wasted waiting for the TwitterDev to confirm my app I had to improvise and transform this application in a The Cat API consumer.

## Objects

### Breeds

breed object definition
```json
{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}
```

breed endpoints

**URL** : `/api/breeds/`

**Method** : `GET`

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Accounts.

**Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`

**URL** : `/api/fetchBreeds/`

**Method** : `GET`

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Data fetched from The Cat API and saved into the db

**Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`

**URL** : `/api/deleteBreeds/`

**Method** : `GET`

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Data deleted from the db

**Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`
```
