package VtorKolokviumVezbi.Zadaca36;

import java.util.*;
import java.util.stream.Collectors;

/*
YOUR CODE HERE
DO NOT MODIFY THE interfaces and classes below!!!
*/

class Address
{
    private String addressName;
    private Location location;

    public Address(String addressName, Location location) {
        this.addressName = addressName;
        this.location = location;
    }

    public String getAddressName() {
        return addressName;
    }

    public Location getLocation() {
        return location;
    }
}

class User implements Comparable<User>
{
    private String id;
    private String name;
    private ArrayList<Address> addresses;
    private double moneySpent;
    private int numberOfOrders;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        moneySpent = 0;
        numberOfOrders = 0;
        addresses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Override
    public int compareTo(User o) {
        Comparator<User> comparator = Comparator.comparingDouble(User::getMoneySpent).thenComparing(User::getId);

        return comparator.compare(this, o);
    }

    @Override
    public String toString() {
        //ID: 1 Name: stefan Total orders: 1 Total amount spent: 450.00 Average amount spent: 450.00
        return String.format("ID: %s Name: %s Total orders: %d Total amount spent: %.2f Average amount spent: %.2f",
                id, name, numberOfOrders, moneySpent, numberOfOrders != 0 ? moneySpent/numberOfOrders : 0.00);
    }
}

class DeliveryPerson implements Comparable<DeliveryPerson>
{
    private String id;
    private String name;
    private Location location;
    private int numberOfOrders;
    private double money;
    private Address address;

