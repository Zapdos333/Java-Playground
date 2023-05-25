#import
import os,re
#defenitions
def deduplicate(List:list):
	output=[]
	for i in range(len(List)):
		if List[i] not in output:
			output.append(List[i])
	return output
#
def deletion(filePath:str):
	os.remove(filePath)
	print(filePath,"deleted")
#
def scanPulseRun(f_paths:list[str], f_scanned:list[str], task):
	output=[]
	for path in f_paths:
		with os.scandir(path) as it:
			for entry in it:
				if entry.is_file and test.search(entry.name):
					task(os.path.join(path,entry.name))
					print("executed on:",os.path.join(path,entry.name),end="\n\n")
				if entry.is_dir:
					if entry.name not in f_scanned:
						output.append(os.path.join(path,entry.name))
		f_scanned.append(path)
	output=deduplicate(output)
	return output
#
def recursiveScanRun(origin:str,task):
	results=[]
	scanned=exclude
	results=scanPulseRun([origin],scanned,task)
	while len(results)>0:
		new_results=[]
		for result in results:
			if os.path.isdir(result):
				new_results.append(result)
		results=scanPulseRun(new_results,scanned,task)
		scanned.extend(results)
		print("found in cycle: ",results,sep=", \n",end="\n\n")
		scanned=deduplicate(scanned)
	print("run complete")
#default setup
test=re.compile(r"\.class")
exclude=[".git",".venv",".vscode",".history","__pycache__"]
#when run from command line
if __name__ == "__main__":
	import sys
	if len(sys.argv)>1:
		test=re.compile(r"\."+sys.argv[1])
print(test)
recursiveScanRun(".",deletion)
