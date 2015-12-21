#!/bin/bash
set -e

BLOCK_SIZE=1M
MAX_ROUND=2000
FILE_NUM=2
COUNT=100

echo "Initializing $FILE_NUM files"
echo "Dd block size is $BLOCK_SIZE, count is $COUNT"

dd if=/dev/urandom of=tmp-file bs=$BLOCK_SIZE count=$COUNT

for i in `seq $FILE_NUM`; do
  dd if=tmp-file of=tmp-file-$i bs=$BLOCK_SIZE &
done
wait

echo "generating disk IO load."
for i in `seq $MAX_ROUND`; do
  echo "starting a new round of writing."
  for i in `seq $FILE_NUM`; do
    dd if=tmp-file-$i of=tmp-file-$i-backup bs=$BLOCK_SIZE &
  done
  wait
done
