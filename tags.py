#import
import os,re
from python_imports import pyScan,platformCheck # import: ignore
scan = pyScan
#platformCheck
if platformCheck.system == "Windows":
    localNull="NUL"
elif platformCheck.system == "Linux" or platformCheck.system == "Unix":
    localsNull="/dev/null"
#defenitions
iterator:int=int(1)
def tagPrint(filePath:str):
    r=os.popen("cat  "+filePath)
    tagName=r.read()
    r.close()
    global iterator
    print ("file:",filePath)
    print("tag:",iterator)
    r=os.popen("git cat-file -p "+tagName+" 2> "+localNull)
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
test=re.compile(r".*")
source = ".\\.git\\refs\\"
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		source = sys.argv[1]
print(test)
scan.recursiveScanRun(source,tagPrint)