# Firebase

## Firebase Read & Write

```java
// 데이터베이스에 연결
FirebaseDatabase database = FirebaseDatabase.getInstance;

// getReference() 메소드를 통해 데이터베이스에 접근
DatabaseReference myRef = database.getReference();

// 또는 한 줄로 만들어 사용하는 것도 가능.
DatabaseReference database = FirebaseDatabase.getInstance().getReference();



// 데이터베이스에 값을 저장하는 방법은 다음과 같음.
// message라는 이름을 가진 Key의 Value를 "Hello, World"로 설정하라.라는 명령 :
DatabaseReference myRef = database.getReference("message")
myRef.setValue("Hello, World!");

/*
parentt_tree
 |
  ->   Key   : Value
  -> message : "Hello, World!")

와 같은 형식으로 저장됨.
*/


// Value가 추가될 때마다 호출되는 클래스
myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                textview1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



// 값이 추가되었을 때 한 번만 콜백 함수가 필요한 경우 사용
myRef.addListenerForSingleValueEvent()



// 리스너가 데이터베이스의 특정 부분에 도달하면 호출되는 클래스.
// 특정 부분의 하부 값이 변경되어도 호출된다.
myRef.ValueEventListener()



// 버튼을 클릭할 때 마다, textView에 입력된 값을 가져와 데이터베이스에 삽입하는 함수.
public void click1button(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(textView.getText().toString());
    }



// 
myRef.addValueEventListener(new ValueEventListener(){
    @Override
    public void onDataChange(DataSnapshot dataSnapshot){
        Object value = dataSnapshot.getValue(Object.class);
        textView.setText(value.toStiring());
    }

    @Override
    public void onCancelled(DatabaseError error){
        //실패할 경우의 값 호출        
    }

}
```


