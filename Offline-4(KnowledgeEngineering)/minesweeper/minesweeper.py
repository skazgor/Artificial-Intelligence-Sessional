import itertools
import random


class Minesweeper():
    """
    Minesweeper game representation
    """

    def __init__(self, height=8, width=8, mines=8):

        # Set initial width, height, and number of mines
        self.height = height
        self.width = width
        self.mines = set()

        # Initialize an empty field with no mines
        self.board = []
        for i in range(self.height):
            row = []
            for j in range(self.width):
                row.append(False)
            self.board.append(row)

        # Add mines randomly
        while len(self.mines) != mines:
            i = random.randrange(height)
            j = random.randrange(width)
            if not self.board[i][j]:
                self.mines.add((i, j))
                self.board[i][j] = True

        # At first, player has found no mines
        self.mines_found = set()

    def print(self):
        """
        Prints a text-based representation
        of where mines are located.
        """
        for i in range(self.height):
            print("--" * self.width + "-")
            for j in range(self.width):
                if self.board[i][j]:
                    print("|X", end="")
                else:
                    print("| ", end="")
            print("|")
        print("--" * self.width + "-")

    def is_mine(self, cell):
        i, j = cell
        return self.board[i][j]

    def nearby_mines(self, cell):
        """
        Returns the number of mines that are
        within one row and column of a given cell,
        not including the cell itself.
        """

        # Keep count of nearby mines
        count = 0

        # Loop over all cells within one row and column
        for i in range(cell[0] - 1, cell[0] + 2):
            for j in range(cell[1] - 1, cell[1] + 2):

                # Ignore the cell itself
                if (i, j) == cell:
                    continue

                # Update count if cell in bounds and is mine
                if 0 <= i < self.height and 0 <= j < self.width and (i == cell[0] or j == cell[1]) :
                    if self.board[i][j]:
                        count += 1

        return count

    def won(self):
        """
        Checks if all mines have been flagged.
        """
        return self.mines_found == self.mines


class Sentence():
    """
    Logical statement about a Minesweeper game
    A sentence consists of a set of board cells,
    and a count of the number of those cells which are mines.
    """

    def __init__(self, cells, count):
        self.cells = set(cells)
        self.count = count

    def __eq__(self, other):
        if isinstance(other, Sentence):
            return frozenset(self.cells) == frozenset(other.cells) and self.count == other.count
        return False

    def __str__(self):
        return f"{self.cells} = {self.count}"
    def __hash__(self):
        return hash((frozenset(self.cells), self.count))

    def known_mines(self):
        # """
        # Returns the set of all cells in self.cells known to be mines.
        # """
        # raise NotImplementedError
        if len(self.cells) == self.count:
            return self.cells
        else:
            return None

    def known_safes(self):
        """
        Returns the set of all cells in self.cells known to be safe.
        """
        # raise NotImplementedError
        if self.count == 0 and len(self.cells) > 0:
            return self.cells
        else:
            return None

    def mark_mine(self, cell):
        """
        Updates internal knowledge representation given the fact that
        a cell is known to be a mine.
        """
        # raise NotImplementedError
        for i in self.cells:
            if i[0] == cell[0] and i[1] == cell[1]:
                self.count -= 1
                self.cells.remove(i)
                break

    def mark_safe(self, cell):
        """
        Updates internal knowledge representation given the fact that
        a cell is known to be safe.
        """
        # raise NotImplementedError
        for i in self.cells:
            if i[0] == cell[0] and i[1] == cell[1]:
                self.cells.remove(i)
                break


