package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.bean.Answer;
import com.project.zhinan.bean.Page;
import com.project.zhinan.bean.Quesition;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WenjuanDetailActivity extends Activity {
    private LinearLayout test_layout;
    private Page the_page;
    //答案列表
    private ArrayList<Answer> the_answer_list;
    //问题列表
    private List<Quesition.QuestionsEntity> questionsEntities;
    //问题所在的View
    private View que_view;
    //答案所在的View
    private View ans_view;
    private LayoutInflater xInflater;
    private Page page;
    //下面这两个list是为了实现点击的时候改变图片，因为单选多选时情况不一样，为了方便控制
    //存每个问题下的imageview
    private ArrayList<ArrayList<ImageView>> imglist = new ArrayList<ArrayList<ImageView>>();
    //存每个答案的imageview
    private ArrayList<ImageView> imglist2;
    private String result;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            result = data.getString("res");
            initDate(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wenjuan_detail);
        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = getIntent();
        final String key = intent.getStringExtra("question_id");
        new Thread() {
            @Override
            public void run() {
                getData(key);
            }
        }.start();
        Button button = (Button) findViewById(R.id.submit);
//        button.setOnClickListener(new submitOnClickListener(page));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WenjuanDetailActivity.this, "您的问卷信息已经提交到远程服务器！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getData(String id) {
        String url = "http://115.159.0.155:8080/user/questionnaire/" + id;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "answer=%7B%2257f9a8d8b793b7198e1885b4%22%3A%2257f9a8e7b793b7198e1885b6%22%2C%2257f9a906b793b7198e1885b7%22%3A%2257f9a911b793b7198e1885b9%22%7D");
        Request request = new Request.Builder()
                .url(url)
//                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "e5902196-f892-3735-8fce-c8f410b2cc6e")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String string = response.body().string();
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("res", string);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDate(String res) {
        Gson gson = new Gson();
        Quesition quesition = gson.fromJson(res, Quesition.class);
        page = new Page();
        page.setPageId("000");
        page.setStatus("0");
        page.setTitle("调查问卷");
        page.setQuesitions(quesition);
        //加载布局
        initView(page);
    }

    private void initView(Page page) {
        //这是要把问题的动态布局加入的布局
        test_layout = (LinearLayout) findViewById(R.id.lly_test);
//        TextView page_txt = (TextView) findViewById(R.id.txt_title);
//        page_txt.setText(page.getTitle());
        //获得问题即第二层的数据
        Quesition quesitions = page.getQuesitions();
        //所有问题的集合
        questionsEntities = quesitions.getQuestions();
        for (int i = 0; i < questionsEntities.size(); i++) {
            que_view = xInflater.inflate(R.layout.quesition_layout, null);
            TextView txt_que = (TextView) que_view.findViewById(R.id.txt_question_item);  //标题
            LinearLayout add_layout = (LinearLayout) que_view.findViewById(R.id.lly_answer);  // 动态添加选项的容器

            //判断单选-多选来实现后面是*号还是*多选，  1 - 单选  2 - 多选
            if (questionsEntities.get(i).getType().equals("1")) {
                set(txt_que, questionsEntities.get(i).getContent(), 1);
            } else if (questionsEntities.get(i).getType().equals("2")) {
                set(txt_que, questionsEntities.get(i).getContent(), 0);
            }

            //获得某个答案的所有选项
            List<Quesition.QuestionsEntity.OptionsEntity> options = questionsEntities.get(i).getOptions();
            imglist2 = new ArrayList<>();  //选项集合的容器
            for (int j = 0; j < options.size(); j++) {
                ans_view = xInflater.inflate(R.layout.answer_layout, null);
                TextView txt_ans = (TextView) ans_view.findViewById(R.id.txt_answer_item);
                ImageView image = (ImageView) ans_view.findViewById(R.id.image);
                View line_view = ans_view.findViewById(R.id.vw_line);
                if (j == options.size() - 1) {
                    //最后一条答案下面不要线是指布局的问题
                    line_view.setVisibility(View.GONE);
                }
//                //判断单选多选加载不同选项图片
//                if (questionsEntities.get(i).getType().equals("1")) {   //单选
//                    image.setBackgroundDrawable(getResources().getDrawable(R.drawable.unchecked));
//                } else {
                image.setBackgroundDrawable(getResources().getDrawable(R.drawable.unchecked));
//                }
                txt_ans.setText(options.get(j).getContent());
                LinearLayout lly_answer_size = (LinearLayout) ans_view.findViewById(R.id.lly_answer_size);
                lly_answer_size.setOnClickListener(new answerItemOnClickListener(i, j, questionsEntities.get(i).getOptions(), txt_ans));
                imglist2.add(image);
                add_layout.addView(ans_view);//依次添加各个选项
            }
            imglist.add(imglist2);
            test_layout.addView(que_view);
        }
    }

    private void set(TextView tv_test, String content, int type) {
        //为了加载问题后面的* 和*多选
        String w;
        if (type == 1) {
            w = content;//+ "*[多选]";
        } else {
            w = content;// + "*";
        }

        int start = content.length();
        int end = w.length();
        Spannable word = new SpannableString(w);
        word.setSpan(new AbsoluteSizeSpan(25), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv_test.setText(word);
    }

    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        private int j;
        private TextView txt;
        private List<Quesition.QuestionsEntity.OptionsEntity> the_answer_lists;

        public answerItemOnClickListener(int i, int j, List<Quesition.QuestionsEntity.OptionsEntity> options, TextView txt_ans) {
            this.i = i;
            this.j = j;
            this.the_answer_lists = options;
            this.txt = txt_ans;
        }

        //实现点击选项后改变选中状态以及对应图片
        @Override
        public void onClick(View arg0) {
//            if (questionsEntities.get(i).getType().equals("1")) {
            //单选
            if (the_answer_lists.get(j).getAns_state() == 0) {
                //如果未被选中
                txt.setTextColor(Color.parseColor("#EA5514"));
                imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.checked));
                the_answer_lists.get(j).setAns_state(1);
                questionsEntities.get(i).setQue_state(1);
            } else {
                txt.setTextColor(Color.parseColor("#595757"));
                imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.unchecked));
                the_answer_lists.get(j).setAns_state(0);
                questionsEntities.get(i).setQue_state(1);
            }
