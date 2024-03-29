import csv
from math import log2
import pandas

data = pandas.read_csv('C:/Local Documents/ratings_and_genres.csv')
#metadata = pandas.read_csv('C:/Local Documents/PD_Shrank_V2.csv')

print(data)

#metadata_list = metadata['avg_rating'].tolist()
#data_list = data['is_adult'].tolist()

size = len(data)
is_good = 0
is_bad = 0

Drama = 0
Comedy = 0
TalkShow = 0
Short = 0
Documentary = 0
Romance = 0
News = 0
Family = 0
RealityTV = 0
Animation = 0
Crime = 0
Action = 0
Adventure = 0
Music = 0
GameShow = 0
Adult = 0
Sport = 0
Fantasy = 0
Mystery = 0
Horror = 0
Thriller = 0
History = 0
Biography = 0
SciFi = 0
Musical = 0
War = 0
Western = 0

Drama_good = 0
Comedy_good = 0
TalkShow_good = 0
Short_good = 0
Documentary_good = 0
Romance_good = 0
News_good = 0
Family_good = 0
RealityTV_good = 0
Animation_good = 0
Crime_good = 0
Action_good = 0
Adventure_good = 0
Music_good = 0
GameShow_good = 0
Adult_good = 0
Sport_good = 0
Fantasy_good = 0
Mystery_good = 0
Horror_good = 0
Thriller_good = 0
History_good = 0
Biography_good = 0
SciFi_good = 0
Musical_good = 0
War_good = 0
Western_good = 0

Drama_bad = 0
Comedy_bad = 0
TalkShow_bad = 0
Short_bad = 0
Documentary_bad = 0
Romance_bad = 0
News_bad = 0
Family_bad = 0
RealityTV_bad = 0
Animation_bad = 0
Crime_bad = 0
Action_bad = 0
Adventure_bad = 0
Music_bad = 0
GameShow_bad = 0
Adult_bad = 0
Sport_bad = 0
Fantasy_bad = 0
Mystery_bad = 0
Horror_bad = 0
Thriller_bad = 0
History_bad = 0
Biography_bad = 0
SciFi_bad = 0
Musical_bad = 0
War_bad = 0
Western_bad = 0

x = 0
#print(len(metadata_list))

