package com.project.zhinan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.bean.QuestionnaireItem;

public class QuestionnaireItemAdapter extends BaseAdapter {

    private List<QuestionnaireItem.QuestionnairesBean> objects = new ArrayList<QuestionnaireItem.QuestionnairesBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public QuestionnaireItemAdapter(Context context,List<QuestionnaireItem.QuestionnairesBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public QuestionnaireItem.QuestionnairesBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.questionnaire_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((QuestionnaireItem.QuestionnairesBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(QuestionnaireItem.QuestionnairesBean object, ViewHolder holder) {
        //TODO implement
        holder.tvQuestionnaireName.setText(object.getTitle());
    }

    protected class ViewHolder {
        private TextView tvQuestionnaireName;

        public ViewHolder(View view) {
            tvQuestionnaireName = (TextView) view.findViewById(R.id.tv_questionnaire_name);
        }
    }
}
