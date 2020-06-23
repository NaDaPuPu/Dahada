# Dahada(다하다) 앱 프로젝트

이 프로젝트는 상명대학교 '스마트 모바일 프로그램 설계' 과목의 D2W팀의 팀 프로젝트를 위한 GitHub 프로젝트입니다.

다하다는 다이어트, 운동, 식단 등을 관리하기 위한 기능을 넣어 간편하게 관리할 수 있는 앱을 목표로 하고있습니다.
'다하다'라는 이름은 '다이어트 하다'와 '최선을 다하다'의 뜻이 담긴 중의적인 이름입니다.
앱이 모든 것을 관리해줄 순 없지만 사용자의 건강을 위하여 할 수 있는 노력을 최대한 담고싶습니다.

## 코드 형식 ##

프로젝트 작성 시 다음과 같은 형식을 지켜주셨으면 합니다.

* 변수 명은 소문자로 시작, 다음에 오는 단어는 대문자
* 파일 명은 그 파일이 무엇인지 바로 알 수 있도록 작성(ex. fragment_mypage.xml)
* 메소드와 메소드 사이에 한 줄 띄어쓰기.
* 커밋은 변경사항 생기면 자주자주 해주기, 푸시를 생활화합시다.

> 'Ctrl + S는 버릇이 되어야합니다.' -준희 조-

readme.md 파일도 추가되었다! 와! 우리는 이제 GitHub를 제대로 쓸 수 있게될거야!

# 목차
1. 다하다 소개
2. 추천 레시피
3. 내 레시피
4. 쇼핑하기
5. 내 운동코스
6. 추천 운동법
7. 커뮤니티
8. 캘린더
9. 마이페이지
10. 이외의 다양한 기능등
11. 활동내역
12. 느낀 점

# 1. 다하다 소개
다하다는 다이어트, 운동, 식단 등을 관리하기 위한 기능을 넣어 간편하게 관리할 수 있는 앱을 목표로 하고있습니다.
'다하다'라는 이름은 '다이어트 하다'와 '최선을 다하다'의 뜻이 담긴 중의적인 이름입니다.

<img src="https://user-images.githubusercontent.com/62593236/85401991-e54ced80-b595-11ea-8633-38ae547426e9.png" ></img>

# 2. 추천 레시피

RecipeItem 모델에 넣고자하는 데이터를 생성하고, Getter Setter로 값을 주고받아오게 한다.
```java
public class RecipeItem {
    private String RecipeImage;
    private String RecipeName;
    private int RecipeKcal;
    private int RecipeGram;
    private String RecipeEx1;
    private String RecipeEx2;
```
```java
public String getRecipeName() {
        return RecipeName;
    }
    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }
```
Adapter부분은 RecyclerView.Adapter를 상속받고 생성자를 지정해준 뒤 
RecyclerView에 들어갈 ViewHolder, 그리고 ViewHolder에 들어갈 아이템들을 지정해준다.
Adapter 클래스 상속 시 구현해야하는 함수 onBindViewHolder는 실제 각 ViewHolder에 데이터를 연결해주는 함수이다.
후에 getItemCount() 로 전체 아이템 갯수를 리턴한다.
```java
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {

    private ArrayList<RecipeItem> arrayList;
    private Context context;

    public ItemAdapter(ArrayList<RecipeItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
```
```java
 @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getRecipeImage())
                .into(holder.iv_picture);
        holder.tv_id.setText(arrayList.get(position).getRecipeName());
        holder.tv_cal.setText(String.valueOf(arrayList.get(position).getRecipeKcal()));

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
```
```java
public class CustomViewHolder extends RecyclerView.ViewHolder {

        CardView item;
        ImageView iv_picture;
        TextView tv_id;
        TextView tv_cal;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.item = itemView.findViewById(R.id.cardview_item);
            this.iv_picture = itemView.findViewById(R.id.iv_picture);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_cal = itemView.findViewById(R.id.tv_cal);

        }
    }
```
어댑터를 통해 Firebase의 데이터 테이블을 받아와 프래그먼트에 나타나도록 합니다.
```java
public class RecipeFragment1 extends Fragment  {
    private ItemAdapter adapter;
    public RecipeFragment1() { }
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecipeItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recipe_1, container, false);
        Log.d("test", "check2");
        recyclerView = view.findViewById(R.id.rcp_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        Log.d("test", "check3");
        databaseReference = database.getReference("RecipeItem"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RecipeItem recipeItem = snapshot.getValue(RecipeItem.class);
                    arrayList.add(recipeItem);
                }
                Log.d("TEST", String.valueOf(arrayList.size()));
                adapter = new ItemAdapter(arrayList, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Fragment1", String.valueOf(databaseError.toException()));
            }
        });
        Log.d("test", "check5");
        return view;
    }
}
```
Adapter에서 인텐트를 생성하여 아이템을 클릭 할 때 그 포지션의 값을 받아준 뒤 RecipeDetails1 Activity에서 그 값을 받아와 상세페이지에 띄워지도록 하였다.

```java
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recipe_list_item, parent, false);
        final CustomViewHolder holder = new CustomViewHolder(view);


        holder.item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDetails1.class);
                intent.putExtra("position",holder.getAdapterPosition());
                intent.putExtra("Image",holder.getAdapterPosition());
                context.startActivity(intent);
                Toast.makeText(context, "Test click"+String.valueOf(holder.getAdapterPosition()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }
```

```java
    Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        Log.d("RecipeDetails", "position : " + position);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("RecipeItem/" + position); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecipeItem recipeItem = dataSnapshot.getValue(RecipeItem.class);
                assert recipeItem != null;
                rcpName.setText(recipeItem.getRecipeName());
                rcpKcal.setText(recipeItem.getRecipeKcal() + "kcal");
                rcpGram.setText(recipeItem.getRecipeGram() + "g");
                rcpEx.setText(recipeItem.getRecipeEx1());
                rcpEx2.setText(recipeItem.getRecipeEx2());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("RecipeDetails1", String.valueOf(databaseError.toException()));
            }

        });
```

<div>
<img src="https://user-images.githubusercontent.com/62593236/85388415-07884080-b581-11ea-9fbd-ba48e53ce82e.png" ></img>
<img src="https://user-images.githubusercontent.com/62593236/85388440-0e16b800-b581-11ea-8b74-c3b1ca2da49b.png" ></img>
</div>

# 3.내 레시피
## 3.1 데이터부분

