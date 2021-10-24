import execjs
import pymysql
import requests
from lxml import etree


def download_all_pages_data():
    tasks = []

    page_no = 1
    flag = True
    while flag:
        flag = download_one_page_data(page_no)
        page_no += 1


def download_one_page_data(page_no):
    child_list = []
    url = f"https://q.10jqka.com.cn/index/index/board/all/field/zdf/order/desc/page/{page_no}/ajax/1/"
    headers["Cookie"] = f"v={context.call('v')}"
    child_resp = requests.get(url, headers=headers)

    child_page = etree.HTML(child_resp.text)
    trs = child_page.xpath('/html/body/table/tbody/tr')
    if len(trs) == 0:
        print(f"第{page_no}页打印失败")
        return False

    for tr in trs:
        all_pages_data.append(get_one_tr_data(tr))

    print(f"第{page_no}页打印完成")
    return True


def get_one_tr_data(tr):
    child_list = []
    stock_code = tr.xpath('./td[2]/a/text()')[0]
    stock_name = tr.xpath('./td[3]/a/text()')[0]
    try:
        current_price = float(tr.xpath('./td[4]/text()')[0])
    except ValueError:
        current_price = None

    try:
        fluctuation_range = float(tr.xpath('./td[5]/text()')[0])
    except ValueError:
        fluctuation_range = None

    child_list.append(stock_code)
    child_list.append(stock_name)
    child_list.append(current_price)
    child_list.append(fluctuation_range)

    return child_list


def save_to_mysql():
    db = pymysql.connect(host="127.0.0.1", user="admin", password="Qq156183@", database="climbox", charset="utf8")
    cur = db.cursor()
    cur.execute('delete from deal;')
    cur.execute('delete from stock;')
    db.commit()
    for item in all_pages_data:
        if item[2] is None:
            item[2] = "null"
        if item[3] is None:
            item[3] = "null"

        sql = f'''
            insert into stock(code, stock_name, price, fluctuation)
            values("{item[0]}", "{item[1]}", {item[2]}, {item[3]});
        '''
        cur.execute(sql)
        db.commit()
    cur.close()
    db.close()


if __name__ == '__main__':
    with open('aes.min.js', 'r') as f:
        jscontent = f.read()
    context = execjs.compile(jscontent)

    all_pages_data = []
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36",
        "Cookie": ""
    }

    download_all_pages_data()
    save_to_mysql()
    print(all_pages_data)
