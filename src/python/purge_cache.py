## general:
# removes all in removeEntry.default and removeEntry.hard defined directories from the set subdirectories
# there to clear up any cache created by build-, compile- or IDE- processes

#imports
import shutil,os
#defenitions
##// class that defines two lists of (directory) names
class removeEntry:
    def __init__(self,default:list[str],hard:list[str]):
        self.default:list[str] = default
        self.hard:list[str] = hard
#default setup
##// define the removeEntry instances and the default for hard:bool
rootRem:removeEntry=removeEntry([".history","bin"],["build"])
pythonRem:removeEntry=removeEntry(["__pycache__"],[])
hard:bool = False
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		##// set hard to true if the first argument is "hard"
		if sys.argv[1]=="hard": hard = True
##// actual execution
with os.scandir(".") as it:
	for e in it:
		##// if the entry is in the default list, remove it
		if e.name in rootRem.default:
			shutil.rmtree(e)
			print(e.path,"removed")
		##// if the entry is in the hard list and hard is enabled, remove it
		if (e.name in rootRem.hard and hard):
			shutil.rmtree(e)
			print(e.path,"removed")
	##// do the same for the subdirectory src/python
with os.scandir("."+os.path.sep+"src"+os.path.sep+"python") as it:
    for e in it:
        if e.name in pythonRem.default:
            shutil.rmtree(e)
            print(e.path,"removed")
        if e.name in pythonRem.hard and hard:
            shutil.rmtree(e)
            print(e.path,"removed")
