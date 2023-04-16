import java.util.ArrayList;

interface MenuItemComponent{

        boolean isComposite = false;
        String getName();
        int getPrice();
        String name = null;

    int find(String givenName);
}


class MenuItem implements MenuItemComponent, AbstractAggregate{

    boolean isComposite=false;
    private String name;
    public int price=0;

    public MenuItem(String name, int price) {
        this.name=name;
        this.price=price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int find(String givenName) {
        return 0;
    }

    @Override
    public AbstractIterator CreateIterator(String nameoftheIterator) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public MenuItemComponent get(int idx) {
        return null;
    }
}

class MenuCategory implements MenuItemComponent, AbstractAggregate{

    private ArrayList<MenuItemComponent> _items = new ArrayList<MenuItemComponent>();

   /* public AbstractIterator CreateIterator(String nameof) {
        //if else
        return new RealIterator(this);
    }*/

    public AbstractIterator CreateIterator(AbstractIterator iterator){
        return iterator;
    }


    public void addMenuItem(MenuItemComponent _item) {
        _items.add(_item);

    }

    public MenuItemComponent remove(int index) {
        _items.remove(index);

        return _items.remove(index);
    }
    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public MenuItemComponent get(int index) {
        return _items.get(index);
    };


    private MenuCategory _collection;
    private int _current;

    boolean isComposite=true;
    private String name;
    private int price;
    private ArrayList<MenuItemComponent> elements = new ArrayList<>();

    public MenuCategory(String name) {
        this.name = name;

    }
    public int showMenu(){
        System.out.println("MENU");
        int i;
        for (i = 0; i < elements.size(); i++) {

            System.out.println(elements.get(i).getName()+" "+elements.get(i).getPrice());
         //   int chld = elements.get(i).find("this");
         /*   if (elements.get(i).getName().equals("this")) {
                System.out.println(elements.get(i).getName()+" "+elements.get(i).getPrice());
                return elements.get(i).getPrice();}
            else {
                System.out.println(elements.get(i).getName()+" "+elements.get(i).getPrice());
                int chld = elements.get(i).find("this");

            }*/
    }

        return i;
    }


    public int find(String givenName) {
      /*  int i;
        for (i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName().equals(givenName)) {
                //on the root
                System.out.print(elements.get(i).getName()+" is on the menu ");
                if(!elements.get(i).isComposite){
                    //leaf
                    System.out.printf("for %d euros%n", elements.get(i).getPrice());
                    price=elements. get(i).getPrice();
                    return price;
                }
                else {
                    //composite
                    price+= elements.get(i).find(givenName);
                    return price;
                }

                 }
                else {
                    //not on the root
                    System.out.println(elements.get(i).getName());
                    int chld = elements.get(i).find(givenName);

                }
        }


        return price; */
        return 0;
    }

    public void add(MenuItemComponent c) {
        elements.add(c);
    }

    public void remove(MenuItemComponent c) {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setPrice() {
        int sum=0;
        for (int i = 0; i < elements.size(); i++) {
            sum = sum + elements.get(i).getPrice();
        }
        price = sum;
    }
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public AbstractIterator CreateIterator(String nameoftheIterator) {
        AbstractIterator InOrderIterator;

        InOrderIterator = new AbstractIterator() {
            @Override
            public void First() {
                _current=0;
            }

            @Override
            public void Next() {
                _current++;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public MenuItemComponent currentItem() {
                return null;
            }

        };


        return null;
    }



}


 class Main{

     static void printAggregate(AbstractIterator itr) {
         System.out.println("iteration over your list");
         for(itr.First();!itr.isDone();itr.Next()){
             System.out.println(itr.currentItem().toString());
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }

         }
         System.out.println();
     }
     public static void main(String[] args) {
        MenuCategory menu = new MenuCategory("menu");
             MenuCategory starters= new MenuCategory(" starters");
             menu.add(starters);

                   MenuCategory salads= new MenuCategory(" salads");
                   starters.add(salads);
                        MenuItem salad1= new MenuItem("   ceasar", 10);
                        salads.add(salad1);
                        MenuItem salad2= new MenuItem("   mixed",12);
                        salads.add(salad2);
                   MenuItem soup=new MenuItem(" soup",15);
                   starters.add(soup);

             MenuCategory entrees = new MenuCategory(" entree");
             menu.add(entrees);
                 MenuCategory chicken= new MenuCategory("  chick");
                    entrees.add(chicken);
                        MenuItem masala =new MenuItem("   masala",20);
                        chicken.add(masala);


         System.out.println(menu.find(" soup"));
         System.out.println(menu.find("fds"));
         System.out.println(starters.find(" salads"));
         System.out.println(salads.find("   mixed"));
         System.out.println(menu.find(" entree"));


         menu.showMenu();
         AbstractIterator iterator=menu.CreateIterator("esdf");

         //does not change
         printAggregate(iterator);

         //System.out.println(soup.getPrice());
     }
 }