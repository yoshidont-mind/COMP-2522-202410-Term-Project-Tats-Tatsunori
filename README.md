# COMP-2522-202410-Term-Project

## Our names:
Tatsuya Yoshida (Set E), Tatsunori Marumo (Set F)

## Our student numbers:
A01361712, A01327744

## Our GitHub names:
yoshidont-mind, TatsunoriMarumo

## Any important comments you'd like to make about your work:


## About this game
### __0. Overview：__
We wanted to create a game that is original, utilizes the principles of OOP (Object-Oriented Programming), and effectively leverages a GUI. We aimed for the game to be fun and engaging despite having simple rules. Inspired by two globally popular games—Tetris and 2048—we devised "Numbered Tetris."

In this game, players engage with elements derived from Tetris, such as clearing blocks, combined with simple arithmetic operations from 2048. The objective for players is to make the sum of consecutive blocks equal to 10 to remove them, and to clear as many blocks as possible before the descending blocks reach the top.

Seeing is believing! Give it a try right away!

### __1. How the Game Progresses:__

#### 1.1 Set up
When you run GameApp.java, the program first tries to load the player file from the Player directory. If the Player/player.ser file does not exist, the command line will display "Could not find the file in: Player/player" and will skip this loading process. Immediately after, a popup screen appears, and the game starts.

#### 1.2 画面上の項目
The left side of the popup screen displays the blocks, while the right side shows the following items:
- The next block
- Best score (if the player file has been loaded successfully, it displays the best score stored in the file; otherwise, it shows 0)
- Current score
- Speed (starts at x1.00 and gradually increases over time.)

#### 1.2 画面上の項目
Players use the following five keys to control the game:
- Left (←): Moves the currently falling block one space to the left.
- Right (→): Moves the currently falling block one space to the right.
- Down (↓): Moves the currently falling block one space down.
- Up (↑): Drops the currently falling block to the bottom.
- Space: Pauses/resumes the game.

#### 1.2 Rule
When a block reaches the bottom, the following sequence of block removal and score adjustment occurs:
1. Starting with the current block, it adds the value of each adjacent block to the left until the sum reaches 10, at which point the blocks are added to the to-get-removed list.
2. If the wall is reached, or there are no more blocks before the sum reaches 10, the addition operation ends.
3. The same operations are repeated to the right and below the current block.
4. Blocks listed in the to-get-removed list are removed, and the number of blocks removed is added to the current score.

#### 1.3 When the game is over
When the game ends, "GAME OVER" appears, and the game concludes. If the player has beaten the best score, the player file in the Player directory is updated. If the player file does not exist, the program will create this file (and the directory if necessary).

## Related Links
- Gameplay video
- [Mind map (FigJam Board)](https://www.figma.com/file/6bLAycrmsc6pl4sIQ3BrK2/term-project-Tats-Tatsnori?type=whiteboard&node-id=0%3A1&t=Aaqj5ZrGC26gpV8b-1)

## Indices for required elements
| **Element**                      | **Example / How we applied**                                                                                                       |
|----------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| **1. Ideation mindmap**          | document > mindmap.pdf                                                                                                             |
| **2. UML diagram**               | document > uml.pdf                                                                                                                 |
| **3. Version control wrangling** | [GitHub repository > commit history](https://github.com/yoshidont-mind/COMP-2522-202410-Term-Project-Tats-Tatsunori/commits/main/) |
| **4. (a) Execution**             | It's completely playable without any errors.                                                                                       |
| **4. (b) Concepts application**  | GameApp (Extends abstract class Application), Player (Implements interface Serializable), etc.                                     |
| **4. (c) Documentation**         | Fully documented throughout code including Javadoc for all public members and helpful inline comments.                             |
| **4. (d) Unit testing**          | Under Test directory (Thoroughly tested all methods where possible)                                                                |
| **BONUS: Save feature**          | Player > player (The highest score is saved by serializing Player object)                                                          |
