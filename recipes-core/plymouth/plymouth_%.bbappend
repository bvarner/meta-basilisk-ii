FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://plymouth/plymouthd.conf"

do_configure_append() {
    cp -fp ${WORKDIR}/plymouth/plymouthd.conf ${S}/src/plymouthd.conf;
}