package insurance_system_employee_service.service;

import insurance_system_employee_service.jpa.claim.ClaimEntity;
import insurance_system_employee_service.jpa.claim.ClaimRepository;
import insurance_system_employee_service.jpa.employee.EmployeeEntity;
import insurance_system_employee_service.jpa.employee.EmployeeRepository;
import insurance_system_employee_service.service.vo.ClaimVO;
import insurance_system_employee_service.service.vo.Department;
import insurance_system_employee_service.service.vo.EmployeeVO;
import insurance_system_employee_service.service.vo.Status;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final EmployeeRepository employeeRepository;
    private final KieContainer kieContainer;

    public List<ClaimVO> getManagingClaims(EmployeeVO vo) {
        EmployeeEntity employee = employeeRepository.getEmployeeById(vo.getId());
        List<ClaimVO> claimVOS = new ArrayList<>();
        List<ClaimEntity> claimEntities;
        if(employee.getDepartment().equals(Department.SUPPORTING)) {
            claimEntities = claimRepository.getClaimsByReviewerId(vo.getId());
            for(ClaimEntity claim : claimEntities) {
                if(claim.getStatus().equals(Status.REVIEWING))
                    claimVOS.add(entityToVO(claim));
            }
        }
        else {
            claimEntities = claimRepository.getClaimsByInvestigatorId(vo.getId());
            for(ClaimEntity claim : claimEntities) {
                if(claim.getStatus().equals(Status.REPORTING))
                    claimVOS.add(entityToVO(claim));
            }
        }

        return claimVOS;
    }

    public boolean submitReport(ClaimVO claimVO) {
        if(claimVO.getReport().trim().isEmpty()) return false;
        ClaimEntity claimEntity = claimRepository.getClaimsById(claimVO.getId());
        ClaimVO claim = entityToVO(claimEntity);
        claim.setCompensation(claimVO.getCompensation());
        excuteRules(claim);
        claim.setReport(claimVO.getReport());
        claimRepository.save(vOToEntity(claim));
        return true;
    }

    public boolean reviewClaim(ClaimVO claimVO) {
        ClaimEntity claimEntity = claimRepository.getClaimsById(claimVO.getId());
        claimEntity.setStatus(Status.valueOf(claimVO.getStatus()));
        claimRepository.save(claimEntity);
        return true;
    }

    public List<ClaimVO> getClaimsByStatus(Status status) {
        List<ClaimVO> claimVOS = new ArrayList<>();
        List<ClaimEntity> claimEntities = claimRepository.getClaimsByStatus(status);;
        for(ClaimEntity claim : claimEntities) {
            claimVOS.add(entityToVO(claim));
        }
        return claimVOS;
    }

    public boolean payCompensation(ClaimVO claimVO) {
        ClaimEntity claimEntity = claimRepository.getClaimsById(claimVO.getId());
        if(!claimEntity.getStatus().equals( Status.ACCEPTED)) return false;
        ClaimVO claim = entityToVO(claimEntity);
        claim.setStatus("PAID");
        claimRepository.save(vOToEntity(claim));
        return true;
    }

    public Float getClaimStatistics() {
        List<ClaimEntity> clalims = claimRepository.getClaimsByStatus(Status.ACCEPTED);
        int sumCompensation = 0;
        for(ClaimEntity claim : clalims) {
            sumCompensation += claim.getCompensation();
        }
        return (float) sumCompensation/clalims.size();
    }

    public void excuteRules(ClaimVO claim) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(claim);
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    public ClaimVO entityToVO(ClaimEntity claim) {
        ClaimVO claimVO = new ClaimVO(claim.getId()
                , claim.getCompensation()
                , claim.getContract_id()
                , claim.getDate()
                , claim.getDescription()
                , claim.getInvestigator()
                , claim.getReviewer()
                , claim.getReport()
                , claim.getStatus().toString()
        );
        return claimVO;
    }

    public ClaimEntity vOToEntity(ClaimVO claim) {
        ClaimEntity claimEntity = new ClaimEntity(claim.getId()
                , claim.getCompensation()
                , claim.getContract_id()
                , claim.getDate()
                , claim.getDescription()
                , claim.getInvestigator()
                , claim.getReviewer()
                , claim.getReport()
                , claim.getStatus()
        );
        return claimEntity;
    }


}
