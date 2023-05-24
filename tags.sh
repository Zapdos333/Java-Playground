# git bash script to get all tags
# List all tags
tags_dir=".git/refs/tags"
tags=$(find "$tags_dir" -type f)
# Iterate over the tags and print them
iterator=1
for tag in $tags; do
	echo tag"$iterator":
	tag_name=`cat $tag`
	git cat-file -p $tag_name
	echo
	iterator=$((iterator+1))
done