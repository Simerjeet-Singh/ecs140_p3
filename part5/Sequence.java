public class Sequence extends Element {

  Sequence next;//for refering to the next element in the sequence
  Element data;//for holding data (Element)
  MyInteger length = new MyInteger();//for keeping track of number of Elements in Sequence

  //constructor
  public Sequence(){
    next = null;
    data = null;
    length.Set(0);
  }

  //constructor
  public Sequence(Element data, Sequence next){
    this.next = next;
    this.data = data;
    length.Set(0);
  }

  //first re turns the first element of the sequence
  public Element first(){
    return this.data;
  }

  //return rest of the sequence leaving the first element of the sequence
  public Sequence rest(){
    return this.next;
  }

  //SequenceIterator pointing to the first element in the sequence
  public SequenceIterator begin(){
    SequenceIterator SeqI = new SequenceIterator(this);
    return SeqI;
  }

  //return reference to the last element
  public SequenceIterator end(){
    SequenceIterator SeqI = new SequenceIterator(null);
    return SeqI;
  }


  //returns the element at given location
  public Element index(int pos){

    MyInteger iterator = new MyInteger();
    iterator.Set(0);
    Sequence tempSeq = this;
    if(pos > length.Get()){
      System.err.println("pos is greater than the length of the sequence");
      System.exit(1);
      return this.data;
    }
    else{
      while(iterator.Get() <= pos){
        if(iterator.Get() == pos){
          return tempSeq.data;
        }
        tempSeq = tempSeq.next;
        iterator.Set(((iterator.Get())+1));
      }
    }
    return this.data;
  }

  // flatten the sequence into one sequence of elements and remove
  // all sequences within a sequence
  public Sequence flatten(){
    MyInteger iterator = new MyInteger();
    iterator.Set(0);
    Sequence tempSeq = this;
    Sequence seq = new Sequence();//for holding reference to the first element in sequence
    Sequence seq2 = new Sequence();//used for swapping and holding reference to element in sequence
    if(this.next == null) {//special case nothing present
      return this;
    }
    else{
      while(tempSeq != null){
        if ( (tempSeq.data instanceof MyChar) || (tempSeq.data instanceof MyInteger)){
          seq.add((tempSeq.data),iterator.Get());
          iterator.Set(((iterator.Get())+1));
        }
        else{
          seq2 = (Sequence)(tempSeq.data);
          seq2 = seq2.flatten();//call flatten recursively
          //using DFS approach
          while(seq2 != null){//transfer the whole sequence to upper sequence one by one
            seq.add(seq2.data,iterator.Get());
            seq2 = seq2.next;
            iterator.Set(((iterator.Get())+1));
          }
        }
        tempSeq = tempSeq.next;//move to the next element

      }
      return seq;
    }
  }

  //return the number of elements in the sequence
  public int length(){
    MyInteger tLength = new MyInteger();
    Sequence tempSeq = this;
    while(tempSeq.next != null){
      tempSeq = tempSeq.next;
      tLength.Set(((tLength.Get())+1));
    }
    tLength.Set(((tLength.Get())+1));
    return tLength.Get();
  }

  //add an element at the given position in the sequence
  public void add(Element elm, int pos){
    MyInteger iterator = new MyInteger();//for loop iteration
    iterator.Set(0);
    if(pos > length.Get()){
      System.err.println("pos is greater than the length of the sequence");
      System.exit(1);
    }
    else if(length.Get() == 0){//special case adding first time
      this.next = null;
      this.data = elm;
      length.Set(((length.Get())+1));
    }
    else if(pos == 0){//special case adding at 0 position
      Sequence obj = new Sequence(elm, this);
      Element tempData = this.data;
      this.data = elm;
      obj.data = tempData;

      Sequence tempSeq = obj;
      obj.next = this.next;
      this.next = obj;
      length.Set(((length.Get())+1));
    }
    else{//normal case
      Sequence tempSeq = this;
      Sequence obj = new Sequence(elm, null);
      while(iterator.Get() != pos){
        if(iterator.Get() == (pos-1)){
          obj.next = tempSeq.next;
          tempSeq.next = obj;
        }
        tempSeq = tempSeq.next;
        iterator.Set(((iterator.Get())+1));
      }
      length.Set(((length.Get())+1));
    }
  }

  // delete the element present at the given position if none then do nothing
  public void delete(int pos){
    if(pos > length.Get()){
      System.err.println("pos > length, cannot delete");
      return;
    }
    else if(length.Get() == 1){
      this.next = null;
      this.data = null;
      length.Set(((length.Get())-1));
      return;
    }
    else if(pos == 0){
      // first copy everything from second sequence to first and then delete second
      // sequence
      this.data = this.next.data;  // assign the data of next Sequence
      this.next = this.next.next; // point this to the next of my next
      length.Set(((length.Get())-1));
      return;
      }

    else if(pos == length.Get() - 1){
      MyInteger iterator = new MyInteger();
        iterator.Set(0);  // =1 because we are comparing with length which starts at 1
      Sequence tempSeq = this;
      while(tempSeq != null ){          // until the second last element of sequence
        if(iterator.Get() == pos-1){    // if the position is found
          tempSeq.next = null;    // create a new sequence
          }
        iterator.Set(((iterator.Get())+1));
        tempSeq = tempSeq.next;
      }
      length.Set(((length.Get())-1));
      return;
    }
    else{
      MyInteger iterator = new MyInteger();
        iterator.Set(0);   // =1 because we are comparing with length which starts at 1
      Sequence tempSeq = this;
      while(iterator.Get() < pos ){          // until the second last element of sequence
        if(iterator.Get() == (pos-1)){    // if the position is found
          tempSeq.next = tempSeq.next.next;    // create a new sequence
          }
        iterator.Set(((iterator.Get())+1));
        tempSeq = tempSeq.next;
      }
      length.Set(((length.Get())-1));
      return;
    }
  }

  //performing the deep copy
  public Sequence copy(){
    MyInteger iterator = new MyInteger();//for loop iteration
    iterator.Set(0);
    Sequence tempSeq = this;//getting reference to the current sequence
    Sequence seq = new Sequence();//local sequences used for iteration and swapping
    Sequence seq2 = new Sequence();//local sequences used for iteration and swapping
    while(tempSeq != null){//iterating through, extracting element and adding to the new sequence
      if ( (tempSeq.data instanceof MyChar) ){//if element is of MyChar type
        MyChar tempC = new MyChar();
        MyChar tempC2 = new MyChar();
        tempC = (MyChar)(tempSeq.data);
        tempC2.Set( (tempC.Get()) );
        seq.add(tempC2,iterator.Get());
        iterator.Set(((iterator.Get())+1));
      }
      else if(tempSeq.data instanceof MyInteger){//if element is of MyInteger type
        MyInteger tempI = new MyInteger();
        MyInteger tempI2 = new MyInteger();
        tempI = (MyInteger)(tempSeq.data);
        tempI2.Set(tempI.Get());
        seq.add(tempI2,iterator.Get());
        iterator.Set(((iterator.Get())+1));
      }
      else{//if element is of sequence type
            seq2 = (Sequence)(tempSeq.data);
            seq2 = seq2.copy();//call copy recursively
              seq.add(seq2,iterator.Get());
            iterator.Set(((iterator.Get())+1));
      }
      tempSeq = tempSeq.next;

    }
    return seq;
  }

  // print the data values(elements)
  @Override
  public void Print(){
    Sequence tempSeq = this;//get the reference to the first element
    System.out.print("[ " );
    while(tempSeq.next != null){
      tempSeq.data.Print();
      tempSeq = tempSeq.next;//move reference to the next element
      System.out.print(" ");
    }
    tempSeq.data.Print();//print the element virtually
    System.out.print(" ");
    System.out.print("]" );
  }

}
