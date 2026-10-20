import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


interface Carbs { String getName(); }
interface Protein { String getName(); }
interface Fats { String getName(); }

class CheeseCarbs implements Carbs { public String getName() { return "Cheese"; } }
class Bread implements Carbs { public String getName() { return "Bread"; } }
class Lentils implements Carbs { public String getName() { return "Lentils"; } }
class Pistachio implements Carbs { public String getName() { return "Pistachio"; } }

class Fish implements Protein { public String getName() { return "Fish"; } }
class Chicken implements Protein { public String getName() { return "Chicken"; } }
class Beef implements Protein { public String getName() { return "Beef"; } }
class Tofu implements Protein { public String getName() { return "Tofu"; } }

class Avocado implements Fats { public String getName() { return "Avocado"; } }
class SourCream implements Fats { public String getName() { return "Sour cream"; } }
class Tuna implements Fats { public String getName() { return "Tuna"; } }
class Peanuts implements Fats { public String getName() { return "Peanuts"; } }



class CarbsFactory 
{
    private static CarbsFactory instance;
    private CarbsFactory() {} // Private Constructor

    public static synchronized CarbsFactory getInstance() 
    {
        if (instance == null) 
        {
            instance = new CarbsFactory();
        }
        return instance;
    }

    public Carbs getCarb(String dietPlan) 
    {
        List<Carbs> options = new ArrayList<>();
        
        switch (dietPlan.toLowerCase()) 
        {
            case "paleo":
                options.add(new Pistachio()); // Paleo - No Carbs except pistachio
                break;
            case "vegan":
                options.add(new Bread());
                options.add(new Lentils());
                options.add(new Pistachio()); // No Dairy (Cheese)
                break;
            case "nut allergy":
                options.add(new CheeseCarbs());
                options.add(new Bread());
                options.add(new Lentils()); // No Pistachio
                break;
            default: // No Restriction
                options.add(new CheeseCarbs());
                options.add(new Bread());
                options.add(new Lentils());
                options.add(new Pistachio());
                break;
        }
        Random rand = new Random();
        return options.get(rand.nextInt(options.size()));
    }
}

class ProteinFactory 
{
    private static ProteinFactory instance;
    private ProteinFactory() {} // Private Constructor

    public static synchronized ProteinFactory getInstance() 
    {
        if (instance == null) 
        {
            instance = new ProteinFactory();
        }
        return instance;
    }

    public Protein getProtein(String dietPlan) 
    {
        List<Protein> options = new ArrayList<>();
        
        switch (dietPlan.toLowerCase()) 
        {
            case "paleo":
                options.add(new Fish());
                options.add(new Chicken());
                options.add(new Beef()); // No Tofu
                break;
            case "vegan":
                options.add(new Tofu()); // No Meat
                break;
            default: // No Restriction
                options.add(new Fish());
                options.add(new Chicken());
                options.add(new Beef());
                options.add(new Tofu());
                break;
        }
        Random rand = new Random();
        return options.get(rand.nextInt(options.size()));
    }
}

class FatsFactory 
{
    private static FatsFactory instance;
    private FatsFactory() {} // Private Constructor

    public static synchronized FatsFactory getInstance() 
    {
        if (instance == null) 
        {
            instance = new FatsFactory();
        }
        return instance;
    }

    public Fats getFat(String dietPlan) 
    {
        List<Fats> options = new ArrayList<>();
        
        switch (dietPlan.toLowerCase()) 
        {
            case "paleo":
                options.add(new Avocado());
                options.add(new Tuna());
                options.add(new Peanuts()); 
                break;
            case "vegan":
                options.add(new Avocado());
                options.add(new Tuna());
                options.add(new Peanuts()); 
                break;
            case "nut allergy":
                options.add(new Avocado());
                options.add(new SourCream());
                options.add(new Tuna()); 
                break;
            default: // No Restriction
                options.add(new Avocado());
                options.add(new SourCream());
                options.add(new Tuna());
                options.add(new Peanuts());
                break;
        }
        Random rand = new Random();
        return options.get(rand.nextInt(options.size()));
    }
}

interface MacronutrientAbstractFactory 
{
    Carbs createCarbs(String dietPlan);
    Protein createProtein(String dietPlan);
    Fats createFats(String dietPlan);
}

class MacronutrientFactory implements MacronutrientAbstractFactory 
{
    private static MacronutrientFactory instance;
    
    private MacronutrientFactory() {} // Private Constructor

    public static synchronized MacronutrientFactory getInstance() 
    {
        if (instance == null) 
        {
            instance = new MacronutrientFactory();
        }
        return instance;
    }

    @Override
    public Carbs createCarbs(String dietPlan) 
    {
        return CarbsFactory.getInstance().getCarb(dietPlan);
    }

    @Override
    public Protein createProtein(String dietPlan) 
    {
        return ProteinFactory.getInstance().getProtein(dietPlan);
    }

    @Override
    public Fats createFats(String dietPlan) 
    {
        return FatsFactory.getInstance().getFat(dietPlan);
    }
}

class Meal 
{
    private final Carbs carb;
    private final Protein protein;
    private final Fats fat;

    public Meal(Carbs carb, Protein protein, Fats fat) 
    {
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
    }

    public void display() 
    {
        System.out.println("  - Carb:    " + carb.getName());
        System.out.println("  - Protein: " + protein.getName());
        System.out.println("  - Fat:     " + fat.getName());
    }
}

class Customer 
{
    private final String name;
    private final String dietPlan;

    public Customer(String name, String dietPlan) 
    {
        this.name = name;
        this.dietPlan = dietPlan;
    }

    public String getName() 
    {
        return name; 
    }

    public String getDietPlan() 
    { 
        return dietPlan; 
    }
}

public class MainDiet 
{
    public static void main(String[] args) 
    {
        MacronutrientAbstractFactory factory = MacronutrientFactory.getInstance();

        List<Customer> customers = Arrays.asList(
            new Customer("Alice", "No Restriction"),
            new Customer("Bob", "Paleo"),
            new Customer("Charlie", "Vegan"),
            new Customer("David", "Nut Allergy"),
            new Customer("Eva", "Vegan"),
            new Customer("Frank", "Paleo")
        );

        System.out.println("=== Generating Meals ===\n");
        
        for (Customer customer : customers) 
        {
            String plan = customer.getDietPlan();
            
            Carbs componentCarb = factory.createCarbs(plan);
            Protein componentProtein = factory.createProtein(plan);
            Fats componentFat = factory.createFats(plan);
            
            Meal meal = new Meal(componentCarb, componentProtein, componentFat);
            
            System.out.println("Customer: " + customer.getName() + " (" + plan + ")");
            meal.display();
            System.out.println("----------------------------------------");
        }
    }
}