FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Use the SDL 1.2.15 mirror with patches for raspberrypi from RetroPie.
SRCREV = "${AUTOREV}"
SRC_URI_remove := "http://www.libsdl.org/release/SDL-${PV}.tar.gz"
SRC_URI_prepend := " \
	git://github.com/RetroPie/sdl1.git;branch=rpi \
"

# This sets up the config script to use pkg-config macros to detect directfb.
SRC_URI += "file://0001-pkgconfig-directfb.patch"

# Raspberrypi Git checksums don't match the normal tarball..
#SRC_URI[md5sum] = ""
#SRC_URI[sha256sum] = ""

S = "${WORKDIR}/git"

# If we're on a raspberrypi...
RPI_DEPS = " userland npth"
RPI_OECONF = " --enable-video-dispmanx"

DEPENDS_raspberrypi_append = "${RPI_DEPS}"
RDEPENDS_${PN}_raspberrypi_append = "${RPI_DEPS}"
EXTRA_OECONF_raspberrypi_append = "${RPI_OECONF}"

DEPENDS_raspberrypi0_append = "${RPI_DEPS}"
RDEPENDS_${PN}_raspberrypi0_append = "${RPI_DEPS}"
EXTRA_OECONF_raspberrypi0_append = "${RPI_OECONF}"

DEPENDS_raspberrypi0-wifi_append = "${RPI_DEPS}"
RDEPENDS_${PN}_raspberrypi0-wifi_append = "${RPI_DEPS}"
EXTRA_OECONF_raspberrypi0-wifi_append = "${RPI_OECONF}"


# Always enable fbcon and disable rpath.
EXTRA_OECONF_remove = "--disable-video-fbcon"
EXTRA_OECONF_append = " --disable-rpath --enable-video-fbcon"
