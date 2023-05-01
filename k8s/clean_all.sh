#!/bin/bash

kubectl delete service --all
kubectl delete deployment --all
kubectl delete pod --all
kubectl delete statefulsets --all