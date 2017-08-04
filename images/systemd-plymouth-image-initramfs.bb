# Simple initramfs image to bring up the hardware and hand off control to systemd
DESCRIPTION = "A small initramfs that brings up systemd and hands off control rapidly."

PACKAGE_INSTALL = "systemd-initramfs plymouth ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
SPLASH="psplash"
IMAGE_FEATURES = "splash"

export IMAGE_BASENAME = "systemd-plymouth-image-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

BAD_RECOMMENDATIONS += "busybox-syslog"
