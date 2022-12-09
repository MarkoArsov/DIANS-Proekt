# -*- coding: utf-8 -*-
import pandas as pd
import requests 
import random

from bs4 import BeautifulSoup

from datetime import date

from IPython.display import HTML

import warnings

requests.packages.urllib3.disable_warnings()
warnings.filterwarnings("ignore")

url = 'https://www.eventbrite.com/d/united-states/events--next-month/?bbox=8.827463652730671%2C29.077360248574635%2C41.47883084023067%2C58.00930111514717&page='
d = []
it = {}
it['Title'] = "TitleTEST"
it['Date'] = "DateTEST"
it['Link to event'] = "LinkTEST"
it['Location'] = "LocationTEST"
d.append(it)
dataFrame = pd.DataFrame(d)
print(dataFrame)

for i in range(1, 51):
  response = requests.get(url + str(i))
  raw_html = response.text
  html = BeautifulSoup(raw_html, "html.parser")

  titlesRaw = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a > h3 > div > div.eds-is-hidden-accessible") 
  titles = []
  for i in range(0, len(titlesRaw)):
    titles.append(titlesRaw[i].text)

  dateRaw = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > div")
  date = []
  for i in range(0, len(dateRaw)):
    date.append(dateRaw[i].text)

  linksRaw = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a")
  links = []
  for i in range(0, len(linksRaw)):
    links.append(linksRaw[i].get('href'))

  locationsRaw = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__sub-content > div:nth-of-type(1) > div")
  locations = []
  for i in locationsRaw:
    locations.append(i.text)

  data = []
  for i in range(0, len(titles)):
    item = {}
    item['Title'] = titles[i].replace(",", "")
    item['Date'] = date[i].replace(",", "")
    item['Link to event'] = links[i].replace(",", "")
    item['Location'] = locations[i].replace(",", "")
    data.append(item)

  df = pd.DataFrame(data)
  dataFrame = pd.concat([dataFrame, df], ignore_index=True)

dataFrame

from google.colab import  drive

drive.mount('/drive')

dataFrame.to_csv('/drive/My Drive/Colab Notebooks/events_scraped.csv')



