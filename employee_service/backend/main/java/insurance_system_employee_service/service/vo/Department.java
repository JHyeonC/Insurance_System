package insurance_system_employee_service.service.vo;

public enum Department {
      MARKETING{
        @Override
        public String toString() {
            return "MARKETING";
        }
    }, INVESTIGATING{
        @Override
        public String toString() {
            return "INVESTIGATING";
        }
    }, SUPPORTING{
        @Override
        public String toString() {
            return "SUPPORTING";
        }
    }, DEVELOPMENT{
        @Override
        public String toString() {
            return "DEVELOPMENT";
        }
    }, CONTRACT{
        @Override
        public String toString() {
            return "CONTRACT";
        }
    }, UNDERWRITING{
        @Override
        public String toString() {
            return "UNDERWRITING";
        }
    };
}
