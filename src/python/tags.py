#!/usr/bin python3

## general:
# scans .git/refs and runs the algorithm to get the tag information from it over any file it finds
# TODO: figure out how to stop the "fatal:" from popping up in the output

#import
import os,re
import pyScan as scan
#defenitions
iterator:int=int(1)
def tagPrint(filePath:str):
    r=os.popen("cat  "+filePath)
    tagName=r.read()
    r.close()
    global iterator
    print ("file:",filePath)
    print("tag:",iterator)
    r=os.popen("git cat-file -p "+tagName+" 2> "+os.path.devnull)
    output= r.read()
    r.close()
    signature=re.compile(r"gpgsig(.|\n)*")
    error=re.compile(r"^fatal")
    correct=re.compile(r"^tree")
    if error.search(output):
        pass
    elif correct.search(output):
        output= re.sub(signature,"",output)
        print(output)
    iterator=+int(1)
#default setup
scan.test=re.compile(r".*")
source = "."+os.path.sep+".git"+os.path.sep+"refs"+os.path.sep
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		source = sys.argv[1]
print(scan.test)
scan.ScanRun(source,tagPrint)