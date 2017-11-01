SUMMARY = "A 680x0 Macintosh Emulator"
DESCRIPTION = "Basilisk II is an Open Source 68k Macintosh emulator. That is, \
               it allows you to run 68k MacOS software on your computer, even \
               if you are using a different operating system. However, you \
               still need a copy of MacOS and a Macintosh ROM image to use \
               Basilisk II. "
HOMEPAGE = "https://basilisk.cebix.net/"



# NOTES:
# This recipe sets up a /bin/basilisk-ii.sh shell script, using the env / boot 
# commands setup by this recipie (so that different machines / arches can set
# specific options). This shell script is setup to run by a systemd service 
# when a multi-user target is specified (like, during boot).
# systemd acts as PID 0, and manages spawning other tasks for us.

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "\
	libsdl (>= 1.2) \
"

RDEPENDS_${PN} = "\
	libsdl (>= 1.2) \
	alsa-lib \
	alsa-utils \
"

SRCREV = "${AUTOREV}"
SRC_URI = "\
	git://github.com/cebix/macemu.git \
	file://basilisk-ii.sh \
	file://systemd-units/basilisk-ii.service \
"

# If you get a bitbake error, it's likely that you don't have the following
# items in your 'files' directory. You'll need to add a mac.rom and mac.hd
SRC_URI += "\
	file://user-files/preferences \
	file://user-files/mac.rom \
	file://user-files/mac.hd \
"

SRC_URI_append_raspberrypi = " \
	file://0001-makefile.patch \
	file://0002-raspberrypi-gensrc.patch \
"
SRC_URI_append_raspberrypi0 = " \
	file://0001-makefile.patch \
	file://0002-raspberrypi-gensrc.patch \
"
SRC_URI_append_raspberrypi0-wifi = " \
	file://0001-makefile.patch \
	file://0002-raspberrypi-gensrc.patch \
"

PR = "rc1"

inherit autotools gettext pkgconfig systemd

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

BII_CROSS_SOCKLEN_T_raspberrypi0 = "yes" 
BII_CROSS_BYTE_BITFIELDS_raspberrypi0 = "yes" 
BII_CROSS_MMAP_ANON_raspberrypi0 = "yes" 
BII_CROSS_MMAP_SUPPORTS_MAP_ANONYMOUS_raspberrypi0 = "yes" 
BII_CROSS_MPROTECT_WORKS_raspberrypi0 = "yes" 
BII_CROSS_SIGNAL_NEED_REINSTALL_raspberrypi0 = "no" 
BII_CROSS_SIGACTION_NEED_REINSTALL_raspberrypi0 = "no" 
BII_CROSS_HAVE_EXTENDED_SIGNALS_raspberrypi0 = "yes" 
BII_CROSS_SIGSEGV_SKIP_INSTRUCTION_raspberrypi0 = "yes"

BII_CROSS_SOCKLEN_T_raspberrypi0-wifi = "yes" 
BII_CROSS_BYTE_BITFIELDS_raspberrypi0-wifi = "yes" 
BII_CROSS_MMAP_ANON_raspberrypi0-wifi = "yes" 
BII_CROSS_MMAP_SUPPORTS_MAP_ANONYMOUS_raspberrypi0-wifi = "yes" 
BII_CROSS_MPROTECT_WORKS_raspberrypi0-wifi = "yes" 
BII_CROSS_SIGNAL_NEED_REINSTALL_raspberrypi0-wifi = "no" 
BII_CROSS_SIGACTION_NEED_REINSTALL_raspberrypi0-wifi = "no" 
BII_CROSS_HAVE_EXTENDED_SIGNALS_raspberrypi0-wifi = "yes" 
BII_CROSS_SIGSEGV_SKIP_INSTRUCTION_raspberrypi0-wifi = "yes"

# And export the values so that OECONF can pick them up.
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
EXTRA_OECONF_append_raspberrypi0 = " --disable-jit-compiler"
EXTRA_OECONF_append_raspberrypi0-wifi = " --disable-jit-compiler"

S = "${WORKDIR}/git/BasiliskII/src/Unix"

BII_BOOT_ENV ?= ""
BII_BOOT_COMMAND ?= "${bindir}/BasiliskII --config ${sysconfdir}/${PN}/preferences --rom ${sysconfdir}/${PN}/mac.rom --disk ${sysconfdir}/${PN}/mac.hd"

BII_BOOT_ENV_raspberrypi = "export SDL_VIDEODRIVER='dispmanx';"
BII_BOOT_ENV_raspberrypi0 = "export SDL_VIDEODRIVER='dispmanx';"
BII_BOOT_ENV_raspberrypi0-wifi = "export SDL_VIDEODRIVER='dispmanx';"

# Uncomment if you don't want to force raspberrypi audio to the headphone jack.
BII_BOOT_ENV_raspberrypi += "/usr/bin/amixer cset numid=3 1;"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/systemd-units/basilisk-ii.service ${D}${systemd_unitdir}/system
	
	install -d ${D}${base_bindir}	
	install -m 0755 ${WORKDIR}/basilisk-ii.sh ${D}${base_bindir}
	
	install -d ${D}${sysconfdir}/${PN}
	install -m 0644 ${WORKDIR}/user-files/* ${D}${sysconfdir}/${PN}
	
	# Append the boot command bits to the basilisk-ii.sh script in the base bindir.
	echo "${BII_BOOT_ENV}" >> ${D}${base_bindir}/basilisk-ii.sh
	echo "${BII_BOOT_COMMAND}" >> ${D}${base_bindir}/basilisk-ii.sh
}

FILES_${PN} =+ "/usr/bin/BasiliskII /usr/share/BasiliskII /usr/share/BasiliskII/* ${systemd_unitdir}/system/basilisk-ii.service" 
FILES_${PN}-bin =+ "/usr/share/BasiliskII /usr/share/BasiliskII/*"

SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_SERVICE_${PN} = "basilisk-ii.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"
