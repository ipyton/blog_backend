CREATE KEYSPACE movie
    WITH REPLICATION = {
        'class' : 'SimpleStrategy',
        'replication_factor' : 1
        };
use movie;


create table resource(movieId text,  resource text, name text,gid text,status text, primary key (movieId, resource));

create table meta(movieId text primary key, poster text, score text, introduction text, movie_name text, tags text,
actor_list list<text>, release_year text, level text, picture_list list<text>, maker_list map<text,text>, genre_list list<text>);

create table movieGallery(userId text, movieId text, poster text, score text, introduction text, movie_name text, tags text,
                          actor_list list<text>, release_year text, level text, picture_list list<text>, maker_list map<text,text>,
                          position int, genre_list list<text>, language text, primary key ( userId, movieId, language));

create table requests(movieId text, movie_name text, create_time timestamp, userId text, release_year text,actor_list list<text>,  primary key ( movieId ));
