public class Sequence extends Element {

  Sequence next;      // points to the next element in the sequence
  Element data; // value store at current location in sequence
  MyInteger length = new MyInteger(); // length of the Sequence

  public Sequence(){ // constructor
    next = null;
    data = null;
    length.Set(0);
  }

// constructor assign values to members
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

  //returns the element at given location
  public Element index(int pos){

    MyInteger iterator = new MyInteger(); // iterator
    iterator.Set(0);
    Sequence tempSeq = this;  // temperary sequence to iterate through
    if(pos > length.Get()){
      System.err.println("pos is greater than the length of the sequence");
      System.exit(1);
      return this.data;
    }
    else{
      while(iterator.Get() <= pos){ // until we find the postion
        if(iterator.Get() == pos){ // if position found
          return tempSeq.data;
        }
        tempSeq = tempSeq.next; // move to next element in sequence
        iterator.Set(((iterator.Get())+1)); // iterator++
      }
    }
    return this.data; // return null if not found
  }

  // flatten the sequence into one sequence of elements and remove
  // all sequences within a sequence
  public Sequence flatten(){
    MyInteger iterator = new MyInteger();  // iterator
    iterator.Set(0);
    Sequence tempSeq = this;  // temperrary sequence to iterate through
    Sequence seq = new Sequence(); // temperrary sequence to swap
    Sequence seq2 = new Sequence(); //// temperrary sequence to swap
    if(this.next == null) { // if only one value
      return this;
    }

    else{
      while(tempSeq != null){ // until end of the sequence
        if ( (tempSeq.data instanceof MyChar) || (tempSeq.data instanceof MyInteger)){ // the element is a char or int
          seq.add((tempSeq.data),iterator.Get());  // add to temp seq
          iterator.Set(((iterator.Get())+1)); //iterator ++
        }
        else{ // if element is a sequence
          seq2 = (Sequence)(tempSeq.data); // collect the sequence element
          seq2 = seq2.flatten(); // recursively call the flatten funcion
          while(seq2 != null){
            seq.add(seq2.data,iterator.Get());  // add the elements into temp sequence
            seq2 = seq2.next;
            iterator.Set(((iterator.Get())+1));
          }
        }
        tempSeq = tempSeq.next; // move to next element

      }
      return seq;
    }
  }

  //return the number of elements in the sequence
  public int length(){
    MyInteger tLength = new MyInteger(); // lenght of the sequence
    Sequence tempSeq = this; // temp sequence to iterate
    while(tempSeq.next != null){ // until end of sequence
      tempSeq = tempSeq.next;
      tLength.Set(((tLength.Get())+1)); // length++
    }
    tLength.Set(((tLength.Get())+1)); //length++
    return tLength.Get();
  }

