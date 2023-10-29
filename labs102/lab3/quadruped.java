package lab3;

public class quadruped extends vehicle {
    final static double ASPHALT_SPEED = 0.5;
    final static double DIRT_SPEED = 1;
    final static double STONE_SPEED = 0.75;

    public static int quadruped_count = 0;

    // constructor
    public quadruped(float speed, float current_position, int fuel) {
        super(speed, current_position, fuel);

        quadruped.quadruped_count++;
        this.name = "Q" + quadruped.quadruped_count;
    }   

    // getters
    public double[] get_speed_multipliers() {double[] array = {quadruped.ASPHALT_SPEED, quadruped.DIRT_SPEED, quadruped.STONE_SPEED}; return array;}
    
    // methods
    public void move(int roadType) {
        if (this.fuel > 0) {
            switch (roadType) {
                case 0:
                    this.current_position += this.speed * quadruped.ASPHALT_SPEED;
                    break;
                case 1:
                    this.current_position += this.speed * quadruped.DIRT_SPEED;
                    break;
                case 2: 
                    this.current_position += this.speed * quadruped.STONE_SPEED;
                    break;
            }

            this.fuel--;
        }
    }
}
