public class MyInteger extends Element {

  int data;

  // constructor
  public MyInteger(){
    data = 0;
  }

  //return the value of character
  public int Get(){
    return data;
  }

  // asssign value to data
  public void Set(int val){
    data = val;
  }

  // print the data value
  @Override
  public void Print(){
    System.out.print(data);
  }
}
