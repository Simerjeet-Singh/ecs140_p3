class Matrix extends Sequence{
  MyInteger row = new MyInteger();//for holding info of rows
  MyInteger col = new MyInteger();//for holding info of columns
  Sequence seq = new Sequence();//defining a sequence that holds the matrix

  //constructor
  public Matrix(int rowsize, int colsize){
    MyInteger i = new MyInteger();
    i.Set(0);
    row.Set(rowsize);
    col.Set(colsize);
    MyInteger localVal = new MyInteger();
    localVal.Set(0);
    while(i.Get()<rowsize*colsize){//initializing all the elements
      seq.add(localVal,0);//adding a new element at the start of the matrix
      i.Set(((i.Get())+1));//i++
    }
  }//done with constructor

  //set function
  public void Set(int row, int col, int value){
    //formula used: (row*col)+col;
    Sequence localSeq = seq;//creating a local sequence that holds reference for mat
    MyInteger localInt = new MyInteger();//creating a MyInteger object that would be added to matrix
    localInt.Set(value);
    MyInteger i = new MyInteger();
    i.Set(0);
    while(i.Get()<((((row)*(this.col.Get()))+col))){//iterating to get to the point where element has to be added
      localSeq = localSeq.next;
      i.Set(((i.Get())+1));//i++
    }
    (localSeq.data) = localInt;
  }

  //getting a value for a row and col using get(row, col)
  public int Get(int rowsize, int colsize){
    Sequence localSeq = seq;//sequence that holds reference to mat and iterates
    MyInteger localInt = new MyInteger();//for holding the value that needs to be returned
    MyInteger i = new MyInteger();//for iteration
    i.Set(0);
    while(i.Get()<(((rowsize)*(col.Get()))+colsize)){//iterate through the sequence to find the row,col of matrix
      localSeq = localSeq.next;
      i.Set(((i.Get())+1));
    }
    localInt = (MyInteger)(localSeq.data);//value found, now return
    return localInt.Get();
  }

  //finding sum of two matrices
  public Matrix Sum(Matrix mat){
    Sequence localSeq1 = seq;//for holding local reference to the first matrix
    Sequence localSeq2 = mat.seq;//holding local reference to the second matrix
    Matrix newMat = new Matrix(row.Get(), col.Get());//creating a matrix that needs to be returned
    MyInteger localInt1 = new MyInteger();//to holdthe value for first matrix
    MyInteger localInt2 = new MyInteger();//hold value from the second matrix
    MyInteger i = new MyInteger();//iterator
    i.Set(0);
    while(localSeq1 != null){//iterating over all the elements and adding them
      localInt1 = (MyInteger)(localSeq1.data);
      localInt2 = (MyInteger)(localSeq2.data);
      newMat.Set( ((i.Get())/(col.Get())) ,((i.Get())%(col.Get())), (localInt1.Get() + localInt2.Get()) );
      localSeq1 = localSeq1.next;//iterate to the next value of matrix1
      localSeq2 = localSeq2.next;//iterate to the next value of matrix2
        i.Set(((i.Get())+1));//i++
    }
    return newMat;
  }

  //calculating the product of two matrices
  public Matrix Product(Matrix mat){
    Sequence localSeq1 = seq;//holding reference to the first matrix
    Sequence localSeq2 = mat.seq;//holding reference to the second matrix
    Sequence localSeq3 = new Sequence();//creating a local sequence that would be used to hold result
    Matrix newMat = new Matrix(row.Get(), mat.col.Get());//matrix to be returned
    MyInteger localInt1 = new MyInteger();//holding value from the first matrix
    MyInteger localInt2 = new MyInteger();//holding value from the second matrix
    MyInteger rowm = new MyInteger();//holding the row value of the second matrix
    MyInteger colm = new MyInteger();//holding the col value of the second matrix
    MyInteger productInt = new MyInteger();//holding the result of each multiplication
    productInt.Set(0);
    MyInteger i = new MyInteger();//holds value of the first matrix's number of row
    i.Set(0);
    MyInteger j = new MyInteger();//holds value of the first matrix's number of col
    j.Set(0);
    MyInteger k = new MyInteger();//holds value of the second matrix's number of row
    k.Set(0);
    MyInteger l = new MyInteger();//holds value of the second matrix's number of col
    l.Set(0);
    MyInteger product = new MyInteger();
    product.Set(0);
    rowm.Set(mat.row.Get());//setting row value of matrix
    colm.Set(mat.col.Get());//setting col value of matrix
    if((col.Get()) != (rowm.Get())){
      System.out.println("Matrix dimensions incompatible for Product");
      System.exit(1);
    }
    else{
    for(i.Set(0); i.Get()<(row.Get()); i.Set(((i.Get())+1))){//for each row of matrix A
      for (j.Set(0); j.Get()<(colm.Get()); j.Set(((j.Get())+1))) {//for each col of matrix b
        product.Set(0);//calculating the product
        k.Set(0);
        l.Set(0);
        while((k.Get()<(col.Get())) && (l.Get()<(rowm.Get()))){
          product.Set( (product.Get())+ (Get(i.Get(),k.Get())*mat.Get(l.Get(),j.Get())));
          k.Set(((k.Get())+1));
          l.Set(((l.Get())+1));
        }
        (newMat).Set(i.Get(),j.Get(),product.Get());//return the matrix to be returned
      }
    }
    return newMat;
  }
  return newMat;
  }

  //printing the matrix
  @Override
  public void Print(){
    MyInteger i = new MyInteger();//for iteration
    i.Set(1);
    Sequence localSeq = seq;//holding the reference of the matrix
    System.out.print("[" );//start printing iterating over all the elements of sequence
    while(i.Get()<(row.Get()*col.Get())){

      System.out.print(" ");
      (localSeq.data).Print();
      localSeq = localSeq.next;
      if(i.Get()%(col.Get()) == 0){
        System.out.print(" ]" );
        System.out.println("" );
        System.out.print("[" );
      }
      i.Set(((i.Get())+1));//i++
    }
    System.out.print(" ");
    (localSeq.data).Print();
      System.out.println(" ]" );
  }
}
