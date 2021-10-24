import os


def load(load_type):
    if load_type == "stock":
        os.system("python stocks.py")
    elif load_type == "others":
        os.system("python others.py")
    elif load_type == "all":
        os.system("python stocks.py")
        os.system("python others.py")


load(load_type="all")
print("python crawler running ... ")
