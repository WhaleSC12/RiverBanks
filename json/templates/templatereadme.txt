users and courses are hashed by their UUID, providing easy sorting and O(1) access

clearance is to be compared against users.json.accessLevel, as to decouple enums from jsons

lessons within courses do not need a hash as they are sequential and close
