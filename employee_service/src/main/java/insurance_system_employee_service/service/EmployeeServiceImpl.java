package insurance_system_employee_service.service;

import insurance_system_employee_service.vo.ClaimDTO;
import insurance_system_employee_service.vo.ClaimManageDTO;
import insurance_system_employee_service.vo.Department;
import insurance_system_employee_service.vo.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl {
    private final EmployeeRepository employeeRepository;
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;

    public Employee login(Employee employee){
        System.out.println(employee.id + " " + employee.password);
        String userPw = employeeRepository.getPassWordById(employee.getId());
        if(userPw == null || !employee.getPassword().equals(userPw)){
            return null;
        }
        return employeeRepository.getEmployeeById(employee.getId());
    }

    public Employee submitClaim(ClaimDTO claimDTO){
        List<Employee> investigators = employeeRepository.getEmployeesByDepartment(Department.INVESTIGATING);
        List<Employee> reviewers = employeeRepository.getEmployeesByDepartment(Department.SUPPORTING);
        Map<String, Integer> investigatingCount = new HashMap<>();
        Map<String, Integer> reviewingCount = new HashMap<>();
        for(Employee investigator : investigators) investigatingCount.put(investigator.getId(), 0);
        for(Employee reviewer : reviewers) reviewingCount.put(reviewer.getId(), 0);
        kafkaProducer.publish("topic", claimDTO.status);
        List<ClaimManageDTO> reportingClaims = kafkaConsumer.getReportingClaimList();
        for(ClaimManageDTO reportingClaim : reportingClaims){
            log.info("Claim_id = {}, investigator = {}, reviewer = {}",
                    reportingClaim.getClaim_id(), reportingClaim.getInvestigator(), reportingClaim.getReviewer());
            investigatingCount.replace(reportingClaim.getInvestigator(), investigatingCount.get(reportingClaim.getInvestigator()) + 1);
            reviewingCount.replace(reportingClaim.getReviewer(), reviewingCount.get(reportingClaim.getReviewer()) + 1);
        }
        String investigator = investigators.get(0).getId();
        String reviewer = reviewers.get(0).getId();
        int min = Integer.MAX_VALUE;
        for (String employeeId : investigatingCount.keySet()) {
            if (investigatingCount.get(employeeId) < min) {
                investigator = employeeId;
                min = investigatingCount.get(employeeId);
            }
        }
        for(String employeeId : reviewingCount.keySet()){
            if(reviewingCount.get(employeeId) < min){
                reviewer = employeeId;
                min = reviewingCount.get(employeeId);
            }
        }
        kafkaProducer.addClaim("topic3", claimDTO);
        kafkaProducer.addClaimManage("topic4", claimDTO.getId() + " " + investigator + " " + reviewer);
        return employeeRepository.getEmployeeById(investigator);
    }


    //    private static void showCustomerStat() throws RemoteException {
//        if (employee.department != Employee.Department.MARKETING) {
//            System.out.println("마케팅팀 직원만 선택할 수 있는 메뉴입니다.");
//            return;
//        }
//        System.out.println("***** 고객 통계 조회 *****");
//        List<Customer> customers = server.getCustomers();
//        float maleCount = 0;
//        float hasOccupationalHazardCount = 0;
//        float smokerCount = 0;
//        for (Customer customer : customers) {
//            if (customer.gender == 0) ++maleCount;
//            if (customer.occupationalHazard == 1) ++hasOccupationalHazardCount;
//            if (customer.smoking == 1) ++smokerCount;
//        }
//        float customerCount = customers.size();
//        System.out.println("성비 : 남 " + (maleCount / customerCount * 100) + "%, 여 " + ((customerCount - maleCount) / customerCount * 100) + "%");
//        System.out.println("위험직업 종사 여부 : 네 " + (hasOccupationalHazardCount / customerCount * 100) + "%, 아니요 " + ((customerCount - hasOccupationalHazardCount) / customerCount * 100) + "%");
//        System.out.println("흡연 여부 : 네 " + (smokerCount / customerCount * 100) + "%, 아니요 " + ((customerCount - smokerCount) / customerCount * 100) + "%");
//    }
}
