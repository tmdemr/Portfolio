#!/bin/bash

for((i=6;i>0;i--))
do
    for((j=0;j<i;j++))
    do
	echo -n -e \*
    done
    echo " "
done
