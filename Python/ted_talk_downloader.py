import requests
from bs4 import BeautifulSoup
import re
import sys

# Exception Handling
if len(sys.argv) > 1:
    url = sys.argv[1]
else:
    sys.exit("Error: Please enter the TED Talk URL")

# Obtains url content from HTTP request.
r = requests.get(url)

print("Download about to start")

# Scrapes html content from webpage using lxml parser.
soup = BeautifulSoup(r.content, features = "lxml")

# Loops through all script elements and searches user regex targeting specific element containing mp4.
for val in soup.findAll("script"):
    if (re.search("__NEXT_DATA__", str(val))) is not None:
        result = str(val)

# Obtains mp4 using regex search.
mp4_url = re.search("(?P<url>https?://[^\s]+)(mp4)", result).group("url") + "mp4"

# Names mp4 file the same file name its url is pointing to.
file_name = mp4_url.split("/")[len(mp4_url.split("/")) - 1].split('?')[0]

# Obtains url content from HTTP request
r = requests.get(mp4_url)

# Writes mp4 content into a file inside includes directory.
with open("includes/" + file_name, 'wb') as f:
    f.write(r.content)

    print("Download Process finished.")