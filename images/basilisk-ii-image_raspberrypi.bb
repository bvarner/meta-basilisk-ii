# Setup image type variables before includes to pull in the image bits.
IMAGE_FSTYPES += "ext4.gz rpi-sdimg"
SDIMG_ROOTFS_TYPE = "ext4.gz"

# Raspberry pi images...
DEPENDS += "bcm2835-bootfiles"

# Base this image on basilisk-ii-image
include basilisk-ii-image.bb
