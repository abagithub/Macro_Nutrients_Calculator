package com.example.android.macro_nutrients_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.macro_nutrients_calculator.R.layout.toast_layout;

public class MainActivity extends AppCompatActivity {

    String gender, dailyActivity, goal;
    int age, weight, height, proteins, fats, carbohydrates;
    float fatIndex;
    boolean genderChecked, dailyActivityChecked, goalChecked, error;
    EditText ageView, weightView, heightView;
    CheckBox checkBoxView[] = new CheckBox[12];
    boolean[] checkBoxArray = new boolean[12];
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
            radioButton5, radioButton6, radioButton7, radioButton8, radioButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioButton1 = (RadioButton) findViewById(R.id.genderM);
        radioButton1.setOnCheckedChangeListener(radioGenderListener);
        radioButton2 = (RadioButton) findViewById(R.id.genderF);
        radioButton2.setOnCheckedChangeListener(radioGenderListener);
        radioButton3 = (RadioButton) findViewById(R.id.activity1);
        radioButton3.setOnCheckedChangeListener(radioActivityListener);
        radioButton4 = (RadioButton) findViewById(R.id.activity2);
        radioButton4.setOnCheckedChangeListener(radioActivityListener);
        radioButton5 = (RadioButton) findViewById(R.id.activity3);
        radioButton5.setOnCheckedChangeListener(radioActivityListener);
        radioButton6 = (RadioButton) findViewById(R.id.activity4);
        radioButton6.setOnCheckedChangeListener(radioActivityListener);
        radioButton7 = (RadioButton) findViewById(R.id.goal1);
        radioButton7.setOnCheckedChangeListener(radioGoalListener);
        radioButton8 = (RadioButton) findViewById(R.id.goal2);
        radioButton8.setOnCheckedChangeListener(radioGoalListener);
        radioButton9 = (RadioButton) findViewById(R.id.goal3);
        radioButton9.setOnCheckedChangeListener(radioGoalListener);

        ageView = (EditText) findViewById(R.id.age);
        weightView = (EditText) findViewById(R.id.weight);
        heightView = (EditText) findViewById(R.id.height);

