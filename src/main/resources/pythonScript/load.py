import json
import os
import re

import execjs
import pymysql
import requests
from lxml import etree


def follow(expert_id):
    url = f"http://moni.10jqka.com.cn/mncg/index/addzzgs/{expert_id}"
    requests.post(url, headers=headers)


def get_expert_id_list():
    expert_id_list = []
    trs = main_page.xpath('//*[@id="myTab_Content0"]/table[2]/tr')

    for tr in trs:
        expert_id_list.append(tr.xpath("./td[2]/p/a/@href")[0].split("/")[-1])

    return expert_id_list


def get_expert_page(expert_id):
    url = f"http://moni.10jqka.com.cn/{expert_id}"
    resp = requests.get(url, headers=headers)
    expert_page = etree.HTML(resp.text)

    return expert_page


def get_expert_info_list(expert_id, expert_page):
    one_expert_info_list = []
    name = expert_page.xpath('//*[@id="mian-left"]/div[1]/div[1]/ul/li[1]/text()')[0]
    level = expert_page.xpath('//*[@id="mian-left"]/div[1]/table/tr[1]/td[2]/p/text()')[0]
    storage = expert_page.xpath('//*[@id="mian-left"]/div[1]/table/tr[2]/td[2]/p/text()')[0]
    try:
        storage = float(storage.replace("%", ""))
    except ValueError:
        storage = 0.0
    success_rate = expert_page.xpath('//*[@id="mian-left"]/div[1]/table/tr[3]/td[2]/p/text()')[0]
    success_rate = float(success_rate.replace("%", ""))
    follower = 0
    total_profit_ratio = expert_page.xpath('//*[@id="myTab_Content0"]/div[4]/table[1]/tr[2]/td[1]/span/text()')[0]
    total_profit_ratio = float(total_profit_ratio.replace("%", ""))
    one_expert_info_list.append(expert_id)
    one_expert_info_list.append(name)
    one_expert_info_list.append(level)
    one_expert_info_list.append(storage)
    one_expert_info_list.append(success_rate)
    one_expert_info_list.append(follower)
    one_expert_info_list.append(total_profit_ratio)

    return one_expert_info_list


def get_one_hold_tr_list(expert_id, tr):
    one_hold_tr_list = []
    stock_code = tr.xpath('./td[1]/span/a/text()')[0]
    amount = int(tr.xpath('./td[3]/span/text()')[0])
    open_time = tr.xpath('./td[4]/span/text()')[0]
    avg_cost = float(tr.xpath('./td[5]/span/text()')[0])

    one_hold_tr_list.append(expert_id)
    one_hold_tr_list.append(stock_code)
    one_hold_tr_list.append(amount)
    one_hold_tr_list.append(avg_cost)
    one_hold_tr_list.append(open_time)

    return one_hold_tr_list


def get_expert_hold_list(expert_id, expert_page):
    one_expert_hold_list = []
    trs = expert_page.xpath('//*[@id="ccqk_tbl"]/tbody/tr')
    if len(trs) == 0:
        return None

    for tr in trs:
        one_expert_hold_list.append(get_one_hold_tr_list(expert_id, tr))

    return one_expert_hold_list


def get_one_deal_tr_list(expert_id, tr):
    one_deal_tr_list = []

    stock_code = tr.xpath('./td[2]/span/a/text()')[0]
    stock_code = re.match(r".*?(?P<code>\d+)", stock_code, re.S)
    stock_code = stock_code.group("code")
    method = tr.xpath('./td[4]/span/text()')[0]
    amount = int(tr.xpath('./td[5]/span/text()')[0])
    price = float(tr.xpath('./td[6]/span/text()')[0])
    trade_time = tr.xpath('./td[1]/span/text()')[0]

    one_deal_tr_list.append(expert_id)
    one_deal_tr_list.append(stock_code)
    one_deal_tr_list.append(method)
    one_deal_tr_list.append(amount)
    one_deal_tr_list.append(price)
    one_deal_tr_list.append(trade_time)

    return one_deal_tr_list


def get_expert_deal_list(expert_id, expert_page):
    expert_deal_list = []
    trs = expert_page.xpath('//*[@id="jyjl_tbl"]/tbody/tr')
    if len(trs) == 0:
        return None

    for tr in trs:
        expert_deal_list.append(get_one_deal_tr_list(expert_id, tr))

    return expert_deal_list


def get_all_data():
    flag = True
    while flag:
        try:
            n = 1
            for expert_id in expert_id_list:
                expert_page = get_expert_page(expert_id)
                follow_btn = expert_page.xpath("//div[@title='点击追踪可以看到更多信息哦~']")
                if len(follow_btn) == 1:
                    follow(expert_id)
                    expert_page = get_expert_page(expert_id)

                expert_info_list.append(get_expert_info_list(expert_id, expert_page))
                try:
                    expert_hold_list.extend(get_expert_hold_list(expert_id, expert_page))
                except TypeError:
                    pass
                try:
                    expert_deal_list.extend(get_expert_deal_list(expert_id, expert_page))
                except TypeError:
                    pass

                print(expert_id, n, "打印完成")
                n += 1

            flag = False
        except Exception as e:
            print(f"出现错误{e}，重新尝试")
            expert_info_list.clear()
            expert_hold_list.clear()
            expert_deal_list.clear()


