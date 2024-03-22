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

movie = 0
short = 0
tvEpisode = 0
tvMovie = 0
tvSeries = 0
tvShort = 0
tvSpecial = 0
video = 0
videoGame = 0

movie_good = 0
short_good = 0
tvEpisode_good = 0
tvMovie_good = 0
tvSeries_good = 0
tvShort_good = 0
tvSpecial_good = 0
video_good = 0
videoGame_good = 0

movie_bad = 0
short_bad = 0
tvEpisode_bad = 0
tvMovie_bad = 0
tvSeries_bad = 0
tvShort_bad = 0
tvSpecial_bad = 0
video_bad = 0
videoGame_bad = 0

x = 0
print(len(metadata_list))

RATING_MINIMUM = 7
print('Rating Minimum = 7')
print('processing')
try:
    for index, row in data.iterrows():
        if x-10 >= len(metadata_list):
            break
        else:

            if row['format'] == 'movie':
                movie += 1
                if metadata_list[x] > RATING_MINIMUM:
                    movie_good += 1
                else:
                    movie_bad += 1
            elif row['format'] == 'short':
                short += 1
                if metadata_list[x] > RATING_MINIMUM:
                    short_good += 1
                else:
                    short_bad += 1
            elif row['format'] == 'tvEpisode':
                tvEpisode += 1
                if metadata_list[x] > RATING_MINIMUM:
                    tvEpisode_good += 1
                else:
                    tvEpisode_bad += 1
            elif row['format'] == 'tvMovie':
                tvMovie += 1
                if metadata_list[x] > RATING_MINIMUM:
                    tvMovie_good += 1
                else:
                    tvMovie_bad += 1
            elif row['format'] == 'tvSeries':
                tvSeries += 1
                if metadata_list[x] > RATING_MINIMUM:
                    tvSeries_good += 1
                else:
                    tvSeries_bad += 1
            elif row['format'] == 'tvShort':
                tvShort += 1
                if metadata_list[x] > RATING_MINIMUM:
                    tvShort_good += 1
                else:
                    tvShort_bad += 1
            elif row['format'] == 'tvSpecial':
                tvSpecial += 1
                if metadata_list[x] > RATING_MINIMUM:
                    tvSpecial_good += 1
                else:
                    tvSpecial_bad += 1
            elif row['format'] == 'video':
                video += 1
                if metadata_list[x] > RATING_MINIMUM:
                    video_good += 1
                else:
                    video_bad += 1
            elif row['format'] == 'videoGame':
                videoGame += 1
                if metadata_list[x] > RATING_MINIMUM:
                    videoGame_good += 1
                else:
                    videoGame_bad += 1
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

prob_movie_good = movie_good / movie
prob_movie_bad = movie_bad / movie
prob_short_good = short_good / short
prob_short_bad = short_bad / short
prob_tvEpisode_good = tvEpisode_good / tvEpisode
prob_tvEpisode_bad = tvEpisode_bad / tvEpisode
prob_tvMovie_good = tvMovie_good / tvMovie
prob_tvMovie_bad = tvMovie_bad / tvMovie
prob_tvSeries_good = tvSeries_good / tvSeries
prob_tvSeries_bad = tvSeries_bad / tvSeries
prob_tvShort_good = tvShort_good / tvShort
prob_tvShort_bad = tvShort_bad / tvShort
prob_tvSpecial_good = tvSpecial_good / tvSpecial
prob_tvSpecial_bad = tvSpecial_bad / tvSpecial
prob_video_good = video_good / video
prob_video_bad = video_bad / video
prob_videoGame_good = videoGame_good / videoGame
prob_videoGame_bad = videoGame_bad / videoGame



print("movie w/ rating > RATING_MINIMUM")
print(prob_movie_good)
print("short w/ rating > RATING_MINIMUM")
print(prob_short_good)
print("tvEpisode w/ rating > RATING_MINIMUM")
print(prob_tvEpisode_good)
print("tvMovie w/ rating > RATING_MINIMUM")
print(prob_tvMovie_good)
print("tvSeries w/ rating > RATING_MINIMUM")
print(prob_tvSeries_good)
print("tvShort w/ rating > RATING_MINIMUM")
print(prob_tvShort_good)
print("tvSpecial w/ rating > RATING_MINIMUM")
print(prob_tvSpecial_good)
print("video w/ rating > RATING_MINIMUM")
print(prob_video_good)
print("videoGame w/ rating > RATING_MINIMUM")
print(prob_video_good)


entropy_parent = -(( (is_good/x) * log2(is_good/x) ) + ( (is_bad/x) * log2(is_bad/x) ))

entropy_movie = -( (movie_good/movie) * log2(movie_good/movie) ) + -( (movie_bad/movie) * log2(movie_bad/movie) )
entropy_short = -( (short_good/short) * log2(short_good/short) ) + -( (short_bad/short) * log2(short_bad/short) )

entropy_tvEpisode = -( (tvEpisode_good/tvEpisode) * log2(tvEpisode_good/tvEpisode) ) + -( (tvEpisode_bad/tvEpisode) * log2(tvEpisode_bad/tvEpisode) )
entropy_tvMovie = -( (tvMovie_good/tvMovie) * log2(tvMovie_good/tvMovie) ) + -( (tvMovie_bad/tvMovie) * log2(tvMovie_bad/tvMovie) )

entropy_tvSeries = -( (tvSeries_good/tvSeries) * log2(tvSeries_good/tvSeries) ) + -( (tvSeries_bad/tvSeries) * log2(tvSeries_bad/tvSeries) )
entropy_tvShort = -( (tvShort_good/tvShort) * log2(tvShort_good/tvShort) ) + -( (tvShort_bad/tvShort) * log2(tvShort_bad/tvShort) )

entropy_tvSpecial = -( (tvSpecial_good/tvSpecial) * log2(tvSpecial_good/tvSpecial) ) + -( (tvSpecial_bad/tvSpecial) * log2(tvSpecial_bad/tvSpecial) )
entropy_video = -( (video_good/video) * log2(video_good/video) ) + -( (video_bad/video) * log2(video_bad/video) )
entropy_videoGame = -( (videoGame_good/videoGame) * log2(videoGame_good/videoGame) ) + -( (videoGame_bad/videoGame) * log2(videoGame_bad/videoGame) )

print('Entropy of Parent')
print(entropy_parent)
print('Entropy of Movie')
print(entropy_movie)
print('Entropy of Short')
print(entropy_short)
print('Entropy of tvEpisode')
print(entropy_tvEpisode)
print('Entropy of tvMovie')
print(entropy_tvMovie)
print('Entropy of tvSeries')
print(entropy_tvSeries)
print('Entropy of tvShort')
print(entropy_tvShort)
print('Entropy of tvSpecial')
print(entropy_tvSpecial)
print('Entropy of video')
print(entropy_video)
print('Entropy of videoGame')
print(entropy_videoGame)

entropy_tree_2 = ((movie/x * entropy_movie) +
                  (short/x * entropy_short) +
                  (tvEpisode/x * entropy_tvEpisode) +
                  (tvMovie/x * entropy_tvMovie) +
                  (tvSeries/x * entropy_tvSeries) +
                  (tvShort/x * entropy_tvShort) +
                  (tvSpecial/x * entropy_tvSpecial) +
                  (video/x * entropy_video) +
                  (videoGame/x * entropy_videoGame) )

print("Weighted Entropy")
print(entropy_tree_2)

infogain = entropy_parent - entropy_tree_2
print("InfoGain")
print(infogain)
