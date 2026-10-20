import java.util.ArrayList;
import java.util.List;

class Pizza {
    private final String chain;
    private final String size;
    private final List<String> toppings;

    private Pizza(Builder builder) {
        this.chain = builder.chain;
        this.size = builder.size;
        this.toppings = new ArrayList<>(builder.toppings);
    }

    public void eat() {
        System.out.println("Pizza Chain: " + chain);
        System.out.println("Size: " + size);
        System.out.println("Toppings: " + (toppings.isEmpty() ? "None" : String.join(", ", toppings)));
        System.out.println("----------------------------------------");
    }

    public static class Builder {
        private final String chain; 
        private String size;        
        private final List<String> toppings = new ArrayList<>();

        public Builder(String chain) {
            this.chain = chain;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder addPepperoni() { toppings.add("Pepperoni"); return this; }
        public Builder addSusage() { toppings.add("Sausage"); return this; }
        public Builder addMushrooms() { toppings.add("Mushrooms"); return this; }
        public Builder addBacon() { toppings.add("Bacon"); return this; }
        public Builder addOnions() { toppings.add("Onions"); return this; }
        public Builder addExtraCheese() { toppings.add("Extra Cheese"); return this; }
        public Builder addPeppers() { toppings.add("Peppers"); return this; }
        public Builder addChicken() { toppings.add("Chicken"); return this; }
        public Builder addOlives() { toppings.add("Olives"); return this; }
        public Builder addSpinach() { toppings.add("Spinach"); return this; }
        public Builder addTomatoAndBasil() { toppings.add("Tomato and Basil"); return this; }
        public Builder addBeef() { toppings.add("Beef"); return this; }
        public Builder addHam() { toppings.add("Ham"); return this; }
        public Builder addPesto() { toppings.add("Pesto"); return this; }
        public Builder addSpicyFork() { toppings.add("Spicy Pork"); return this; }
        public Builder addHamAndPineapple() { toppings.add("Ham and Pineapple"); return this; }

        public Pizza build() {
            if (this.size == null || this.size.isEmpty()) {
                throw new IllegalStateException("Size is required!");
            }
            return new Pizza(this);
        }
    }
}


public class MainPizza {
    public static void main(String[] args) {
        
        // PART 1: Pizza Hut - 3 Pizzas (3, 6, 9 Toppings)
        System.out.println("=== PART 1: PIZZA HUT ===\n");

        Pizza phSmall = new Pizza.Builder("Pizza Hut")
                .size("Small")
                .addPepperoni().addMushrooms().addExtraCheese()
                .build();

        Pizza phMedium = new Pizza.Builder("Pizza Hut")
                .size("Medium")
                .addSusage().addBacon().addOnions().addPeppers().addChicken().addOlives()
                .build();

        Pizza phLarge = new Pizza.Builder("Pizza Hut")
                .size("Large")
                .addSpinach().addTomatoAndBasil().addBeef().addHam().addPesto().addSpicyFork()
                .addHamAndPineapple().addPepperoni().addExtraCheese()
                .build();

        phSmall.eat();
        phMedium.eat();
        phLarge.eat();

        // PART 2: Three Different Pizza Chains
        System.out.println("=== PART 2: MULTIPLE CHAINS ===\n");

        Pizza pizzaHut1 = new Pizza.Builder("Pizza Hut")
                .size("Large")
                .addPepperoni().addMushrooms().addExtraCheese()
                .build();

        Pizza pizzaHut2 = new Pizza.Builder("Pizza Hut")
                .size("Small")
                .addBacon().addOnions()
                .build();

        Pizza littleCaesars1 = new Pizza.Builder("Little Caesars")
                .size("Medium")
                .addPepperoni().addSusage().addMushrooms().addBacon().addOnions().addExtraCheese().addPeppers().addChicken()
                .build();

        Pizza littleCaesars2 = new Pizza.Builder("Little Caesars")
                .size("Small")
                .addOlives().addSpinach().addTomatoAndBasil().addBeef().addHam().addPesto()
                .build();

        Pizza dominos1 = new Pizza.Builder("Dominos")
                .size("Small")
                .addSpicyFork()
                .build();

        Pizza dominos2 = new Pizza.Builder("Dominos")
                .size("Large")
                .addHamAndPineapple().addExtraCheese().addPepperoni()
                .build();

        pizzaHut1.eat();
        pizzaHut2.eat();
        littleCaesars1.eat();
        littleCaesars2.eat();
        dominos1.eat();
        dominos2.eat();
    }
}