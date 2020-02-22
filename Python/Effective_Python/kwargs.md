# \*\*kwargs 예제

- 용도 :   
 - \*\*kwargs와 *args는 어떠한 값을 넣을 지 모를 때 사용.
 - \*args는 값을 넣으면 함수에 변수가 튜플형태로 입력.
 - \*\*kwargs는 딕셔너리 형태로 입력.

```
import requests

class SampleApi:

def __init__(self, url, a, b, c):
  self.url = url
  self.a = a
  self.b = b
  self.c = c



def getUrl(self):
  url = self.url + "a=" + self.a + "&b=" + self.b + "&c=" +self.c
  response = requests.get(url)
  return response

sample = SampleApi( "http://www.sampleapi.com/api?", "1", "2","3" )

a = sample.getUrl()
```

```


import requests

class SampleApi:
  def __init__(self, url, a):
  self.url = url
  self.a = a

def getUrl(self, **kwargs):
  optionUrl = ""
    if kwargs.has_key("b"):
      optionUrl += "&b=" + kwargs["b"]

    if kwargs.has_key("c"):
      optionUrl += "&c=" + kwargs["c"]

url = self.url + "a=" + self.a + optionUrl

response = requests.get(url)

return response
```
