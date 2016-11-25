package com.project.zhinan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.adapter.ZnActItemAdapter;
import com.project.zhinan.bean.ZNACBean;

import java.util.ArrayList;

public class ZNActActivity extends AppCompatActivity {

    private ListView mActListView;
    private ZnActItemAdapter znActItemAdapter;
    private ArrayList<ZNACBean.ItemListBean> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_znact);
        mActListView = (ListView) findViewById(R.id.lv_zn_act);
        objects = new ArrayList<>();
        znActItemAdapter = new ZnActItemAdapter(this, objects);
        mActListView.setAdapter(znActItemAdapter);
        mActListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZNActActivity.this, ZNActDetailActivity.class);
                intent.putExtra("name",objects.get(position).getWname());
                intent.putExtra("id",objects.get(position).getWareId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromNet();
    }

    private void getDataFromNet() {
        String json = "{\n" +
                "\"message\": \"掌上专享低价，数量有限，先抢先得！\",\n" +
                "\"itemList\": [\n" +
                "      {\n" +
                "        \"wareId\": \"4042076\",\n" +
                "        \"wname\": \"展卉 智利车厘子 Santina 1斤装 进口新鲜樱桃 26-28mm 自营水果\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3547/70/883748091/99287/9037698b/58185a43Na323eb2a.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"94\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"4042076\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.5折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"69.9\",\n" +
                "        \"discount\": \"24.10\",\n" +
                "        \"activeId\": \"474504691\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_4042076_0_2\",\n" +
                "        \"promotionId\": \"194338197\",\n" +
                "        \"soldRate\": 30,\n" +
                "        \"tagType\": 1,\n" +
                "        \"tagText\": \"超值\",\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"colorRGB\": \"#2DBBFC\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3961348\",\n" +
                "        \"wname\": \"创悦 足浴盆 泡脚盆 洗脚盆 电动足浴器 带加热 按摩 4轮转盘版\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3553/159/331876434/221867/710a1869/58071555N4bc518e7.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"319\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"3961348\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"259\",\n" +
                "        \"discount\": \"60.00\",\n" +
                "        \"activeId\": \"474054633\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3961348_1_2\",\n" +
                "        \"promotionId\": \"194269408\",\n" +
                "        \"soldRate\": 24,\n" +
                "        \"tagType\": 1,\n" +
                "        \"tagText\": \"热卖\",\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"colorRGB\": \"#FF8400\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"961215\",\n" +
                "        \"wname\": \"佳能（Canon） EOS 70D 单反套机 （EF-S 18-200mm f/3.5-5.6 IS 镜头）\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_g14/M06/0E/07/rBEhVlLgvvUIAAAAAAIek8BnBDcAAIRQgFsaqYAAh6r517.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"8299\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"974380\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"9.3折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 27682,\n" +
                "        \"miaoShaPrice\": \"7699\",\n" +
                "        \"discount\": \"600.00\",\n" +
                "        \"activeId\": \"473607668\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_961215_2_2\",\n" +
                "        \"promotionId\": \"194304353\",\n" +
                "        \"soldRate\": 22,\n" +
                "        \"tagType\": 1,\n" +
                "        \"tagText\": \"推荐\",\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"colorRGB\": \"#7A44FB\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1000294\",\n" +
                "        \"wname\": \"明基（BenQ）MS3081+ 办公 投影机（DLP芯片 3200ANSI流明 SVGA分辨率）\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3166/318/2399710550/169101/25bf2417/57e0e64cNe10db2cb.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"2299\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1000294\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"9.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"2049\",\n" +
                "        \"discount\": \"250.00\",\n" +
                "        \"activeId\": \"474462420\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1000294_3_2\",\n" +
                "        \"promotionId\": \"194381037\",\n" +
                "        \"soldRate\": 33,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10071015582\",\n" +
                "        \"wname\": \"V+(韦茄)毛衣链秋冬长款装饰品项链女士竹节百搭配饰潮挂件时尚衣服挂饰生日礼物送女友\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3772/359/1768459351/174589/174acc36/58324bdfNee1ac21b.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"137\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10014408606\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.8折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"79\",\n" +
                "        \"discount\": \"58.00\",\n" +
                "        \"activeId\": \"474038051\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10071015582_4_2\",\n" +
                "        \"promotionId\": \"3666520065\",\n" +
                "        \"soldRate\": 52,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10129154367\",\n" +
                "        \"wname\": \"帅人（sazn）BCD-109W双门小冰箱家用109L冷藏冷冻冰柜\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2914/150/1774436459/343260/ceaf3498/578eef9cN6eb4e6f6.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"799\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10027899629\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.3折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"499\",\n" +
                "        \"discount\": \"300.00\",\n" +
                "        \"activeId\": \"474451164\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10129154367_5_2\",\n" +
                "        \"promotionId\": \"3672488598\",\n" +
                "        \"soldRate\": 28,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10276993916\",\n" +
                "        \"wname\": \"【京东超市】五粮液股份公司 富贵天下 绵纯级 整箱白酒 52°500ml*6 \",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3433/269/1613576608/318459/6472b875/582c0d5fNc28537a2.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"399\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10053620121\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"4.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"159\",\n" +
                "        \"discount\": \"240.00\",\n" +
                "        \"activeId\": \"474000223\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10276993916_6_2\",\n" +
                "        \"promotionId\": \"3666127305\",\n" +
                "        \"soldRate\": 26,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10351899073\",\n" +
                "        \"wname\": \"特华诗男士手表机械表全自动腕表金表男装正品商务防水男土手表国产男款手表机械男表镂空品牌自营 间金白面\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2683/92/1261397970/475650/b2d9aa23/57397969Nafcd0a20.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"958\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10075454523\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"2.1折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"199\",\n" +
                "        \"discount\": \"759.00\",\n" +
                "        \"activeId\": \"474010342\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10351899073_7_2\",\n" +
                "        \"promotionId\": \"3666295967\",\n" +
                "        \"soldRate\": 41,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10391529360\",\n" +
                "        \"wname\": \"【速发】小米（MI）小米手环2代 智能运动手环 心率手环腕带防水手表计步器 小米手环2黑色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3001/200/1527596178/90231/de31bb84/57c677ccN9c2eb2d5.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"169\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10084800158\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.9折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"149\",\n" +
                "        \"discount\": \"20.00\",\n" +
                "        \"activeId\": \"474587553\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10391529360_8_2\",\n" +
                "        \"promotionId\": \"3675335793\",\n" +
                "        \"soldRate\": 27,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10403441390\",\n" +
                "        \"wname\": \"维融验钞机银行专用小型智能点钞机 新版人民币 T6灰黑色(C类)\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3448/139/293046335/118298/fed6b693/5806c9daNe6a17fde.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"398\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10086854293\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.3折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"288\",\n" +
                "        \"discount\": \"110.00\",\n" +
                "        \"activeId\": \"474680040\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10403441390_9_2\",\n" +
                "        \"promotionId\": \"3677041639\",\n" +
                "        \"soldRate\": 33,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10483805387\",\n" +
                "        \"wname\": \"美的（Midea）扫地机器人R3-L081C全自动智能家用吸尘器 \",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3193/120/2785237614/202915/d6497a87/57e699ccN32337bde.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"1299\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10103867941\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.5折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"1099\",\n" +
                "        \"discount\": \"200.00\",\n" +
                "        \"activeId\": \"474494582\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10483805387_10_2\",\n" +
                "        \"promotionId\": \"3673481152\",\n" +
                "        \"soldRate\": 32,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10758504483\",\n" +
                "        \"wname\": \"吧嗒兔2016春秋新款儿童鞋女童休闲鞋男童运动鞋童鞋篮球鞋跑步鞋DS1623 天蓝/白(加绒) 36码/23.1cm内长\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3079/168/2467772995/262353/bf2383e/57e1dee8N533e67fb.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"99\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10105798266\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"69\",\n" +
                "        \"discount\": \"30.00\",\n" +
                "        \"activeId\": \"474074684\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10758504483_11_2\",\n" +
                "        \"promotionId\": \"3667363357\",\n" +
                "        \"soldRate\": 69,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10537598137\",\n" +
                "        \"wname\": \"邦德富士达  禧玛诺21速铝合金山地车自行车26寸 悦动S7 黒橙\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2674/235/4073660055/121316/90205c4/57ad3f5bN9209100f.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"899\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10112827248\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"599\",\n" +
                "        \"discount\": \"300.00\",\n" +
                "        \"activeId\": \"472888064\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10537598137_12_2\",\n" +
                "        \"promotionId\": \"3646590141\",\n" +
                "        \"soldRate\": 22,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10552953069\",\n" +
                "        \"wname\": \"梵古斯丁韩版保暖围巾女士秋冬季长款加厚流苏纯色仿羊绒空调披肩围脖两用1270 酒红色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2770/168/4277602034/131980/7e29d5b2/57b172e4Nb7adf859.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"299\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10114596793\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"1.5折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"45\",\n" +
                "        \"discount\": \"254.00\",\n" +
                "        \"activeId\": \"474209458\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10552953069_13_2\",\n" +
                "        \"promotionId\": \"3668966549\",\n" +
                "        \"soldRate\": 28,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10859494079\",\n" +
                "        \"wname\": \"花艺家 多肉植物室内桌面组合盆栽绿植花卉盆景礼品小植物含盆含土套餐 新手多肉 萌肉植物 A款方木盒套装\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3313/267/500824296/163382/63856fe9/580d7908Ne370587b.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"38\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10154965451\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"4.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"15.8\",\n" +
                "        \"discount\": \"22.20\",\n" +
                "        \"activeId\": \"474205840\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10859494079_14_2\",\n" +
                "        \"promotionId\": \"3668933604\",\n" +
                "        \"soldRate\": 30,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10880014003\",\n" +
                "        \"wname\": \"四季沐歌 保温壶家用热水保温瓶保温水壶真空不锈钢暖壶2L 不锈钢色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3490/69/1702257946/230476/a37e0c1c/5832d08aN3134218a.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"149\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10159551297\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"4.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"59\",\n" +
                "        \"discount\": \"90.00\",\n" +
                "        \"activeId\": \"473758420\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10880014003_15_2\",\n" +
                "        \"promotionId\": \"3660789906\",\n" +
                "        \"soldRate\": 24,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10979657911\",\n" +
                "        \"wname\": \"【真皮女包，限量300件秒杀】特斯黛牛皮女包 压花包单肩斜挎包女士包包065 蓝色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3454/331/1640431761/223776/19ff9c46/582d7e68N317b38e7.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"798\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10171202888\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"1.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"89\",\n" +
                "        \"discount\": \"709.00\",\n" +
                "        \"activeId\": \"472697698\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10979657911_16_2\",\n" +
                "        \"promotionId\": \"3643750582\",\n" +
                "        \"soldRate\": 34,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10984885135\",\n" +
                "        \"wname\": \"【历史低价！买一再送‘4’】泓辰阁高密高油老料小叶紫檀手串108颗佛珠念珠男女手链 高密高油老料6mm\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3790/150/1610087382/272690/19e9bb/582d01e0Ncf2bfead.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"998\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10171911111\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"1.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"99\",\n" +
                "        \"discount\": \"899.00\",\n" +
                "        \"activeId\": \"474014261\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10984885135_17_2\",\n" +
                "        \"promotionId\": \"3666318337\",\n" +
                "        \"soldRate\": 41,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10988386573\",\n" +
                "        \"wname\": \"【长款卫衣+皮带两件套限时抢59元】卡帝乐鳄鱼（CARTELO）长袖t恤 男士休闲T恤 1601藏青 XL.\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3439/351/1825033888/368233/4b769557/58329d4eN24732ef7.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"199\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"10172311957\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"3.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"59\",\n" +
                "        \"discount\": \"140.00\",\n" +
                "        \"activeId\": \"474206176\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10988386573_18_2\",\n" +
                "        \"promotionId\": \"3668937113\",\n" +
                "        \"soldRate\": 40,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1102262\",\n" +
                "        \"wname\": \"九阳（Joyoung）电压力锅（一锅双胆）5L智能预约高压锅JYY-50YL80\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3484/81/271874257/236107/21a9bcce/58048649Nde02f660.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"399\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1102262\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"197\",\n" +
                "        \"discount\": \"202.00\",\n" +
                "        \"activeId\": \"473840997\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1102262_19_2\",\n" +
                "        \"promotionId\": \"194324135\",\n" +
                "        \"soldRate\": 38,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1115593\",\n" +
                "        \"wname\": \"【京东超市】天喔茶庄 蜂蜜柚子茶250ml*16整箱装\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t514/188/359783303/147151/ae5c754e/545f220aN9bb23bce.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"24.9\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1115593\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.8折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"16.9\",\n" +
                "        \"discount\": \"8.00\",\n" +
                "        \"activeId\": \"473590324\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1115593_20_2\",\n" +
                "        \"promotionId\": \"194304186\",\n" +
                "        \"soldRate\": 33,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10041193862\",\n" +
                "        \"wname\": \"丁威特 中控台智能高清行车记录仪电子狗带导航仪一体机 车载后视镜倒车影像双镜头高德地图 送16G卡 固定测速+倒车影像+GPS导航 黑色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3490/350/1490549449/328199/6b031f20/582d4673N39162682.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"1599\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1140608852\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"3.5折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"555\",\n" +
                "        \"discount\": \"1044.00\",\n" +
                "        \"activeId\": \"472906263\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10041193862_21_2\",\n" +
                "        \"promotionId\": \"3646826776\",\n" +
                "        \"soldRate\": 42,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"11761207\",\n" +
                "        \"wname\": \"开心作文第一工具书：最新5年中考满分作文大全+初中生分类作文+初中生优秀作文+作文一本全（套装4册）\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2419/31/128508571/321395/18a04d02/55effa3cN78380a25.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"56.3\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"11761207\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.8折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"32.5\",\n" +
                "        \"discount\": \"23.80\",\n" +
                "        \"activeId\": \"473586774\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_11761207_22_2\",\n" +
                "        \"promotionId\": \"194293246\",\n" +
                "        \"soldRate\": 26,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"11932057\",\n" +
                "        \"wname\": \"菊与刀\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3817/101/174579161/230232/7351a9d2/58098b0dN31f94312.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"24.8\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"11932057\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"14\",\n" +
                "        \"discount\": \"10.80\",\n" +
                "        \"activeId\": \"473586714\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_11932057_23_2\",\n" +
                "        \"promotionId\": \"194265780\",\n" +
                "        \"soldRate\": 26,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"11963249\",\n" +
                "        \"wname\": \"做爆品：传统企业转型互联网的商业解决方案\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3184/242/1199427925/918316/40956d24/57c79b80N76197b59.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"25.8\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"11963249\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"4.8折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"12.4\",\n" +
                "        \"discount\": \"13.40\",\n" +
                "        \"activeId\": \"473586707\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_11963249_24_2\",\n" +
                "        \"promotionId\": \"194265775\",\n" +
                "        \"soldRate\": 24,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1451630414\",\n" +
                "        \"wname\": \"伊索（AESOP）男士手表 全自动机械表运动时尚腕表 多功能钢带夜光防水男士表 9002 钢带蓝面送皮表带请拍此项\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2968/93/1758941575/438861/cfbd269c/578df032N51fc117e.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"3250\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1231721785\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"1.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"388\",\n" +
                "        \"discount\": \"2862.00\",\n" +
                "        \"activeId\": \"474562706\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1451630414_25_2\",\n" +
                "        \"promotionId\": \"3674879481\",\n" +
                "        \"soldRate\": 54,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1542921846\",\n" +
                "        \"wname\": \"美的（Midea）电烤箱家用大容量 广域控温 MG38CB-AA 38L黑色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3043/120/2283969624/245825/5620772f/57d625f6N42f7e891.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"329\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1257837054\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.8折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"289\",\n" +
                "        \"discount\": \"40.00\",\n" +
                "        \"activeId\": \"474494579\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1542921846_26_2\",\n" +
                "        \"promotionId\": \"3673467728\",\n" +
                "        \"soldRate\": 24,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"10164839753\",\n" +
                "        \"wname\": \"【一壶6杯 损坏包赔】中国风茶具7件套套装  功夫茶具套装茶杯 便携茶具套装送领导 七彩冰裂七件套\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3820/7/876473888/370658/47296d46/581d7cabN50c55fb3.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"198\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1275127790\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"1.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"19.9\",\n" +
                "        \"discount\": \"178.10\",\n" +
                "        \"activeId\": \"473111189\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_10164839753_27_2\",\n" +
                "        \"promotionId\": \"3648431399\",\n" +
                "        \"soldRate\": 34,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1797476632\",\n" +
                "        \"wname\": \"菲诗芬 2016冬装新款棉外套韩版长款棉袄连帽长袖加厚棉衣纯色棉服女 纯黑色 m\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2086/259/1369142670/558545/54401a35/5655752bN02cb440f.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"298\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1307730025\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"199\",\n" +
                "        \"discount\": \"99.00\",\n" +
                "        \"activeId\": \"472705224\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1797476632_28_2\",\n" +
                "        \"promotionId\": \"3643839858\",\n" +
                "        \"soldRate\": 21,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1332195\",\n" +
                "        \"wname\": \"Misfit Flash 智能手环 玛瑙黑（无需充电 生活防水 运动睡眠监测 时间显示 音乐自拍手机控制）\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t520/150/1282624313/55455/a2849231/54c71802N49b69b29.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"199\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1332195\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"99\",\n" +
                "        \"discount\": \"100.00\",\n" +
                "        \"activeId\": \"474143639\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1332195_29_2\",\n" +
                "        \"promotionId\": \"194347937\",\n" +
                "        \"soldRate\": 37,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1436526\",\n" +
                "        \"wname\": \"富士通 正1.5匹 3级能效 全直流变频 壁挂式家用冷暖空调（白色）（Fujitsu）ASQG12LPCA\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2647/258/537260818/116410/67fb9411/5718e4a8Neadcf860.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"4580\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1436526\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.6折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"2999\",\n" +
                "        \"discount\": \"1581.00\",\n" +
                "        \"activeId\": \"472139295\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1436526_30_2\",\n" +
                "        \"promotionId\": \"194201791\",\n" +
                "        \"soldRate\": 28,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1578164\",\n" +
                "        \"wname\": \"美的（Midea）电磁炉纤薄整板触摸C21-WT2120 (赠汤锅+炒锅）\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3388/241/1460365660/242394/bf803858/582687a5Nba3d099b.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"399\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1578164\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"199\",\n" +
                "        \"discount\": \"200.00\",\n" +
                "        \"activeId\": \"472311174\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1578164_31_2\",\n" +
                "        \"promotionId\": \"194214091\",\n" +
                "        \"soldRate\": 66,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1597266\",\n" +
                "        \"wname\": \"小狗（puppy）家用智能扫地机器人吸尘器地宝V-M611\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t1021/208/1002867554/216172/6205f1c1/5562c30cN734c21d2.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"1299\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1597266\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.4折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"699\",\n" +
                "        \"discount\": \"600.00\",\n" +
                "        \"activeId\": \"474141786\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1597266_32_2\",\n" +
                "        \"promotionId\": \"194345971\",\n" +
                "        \"soldRate\": 36,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1869569\",\n" +
                "        \"wname\": \"【满499-150】Swisse 月见草油胶囊 调节女性\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3034/126/195578581/244649/cbd9e1d2/5796d6adN30d872f9.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"159\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1909895\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"79\",\n" +
                "        \"discount\": \"80.00\",\n" +
                "        \"activeId\": \"474844067\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1869569_33_2\",\n" +
                "        \"promotionId\": \"194407662\",\n" +
                "        \"soldRate\": 28,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1973114112\",\n" +
                "        \"wname\": \"美度(MIDO)手表 贝伦赛丽系列BARONCELLI 全自动手表男机械 潮流时尚男士腕表 钢带白盘 M8600.4.26.1\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3253/31/4126839639/305817/d1124d4a/5836a71bN62076b2a.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"6000\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1956822604\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"3.9折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"2299\",\n" +
                "        \"discount\": \"3701.00\",\n" +
                "        \"activeId\": \"474572806\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1973114112_34_2\",\n" +
                "        \"promotionId\": \"3675090525\",\n" +
                "        \"soldRate\": 28,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1973344841\",\n" +
                "        \"wname\": \"【买2支打9折】YSL新款星辰圣罗兰女士口红 迷魅方管唇膏1.3g小样滋润保湿持久不脱色 星辰52#星星色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3664/251/1688021946/351873/82cbacb9/582ed7beNd0ec6008.png!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"238\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1956898772\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"3.6折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"85\",\n" +
                "        \"discount\": \"153.00\",\n" +
                "        \"activeId\": \"474390682\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1973344841_35_2\",\n" +
                "        \"promotionId\": \"3671478240\",\n" +
                "        \"soldRate\": 23,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1989595\",\n" +
                "        \"wname\": \"【京东超市】潘婷洗护套装乳液修护（洗发水750ml+护发素400ml）赠洗发水80ml*3+护发素80ml*2\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3667/142/1097108404/250824/d0582484/581bf0d8N2a0624f6.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"89\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"1989595\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.1折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"62.9\",\n" +
                "        \"discount\": \"26.10\",\n" +
                "        \"activeId\": \"474250266\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1989595_36_2\",\n" +
                "        \"promotionId\": \"194338277\",\n" +
                "        \"soldRate\": 34,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"2029547\",\n" +
                "        \"wname\": \"雷柏（Rapoo）V20S 光学游戏鼠标 银色烈焰版\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t1963/38/670430668/94924/288813c3/562059b3Ne596d4a5.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"139\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2029547\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.4折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"89\",\n" +
                "        \"discount\": \"50.00\",\n" +
                "        \"activeId\": \"474514508\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_2029547_37_2\",\n" +
                "        \"promotionId\": \"194386448\",\n" +
                "        \"soldRate\": 24,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3042413\",\n" +
                "        \"wname\": \"嘉实多（Castrol）全合成机油 磁护Magnatec 启停保 5W-30 A5/B5 SN 5L装 德国原装进口\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3820/205/622908659/427696/38ce0791/5816b1fbNb382617f.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"319.9\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2171067\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"246\",\n" +
                "        \"discount\": \"73.90\",\n" +
                "        \"activeId\": \"475205253\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3042413_38_2\",\n" +
                "        \"promotionId\": \"194431930\",\n" +
                "        \"soldRate\": 17,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"2189692\",\n" +
                "        \"wname\": \" ventry 泰国进口乳胶枕头PT3颗粒保健枕 高低颈椎枕 橡胶护颈枕 助睡眠枕芯   \",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3229/258/220999407/137469/d28a92d4/57ac3f7cNaa559784.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"235\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2189692\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"179\",\n" +
                "        \"discount\": \"56.00\",\n" +
                "        \"activeId\": \"474662129\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_2189692_39_2\",\n" +
                "        \"promotionId\": \"194381079\",\n" +
                "        \"soldRate\": 33,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"2562794\",\n" +
                "        \"wname\": \"【京东超市】卫龙 亲嘴条（辣条） 休闲零食 1100g/盒\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t2047/109/2177203500/404831/bf03a5b3/56f3a3ccN3a47d0af.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"29.9\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2562681\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.7折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"19.9\",\n" +
                "        \"discount\": \"10.00\",\n" +
                "        \"activeId\": \"473830919\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_2562794_40_2\",\n" +
                "        \"promotionId\": \"194328440\",\n" +
                "        \"soldRate\": 33,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3598058\",\n" +
                "        \"wname\": \"惠普(HP)畅游人Pavilion x360 13-u114TU 13.3英寸超薄360°笔记本(i5-7200U 4G 128GSSD IPS FHD 触控屏)银色\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3274/147/1238872865/92704/477813bd/57c8d89eNea152682.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"4699\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2774392\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"9.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"4199\",\n" +
                "        \"discount\": \"500.00\",\n" +
                "        \"activeId\": \"473767330\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3598058_41_2\",\n" +
                "        \"promotionId\": \"194315181\",\n" +
                "        \"soldRate\": 26,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"2960136\",\n" +
                "        \"wname\": \"欧博（OPO）YS01 养生壶电热养生杯陶瓷电热水杯煮茶壶器\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3781/243/1536178287/266226/370582d6/5829628dN9ec21bfb.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"119\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2960136\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"5.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"59\",\n" +
                "        \"discount\": \"60.00\",\n" +
                "        \"activeId\": \"472224522\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_2960136_42_2\",\n" +
                "        \"promotionId\": \"194215411\",\n" +
                "        \"soldRate\": 36,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3111785\",\n" +
                "        \"wname\": \"飞利浦（PHILIPS） S118/02电动剃须刀\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3280/313/624061761/181469/e439208/57bbfc4fNe8eef32c.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"209\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"3111785\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"149\",\n" +
                "        \"discount\": \"60.00\",\n" +
                "        \"activeId\": \"472388743\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3111785_43_2\",\n" +
                "        \"promotionId\": \"194220225\",\n" +
                "        \"soldRate\": 32,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3685382\",\n" +
                "        \"wname\": \"HEAD 羽毛球拍 两支装 初级情侣拍 含拍套(已穿线) L700\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3106/96/2013481097/288392/6a35708/57d7552aN243c79fd.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"99\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"3255367\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.0折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"79\",\n" +
                "        \"discount\": \"20.00\",\n" +
                "        \"activeId\": \"474566620\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3685382_44_2\",\n" +
                "        \"promotionId\": \"194368113\",\n" +
                "        \"soldRate\": 31,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"855620\",\n" +
                "        \"wname\": \"【京东超市】百雀羚 水嫩倍现至尚套装（洁面乳+精华水+精华霜）(补充水份，质地清润，改善干燥肤质)\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3034/335/2050410581/421349/7ca60a1/57d12076Nb19cbeb5.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"188\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"855620\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"6.9折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"129\",\n" +
                "        \"discount\": \"59.00\",\n" +
                "        \"activeId\": \"473850422\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_855620_45_2\",\n" +
                "        \"promotionId\": \"194329916\",\n" +
                "        \"soldRate\": 19,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"1059164\",\n" +
                "        \"wname\": \"倍轻松（breo） 眼部按摩器 isee4 智能按摩眼镜 眼保仪 眼轻松 眼部按摩仪 护眼仪\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_g15/M03/04/15/rBEhWFL5iMoIAAAAAAC7TAp_o30AAIXvgFIAxAAALtk296.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"1280\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"898150\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"7.1折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"899\",\n" +
                "        \"discount\": \"381.00\",\n" +
                "        \"activeId\": \"472893430\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_1059164_46_2\",\n" +
                "        \"promotionId\": \"194270741\",\n" +
                "        \"soldRate\": 27,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3530232\",\n" +
                "        \"wname\": \"康佳（KONKA）LED43S1 43英寸全高清10核HDR智能LED液晶平板电视 (黑+金)\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3103/125/703592603/207651/dfeae05b/57bd657aN2dd60c08.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"2399\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2003200\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": true,\n" +
                "        \"rate\": \"8.4折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85282,\n" +
                "        \"miaoShaPrice\": \"1999\",\n" +
                "        \"discount\": \"400.00\",\n" +
                "        \"activeId\": \"472465652\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3530232_47_2\",\n" +
                "        \"promotionId\": \"194234199\",\n" +
                "        \"soldRate\": 22,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 3,\n" +
                "        \"tagType\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"wareId\": \"3738548\",\n" +
                "        \"wname\": \"蔻驰 Coach 女士牛皮手提斜挎包粉红色34340SVDULF\",\n" +
                "        \"imageurl\": \"http://m.360buyimg.com/mobilecms/s220x220_jfs/t3178/17/2862749100/123028/5214e72e/57e89ae6N5a5fcfca.jpg!q70.jpg\",\n" +
                "        \"good\": \"\",\n" +
                "        \"jdPrice\": \"2099\",\n" +
                "        \"book\": \"false\",\n" +
                "        \"promotion\": \"false\",\n" +
                "        \"spuId\": \"2943696\",\n" +
                "        \"adword\": \"\",\n" +
                "        \"message\": \"\",\n" +
                "        \"canBuy\": \"true\",\n" +
                "        \"miaoSha\": false,\n" +
                "        \"rate\": \"7.2折\",\n" +
                "        \"startRemainTime\": -1117,\n" +
                "        \"endRemainTime\": 85281,\n" +
                "        \"miaoShaPrice\": \"1499\",\n" +
                "        \"discount\": \"600.00\",\n" +
                "        \"activeId\": \"474078735\",\n" +
                "        \"canFreeRead\": \"false\",\n" +
                "        \"moreFunId\": \"searchCatelogy\",\n" +
                "        \"cid\": \"\",\n" +
                "        \"cName\": \"\",\n" +
                "        \"sourceValue\": \"44_1_3738548_48_2\",\n" +
                "        \"promotionId\": \"194345975\",\n" +
                "        \"soldRate\": 100,\n" +
                "        \"clockNum\": 0,\n" +
                "        \"tips\": \"20件抢光啦\",\n" +
                "        \"mTips\": \"20件已秒杀完\",\n" +
                "        \"startTimeShow\": \"22:00\",\n" +
                "        \"resultSort\": 4,\n" +
                "        \"tagType\": 0\n" +
                "      }\n" +
                "    ]\n" +
                "}";
        Gson gson = new Gson();
        ZNACBean znacBean = gson.fromJson(json, ZNACBean.class);
        objects.clear();
        objects.addAll(znacBean.getItemList());
        znActItemAdapter.notifyDataSetChanged();
    }
}
