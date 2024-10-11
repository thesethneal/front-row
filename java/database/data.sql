BEGIN TRANSACTION;

--Users
INSERT INTO users (username,password_hash,role) VALUES ('user@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('drake@drake.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('rihanna@rihanna.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('thebeatles@thebeatles.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('taylorswift@taylorswift.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('white_hat@TechElevatorRecords.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('cool_dude@CoriolisRecords.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BAND');

--Bands
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Drake', 'Aubrey Drake Graham (born October 24, 1986), known mononymously as Drake, is a Canadian rapper, singer, and actor. An influential figure in popular music, he has been credited with popularizing R&B sensibilities in hip hop artists. Gaining recognition by starring as Jimmy Brooks in the CTV teen drama series Degrassi: The Next Generation (2001–2008), Drake began his recording career in 2006 with the release of his debut mixtape, Room for Improvement (2006). He followed up with the mixtapes Comeback Season (2007) and So Far Gone (2009) before signing with Young Money Entertainment.', 3, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727715299/iwussu1yrp4nlpml4efa_kajbzq.webp');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Rihanna', 'Robyn Rihanna Fenty (/riˈænə/ ⓘ ree-AN-ə; born February 20, 1988) is a Barbadian singer, businesswoman and actress based in the United States. She was cited as the best-selling female recording artist of the 21st century by Guinness World Records and is the wealthiest female musical artist, with an estimated net worth of $1.4 billion as of 2024. Rihanna is the highest-certified female digital single artist and has six U.S. diamond certified singles (seven as a featured artist). She has achieved 14 number-one singles on the Billboard Hot 100. The recipient of various accolades, Rihanna is one of the best-selling recording artists ever, with sales estimated at 250 million units worldwide.', 4, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727715794/Rihanna-Fenty-Hair-Launch-GettyImages-2157052912-H-2024_ocf8ih.webp');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('The Beatles', 'The Beatles were an Engcdlish rock band formed in Liverpool in 1960. The core lineup of the band comprised John Lennon, Paul McCartney, George Harrison and Ringo Starr. They are widely regarded as the most influential band of all time and were integral to the development of 1960s counterculture and the recognition of popular music as an art form. Rooted in skiffle, beat and 1950s rock n roll, their sound incorporated elements of classical music and traditional pop in innovative ways. The band also explored music styles ranging from folk and Indian music to psychedelia and hard rock. As pioneers in recording, songwriting and artistic presentation, the Beatles revolutionized many aspects of the music industry and were often publicized as leaders of the era youth and sociocultural movements.', 5, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716027/the-beatles-rehearse-for-that-nights-royal-variety-performance-at-the-prince-of-wales-theatre-4th-november-1963-the-queen-mother-will-attend-photo-by-central-press_hulton-archive_getty-images_uk6oyh.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Taylor Swift', 'Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. Known for her autobiographical songwriting, artistic reinventions, and cultural impact, Swift is a leading figure in popular music and the subject of widespread public interest.', 6, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716174/taylor-swift-red-carpet_hqtg3z.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Morgan Wallen', 'Morgan Cole Wallen (born May 13, 1993) is an American country pop singer. He competed in the sixth season of The Voice, originally as a member of Ushers team, but later as a member of Adam Levines team. After being eliminated in the playoffs of that season, he signed to Panacea Records, releasing his debut EP, Stand Alone, in 2015.', 6, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716353/2203-morgan-wallen.990x660_jk8vmj.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Chris Stapleton', 'Kentucky-born artist Chris Stapleton is a 10x Grammy, 16x CMA and 15x ACM Award-winner and one of the country’s most respected and beloved musicians. In the past year, he’s been named Entertainer of the Year at the 58th ACM Awards—resulting in the prestigious ACM Triple Crown Award—and Male Vocalist of the Year at the 2022 CMA Awards—his sixth time earning the award, setting the record for most wins ever in the category.', 6, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727790001/1_6Ju6KHPQR6oOoswk5UmuVw_znm4wb.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Mumford & Sons', 'Mumford & Sons rose to prominence in 2009, releasing debut album Sigh No More. With folk music at its nucleus, the band took a propulsive, heart-on-sleeve approach to playing their acoustic instruments, spawning the likes of Little Lion Man and The Cave. Whatever Mumford & Sons were doing, it connected emphatically, garnering a first BRIT Award for British Album and 6 Grammy nominations.', 5, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727962370/channels4_profile_ki2jxv.jpg');

--Managed by Tech Elevator Records
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Men I Trust', 'Men I Trust is a Canadian indie band formed in Montreal, Quebec, in 2014. The band consists of Emmanuelle Proulx (lead vocals, guitar), Jessy Caron (guitar, bass), and Dragos Chiriac (keyboards). The group has self-released all of their music and has released physical editions on the Canadian indie label Return to Analog.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491288/image_b5mnwj.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Duster', 'Duster is an American indie rock band from San Jose, California that was formed in 1996. The group has consisted of multi-instrumentalists Clay Parton, Canaan Dove Amber, and Jason Albertini for most of its history; Albertini left the band in 2022. Duster were closely associated with the burgeoning slowcore and space rock revival movements during the 1990s.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491330/image_1_yqgij1.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('The Marías', 'The Marías is an American indie pop band from Los Angeles, California. They are known for performing songs in both English and Spanish, as well as infusing their music with elements like jazz percussion, guitar riffs, and horn solos. Their core lineup consists of lead vocalist María Zardoya and drummer/producer Josh Conway, guitarist Jesse Perlman, and keyboardist Edward James.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491359/image_2_gcjuh2.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Inner Wave', 'Inner Wave is an alternative indie-pop band from Los Angeles, California. They are known for their blend of psychedelic and synthwave sounds weaving in indie rock elements, jazz chords, Latin beats, and smooth vocals. Pablo Sotelo is the lead vocalist and guitarist with Jean Pierre Narvaez on the bass and Elijah Trujillo on the guitar. Sotelo, Narvaez, and Trujillo, the founding members of the band, are now joined by drummer Luis Portillo and sound engineer and keyboardist Jose Cruz', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491376/image_3_wjmbrv.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('The Weekend', 'Abel Makkonen Tesfaye (born February 16, 1990), known professionally as the Weeknd, is a Canadian singer-songwriter. He is known for his unconventional musical production, artistic reinventions and use of the falsetto register. Tesfaye has sold over 75 million records, making him one of the world''s best-selling artists.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491398/image_4_syi8nb.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Ludovico Einaudi', 'Ludovico Maria Enrico Einaudi OMRI (born 23 November 1955) is an Italian pianist and composer. Trained at the Conservatorio Verdi in Milan, Einaudi began his career as a classical composer, later incorporating other styles and genres such as pop, rock, folk, and world music.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491411/image_360_nesgmi.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Gibran Alcocer', 'Gibran Alcocer, a 21-year-old pianist, composer, and producer hailing from Yucatán, Mexico, has been immersed in music since childhood. He kicked off his musical journey by taking piano lessons, eventually diving into classical music studies. His knack for composition and production emerged at the age of 12.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491424/image_480_ogwtw0.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Martin Hayes Quartet', 'The Martin Hayes Quartet expands on many of the musical ideas pursued by Martin in his longstanding partnership with Dennis Cahill. The melody still remains central but now with an added range of sonic possibilities provided by the bass clarinet and viola d''amore. The addition of these instruments creates an added aural texture and amplifies the rhythmic possibilities while also allowing a larger role for improvisation. Both Doug and Liz bring a wealth of musical experience that contributes to the spacious, rich arrangements of the Quartet.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491442/image_480_ipqexz.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Brian Crain', 'Brian was born in Hollywood, California. He has been composing music as long as he can remember. Family members tell him that when he was very young he began whistling nearly all the time. When someone would ask what he was whistling, Brian would usually answer something like, "I don''t know. I just made it up."', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491450/image_720_lvcckr.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Fabrizio Paterlini', 'Paterlini began playing piano at six-years-old. From the first moment he stroked the keys his life irrevocably changed, music becoming “a choice made every day...explored in all its aspects.” And so music and his life’s path became, and remain, inextricably linked; while he progresses as a musician and composer, so his development as a human being runs parallel. Formal study in his chosen artistic endeavour came with five years at Campiani’s Academy, Mantua’s Academy of Arts, from where he graduated in Music Theory.', 7, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728491456/image_720_qrtlpa.png');

--Managed by Coriolis Records
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Jim Croce', 'James Joseph Croce (January 10, 1943 – September 20, 1973) was an American folk and rock singer-songwriter. Between 1966 and 1973, he released five studio albums and numerous singles. During this period, Croce took a series of odd jobs to pay bills while he continued to write, record and perform concerts. After Croce formed a partnership with the songwriter and guitarist Maury Muehleisen in the early 1970s, his fortunes turned. Croce''s breakthrough came in 1972, when his third album, You Don''t Mess Around with Jim, produced three charting singles, including "Time in a Bottle", which reached No. 1 after Croce died. The follow-up album Life and Times included the song "Bad, Bad Leroy Brown", Croce''s only No. 1 hit during his lifetime.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494626/image_720_zfnkaj.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('The Dandy Warhols', 'The Dandy Warhols are an American psychedelic/alternative rock band, formed in Portland, Oregon, in 1994 by singer-guitarist Courtney Taylor-Taylor and guitarist Peter Holmström. They were later joined by keyboardist Zia McCabe and drummer Eric Hedford. Hedford left in 1998 and was replaced by Taylor-Taylor''s cousin Brent DeBoer. The band''s name is a play on the name of American pop artist Andy Warhol. The band gained recognition after they were signed to Capitol Records and released their major label album debut, ...The Dandy Warhols Come Down, in 1997. In 2001, the band rose to new levels of fame after their song "Bohemian Like You" enjoyed extensive exposure due to being featured in an advertisement.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494630/image_480_ucdq1f.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Tame Impala', 'Tame Impala is the psychedelic music project of Australian multi-instrumentalist Kevin Parker. In the recording studio, Parker writes, records, performs, and produces all of the project''s music. As a touring act, Tame Impala consists of Parker, Dominic Simper, Jay Watson, Cam Avery, and Julien Barbagallo.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494631/image_480_sydrd4.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Neon Indian', 'Neon Indian is an American electronic music band from Denton, Texas. The music is composed by Mexican-born Alan Palomo, who is also part of the band Ghosthustler and releases solo music under his birth name and the stage name VEGA. The project has been characterized as defining the 2010s music genre chillwave.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494653/image_720_ywbc8d.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Future Islands', 'Future Islands is an American synth-pop band based in Baltimore, Maryland, comprising Gerrit Welmers (keyboards and programming), William Cashion (bass, acoustic and electric guitars), Samuel T. Herring (lyrics and vocals), and Michael Lowry (percussion). Future Islands came to prominence in 2014 with their fourth album Singles released by 4AD. Its lead single "Seasons (Waiting on You)" was considered the best song of 2014 by Pitchfork, and NME and its performance at the Late Show with David Letterman in March 2014, became the most-viewed video on the show''s YouTube page.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494658/image_720_diaogc.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Vulfpeck', 'Vulfpeck is an American funk band founded in 2011 in Ann Arbor, Michigan, by Jack Stratton, Theo Katzman, Woody Goss and Joe Dart. The band has released four extended plays, six studio albums and one live album through their own record label. The band gained recognition in 2014 for releasing Sleepify, a silent album that exposed a loophole in Spotify''s royalty distribution and funded an admission-free tour. The band is one of the first to sell out Madison Square Garden without a manager or backing label, and released the recorded performance as a live album in 2019. The band''s most recent album, Schvitz, was released in December 2022.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494671/image_720_n3mfxh.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Poor Man''s Poison', 'Poor Man''s Poison is an American folk band from Hanford, California. The lineup since its creation consists of guitarists Ryan Hakker and Michael Jacobs, mandolinist Tommy McCarthy and double bassist Dustin Medeiros; all members share vocal duties, although Hakker usually acts as lead vocalist. The band was originally formed in 2009 from the remnants of Done For Good, another band by Hakker, McCarthy and Medeiros; after a hiatus in 2014, they reformed in 2019, this time more focused on releasing studio singles rather than producing albums or performing live. Mostly defined by their acoustic sound, they have been categorized as various genres, including folk, Americana, country, rock, and pop; Medeiros has claimed that the band "cannot be pinned down in one specific category".', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494675/image_720_flel3s.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Marvin Gaye', 'Marvin Pentz Gaye Jr. (April 2, 1939 – April 1, 1984) was an American soul and R&B singer, songwriter, and musician. He helped shape the sound of Motown in the 1960s, first as an in-house session player and later as a solo artist with a string of successes, which earned him the nicknames "Prince of Motown" and "Prince of Soul". Gaye''s Motown songs include "Ain''t That Peculiar", "How Sweet It Is (To Be Loved by You)", and "I Heard It Through the Grapevine". He also recorded duets with Mary Wells, Kim Weston, Tammi Terrell, and Diana Ross. During the 1970s, Gaye recorded the albums What''s Going On (1971) and Let''s Get It On (1973) and became one of the first artists in Motown to break away from the reins of a production company.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494692/image_720_ya1z4i.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('NxxxxxS', 'French hip-hop producer and DJ under Dusty Records. "The only goal is to make something that looks and feels beautiful to me and hopefully can touch other people at the same time."', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494695/image_480_mfg8wd.png');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Denzel Curry', 'Denzel Rae Don Curry is an American rapper. Born and raised in Miami Gardens, Florida, Curry started rapping while in the sixth grade and began working on his first mixtape, King Remembered Underground Tape 1991–1995, in 2011, which was influenced by underground Florida rapper SpaceGhostPurrp.  He has since released three extended plays: 32 Zel/Planet Shrooms in 2015, 13 in 2017, and Unlocked (a collaboration with Kenny Beats) in 2020, and four studio albums: Imperial in 2016, Ta13oo in 2018, Zuu in 2019, and Melt My Eyez See Your Future in 2022. Ta13oo, Zuu, and Melt My Eyez debuted at numbers 28, 32, and 51 on the Billboard 200 chart, respectively, with Melt My Eyez returning at number 17 after the release of an extended edition.', 8, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1728494705/image_480_fbx4bb.png');


--Users following bands
INSERT INTO user_band (user_id, band_id) VALUES (1, 3), (1, 5), (1, 1);

--Images
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978501/singer-Taylor-swift-2013_nbzrsl.webp');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978381/Taylor-Swift-concert-yellow-dress-GettyImages-2015112497-2400x1600-1_ldmruc.webp');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978408/240927-taylor-swift-republicans-kh-513629_eia5cy.webp');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978042/IBYTAM_still-page_yk4vjm.jpg');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727977890/VF1421_Taylor_Swift_Lede_qlev8u.webp');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978430/taylor-swift-red-carpet_zkkype.jpg');
INSERT INTO images (band_id, image_link) VALUES (4, 'https://res.cloudinary.com/dhgcjy2pf/image/upload/v1727978064/download_t058lp.webp');

--Messages
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Paul is dead.', '2024-10-01 13:12:00', '2024-10-14 13:12:00', 3);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Under my umbrella ella ella ey ey ey', '2024-10-01 13:12:00', '2024-10-31 13:12:00', 2);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Paul is alive.', '2024-10-01 13:13:00', '2024-10-14 13:13:00', 3);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('This is an expired message. It should not be visible.', '2024-09-01 13:12:00', '2024-10-01 13:12:00', 3);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('I love Kendrick Lamar.', '2024-10-02 13:12:00', '2024-10-16 13:12:00', 1);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Excited to see you in Miami (Oct 18-20), NOLA (Oct 25-27), and Indy (Nov 1-3)!', '2024-10-02 13:12:00', '2024-11-3 13:12:00', 4);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Howdy.', '2024-10-02 13:12:00', '2024-10-16 13:12:00', 5);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('You should probably leave.', '2024-10-03 09:31:00', '2024-10-17 09:31:00', 6);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('I really messed it up this time, didn''t I, my dear?', '2024-10-03 13:35:00', '2024-10-17 13:35:00', 7);

