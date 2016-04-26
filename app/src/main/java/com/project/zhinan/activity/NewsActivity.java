package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.adapter.columnAdapter;
import com.project.zhinan.adapter.newsDetailAdapter;
import com.project.zhinan.bean.newsbean;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {

    private ListView listview_1, listview_2;
    private List<String> list1;
    private channelAdapter channeladapter;
    private Intent intent;
    private String newsdatas = "[{\"title\":\"马云被曝曾要求新员工上下班时间不得超过15分钟\",\"description\":\"【环球科技综合报道】据印度媒体4月24日报道，邓肯·克拉克(Duncan Clark)在新书《阿里巴巴：马云的基业》(Alibaba: The House that Jack Ma Built)中曝出马云在阿里巴巴创业阶段曾要求员工住在公司附近，以节省上下班时间。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-26 08:46\"},{\"title\":\"记者调查全国公务员考试泄题事件：不排除商业炒作\",\"description\":\"据中国之声《央广夜新闻》报道，4月23日，全国共有25个省（市、自治区）举行公务员笔试，总报名人数达到409.5万，堪称最大规模公务员考试。就在网上疯传江西省公务员考试存在泄题嫌疑的同时，也有四川、山西、广西等多个省区的考生反映类似的情况。\",\"category\":\"国内综合\",\"pubDate\":\"2016-04-26 08:44\"},{\"title\":\"湖南永州一小学女生被掌掴32次 5名女生拍视频扩散\",\"description\":\"近日，一段有关湖南永州市道县数名女孩殴打另一名女生的视频引发了网民热议。道县方面的调查结果显示：从道县一民办学校辍学将近半年的15岁女孩马某某，因生活琐事对车头中心小学14岁的六年级女生熊某心生怨恨。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-26 08:43\"},{\"title\":\"城管执法中受伤 女儿画画送爸爸：和小贩做好朋友\",\"description\":\"扬子晚报记者采访了解到，这幅画背后有一个感人心扉的故事，画是小姑娘给她当城管的爸爸的，孩子希望老是受伤的爸爸和小贩“做好朋友”，再也不要受伤。这张图片始发于常州天宁城管大队红梅中队副中队长刘亮的朋友圈。”　　这件事发生的前两天，刘亮所在中队一位协管员也在执法过程中被撞伤。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-26 08:42\"},{\"title\":\"亚马逊实体书店 未来人类的读书新模式\",\"description\":\"&nbsp; 以在线销售图书起家的美国电商巨头亚马逊去年秋天在西雅图开设首家实体书店。该店只有5000种图书，店面远小于一般的巴恩斯－诺布尔连锁店，相对来说小而精。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-26 08:40\"},{\"title\":\"揭秘95后追星应援文化：舍得投入时间和金钱\",\"description\":\"清晨5点40分，北京东直门附近一处青年旅舍里，灰子被手机闹钟叫醒。与此同时，工体北门的小空地上已经支起了10余个大大小小的摊点，吸引着越来越多的粉丝聚集，领取或购买应援物。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-25 17:10\"},{\"title\":\"发改委：一季度经济开局平稳 积极因素正在增加\",\"description\":\"据国家发改委网站消息，国家发展改革委副主任宁吉喆日前指出，一季度经济实现平稳开局，积极因素正在增加，但也要看到，经济发展面临不少困难和问题，平稳运行的基础尚不牢固。4月24日，国家发展改革委副主任宁吉喆出席“2016年经贸形势报告会”，作了题为“当前经济形势和下一步宏观政策举措”的主旨演讲。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 17:10\"},{\"title\":\"营改增 对接 “最后一厘米”\",\"description\":\"目前，税务总局正在加紧相关软件测试，加强系统的融合对接，并对全国90个二手房交易热点城市实行重点监控，确保税制转换平稳顺利。“实现行业税负只减不增的关键，就是完善抵扣链条，充分享受增值税进项抵扣的制度红利。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 16:24\"},{\"title\":\"90后任性海归吃不了苦 职场“隐形”偏见你有吗\",\"description\":\"近日，一则“90后实习生拒绝给同事订盒饭”的事件引发舆论热议。有人觉得，这名90后不懂职场法则，我行我素；但也有网友认为，使唤实习生订盒饭是职场陋习，相关指责更是一种陈腐偏见。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-25 16:26\"},{\"title\":\"养老金入市脚步渐进 风控成为投资运营焦点\",\"description\":\"主持人左永刚：目前，地方养老金结余资金委托投资运营已具备法律法规依据，今年下半年将有更多配套政策文件鱼贯而出。今年3月28日《全国社会保险基金条例》（以下简称《条例》）的发布，意味着全国社保基金理事会管理运营资金的范围将进一步扩大，为基本养老金委托投资运营提供了制度安排。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 16:05\"},{\"title\":\"4月国债开售“一抢而空” 年内还有7次购买机会\",\"description\":\"根据财政部公布的2016年国债发行计划表，今年3月至11月的每月10日都会有国债发行，发行期限有三年和五年两种，有4次为凭证式国债，5次为电子式储蓄国债。需要注意的是，若是购买凭证式国债，只能在银行柜台购买；若是电子式国债，除了在网点柜台进行购买外，也可通过指定银行的网银购买。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 15:06\"},{\"title\":\"多地拟推楼市限价限购新政 开发商周末集中推盘\",\"description\":\"4月23日至24日，南京被曝出将收紧调控的首个周末，也可能是南京楼市调控新政出台前的最后一个周末，开发商闻风而动，集中推盘入市。而在业内人士看来，即便南京推出上述楼市收紧新政，“开发商也完全可以选择捂盘惜售来阻挡对于价格的限制”。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 14:35\"},{\"title\":\"基金一季度重仓消费股 偏股基金平均仓位微升\",\"description\":\"在今年A股开年遇冷的行情下，基金一季度的操作整体偏谨慎，全部偏股基金平均股票仓位为61.87%，较去年末上升1.56个百分点，其中股基仓位平均不足九成。 此外，尽管金融板块整体遭减持，但在前十只增持力度最大的个股中，农业银行、浦发银行位列其中，持有它们的基金分别为22只、21只。\",\"category\":\"财经新闻\",\"pubDate\":\"2016-04-25 14:19\"},{\"title\":\"透视“城市留守儿童”：11岁还在吃手 不写作业\",\"description\":\"重庆某度假村，大人们猫在房间里打麻将斗地主，孩子在大堂里独自玩电脑游戏，一根电源线，一台平板电脑，就这样度过整整一天。”　　原来，露露的父母在她很小的时候就离开北京到外地去工作了，直到露露上小学二年级才回京。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-25 09:54\"},{\"title\":\"\\\"跳泥坑\\\"西南大学运动会又出新招 接力棒用灭火器\",\"description\":\"　　那个运动会跳泥坑的西南大学，又出新招了…心疼你校学生…肩扛、手提、挟抱…怎么顺手怎么来。\",\"category\":\"科技教育\",\"pubDate\":\"2016-04-25 08:49\"}]";
    private String newsdatas2 = "[{\"title\":\"敬老院里“黄昏恋” 5对老人结良缘\",\"description\":\"位于凤县城郊的福利园区里，八旬的王国忠老人和六旬的张明秀坦然开始了“黄昏恋”。\",\"category\":\"频道推荐\",\"pubDate\":\"2011-04-19 17:45\"},{\"title\":\"“西飞”“雅奇”挽手落户经开区\",\"description\":\"&nbsp;&nbsp;&nbsp; 项目首期投资1000万美元，主要为国内外航空企业提供航空复合材料加工、分拨和配送等进出口业务，\",\"category\":\"频道推荐\",\"pubDate\":\"2011-04-19 17:43\"},{\"title\":\"铜川市十五届人大一次会议开幕 将建西铜动车组\",\"description\":\"&nbsp;&nbsp;&nbsp; 为缓解停车难，铜川今年将建1500个车位，未来五年将建成西铜动车组。\",\"category\":\"频道推荐\",\"pubDate\":\"2011-04-19 17:25\"},{\"title\":\"咸阳摊贩拿“血脖肉”做包子 含淋巴结与脂肪瘤\",\"description\":\"&nbsp;&nbsp;&nbsp; 正常的血脖肉可以食用，但是带有淋巴结的血脖肉食用后很容易感染疾病。\",\"category\":\"内容页正文推荐\",\"pubDate\":\"2011-04-19 17:21\"},{\"title\":\"寻找西安最美街道和角落\",\"description\":\"&nbsp;&nbsp;&nbsp; 西安这个充满风景的城市绽放着独特的魅力，如果我们每个人都用心去发现、去发掘，就能体会到西安这座城市的雄浑厚重和生机勃发。\",\"category\":\"链接稿件库\",\"pubDate\":\"2011-04-19 07:57\"},{\"title\":\"警花不让须眉 西铁女警两月查处各类案件282起\",\"description\":\"&nbsp;&nbsp; 今年以来，西安铁路公安局女民警共参与查处各类案件282起，为铁路公安保卫工作作出积极贡献。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-03-07 16:14\"},{\"title\":\"西安日报明开消费者维权专栏 帮您解决消费难题\",\"description\":\"&nbsp;&nbsp;&nbsp; 从明日起，日报将开设“消费难题，党报助您解决”专栏，并邀请专家、法律顾问从专业的角度为读者提供消费提醒和问题解决之道。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-03-07 16:10\"},{\"title\":\"莲湖缉毒民警乔扮烟民 千里追踪破特大贩毒案\",\"description\":\"&nbsp;&nbsp; 莲湖警方缉毒民警乔扮成“烟民”追踪千里，最终将我市一毒贩成功擒获。\",\"category\":\"莲湖区\",\"pubDate\":\"2011-03-07 15:58\"},{\"title\":\"5年内西安南门东门将建立交桥 缓解两地拥堵\",\"description\":\"文锦)从今年开始的今后五年期间，西安市将建设一批高架桥和立交桥来缓解日益加剧的交通拥堵。2月19日记者从西安市建委了解到，目前古城最易出现堵塞的南门、东门等地段将建起立交桥。\",\"category\":\"西安交通\",\"pubDate\":\"2011-02-21 09:31\"},{\"title\":\"2011年西安市蓝天目标确定：300 天以上\",\"description\":\"&nbsp;&nbsp;&nbsp; 今年西安市将落实大气排污许可证制度，建立大气污染联防联控机制，将优良天数稳定在300天以上。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-21 09:37\"},{\"title\":\"西安今年建黑河湿地保护区 占地约为城区面积20倍\",\"description\":\"一期工程将于今年开工，前期主要进行基础设施建设。\",\"category\":\"西安建设\",\"pubDate\":\"2011-02-18 11:07\"},{\"title\":\"西安市行政中心北迁 4条通勤班车15路公交可到达\",\"description\":\"近期西安市政府有关部门将陆续搬迁至北郊行政中心办公，为方便市民前去办事，西安市运管处对部分公交线路进行了调整，同时开通4条通勤班车方便广大干部职工。西安市委办公新址周边调整、延伸、新开7线路1.\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-18 10:47\"},{\"title\":\"2011年西安进入“北城时代”\",\"description\":\"&nbsp;&nbsp;&nbsp; 在“十二五”开局之年，高铁时代、地铁时代都在城市新中心的北城迈入高速发展的轨道……\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-18 10:47\"},{\"title\":\"西安将建3条城际铁路 最快时速可达250公里\",\"description\":\"&nbsp;&nbsp;&nbsp; 力争上半年开工建设西安—临潼、西安北客站—机场—咸阳、西安—阎良—富平三条线路。\",\"category\":\"西安交通\",\"pubDate\":\"2011-02-15 09:22\"},{\"title\":\"西安80岁以上老人可领高龄补贴 百岁老人上门发放\",\"description\":\"昨日，《西安市高龄老人生活保健补贴发放管理暂行办法》正式出台，只要户籍在西安市，年龄在80周岁以上者，均可申请领取高龄补贴。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-15 09:05\"},{\"title\":\"西安将创建“公交城市” 运营车辆力争突破1万辆\",\"description\":\"&nbsp;&nbsp;&nbsp; “十二五”期间，西安将全方位优先发展公共交通，创建“公交城市”，全市公交运营车辆将力争突破１万辆。\",\"category\":\"西安建设\",\"pubDate\":\"2011-02-14 10:04\"},{\"title\":\"西安各区新年“排兵布阵” 给力国际化大都市建设\",\"description\":\"今年是“十二五”规划的开局之年，也是西安建设国际化大都市的关键之年，加快基础设施改造进程，打造有影响力的文化产业示范园，推进商圈发展，不断提升民生工程等成为今年各区的重点工作。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-12 10:22\"},{\"title\":\"西安今年将新建未成年流浪儿童救助保护中心\",\"description\":\"&nbsp;&nbsp;&nbsp; 西安计划今年3月，新建未成年流浪儿童救助保护中心，建成后将容纳200至250余名流浪儿童。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-12 09:40\"},{\"title\":\"春节长假西安万余民警坚守岗位 维护社会治安稳定\",\"description\":\"2011年春节期间，西安1万余名民警坚守岗位，有力地维护了全市社会治安稳定。为了抓好大型活动管理的安全管理，西安警方共出动警力5849人次，检查重点单位、场所2104家，消除安全隐患351处。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-02-10 10:11\"},{\"title\":\"地铁二号线各车站主体装修将于3月底完成\",\"description\":\"昨日，记者走进地铁二号线市图书馆站，看到车站的内部装修已经初具规模。现场负责人告诉记者，市图书馆站站台层装修工作将于月底结束，3月底站厅层的装修将完工并移交运营中心。\",\"category\":\"西安关注\",\"pubDate\":\"2011-02-10 09:56\"},{\"title\":\"今年西安将建8.6万套保障房 比去年翻两番\",\"description\":\"今年西安市将完成建设8.6万套保障性住房，比去年翻两番，其中廉租住房1.31万套，经适房2万套，公共租赁住房3.7万套，限价商品房1.59万套。\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-30 09:59\"},{\"title\":\"“十二五”西安市重点推进“十大工程”建设\",\"description\":\"&nbsp;&nbsp; “十二五”期间，西安市将重点推进综合交通体系、能源保障等“十大工程”建设，涉及重大项目300个，总投资15000亿元。\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-30 09:53\"},{\"title\":\"地铁一号线今年底全线隧道基本贯通\",\"description\":\"据中铁十八局西安地铁建设项目部负责人介绍，后卫寨车站建设历时10个月时间，这也创造了西安地铁车站建设的第一速度。\",\"category\":\"西安关注\",\"pubDate\":\"2011-01-30 09:48\"},{\"title\":\"西安市政务中心28日正式启用 30个市级部门到位\",\"description\":\"&nbsp;&nbsp;&nbsp; 1月24日，西安市政务中心已开始试运行，30个市级部门230余名工作人员已全部到位，平均每天接访量100余件。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-28 09:40\"},{\"title\":\"西安东大街拓宽改造今年完成 整体拓宽至50米\",\"description\":\"在1月19日～21日召开的西安市碑林区第十六届人民代表大会第五次会议上，碑林区区长董劲威表示，东大街拓宽改造今年完成。按照计划，碑林区今年将完成东大街道路拓宽改造。\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-24 09:12\"},{\"title\":\"“群众厨房”惠民加工中心正式竣工开业\",\"description\":\"机械化、流水线生产的馒头味道，让这位市民监督员非常满意。当日，市放心馒头工程办公室还专门邀请30名市民代表参观加工中心的生产全过程。\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-24 09:09\"},{\"title\":\"委员关注交通拥堵 19份提案给力西安缓堵\",\"description\":\"&nbsp;&nbsp;&nbsp; 日益拥堵的交通环境已经成为社会关注的热点，如何缓解拥堵也成为本次政协会上委员们关心的焦点问题。\",\"category\":\"西安交通\",\"pubDate\":\"2011-01-24 09:06\"},{\"title\":\"建国际化大都市西安需持续发力\",\"description\":\"&nbsp;&nbsp;&nbsp; 在省政协十届四次会议上，许多委员针对西安的发展提出建议，显示出他们对西安的关心与期盼。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-21 09:19\"},{\"title\":\"地铁三号线10月开工 西安交通将再度接受考验\",\"description\":\"今年4月，鱼化寨、吉祥村及国际港务区高架段这3个试验段土建工程将正式开工。本次树木移植从吉祥村和辛家庙率先启动，春节后将进行大范围的树木移植与管线改迁工作。\",\"category\":\"西安关注\",\"pubDate\":\"2011-01-21 09:17\"},{\"title\":\"西北最大农产品市场运营 已为春节消费高峰准备\",\"description\":\"昨日，西安市西三环旁的西部欣桥农产品物流中心，作为西北地区规模最大的现代化农产品批发市场，昨日开始正式开业启动，日供新鲜蔬菜可达万吨以上。目前该市场仍然为试运营，并已为今年春节蔬菜副食品消费高峰储备了1.5万吨应急蔬菜。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-19 09:00\"},{\"title\":\"西安今年建2.1万套公租房 解决低收入居民住房\",\"description\":\"据了解，在1月14日召开的西安市住房保障和房屋管理局2010年度目标责任考评工作大会上，该局明确提出今年通过政府和各类企业、机构投资建设的形式，将建设公共租赁住房70万平方米、2.1万套。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-19 08:57\"},{\"title\":\"陕西省政协委员为缓堵开出五“处方”\",\"description\":\"多位委员针对缓解交通拥堵进行调研，分析其原因，并提出建议。对机动车故意闯禁区、随意掉头、变道、争道抢行、逆向行驶、闯红灯等严重交通违法行为，发现一起查处一起，各类重点车辆、重点驾驶人建立“交通违法黑名单”，实行重点监管。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-17 09:58\"},{\"title\":\"放心馒头覆盖城六区 流动销售车进社区方便市民\",\"description\":\"&nbsp;&nbsp;&nbsp; 西安市长陈宝根说，放心馒头工程要继续做强品牌，坚持政府引导，市场化运作、企业化运营，让市民持续放心。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-17 09:33\"},{\"title\":\"西安2011年力争让3.9万下岗失业人员再就业\",\"description\":\"\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-14 10:56\"},{\"title\":\"西安市首届十大道德模范评出 郭随宝等人当选\",\"description\":\"\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-14 10:36\"},{\"title\":\"西安今年投资966亿用于327个重点项目建设\",\"description\":\"&nbsp;&nbsp;&nbsp; 西安市今年将发挥重大项目的集聚、引领、带动作用，安排年度重点建设项目327个，总投资711 5亿元。\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-14 10:30\"},{\"title\":\"西安市民除夕到元宵期间可燃放爆竹\",\"description\":\"\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-14 10:19\"},{\"title\":\"孙清云寄语部门区县：网络问政应该成为一种常态\",\"description\":\"\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-11 14:45\"},{\"title\":\"西安火车北站投入运营 22条高铁动车入北站\",\"description\":\"&nbsp;&nbsp;&nbsp; 西安火车北站投入运营，市公交总公司263路、264路、265路、266路四条线路今日正式开通。\",\"category\":\"新闻推荐\",\"pubDate\":\"2011-01-11 14:48\"},{\"title\":\"专家：西安须打造6大中心 最有资格建大物流城市\",\"description\":\"\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-06 09:40\"},{\"title\":\"西安地铁2号线人防工程1月4日起开始验收\",\"description\":\"\",\"category\":\"西安关注\",\"pubDate\":\"2011-01-06 09:39\"},{\"title\":\"赵正永强调加快建设西安国际化大都市\",\"description\":\"&nbsp;&nbsp;&nbsp; 赵正永对西安市的工作给予充分肯定，希望西安市要进一步加快发展步伐，勇立我省“十二五”发展的潮头。\",\"category\":\"频道推荐\",\"pubDate\":\"2011-01-05 09:22\"},{\"title\":\"\\\"一河三带十组团\\\"&nbsp; 西咸新区将成为大都市新核心\",\"description\":\"\",\"category\":\"西安建设\",\"pubDate\":\"2011-01-05 09:10\"},{\"title\":\"东北二环立交通车 道路拥堵将得到较大缓解\",\"description\":\"\",\"category\":\"西安交通\",\"pubDate\":\"2010-12-31 09:58\"},{\"title\":\"西安将建快速公交 未来5年建成地铁1、2、3号线\",\"description\":\"\",\"category\":\"西安交通\",\"pubDate\":\"2010-12-31 09:47\"},{\"title\":\"央视《新闻联播》聚焦西安新变化\",\"description\":\"\",\"category\":\"新闻推荐\",\"pubDate\":\"2010-12-30 10:33\"},{\"title\":\"西安北客站地下停车场主体封顶 明年3月底投用\",\"description\":\"\",\"category\":\"西安关注\",\"pubDate\":\"2010-12-29 10:21\"},{\"title\":\"西安未来五年规划给力民生\",\"description\":\"\",\"category\":\"西安建设\",\"pubDate\":\"2010-12-29 10:21\"},{\"title\":\"西安2011年生产总值增长14% 建成经适房150万m2\",\"description\":\"&nbsp;&nbsp;&nbsp; 2011年，西安市将以科学发展观为主题，以加快转变经济发展方式为主线，保持西安经济社会又好又快发展的良好势头。\",\"category\":\"动态新闻\",\"pubDate\":\"2010-12-29 10:05\"},{\"title\":\"新华网陕西频道　：：最新陕西新闻 尽在新华网：：\",\"description\":\"\",\"category\":\"西安经济\",\"pubDate\":\"2010-12-29 10:05\"},{\"title\":\"西安—成都高铁开工 4年后去成都2个半小时\",\"description\":\"\",\"category\":\"西安交通\",\"pubDate\":\"2010-12-24 10:09\"},{\"title\":\"西安地铁一号线首段盾构隧道双线贯通\",\"description\":\"\",\"category\":\"西安关注\",\"pubDate\":\"2010-12-23 10:42\"},{\"title\":\"张家堡广场将建99米高“长安门”\",\"description\":\"\",\"category\":\"西安建设\",\"pubDate\":\"2010-12-23 10:42\"}]";
    private List<newsbean> datasNews = new ArrayList<newsbean>();
    private List<newsbean> datasNews2 = new ArrayList<newsbean>();
    private List<newsbean> list2 = new ArrayList<newsbean>();
    private newsDetailAdapter columnadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        MyApplication.addNewsActivity(this);
        listview_1 = (ListView) findViewById(R.id.listview_1);
        listview_2 = (ListView) findViewById(R.id.listview_2);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        Gson gson = new Gson();
        datasNews = gson.fromJson(newsdatas, new TypeToken<List<newsbean>>() {
        }.getType());
        datasNews2 = gson.fromJson(newsdatas2, new TypeToken<List<newsbean>>() {
        }.getType());
        list1 = new ArrayList<String>();
        list1.add(0, "华商报");
        list1.add(1, "陕西农林");
        list1.add(2, "三秦都市");
        list1.add(3, "西安晚报");
        list2.addAll(datasNews);
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
        columnadapter = new newsDetailAdapter(this, list2);
        listview_1.setAdapter(channeladapter);
        listview_2.setAdapter(columnadapter);
    }

    private void addListener() {
        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                channeladapter.pos = position;
                channeladapter.notifyDataSetChanged();
                list2.clear();
                if (position % 2 == 0) {
                    list2.addAll(datasNews);
                } else {
                    list2.addAll(datasNews2);
                }
                columnadapter.notifyDataSetChanged();
            }
        });
        listview_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NewsActivity.this, MyCommentActivity.class));
            }
        });
    }

}