DietItem이라는 자바클래스를 하나 만들어 데이터베이스에 넣어줄 변수들을 넣어준 뒤
구조체를 만들고
```java
public class DietItem {

    private String DietImage;
    private String DietName;
    private int DietKcal;

    public DietItem(String dietImage, String dietName, int dietKcal) {
        DietImage = dietImage;
        DietName = dietName;
        DietKcal = dietKcal;
    }
```
getter/setter를 만들어 준다.

```java
    public DietItem() {

    }


    public String getDietImage() {
        return DietImage;
    }

    public void setDietImage(String dietImage) {
        DietImage = dietImage;
    }

    public String getDietName() {
        return DietName;
    }

    public void setDietName(String dietName) {
        DietName = dietName;
    }

    public int getDietKcal() {
        return DietKcal;
    }

    public void setDietKcal(int dietKcal) {
        DietKcal = dietKcal;
    }
}

```

자바클래스로 DietAdapter라는 파일을 만들어주고 Recyclerview.Adapter를 extends해준 뒤
alt+insert 버튼을 눌러 필요한 클래스와 메서드들을 만들어 준다.
```java
public class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolderDiet> {
```
그리고 ArrayList로 위에서 만들어준 변수들을 불러와준다.
```java
    private ArrayList<DietItem> arrayList;
    private Context context;
    private TextView textView_diet;



    public DietAdapter(ArrayList<DietItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
```


맨 밑에 만들어진 클래스 안에 새 변수들을 만들고
```java
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolderDiet extends RecyclerView.ViewHolder {
        ImageView diet_iv_picture;
        TextView diet_tv_id;
        TextView diet_tv_cal;
        LinearLayout itemLinear_diet;
```
diet_list_item.xml파일에 만들었던 id값들을 가져와준다.
```java
        public CustomViewHolderDiet(@NonNull View itemView) {
            super(itemView);
            this.diet_iv_picture = itemView.findViewById(R.id.diet_iv_picture);
            this.diet_tv_id = itemView.findViewById(R.id.diet_tv_id);
            this.diet_tv_cal = itemView.findViewById(R.id.diet_tv_cal);
            this.itemLinear_diet = itemView.findViewById(R.id.itemLinear_diet);
        }
    }
}
```

onCreateViewHolder는 리스트 아이템에 데이터들을 보여주기 위한 기능이다.
변수 view를 만들고 diet_lis_item.xml파일을 inflate해준다.
```java
   @NonNull
    @Override
    public CustomViewHolderDiet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_list_item, parent, false);
        final CustomViewHolderDiet holder = new CustomViewHolderDiet(view);


        holder.itemLinear_diet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(context, "아이템 클릭됨"+String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }
```


마지막으로 onBindViewHolder에 실제 데이터들을 매칭시켜주는 코딩을 해준다.
```java

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.CustomViewHolderDiet holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getDietImage())
                .into(holder.diet_iv_picture);
        holder.diet_tv_id.setText(arrayList.get(position).getDietName());
        holder.diet_tv_cal.setText(String.valueOf(arrayList.get(position).getDietKcal()));

    }
```

이제 FragmentVeg라는 자바파일에 만들어준 Adapter와 변수들을 불러오고
파이어베이스 연동을 위한 FirebaseDatabase, DatabaseReference 변수를 선언한다. 
```java
public class FragmentVeg extends Fragment {
    private DietAdapter adapter;

    public FragmentVeg() {
    }
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DietItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

```

변수 view에 fragment_diet_veg를, 변수 recyclerview에는 xml파일 안의 recyclerview id값을 넣어준다.

```java
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_veg, container, false);

        Log.d("test","check2");
        recyclerView = view.findViewById(R.id.recyclerview_diet_veg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
```

FirebaseDatabse.getInstance()를 통해 Firebase의 Database를 연동시켜주고
database.getReference를 통해 DietItem이라는 DB테이블 만들어준다.
```java
        database = FirebaseDatabase.getInstance(); 
        Log.d("test","check3");
        databaseReference = database.getReference("DietItem");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DietItem dietItem = snapshot.getValue(DietItem.class);
                    arrayList.add(dietItem);
                }
                Log.d("TEST",String.valueOf(arrayList.size()));
                adapter = new DietAdapter(arrayList,getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Fragment1", String.valueOf(databaseError.toException()));
            }
        });

```

마지막으로 변수 view를 return해주면 DB가 연동된다.
```java
return view;
```
<div>
<img src="https://user-images.githubusercontent.com/62593277/85403495-6907d980-b598-11ea-8e85-b3f6ee16415a.png"width="30%"></img>
</div>



## 3.2 계산기
피연산자 2개를 생성하고 각 연산자 버튼을 생성한다. 
어떤 연산자가 선택되었는지 확인하기 위해 int형 타입변수를 사용한다.

```java
    Button add,sub,mul,div,remainder,del;
    Button result;

    String history = "";
    String number1 = "";
    String number2 = "";

    int type;

    int ADD = 0;
    int SUB = 1;
    int MUL = 2;
    int DIV = 3;
    int REMAINDER = 4;
    double d1;
    double d2;
```

