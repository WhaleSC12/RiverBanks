users and courses are hashed by their UUID, providing easy sorting and O(1) access

clearance is to be compared against users.json.accessLevel, as to decouple enums from jsons

lessons within courses do not need a hash as they are sequential and close

userCourses is used to store data pertaining to user progress in courses, such as lessons completed,
grades per lesson, and grade on the course final

user course progress is unhashed to make iteration simpler, but it is possible to map these and simply store the key as a member of the map