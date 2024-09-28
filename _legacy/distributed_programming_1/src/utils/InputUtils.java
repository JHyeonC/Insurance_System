package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtils {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString(String label) {
        while (true) {
            try {
                System.out.print(label);
                String str = reader.readLine();
                if (!str.equals("")) return str;
                System.out.println("입력이 비어있습니다, 다시 입력해주세요.");
            } catch (IOException e) {
                System.out.println("입력에 실패했습니다. 다시 입력해주세요.");
            }
        }
    }

    public static int readInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                int i = Integer.parseInt(reader.readLine());
                if (i < 1) throw new IOException();
                return i;
            } catch (IOException e) {
                System.out.println("입력에 실패했습니다. 다시 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    public static float readFloat(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Float.parseFloat(reader.readLine());
            } catch (IOException e) {
                System.out.println("입력에 실패했습니다. 다시 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("올바른 값을 입력해주세요.");
            }
        }
    }

    public static boolean readBoolean(String label) {
        while (true) {
            try {
                System.out.print(label + "(Y/N) : ");
                String input = reader.readLine();
                if (input.equals("Y")) return true;
                if (input.equals("N")) return false;
                System.out.println("Y혹은 N을 입력해주세요.");
            } catch (IOException e) {
                System.out.println("입력에 실패했습니다. 다시 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

}
