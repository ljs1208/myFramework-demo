package jason.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassesUtil {

	private static ClassesUtil getClasses;
	
	private List<Class> classes = new ArrayList<Class>();

	public static ClassesUtil getInstence() {
		if (getClasses == null) {
			getClasses = new ClassesUtil();
		}
		try {
			getClasses.findClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getClasses;
	}

	public List<Class> findClass() throws Exception {
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
				.getResources("/");
		if (!urls.hasMoreElements()) {
			urls = Thread.currentThread().getContextClassLoader()
					.getResources(".");
		}
		List list = new ArrayList();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String path = url.getPath();
			File file = new File(path);
			getFile(file, null, list);

		}
		return list;

	}

	public void getFile(File file, String packageName, List<String> list) {

		String packageFile = "";
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File folder = files[i];
				if (folder.isDirectory()) {
					if (packageName == null && packageFile.trim().isEmpty()) {
						packageFile = folder.getName();
					} else {
						packageFile = packageName == null ? folder.getName()
								: packageName + "." + folder.getName();
					}
					getFile(folder, packageFile, list);
				} else {
					getFile(folder, packageName, list);
				}
			}
		} else {
			String fileName = file.getName();
			if (fileName.endsWith(".class")) {
				if (packageName != null) {
					fileName = packageName + "." + fileName;
				}

				String className = fileName.substring(0, fileName.length() - 6);
				try {
					//×ª»¯³ÉClass
					Class clazz = Class.forName(className);
					classes.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// list.add(className);
			}

		}

	}

	public List<Class> queryClasses() {
		return this.classes;
	}
}
