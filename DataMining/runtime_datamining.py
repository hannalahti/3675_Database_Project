import csv
from math import log2
import pandas

data = pandas.read_csv('C:/Local Documents/Movies_Shrank_V2.csv')
metadata = pandas.read_csv('C:/Local Documents/PD_Shrank_V2.csv')

print(data)

metadata_list = metadata['avg_rating'].tolist()
data_list = data['is_adult'].tolist()

size = len(data)

isAdult = []
isNotAdult = []

loRuntime = []
hiRuntime = []

adult_has_hi_rating = 0
adult_has_lo_rating = 0
kid_has_hi_rating = 0
kid_has_lo_rating = 0

loruntime_has_hi_rating = 0
loruntime_has_lo_rating = 0
hiruntime_has_hi_rating = 0
hiruntime_has_lo_rating = 0

is_good = 0
is_bad = 0

x = 0
print(len(metadata_list))

RATING_MINIMUM = 7
RUNTIME_MINIMUM = 25

print('Rating Minimum = 7')
print('processing')
try:
    for index, row in data.iterrows():
        if x-10 >= len(metadata_list):
            break
        else:

            if int(row['run_time']) > RUNTIME_MINIMUM:
                hiRuntime.append(row)
                if metadata_list[x] > RATING_MINIMUM:
                    hiruntime_has_hi_rating += 1
                else:
                    hiruntime_has_lo_rating += 1
            else:
                loRuntime.append(row)
                if metadata_list[x] > RATING_MINIMUM:
                    loruntime_has_hi_rating += 1
                else:
                    loruntime_has_lo_rating += 1

            if metadata_list[x] > RATING_MINIMUM:
                is_good += 1
            else:
                is_bad += 1
            x += 1
except:
    pass

print(' ')
print('Processing Complete :)')
print(' ')

prob_lorun_hi = loruntime_has_hi_rating / len(loRuntime)
prob_lorun_lo = loruntime_has_lo_rating / len(loRuntime)
prob_hirun_hi = hiruntime_has_hi_rating / len(hiRuntime)
prob_hirun_lo = hiruntime_has_lo_rating / len(hiRuntime)

entropy_parent = -(( (is_good/x) * log2(is_good/x) ) + ( (is_bad/x) * log2(is_bad/x) ))

entropy_lorun = -1 * ( ( prob_lorun_lo * log2(prob_lorun_lo) ) + ( prob_lorun_hi   * log2(prob_lorun_hi) )   )
entropy_hirun = -1 * ( ( prob_hirun_lo * log2(prob_hirun_lo) ) + ( prob_hirun_hi   * log2(prob_hirun_hi) )   )

print("Lo Runtime w/ rating > RATING_MINIMUM")
print(prob_lorun_hi)
print("Lo Runtime w/ rating < RATING_MINIMUM")
print(prob_lorun_lo)
print("Hi Runtime w/ rating > RATING_MINIMUM")
print(prob_hirun_hi)
print("Hi Runtime w/ rating < RATING_MINIMUM")
print(prob_hirun_lo)

print('Entropy of Parent')
print(entropy_parent)
print('Entropy of Hi Runtime')
print(entropy_hirun)
print('Entropy of Lo Runtime')
print(entropy_lorun)

entropy_tree_2 = (( len(loRuntime)/x * entropy_lorun) +
                  ( len(hiRuntime)/x * entropy_hirun)  )

print("Weighted Entropy")
print(entropy_tree_2)

infogain = entropy_parent - entropy_tree_2
print("InfoGain")
print(infogain)