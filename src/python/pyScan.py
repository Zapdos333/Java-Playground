## general:
# ScanRun scans the entire tree provided as origin and executes task on any file that fulfill test while ignoring the directories specified in exclude
# the task can be run on directories instead if scandir is True (default is False)

#import
import os,re
#defenitions
##// standard list deduplication
def deduplicate(List:list):
	output=[]
	for i in range(len(List)):
		if List[i] not in output:
			output.append(List[i])
	return output
#
##// a single layer pulse of scanning
def scanPulseRun(f_paths:list[str], f_scanned:list[str], task, scandir:bool=False):
	output=[]
	for path in f_paths:
		with os.scandir(path) as it:
			for entry in it:
				# print ("debug:",bool(re.search(test,entry.name)),bool(entry.is_file()))
				if entry.is_dir() and entry.name not in f_scanned:
					output.append(entry.path)
					if scandir and re.search(test,entry.name):
						task(entry.path)
						print("executed on:",entry.path,end="\n\n")
				if entry.is_file() and re.search(test,entry.name) and not scandir:
					task(entry.path)
					print("executed on:",entry.path,end="\n\n")
		f_scanned.append(path)
	output=deduplicate(output)
	return output
#
##// the main function
def ScanRun(origin:str,task,scandir:bool=False):
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
##// the list of directories to exclude from scanning
exclude:list[str]=[""]
##// the pattern that the files/directories must match for task to be executed
test:re.Pattern[str]=re.compile(r"")
