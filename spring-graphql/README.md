

# 请求
```json
query{
  queryBook{
    id
    name
    authors {
      id
      name
    }
  }
}
```

# 返回
```json
{
  "data": {
    "queryBook": [
      {
        "id": "1",
        "name": "book1",
        "authors": [
          {
            "id": "1",
            "name": "auth 1"
          },
          {
            "id": "2",
            "name": "auth 2"
          }
        ]
      },
      {
        "id": "2",
        "name": "book2",
        "authors": [
          {
            "id": "1",
            "name": "auth 1"
          },
          {
            "id": "2",
            "name": "auth 2"
          }
        ]
      }
    ]
  }
}
```