  //add an element at the given position in the sequence
  public void add(Element elm, int pos){
    MyInteger iterator = new MyInteger(); // iterator
    iterator.Set(0);
    if(pos > length.Get()){
      System.err.println("pos is greater than the length of the sequence");
      System.exit(1);
    }
    else if(length.Get() == 0){ // if no element in the sequence
      this.next = null;
      this.data = elm;
      length.Set(((length.Get())+1));
    }
    else if(pos == 0){ // if postion is the first position in the sequence
      Sequence obj = new Sequence(elm, this); // construct a temperary sequence
      Element tempData = this.data; //to swap data
      this.data = elm; // swap data
      obj.data = tempData;// swap data

      Sequence tempSeq = obj; // to swap next links
      obj.next = this.next; // swap link
      this.next = obj; // swap next link
      length.Set(((length.Get())+1));
    }
    else{ // if position is 0 < pos < length
      Sequence tempSeq = this;
      Sequence obj = new Sequence(elm, null);
      while(iterator.Get() != pos){ // until end of sequence
        if(iterator.Get() == (pos-1)){ // found postion to add
          obj.next = tempSeq.next; // swap the next links
          tempSeq.next = obj; // swap the links
        }
        tempSeq = tempSeq.next;  // next element in sequence
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
    else if(length.Get() == 1){ // if only one element in the sequence
      this.next = null;
      this.data = null;
      length.Set(((length.Get())-1));
      return;
    }
    else if(pos == 0){ // if deleting first element
      // first copy everything from second sequence to first and then delete second
      // sequence
      this.data = this.next.data;  // assign the data of next Sequence
      this.next = this.next.next; // point this to the next of my next
      length.Set(((length.Get())-1));
      return;
      }
    else if(pos == length.Get() -1){ // if the last element
      MyInteger iterator = new MyInteger();
      iterator.Set(0);
      Sequence tempSeq = this;
      while(iterator.Get() <= pos ){          // until the second last element of sequence
        if(iterator.Get() == pos-1){    // if the position is found
          tempSeq.next = null;    // point the next to null
          }
        iterator.Set(((iterator.Get())+1));
        tempSeq = tempSeq.next;
      }
      length.Set(((length.Get())-1));
      return;
    }
    else{ // if element is 0 < pos < length
      MyInteger iterator = new MyInteger();
      iterator.Set(0);
      Sequence tempSeq = this;
      while(iterator.Get() < pos ){          // until the second last element of sequence
        if(iterator.Get() == (pos-1)){    // if the position is found
          tempSeq.next = tempSeq.next.next;    // point the next to the next of my next
        }// element deleted
        iterator.Set(((iterator.Get())+1));
        tempSeq = tempSeq.next;
      }
      length.Set(((length.Get())-1));
      return;
    }
  }

  // deep copy of a sequence and return a sequence
  public Sequence copy(){//
    MyInteger iterator = new MyInteger(); // iterator
    iterator.Set(0);
    Sequence tempSeq = this; // temporary sequence to iterate
    Sequence seq = new Sequence(); // temporary sequences to copy
    Sequence seq2 = new Sequence(); // temporary sequences to copy

    while(tempSeq != null){ // until the end of the sequence
      if ( (tempSeq.data instanceof MyChar) ){ // if element is char
        MyChar tempC = new MyChar(); //temp char to copy
        MyChar tempC2 = new MyChar(); // temp char to copy
        tempC = (MyChar)(tempSeq.data); // copy the current element
        tempC2.Set( (tempC.Get()) ); // assign to a new char not reference
        seq.add(tempC2,iterator.Get()); // add to temp sequence
        iterator.Set(((iterator.Get())+1)); // iterator++
      }
      else if(tempSeq.data instanceof MyInteger){ // if elemetn is an integer
        MyInteger tempI = new MyInteger(); //temp int to copy
        MyInteger tempI2 = new MyInteger();  //temp int to copy
        tempI = (MyInteger)(tempSeq.data);// copy the current element
        tempI2.Set(tempI.Get());// assign to a new int not reference
        seq.add(tempI2,iterator.Get());// add to temp sequence
        iterator.Set(((iterator.Get())+1));
      }
      else{ // if the element is a sequence
            seq2 = (Sequence)(tempSeq.data); // exctract the sequence into a new sequence
            seq2 = seq2.copy();  // recursively call copy to copy integers and char values not references
              seq.add(seq2,iterator.Get());  // add to temp sequence
            iterator.Set(((iterator.Get())+1));
      }
      tempSeq = tempSeq.next;

    }
    //System.out.println("Inside flatten4");
    return seq;  // return the temp sequence
  }


  // print the data value
  @Override
  public void Print(){
    Sequence tempSeq = this;  // temp sequence to iterate
    System.out.print("[ " );
    while(tempSeq.next != null){ // until the end of the sequence
      tempSeq.data.Print();
      tempSeq = tempSeq.next; // move to next element of the sequence
      System.out.print(" ");
    }
    tempSeq.data.Print(); // call the abstract print function
    System.out.print(" ");
    System.out.print("]" );
  }

}