    public DeliveryPerson(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        numberOfOrders = 0;
        money = 0;
        address = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public double getMoney() {
        return money;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public int compareTo(DeliveryPerson o) {
        Comparator<DeliveryPerson> comparator = Comparator.comparingDouble(DeliveryPerson::getMoney).thenComparing(DeliveryPerson::getId);
        return comparator.compare(this, o);
    }

    @Override
    public String toString() {
        //ID: 2 Name: Riste Total deliveries: 1 Total delivery fee: 90.00 Average delivery fee: 90.00↩
        return String.format("ID: %s Name: %s Total deliveries: %d Total delivery fee: %.2f Average delivery fee: %.2f",
                id, name, numberOfOrders, money, numberOfOrders != 0 ? money/numberOfOrders : 0.00);
    }
}

class Restaurant implements Comparable<Restaurant>
{
    private String id;
    private String name;
    private Location location;

    private double earnings;
    private int numberOfOrdersReceived;

    public Restaurant(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        earnings = 0;
        numberOfOrdersReceived = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public double getEarnings() {
        return earnings;
    }

    public int getNumberOfOrdersReceived() {
        return numberOfOrdersReceived;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public void setNumberOfOrdersReceived(int numberOfOrdersReceived) {
        this.numberOfOrdersReceived = numberOfOrdersReceived;
    }

    public double getAverageEarnings()
    {
        return this.getNumberOfOrdersReceived() != 0 ? this.getEarnings() / this.getNumberOfOrdersReceived() : 0.00;
    }

    @Override
    public int compareTo(Restaurant o) {
        Comparator<Restaurant> comparator = Comparator.comparingDouble(Restaurant::getAverageEarnings).thenComparing(Restaurant::getId);
        return comparator.compare(this,o);
    }

    @Override
    public String toString() {
        //ID: 1 Name: Morino Total orders: 1 Total amount earned: 450.00 Average amount earned: 450.00
        return String.format("ID: %s Name: %s Total orders: %d Total amount earned: %.2f Average amount earned: %.2f",
                id, name, numberOfOrdersReceived, earnings, numberOfOrdersReceived != 0 ? earnings/numberOfOrdersReceived : 0.00);
    }
}

class Order
{
    User userThatOrdered;
    Address addressWhereToBring;
    Restaurant fromWhichRestaurant;
    double cost;
    DeliveryPerson deliveryPerson;


    public Order(User userThatOrdered, Address addressWhereToBring, Restaurant fromWhichRestaurant, double cost, DeliveryPerson deliveryPerson) {
        this.userThatOrdered = userThatOrdered;
        this.addressWhereToBring = addressWhereToBring;
        this.fromWhichRestaurant = fromWhichRestaurant;
        this.cost = cost;
        this.deliveryPerson = deliveryPerson;
    }

    public User getUserThatOrdered() {
        return userThatOrdered;
    }

    public Address getAddressWhereToBring() {
        return addressWhereToBring;
    }

    public Restaurant getFromWhichRestaurant() {
        return fromWhichRestaurant;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(cost, order.cost) == 0 && Objects.equals(userThatOrdered, order.userThatOrdered) && Objects.equals(addressWhereToBring, order.addressWhereToBring) && Objects.equals(fromWhichRestaurant, order.fromWhichRestaurant) && Objects.equals(deliveryPerson, order.deliveryPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userThatOrdered, addressWhereToBring, fromWhichRestaurant);
    }
}

class DeliveryApp
{
    private String name;
    ArrayList<DeliveryPerson> freeDeliveryDeliveryPeople;
    ArrayList<Restaurant> restaurants;
    ArrayList<User> users;
    Map<String, Order> ordersMap;
    public DeliveryApp (String name)
    {
        freeDeliveryDeliveryPeople = new ArrayList<>();
        restaurants = new ArrayList<>();
        users = new ArrayList<>();
        ordersMap = new HashMap<>();
        this.name = name;
    }
    public void registerDeliveryPerson (String id, String name, Location currentLocation)
    {
        freeDeliveryDeliveryPeople.add(new DeliveryPerson(id, name, currentLocation));
    }
    public void addRestaurant (String id, String name, Location location)
    {
        restaurants.add(new Restaurant(id, name, location));
    }
    public void addUser (String id, String name)
    {
        users.add(new User(id, name));
    }
    public void addAddress (String id, String addressName, Location location)
    {
        Optional<User> foundUser = users.stream()
                .filter(user -> user.getId().equals(id)).findFirst();
        if(foundUser.isPresent())
        {
            foundUser.get().getAddresses().add(new Address(addressName, location));
        }
    }

    private double calculateDistance(Location location1, Location location2)
    {
        return location1.distance(location2);
    }

    private DeliveryPerson closestDeliveryPerson(Address deliveryAddress)
    {
        double distance = calculateDistance(freeDeliveryDeliveryPeople.get(0).getLocation(), deliveryAddress.getLocation());
        for(DeliveryPerson deliveryPerson : freeDeliveryDeliveryPeople)
        {
            if(calculateDistance(deliveryPerson.getLocation(), deliveryAddress.getLocation()) < distance)
            {
                distance = calculateDistance(deliveryPerson.getLocation(), deliveryAddress.getLocation());
            }
        }

        double finalDistance = distance;
        ArrayList<DeliveryPerson> closestDeliveryPersons = (ArrayList<DeliveryPerson>) freeDeliveryDeliveryPeople.stream()
                .filter(deliveryGuy ->
                        calculateDistance(deliveryGuy.getLocation(), deliveryAddress.getLocation()) <= finalDistance)
                .collect(Collectors.toList());

        DeliveryPerson deliveryPerson = closestDeliveryPersons.stream()
                .filter(dp -> dp.getNumberOfOrders() ==
                        closestDeliveryPersons.stream()
                                .mapToInt(DeliveryPerson::getNumberOfOrders)
                                .min()
                                .orElseThrow(null))
                .findFirst()
                .orElseThrow(null);

        deliveryPerson.setMoney(deliveryPerson.getMoney() + 90 + ((int)distance/10)*10);
        deliveryPerson.setNumberOfOrders(deliveryPerson.getNumberOfOrders()+1);

        return deliveryPerson;

    }


    public void orderFood(String userId, String userAddressName, String restaurantId, float cost)
    {
        User user = users.stream().filter(r -> r.getId().equals(userId)).findFirst().orElseThrow(null);
        Address address = user.getAddresses().stream().filter(r -> r.getAddressName().equals(userAddressName)).findFirst().orElseThrow(null);
        Restaurant restaurant = restaurants.stream().filter(r -> r.getId().equals(restaurantId)).findFirst().orElseThrow(null);

        user.setMoneySpent(user.getMoneySpent() + cost);
        user.setNumberOfOrders(user.getNumberOfOrders() + 1);

        restaurant.setEarnings(restaurant.getEarnings() + cost);
        restaurant.setNumberOfOrdersReceived(restaurant.getNumberOfOrdersReceived()+1);

        DeliveryPerson deliveryPerson = closestDeliveryPerson(new Address ("", restaurant.getLocation()));
        deliveryPerson.setLocation(address.getLocation());
        deliveryPerson.setAddress(address);

        ordersMap.put(userId, new Order(user, address, restaurant, cost, deliveryPerson));
    }

    public void printUsers()
    {
        this.users.stream()
                .sorted(User::compareTo)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    void printRestaurants()
    {
        this.restaurants.stream()
                .sorted(Restaurant::compareTo)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
    void printDeliveryPeople()
    {
        this.freeDeliveryDeliveryPeople.stream()
                .sorted(DeliveryPerson::compareTo)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

}


interface Location {
    int getX();

    int getY();

    default int distance(Location other) {
        int xDiff = Math.abs(getX() - other.getX());
        int yDiff = Math.abs(getY() - other.getY());
        return xDiff + yDiff;
    }
}

class LocationCreator {
    public static Location create(int x, int y) {

        return new Location() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }
        };
    }
}

public class DeliveryAppTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String appName = sc.nextLine();
        DeliveryApp app = new DeliveryApp(appName);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            if (parts[0].equals("addUser")) {
                String id = parts[1];
                String name = parts[2];
                app.addUser(id, name);
            } else if (parts[0].equals("registerDeliveryPerson")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.registerDeliveryPerson(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("addRestaurant")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.addRestaurant(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("addAddress")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.addAddress(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("orderFood")) {
                String userId = parts[1];
                String userAddressName = parts[2];
                String restaurantId = parts[3];
                float cost = Float.parseFloat(parts[4]);
                app.orderFood(userId, userAddressName, restaurantId, cost);
            } else if (parts[0].equals("printUsers")) {
                app.printUsers();
            } else if (parts[0].equals("printRestaurants")) {
                app.printRestaurants();
            } else {
                app.printDeliveryPeople();
            }

        }
    }
}
