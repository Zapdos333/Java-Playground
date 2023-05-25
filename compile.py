import purge_class,os,re
scan = purge_class

def compile(filePath):
	os.system('javac '+filePath)
	print (filePath," compiled")

#default setup
scan.test=re.compile("\.java")
source = "."
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		source = sys.argv[1]
	print(scan.test)
	scan.recursiveScanRun(source,compile)
 