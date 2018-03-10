public class Map extends Element {

  Map next; //for moving to the next pair aka linked list
  Element data; //element data held by the current pointer
  MyInteger length = new MyInteger();//used for keeping count of how many pairs

  //constructor
  public Map(){
    next = null;
    data = null;
    length.Set(0);
  }

  //constructor
  public Map(Element data, Map next){
    this.next = next;
    this.data = data;
    length.Set(0);
  }

  //first re turns the first element of the Map
  public Element first(){
    return this.data;
  }

  //return rest of the Map leaving the first element of the Map
  public Map rest(){
    return this.next;
  }

  //begin() returns the reference to the first pair in map
  public MapIterator begin(){
    MapIterator SeqI = new MapIterator(this);
    return SeqI;
  }

  //end returns reference to the last element in the map which is null
  public MapIterator end(){
    MapIterator SeqI = new MapIterator(null);
    return SeqI;
  }


  //addat() adds the element at a given position in map sequence
  public void addat(Element elm, int pos){
    MyInteger iterator = new MyInteger();//used for iteration loop
    iterator.Set(0);
    if(pos > length.Get() ){
      System.err.println("pos is greater than the length of the Map");
      System.exit(1);
    }
    else if(length.Get() == 0){//special case adding first time
      this.next = null;
      this.data = elm;
      length.Set(((length.Get())+1));
    }
    else if(pos == 0){//special case adding at the front
      Map obj = new Map(elm, this);//local object reference to map object
      Element tempData = this.data;//capture value of data in tempData
      this.data = elm;//capturing the value of the elm and making it the first element
      obj.data = tempData;
      Map tempSeq = obj;//local map that references to obj
      obj.next = this.next;//adjusting references
      this.next = obj;
      length.Set(((length.Get())+1));
    }
    else{
      Map tempSeq = this;//local object reference to map object
      Map obj = new Map(elm, null);//constructing another map object for getting help adjusting the references in sequence
      while(iterator.Get() != pos){
        if(iterator.Get() == (pos-1)){//position matches where we need to add
          obj.next = tempSeq.next;//adjust references and add
          tempSeq.next = obj;
        }
        tempSeq = tempSeq.next;
        iterator.Set(((iterator.Get())+1));
      }
      length.Set(((length.Get())+1));//increase the length subject to addition of new element
    }
  }

  //add a pair at the given position in the Map
  public void add(Pair p){
    MyInteger iterator = new MyInteger();//for iterating through the map
    MyInteger pkeyint = new MyInteger();//for holding the value of key from map
    MyInteger localkeyint = new MyInteger();//for holding the key we need to add
    MyChar localChar1 = new MyChar();//for holding the value
    pkeyint.data = p.key.data;
    Map tempMap = this;//reference to the current map that calls the add
    MyInteger i = new MyInteger();//helps us specify the position where we need to add and hence call addat()
    i.Set(0);
    MyInteger done = new MyInteger();//indicates we are done with the addition
    done.Set(0);
    if((tempMap.data == null)){//special case, nothing present in map. so add at front
      addat(p.key,0);//add key
      addat(p.value,1);//add value
    }
    else{
      while(done.Get() ==0){
        if(tempMap == null){//special case, adding pair at the end
          addat(p.key,i.Get());//add key
          addat(p.value,i.Get()+1);//add value
          done.Set(1);
          break;
        }
        else if((i.Get()%2)==0 && (tempMap !=null) && (tempMap.data != null)){//normal case adding pair somewhere
          localChar1 = (MyChar)(tempMap.data);//typecast necessary
          localkeyint.Set((localChar1.Get()));
          if( (pkeyint.Get()) < (localkeyint.Get()) ){
            addat(p.key,i.Get());//add key
            addat(p.value,i.Get()+1);//add value
            done.Set(1);
            break;
          }
        }
        tempMap = tempMap.next;//move the reference to the next pair(key/value)
        i.Set(((i.Get())+1));
      }
    }
  }

  //returning the reference to the value corresponding to a specific key, using find()
  public MapIterator find(MyChar key){
    MyInteger iterator = new MyInteger();//used for looping through the map
    MyInteger pkeyint = new MyInteger();//used for holding the key value
    MyInteger localkeyint = new MyInteger();//used for caputuring the key value from map and comparison
    MyChar localChar1 = new MyChar();//used for holding the char value and typecasting
    pkeyint.data = key.data;
    Map tempMap = this;//reference to where the map points
    Map tempMap1 = this;//temporary reference to swap
    MyInteger i = new MyInteger();//helps us specify the position where we need to add and hence call addat()
    i.Set(0);
    MyInteger done = new MyInteger();//indicates we are done with the addition
    done.Set(0);//setting done to 0, would be 1 when we want to break outof loop
    while(done.Get() ==0){
      if((i.Get()%2)==0){//checking if it is the key
        localChar1 = (MyChar)(tempMap.data);
        localkeyint.data = localChar1.data;
        if( (pkeyint.Get()) == (localkeyint.Get()) ){
          MapIterator mapItr = new MapIterator(tempMap);
          done.Set(1);
          return mapItr;

        }
      }
      tempMap1 = tempMap;//caputuring the reference
      tempMap = tempMap.next;//moving to the next
      if(tempMap == null){//special case if it points to null=>reached the end
        MapIterator mapItr = new MapIterator(null);
        done.Set(1);
        return mapItr;
      }
      i.Set(((i.Get())+1));
    }
    MapIterator mapitr = new MapIterator(tempMap1);//created the temporary reference if not found and prevent compilation error
    return mapitr;
  }

  // print the key value pairs
  @Override
  public void Print(){
    Map tempMap = new Map();//get the temporary reference to the map
    tempMap = this;
    MyInteger i = new MyInteger();//used for looping and printing
    i.Set(0);
    System.out.print("[ ");//adjusting the print and brackets
    System.out.print("(");
    while(tempMap != null){//until we have not reached null aka end of the pairs
      if((i.Get()%2) == 0 && (i.Get() != 0)){//reached value of pair
        System.out.print(") (");
      }
      tempMap.data.Print();
      if((i.Get()%2) == 0)//reached value of pair
        System.out.print(" ");
      tempMap = tempMap.next;
      i.Set(((i.Get())+1));//i++
    }
    System.out.print(") ");
    System.out.print("]");
  }

}
