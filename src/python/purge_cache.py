#imports
import shutil,os
#defenitions
def deletion(filePath:str):
	shutil.rmtree(filePath)
	print(filePath,"deleted")
class removeEntry:
    def __init__(self,default:list[str],hard:list[str]):
        self.default:list[str] = default
        self.hard:list[str] = hard
#default setup
rootRem:removeEntry=removeEntry(["./__pycache__","./.history","./bin"],["./build"])
pythonRem:removeEntry=removeEntry(["./__pycache__"],[])
hard:bool = False
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		if sys.argv[1]=="hard": hard = True
with os.scandir(".") as it:
	for e in it:
		if e.name in rootRem.default:
			shutil.rmtree(e)
			print(e.path,"removed")
		if e.name in pythonRem.hard and hard:
			shutil.rmtree(e)
			print(e.path,"removed")
with os.scandir("./src/python") as it:
    for e in it:
        if e.name in pythonRem.default:
            shutil.rmtree(e)
            print(e.path,"removed")
        if e.name in pythonRem.hard and hard:
            shutil.rmtree(e)
            print(e.path,"removed")