def save_to_mysql():
    db = pymysql.connect(host="localhost", user="root", password="root", database="tonghuashun", charset="utf8")
    cur = db.cursor()
    cur.execute('delete from deal;')

    cur.execute('delete from hold;')

    cur.execute('delete from expert;')
    db.commit()
    for item in expert_info_list:
        sql = f'''
            insert into expert (expert_id, expert_name, level, storage, success_rate, follower_num, total_profit_ratio)
            values ("{item[0]}", "{item[1]}", "{item[2]}", {item[3]}, {item[4]}, {item[5]},{item[6]});
        '''
        try:
            cur.execute(sql)
            db.commit()
        except pymysql.err.IntegrityError:
            pass

    for item in expert_deal_list:
        sql = f'''
            insert into deal (expert_id, code, method, amount, price, trade_time)
            values ("{item[0]}", "{item[1]}", "{item[2]}", {item[3]}, {item[4]}, "{item[5]}")
        '''
        try:
            cur.execute(sql)
            db.commit()
        except pymysql.err.IntegrityError:
            # 此时stock中不存在此code
            # stockpage_save_to_stock(item[1], db, cur)
            # cur.execute(sql)
            # db.commit()
            pass

    for item in expert_hold_list:
        sql = f'''
            insert into hold (expert_id, code, amount, avg_cost, open_time)
            values ("{item[0]}", "{item[1]}", {item[2]}, {item[3]}, "{item[4]}")
        '''
        cur.execute(sql)
        db.commit()

    cur.close()
    db.close()


def stockpage_save_to_stock(code, db, cur):
    url = f"http://qd.10jqka.com.cn/quote.php?cate=real&type=stock&return=json&callback=showStockData&code={code}"
    headers["Cookie"] = f"v={context.call('v')}"
    resp = requests.get(url, headers=headers)
    print(resp.text)
    headers["Cookie"] = previous_cookie
    str = resp.text.replace("showStockData(", "").replace(")", "")
    jsn = json.loads(str)

    print(jsn, type(jsn))
    name = jsn["info"][code]["name"]
    price = float(jsn["data"][code]["10"])
    fluctuation = float(jsn["data"][code]["199112"])

    sql = f'''
        insert into stock(code, stock_name, price, fluctuation)
        values("{code}", "{name}", {price}, {fluctuation})
    '''
    cur.execute(sql)
    db.commit()


if __name__ == '__main__':
    os.system("python stocks.py")
    with open('aes.min.js', 'r') as f:
        jscontent = f.read()
    context = execjs.compile(jscontent)
    main_url = "http://moni.10jqka.com.cn/paihang.shtml"
    previous_cookie = "spversion=20130314; __utmz=68909069.1631319620.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); user=MDrT4MP6z806Ok5vbmU6NTAwOjYwOTAxNDM3Mzo3LDExMTExMTExMTExLDQwOzQ0LDExLDQwOzYsMSw0MDs1LDEsNDA7MSwxMDEsNDA7MiwxLDQwOzMsMSw0MDs1LDEsNDA7OCwwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMSw0MDsxMDIsMSw0MDo6Ojo1OTkwMTQzNzM6MTYzMTgwNzE3NTo6OjE2MzEyODkzNjA6ODY0MDA6MDoxYmNiZTIxODM4MTAwYzY1M2MwZDczMDYxYTUyNGFhZTQ6ZGVmYXVsdF80OjE%3D; userid=599014373; u_name=%D3%E0%C3%FA%CF%CD; escapename=%25u4f59%25u94ed%25u8d24; ticket=2fb93bc521d7b6e5dea6bd777544c202; user_status=0; utk=ed94b6220ffcd656ba706b52effc1f25; __utma=156575163.1364412102.1631867188.1631867188.1631867188.1; __utmz=156575163.1631867188.1.1.utmcsr=stockpage.10jqka.com.cn|utmccn=(referral)|utmcmd=referral|utmcct=/; Hm_lvt_a9190969a435c4c490361fdf65267856=1631807146,1631867227,1631872760,1631881431; Hm_lvt_78c58f01938e4d85eaf619eae71b4ed1=1631807146,1631867210,1631872760,1631881431; __utma=68909069.925067846.1631319620.1631872760.1631881432.8; __utmc=68909069; __utmt=1; historystock=601728%7C*%7C688001%7C*%7C605033%7C*%7C600393%7C*%7C601877; v=A0RgvbLA-XNdWU0_hN5gtl-tFck1XWjCKoH8C17l0I_Sier_hm04V3qRzJCt; Hm_lpvt_78c58f01938e4d85eaf619eae71b4ed1=1631881871; Hm_lpvt_a9190969a435c4c490361fdf65267856=1631881871; __utmb=68909069.3.10.1631881432"
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36",
        "Cookie": previous_cookie
    }
    resp = requests.get(main_url, headers=headers)
    main_page = etree.HTML(resp.text)
    expert_id_list = get_expert_id_list()
    print(expert_id_list)

    expert_info_list = []
    expert_hold_list = []
    expert_deal_list = []

    get_all_data()
    save_to_mysql()
    print(expert_info_list, expert_hold_list, expert_deal_list)