RATING_MINIMUM = 7
MINIMUM_YEAR = 2005
print('Rating Minimum = 7')
print('processing')
try:
    for index, row in data.iterrows():
        #if x-10 >= len(metadata_list):
            #break
        #else:

            if row['genres'] == 'Drama':
                Drama += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Drama_good += 1
                else:
                    Drama_bad += 1
            elif row['genres'] == 'Comedy':
                Comedy += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Comedy_good += 1
                else:
                    Comedy_bad += 1
            elif row['genres'] == 'Talk-Show':
                TalkShow += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    TalkShow_good += 1
                else:
                    TalkShow_bad += 1
            elif row['genres'] == 'Short':
                Short += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Short_good += 1
                else:
                    Short_bad += 1
            elif row['genres'] == 'Documentary':
                Documentary += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Documentary_good += 1
                else:
                    Documentary_bad += 1
            elif row['genres'] == 'Romance':
                Romance += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Romance_good += 1
                else:
                    Romance_bad += 1
            elif row['genres'] == 'News':
                News += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    News_good += 1
                else:
                    News_bad += 1
            elif row['genres'] == 'Family':
                Family += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Family_good += 1
                else:
                    Family_bad += 1
            elif row['genres'] == 'Reality-TV':
                RealityTV += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    RealityTV_good += 1
                else:
                    RealityTV_bad += 1
            elif row['genres'] == 'Animation':
                Animation += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Animation_good += 1
                else:
                    Animation_bad += 1
            elif row['genres'] == 'Crime':
                Crime += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Crime_good += 1
                else:
                    Crime_bad += 1
            elif row['genres'] == 'Action':
                Action += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Action_good += 1
                else:
                    Action_bad += 1
            elif row['genres'] == 'Adventure':
                Adventure += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Adventure_good += 1
                else:
                    Adventure_bad += 1
            elif row['genres'] == 'Music':
                Music += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Music_good += 1
                else:
                    Music_bad += 1
            elif row['genres'] == 'Game-Show':
                GameShow += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    GameShow_good += 1
                else:
                    GameShow_bad += 1
            elif row['genres'] == 'Adult':
                Adult += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Adult_good += 1
                else:
                    Adult_bad += 1
            elif row['genres'] == 'Sport':
                Sport += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Sport_good += 1
                else:
                    Sport_bad += 1
            elif row['genres'] == 'Fantasy':
                Fantasy += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Fantasy_good += 1
                else:
                    Fantasy_bad += 1
            elif row['genres'] == 'Mystery':
                Mystery += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Mystery_good += 1
                else:
                    Mystery_bad += 1
            elif row['genres'] == 'Horror':
                Horror += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Horror_good += 1
                else:
                    Horror_bad += 1
            elif row['genres'] == 'Thriller':
                Thriller += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Thriller_good += 1
                else:
                    Thriller_bad += 1
            elif row['genres'] == 'History':
                History += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    History_good += 1
                else:
                    History_bad += 1
            elif row['genres'] == 'Biography':
                Biography += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Biography_good += 1
                else:
                    Biography_bad += 1
            elif row['genres'] == 'Sci-Fi':
                SciFi += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    SciFi_good += 1
                else:
                    SciFi_bad += 1
            elif row['genres'] == 'Musical':
                Musical += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Musical_good += 1
                else:
                    Musical_bad += 1
            elif row['genres'] == 'War':
                War += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    War_good += 1
                else:
                    War_bad += 1
            elif row['genres'] == 'Western':
                Western += 1
                if row['avg_rating'] > RATING_MINIMUM:
                    Western_good += 1
                else:
                    Western_bad += 1
            if row['avg_rating'] > RATING_MINIMUM:
                is_good += 1
            else:
                is_bad += 1
            x += 1
except:
    pass



print(' ')
print('Processing Complete :)')
print(' ')

num_in_genre_list = [
            Drama ,
            Comedy,
            TalkShow,
            Short ,
            Documentary ,
            Romance ,
            News ,
            Family ,
            RealityTV ,
            Animation ,
            Crime ,
            Action ,
            Adventure,
            Music ,
            GameShow ,
            Adult,
            Sport ,
            Fantasy ,
            Mystery ,
            Horror ,
            Thriller ,
            History ,
            Biography ,
            SciFi ,
            Musical ,
            War ,
            Western
]

prob_Drama_good = Drama_good / Drama
prob_Comedy_good = Comedy_good / Comedy
prob_TalkShow_good = TalkShow_good / TalkShow
prob_Short_good = Short_good / Short
prob_Documentary_good = Documentary_good / Documentary
prob_Romance_good = Romance_good / Romance
prob_News_good = News_good / News
prob_Family_good = Family_good / Family
prob_RealityTV_good = RealityTV_good / RealityTV
prob_Animation_good = Animation_good / Animation
prob_Crime_good = Crime_good / Crime
prob_Action_good = Action_good / Action
prob_Adventure_good = Adventure_good / Adventure
prob_Music_good = Music_good / Music
prob_GameShow_good = GameShow_good / GameShow
prob_Adult_good = Adult_good / Adult
prob_Sport_good = Sport_good / Sport
prob_Fantasy_good = Fantasy_good / Fantasy
prob_Mystery_good = Mystery_good / Mystery
prob_Horror_good = Horror_good / Horror
prob_Thriller_good = Thriller_good / Thriller
prob_History_good = History_good / History
prob_Biography_good = Biography_good / Biography
prob_SciFi_good = SciFi_good / SciFi
prob_Musical_good = Musical_good / Musical
prob_War_good = War_good / War
prob_Western_good = Western_good / Western

