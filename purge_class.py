import os
import re

test=re.compile("\.class")

def deduplicate(List):
	output=[]
	for i in range(len(List)):
		if List[i] not in output:
			output.append(List[i])
	return output

def deletion(file):
	os.remove(file)

def scan(f_paths, f_scanned, task):
	output=[]
	for path in f_paths:
		with os.scandir(path) as it:
			for entry in it:
				if entry.is_file and test.search(entry.name):
					task(os.path.join(path,entry.name))
					print("removed:",os.path.join(path,entry.name))
				if entry.is_dir:
					if entry.name not in f_scanned:
						output.append(os.path.join(path,entry.name))
		f_scanned.append(path)
	output=deduplicate(output)
	return output

def recursiveScan(origin):
	results=[]
	scanned=[".git"]
	results=scan([origin],scanned,deletion)
	while len(results)>0:
		new_results=[]
		for result in results:
			if os.path.isdir(result):
				new_results.append(result)
		results=scan(new_results,scanned,deletion)
		scanned.extend(results)
		#print("found in cycle: ",results)
		scanned=deduplicate(scanned)
	print("run complete")


if __name__ == "__main__":
	import sys
	recursiveScan(".")
	#running main code
	#arguments under sys.argv[]