.setOnClickListener(mListener);는 각 연산을 수행하기 위한 리스너이다.
```java
	et_show = findViewById(R.id.et_show);
        et_result = findViewById(R.id.et_result);
        et_result.setText("");
        add = findViewById(R.id.btn_add);
        sub = findViewById(R.id.btn_sub);
        mul = findViewById(R.id.btn_mul);
        div = findViewById(R.id.btn_div);
        remainder = findViewById(R.id.btn_remainder);
        del = findViewById(R.id.btn_del);

        result = findViewById(R.id.btn_result);

        add.setOnClickListener(mListener);
        sub.setOnClickListener(mListener);
        mul.setOnClickListener(mListener);
        div.setOnClickListener(mListener);
        remainder.setOnClickListener(mListener);
        result.setOnClickListener(mListener);
        del.setOnClickListener(mListener);
```
Clear버튼에는 onClick이벤트를 통해 초기화 하는 메소드를 작성한다.
```java
        Button clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_show.setText("");
                et_result.setText("");
                d1 = d2 = 0;
                history = number1 = number2 = "";
            }
        });
        Button plus_minus = findViewById(R.id.btn_plus_minus);
        plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //실수인지 정수인지 판단해서 부호 바꾸기
                if( ( (Double.parseDouble(et_result.getText().toString()))
                        - ((int)Double.parseDouble(et_result.getText().toString())) ) == 0.0 )
                {
                    et_result.setText( "" + (Integer.parseInt(et_result.getText().toString()) * -1) );
                }
                else {
                    et_result.setText( "" + (Double.parseDouble(et_result.getText().toString()) * -1) );
                }

            }
        });
    }
```
switch문을 통해 해당 버튼을 눌렀을 때마다 연산기능이 수행된다.
```java
Button.OnClickListener mListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(et_result.getText().toString() == null)
            { Toast.makeText(DietActivity.this,"수를 입력하세요",Toast.LENGTH_SHORT).show(); }

            switch (v.getId()) {
                case R.id.btn_add :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " + ";
                    et_show.setText(history);
                    et_result.setText("");
                    type = ADD;
                    break;

                case R.id.btn_sub :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " - ";
                    et_show.setText(history);
                    et_result.setText("");
                    type = SUB;
                    break;

                case R.id.btn_mul :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " * ";
                    et_show.setText(history);
                    et_result.setText("");
                    type = MUL;
                    break;

                case R.id.btn_div :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " / ";
                    et_show.setText(history);
                    et_result.setText("");
                    type = DIV;
                    break;

                case R.id.btn_remainder :
                    number1 = et_result.getText().toString();
                    history = et_result.getText().toString() + " % ";
                    et_show.setText(history);
                    et_result.setText("");
                    type = REMAINDER;
                    break;

                case R.id.btn_del :
                    String del_number = et_result.getText().toString();
                    Toast.makeText(DietActivity.this,del_number,Toast.LENGTH_SHORT).show();

                    et_result.setText(del_number.substring(0,del_number.length() - 1));
                    break;

                case R.id.btn_result :
                    double result = 0;
                    Toast.makeText(DietActivity.this, "결과", Toast.LENGTH_SHORT).show();
                    number2 = et_result.getText().toString();
                    history = history + et_result.getText().toString();
                    et_show.setText(history);
                    d1 = Double.parseDouble(number1);
                    d2 = Double.parseDouble(number2);

                    if(type == ADD) {
                        result = d1 + d2;
                        et_result.setText("" + result);
                    } else if (type == SUB) {
                        result = d1 - d2;
                        et_result.setText("" + result);
                    }else if (type == MUL) {
                        result = d1 * d2;
                        et_result.setText("" + result);
                    }else if (type == DIV) {
                        result = d1 / d2;
                        et_result.setText("" + result);
                    }else if (type == REMAINDER) {
                        result = d1 % d2;
                        et_result.setText("" + result);
                    }
                    number1 = et_result.getText().toString();
                    break;
            }
        }
    };
    public void onClick (View v)
    {
        switch(v.getId()){
            case R.id.btn0 : et_result.setText(et_result.getText().toString() + 0); break;
            case R.id.btn1 : et_result.setText(et_result.getText().toString() + 1); break;
            case R.id.btn2 : et_result.setText(et_result.getText().toString() + 2); break;
            case R.id.btn3 : et_result.setText(et_result.getText().toString() + 3); break;
            case R.id.btn4 : et_result.setText(et_result.getText().toString() + 4); break;
            case R.id.btn5 : et_result.setText(et_result.getText().toString() + 5); break;
            case R.id.btn6 : et_result.setText(et_result.getText().toString() + 6); break;
            case R.id.btn7 : et_result.setText(et_result.getText().toString() + 7); break;
            case R.id.btn8 : et_result.setText(et_result.getText().toString() + 8); break;
            case R.id.btn9 : et_result.setText(et_result.getText().toString() + 9); break;
            case R.id.btndot : et_result.setText(et_result.getText().toString() + "."); break;
        }


    }
```

<img src="https://user-images.githubusercontent.com/62593236/85388920-add44600-b581-11ea-8231-2a8ee7d45acf.png"></img>

# 4.쇼핑하기
ShoppingActivity 파일에
TabLayout을 사용하여 상단 탭을 마켓, 닭가슴살, 샐러드로 구분해준다.
```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_shopping);

        TabLayout tabLayout = findViewById(R.id.shop_tablayout);

        tabLayout.addTab((tabLayout.newTab().setText("마켓")));
        tabLayout.addTab((tabLayout.newTab().setText("닭가슴살")));
        tabLayout.addTab((tabLayout.newTab().setText("샐러드")));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

```


shop_sectionPage 변수 안에 Shop_SectionPage 자바파일을 불러온다.
```java
shop_sectionPage = new Shop_SectionPage(getSupportFragmentManager(), tabLayout.getTabCount());
```java


shop_viewPager.xml의 id값을 불러오고 shop_sectionPage를 Adapt해준다.
```java
 shop_viewPager = findViewById(R.id.shop_viewPager);
 shop_viewPager.setAdapter(shop_sectionPage);
  shop_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
```



addOnTabSelectedListner를 사용해 탭을 선택할 수 있는 기능을 넣어준다.
```java
tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                shop_viewPager.setCurrentItem(tab.getPosition());
                shop_sectionPage.notifyDataSetChanged();
            }

```




Uri.pars를 사용해 버튼클릭 시 해당 사이트로 연동시켜준다.
```java
 @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_shop_1, container, false);
        Button btn_shop_gmarket = (Button) view.findViewById(R.id.btn_shop_gmarktet);
        btn_shop_gmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gmarket.co.kr"));
                startActivity(myIntent);
            }
        });
```
<div>
<img src="https://user-images.githubusercontent.com/62593277/85402202-39f06880-b596-11ea-91bf-3e42b5f6615a.png"width="30%"></img>
</div>

# 5. 내 운동코스

내 운동코스 기능은 Google Map API를 사용하여 제작되었다. 간단한 코스 표시를 할 수 있다.

아래의 내용을 Gradle에 추가한다.
```java
	implementation 'com.google.android.gms:play-services-maps:17.0.0'
    implementation 'com.google.android.gms:play-services-location:17.0.0'
```

아래의 내용은 Manifest에 추가하는 내용이다.
```java
	<uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

	<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="(API KEY를 입력하세요)"/>

```

먼저 필요한 변수, 상수들을 private로 선언한다.
```java
	private static final String TAG = "MapActivity";
    private GoogleMap map;
    private CameraPosition cameraPosition;

    private ConstraintLayout courseEdit;
    private Button courseButton, saveButton, cancelButton;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private LatLng defaultLocation = new LatLng(37.56, 126.97); // 서울
    private LatLng beforeLocation;

    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints = new ArrayList<>();

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab1, fab2;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int DEFAULT_ZOOM = 15;
    private boolean locationPermissionGranted;

    private Location lastKnownLocation;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
```

