import platform
system:str
if platform.system() == "Windows":
    system = "Windows"
elif platform.system() == "Linux":
    system = "Linux"
elif platform.system() == "Java":
    system = "Java"
elif platform.system() == "Unix":
    system = "Unix"
elif platform.system() == "macOS":
    system = "macOS"
else:
    pass