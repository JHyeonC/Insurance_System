package insurance_system_employee_service.service.vo;

public enum Status {
    REPORTING{
        @Override
        public String toString(){
            return "REPORTING";
        }
    }, REVIEWING{
        @Override
        public String toString(){
            return "REVIEWING";
        }
    }, ACCEPTED{
        @Override
        public String toString(){
            return "ACCEPTED";
        }
    }, REJECTED{
        @Override
        public String toString(){
            return "REJECTED";
        }
    }, PAID{
        @Override
        public String toString(){
            return "PAID";
        }
    }
}
