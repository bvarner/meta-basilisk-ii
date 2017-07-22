# BasiliskII OpenEmbedded Layer
This is a yocto project layer for building the [Basilisk-II, 68k Macintosh emulator](https://github.com/cebix/macemu/tree/master/BasiliskII).

This layer has been developed against the Morty branch (release 2.2) of of the [Yocto project](https://www.yoctoproject.org/).

The layer is currently "in development", and is not producing what I'd consider a 'finished project'.

For the most part, I'm targeting a `MACHINE="raspberrypi"` in a cross-compiled context from an "x86_64" host.
 
## Usage (for Yocto novices)

Follow your distribution instructions for prepping for a yocto install.

Once you've got the prerequisites out of the way, we'll get a structure setup to do builds in.

I've taken most of the information here, from the [excellent writeup from jumpnowtek](http://www.jumpnowtek.com/rpi/Raspberry-Pi-Systems-with-Yocto.html) on building Raspberry-Pi systems with Yocto.
Some of the things I'll be doing below here are based upon his instructions, in a slightly more condensed form.


* Create a directory to hold all the bits.
    There's going to be a lot of data in this directory, so put it in a place where your desktop search won't be indexing things.
* Get the main yocto project source poky repository, morty branch.
   `git clone -b morty git://git.yoctoproject.org/poky.git poky-morty`
* Inside of that directory, we'll get the main open-embedded repo, meta-raspberrypi, and meta-basilisk-ii layers.
    ```
    cd poky-morty
    git clone -b morty git://git.openembedded.org/meta-openembedded
    git clone -b morty git://git.yoctoproject.org/meta-raspberrypi
    git clone -b morty https://github.com/bvarner/meta-basilisk-ii.git
    ```
* Move back up to the original directory you cloned poky-morty into, and initialize a build directory. 
    Yocto will create the build directory (and other things it needs) if the directory does not already exist.
    
    ```
    cd ..
    source poky-morty/oe-init-build-env
    ```

    Assuming you've executed this in a directory `~/yocto-projects`, your filesystem will look like this:

    ```
    ~/yocto-projects/
                     poky-morty/
                                meta-openembedded/
                                meta-raspberrypi/
                                meta-basilisk-ii/
                                ... (a bunch of other things from poky)
                     build/
                           conf/
                                bblayers.conf
                                local.conf
    ```
* Customize your configuration filles.
    The bblayers.conf and local.conf in the `build/conf` directory. There's a couple of samples in the basilisk-meta/conf
    directory you can use as a good starting point. Since I'm typically building images for a raspberrypi, that's what 
    the samples will reflect.
    
