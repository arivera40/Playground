import os
import re

def replace_word(path, find, replace):
    with open(path) as fp:
        lines = fp.readlines()

        for idx, line in enumerate(lines):
            if len(line) <= 0:
                continue
            if find in line:
                lines[idx] = re.sub(find, replace, line)

        with open(path, "w") as fp:
            for line in lines:
                fp.write(line)

path = os.getcwd() + "\\includes\\tech_report.txt"
find = input("Please enter a word to find: ")
replace = input("Please enter a word to replace with: ")
replace_word(path, find, replace)

