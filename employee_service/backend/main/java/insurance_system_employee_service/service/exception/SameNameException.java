package insurance_system_employee_service.service.exception;

import insurance_system_employee_service.service.vo.ProductVO;

public class SameNameException extends Exception{
    public SameNameException(String inputName , String originId) {
        String errorMessage = inputName+"과 기존 DB의 [ID : " +originId+"]의 이름이 같습니다.";
    }

    public ProductVO sameProductIsAlreadyExist() {
        ProductVO productVO = new ProductVO("SameNameException",  (float) -1, (float) -1, (float) -1, (float) -1, (float) -1 );
        return productVO;
    }
}
