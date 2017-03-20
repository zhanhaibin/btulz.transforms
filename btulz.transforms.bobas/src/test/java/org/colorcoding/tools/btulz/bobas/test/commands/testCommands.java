package org.colorcoding.tools.btulz.bobas.test.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.colorcoding.ibas.bobas.MyConfiguration;
import org.colorcoding.ibas.bobas.core.BOFactory;
import org.colorcoding.tools.btulz.bobas.Console;
import org.colorcoding.tools.btulz.bobas.commands.ClassLoder4bobas;
import org.colorcoding.tools.btulz.bobas.commands.Command4init;

import junit.framework.TestCase;

public class testCommands extends TestCase {

	public void testClassLoader() throws ClassNotFoundException, IOException {
		File folder = new File(MyConfiguration.getStartupFolder());
		folder = folder.getParentFile().getParentFile().getParentFile().getParentFile();
		String ifFolder = folder.getPath() + File.separator + "ibas.initialfantasy";
		String data = String.format("%s%sibas.initialfantasy%starget%sclasses", ifFolder, File.separator,
				File.separator, File.separator);
		ClassLoder4bobas loader = new ClassLoder4bobas();
		loader.setWorkFolder(data);
		for (Class<?> item : BOFactory.create().getKnownClasses("ibas.initialfantasy.bo")) {
			System.out.println(item.getName());
		}
		Class<?> type = loader.getClass("http://colorcoding.org/ibas/initialfantasy/bo/applicationmodule");
		assertNotNull("not found.", type);
		assertEquals("not found.", type.getSimpleName(), "ApplicationModule");
	}

	public void testInit() {
		File folder = new File(MyConfiguration.getStartupFolder());
		folder = folder.getParentFile().getParentFile().getParentFile().getParentFile();
		String ifFolder = folder.getPath() + File.separator + "ibas.initialfantasy";
		String config = String.format("%s%sibas.initialfantasy%sapp.xml", ifFolder, File.separator, File.separator);
		String data = String.format("%s%srelease%sibas.initialfantasy-0.0.1.jar", ifFolder, File.separator,
				File.separator);
		ArrayList<String> args = new ArrayList<>();
		args.add(String.format(Command4init.COMMAND_PROMPT)); // 命令
		args.add(String.format("-data=%s", data));
		args.add(String.format("-config=%s", config));
		Console.main(args.toArray(new String[] {}));
		data = String.format("%s%sibas.initialfantasy%starget%sclasses%sinitialization", ifFolder, File.separator,
				File.separator, File.separator, File.separator);
		args = new ArrayList<>();
		args.add(String.format(Command4init.COMMAND_PROMPT)); // 命令
		args.add(String.format("-data=%s", data));
		args.add(String.format("-config=%s", config));
		Console.main(args.toArray(new String[] {}));
	}
}
