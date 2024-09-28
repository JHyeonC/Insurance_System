package client.common;

import utils.InputUtils;
import utils.RMIUtils;
import common.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class InsuranceClient {

    protected static final String HOST = "localhost";
    protected static final int PORT = 40051;
    protected static final String NAME = "SERVER";
    protected static Server server = null;
    protected static List<InsuranceMenu> contractFunctions;
    protected static List<InsuranceMenu> marketingFunctions;
    protected static List<InsuranceMenu> compensationFunctions;

    public InsuranceClient() {
        server = RMIUtils.connect(HOST, PORT, NAME);
        contractFunctions = new ArrayList<>();
        marketingFunctions = new ArrayList<>();
        compensationFunctions = new ArrayList<>();
    }

    public void mainMenu() throws RemoteException {
        init();
        while (true) {
            System.out.println("***** 메인 메뉴 *****\n" + "1. 계약\n" + "2. 마케팅\n" + "3. 보상\n" + "4. 종료");
            switch (InputUtils.readInt("번호를 선택해주세요 : ")) {
                case 1 -> showMenu("계약 메뉴", contractFunctions);
                case 2 -> showMenu("마케팅 메뉴", marketingFunctions);
                case 3 -> showMenu("보상 메뉴", compensationFunctions);
                case 4 -> exit();
                default -> System.out.println("잘못된 번호입니다, 다시 입력해주세요.");
            }
        }
    }

    public void showMenu(String menuName, List<InsuranceMenu> menuList) throws RemoteException {
        while (true) {
            System.out.println("***** " + menuName + " *****");
            for (int i = 0; i < menuList.size(); i++)
                System.out.println((i + 1) + ". " + menuList.get(i).name);
            System.out.println((menuList.size() + 1) + ". 뒤로가기");
            int selectedIndex = InputUtils.readInt("번호를 선택해주세요 : ");
            if (selectedIndex > 0 && selectedIndex < menuList.size() + 1)
                menuList.get(selectedIndex - 1).function.function();
            else if (selectedIndex == menuList.size() + 1) return;
            else System.out.println("잘못된 번호입니다, 다시 입력해주세요.");
        }
    }

    private void exit() {
        System.out.println("이용해주셔서 감사합니다.");
        System.exit(0);
    }

    public abstract boolean login() throws RemoteException;

    public abstract void init();

}
