from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup as Soup
import pandas as pd
from random import randint
import string
from time import sleep

driver = webdriver.Chrome(ChromeDriverManager().install())
soup = Soup(driver.page_source,"lxml")
driver.get('https://www.geptkids.org.tw/Home/wordlist')

def get_df(char):
    button = driver.find_elements(by=By.XPATH, value=".//a[text()='{}']".format(char))[0]
    button.click()
    df = pd.read_html(driver.page_source)[0]
    return df

df = get_df('A')
for i in list(string.ascii_uppercase)[1:]:
    sleep(randint(1,10))
    df = df.append(get_df(i), ignore_index=True)

df = df.drop('播放語音', axis=1)
df = df.rename(columns={'英文': 'vocab', '中文': 'def', '詞性':'pos'})
df = df[df['vocab']!='無此字母開頭單字']
df.to_excel('elem_voc.xlsx', encoding='utf-8-sig', columns=['vocab', 'pos', 'def'])

tmp = pd.read_excel('elem_voc.xlsx', index_col=0)
tmp