Google Map은 준비되었을 때, onMapReady를 호출한다.
지도를 표시할 때 권한 확인과 현재 위치로 설정, 지도 터치 이벤트를 추가한다.
```java
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        getLocationPermission(); // 권한 확인

        updateLocationUI(); //

        getDeviceLocation();

        map.setOnMapClickListener(this);
    }
```
아래는 위치 권한 요청, 요청 응답 결과 확인, 위치 서비스 준비 메서드이다.
```java
	private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                    updateLocationUI();
                }
            }
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
```

위치 권한이 있을 시에 현재 위치를 찾으면, 현재 위치로 지도를 이동시킨다.

```java
	private void getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // 현재 위치로 지도의 카메라 위치를 변경
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
```

화면이 꺼지거나, 잠금 상태가 되었을 시에 마지막 위치를 저장하여 다시 표시할 때 위치를 다시 불러오지 않아도 지도 위치를 설정한다.
```java
	protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }
```
맵을 터치 시 빨간 핀을 표시하도록 하였다.
또한, 지도 위에 FloatingActionButton을 추가하여 검은 핀을 표시할 수 있도록 하였다.
검은 핀의 LatLng값은 ArrayList에 추가되며, 핀과 핀 사이를 PolyLine을 그려서 경로 표시를 해주었다.
FloatingActionButton을 누를 시 애니메이션도 추가하였다.
```java
	public void onMapClick(LatLng latLng) {
        if (courseEdit.getVisibility() == View.VISIBLE) {
            beforeLocation = latLng;
            Log.d("onMapClick", beforeLocation + "");
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            map.clear();

            map.addMarker(markerOptions);

            polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.RED);
            polylineOptions.width(5);
            polylineOptions.addAll(arrayPoints);
            if (arrayPoints.size() > 0) {
                polylineOptions.add(arrayPoints.get(0));
            }
            map.addPolyline(polylineOptions);

            for (int i = 0; i < arrayPoints.size(); i++) {
                markerOptions.position(arrayPoints.get(i));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.round_location_on_black_24dp));
                map.addMarker(markerOptions);
            }
        }
    }

	public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab1:
                anim();
                break;
            case R.id.fab2:
                anim();
                map.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(beforeLocation);
                map.addMarker(markerOptions);

                for (int i = 0; i < arrayPoints.size(); i++) {
                    markerOptions.position(arrayPoints.get(i));
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.round_location_on_black_24dp));
                    map.addMarker(markerOptions);
                }

                polylineOptions = new PolylineOptions();
                polylineOptions.color(Color.RED);
                polylineOptions.width(5);
                arrayPoints.add(beforeLocation);
                polylineOptions.addAll(arrayPoints);
                polylineOptions.add(arrayPoints.get(0));
                map.addPolyline(polylineOptions);

                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            fab2.startAnimation(fab_close);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab2.startAnimation(fab_open);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399902-88036d00-b592-11ea-81bb-04eb8843bc69.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399904-88036d00-b592-11ea-9be9-400ff644a040.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399906-889c0380-b592-11ea-8de4-215b36858bb6.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399907-889c0380-b592-11ea-9c4a-96904d450a13.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399908-89349a00-b592-11ea-97c8-55cc801d4484.jpg">
</div>



# 6.추천 운동법

각 카테고리마다 데이터 조사를 거쳐 적절한 데이터를 삽입하여 목록 형식으로 보일 수 있도록 했다.
탭을 이용하여 섹션을 나눈 후 뷰페이저에 프래그먼트를 넣어 탭을 이동할 때마다 프래그먼트가 바뀌도록 해놓았다.
```java
@Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RecExerciseFragment1 recExerciseFragment1 = new RecExerciseFragment1();
                return recExerciseFragment1;
            case 1:
                RecExerciseFragment2 recExerciseFragment2 = new RecExerciseFragment2();
                return recExerciseFragment2;
            case 2:
                RecExerciseFragment3 recExerciseFragment3 = new RecExerciseFragment3();
                return recExerciseFragment3;
            case 3:
                RecExerciseFragment4 recExerciseFragment4 = new RecExerciseFragment4();
                return recExerciseFragment4;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return num;
    }
```
RecyclerView Adapter를 통해 데이터가 정렬되어 나타나게 하였습니다.
```java
@Override
    public void onBindViewHolder(@NonNull RecEx_RecyclerAdapter.VideoViewHolder holder, int position) {
        holder.videoWeb.loadData(movieList.get(position).getVideoId(), "text/html" , "utf-8" );
        holder.videoname.setText(movieList.get(position).getVideoname());
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;
        TextView videoname;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);
            this.videoname = itemView.findViewById(R.id.rec_ex_name);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {
            } );
        }
    }
```
모델을 만들어 영상링크와 이름을 넣을 수 있게 해준 뒤, 백터를 이용해 모델을 가져와 리사이클러뷰에 데이터가 정렬되도록 하였습니다.
유투브 동영상은 WebView 위젯을 통해 삽입하였습니다. 
```java
recyclerView = view.findViewById(R.id.rec_ex_2_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3VouSaW_LPw\" " +
                "frameborder=\"0\" allowfullscreen></iframe>", "전신 다이어트 유산소운동 [홈트레이닝]") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VNQpP6C1fJg\" " +
                "frameborder=\"0\" allowfullscreen></iframe>", "집에서 하는 유산소운동 다이어트 [칼소폭] 30 MIN FULL BODY") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/swRNeYw1JkY\" " +
                "frameborder=\"0\" allowfullscreen></iframe>", "하루 15분! 전신 칼로리 불태우는 다이어트 운동 15 MIN FULL BODY") );
        movies.add( new Movie("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MMswNnLdHso\" " +
                "frameborder=\"0\" allowfullscreen></iframe>", "단기간 살빠지는 최고의 운동 [칼소폭2] 30 MIN FAT BURNING CARDIO WORKOUT") );
        
        RecEx_RecyclerAdapter recEx_recyclerAdapter = new RecEx_RecyclerAdapter(movies);
        recyclerView.setAdapter(recEx_recyclerAdapter);

        return view;
```
전신, 상체, 하체로 나누어 자기가 원하는 카테고리만 보면서 빠르게 정보를 얻을 수 있게 하였습니다.

<img src="https://user-images.githubusercontent.com/62593236/85388641-4918eb80-b581-11ea-8beb-234bf0b32775.png"></img>

# 7.커뮤니티

Post model을 생성해 firebase에 데이터가 저장될 틀을 구성한다.
```java
public class Post {

        private String documentId;
        private String title;
        private String contents;
        @ServerTimestamp
        private Date date;

    public Post() { }

    public Post(String documentId, String title, String contents) {
        this.documentId = documentId;
        this.title = title;
        this.contents = contents;
    }
        public String getDocumentId() {
        return documentId;
    }
        public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    
        public String getTitle() {
        return title;
    }
        public void setTitle(String title) {
        this.title = title;
    }
    
