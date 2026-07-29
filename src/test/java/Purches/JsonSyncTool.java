package Purches;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.io.File;
import java.util.*;

public class JsonSyncTool {

    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {

        	 System.out.println("Enter your Original Json file");
         	String original=scanner.nextLine().trim();
         	System.out.println("Enter your Sample json file");
         	String sample=scanner.nextLine().trim();
         	System.out.println("Enter your new json file name like sivamani.json");
         	String outputPath=scanner.nextLine().trim();
        	
       // String oldPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\StartUpJsonfiles.json";
         //  String newPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\Sample_StartUpJson_Appu4Series_v5.1.5.json";
           // String outputPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\Updated_StartUpJson.json";

            JsonNode oldJson = mapper.readTree(new File(original));
            JsonNode newJson = mapper.readTree(new File(sample));

            // 🔥 Preserve exact structure from JSON-2
            ObjectNode updatedJson = newJson.deepCopy();

            Map<Integer, String[]> extraParams = new LinkedHashMap<>();
            Map<Integer, String[]> obsoleteParams = new LinkedHashMap<>();

            int counter = 1;

            Iterator<String> sections = newJson.fieldNames();

            while (sections.hasNext()) {

                String section = sections.next();

                if (!newJson.get(section).isArray()) continue;
                if (!oldJson.has(section)) continue;

                JsonNode newArray = newJson.get(section);
                JsonNode oldArray = oldJson.get(section);

                if (newArray.size() == 0 || oldArray.size() == 0) continue;

                ObjectNode updatedSection =
                        (ObjectNode) updatedJson.get(section).get(0);

                JsonNode newObject = newArray.get(0);
                JsonNode oldObject = oldArray.get(0);

                Iterator<String> keys = newObject.fieldNames();

                while (keys.hasNext()) {

                    String key = keys.next();

                    if (oldObject.has(key)) {
                        updatedSection.set(key, oldObject.get(key));
                    } else {
                        extraParams.put(counter++, new String[]{section, key});
                    }
                }

                Iterator<String> oldKeys = oldObject.fieldNames();

                while (oldKeys.hasNext()) {

                    String key = oldKeys.next();

                    if (!newObject.has(key)) {
                        obsoleteParams.put(counter++, new String[]{section, key});
                    }
                }
            }

            // ============ HANDLE EXTRA ============
            if (!extraParams.isEmpty()) {

                System.out.println("\nExtra Parameters:");
                extraParams.forEach((k, v) ->
                        System.out.println(k + " -> " + v[0] + " : " + v[1]));

                System.out.println("\nAdd all? (y/n)");
                String choice = scanner.nextLine().toLowerCase();

                if (!(choice.equals("y") || choice.equals("yes"))) {

                    System.out.println("Enter  the parameter numbers: ex: 1,4,5,67,...");
                    String input = scanner.nextLine();

                    for (String num : input.split(",")) {

                        int n = Integer.parseInt(num.trim());

                        if (extraParams.containsKey(n)) {

                            String section = extraParams.get(n)[0];
                            String key = extraParams.get(n)[1];

                            ObjectNode sec =
                                    (ObjectNode) updatedJson.get(section).get(0);

                            System.out.println("Keep default for "
                                    + key + "? (y/n)");

                            String ans = scanner.nextLine();

                            if (!ans.equalsIgnoreCase("y")) {

                                System.out.println("Enter custom value:");
                                String val = scanner.nextLine();

                                sec.set(key, parseValue(val));
                            }
                        }
                    }
                }
            }

            // ============ HANDLE OBSOLETE ============
            if (!obsoleteParams.isEmpty()) {

                System.out.println("\nObsolete Parameters:");
                obsoleteParams.forEach((k, v) ->
                        System.out.println(k + " -> " + v[0] + " : " + v[1]));

                System.out.println("\nRemove all? (y/n)");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("y") || choice.equals("yes")) {

                    obsoleteParams.forEach((k, v) -> {
                        ObjectNode sec =
                                (ObjectNode) updatedJson.get(v[0]).get(0);
                        sec.remove(v[1]);
                    });

                } else {

                    System.out.println("Enter numbers to remove:");
                    String input = scanner.nextLine();

                    for (String num : input.split(",")) {

                        int n = Integer.parseInt(num.trim());

                        if (obsoleteParams.containsKey(n)) {

                            String section = obsoleteParams.get(n)[0];
                            String key = obsoleteParams.get(n)[1];

                            ObjectNode sec =
                                    (ObjectNode) updatedJson.get(section).get(0);

                            sec.remove(key);
                        }
                    }
                }
            }

            // ============ VS CODE STYLE FORMAT ============
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            DefaultIndenter indenter = new DefaultIndenter("    ", "\n");

            printer.indentObjectsWith(indenter);
            printer.indentArraysWith(indenter);

            mapper.writer(printer)
                    .writeValue(new File(outputPath), updatedJson);

            System.out.println("\nUpdated the new json file with good formate.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Type-safe parser
    static JsonNode parseValue(String input) {

        if (input.equalsIgnoreCase("true")
                || input.equalsIgnoreCase("false")) {
            return BooleanNode.valueOf(Boolean.parseBoolean(input));
        }

        try {
            if (input.contains(".")) {
                return DoubleNode.valueOf(Double.parseDouble(input));
            } else {
                return IntNode.valueOf(Integer.parseInt(input));
            }
        } catch (Exception e) {
            return TextNode.valueOf(input);
        }
    }
}