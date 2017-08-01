# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

SUMMARY = "An image for booting straight to Basilisk II"
HOMEPAGE = "http://bvarner.github.io"
LICENSE = "MIT"

IMAGE_FEATUREs += "splash"
IMAGE_LINGUAS = "en-us"

DISTRO_FEATURES_remove = "opengl"
DISTRO_FEATURES_remove = "directfb"

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
    basilisk-ii \
    alsa-lib \
    alsa-utils \
    alsa-utils-scripts \
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