        public String getContents() {
        return contents;
    }
        public void setContents(String contents) {
        this.contents = contents;
    }
    
        public Date getDate() {
        return date;
    }
        public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "documentId='" + documentId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                '}';
    }

}
```
FirebaseID로 아이디들을 추가해준다.
```java
public class FirebaseID {
    public static String user = "user";
    public static String post = "post";
    public static String documentId = "documentId";
    public static String title = "title";
    public static String contents = "contents";
    public static String timestamp = "timestamp";
}
```

Post Activity에 onClick이벤트를 생성하여 저장버튼을 누르면 documentId,title,contents,timestamp 의 데이터가 FirebaseFirestore에 저장되도록 하였다.
```java
findViewById(R.id.post_save_button).setOnClickListener(this);

        if (mAuth.getCurrentUser() != null) {
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        }
                    });
        }
```
```java
@Override
    public void onClick(View v) {
        if (mAuth.getCurrentUser() != null) {
            String postId = mStore.collection(FirebaseID.post).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentId, mAuth.getCurrentUser().getDisplayName());
            data.put(FirebaseID.title, mTitle.getText().toString());
            data.put(FirebaseID.contents, mContents.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.post).document(postId).set(data, SetOptions.merge());
            finish();
        }
    }
```

PostAdapter를 통해 데이터를 연결해주고
```java
 @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        final Post post = datas.get(position);
        holder.documentid.setText(post.getDocumentId());
        holder.title.setText(post.getTitle());
        holder.contents.setText(post.getContents());
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView documentid;
        private TextView title;
        private TextView contents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            documentid = itemView.findViewById(R.id.item_post_nickname);
            title = itemView.findViewById(R.id.item_post_title);
            contents = itemView.findViewById(R.id.item_post_contents);
        }
    }
```

Community Activity에는 액티비티 시작 시 mDatas 리스트가 보이게 하였다.
```java
@Override
    protected void onStart() {
        super.onStart();
        mlogingData = new ArrayList<>();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                //여기서부터 실시간으로 정보 불러오는 코드
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String user = String.valueOf(shot.get(FirebaseID.user));
                                String title = String.valueOf(shot.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(documentId, title, contents);
                                mDatas.add(data);
                            }
                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerview.setAdapter(mAdapter);
                        }
                    }
                });
    }
    @Override
    public void onClick(View v){
        startActivity(new Intent(this, PostActivity.class));
    }
```

<img src="https://user-images.githubusercontent.com/62593236/85400709-d06f5a80-b593-11ea-88b2-3fa036d8508f.png" ></img>

<div>
<img src="https://user-images.githubusercontent.com/62593236/85388690-5cc45200-b581-11ea-83cb-5c2f09ad92d6.png" width="30%"></img>
<img src="https://user-images.githubusercontent.com/62593236/85388704-5fbf4280-b581-11ea-820f-cc7c2e782fb7.png" width="30%"></img>
</div>


# 8. 캘린더

캘린더 기능은 선택한 날짜에 그 날 먹은 칼로리, 음식 메뉴, 마신 물의 양을 입력할 수 있다.
파일 입출력을 통하여 앱 내부 저장소에 저장되며, 앱 삭제 시 같이 삭제된다.

먼저 외부 라이브러리인 'Material Calendar'를 Gradle에 추가한다.
```java
	implementation 'com.github.prolificinteractive:material-calendarview:1.4.3'
```

CalendarFragment가 실행되면 달력을 표시하고, 가시성을 높이기 위해 토요일, 일요일을 다른 색으로 표시해주는 Decorator를 추가한다.
```java
	materialCalendarView = v.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
```

SaturdayDecorator
```java
public class SaturdayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public SaturdayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SATURDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
    }
}

```

SundayDecorator
```java
public class SundayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public SundayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.RED));
    }
}

```

만약 파일 입출력을 통해 기존에 저장된 값이 있을 경우, 달력에 표시해줘야한다. 이는 사용자가 달력을 보기 전에 이루어져야한다.
따라서 AsyncTask를 통해 백그라운드에서 진행한다.

```java
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getContext().getFilesDir() + "savedCalendar"));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] readedContent = line.split(" ");
                Date rdate = simpleDateFormat.parse(readedContent[0]);
                int rkcal = Integer.parseInt(readedContent[1]);
                String rmenu = readedContent[2];
                int rwater = Integer.parseInt(readedContent[3]);

                Schedule schedule = new Schedule(rdate, rkcal, rmenu, rwater);
                scheduleList.add(schedule);
            }
            bufferedReader.close();

            String[] result = new String[scheduleList.size()];
            for (int i = 0; i < scheduleList.size(); i++) {
                result[i] = simpleDateFormat.format(scheduleList.get(i).getDate());
            }

            ArrayList<String> w_result = new ArrayList<>();
            for (int i = 0; i < scheduleList.size(); i++) {
                if (scheduleList.get(i).getWater() >= 20) {
                    w_result.add(simpleDateFormat.format(scheduleList.get(i).getDate()));
                }
            }

            new ApiSimulator(result, w_result).executeOnExecutor(Executors.newSingleThreadExecutor());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setHelper();
        setHelper2();
```

AsyncTask와 이를 실행할 때 필요한 데이터를 입력할 클래스를 만들었다.
doInBackground에서 실행의 결과로 return하는 l_calendarDay를 onPostExecute에서 받아 진행한다.
```java
	private class ApiSimulator extends AsyncTask<Void, Void, L_CalendarDay> {
        String[] Time_Result;
        ArrayList<String> Water_Result;

        ApiSimulator(String[] Time_Result, ArrayList<String> Water_Result) {
            this.Time_Result = Time_Result;
            this.Water_Result = Water_Result;
        }

        @Override
        protected L_CalendarDay doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();
            ArrayList<CalendarDay> wdates = new ArrayList<>();

            for (int i = 0; i < Time_Result.length; i++) {

                String[] time = Time_Result[i].split("\\."); // "."으로 하면 X
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                dates.add(calendarDay);
            }

            for (int i = 0; i < Water_Result.size(); i++) {

                String[] time = Water_Result.get(i).split("\\."); // "."으로 하면 X
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                wdates.add(calendarDay);
            }
            L_CalendarDay l_calendarDay = new L_CalendarDay(dates, wdates);

            return l_calendarDay;
        }

        @Override
        protected void onPostExecute(L_CalendarDay l_calendarDay) {
            super.onPostExecute(l_calendarDay);

            if (isRemoving()) {
                return;
            }
            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, l_calendarDay.Time_Result, getActivity()));
            materialCalendarView.addDecorator(new EventsDecorator(l_calendarDay.Water_Result, getActivity()));
        }
    }
