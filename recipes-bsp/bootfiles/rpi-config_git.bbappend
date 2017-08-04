
do_deploy_append() {
    echo "" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "## Enable Raspberrypi Audio" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "dtparam=audio=on" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "audio_pwm_mode=2" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "## Disable Rainbow Splash Screen" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "disable_splash=1" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "## Force turbo for a stable clock speed" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "force_turbo=1" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    echo "## Setup HDMI Resolution" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "hdmi_group=2" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "hdmi_mode=14" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
