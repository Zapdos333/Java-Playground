#imports
import os,re
import pyScan
scan = pyScan
#defenitions
def deletion(filePath:str):
	os.remove(filePath)
	print(filePath,"deleted")
#default setup
scan.test=re.compile(r"\.class")
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		scan.test=re.compile(r"\."+sys.argv[1])
print(scan.test)
scan.recursiveScanRun(".",deletion)
