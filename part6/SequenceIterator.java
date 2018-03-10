class SequenceIterator{
  Sequence seq;

  public SequenceIterator(Sequence seq){
    this.seq = seq;
  }

  public boolean equal(SequenceIterator other){ // overloads the equal
      if(seq==null) return true;  // last element of a sequence is null
      else return false;
  }

  // increment to the next element in a sequence
  public SequenceIterator advance(){
    seq = seq.next; // move to next element in sequence
    return this;
  }

  // return the current element of the sequence
  public Element get(){
    return seq.data;
  }
}
