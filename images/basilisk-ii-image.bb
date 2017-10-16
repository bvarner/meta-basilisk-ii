inherit core-image

SUMMARY = "An image for booting straight to Basilisk II"
HOMEPAGE = "http://bvarner.github.io"
LICENSE = "MIT"

SPLASH="psplash"
IMAGE_FEATURES += "splash"

IMAGE_LINGUAS = "en-us"

#IMAGE_FEATURES += "tools-sdk"

IMAGE_INSTALL += " \
	kernel-modules \
    tzdata \
    bzip2 \
    devmem2 \
    dosfstools \
    less \
    nano \
    unzip \
    zip \
    unzip \
    alsa-lib \
    alsa-utils \
    alsa-utils-scripts \
    plymouth \
    basilisk-ii \
"

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND += " \
    set_local_timezone ; \
    disable_bootlogd ; \
"
export IMAGE_BASENAME = "basilisk-ii-image"
