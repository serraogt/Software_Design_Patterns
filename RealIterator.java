public class RealIterator implements AbstractIterator {
  //  public RealIterator(ConcreteAggregate concreteAggregate) {}
    private MenuCategory _collection;
    private int _current;

    @Override
    public void First() {
        _current = 0;

    }

    @Override
    public void Next() {
        _current++;
    }

    @Override
    public boolean isDone() {
        if (_current >= _collection.getCount())
        { return true;}
        else {return false;}
    }

    @Override
    public MenuItemComponent currentItem() {
        if (isDone()==true){return null;}
        else{ return _collection.get(_current); }
    }
    public RealIterator(MenuCategory mycollectionList){
        _collection= mycollectionList;
        _current=0;

    }

}