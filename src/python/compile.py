## general:
# scans all java source files and compiles them in place

#import
import os,re
import pyScan as scan
#defenitions
##// function to compile the given java file
def compile(filePath:str):
	print ("Compiling:",filePath)
	os.system('javac '+filePath)
#default setup
scan.test=re.compile(r"\.java$")
source="."+os.path.sep+"src"+os.path.sep+"java"+os.path.sep
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		pass
##// actual execution:
# runs compile over all found files
scan.ScanRun(source,compile)
