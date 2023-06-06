## general:
# removes all found .class files

#imports
import os,re
import pyScan as scan
#defenitions
##// function to remove the file at filePath
def deletion(filePath:str):
	os.remove(filePath)
	print(filePath,"deleted")
#default setup
##// standard values for scan
scan.test=re.compile(r"\.class")
scan.exclude=["bin","build",".gradle","python",".venv",".history",".git"]
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
    ##// arguments passed are taken as alternate file-type
		scan.test=re.compile(r"\."+sys.argv[1]+r"$")
##// actual execution
print(scan.test)
scan.ScanRun(".",deletion)
