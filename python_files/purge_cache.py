#imports
import shutil,re
import pyScan
scan=pyScan
#defenitions
def deletion(filePath:str):
	shutil.rmtree(filePath)
	print(filePath,"deleted")
#default setup
scan.test=re.compile(r"__pycache__|\.history")
scan.exclude=[".venv",".git"]
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		pass
print(scan.test)
scan.recursiveScanRun(".",deletion,True)
