#import
import os,re
#defenitions
def deduplicate(List):
	output=[]
	for i in range(len(List)):
		if List[i] not in output:
			output.append(List[i])
	return output
#
def deletion(filePath):
	os.remove(filePath)
	print(filePath,"deleted")
#
def scanPulseRun(f_paths, f_scanned, task):
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
def recursiveScanRun(origin,task):
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
#when run from command line
test=re.compile("\.class")
exclude=[".git"]
if __name__ == "__main__":
	import sys
	test=re.compile("\."+sys.argv[1])
	print(test)
	recursiveScanRun(".",deletion)