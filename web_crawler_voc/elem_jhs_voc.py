from bs4 import BeautifulSoup
import requests
import pandas as pd

URL = "http://www.taiwantestcentral.com/wordlist/BCTWordList.aspx?CategoryID=12"
  
HEADERS = ({'User-Agent':
            'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 \
            (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36',\
            'Accept-Language': 'en-US, en;q=0.5'})
  
webpage = requests.get(URL, headers=HEADERS)
soup = BeautifulSoup(webpage.content, "html.parser")

voc = [i.get_text() for i in soup.find_all(attrs={'class': 'nowrap'})]
chinese = [i.get_text() for i in soup.find_all(attrs={'class': 'Chinese'})]

df = pd.DataFrame((zip(voc, chinese)), columns = ['vocab', 'chinese'])
df['chinese'] = df['chinese'].str.split('; ')
df = df.explode('chinese')
df[['pos', 'def']] = df['chinese'].str.split('] ', 1, expand=True)
df['pos'] = df['pos'].str.replace('[', '', regex=True)
df.to_excel('elem_jhs_voc.xlsx', encoding='utf-8-sig', columns=['vocab', 'pos', 'def'])

tmp = pd.read_excel('elem_jhs_voc.xlsx', index_col=0)
tmp
