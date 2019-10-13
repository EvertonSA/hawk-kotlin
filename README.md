# hawk-kotlin

## Description
This repo suposed to be the Twitter Consumer but due to complications with the ammount of time wasted waiting for the TwitterDev to confirm my app I had to improvise and transform this application in a The Cat API consumer.

## Objects

### Breeds

#### Breed object definition
```json
{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}
```

#### Breed endpoints

---

**URL** : `/api/breeds/`

**Method** : `GET`

**Sucess Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`

---

**URL** : `/api/fetchBreeds/`

**Method** : `GET`

**Success Condition** : Data fetched from The Cat API and saved into the db

**Success Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`

---

**URL** : `/api/deleteBreeds/`

**Method** : `GET`

**Success Condition** : Data deleted from the db

**Success Code** : `200 OK`

**Content** : `{[{"id":"abys","name":"Abyssinian","origin":"Egypt","intelligence":5}]}`
```