```

달력의 날짜를 선택할 시 이벤트가 발생한다. 이벤트 발생 시 선택된 날짜를 시각적으로 표시하고
만약 그 날짜에 해당하는 데이터가 존재할 경우 텍스트로 그 값을 보여준다.
```java
		materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            // 날짜 선택 시
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (inputContainer.getVisibility() == View.VISIBLE) {
                    materialCalendarView.setSelectedDate(beforeSelectedDate);
                } else {
                    materialCalendarView.setSelectionColor(getResources().getColor(R.color.colorPrimary));
                    boolean ifEquals = false;

                    int Year = date.getYear();
                    int Month = date.getMonth() + 1;
                    int Day = date.getDay();

                    currentDate = date.getDate();
                    shot_Day = Year + "." + Month + "." + Day;

                    dateText.setText("date : " + shot_Day);

                    for (int i = 0; i < scheduleList.size(); i++) {
                        if (simpleDateFormat.format(date.getDate()).equals(simpleDateFormat.format(scheduleList.get(i).getDate()))) {
                            kcalText2.setText("kcal : " + scheduleList.get(i).getKcal());
                            menuText2.setText("menu : " + scheduleList.get(i).getMenu());
                            waterText2.setText("water : " + scheduleList.get(i).getWater() / 10.0 + "L");
                            ifEquals = true;
                        }
                    }

                    if (!ifEquals) {
                        kcalText2.setText("kcal : ");
                        menuText2.setText("menu : ");
                        waterText2.setText("water : ");
                    }

                    beforeSelectedDate = date;
                }
            }
        });
```

날짜를 선택하고, 그 날짜에 데이터를 넣을 수 있다. 입력 상태에 들어가기 위한 버튼 이벤트를 작성한다.
입력 창의 visibility는 GONE으로 설정되어있으므로, 이를 변경한다.
만약 선택된 날짜에 데이터가 존재할 경우, 수정하기 편하게 그 데이터들을 표시해준다.
```java
		buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputContainer.setVisibility(View.VISIBLE);
                outputContainer.setVisibility(View.GONE);

                if (!kcalText2.getText().toString().equals("kcal : ")) {
                    for (int i = 0; i < scheduleList.size(); i++) {
                        String sdate = simpleDateFormat.format(scheduleList.get(i).getDate());
                        if (simpleDateFormat.format(currentDate).equals(sdate)) {
                            kcalText.setText(scheduleList.get(i).getKcal() + "");
                            menuText.setText(scheduleList.get(i).getMenu() + "");
                            waterText.setText("이 날 마신 물 : " + scheduleList.get(i).getWater() / 10.0 + "L");
                        }
                    }
                } else {
                    kcalText.setText("");
                    menuText.setText("");
                    waterText.setText("이 날 마신 물 : 0.0L");
                    seekBar.setProgress(0);
                }
            }
        });
```

확인 버튼의 이벤트를 설정한다.
데이터를 입력하고 확인 버튼을 누를 시, 파일 입출력을 통하여 데이터를 저장한다.
또한, 달력에 이를 표시한다. 만약 마신 물의 양이 2L 이상일 경우, 파란색을 표시한다.
이후에 수정 화면을 종료한다.

```java
		buttonInput.setOnClickListener(new View.OnClickListener() { // 입력 버튼
            @Override
            public void onClick(View v) {
                String content = "";
                try {
                    File directory = getActivity().getFilesDir();
                    File file = new File(directory, "savedCalendar");
                    FileWriter fileWriter = new FileWriter(getActivity().getFilesDir() + "savedCalendar", false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    Schedule schedule = new Schedule(simpleDateFormat.parse(shot_Day), Integer.parseInt(kcalText.getText().toString()), menuText.getText().toString(), seekBar.getProgress());
                    int currentsize = scheduleList.size();
                    boolean isChanged = false;

                    if (currentsize == 0) {
                        scheduleList.add(schedule);
                        isChanged = true;
                    }

                    for (int i = 0; i < currentsize; i++) {
                        if (simpleDateFormat.format(scheduleList.get(i).getDate()).equals(simpleDateFormat.format(schedule.getDate()))) {
                            scheduleList.set(i, schedule);
                            isChanged = true;
                        } else if (i == currentsize - 1 && !isChanged) {
                            scheduleList.add(schedule);
                        }
                    }

                    for (int i = 0; i < scheduleList.size(); i++) {
                        content += simpleDateFormat.format(scheduleList.get(i).getDate()) + " " + scheduleList.get(i).getKcal() + " " + scheduleList.get(i).getMenu() + " " + scheduleList.get(i).getWater() + "\n";
                    }
                    bufferedWriter.write(content);
                    bufferedWriter.close();

                    ArrayList<CalendarDay> CalendarDays = new ArrayList<>();
                    CalendarDays.add(CalendarDay.from(currentDate));

                    if (seekBar.getProgress() >= 20) {
                        materialCalendarView.addDecorator(new EventsDecorator(CalendarDays, getActivity()));
                    } else {
                        materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, CalendarDays, getActivity()));
                    }

                    kcalText2.setText("kcal : " + kcalText.getText());
                    menuText2.setText("menu :" + menuText.getText());
                    waterText2.setText("water : " + (float) seekBar.getProgress() / 10 + "L");
                    kcalText.setText("");
                    menuText.setText("");
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

                inputContainer.setVisibility(View.GONE);
                outputContainer.setVisibility(View.VISIBLE);
                setHelper();
                setHelper2();
            }
        });
```

취소 버튼은 입력된 데이터를 초기화하고, 수정 화면을 종료한다.
```java
		buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputContainer.setVisibility(View.GONE);
                outputContainer.setVisibility(View.VISIBLE);
                kcalText.setText("");
                menuText.setText("");
                seekBar.setProgress(0);
                waterText.setText("이 날 마신 물 : 0.0L");
            }
        });
```

마신 물의 양을 입력하기 위한 Seekbar의 Progress가 변할 경우의 이벤트를 작성한다.
텍스트를 변경하여 입력 값이 얼마인지를 표시한다.
```java
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float water = (float) progress / 10;
                waterText.setText("이 날 마신 물 : " + water + "L");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
