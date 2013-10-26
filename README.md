MusicWriter
===========

A program that (semi)randomly creates MIDI files

WHAT HAPPENS SO FAR:

(This may change a lot as I work through each section)

Step 1: Generate song info

- This includes: 
    - key (can be any major key from A to G#)
    - tempo (currently 100% static)
    - and time signature (also completely static)

Step 2: Generate song parts

- Main Chords
    - Generated based on key signature set in the above step
    - Currently are only 5th chords, because they're easy to make for any note of the scale
- Components
    - Intro (1), Verses (1-3), Choruses (1-3), Bridges (0-3), Solos (0-1), Outro (1)
    - Each as its own probability for number of bars
    - Notes of different lenghts (also determined by probability) are added until the number of beats added equals the total beats in the generated bars

Step 3: Generate song file
    - Generic MIDI info is added first
    - Song bytes then get added
    - File is closed and save
    - Voila, ze song is made
    

Song generation time varies based on the overall length of the song, but doesn't usually take much longer than 5 or so seconds. Insta-song!



UPCOMING FEATURES:

(None of these are guarenteed, of course, but they're definitely what I plan to add...)

- Melodies on top of chords
- More chord/scale choices
    - (Different genres?)
- User interface
    - Ability to set time signature before generation
    - Ability to set tempo before generation
    - More dynamic ability to set key
- Multiple instruments
