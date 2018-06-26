package ru.rubiconepro.study.Modules.Tests.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.Tests.Adapter.TestsListAdapter;
import ru.rubiconepro.study.Modules.Tests.Model.TestsAnswerModel;
import ru.rubiconepro.study.Modules.Tests.Model.TestsNodeModel;
import ru.rubiconepro.study.Modules.Tests.Tests;
import ru.rubiconepro.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для прохождения тестов.
 *
 * Будет отображать название теста
 * его описание, и варианты ответов.
 *
 * Так же бедет возможность перехода, на
 * предыдущий - следующий тест.
 *
 * Для отображения результата, будет использован
 * новый класс
 *
 * TODO добавить документацию по классу отображения результата
 */
public class TestsPass extends AppCompatActivity implements View.OnClickListener {

    /**
     * Описываем компонентя формы
     */
    TextView tvName;
    WebView  wvDescription;
    ListView lstAnswers;
    Button   btnPrev;
    Button   btnNext;

    TestsListAdapter listAdapter;

    TestsNodeModel currentModel;


    List<TestsNodeModel> questions;
    List<TestsNodeModel> questAnswUser;
    int currentIndex = 0;
    int countRightAnswer = 0;
    int countRightAnswer2 = 0;


    /**
     * Функция поиска компонентов
     * вызывается в конструкторе
     */
    private void initComponents() {
        tvName = findViewById(R.id.tvName);
        wvDescription = findViewById(R.id.wvDescription);

        wvDescription.getSettings().setJavaScriptEnabled(true);

        lstAnswers = findViewById(R.id.lstAnswers);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        btnPrev.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        listAdapter = new TestsListAdapter(this);
        lstAnswers.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_pass);

        initComponents();

        questions = Tests.Current().getList();
        questAnswUser = new ArrayList<>();

        drawQuestion(0);
    }

    private void drawQuestion(int index) {
        TestsNodeModel model =  questions.get(index);
        currentModel = questions.get(index);

        tvName.setText(model.getName());
        wvDescription.loadDataWithBaseURL("", model.getText(), "text/html", "UTF-8", "");

        listAdapter.setCurrent(index);
        currentIndex = index;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPrev){
            goPrew();
        }

        if (view.getId() == R.id.btnNext){
            goNext();
        }

    }

    private  void  goPrew(){
        currentIndex--;
        if (currentIndex >= 0) {

            drawQuestion(currentIndex);
        } else {
            currentIndex = 0;
        }
    }

    private  void  goNext(){
        currentIndex++;
        TestsNodeModel tnm = new TestsNodeModel();
        tnm.setName(tvName.getText().toString());
        tnm. setText(currentModel.getText());

        List<TestsAnswerModel> tams = new ArrayList<>();

        tams = currentModel.getAnswers();
        
        tams.get(0).setRight(false);
        tnm.addAnswer(tams.get(0));
        questAnswUser.add(tnm);
        tams.get(1).setRight(false);
        tnm.addAnswer(tams.get(1));
        questAnswUser.add(tnm);
        tams.get(2).setRight(false);
        tnm.addAnswer(tams.get(2));
        questAnswUser.add(tnm);
        tams.get(3).setRight(false);
        tnm.addAnswer(tams.get(3));
        questAnswUser.add(tnm);


        if(currentIndex <= (questions.size()-1)) {


//            final Switch switchAnsw = findViewById(R.id.swAnswer);
//            switchAnsw.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            listAdapter.setSwitchAnsw(switchAnsw.isChecked());
//            tams
//        }
//        });
//            lstAnswers.getAdapter().getItem(0).;
//            lstAnswers.getI

//           for (int i = 0; i < tams.size(); i++) {
//               tams.get(i).setRight(true);
//               tnm.addAnswer(tams.get(i));
//            questAnswUser.add(tnm);
//           }



            isCheckAnswer(currentModel, tnm);

            drawQuestion(currentIndex);
        } else if (currentIndex == questions.size()) {

            isCheckAnswer(currentModel, tnm);
 //           isCheckAnswer2();

            Toast.makeText(this, "Вы правильно ответили на " + countRightAnswer + " из " + questions.size(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Is right " + countRightAnswer2 + " for " + questions.size(), Toast.LENGTH_SHORT).show();
            currentIndex = questions.size() - 1;
        }
    }

    public  void isCheckAnswer (TestsNodeModel curMod, TestsNodeModel userMod){
        int count = 0;
        for (int i = 0; i < curMod.getAnswers().size(); i++){
           if (curMod.getAnswers().get(i).getRight().equals(userMod.getAnswers().get(i).getRight())){
             count++;
           }
        }
        if (curMod.getAnswers().size() == count){
            countRightAnswer++;
        }
    }

    public  void isCheckAnswer2 (){
        int count = 0;
        for (int i = 0; i < questAnswUser.size(); i++){
            for (int j = 0; j < questAnswUser.get(i).getAnswers().size(); j++){
            if (questAnswUser.get(i).getAnswers().get(j).getRight().equals(questions.get(i).getAnswers().get(j).getRight())){
                count++;
            }

            }
            if ((questAnswUser.get(i).getAnswers().size() == count)){
                countRightAnswer2++;
            }
        }

    }



}
