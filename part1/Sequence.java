public class Sequence extends Element {

  Sequence next,prev;
  Element data;
  MyInteger length = new MyInteger;

  public Sequence(){
    next = null;
    data = 0;
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

  //return the number of elements in the sequence
  public int length(){
    return length.Get();
  }

  //add an element at the given position in the sequence
  public void add(Element elm, int pos){
    if(pos > length.Get()){
      System.err.println("pos is greater than the length of the sequence");
      System.exit(1);
    }
    else{
      int temp = 0;  // =1 because we are comparing with length which starts at 1
      Sequence tempSeq = this;
      while(temp <= pos ){          // until the second last element of sequence
        if(pos==temp){    // if the position is found
          Sequence obj = new Sequence();      // create a new sequence
          obj.data = elm;                     // assign elm to new sequence
          obj.next = tempSeq.next;            // assign next sequence of new obj to next of current sequence
          tempSeq.next = obj;
        }
        temp++;
        tempSeq = tempSeq.next;
      }
      length.Set((length.Get())++);
    }
  }

  // delete the element present at the given position if none then do nothing
  public void delete(int pos){
    if(pos > length.get()){
      System.err.println("pos > length, cannot delete");
    }
    else if(length.get() == 1){
      this.next = null;
      this.data = 0;
      length.Set((length.Get())--);
    }
    else{
      int temp = 0;  // =1 because we are comparing with length which starts at 1
      Sequence tempSeq = this;
      while(temp < pos ){          // until the second last element of sequence
        if((pos-1)==temp){    // if the position is found
          Sequence obj = new Sequence();      // create a new sequence
          obj = tempSeq.next;                     // assign elm to new sequence
          tempSeq.next = obj.next;
          obj.next = null;            // assign next sequence of new obj to next of current sequence
        }
        temp++;
        tempSeq = tempSeq.next;
      }
      length.Set((length.Get())--);
    }
  }

  // print the data value
  @Override
  public void Print(){
    System.out.print("[ " + data + " ]");
  }

}
