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
```java
implementation 'com.google.firebase:firebase-auth:19.3.1'
```

# 2. 추천 레시피

RecipeItem 모델에 넣고자하는 데이터를 생성합니다.
```java
public class RecipeItem {
    private String RecipeImage;
    private String RecipeName;
    private int RecipeKcal;
    private int RecipeGram;
    private String RecipeEx1;
    private String RecipeEx2;
```
Getter Setter로 값을 주고받아오게 해주고 난 뒤
```java
public String getRecipeName() {
        return RecipeName;
    }
    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }
```
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
어댑터에서 인텐트를 생성하여 아이템을 클릭 할 때 그 포지션의 값을 받아준 뒤

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

DietItem이라는 자바클래스를 하나 만들어 데이터베이스에 넣어줄 변수들을 넣어준 뒤
Alt+insert 버튼을 눌러 변수들의 구조체를 만들고
```
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

```
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
빨간줄에 alt+insert 버튼을 눌러 필요한 클래스와 메서드들을 만들어 준다.
그리고 ArrayList로 위에서 만들어준 변수들을 불러와준다.
```
public class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolderDiet> {
    private ArrayList<DietItem> arrayList;
    private Context context;
    private TextView textView_diet;



    public DietAdapter(ArrayList<DietItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
```


그리고 맨 밑에 만들어진 클래스 안에 새 변수들을 만들고
diet_list_item.xml파일에 만들었던 id값들을 가져와준다.
```
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolderDiet extends RecyclerView.ViewHolder {
        ImageView diet_iv_picture;
        TextView diet_tv_id;
        TextView diet_tv_cal;
        LinearLayout itemLinear_diet;

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

onCreateViewHolder는 리스트 아이템에 데이터들을 보여주기 위한 기능이므로
변수 view를 만들고 diet_lis_item.xml파일을 inflate해준다.
```
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
```

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
```
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

```
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
```
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
```
return view;
```


 ## 3.1 데이터부분

## 3.2 계산기
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

# 6.추천 운동법

각 카테고리마다 데이터 조사를 거쳐 적절한 데이터를 삽입하여 목록 형식으로 보일 수 있도록 하였습니다.
탭을 이용하여 섹션을 나눈 후 뷰페이저에 프래그먼트를 넣어 탭을 이동할 때마다 프래그먼트가 바뀌도록 해놓았습니다.
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
Post model을 생성한다.
```
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
```
public class FirebaseID {
    public static String user = "user";
    public static String post = "post";
    public static String documentId = "documentId";
    public static String title = "title";
    public static String contents = "contents";
    public static String timestamp = "timestamp";
}
```
```
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
```
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

PostAdapter부분

```
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
```
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
<div>
<img src="https://user-images.githubusercontent.com/62593236/85388690-5cc45200-b581-11ea-83cb-5c2f09ad92d6.png"></img>
<img src="https://user-images.githubusercontent.com/62593236/85388704-5fbf4280-b581-11ea-820f-cc7c2e782fb7.png"></img>
</div>

## 로그인

먼저 Gradle에 firebase auth를 추가시켜준다.

```java
implementation 'com.google.firebase:firebase-auth:19.3.1'
```

추가한 라이브러리 안에 있는 클래스들을 호출한다.

```java
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
