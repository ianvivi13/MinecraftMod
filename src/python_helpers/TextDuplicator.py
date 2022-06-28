colors = (
    "white terracotta",
    "orange terracotta",
    "magenta terracotta",
    "light blue terracotta",
    "yellow terracotta",
    "lime terracotta",
    "pink terracotta",
    "gray terracotta",
    "light gray terracotta",
    "cyan terracotta",
    "purple terracotta",
    "blue terracotta",
    "brown terracotta",
    "green terracotta",
    "red terracotta",
    "black terracotta"
)

seperator = "\n"
search = 'terracotta'

corals = {
    "brain",
    "fire",
    "tube",
    "horn"
}

with open('duplicate.txt','r') as file:
    data = file.read()

with open('duplicate.txt','a') as file:
    for single in colors:
        newdata = data.replace(search, single)
        newdata = newdata.replace(search.upper(), single.upper())
        newdata = newdata.replace(search.capitalize(), single.capitalize())
        file.write(seperator + newdata)


# all lower
# all upper
# front upper