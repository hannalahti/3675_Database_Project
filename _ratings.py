import pandas

data = pandas.read_csv('C:/Local Documents/Belongs_Shrank.csv')
metadata = pandas.read_csv('C:/Local Documents/PD_Shrank_V2.csv')

x = metadata.merge(data, left_on='movie_id', right_on='movie_id')

print(x)
x.to_csv("ratings_and_genres.csv")