        //create array that stores checkbox ids
        int[] ids = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4,
                R.id.checkbox5, R.id.checkbox6, R.id.checkbox7, R.id.checkbox8,
                R.id.checkbox9, R.id.checkbox10, R.id.checkbox11, R.id.checkbox12};

        //creating array for checkBoxViews
        for (int i = 0; i < checkBoxView.length; i++) {
            checkBoxView[i] = (CheckBox) findViewById(ids[i]);
        }
    }

    //gender button listener
    private CompoundButton.OnCheckedChangeListener radioGenderListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                gender = buttonView.getText().toString();
                genderChecked = true;
            }
        }
    };
    // daily activity button listener
    private CompoundButton.OnCheckedChangeListener radioActivityListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                dailyActivity = buttonView.getText().toString();
                dailyActivityChecked = true;
            }
        }
    };
    //goal button listener
    private CompoundButton.OnCheckedChangeListener radioGoalListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                goal = buttonView.getText().toString();
                goalChecked = true;
            }
        }
    };

    public void validateInputs(View view) {
        String toastErrorMessage = "";
        age = 0;
        weight = 0;
        height = 0;
        error = false;
        boolean checkBoxTrue = false;

        String verifyAgeField = ageView.getText().toString();
        String verifyWeightField = weightView.getText().toString();
        String verifyHeightField = heightView.getText().toString();

        // convert to integer only if age, weight and height fields are not empty
        if (!verifyAgeField.isEmpty())
            age = Integer.parseInt(ageView.getText().toString());
        if (!verifyWeightField.isEmpty())
            weight = Integer.parseInt(weightView.getText().toString());
        if (!verifyHeightField.isEmpty())
            height = Integer.parseInt(heightView.getText().toString());

        //check if gender radio button is not selected
        if (!genderChecked) {
            toastErrorMessage += "Gender field required\n";
            error = true;
        }
        //check if age typed is in the required range
        if (age < 2 || age > 120) {
            ageView.setError("required 2-120 range");
            toastErrorMessage += "Age field required\n";
            error = true;
        }
        //check if weight typed is in the required range
        if (weight < 5 || weight > 200) {
            weightView.setError("required 10-200 range");
            toastErrorMessage += "Weight field required\n";
            error = true;
        }
        //check if age typed is in the required range
        if (height < 50 || height > 250) {
            heightView.setError("required 50-250 range");
            toastErrorMessage += "Height field required\n";
            error = true;
        }

        //create boolean array which stores checked boxes values
        for (int i = 0; i < checkBoxArray.length; i++)
            checkBoxArray[i] = checkBoxView[i].isChecked();
        //check if at least one checkbox has been selected
        for (int i = 0; i < checkBoxArray.length; i++)
            if (checkBoxArray[i]) {
                checkBoxTrue = true;
                break;
            }
        //if no checkbox selected
        if (!checkBoxTrue) {
            toastErrorMessage += "Check Favorite Food\n";
            error = true;
        }

        //check if Daily Activity radio button is not selected
        if (!dailyActivityChecked) {
            toastErrorMessage += "Daily Activity field required\n";
            error = true;
        }
        //check if goal radio button is not selected
        if (!goalChecked) {
            toastErrorMessage += "Goal field required";
            error = true;
        }

        //toast the error message
        if (!toastErrorMessage.isEmpty())
            Toast.makeText(this,toastErrorMessage.trim(), Toast.LENGTH_LONG).show();

    }

    public void fatIndex(View view) {
        int fatFood = 0;
        int carboFood = 0;
        float fatIndex1 = Float.parseFloat(getResources().getString(R.string.fatIndex1));
        float fatIndex2 = Float.parseFloat(getResources().getString(R.string.fatIndex2));
        float fatIndex3 = Float.parseFloat(getResources().getString(R.string.fatIndex3));

        String[] checkBoxCategory = {
                getResources().getString(R.string.checkBox1Category),
                getResources().getString(R.string.checkBox2Category),
                getResources().getString(R.string.checkBox3Category),
                getResources().getString(R.string.checkBox4Category),
                getResources().getString(R.string.checkBox5Category),
                getResources().getString(R.string.checkBox6Category),
                getResources().getString(R.string.checkBox7Category),
                getResources().getString(R.string.checkBox8Category),
                getResources().getString(R.string.checkBox9Category),
                getResources().getString(R.string.checkBox10Category),
                getResources().getString(R.string.checkBox11Category),
                getResources().getString(R.string.checkBox12Category)};

        //for each checked box it counts whether is a fat food or carbohydrate food
        for (int i = 0; i < checkBoxCategory.length; i++)
            if (checkBoxArray[i]) {
                switch (checkBoxCategory[i]) {
                    case "fat": {
                        fatFood += 1;
                        break;
                    }
                    case "carbohydrate": {
                        carboFood += 1;
                        break;
                    }
                }
            }

        //assign the right fat index
        if (fatFood > carboFood) fatIndex = fatIndex3;
        else if (fatFood < carboFood) fatIndex = fatIndex1;
        else fatIndex = fatIndex2;
    }

    public void calculateMacros(View view) {
        double restingEnergyExpenditure, totalDailyEnergyExpenditure = 0, proteinsCalories,
                fatsCalories, carbohydratesCalories;
        int weightIndex = getResources().getInteger(R.integer.weightIndex);
        float heightIndex = Float.parseFloat(getResources().getString(R.string.heightIndex));
        int ageIndex = getResources().getInteger(R.integer.ageIndex);
        int maleIndex, femaleIndex;
        float proteinIndex = Float.parseFloat(getResources().getString(R.string.proteinIndex));
        float poundToKgRatio = Float.parseFloat(getResources().getString(R.string.poundToKg));
        int proteinCaloriesPer1g = getResources().getInteger(R.integer.proteinCaloriesPer1g);
        int fatCaloriesPer1g = getResources().getInteger(R.integer.fatCaloriesPer1g);
        int carbohydratesCaloriesPer1g = getResources().getInteger(R.integer.carbohydratesCaloriesPer1g);

        float dailyActivitiesIndex[] = {
                Float.parseFloat(getResources().getString(R.string.activity1index)),
                Float.parseFloat(getResources().getString(R.string.activity2index)),
                Float.parseFloat(getResources().getString(R.string.activity3index)),
                Float.parseFloat(getResources().getString(R.string.activity4index))};
        String[] dailyActivities = {
                getResources().getString(R.string.activity1),
                getResources().getString(R.string.activity2),
                getResources().getString(R.string.activity3),
                getResources().getString(R.string.activity4)};

        //calculate calories for man or woman
        if (gender == getResources().getString(R.string.male)) {
            maleIndex = getResources().getInteger(R.integer.maleIndex);
            restingEnergyExpenditure = weightIndex * weight + heightIndex * height
                    - ageIndex * age + maleIndex;
        } else {
            femaleIndex = getResources().getInteger(R.integer.femaleIndex);
            restingEnergyExpenditure = weightIndex * weight + heightIndex * height
                    - ageIndex * age - femaleIndex;
        }

        //calculate calories depending of daily activities
        for (int i = 0; i < dailyActivities.length; i++) {
            if (dailyActivity == dailyActivities[i]) {
                totalDailyEnergyExpenditure = restingEnergyExpenditure * dailyActivitiesIndex[i];
                break;
            }
        }
        //calculate calories depending of goals : lose, maintain or gain weight
        if (goal == getResources().getString(R.string.goal1)) {
            float goal1Index = Float.parseFloat(getResources().getString(R.string.goal1index));
            totalDailyEnergyExpenditure *= goal1Index;
        }
        if (goal == getResources().getString(R.string.goal3)) {
            float goal3Index = Float.parseFloat(getResources().getString(R.string.goal3index));
            totalDailyEnergyExpenditure *= goal3Index;
        }

        //calculate Macros'calories : Proteins, Fats, Carbohydrates
        proteinsCalories = weight * proteinIndex / poundToKgRatio * proteinCaloriesPer1g;

        fatIndex(view);
        fatsCalories = totalDailyEnergyExpenditure * fatIndex;

        carbohydratesCalories = totalDailyEnergyExpenditure - proteinsCalories - fatsCalories;

        //calculate Macros/gram : Proteins, Fats, Carbohydrates
        proteins = (int) Math.round(proteinsCalories / proteinCaloriesPer1g);
        fats = (int) Math.round(fatsCalories / fatCaloriesPer1g);
        carbohydrates = (int) Math.round(carbohydratesCalories / carbohydratesCaloriesPer1g);
    }

    public void toastMessage(View view) {
        //create the message for toast
        String toastMessage = getString(R.string.titleToast) + "\n" + getString(R.string.proteins, proteins) + "g\n"
                + getString(R.string.fats, fats) + "g\n" + getString(R.string.carbohydrates, carbohydrates) + "g";

        //inflate toast_layout.xml
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        //set the message to textView from toast_layout.xml
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(toastMessage);

        //create new toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        for (int i = 0; i < 5; i++) toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        for (int i = 0; i < 5; i++) toast.show();
    }

    public void submit(View view) {
        validateInputs(view);
        if (error) return;
        calculateMacros(view);
        toastMessage(view);
    }

    public void reset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
