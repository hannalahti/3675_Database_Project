import csv
from math import log2
import pandas

data = pandas.read_csv('C:/Local Documents/Movies_Shrank_V2.csv')
metadata = pandas.read_csv('C:/Local Documents/PD_Shrank_V2.csv')

print(data)

metadata_list = metadata['avg_rating'].tolist()
data_list = data['is_adult'].tolist()

size = len(data)
is_good = 0
is_bad = 0

young_movie = 0
old_movie = 0

young_movie_good = 0
old_movie_good = 0
young_movie_bad = 0
old_movie_bad = 0

x = 0
print(len(metadata_list))

RATING_MINIMUM = 7
MINIMUM_YEAR = 2005
print('Rating Minimum = 7')
print('processing')
try:
    for index, row in metadata.iterrows():
        if x-10 >= len(metadata_list):
            break
        else:

            if row['year'] > 2005:
                young_movie += 1
                if metadata_list[x] > RATING_MINIMUM:
                    young_movie_good += 1
                else:
                    young_movie_bad += 1
            else:
                old_movie += 1
                if metadata_list[x] > RATING_MINIMUM:
                    old_movie_good += 1
                else:
                    old_movie_bad += 1
            if metadata_list[x] > RATING_MINIMUM:
                is_good += 1
            else:
                is_bad += 1
            x += 1
except:
    pass

print(young_movie)
print(' ')
print('Processing Complete :)')
print(' ')

prob_young_movie_good = young_movie_good / young_movie
prob_young_movie_bad = young_movie_bad / young_movie

prob_old_movie_good = old_movie_good / old_movie
prob_old_movie_bad = old_movie_bad / old_movie



print("old movie w/ rating > RATING_MINIMUM")
print(prob_old_movie_good)
print("young movie w/ rating > RATING_MINIMUM")
print(prob_young_movie_good)


entropy_parent = -(( (is_good/x) * log2(is_good/x) ) + ( (is_bad/x) * log2(is_bad/x) ))

entropy_young_movie = -( (young_movie_good/young_movie) * log2(young_movie_good/old_movie) ) + -( (young_movie_bad/young_movie) * log2(young_movie_bad/young_movie) )
entropy_old_movie = -( (old_movie_good/old_movie) * log2(old_movie_good/old_movie) ) + -( (old_movie_bad/old_movie) * log2(old_movie_bad/old_movie) )


print('Entropy of Parent')
print(entropy_parent)
print('Entropy of Young Movie')
print(entropy_young_movie)
print('Entropy of Old Movie')
print(entropy_old_movie)

entropy_tree_2 = ((young_movie/x * entropy_young_movie) +
                  (old_movie/x * entropy_old_movie)  )

print("Weighted Entropy")
print(entropy_tree_2)

infogain = entropy_parent - entropy_tree_2
print("InfoGain")
print(infogain)
