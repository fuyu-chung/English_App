from bs4 import BeautifulSoup
import requests
import pandas as pd
import string
from random import randint
from time import sleep

def get_df(char):
    URL = "http://www.taiwantestcentral.com/wordlist/WordListByName.aspx?MainCategoryID=25&Letter={}".format(char)
    
    HEADERS = ({'User-Agent':
                'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 \
                (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36',\
                'Accept-Language': 'en-US, en;q=0.5'})
    
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "html.parser")

    table = soup.find('table', {'class': 'WordList fg_highlight'})
    ###
    columns = [th.text.replace('\n', '') for th in table.find('tr').find_all('th')]

    trs = table.find_all('tr')[1:]
    rows = list()
    for tr in trs:
        rows.append([td.text.replace('\n', '').replace('\xa0', '') for td in tr.find_all('td')])
    ###
    df = pd.DataFrame(data=rows, columns=columns)
    df = df.drop('考題', axis=1)
    df = df.rename(columns={'級別': 'classification', '字詞': 'vocab', '中文釋義':'chinese'})
    return df

df = get_df('A')
for i in list(string.ascii_uppercase)[1:]:
    sleep(randint(1,10))
    df = df.append(get_df(i), ignore_index=True)

df = df.drop('', axis=1)
df['chinese'] = df['chinese'].str.split('; ')
df = df.explode('chinese')
df[['pos', 'def']] = df['chinese'].str.split('] ', 1, expand=True)
df['pos'] = df['pos'].str.replace('[', '', regex=True)
df.to_excel('hs_voc.xlsx', encoding='utf-8-sig', columns=['classification', 'vocab', 'pos', 'def'])

tmp = pd.read_excel('hs_voc.xlsx', index_col=0)
tmp