prob_Drama_bad = Drama_bad / Drama
prob_Comedy_bad = Comedy_bad / Comedy
prob_TalkShow_bad = TalkShow_bad / TalkShow
prob_Short_bad = Short_bad / Short
prob_Documentary_bad = Documentary_bad / Documentary
prob_Romance_bad = Romance_bad / Romance
prob_News_bad = News_bad / News
prob_Family_bad = Family_bad / Family
prob_RealityTV_bad = RealityTV_bad / RealityTV
prob_Animation_bad = Animation_bad / Animation
prob_Crime_bad = Crime_bad / Crime
prob_Action_bad = Action_bad / Action
prob_Adventure_bad = Adventure_bad / Adventure
prob_Music_bad = Music_bad / Music
prob_GameShow_bad = GameShow_bad / GameShow
prob_Adult_bad = Adult_bad / Adult
prob_Sport_bad = Sport_bad / Sport
prob_Fantasy_bad = Fantasy_bad / Fantasy
prob_Mystery_bad = Mystery_bad / Mystery
prob_Horror_bad = Horror_bad / Horror
prob_Thriller_bad = Thriller_bad / Thriller
prob_History_bad = History_bad / History
prob_Biography_bad = Biography_bad / Biography
prob_SciFi_bad = SciFi_bad / SciFi
prob_Musical_bad = Musical_bad / Musical
prob_War_bad = War_bad / War
prob_Western_bad = Western_bad / Western

print("Drama w/ rating > RATING_MINIMUM")
print(prob_Drama_good)
print("Comedy w/ rating > RATING_MINIMUM")
print(prob_Comedy_good)


entropy_parent = -(( (is_good/x) * log2(is_good/x) ) + ( (is_bad/x) * log2(is_bad/x) ))

