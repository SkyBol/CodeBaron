import requests
from bs4 import BeautifulSoup
import json

def scrape_flatfox(city="Zürich"):
    url = f"https://flatfox.ch/api/v1/pin/?max_count=2000"
    headers = {"User-Agent": "Mozilla/5.0"}

    response = requests.get(url, headers=headers)
    print(response.json())

    with open('data.json', 'w') as f:
        json.dump(response.json(), f)
