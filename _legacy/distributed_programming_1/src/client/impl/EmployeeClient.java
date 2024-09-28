package client.impl;

import utils.InputUtils;
import client.common.InsuranceClient;
import client.common.InsuranceMenu;
import common.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public class EmployeeClient extends InsuranceClient {

    private static Employee employee = null;

    public static void main(String[] args) throws RemoteException {
        EmployeeClient employeeClient = new EmployeeClient();
        if (employeeClient.login()) employeeClient.mainMenu();
    }

    @Override
    public boolean login() throws RemoteException {
        if (server == null) return false;
        while (employee == null) {
            System.out.println("***** 로그인 *****");
            String id = InputUtils.readString("ID: ");
            String pw = InputUtils.readString("PW: ");
            Employee employee = server.employeeLogin(id, pw);
            if (employee != null) this.employee = employee;
            else System.out.println("로그인에 실패했습니다, 다시 시도해주세요.");
        }
        return true;
    }

    @Override
    public void init() {
        contractFunctions.add(new InsuranceMenu("상품 가입", () -> developProduct()));
        contractFunctions.add(new InsuranceMenu("요율 결정", () -> decideRate()));
        contractFunctions.add(new InsuranceMenu("상품 조회", () -> showProductList()));
        marketingFunctions.add(new InsuranceMenu("문의 답변 작성", () -> answerBoard()));
        marketingFunctions.add(new InsuranceMenu("고객 통계 조회", () -> showCustomerStat()));
        compensationFunctions.add(new InsuranceMenu("조사보고서 제출", () -> reportClaim()));
        compensationFunctions.add(new InsuranceMenu("보상금 지급 심사", () -> evaluateClaim()));
        compensationFunctions.add(new InsuranceMenu("보상금 지급", () -> payCompensation()));
        compensationFunctions.add(new InsuranceMenu("제지급금 분석", () -> analysisExclusivePayout()));
        System.out.println(employee.name + "님, 환영합니다!");
    }



    ////////////// contract/////////////


    private static void analysisExclusivePayout() throws RemoteException {
        List<Claim> claims = server.getClaimsByStatus(Claim.Status.ACCEPTED);
        int sum =0;
        for(Claim claim : claims) {
            sum += claim.compensation;
        }
        System.out.println("제지급금 합계 : " + sum+" 원");
        if(sum != 0) System.out.println("제지급금 평균 : " + sum/claims.size()+" 원");
        else System.out.println("제지급금 평균 : 0 원");

    }
    private static void showProductList() throws RemoteException {
        int i =  InputUtils.readInt("1.전체 상품 조회   2. 아이디로 상품 조회");
        List<Product> products = server.getProducts();
        switch (i) {
            case 1 : Product.printHeader();
                for(Product product : products) {
                    product.print();
                    break;
                }
            case 2: String inputId = InputUtils.readString("상품의 아이디를 입력하여 주십시오");
                Product.printHeader();
                for(Product product : products) {
                    if(inputId.equals(product.id)) product.print();
                }

        }
    }
//////////////// contract /////////////



    private static void developProduct() throws RemoteException {
        if (employee.department != Employee.Department.DEVELOPMENT) {
            System.out.println("개발팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        String id = InputUtils.readString("새로운 상품의 아이디를 입력해주세요 : ");
        String name = InputUtils.readString("새로운 상품의 이름을 입력해주세요 : ");
        int premium = InputUtils.readInt("새로운 상품의 보험료를 입력해주세요 : ");
        if (server.developProduct(id, name, premium)) {
            System.out.println("개발 상품 등록이 완료되었습니다.");
            System.out.println("등록된 상품은 요율 계산 후에 공개됩니다.");
        } else System.out.println("이미 존재하는 상품입니다.");
    }

    private static void decideRate() throws RemoteException {
        if (employee.department != Employee.Department.UNDERWRITING) {
            System.out.println("언더라이팅팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        List<Product> products = server.getProducts();
        if (products == null) {
            System.out.println("상품 리스트를 불러오지 못했습니다.");
            return;
        }
        Product selectedProduct = null;
        while (selectedProduct == null) {
            Product.printHeader();
            for (Product product : products) if (product.released == 0) product.print();
            String selectedId = InputUtils.readString("요율을 책정하고자 하는 상품의 아이디를 입력해주세요 : ");
            for (Product product : products) if (selectedId.equals(product.id)) selectedProduct = product;
            if (selectedProduct == null) System.out.println("올바른 상품 아이디를 입력해주세요.");
        }
        selectedProduct.seniorRate = InputUtils.readFloat("고령 요율을 입력해주세요 : ");
        selectedProduct.maleRate = InputUtils.readFloat("남성 요율을 입력해주세요 : ");
        selectedProduct.femaleRate = InputUtils.readFloat("여성 요율을 입력해주세요 : ");
        selectedProduct.occupationalHazardRate = InputUtils.readFloat("위험 직업 요율을 입력해주세요 : ");
        selectedProduct.smokingRate = InputUtils.readFloat("흡연 요율을 입력해주세요 : ");
        selectedProduct.released = 1;
        if (server.updateProduct(selectedProduct)) {
            System.out.println("요율 책정에 성공했습니다.");
            System.out.println("상품이 공개되었습니다.");
        } else System.out.println("요율 책정에 실패했습니다.");
    }

    private static void reportClaim() throws RemoteException {
        if (employee.department != Employee.Department.INVESTIGATING) {
            System.out.println("보험조사팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        List<Claim> claims = server.getClaimsByInvestigator(employee.id);
        if (claims == null) {
            System.out.println("보험 청구 리스트를 불러오지 못했습니다.");
            return;
        }
        Claim selectedClaim = null;
        while (selectedClaim == null) {
            Claim.printHeader();
            for (Claim claim : claims) if (claim.status == Claim.Status.REPORTING) claim.print();
            String selectedId = InputUtils.readString("조사 보고서를 제출하고자 하는 청구의 아이디를 입력해주세요 : ");
            for (Claim claim : claims) if (selectedId.equals(claim.id)) selectedClaim = claim;
            if (selectedClaim == null) System.out.println("올바른 청구 아이디를 입력해주세요.");
        }
        String report = InputUtils.readString("조사 보고서를 입력해주세요 : ");
        int compensation = InputUtils.readInt("보험금을 입력해주세요 : ");
        selectedClaim.report = report;
        selectedClaim.compensation = compensation;
        selectedClaim.status = Claim.Status.REVIEWING;
        if (server.reportClaim(selectedClaim)) {
            System.out.println("조사 보고서가 성공적으로 제출되었습니다.");
            System.out.println("보험금 지급 심사가 요청되었습니다.");
        } else System.out.println("조사 보고서 제출에 실패했습니다.");
    }

    private static void evaluateClaim() throws RemoteException {
        if (employee.department != Employee.Department.SUPPORTING) {
            System.out.println("보험지원팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        List<Claim> claims = server.getClaimsByStatus(Claim.Status.REVIEWING);
        if (claims == null) {
            System.out.println("보험 청구 리스트를 불러오지 못했습니다.");
            return;
        }
        Claim selectedClaim = null;
        while (selectedClaim == null) {
            Claim.printHeader();
            for (Claim claim : claims) claim.print();
            String selectedId = InputUtils.readString("지급 심사를 하고자 하는 청구의 아이디를 입력해주세요 : ");
            for (Claim claim : claims) if (selectedId.equals(claim.id)) selectedClaim = claim;
            if (selectedClaim == null) System.out.println("올바른 청구 아이디를 입력해주세요.");
        }
        selectedClaim.reviewer = employee.id;
        selectedClaim.status = InputUtils.readBoolean("지급을 승인하시겠습니까?") ? Claim.Status.ACCEPTED : Claim.Status.REJECTED;
        if (server.evaluateClaim(selectedClaim)) System.out.println("보험금 지급 심사 결과가 저장되었습니다.");
        else System.out.println("보험금 지급 심사 결과 저장에 실패했습니다.");
    }

    private static void payCompensation() throws RemoteException {
        if (employee.department != Employee.Department.SUPPORTING) {
            System.out.println("보험지원팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        List<Claim> claims = server.getClaimsByStatus(Claim.Status.ACCEPTED);
        if (claims == null) {
            System.out.println("보험 청구 리스트를 불러오지 못했습니다.");
            return;
        }
        Claim selectedClaim = null;
        while (selectedClaim == null) {
            Claim.printHeader();
            for (Claim claim : claims) claim.print();
            String selectedId = InputUtils.readString("보상금을 지급하고자 하는 청구의 아이디를 입력해주세요 : ");
            for (Claim claim : claims) if (selectedId.equals(claim.id)) selectedClaim = claim;
            if (selectedClaim == null) System.out.println("올바른 청구 아이디를 입력해주세요.");
        }
        if (InputUtils.readBoolean("보상금을 지급하시겠습니까?")) {
            selectedClaim.status = Claim.Status.PAID;
            if (server.payCompensation(selectedClaim)) System.out.println("보상금이 지급되었습니다.");
            else System.out.println("보상금 지급에 실패했습니다.");
        } else System.out.println("보상금 지급이 유예되었습니다.");
    }

    private static void answerBoard() throws RemoteException {
        if (employee.department != Employee.Department.MARKETING) {
            System.out.println("마케팅팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        System.out.println("***** 문의 답변 *****");
        List<Board> boards = server.getBoards();
        Board selectedBoard = null;
        while (selectedBoard == null) {
            Board.printHeader();
            for (Board board : boards) if (board.isAnswered == 0) board.print();
            String selectedId = InputUtils.readString("조회하고자 하는 문의의 아이디를 입력해주세요 : ");
            for (Board board : boards) if (selectedId.equals(board.id)) selectedBoard = board;
            if (selectedBoard == null) System.out.println("올바른 문의 아이디를 입력해주세요.");
        }
        selectedBoard.answer = InputUtils.readString("답변을 입력해주세요 : ");
        selectedBoard.answerer = employee.id;
        selectedBoard.isAnswered = 1;
        if (server.updateBoard(selectedBoard)) System.out.println("문의 답변 작성이 완료되었습니다.");
        else System.out.println("문의 답변 작성에 실패했습니다.");
    }

    private static void showCustomerStat() throws RemoteException {
        if (employee.department != Employee.Department.MARKETING) {
            System.out.println("마케팅팀 직원만 선택할 수 있는 메뉴입니다.");
            return;
        }
        System.out.println("***** 고객 통계 조회 *****");
        List<Customer> customers = server.getCustomers();
        float maleCount = 0;
        float hasOccupationalHazardCount = 0;
        float smokerCount = 0;
        for (Customer customer : customers) {
            if (customer.gender == 0) ++maleCount;
            if (customer.occupationalHazard == 1) ++hasOccupationalHazardCount;
            if (customer.smoking == 1) ++smokerCount;
        }
        float customerCount = customers.size();
        System.out.println("성비 : 남 " + (maleCount / customerCount * 100) + "%, 여 " + ((customerCount - maleCount) / customerCount * 100) + "%");
        System.out.println("위험직업 종사 여부 : 네 " + (hasOccupationalHazardCount / customerCount * 100) + "%, 아니요 " + ((customerCount - hasOccupationalHazardCount) / customerCount * 100) + "%");
        System.out.println("흡연 여부 : 네 " + (smokerCount / customerCount * 100) + "%, 아니요 " + ((customerCount - smokerCount) / customerCount * 100) + "%");
    }

}
