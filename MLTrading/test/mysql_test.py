import mysql.connector

conn = mysql.connector.connect(user='mltdev', password='asdfasdf', host='localhost', database='mltdb')

cursor = conn.cursor()

query = "show tables"

cursor.execute(query)

for row in cursor:
    print(row)

cursor.close()
conn.close()
