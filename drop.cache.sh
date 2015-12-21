echo "before.."
free -m
echo "dropping all caches"
sudo sh -c " echo 3 > /proc/sys/vm/drop_caches"
echo "after.."
free -m
