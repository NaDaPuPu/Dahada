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
```
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
    디테일 액티비티에서 받아온다.
    ```
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


# 3. 내 레시피

1.xml파일 생성
데이터들을 recyclerview로 화면에 보여줄 fragment_diet_veg.xml 파일과
recyclerview에 보여줄 틀을 diet_list_item.xml파일에 만들어 준다.
```
<img src="https://user-images.githubusercontent.com/62593277/85393565-2d651380-b588-11ea-81d4-2c6671306fe9.png"></img>

```

2.변수만들기
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
'''
getter/setter를 만들어 준다.

'''
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

3.Adapter 생성
recyclerview를 사용하기 위해선 Adapter가 필요하기 때문에
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
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        Log.d("test","check3");
        databaseReference = database.getReference("DietItem"); // DB 테이블 연결
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
