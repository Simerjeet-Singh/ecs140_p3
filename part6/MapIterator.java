class MapIterator{
  Map map;//local map referencing to the map calling it.

  //constructor
  public MapIterator(Map map){
    this.map = map;
  }

  //check if two iterators are pointing to the same place
  public boolean equal(MapIterator other){
      if(map==null) return true;//return true if it is the last element
      else return false;
  }

  //moving the iterator to the next position
  public MapIterator advance(){
    MyInteger i = new MyInteger();
    i.Set(0);//used as an iterator
    MyInteger jump2 = new MyInteger();
    jump2.Set(0);//used to break out of loop
    while(jump2.Get() == 0){
      map = map.next;
      if(i.Get()==1) jump2.Set(1);
      i.Set(((i.Get())+1));
    }
    return this;
  }

  //get returns the current pair pointed by map
  public Pair get(){
    Map tempMap = map;
    tempMap = tempMap.next;
    MyChar tempChar = (MyChar)(map.data);
    Pair p = new Pair(tempChar, tempMap.data);
    return p;
  }
}
