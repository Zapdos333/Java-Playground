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
def scanPulseRun(f_paths:list[str], f_scanned:list[str], task, scandir:bool=False):
	output=[]
	for path in f_paths:
		with os.scandir(path) as it:
			for entry in it:
				# print ("debug:",bool(re.search(test,entry.name)),bool(entry.is_file()))
				if entry.is_dir():
					if entry.name not in f_scanned:
						output.append(os.path.join(path,entry.name))
						if scandir and re.search(test,entry.name): task(os.path.join(path,entry.name))
				if entry.is_file() and re.search(test,entry.name) and not scandir:
					task(os.path.join(path,entry.name))
					print("executed on:",os.path.join(path,entry.name),end="\n\n")
		f_scanned.append(path)
	output=deduplicate(output)
	return output
#
def recursiveScanRun(origin:str,task,scandir:bool=False):
	results=[]
	scanned=exclude
	results=scanPulseRun([origin],scanned,task,scandir)
	while len(results)>0:
		new_results=[]
		for result in results:
			if os.path.isdir(result):
				new_results.append(result)
		results=scanPulseRun(new_results,scanned,task,scandir)
		scanned.extend(results)
		print("found in cycle:\n",results,sep="",end="\n\n")
		scanned=deduplicate(scanned)
	print("run complete")
#export defenitions
exclude:list[str]=[".git",".venv",".vscode",".history","__pycache__"]
test=re.compile(r"")
