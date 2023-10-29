package lab3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class simulation {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);        
        simulation sim = new simulation();

        do {
            System.out.println("Please enter the road length: ");
            sim.road_generator(user_input.nextInt());  
            System.out.println("\nThe following road is generated:");

            String road = "|";

            for (int i = 0; i < sim.roadType.length; i++) {
                switch (sim.roadType[i]) {
                    case 0:
                        road += "-Asphalt " + sim.roadLength[i] + "-|";
                        break;
                    case 1:
                        road += "-Dirt " + sim.roadLength[i] + "-|";
                        break;
                    case 2:
                        road += "-Stone " + sim.roadLength[i] + "-|";
                        break;
                }
            }

            System.out.println(road);
            System.out.println("\nPlease enter vehicle count: ");
            sim.vehicle_generator(user_input.nextInt());
            System.out.println("\nThe following vehicles are generated: ");   

            String vehicles = "";

            for (int i = 0; i < sim.vehicles.length; i++) {
                vehicle vehicle_x = sim.vehicles[i];
                vehicles += vehicle_x.get_name() + " - Speed: " + vehicle_x.get_speed() + " - Fuel: " + vehicle_x.get_fuel() + "\n";
            }

            System.out.println(vehicles);

            int turn = 1;

            do {
                System.out.println("Turn " + turn);

                vehicles = "";
                
                for (int i = 0; i < sim.vehicles.length; i++) {
                    vehicle vehicle_x = sim.vehicles[i];
                    vehicles += vehicle_x.get_name() + " - Position: " + vehicle_x.get_current_position() + " - Speed: " + vehicle_x.get_speed() + " - Fuel: " + vehicle_x.get_fuel() + "\n";
                }

                System.out.println(vehicles);
                System.out.println("Movements: ");

                String movements = "";
                int check = 0;

                for (int i = 0; i < sim.vehicles.length; i++) {
                    vehicle vehicle_x = sim.vehicles[i];
                    int road_type = sim.getType((int)vehicle_x.get_current_position());

                    if (vehicle_x.get_fuel() > 0) {
                        switch (road_type) {
                            case 0:
                                movements += vehicle_x.get_name() + " moves from asphalt, for " + vehicle_x.get_speed() + " * " + vehicle_x.get_speed_multipliers()[0] + " = " + vehicle_x.get_speed() * vehicle_x.get_speed_multipliers()[0] + "\n";
                                break;
                            case 1:
                                movements += vehicle_x.get_name() + " moves from dirt, for " + vehicle_x.get_speed() + " * " + vehicle_x.get_speed_multipliers()[1] + " = " + vehicle_x.get_speed() * vehicle_x.get_speed_multipliers()[1] + "\n";
                                break;
                            case 2:
                                movements += vehicle_x.get_name() + " moves from stone, for " + vehicle_x.get_speed() + " * " + vehicle_x.get_speed_multipliers()[2] + " = " + vehicle_x.get_speed() * vehicle_x.get_speed_multipliers()[2] + "\n";
                                break;
                        }
                    }

                    vehicle_x.move(road_type);

                    check = sim.is_race_over();

                    if (check == 1) {
                        System.out.println(movements);
                        System.out.println("Vehicle " + vehicle_x.get_name() + " finishes the race! Position: " + vehicle_x.get_current_position() + " - Speed: " + vehicle_x.get_speed() + " - Fuel: " + vehicle_x.get_fuel());
                        System.out.println("End of simulation. Do you want to play again?");
                        user_input.nextLine();
                        System.out.println("");

                        break;
                    }

                    if (check == 2) {
                        float winner_position = sim.vehicles[0].get_current_position();
                        vehicle winner = sim.vehicles[0];

                        for (int u = 1; u < sim.vehicles.length; u++) {
                            if (sim.vehicles[u].get_current_position() > winner_position) {
                                winner_position = sim.vehicles[u].get_current_position();
                                winner = sim.vehicles[u];
                            }
                        }

                        System.out.println(movements);
                        System.out.println("Vehicle " + winner.get_name() + " finishes the race! Position: " + winner.get_current_position() + " - Speed: " + winner.get_speed() + " - Fuel: " + winner.get_fuel());
                        System.out.println("End of simulation. Do you want to play again?");
                        user_input.nextLine();
                        System.out.println("");

                        break;                        
                    }
                }

                if (check != 0) {break;}

                System.out.println(movements);

                turn++;

            } while (sim.is_race_over() == 0);

        } while (user_input.nextLine().equals("yes"));

        user_input.close();
    }

    private int[] roadType;
    private int[] roadLength;
    private vehicle[] vehicles;

    /**
     * This method generates a road with normalization according to the input road length.
     * @param road_length
     */
    public void road_generator(int road_length) {
        Random randy_random = new Random();

        int normalizer = 15; // this value normalizes the road generation
        if (road_length < normalizer) {road_length = normalizer;} // road length must be at least the length of normalizer.
        int segment_count = road_length / normalizer;

        ArrayList<Integer> cuts = new ArrayList<>();
        cuts.add(0);
        cuts.add(road_length);

        for (int i = 0; i < segment_count - 1; i++) {
            int cut_point = randy_random.nextInt(road_length);

            while (cuts.contains(cut_point)) {
                cut_point = randy_random.nextInt(road_length);
            }

            cuts.add(cut_point);
        }

        int prev;
        int last;
        int count = 0;

        do {
            count = 0;
            for (int i = 0; i < cuts.size() - 1; i++) {
                prev = cuts.get(i);
                last = cuts.get(i + 1);
    
                if (prev > last) {
                    cuts.set(i, last);
                    cuts.set(i + 1, prev);
    
                    count++;
                }
            }
        } 
        while (count != 0);

        this.roadType = new int[cuts.size() - 1];
        this.roadLength = new int[cuts.size() - 1];

        for (int i = 0; i < this.roadType.length; i++) {
            this.roadType[i] = -1; // elements of roadType array must be converted into something else than 0, 1 or 2.
        }

        for (int i = 0; i < cuts.size() - 1; i++) {
            int segment_length = cuts.get(i + 1) - cuts.get(i);
            this.roadLength[i] = segment_length;

            while (this.roadType[i] == -1 || this.roadType[i] == this.roadType[i - 1]) {
                int road_type = randy_random.nextInt(100);

                if (road_type < 33) {this.roadType[i] = 0;} // asphalt
                else if (road_type >= 33 && road_type < 66) {this.roadType[i] = 1;} // dirt
                else {this.roadType[i] = 2;} // stone

                if (i == 0) {break;}
            }
        }
    }

    /**
     * This method generates desired number of vehicles and updates the simulation instance accordingly.
     * @param number_of_vehicles
     */
    public void vehicle_generator(int number_of_vehicles) {
        this.vehicles = new vehicle[number_of_vehicles];
        Random randy_random = new Random();

        for (int i = 0; i < this.vehicles.length; i++) {
            int vehicle_decider = randy_random.nextInt(100);

            if (vehicle_decider <= 39) {
                int speed = randy_random.nextInt(11) + 15; // 15-25
                int fuel = randy_random.nextInt(11) + 30; // 30-40

                this.vehicles[i] = new wheeled(speed, 0, fuel);
            }
            else if (vehicle_decider > 39 && vehicle_decider <= 64) {
                int speed = randy_random.nextInt(11) + 20; // 20-30
                int fuel = randy_random.nextInt(11) + 20; // 20-30

                this.vehicles[i] = new flying(speed, 0, fuel);
            }
            else {
                int speed = randy_random.nextInt(21) + 20; // 20-40
                int fuel = randy_random.nextInt(11) + 10; // 10-20

                this.vehicles[i] = new quadruped(speed, 0, fuel);
            }
        }
    }

    /**
     * This method moves the vehicle based on the current type of road (0: asphalt, 1:dirt, 2: stone).
     * @param roadType
     */
    public void move(int roadType, vehicle vehicle) {
        vehicle.move(roadType);
    }

    /**
     * This method returns the type of road given the current position of a vehicle (0: asphalt, 1:dirt, 2: stone).
     * @param position
     * @return
     */
    public int getType(int position) {
        int road_type;
        int indicator = 0;
        int i = 0;

        do {
            indicator += this.roadLength[i];
            road_type = this.roadType[i];

            i++;
        } while (position > indicator);

        return road_type;
    }

    /**
     * Returns an integer value indicating whether the race is finished or not (0: race continues, 1: someone has finished the race, 2: vehicles run out of fuel).
     * @return
     */
    public int is_race_over() {
        Boolean check = false;
        Boolean fuel_limit = false;
        int fuel_checker = 0;

        for (int i = 0; i < this.vehicles.length; i++) {
            int total_road_length = 0;

            for (int a = 0; a < this.roadLength.length; a++) {
                total_road_length += this.roadLength[a];
            }

            if (this.vehicles[i].get_current_position() >= total_road_length) {
                check = true;
                break;
            } 
            else if (this.vehicles[i].get_fuel() == 0) {
                fuel_checker++;
                if (fuel_checker == this.vehicles.length) {
                    fuel_limit = true;
                    break;
                }
            }

        }

        if (check) {
            return 1;
        }
        else if (fuel_limit) {
            return 2;
        }
        else {
            return 0;
        }
    }
}
