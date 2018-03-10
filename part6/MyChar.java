public class MyChar extends Element {
  char data;

  // constructor
  public MyChar(){
    data = 'O';
  }

  //return the value of character
  public char Get(){
    return data;
  }

  // asssign value to data
  public void Set(char val){
    data = val;
  }

  // print the data value
  @Override
  public void Print(){
    System.out.print("'" + data + "'");
  }

}
