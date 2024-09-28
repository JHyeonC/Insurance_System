package client.impl;

import utils.InputUtils;
import client.common.InsuranceClient;
import client.common.InsuranceMenu;
import common.*;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CustomerClient extends InsuranceClient {

    private Customer customer = null;

    public static void main(String[] args) throws RemoteException {
        CustomerClient customerClient = new CustomerClient();
        if (customerClient.login()) customerClient.mainMenu();
    }

    @Override
    public boolean login() throws RemoteException {
        if (server == null) return false;
        while (customer == null) {
            System.out.println("***** 로그인 *****");
            String id = InputUtils.readString("ID: ");
            String pw = InputUtils.readString("PW: ");
            Customer customer = server.customerLogin(id, pw);
            if (customer != null) this.customer = customer;
            else System.out.println("로그인에 실패했습니다, 다시 시도해주세요.");
        }
        return true;
    }

    @Override
    public void init() {
        contractFunctions.add(new InsuranceMenu("상품 가입", () -> registerContract()));
        marketingFunctions.add(new InsuranceMenu("문의 작성", () -> writeBoard()));
        marketingFunctions.add(new InsuranceMenu("문의 조회", () -> listBoards()));
        compensationFunctions.add(new InsuranceMenu("보상금 청구", () -> submitClaim()));
        System.out.println(customer.name + "님, 환영합니다!");
    }

    private void registerContract() throws RemoteException {
        System.out.println("***** 상품 가입 *****");
        List<Product> products = server.getProducts();
        if (products == null) {
            System.out.println("상품을 불러오지 못했습니다.\n나중에 다시 시도해주세요.");
            return;
        }
        Product selectedProduct = null;
        while (selectedProduct == null) {
            Product.printHeader();
            for (Product product : products) if (product.released == 1) product.print();
            String selectedId = InputUtils.readString("가입하고자 하는 상품의 아이디를 입력해주세요 : ");
            for (Product product : products) if (selectedId.equals(product.id)) selectedProduct = product;
            if (selectedProduct == null) System.out.println("올바른 상품 아이디를 입력해주세요.");
        }
        int premium = selectedProduct.premium;
        if (2023 - Integer.parseInt(customer.birth.split("-")[0]) > 60) premium *= selectedProduct.seniorRate;
        if (customer.gender == 0) premium *= selectedProduct.maleRate;
        if (customer.gender == 1) premium *= selectedProduct.femaleRate;
        if (customer.occupationalHazard == 1) premium *= selectedProduct.occupationalHazardRate;
        if (customer.smoking == 1) premium *= selectedProduct.smokingRate;
        System.out.println("책정된 보험료는 다음과 같습니다 : " + premium);
        if (InputUtils.readBoolean("상품에 가입하시겠습니까? ")) {
            if (server.registerProduct(customer.id, selectedProduct.id, premium)) {
                System.out.println("상품 가입에 성공했습니다.");
            } else System.out.println("이미 가입된 상품입니다.");
        } else System.out.println("상품 가입을 거절하셨습니다.");
    }

    private void submitClaim() throws RemoteException {
        System.out.println("***** 보상금 청구 *****");
        List<Contract> contracts = server.getContractsByCustomerId(customer.id);
        if (contracts == null) {
            System.out.println("계약을 불러오지 못했습니다.\n나중에 다시 시도해주세요.");
            return;
        }
        Contract selectedContract = null;
        while (selectedContract == null) {
            Contract.printHeader();
            for (Contract contract : contracts) contract.print();
            String selectedId = InputUtils.readString("보상금을 청구하고자 하는 상품의 아이디를 입력해주세요 : ");
            for (Contract contract : contracts) if (selectedId.equals(contract.productId)) selectedContract = contract;
            if (selectedContract == null) System.out.println("올바른 상품 아이디를 입력해주세요.");
        }
        String description = InputUtils.readString("보상금 청구 내용을 입력해주세요 : ");
        Claim claim = new Claim();
        claim.id = UUID.randomUUID().toString();
        claim.compensation = 0;
        claim.customerId = selectedContract.customerId;
        claim.productId = selectedContract.productId;
        claim.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        claim.description = description;
        claim.investigator = null;
        claim.report = "null";
        claim.reviewer = "null";
        claim.status = Claim.Status.REPORTING;
        Employee investigator = server.submitClaim(claim);
        if (investigator != null) {
            System.out.println("보상금 청구가 완료되었습니다. 담당 조사관은 아래와 같습니다.");
            investigator.print();
        } else System.out.println("보상금 청구에 실패했습니다.");
    }

    private void writeBoard() throws RemoteException {
        System.out.println("***** 문의 작성 *****");
        String title = InputUtils.readString("문의 제목을 입력해주세요 : ");
        String content = InputUtils.readString("문의 내용을 입력해주세요 : ");
        Board board = new Board();
        board.id = UUID.randomUUID().toString();
        board.author = customer.id;
        board.title = title;
        board.content = content;
        board.answer = "null";
        board.answerer = "null";
        board.isAnswered = 0;
        if (server.writeBoard(board)) System.out.println("문의가 성공적으로 작성되었습니다.");
        else System.out.println("문의 작성에 실패했습니다.");
    }

    private void listBoards() throws RemoteException {
        System.out.println("***** 문의 조회 *****");
        List<Board> boards = server.getBoards();
        Board selectedBoard = null;
        while (selectedBoard == null) {
            Board.printHeader();
            for (Board board : boards) board.print();
            String selectedId = InputUtils.readString("조회하고자 하는 문의의 아이디를 입력해주세요 : ");
            for (Board board : boards) if (selectedId.equals(board.id)) selectedBoard = board;
            if (selectedBoard == null) System.out.println("올바른 문의 아이디를 입력해주세요.");
        }
        selectedBoard.printDetails();
    }

}