//            } else {
//                for (int z = 0; z < the_answer_lists.size(); z++) {
//                    the_answer_lists.get(z).setAns_state(0);
//                    imglist.get(i).get(z).setBackgroundDrawable(getResources().getDrawable(R.drawable.checked));
//                }
//                if (the_answer_lists.get(j).getAns_state() == 0) {
//                    //如果当前未被选中
//                    imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.unchecked));
//                    the_answer_lists.get(j).setAns_state(1);
//                    questionsEntities.get(i).setQue_state(1);
//                } else {
//                    //如果当前已被选中
//                    the_answer_lists.get(j).setAns_state(0);
//                    questionsEntities.get(i).setQue_state(1);
//                }
//            }
            //判断当前选项是否选中
        }
    }

    class submitOnClickListener implements View.OnClickListener {
        private Page page;

        public submitOnClickListener(Page page) {
            this.page = page;
        }

        @Override
        public void onClick(View arg0) {
            //判断是否答完题
            boolean isState = true;
            //最终要的json数组
            JSONArray jsonArray = new JSONArray();
            //点击提交的时候，先判断状态，如果有未答完的就提示，如果没有再把每条答案提交（包含问卷ID 问题ID 及答案ID）
            //注：不用管是否是一个问题的答案，就以答案的个数为准来提交上述格式的数据
            for (int i = 0; i < questionsEntities.size(); i++) {
//                the_answer_list = questionsEntities.get(i).getAnswers();
                //判断是否有题没答完
                if (questionsEntities.get(i).getQue_state() == 0) {
                    Toast.makeText(getApplicationContext(), "您第" + (i + 1) + "题没有答完", Toast.LENGTH_LONG).show();
                    jsonArray = null;
                    isState = false;
                    break;
                } else {
//                    for (int j = 0; j < the_answer_list.size(); j++) {
//                        if (the_answer_list.get(j).getAns_state() == 1) {
//                            JSONObject json = new JSONObject();
//                            try {
//                                json.put("psychologicalId", page.getPageId());
//                                json.put("questionId", questionsEntities.get(i).getQuesitionId());
//                                json.put("optionId", the_answer_list.get(j).getAnswerId());
//                                jsonArray.put(json);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
                    Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                }
            }
            if (isState) {
                if (jsonArray.length() > 0) {
                    Log.e("af", jsonArray.toString());
                    for (int item = 0; item < jsonArray.length(); item++) {
                        JSONObject job;
                        try {
                            job = jsonArray.getJSONObject(item);
                            Log.e("----", "pageId--------" + job.get("pageId"));
                            Log.e("----", "quesitionId--------" + job.get("quesitionId"));
                            Log.e("----", "answerId--------" + job.get("answerId"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    }
                }
            }
        }
    }
}