```

일정 입력을 활발히 하기 위해 격려의 메시지를 표시하는 기능을 추가하였다.
일정이 입력된 날과 2L 이상 물을 마신 날을 계산하여 일정 수마다 표시하는 메시지가 달라진다.
아래는 2L 이상 물을 마신 날을 기준으로 하는 코드이다.
```java
	private void setHelper() {
        int count = 0;
        CalendarDay todayCal = CalendarDay.today();

        for (int i = 0; i < scheduleList.size(); i++) {
            if (monthFormat.format(todayCal.getDate()).equals(monthFormat.format(scheduleList.get(i).getDate())) && scheduleList.get(i).getWater() >= 20) {
                count++;
            }
        }

        cnum = count;

        helperTitle1.setText("이번 달 물 2L 마신 날 : " + cnum + "/" + mnum);

        if (cnum < 7) {
            helperContent1.setText(R.string.wday0);
        } else if (cnum < 14) {
            helperContent1.setText(R.string.wday7);
        } else if (cnum < 21) {
            helperContent1.setText(R.string.wday14);
        } else if (cnum < 28) {
            helperContent1.setText(R.string.wday21);
        } else if (cnum < mnum) {
            helperContent1.setText(R.string.wday28);
        }
    }
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399894-86d24000-b592-11ea-814b-443596fe275b.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399897-86d24000-b592-11ea-8dc7-1221a4e7dd9e.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399899-876ad680-b592-11ea-80fd-23aa22650029.jpg">
</div>


# 9. 마이페이지

마이페이지는 4개의 버튼으로 이루어져 있다. 각각의 버튼마다 실행되는 기능이 다르다.

먼저 로그인 여부를 확인하기 위해 firebase auth를 선언한다.
```java
	private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
```

firebase auth와 버튼들을 호출하고, 버튼에 클릭 리스너를 추가한다.
```java
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        button_login = v.findViewById(R.id.button_log);
        button_notice = v.findViewById(R.id.button_notice);
        button_question = v.findViewById(R.id.button_question);
        button_setting = v.findViewById(R.id.button_setting);

        button_login.setOnClickListener(this);
        button_notice.setOnClickListener(this);
        button_question.setOnClickListener(this);
        button_setting.setOnClickListener(this);

        return v;
    }
```

로그인/로그아웃 버튼은 로그인 여부에 따라 기능과 텍스트가 달라진다.
공지사항, 문의하기 버튼은 그에 맞는 액티비티를 실행시킨다.
```java
	public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_log:
                if (button_login.getText() == getString(R.string.mypage_login)) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else if (button_login.getText() == getString(R.string.mypage_logout)) {
                    showMessage();
                }
                break;
            case R.id.button_notice:
                Intent intent = new Intent(getActivity().getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_question:
                Intent intent2 = new Intent(getActivity().getApplicationContext(), QuestionActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_setting:
                Toast.makeText(getContext(), "환경설정", Toast.LENGTH_SHORT).show();
                break;
        }
    }
```

로그아웃 버튼을 눌렀을 때, 바로 로그아웃이 되는 것이 아니라 알림 메시지를 띄워줘서 실수로 인한 로그아웃을 방지한다.
확인 버튼을 누를 시 로그아웃을 실행한다.
```java
	private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("알림");
        builder.setMessage("로그아웃 하시겠습니까?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                signOut();
                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                button_login.setText(R.string.mypage_login);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
```

## 9.1. 로그인

먼저 Gradle에 firebase auth를 추가시켜준다.

```
implementation 'com.google.firebase:firebase-auth:19.3.1'
```

추가한 라이브러리 안에 있는 클래스들을 호출한다.

```
private FirebaseAuth mAuth;

private GoogleSignInClient mGoogleSignInClient;
```

액티비티가 생성되었을 때, firebase auth를 사용할 수 있도록 설정하고, 로그인 버튼에 대한 기능을 추가시켜준다.

```java
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(this);
    }
```

로그인 버튼에 대한 클릭 리스너
```java
	public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                progressDialog("로그인 중입니다.");
                break;
        }
    }
```

구글 로그인 버튼이 눌렸을 때 실행될 로그인 과정을 메서드로 제작한다.

signIn 메서드는 GoogleSignInClient 내부에 있는 인텐트를 실행하여 결과값을 가져온다.
```java
	private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
	
```

signIn 메서드에서 가져온 결과값을 RC_SIGN_IN과 비교하여 메서드 실행을 결정짓는다.
```java
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(getApplicationContext(),  "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
    }
```

onActivityResult를 통과한 뒤 Google 계정을 이용한 인증 방식을 통해 로그인을 실행한다.
```java
	private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle : " + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Snackbar.make(findViewById(R.id.container), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(findViewById(R.id.container), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
```

정상적으로 작동하였으면, 유저의 닉네임을 포함한 Toast 메시지를 표시한다.
```java
    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Toast.makeText(getApplicationContext(), user.getDisplayName() + "로 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            finish();
        } else {

        }
    }
```

로딩 시간을 알려주기 위해 ProgressDialog를 표시한다.
```java
	private void progressDialog(String message) {
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);

        dialog.show();
    }
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399864-7ae67e00-b592-11ea-9979-871912cf7d22.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399910-89349a00-b592-11ea-9527-5c145a22e3df.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85399892-8639a980-b592-11ea-9c02-3b27d6493bf7.jpg">
</div>


## 9.2. 공지사항

공지사항은 실제로 데이터베이스 내에 들어가있는 공지사항을 볼 수 있는 액티비티입니다.
RecyclerView와 firebase database를 통하여 구현했습니다.

먼저 Gradle에 다음 문구를 추가합니다.
```java
	implementation 'com.google.firebase:firebase-firestore:21.4.3'
```

RecyclerView를 호출하고, LayoutManager와 Adapter를 추가하는 메서드와
DB 내에 데이터가 존재할 경우 이를 표시해주는 메서드를 추가합니다.
```java
	private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        adapter = new RecyclerNoticeAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
    }

    private void setup() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("mypage/notice/posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String gettedDate;
                                String gettedTitle;
                                String gettedContent;
                                date = document.getDate("date");
                                gettedDate = format.format(date);
                                gettedTitle = document.get("title").toString();
                                gettedContent = document.get("content").toString();

                                RecyclerItem item = new RecyclerItem(gettedDate, gettedTitle, gettedContent);
                                adapter.addItem(item);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
```

onCreate에서 메서드들을 실행합니다.
```java
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        init();
        setup();
    }