--Genres
INSERT INTO genres (genre_name) VALUES ('blues'), ('classical'), ('country'), ('electronic'), ('folk'), ('hip-hop'), ('jazz'), ('latin'), ('new age'), ('pop'), ('r&b'), ('reggae'), ('rock');

--Genres matched with bands
INSERT INTO band_genre (band_id, genre_id) VALUES (1, 6), (2, 6), (2, 10), (3, 5), (3, 10), (3, 13), (4, 3), (4, 5), (4, 10), (5, 3), (6, 3), (6, 13), (7, 5), (7, 13),
(8, 5), (8, 10), (9, 13), (10, 10), (11, 10), (11, 13), (12, 11), (12, 10), (12, 6), (13, 2), (14, 2), (15, 2), (16, 2), (17, 2), (18, 5), (18, 13), (19, 13), (20, 10),
(20, 4), (21, 4), (22, 10), (22, 4), (23, 7), (23, 10), (24, 5), (25, 11), (26, 4), (27, 6);

--Events
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-18T19:00:00','Hard Rock Stadium', 'Miami, FL', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-19T19:00:00','Hard Rock Stadium', 'Miami, FL', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
    VALUES ('2024-10-20T19:00:00','Hard Rock Stadium', 'Miami, FL', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-25T19:00:00','Caesars Superdome', 'New Orleans, LA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-26T19:00:00','Caesars Superdome', 'New Orleans, LA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-27T19:00:00','Caesars Superdome', 'New Orleans, LA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-01T19:00:00','Lucas Oil Stadium', 'Indianapolis, IN', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-02T19:00:00','Lucas Oil Stadium', 'Indianapolis, IN', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-03T19:00:00','Lucas Oil Stadium', 'Indianapolis, IN', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-14T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-15T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-16T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-21T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-22T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-11-23T19:00:00','Rogers Centre', 'Toronto, ON, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-12-06T19:00:00','BC Place', 'Vancouver, BC, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-12-07T19:00:00','BC Place', 'Vancouver, BC, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-12-08T19:00:00','BC Place', 'Vancouver, BC, CA', 4, 'The Eras Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-18T18:00:00','Bank of America Stadium', 'Charlotte, NC', 5, 'One Night At A Time Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-19T18:00:00','Bank of America Stadium', 'Charlotte, NC', 5, 'One Night At A Time Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-11T20:30:00','Bank of America Stadium', 'Austin, TX', 6, 'Austin City Limits Festival');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-12-07T17:45:00','Allegiant Stadium', 'Las Vegas, NV', 6, 'George Strait Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-16T19:30:00','AO Arena', 'Manchester, GB', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-17T18:30:00','OVO Hydro', 'Glasgow, GB', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-20T18:30:00','3Arena', 'Dublin, IE', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-22T19:30:00','Utilita Arena', 'Birmingham, GB', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-23T18:30:00','The O2', 'London, GB', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-24T18:30:00','The O2', 'London, GB', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2025-03-07T18:30:00','Spark Arena', 'Auckland, NZ', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2025-03-08T18:30:00','Spark Arena', 'Auckland, NZ', 6, 'All-American Road Show');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-10T20:00:00','LANXESS arena', 'Cologne, DE', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-11T20:00:00','Festhalle', 'Frankfurt am Main, DE', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-12T20:00:00','Barclays Arena', 'Hamburg, DE', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-16T19:00:00','O2 Arena', 'Praha 9, CZ', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-18T20:00:00','Unipol Arena', 'Casalecchio di Reno, IT', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-19T20:00:00','Forum', 'Assago, IT', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-19T20:00:00','Forum', 'Assago, IT', 8, 'Melanie Martinez Trilogy Tour');
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-11T20:00:00','Majestic Theatre', 'Detroit, MI', 9, null);
INSERT INTO events (event_time, event_venue, event_address, event_host, event_name)
	VALUES ('2024-10-13T20:00:00','Brooklyn Bowl', 'Nashville, TN', 9, '91.One WNXP Presents');



--Match messages to users
INSERT INTO message_user (message_id, user_id, is_read)
    SELECT messages.message_id, users.user_id, FALSE
    FROM messages
    JOIN user_band ON messages.message_sender = user_band.band_id
    JOIN users ON user_band.user_id = users.user_id;

COMMIT TRANSACTION;
