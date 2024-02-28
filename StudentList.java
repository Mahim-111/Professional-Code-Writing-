import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {

	public static void main(String[] args) {

		// Check arguments
		if (args == null || args.length != 1) {
			System.out.println(Constants.Arguments);
			return;
		}
		String fileContents = LoadData(Constants.StudentList);
		if (args[0].equals(Constants.ShowAll)) {
			System.out.println(Constants.Loading);
			String words[] = fileContents.split(Constants.StudentEntryDelimiter);
			for (String word : words) {
				System.out.println(word);
			}
			System.out.println(Constants.DataLoad);
		} else if (args[0].equals(Constants.ShowRandom)) {
			try {
				System.out.println(Constants.Loading);
				System.out.println(fileContents);
				String words[] = fileContents.split(Constants.StudentEntryDelimiter);
				Random random = new Random();
				int randomIndex = random.nextInt();
				System.out.println(words[randomIndex]);
			} catch (Exception e) {
			}
			System.out.println(Constants.DataLoad);
		} else if (args[0].contains(Constants.AddEntry)) {
			System.out.println(Constants.Loading);
			String argValue = args[0].substring(1);
			UpdateContent(argValue, Constants.StudentList);
			System.out.println(Constants.DataLoad);
		} else if (args[0].contains(Constants.FindEntry)) {
			System.out.println(Constants.Loading);
			String words[] = fileContents.split(Constants.StudentEntryDelimiter);
			boolean done = false;
			String argValue = args[0].substring(1);
			for (int index = 0; index < words.length && !done; index++) {
				if (words[index].equals(argValue)) {
					System.out.println(Constants.Found);
					done = true;
				}
			}
			System.out.println(Constants.DataLoad);
		} else if (args[0].contains(Constants.ShowCount)) {
			System.out.println(Constants.Loading);
			char characters[] = fileContents.toCharArray();
			boolean in_word = false;
			int count = 0;
			for (char character : characters) {
				if (character == ' ') {
					if (!in_word) {
						count++;
						in_word = true;
					} else {
						in_word = false;
					}
				}
			}
			System.out.println(count + Constants.WordFound + characters.length);
			System.out.println(Constants.DataLoad);
		}
	}

	public static String LoadData(String fileName) {
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			return fileReader.readLine();
		} catch (Exception e) {
		}
		return null;
	}

	public static void UpdateContent(String content, String fileName) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
			Date newDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat(Constants.DateStyle);
			fileWriter.write(
					Constants.StudentEntryDelimiter + content + Constants.UpdateContent + dateFormat.format(newDate));
			fileWriter.close();
		} catch (Exception e) {
		}
	}
}