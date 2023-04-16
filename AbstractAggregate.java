import javax.lang.model.element.Element;
import java.util.Collection;

public interface AbstractAggregate {
    AbstractIterator CreateIterator(String nameoftheIterator);

  //  void addMenuItem(MenuItemComponent it);

  //  MenuItemComponent remove(int index);
    public int getCount();
    public MenuItemComponent get(int idx);
}

interface AbstractIterator {
    void First();
    void Next();
    boolean isDone();
    MenuItemComponent currentItem();


}

