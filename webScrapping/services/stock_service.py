from scrapper_config.brvm_config import config_brvm_scraper

def get_brvm_actions():
    url = "https://www.brvm.org/fr/cours-actions/0"
    soup = config_brvm_scraper(url)

    tables = soup.find_all("table")
    if not tables:
        return None

    result = {}

    for row in tables[3].find_all("tr"):
        cells = row.find_all("td")

      
        if len(cells) >= 7:
            stock_symbol = cells[0].text.strip()   
            stock_name   = cells[1].text.strip()   
            volume       = cells[2].text.strip()  
            price_str    = cells[5].text.strip()   
            variation    = cells[6].text.strip()    

           
            try:
                price = float(price_str.replace(" ", "").replace(",", "."))
            except ValueError:
                price = None

            try:
                volume = int(volume.replace(" ", ""))
            except ValueError:
                volume = None

            result[stock_symbol] = {
                "symbol": stock_name,
                "price": price,
                "variation": variation,
                "volume": volume
            }

    return result
