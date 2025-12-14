from fastapi import FastAPI
import requests

from services.brvm_service import get_brvm_indices
from services.stock_service import get_brvm_actions

app=FastAPI()






@app.get("/api/market/indices")
def  get_indices():
   
    data = get_brvm_indices()

    if not data:
        return {"error": "Impossible de récupérer les indices BRVM"}

    return data

@app.get("/api/market/stocks")
def get_stocks():
    data=get_brvm_actions()
    if not data:
         return {"error":"Impossible de récupérer les actions BRVM"}
    return data


