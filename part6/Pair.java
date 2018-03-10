public class Pair extends Element{
  MyChar key;//for holding the key
  Element value;//for holding the value of the key

  //constructor
  public Pair(MyChar key, Element value){
    this.key = key;
    this.value = value;
  }

  //overridden print method
  @Override
  public void Print(){
    System.out.print("(");
    key.Print();
    System.out.print(" ");
    value.Print();
    System.out.print(")");
  }
}
