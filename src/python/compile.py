#import
import os,re
import pyScan
scan = pyScan
#defenitions
def compile(filePath:str):
	print ("Compiling:",filePath)
	os.system('javac '+filePath)
#default setup
scan.test=re.compile(r"\.java")
source = "."
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		source = sys.argv[1]
print(scan.test)
scan.recursiveScanRun(source,compile)
 