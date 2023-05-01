#!/bin/bash

for file in *.yaml
do
#  printf $file
  kubectl apply -f $file
done