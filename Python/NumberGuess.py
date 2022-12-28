import random

def check_guess(rand: int, guess: int) -> bool:
    if rand == guess:
        return True
    return False

options = [random.randint(1, 10), random.randint(11, 20), random.randint(21, 30), random.randint(31, 40), random.randint(41, 50), random.randint(51, 60), random.randint(61, 70), random.randint(71, 80)]
print("Here are your options:")
for i in range(0, len(options)):
    if i != len(options) - 1:
        print(options[i], end = ' ')
    else:
        print(options[i])
    

guess = int(input('Guess a number from your options: '))
rand = options[random.randint(0, len(options) - 1)]
if check_guess(rand, guess) == True:
    print("You were right!!")
else:
    print("WRONG!!!")
    print("The number was", rand)
