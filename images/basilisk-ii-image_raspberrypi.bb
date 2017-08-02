# Setup image type variables before includes to pull in the image bits.
IMAGE_FSTYPES += "ext4 rpi-sdimg"
SDIMG_ROOTFS_TYPE = "ext4"

# Raspberry pi images...
DEPENDS += "bcm2835-bootfiles"

# Base this image on basilisk-ii-image
include basilisk-ii-image.bb

# TODO: add to cmdline.txt
# logo.nologo fbcon=map:10 splash quiet plymouth.ignore-serial-consoles

# TODO: modprobe snd-bcm2835
# TODO: dtparam=audio=on in config.txt
# TODO: disable-splash=1 in config.txt