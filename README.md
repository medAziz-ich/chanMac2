# chanMac2
    chanMac2 is a Java-based graphical application that allows users to generate and change the MAC address of a specified     network interface on Linux systems. This tool is designed for educational purposes, particularly for students and professionals in network and systems engineering.

Features

    Generate Random MAC Addresses
    Create random MAC addresses for any network interface.

    Change MAC Address
    Modify the MAC address of a specified network interface with ease.

    GUI Interface
    A user-friendly graphical interface built with Java Swing for intuitive use.

Prerequisites

    Linux System
    This tool is intended for use on Linux-based systems.

    Java Runtime Environment (JRE)
    Ensure that Java 17 or later is installed.


 How It Works


    The user provides the name of the network interface (e.g., eth0, wlan0).
    The application prompts for the root password securely.
    The chanmac backend generates and sets a new MAC address for the specified interface.
    The new MAC address is displayed in the GUI.


Screenshots
     ![ch21](https://github.com/user-attachments/assets/d393270e-a778-476f-b4f8-3f2f763440e0)


Security Note

    The application uses sudo to execute backend commands. Make sure your sudo configuration is properly secured.
    Avoid running this tool in untrusted environments.
