import requests
from bs4 import BeautifulSoup
from scrapper_config import brvm_config






def get_brvm_indices():
    url = "https://www.brvm.org/fr/cours-indices"
    soup=brvm_config.config_brvm_scraper(url)

    tables = soup.find_all("table")
    if not tables:
        return None

    result = {}

    for row in tables[0].find_all("tr"):
        cells = row.find_all("td")

        
        if len(cells) >= 3:
            index_name = cells[0].text.strip()    
            index_value = cells[1].text.strip()   
            variation = cells[2].text.strip()
            removeSpaceForIndexValue=index_value.replace(' ', '.') 

          
            result[index_name] = {
                "value": float(removeSpaceForIndexValue),
                "variation": variation
            }

    return result