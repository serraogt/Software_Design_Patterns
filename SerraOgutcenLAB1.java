import java.util.ArrayList;

interface FileSystemComponent {
    void Add(FileSystemComponent d);
    void Remove(FileSystemComponent d);
    void Display(int indent);
    public String getName();

    int getCount();
    FileSystemComponent get(int current);
}

interface AbstractAggregate {
    public AbstractIterator CreateIterator();
    public void add(FileSystemComponent it); 		// Not needed for iteration.
    public int getCount (); // Needed for iteration.
    public FileSystemComponent get(int idx); // Needed for iteration.
};


class File implements FileSystemComponent {
    private String name;

    public String getName() { return name;}

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public FileSystemComponent get(int current) {
        return null;
    }

    public File(String name) {this.name = name;}
    public void Add(FileSystemComponent c) {
        System.out.println("Cannot add to a PrimitiveElement.");
    }
    public  void Remove(FileSystemComponent c) {
        System.out.println("Cannot remove from a PrimitiveElement.");
    }
    public void Display(int indent) {
        for(int i = 1;i <= indent;i++) 	System.out.print("-");
        System.out.println(" "  + name);
    }
}

class Directory implements FileSystemComponent , AbstractAggregate {
    private	ArrayList<FileSystemComponent> _items = new ArrayList<FileSystemComponent>();
    private String name;
    public String getName() { return name;}
    public Directory(String name) {this.name = name;}
    public void Add(FileSystemComponent d) {_items.add(d);};
    public void Remove(FileSystemComponent d) {
        for (int i = 0; i< _items.size(); i++) {
            if (_items.get(i).getName() == d.getName()) {
                _items.remove(i);
                return;
            }
        }
    }




    public int sumSize(String str){
        ArrayList summy= new ArrayList<>();
                for (int i = 0; i < _items.size(); i++) {
                    if (_items.get(i).getName().startsWith(str)) {
                        summy.add(_items.get(i));
                    }
            }
                if(summy.isEmpty()){return _items.size();}
                else
                return summy.size();

        }

    public	void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());

        // Display each child element on this node
        for (int i= 0; i< _items.size(); i++) {
            _items.get(i).Display(indent+2);
        }
    }
    
    public void add(FileSystemComponent item) {
        //_items.add(item);
        // no use
        }

    @Override
    public int getCount() {
        return _items.size();
    }

    ;
    public FileSystemComponent get(int index) { return _items.get(index);};


    @Override
        public CollectionIterator CreateIterator() {
            return new CollectionIterator(this) {
            };
        }



    class CollectionIterator implements AbstractIterator {
        public void First() {_current = 0;}
        public void Next()  {_current++; }
        public FileSystemComponent CurrentItem() { return (IsDone()?null:_collection.get(_current)); }
        public Boolean IsDone() {	return _current >= _collection.getCount(); }
        public CollectionIterator(FileSystemComponent collection) {
            _collection = collection;
            _current = 0;
        }
        private FileSystemComponent _collection;
        private int _current;
    };

}
//This is the "client"




public class SerraOgutcenLAB1 {

    static void printAggregate(AbstractIterator itr) {
        System.out.println("Iterating over collection:");
        while (!itr.IsDone()) {
            FileSystemComponent element = itr.CurrentItem();
            System.out.println(element.getName());
            itr.Next();
        }

        System.out.println();
    }







    public static void main(String[] args) {

        // Create a tree structure
        Directory root = new Directory("Picture");
        root.Add(new File("Red Line"));
        root.Add(new File("Blue Circle"));
        root.Add(new File("Green Box"));

        FileSystemComponent comp = new Directory("Two Circles");
        comp.Add(new File("Black Circle"));
        comp.Add(new File("White Circle"));
        root.Add(comp);

        // Add and remove a PrimitiveElement
        FileSystemComponent pe = new File("Yellow Line");
        pe.Add(new File("Red Line"));
        root.Add(pe);
        root.Remove(pe);

        // Recursively display nodes
        root.Display(1);

        AbstractIterator iterator = root.CreateIterator();
        // Traverse the Aggregate.
        printAggregate(iterator);

        System.out.println(root.sumSize("B"));
    }

    private static void printString(FileSystemComponent  it) {
        System.out.println(it.getName());
    }
}


interface  AbstractIterator {
    void First();
    void Next();
    Boolean IsDone () ;
    FileSystemComponent CurrentItem() ;
}