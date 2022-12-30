import requests # Http Requests
from bs4 import BeautifulSoup # Web Scraping

# Send Mail
import smtplib
# Email Body
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import datetime # System Date and Time

import re
import base64

now = datetime.datetime.now()

# email content placeholder
content = ''

# Extracting Hacker News Stories.
def extract_news(url):
    print('Extracting Hacker News Stories...')
    cnt = ''
    cnt += ('<b>HN Top Stories:</b>\n' + '<br>' + '-' *50 + '<br>')
    response = requests.get(url)
    content = response.content
    soup = BeautifulSoup(content, 'html.parser')
    for i, tag in enumerate(soup.find_all('td', attrs={'class':'title','valign':''})):
        cnt += ((str(i + 1) + ' :: ' + tag.text + "\n" + '<br>') if tag.text != 'More' else '')
    return(cnt)

# Decode message that is received through DebuggingServer.
def decode_base64(content):
    content = re.sub("b'|'", "", content)
    print(base64.b64decode(content))

cnt = extract_news('https://news.ycombinator.com/')
content += cnt
content += ('<br>------</br>')
content += ('<br><br>End of Message')

html_file = open("includes/output.html", "w")
html_file.write(content)
html_file.close()

# Set server settings & details to compose mail.
SERVER = 'localhost'
PORT = 8025
FROM = 'ttestandyriv@gmail.com'
TO = 'ttestandyriv@gmail.com'
PASS = 'ttestpassword'

# Object used to form mail.
msg = MIMEMultipart()

# Compose email header.
msg['Subject'] = 'Top News Stories [Automated Email] ' + str(now.day) + '-' + str(now.month) + '-' +str(now.year)
msg['From'] = FROM
msg['To'] = TO

msg.attach(MIMEText(content, 'html'))

print('Initiating Server...')

# Email settings.
try:
    server = smtplib.SMTP(SERVER, PORT) # Server and Port # to access
    server.set_debuglevel(0)    # Debug options
    server.ehlo()   # Optional: can be omitted
    # server.starttls() # Secure the connection
    # server.login(FROM, PASS)  # Login to email
    server.sendmail(FROM, TO, msg.as_string())  # Send mail to recipient
    print('Email Sent...')
except Exception as e:
    print(e)    # Print any error messages to stdout
finally:
    server.quit()   # Quit server whether on success or fail