entropy_Drama = -( (Drama_good/Drama) * log2(Drama_good/Drama) ) + -( (Drama_bad/Drama) * log2(Drama_bad/Drama) )
entropy_Comedy = -( (Comedy_good/Comedy) * log2(Comedy_good/Comedy) ) + -( (Comedy_bad/Comedy) * log2(Comedy_bad/Comedy) )
entropy_TalkShow = -( (TalkShow_good/TalkShow) * log2(TalkShow_good/TalkShow) ) + -( (TalkShow_bad/TalkShow) * log2(TalkShow_bad/TalkShow) )
entropy_Short = -( (Short_good/Short) * log2(Short_good/Short) ) + -( (Short_bad/Short) * log2(Short_bad/Short) )
entropy_Documentary = -( (Documentary_good/Documentary) * log2(Documentary_good/Documentary) ) + -( (Documentary_bad/Documentary) * log2(Documentary_bad/Documentary) )
entropy_Romance = -( (Romance_good/Romance) * log2(Romance_good/Romance) ) + -( (Romance_bad/Romance) * log2(Romance_bad/Romance) )
entropy_News = -( (News_good/News) * log2(News_good/News) ) + -( (News_bad/News) * log2(News_bad/News) )
entropy_Family = -( (Family_good/Family) * log2(Family_good/Family) ) + -( (Family_bad/Family) * log2(Family_bad/Family) )
entropy_RealityTV = -( (RealityTV_good/RealityTV) * log2(RealityTV_good/RealityTV) ) + -( (RealityTV_bad/RealityTV) * log2(RealityTV_bad/RealityTV) )
entropy_Animation = -( (Animation_good/Animation) * log2(Animation_good/Animation) ) + -( (Animation_bad/Animation) * log2(Animation_bad/Animation) )
entropy_Crime = -( (Crime_good/Crime) * log2(Crime_good/Crime) ) + -( (Crime_bad/Crime) * log2(Crime_bad/Crime) )
entropy_Action = -( (Action_good/Action) * log2(Action_good/Action) ) + -( (Action_bad/Action) * log2(Action_bad/Action) )
entropy_Adventure = -( (Adventure_good/Adventure) * log2(Adventure_good/Adventure) ) + -( (Adventure_bad/Adventure) * log2(Adventure_bad/Adventure) )
entropy_Music = -( (Music_good/Music) * log2(Music_good/Music) ) + -( (Music_bad/Music) * log2(Music_bad/Music) )
entropy_GameShow = -( (GameShow_good/GameShow) * log2(GameShow_good/GameShow) ) + -( (GameShow_bad/GameShow) * log2(GameShow_bad/GameShow) )
entropy_Adult = -( (Adult_good/Adult) * log2(Adult_good/Adult) ) + -( (Adult_bad/Adult) * log2(Adult_bad/Adult) )
entropy_Sport = -( (Sport_good/Sport) * log2(Sport_good/Sport) ) + -( (Sport_bad/Sport) * log2(Sport_bad/Sport) )
entropy_Fantasy = -( (Fantasy_good/Fantasy) * log2(Fantasy_good/Fantasy) ) + -( (Fantasy_bad/Fantasy) * log2(Fantasy_bad/Fantasy) )
entropy_Mystery = -( (Mystery_good/Mystery) * log2(Mystery_good/Mystery) ) + -( (Mystery_bad/Mystery) * log2(Mystery_bad/Mystery) )
entropy_Horror = -( (Horror_good/Horror) * log2(Horror_good/Horror) ) + -( (Horror_bad/Horror) * log2(Horror_bad/Horror) )
entropy_Thriller = -( (Thriller_good/Thriller) * log2(Thriller_good/Thriller) ) + -( (Thriller_bad/Thriller) * log2(Thriller_bad/Thriller) )
entropy_History = -( (History_good/History) * log2(History_good/History) ) + -( (History_bad/History) * log2(History_bad/History) )
entropy_Biography = -( (Biography_good/Biography) * log2(Biography_good/Biography) ) + -( (Biography_bad/Biography) * log2(Biography_bad/Biography) )
entropy_SciFi = -( (SciFi_good/SciFi) * log2(SciFi_good/SciFi) ) + -( (SciFi_bad/SciFi) * log2(SciFi_bad/SciFi) )
entropy_Musical = -( (Musical_good/Musical) * log2(Musical_good/Musical) ) + -( (Musical_bad/Musical) * log2(Musical_bad/Musical) )
entropy_War = -( (War_good/War) * log2(War_good/War) ) + -( (War_bad/War) * log2(War_bad/War) )
entropy_Western = -( (Western_good/Western) * log2(Western_good/Western) ) + -( (Western_bad/Western) * log2(Western_bad/Western) )

entropy_list = [entropy_Drama,
                entropy_Comedy,
                entropy_TalkShow,
                entropy_Short ,
                entropy_Documentary ,
                entropy_Romance ,
                entropy_News ,
                entropy_Family ,
                entropy_RealityTV ,
                entropy_Animation ,
                entropy_Crime ,
                entropy_Action ,
                entropy_Adventure ,
                entropy_Music,
                entropy_GameShow ,
                entropy_Adult ,
                entropy_Sport ,
                entropy_Fantasy ,
                entropy_Mystery,
                entropy_Horror ,
                entropy_Thriller ,
                entropy_History,
                entropy_Biography ,
                entropy_SciFi ,
                entropy_Musical,
                entropy_War,
                entropy_Western
]

print('Entropy of Parent')
print(entropy_parent)
print('Entropies')
for i in entropy_list:
    print(i)

entropy_tree_2 = 0
y = 0
for i in entropy_list:
    entropy_tree_2 = entropy_tree_2 + ( num_in_genre_list[y]/x * i)
    y += 1


print("Weighted Entropy")
print(entropy_tree_2)

infogain = entropy_parent - entropy_tree_2
print("InfoGain")
print(infogain)