```

어댑터에서 리사이클러뷰에 표시할 아이템들을 정렬, 추가 등의 기능을 가집니다.
```java
public class RecyclerNoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<RecyclerItem> mData = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭한 Item의 position
    private int prePosition = -1;

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recycler_item, parent, false);
        return new ViewHolderNotice(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position ) {
        ViewHolderNotice viewHolderNotice = (ViewHolderNotice)holder;
        viewHolderNotice.onBind(mData.get(position), position, selectedItems);
        viewHolderNotice.setOnViewHolderItemClickListener(new OnViewHolderItemClickListener() {
            @Override
            public void onViewHolderItemClick() {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(RecyclerItem item) {
        mData.add(item);
    }
}
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400441-5fc83e00-b593-11ea-9896-1c845b895640.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400442-5fc83e00-b593-11ea-982f-6bcaf7242340.jpg">
</div>


## 9.3. 문의하기

문의하기는 문의 작성, 문의 내역 확인 두 개의 프래그먼트로 이루어져있다.
문의 내역 확인 코드는 공지사항 코드와 거의 차이가 없다.

문의 작성은 먼저 스피너를 사용한다. 따라서 스피너 아이템 선택 이벤트를 작성한다.
선택된 아이템에 따라 문의 내용 텍스트가 달라진다.
```java
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 1:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 2:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 3:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생상품 :\n" +
                                "- 문제발생일시 :\n" +
                                "- 문의내용 : ");
                        break;
                    case 4:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생게시판 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 5:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                }
            }
```

전송 버튼을 누를 시 DB에 형식에 맞춰서 문의를 저장한다. 저장하기 전에 알림 메시지를 띄워서 재확인 후 문의를 저장한다.

```java
		sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("알림")
                        .setMessage("문의를 보내시겠습니까?")
                        .setCancelable(false)

                        // 확인 시
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String contentStr = content.getText().toString();
                                int startIndex = contentStr.indexOf("문의내용 : ");
                                int endIndex = contentStr.length();
                                String title;

                                if (endIndex - startIndex >= 20) {
                                    title = contentStr.substring(startIndex + 7, startIndex + 27);
                                } else {
                                    title = contentStr.substring(startIndex + 7, endIndex);
                                }

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Question question = new Question(mAuth.getUid(), format.format(date), spinner.getSelectedItemPosition(), title, contentStr, null, emailText.getText().toString());

                                db.collection("mypage/question/questions").document(question.getType() + " " + question.getDate() + " " + question.getEmail()).set(question);
                                Toast.makeText(getContext(), "문의를 보냈습니다.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        })

                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400429-5ccd4d80-b593-11ea-9269-e89317067d11.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400431-5dfe7a80-b593-11ea-96d7-9295425a1ec5.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400435-5e971100-b593-11ea-8b9f-eadebda1be98.jpg">
</div>


문의 내역 확인은 공지사항 기능과 거의 유사하지만, 사용자가 작성한 문의만 표시한다.
```java
	private void setup() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("mypage/question/questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String gettedUid = document.get("uid").toString();
                                if (gettedUid.equals(mAuth.getUid())) {
                                    String gettedTitle;
                                    String gettedContent;
                                    String gettedAnswer;
                                    gettedTitle = document.get("title").toString();
                                    gettedContent = document.get("content").toString();
                                    if (document.get("answer") != null) {
                                        gettedAnswer = document.get("answer").toString();
                                    } else {
                                        gettedAnswer = null;
                                    }

                                    RecyclerItem item = new RecyclerItem(gettedTitle, gettedContent, gettedAnswer);
                                    adapter.addItem(item);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
```

<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400438-5e971100-b593-11ea-9dd9-358f2a5d4e82.jpg">
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400439-5f2fa780-b593-11ea-9e59-6f97c1c36633.jpg">
</div>



# 10. 이외의 다양한 기능
## 1) FCM 메시지
build gradle(Module:app)에 firebase-messaging 서비스를 추가해준다
```java
 implementation 'com.google.firebase:firebase-messaging:20.1.7'
```

그리고 FirebaseMessagingService라는 자바클래스를 하나 만들어 아래와 같이
글 내용을 변수 msg에, 글 제목을 변수 title에 넣어 준 뒤 Notification.builder로 메시지 틀을 만들어주고
NotificationManager로 만든 틀을 실행해 준다.

```java
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    protected static final String FOMTAG = "[FC Sserivce]";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(FOMTAG, "onMessageReceived is Called");

        String msg, title;

        msg = remoteMessage.getNotification().getBody();
        title = remoteMessage.getNotification().getTitle();

        Notification.Builder noti = new Notification.Builder(this)
                .setContentTitle("New push from " + title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,noti.build());



    }
}
```


firebase 콘솔의 클라우딩 메시지를 보낼때 적는 알림제목이 변수 title에, 텍스트 내용이 msg에 들어가게 된다.
<div>
<img src="https://user-images.githubusercontent.com/62593277/85392098-086fa100-b586-11ea-817f-ad03af7fcd7d.png"></img>
</div>


마지막으로 manifest에 MESSAGING_EVENT와 INSTANCE_ID_EVENT를 intent해주면 
```java
 <service
            android:name=".datapushsend.FirebaseMessagingService"
            android:stopWithTask="false">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
                <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
            </intent-filter>
</service>
```

파이어베이스 콘솔에서 푸시알림을 보낼수 있다.
<div>
<img src="https://user-images.githubusercontent.com/62593277/85392412-71efaf80-b586-11ea-98b0-c709968aa5bd.png"width="30%"></img>
</div>


## 하단 바

메인화면에 하단 바(BottomNavigationView)를 추가하여서 프래그먼트의 전환을 편하게 하였다.

먼저 MainActivity가 실행될 때, 프래그먼트들을 모두 호출, 선언한다.
```java
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        main = new Main();
        calendarFragment = new CalendarFragment();
        notice = new Notice();
        mypage = new Mypage();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, main).commit();

```

탭이 변경될 때 마다 프래그먼트 매니저가 container에 들어가는 프래그먼트를 전환한다.
```java
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
//                        Toast.makeText(getApplicationContext(), "홈", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, main).commit();
                        return true;

                    case R.id.tab2:
//                        Toast.makeText(getApplicationContext(), "캘린더 화면", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarFragment).commit();
                        return true;

                    case R.id.tab3:
//                        Toast.makeText(getApplicationContext(), "알림", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notice).commit();
                        return true;

                    case R.id.tab4:
//                        Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();
                        return true;
                }
                return false;
            }
        });
    }
```

## 로딩화면

앱 실행 시 맨 처음에 보여지는 화면으로, 2초 동안 보여진 후 MainActivity로 전환한다.
```java
public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 금지
    }
}
```
<div>
<img width="280" src="https://user-images.githubusercontent.com/51768326/85400892-17f5e680-b594-11ea-9b23-0970828c9b5d.jpg">
</div>
