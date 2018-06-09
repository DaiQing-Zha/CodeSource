package com.pay.sdk.alan.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AntiEmulator {
	private static String[] known_pipes = { "/dev/socket/qemud",
			"/dev/qemu_pipe" };

	private static String[] known_qemu_drivers = { "goldfish" };

	private static String[] known_files = {
			"/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace",
			"/system/bin/qemu-props",
			"/data/app/com.bluestacks.appmart-1.apk",
			"/data/app/com.bluestacks.BstCommandProcessor-1.apk",
			"/data/app/com.bluestacks.help-1.apk",
			"/data/app/com.bluestacks.home-1.apk",
			"/data/app/com.bluestacks.s2p-1.apk",
			"/data/app/com.bluestacks.searchapp-1.apk",
			"/data/bluestacks.prop",
			"/data/data/com.androVM.vmconfig",
			"/data/data/com.bluestacks.accelerometerui",
			"/data/data/com.bluestacks.appfinder",
			"/data/data/com.bluestacks.appmart",
			"/data/data/com.bluestacks.appsettings",
			"/data/data/com.bluestacks.BstCommandProcessor",
			"/data/data/com.bluestacks.bstfolder",
			"/data/data/com.bluestacks.help",
			"/data/data/com.bluestacks.home",
			"/data/data/com.bluestacks.s2p"
			,"/data/data/com.bluestacks.searchapp"
			,"/data/data/com.bluestacks.settings"
			,"/data/data/com.bluestacks.setup"
			,"/data/data/com.bluestacks.spotlight"
			,"/data/youwave_id"
			,"/dev/vboxguest"
			,"/dev/vboxuser"
			,"/fstab.vbox86"
			,"/init.vbox86.rc"
			,"/mnt/prebundledapps/bluestacks.prop.orig"
			,"/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note"
			,"/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s2"
			,"/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s3"
			,"/mnt/sdcard/bstfolder/InputMapper/com.bluestacks.appmart.cfg"
			,"/mnt/sdcard/buildroid-gapps-ics-20120317-signed.tgz"
			,"/mnt/sdcard/windows/InputMapper/com.bluestacks.appmart.cfg"
			,"/proc/irq/9/vboxguest"
			,"/sys/bus/pci/drivers/vboxguest"
			,"/sys/bus/pci/drivers/vboxguest/0000:00:04.0"
			,"/sys/bus/pci/drivers/vboxguest/bind"
			,"/sys/bus/pci/drivers/vboxguest/module"
			,"/sys/bus/pci/drivers/vboxguest/new_id"
			,"/sys/bus/pci/drivers/vboxguest/remove_id"
			,"/sys/bus/pci/drivers/vboxguest/uevent"
			,"/sys/bus/pci/drivers/vboxguest/unbind"
			,"/sys/bus/platform/drivers/qemu_pipe"
			,"/sys/bus/platform/drivers/qemu_trace"
			,"/sys/class/bdi/vboxsf-c"
			,"/sys/class/misc/vboxguest"
			,"/sys/class/misc/vboxuser"
			,"/sys/devices/virtual/bdi/vboxsf-c"
			,"/sys/devices/virtual/misc/vboxguest"
			,"/sys/devices/virtual/misc/vboxguest/dev"
			,"/sys/devices/virtual/misc/vboxguest/power"
			,"/sys/devices/virtual/misc/vboxguest/subsystem"
			,"/sys/devices/virtual/misc/vboxguest/uevent"
			,"/sys/devices/virtual/misc/vboxuser"
			,"/sys/devices/virtual/misc/vboxuser/dev"
			,"/sys/devices/virtual/misc/vboxuser/power"
			,"/sys/devices/virtual/misc/vboxuser/subsystem"
			,"/sys/devices/virtual/misc/vboxuser/uevent"
			,"/sys/module/vboxguest"
			,"/sys/module/vboxguest/coresize"
			,"/sys/module/vboxguest/drivers"
			,"/sys/module/vboxguest/drivers/pci:vboxguest"
			,"/sys/module/vboxguest/holders"
			,"/sys/module/vboxguest/holders/vboxsf"
			,"/sys/module/vboxguest/initsize"
			,"/sys/module/vboxguest/initstate"
			,"/sys/module/vboxguest/notes"
			,"/sys/module/vboxguest/notes/.note.gnu.build-id"
			,"/sys/module/vboxguest/parameters"
			,"/sys/module/vboxguest/parameters/log"
			,"/sys/module/vboxguest/parameters/log_dest"
			,"/sys/module/vboxguest/parameters/log_flags"
			,"/sys/module/vboxguest/refcnt"
			,"/sys/module/vboxguest/sections"
			,"/sys/module/vboxguest/sections/.altinstructions"
			,"/sys/module/vboxguest/sections/.altinstr_replacement"
			,"/sys/module/vboxguest/sections/.bss"
			,"/sys/module/vboxguest/sections/.data"
			,"/sys/module/vboxguest/sections/.devinit.data"
			,"/sys/module/vboxguest/sections/.exit.text"
			,"/sys/module/vboxguest/sections/.fixup"
			,"/sys/module/vboxguest/sections/.gnu.linkonce.this_module"
			,"/sys/module/vboxguest/sections/.init.text"
			,"/sys/module/vboxguest/sections/.note.gnu.build-id"
			,"/sys/module/vboxguest/sections/.rodata"
			,"/sys/module/vboxguest/sections/.rodata.str1.1"
			,"/sys/module/vboxguest/sections/.smp_locks"
			,"/sys/module/vboxguest/sections/.strtab"
			,"/sys/module/vboxguest/sections/.symtab"
			,"/sys/module/vboxguest/sections/.text"
			,"/sys/module/vboxguest/sections/__ex_table"
			,"/sys/module/vboxguest/sections/__ksymtab"
			,"/sys/module/vboxguest/sections/__ksymtab_strings"
			,"/sys/module/vboxguest/sections/__param"
			,"/sys/module/vboxguest/srcversion"
			,"/sys/module/vboxguest/taint"
			,"/sys/module/vboxguest/uevent"
			,"/sys/module/vboxguest/version"
			,"/sys/module/vboxsf"
			,"/sys/module/vboxsf/coresize"
			,"/sys/module/vboxsf/holders"
			,"/sys/module/vboxsf/initsize"
			,"/sys/module/vboxsf/initstate"
			,"/sys/module/vboxsf/notes"
			,"/sys/module/vboxsf/notes/.note.gnu.build-id"
			,"/sys/module/vboxsf/refcnt"
			,"/sys/module/vboxsf/sections"
			,"/sys/module/vboxsf/sections/.bss"
			,"/sys/module/vboxsf/sections/.data"
			,"/sys/module/vboxsf/sections/.exit.text"
			,"/sys/module/vboxsf/sections/.gnu.linkonce.this_module"
			,"/sys/module/vboxsf/sections/.init.text"
			,"/sys/module/vboxsf/sections/.note.gnu.build-id"
			,"/sys/module/vboxsf/sections/.rodata"
			,"/sys/module/vboxsf/sections/.rodata.str1.1"
			,"/sys/module/vboxsf/sections/.smp_locks"
			,"/sys/module/vboxsf/sections/.strtab"
			,"/sys/module/vboxsf/sections/.symtab"
			,"/sys/module/vboxsf/sections/.text"
			,"/sys/module/vboxsf/sections/__bug_table"
			,"/sys/module/vboxsf/sections/__param"
			,"/sys/module/vboxsf/srcversion"
			,"/sys/module/vboxsf/taint"
			,"/sys/module/vboxsf/uevent"
			,"/sys/module/vboxsf/version"
			,"/sys/module/vboxvideo"
			,"/sys/module/vboxvideo/coresize"
			,"/sys/module/vboxvideo/holders"
			,"/sys/module/vboxvideo/initsize"
			,"/sys/module/vboxvideo/initstate"
			,"/sys/module/vboxvideo/notes"
			,"/sys/module/vboxvideo/notes/.note.gnu.build-id"
			,"/sys/module/vboxvideo/refcnt"
			,"/sys/module/vboxvideo/sections"
			,"/sys/module/vboxvideo/sections/.data"
			,"/sys/module/vboxvideo/sections/.exit.text"
			,"/sys/module/vboxvideo/sections/.gnu.linkonce.this_module"
			,"/sys/module/vboxvideo/sections/.init.text"
			,"/sys/module/vboxvideo/sections/.note.gnu.build-id"
			,"/sys/module/vboxvideo/sections/.rodata.str1.1"
			,"/sys/module/vboxvideo/sections/.strtab"
			,"/sys/module/vboxvideo/sections/.symtab"
			,"/sys/module/vboxvideo/sections/.text"
			,"/sys/module/vboxvideo/srcversion"
			,"/sys/module/vboxvideo/taint"
			,"/sys/module/vboxvideo/uevent"
			,"/sys/module/vboxvideo/version"
			,"/system/app/bluestacksHome.apk"
			,"/system/bin/androVM-prop"
			,"/system/bin/androVM-vbox-sf"
			,"/system/bin/androVM_setprop"
			,"/system/bin/get_androVM_host"
			,"/system/bin/mount.vboxsf"
			,"/system/etc/init.androVM.sh"
			,"/system/etc/init.buildroid.sh"
			,"/system/lib/hw/audio.primary.vbox86.so"
			,"/system/lib/hw/camera.vbox86.so"
			,"/system/lib/hw/gps.vbox86.so"
			,"/system/lib/hw/gralloc.vbox86.so"
			,"/system/lib/hw/sensors.vbox86.so"
			,"/system/lib/modules/3.0.8-android-x86+/extra/vboxguest"
			,"/system/lib/modules/3.0.8-android-x86+/extra/vboxguest/vboxguest.ko"
			,"/system/lib/modules/3.0.8-android-x86+/extra/vboxsf"
			,"/system/lib/modules/3.0.8-android-x86+/extra/vboxsf/vboxsf.ko"
			,"/system/lib/vboxguest.ko"
			,"/system/lib/vboxsf.ko"
			,"/system/lib/vboxvideo.ko"
			,"/system/usr/idc/androVM_Virtual_Input.idc"
			,"/system/usr/keylayout/androVM_Virtual_Input.kl"
			,"/system/xbin/mount.vboxsf"
			,"/ueventd.android_x86.rc"
			,"/ueventd.vbox86.rc"		
	};

	private static String[] known_numbers = { "15555215554", "15555215556",
			"15555215558", "15555215560", "15555215562", "15555215564",
			"15555215566", "15555215568", "15555215570", "15555215572",
			"15555215574", "15555215576", "15555215578", "15555215580",
			"15555215582", "15555215584", };

	private static String[] known_device_ids = { "000000000000000" // 默认ID
	};

	private static String[] known_imsi_ids = { "310260000000000" // 默认的 imsi id
	};

	// 检测“/dev/socket/qemud”，“/dev/qemu_pipe”这两个通道
	public static boolean checkPipes() {
		for (int i = 0; i < known_pipes.length; i++) {
			String pipes = known_pipes[i];
			File qemu_socket = new File(pipes);
			if (qemu_socket.exists()) {
				Log.v("Result:", "Find pipes!");
				return true;
			}
		}
		Log.i("Result:", "Not Find pipes!");
		return false;
	}

	// 检测驱动文件内容
	// 读取文件内容，然后检查已知QEmu的驱动程序的列表
	public static Boolean checkQEmuDriverFile() {
		File driver_file = new File("/proc/tty/drivers");
		if (driver_file.exists() && driver_file.canRead()) {
			byte[] data = new byte[1024]; // (int)driver_file.length()
			try {
				InputStream inStream = new FileInputStream(driver_file);
				inStream.read(data);
				inStream.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			String driver_data = new String(data);
			for (String known_qemu_driver : AntiEmulator.known_qemu_drivers) {
				if (driver_data.indexOf(known_qemu_driver) != -1) {
					Log.i("Result:", "Find know_qemu_drivers!");
					return true;
				}
			}
		}
		Log.i("Result:", "Not Find known_qemu_drivers!");
		return false;
	}

	// 检测模拟器上特有的几个文件
	public static Boolean CheckEmulatorFiles() {
		for (int i = 0; i < known_files.length; i++) {
			String file_name = known_files[i];
			File qemu_file = new File(file_name);
			if (qemu_file.exists()) {
				Log.v("Result:", "Find Emulator Files!");
				return true;
			}
		}
		Log.v("Result:", "Not Find Emulator Files!");
		return false;
	}

	// 检测模拟器默认的电话号码
	public static Boolean CheckPhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String phonenumber = telephonyManager.getLine1Number();

		for (String number : known_numbers) {
			if (number.equalsIgnoreCase(phonenumber)) {
				Log.v("Result:", "Find PhoneNumber!");
				return true;
			}
		}
		Log.v("Result:", "Not Find PhoneNumber!");
		return false;
	}

	// 检测设备IDS 是不是 “000000000000000”
	public static Boolean CheckDeviceIDS(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String device_ids = telephonyManager.getDeviceId();

		for (String know_deviceid : known_device_ids) {
			if (know_deviceid.equalsIgnoreCase(device_ids)) {
				Log.v("Result:", "Find ids: 000000000000000!");
				return true;
			}
		}
		Log.v("Result:", "Not Find ids: 000000000000000!");
		return false;
	}

	// 检测imsi id是不是“310260000000000”
	public static Boolean CheckImsiIDS(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String imsi_ids = telephonyManager.getSubscriberId();

		for (String know_imsi : known_imsi_ids) {
			if (know_imsi.equalsIgnoreCase(imsi_ids)) {
				Log.v("Result:", "Find imsi ids: 310260000000000!");
				return true;
			}
		}
		Log.v("Result:", "Not Find imsi ids: 310260000000000!");
		return false;
	}

	// 检测手机上的一些硬件信息
	public static Boolean CheckEmulatorBuild(Context context) {
		String BOARD = android.os.Build.BOARD;
		String BOOTLOADER = android.os.Build.BOOTLOADER;
		String BRAND = android.os.Build.BRAND;
		String DEVICE = android.os.Build.DEVICE;
		String HARDWARE = android.os.Build.HARDWARE;
		String MODEL = android.os.Build.MODEL;
		String PRODUCT = android.os.Build.PRODUCT;
		if (BOARD == "unknown" || BOOTLOADER == "unknown" || BRAND == "generic"
				|| DEVICE == "generic" || MODEL == "sdk" || PRODUCT == "sdk"
				|| HARDWARE == "goldfish") {
			Log.v("Result:", "Find Emulator by EmulatorBuild!");
			return true;
		}
		Log.v("Result:", "Not Find Emulator by EmulatorBuild!");
		return false;
	}

	// 检测手机运营商家
	public static boolean CheckOperatorNameAndroid(Context context) {
		String szOperatorName = ((TelephonyManager) context
				.getSystemService("phone")).getNetworkOperatorName();

		if (szOperatorName.toLowerCase().equals("android") == true) {
			Log.v("Result:", "Find Emulator by OperatorName!");
			return true;
		}
		Log.v("Result:", "Not Find Emulator by OperatorName!");
		return false;
	}
}
