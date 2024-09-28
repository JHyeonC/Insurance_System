export const HOST = 'http://localhost:';
export const GATEWAY_PORT = 40005;
export const SERVICE_NAME = '/employee-service';
export const ORIGIN = HOST + GATEWAY_PORT + SERVICE_NAME;

export const MAPPING_POINT_EMPLOYEE_LOGIN = ORIGIN + '/employee/login';
export const MAPPING_POINT_PRODUCT_CREATE_PRODUCT = ORIGIN + '/product/create_product';
export const MAPPING_POINT_PRODUCT_GET_ALL_PRODUCTS = ORIGIN + '/product/get_all_products';
export const MAPPING_POINT_PRODUCT_UNDERWRITE = ORIGIN + '/product/underwrite';
export const MAPPING_POINT_BOARD_GET_ALL_BOARDS = ORIGIN + '/board/get_all_boards';
export const MAPPING_POINT_BOARD_ANSWER = ORIGIN + '/board/answer';
export const MAPPING_POINT_CLAIM_GET_ALL_CLAIMS_BY_EMPLOYEE_ID = ORIGIN + '/claim/get_all_claims_by_employee_id';
export const MAPPING_POINT_CLAIM_SUBMIT_REPORT = ORIGIN + '/claim/submit_report';
export const MAPPING_POINT_CLAIM_REVIEW_CLAIM = ORIGIN + '/claim/review_claim';
export const MAPPING_POINT_CLAIM_GET_ACCEPTED_CLAIMS = ORIGIN + '/claim/get_accepted_claims';
export const MAPPING_POINT_CLAIM_PAY_COMPENSATION = ORIGIN + '/claim/pay_compensation';
export const MAPPING_POINT_CUSTOMER_GET_CUSTOMER_STATISTICS = ORIGIN + '/customer/get_customer_statistics';
export const MAPPING_POINT_CLAIM_GET_CLAIM_STATISTICS = ORIGIN + '/claim/get_claim_statistics';
