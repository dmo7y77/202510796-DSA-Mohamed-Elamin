# --- Assignment Solutions ---

# Question 1: Linear Search
print("--- Task 1: Family Search ---")

# Making lists to store data
names = []
ages = []

n = int(input("How many members in your family? "))

# Getting data from the user
for i in range(n):
    name_input = input("Enter name: ")
    age_input = input("Enter age: ")
    names.append(name_input)
    ages.append(age_input)

# The search part
key = input("\nEnter the name you want to find: ")
found = False

for i in range(len(names)):
    if names[i].lower() == key.lower():
        print(names[i] + " was found and is aged " + str(ages[i]))
        found = True
        break

if found == False:
    print("Not found")


# Question 2: Bubble Sort for Names
print("\n--- Task 2: Sorting Names ---")

my_names = ["Zuck", "Shayd", "Emely", "Amjed", "Esther", "Manar", "Anne"]

# Bubble sort logic
for i in range(len(my_names)):
    for j in range(0, len(my_names) - i - 1):
        if my_names[j] > my_names[j+1]:
            # Swap the names
            temp = my_names[j]
            my_names[j] = my_names[j+1]
            my_names[j+1] = temp

print("Names after sorting:")
print(my_names)


# Question 3: Bubble Sort for Numbers
print("\n--- Task 3: Sorting Numbers ---")

my_nums = [109, 99, 23, 45, 23, 2, 5, 1]

# Bubble sort logic
for i in range(len(my_nums)):
    for j in range(0, len(my_nums) - i - 1):
        if my_nums[j] > my_nums[j+1]:
            # Swap the numbers
            temp = my_nums[j]
            my_nums[j] = my_nums[j+1]
            my_nums[j+1] = temp

print("Numbers after sorting:")
print(my_nums)