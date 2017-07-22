SUMMARY = "A 680x0 Macintosh Emulator"
DESCRIPTION = "Basilisk II is an Open Source 68k Macintosh emulator. That is, \
               it allows you to run 68k MacOS software on your computer, even \
               if you are using a different operating system. However, you \
               still need a copy of MacOS and a Macintosh ROM image to use \
               Basilisk II. "
HOMEPAGE = "https://basilisk.cebix.net/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "\
	libsdl (>= 1.2) \
"

RDEPENDS_${PN} = "\
	libsdl (>= 1.2) \
	pulseaudio \
"

SRCREV = "${AUTOREV}"
SRC_URI = "\
	git://github.com/cebix/macemu.git \
"

SRC_URI_append_raspberrypi = " \
	file://0001-makefile.patch \
	file://0002-raspberrypi-gensrc.patch \
"

PR = "rc1"

inherit autotools
inherit gettext
inherit pkgconfig


# Setup default values for the cross-compile environment variable defaults...
BII_CROSS_SOCKLEN_T ?= ""
BII_CROSS_BYTE_BITFIELDS ?= "" 
BII_CROSS_MMAP_ANON ?= ""
BII_CROSS_MMAP_SUPPORTS_MAP_ANONYMOUS ?= ""
BII_CROSS_MPROTECT_WORKS ?= ""
BII_CROSS_SIGNAL_NEED_REINSTALL ?= ""
BII_CROSS_SIGACTION_NEED_REINSTALL ?= ""
BII_CROSS_HAVE_EXTENDED_SIGNALS ?= ""
BII_CROSS_SIGSEGV_SKIP_INSTRUCTION ?= ""

# Overrides for raspberrypi machines...
BII_CROSS_SOCKLEN_T_raspberrypi = "yes" 
BII_CROSS_BYTE_BITFIELDS_raspberrypi = "yes" 
BII_CROSS_MMAP_ANON_raspberrypi = "yes" 
BII_CROSS_MMAP_SUPPORTS_MAP_ANONYMOUS_raspberrypi = "yes" 
BII_CROSS_MPROTECT_WORKS_raspberrypi = "yes" 
BII_CROSS_SIGNAL_NEED_REINSTALL_raspberrypi = "no" 
BII_CROSS_SIGACTION_NEED_REINSTALL_raspberrypi = "no" 
BII_CROSS_HAVE_EXTENDED_SIGNALS_raspberrypi = "yes" 
BII_CROSS_SIGSEGV_SKIP_INSTRUCTION_raspberrypi = "yes"

# And export the values.
export BII_CROSS_SOCKLEN_T
export BII_CROSS_BYTE_BITFIELDS 
export BII_CROSS_MMAP_ANON
export BII_CROSS_MMAP_SUPPORTS_MAP_ANONYMOUS
export BII_CROSS_MPROTECT_WORKS
export BII_CROSS_SIGNAL_NEED_REINSTALL
export BII_CROSS_SIGACTION_NEED_REINSTALL
export BII_CROSS_HAVE_EXTENDED_SIGNALS
export BII_CROSS_SIGSEGV_SKIP_INSTRUCTION


EXTRA_OECONF_append = " --without-x --enable-sdl-audio --enable-sdl-video --enable-vosf"
EXTRA_OECONF_append_raspberrypi = " --disable-jit-compiler"

FILES_${PN} = "/usr/bin/* /usr/share/BasiliskII /usr/share/BasiliskII/*" 

S = "${WORKDIR}/git/BasiliskII/src/Unix"