class MinesweeperAI():
    """
    Minesweeper game player
    """

    def __init__(self, height=8, width=8):

        # Set initial height and width
        self.height = height
        self.width = width

        # Keep track of which cells have been clicked on
        self.moves_made = set()

        # Keep track of cells known to be safe or mines
        self.mines = set()
        self.safes = set()

        # List of sentences about the game known to be true
        self.knowledge = []

    def mark_mine(self, cell):
        """
        Marks a cell as a mine, and updates all knowledge
        to mark that cell as a mine as well.
        """
        self.mines.add(cell)
        for sentence in self.knowledge:
            sentence.mark_mine(cell)

    def mark_safe(self, cell):
        """
        Marks a cell as safe, and updates all knowledge
        to mark that cell as safe as well.
        """
        self.safes.add(cell)
        for sentence in self.knowledge:
            sentence.mark_safe(cell)

    def add_knowledge(self, cell, count):
        """
        Called when the Minesweeper board tells us, for a given
        safe cell, how many neighboring cells have mines in them.

        This function should:
            1) mark the cell as a move that has been made
            2) mark the cell as safe
            3) add a new sentence to the AI's knowledge base
               based on the value of `cell` and `count`
            4) mark any additional cells as safe or as mines
               if it can be concluded based on the AI's knowledge base
            5) add any new sentences to the AI's knowledge base
               if they can be inferred from existing knowledge
        """
        # raise NotImplementedError
        self.moves_made.add(cell)
        self.mark_safe(cell)
        neighbors=set()
        for i in range(cell[0] - 1, cell[0] + 2):
            for j in range(cell[1] - 1, cell[1] + 2):

                if (i, j) == cell:
                    continue

                if 0 <= i < self.height and 0 <= j < self.width and (i == cell[0] or j == cell[1]) :
                        neighbors.add((i,j))
        new_sentence=Sentence(neighbors,count)
        new_sentences=[]
        new_sentences.append(new_sentence)
        n=len(self.knowledge)
        while len(new_sentences) > 0:
            new_sentence=new_sentences.pop(0)
            if(new_sentence in self.knowledge):
                continue 
            for sentence in self.knowledge:
                cells=set()
                if new_sentence.cells.issubset(sentence.cells):
                    cells=sentence.cells-new_sentence.cells
                    count=sentence.count-new_sentence.count
                elif sentence.cells.issubset(new_sentence.cells):
                    cells=new_sentence.cells-sentence.cells
                    count=new_sentence.count-sentence.count
                if len(cells) > 0:
                    new_sentences.append ( Sentence(cells,count))
            self.knowledge.append(new_sentence)
        if n != len(self.knowledge)-1:  
            print("new sentence added.")
        for c in self.safes:
            for sentence in self.knowledge:
                sentence.mark_safe(c)
        for c in self.mines:
            for sentence in self.knowledge:
                sentence.mark_mine(c)
        flag=True
        while flag:
            flag=False
            for sentence in self.knowledge:
                if len(sentence.cells) == 0:
                    self.knowledge.remove(sentence)
                if sentence.known_mines() != None:
                    temp=set(sentence.known_mines())
                    for cell in  temp:
                        self.mark_mine(cell)
                    flag=True
                    break
                elif sentence.known_safes() != None:
                    temp=set(sentence.known_safes())
                    for cell in temp:
                        self.mark_safe(cell)
                    flag=True
                    break
        print("Sentences->")
        for sentence in self.knowledge:
            print(sentence)
        print("Safe Moves->"+str(self.safes))
        print("Mines->"+str(self.mines))
    def make_safe_move(self):
        """
        Returns a safe cell to choose on the Minesweeper board.
        The move must be known to be safe, and not already a move
        that has been made.

        This function may use the knowledge in self.mines, self.safes
        and self.moves_made, but should not modify any of those values.
        """
        # raise NotImplementedError
        for cell in self.safes:
            if cell not in self.moves_made:
                print("Given Move:"+ str(cell))
                return cell
        return None

    def make_random_move(self):
        """
        Returns a move to make on the Minesweeper board.
        Should choose randomly among cells that:
            1) have not already been chosen, and
            2) are not known to be mines
        """
        # raise NotImplementedError
        numbers=[i for i in range(self.height)]
        random.shuffle(numbers)
        colnumbers=[i for i in range(self.width)]
        for i in numbers :
            random.shuffle(colnumbers)
            for j in colnumbers:
                cell=(i,j)
                if cell not in self.moves_made and cell not in self.mines:
                    print("Given Move:"+str(cell))
                    return cell
        return None
