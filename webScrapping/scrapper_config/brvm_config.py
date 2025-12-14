import requests
from bs4 import BeautifulSoup
import urllib3


urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)
def config_brvm_scraper(url: str):
   response = requests.get(url, verify=False)
   soup = BeautifulSoup(response.content, "html.parser")
   return soup