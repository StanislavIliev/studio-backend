package studio.demo.enums;


public enum DefaultProducts {

        OIL("OIL" ),
        FILE("FILE" );

        private String name;

        DefaultProducts(String name){
            this.name= name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


}
