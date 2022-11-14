import pandas as pd
import requests 
import random

from bs4 import BeautifulSoup

from datetime import date

from IPython.display import HTML

import warnings

requests.packages.urllib3.disable_warnings()
warnings.filterwarnings("ignore")

url = 'https://www.eventbrite.com/d/online/all-events/?page=1'
response = requests.get(url)

raw_html = response.text
html = BeautifulSoup(raw_html, "html.parser")

titlesRaw = html.select(" div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a > h3 > div > div.eds-is-hidden-accessible")

titles = []
for i in range(0, len(titlesRaw)):
    titles.append(titlesRaw[i].text)


dateRaw = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > div")
date = []
for i in range(0, len(dateRaw)):
    date.append(dateRaw[i].text)

linksRaw = html.select(" div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a")
links = []
for i in range(0, len(linksRaw)):
    links.append(linksRaw[i].get('href'))

data = []

for i in range(0, len(titles)):
  item = {}
  item['Title'] = titles[i].replace(",", "")
  item['Date'] = date[i].replace(",", "")
  item['Link to event'] = links[i].replace(",", "")
  data.append(item)

df = pd.DataFrame(data)

url = 'https://www.eventbrite.com/d/online/all-events/?page='

for i in range(2, 51):
  response = requests.get(url + str(i))
  raw_html = response.text
  html = BeautifulSoup(raw_html, "html.parser")

  titlesRaw1 = html.select(" div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a > h3 > div > div.eds-is-hidden-accessible") 
  titles1 = []
  for i in range(0, len(titlesRaw1)):
    titles1.append(titlesRaw1[i].text)

  dateRaw1 = html.select("div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > div")
  date1 = []
  for i in range(0, len(dateRaw1)):
    date1.append(dateRaw1[i].text)

  linksRaw1 = html.select(" div > div > div.search-event-card-rectangle-image > div > div > div > article > div.eds-event-card-content__content-container.eds-l-pad-right-4 > div > div > div.eds-event-card-content__primary-content > a")
  links1 = []
  for i in range(0, len(linksRaw1)):
    links1.append(linksRaw1[i].get('href'))

  data1 = []
  for i in range(0, len(titles1)):
    item1 = {}
    item1['Title'] = titles1[i].replace(",", "")
    item1['Date'] = date1[i].replace(",", "")
    item1['Link to event'] = links1[i].replace(",", "")
    data1.append(item1)

  df1 = pd.DataFrame(data1)
  df = pd.concat([df, df1], ignore_index=True)

from google.colab import  drive
drive.mount('/drive')
df.to_csv('/drive/My Drive/Colab Notebooks/online_events_scraped.csv')
