head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示条件番号(WEB3PvInfoConditionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
                   2005/10/07 沈  迪(sinocom) 追加
                   2006/5/22 凌建平(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo012
                   2006/9/13 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo013
                   2007/2/26 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo014
Revesion History : 2007/7/13 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo015
Revesion History : 2007/9/13 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo016
Revesion History : 2007/12/10 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo017
Revesion History : 2008/02/19 栄イ(中訊) プライベートインフォメーションの仕様変更・ＤＢレイアウトNo019
Revesion History : 2008/10/06 陸文静(中訊) プライベートインフォメーションの仕様変更No107
Revesion History : 2008/10/06 陸文静(中訊) プライベートインフォメーションの仕様変更No110
*/
package webbroker3.common.define;

/** 
 * 表示条件番号
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoConditionDef
{

    /**
     * 0000：ダイレクト指定　@　@　@　@　@  　@　@
     */
    public final static String DIRECT_ASSIGN = "0000";

    /**
     * 1001:入金請求発生&信用口座開設 　@　@
     */
    public final static String DEPOSIT_REQUEST_MARGIN_ACC_OPEN = "1001";

    /**
     * 1002:入金請求発生&信用口座未開設　@　@　@　@  　@　@
     */
    public final static String DEPOSIT_REQUEST_MARGIN_ACC_CLOSE = "1002";

    /**
     * 1003立替金発生　@　@　@　@  　@　@
     */
    public final static String ADVANCE_GENERATION = "1003";

    /**
     * 1004:立替金実績　@　@　@　@  　@　@
     */
    public final static String ADVANCE_RESULTS = "1004";

    /**
     * 1005:証拠金不足　@　@　@　@  　@　@
     */
    public final static String IFO_DEPOSIT_SHORTAGE = "1005";

    /**
     * 1006:決済期限間近（一ヶ月前）の建玉保有 　@　@　@　@  　@　@
     */
    public final static String SETTLE_BEF_AMONTH_CONTRACT_HAS = "1006";

    /**
     * 1007:決済期限間近（一週間前）の建玉保有　@　@　@  　@　@
     */
    public final static String SETTLE_BEF_AWEEK_CONTRACT_HAS = "1007";

    /**
     * 1008:信用口座開設　@　@　@　@  　@　@
     */
    public final static String MARGIN_ACCOUNT_OPEN = "1008";

    /**
     * 1009:信用口座未開設　@　@　@　@  　@　@
     */
    public final static String MARGIN_ACCOUNT_CLOSE = "1009";

    /**
     * 1010:オプション口座開設　@　@　@　@  　@　@
     */
    public final static String OPTION_ACCOUNT_OPEN = "1010";

    /**
     * 1011:株式保有　@　@　@　@  　@　@
     */
    public final static String STOCKS_HAS = "1011";

    /**
     * 1012:信用建玉保有　@　@　@　@  　@　@
     */
    public final static String MARGIN_CONTRACT_HAS = "1012";

    /**
     * 1013:投信保有　@　@　@　@  　@　@
     */
    public final static String MUTUAL_HAS = "1013";

    /**
     * 1014:累投保有　@　@　@  　@　@
     */
    public final static String RUITO_HAS = "1014";

    /**
     * 1015:オプション建玉保有　@　@　@　@  　@　@
     */
    public final static String OPTION_CONTRACT_HAS = "1015";

    /**
     * 1016:ミニ株保有　@　@　@　@  　@　@
     */
    public final static String MINI_STOCK_HAS = "1016";

    /**
     * 1017:先物保有　@　@　@　@  　@　@
     */
    public final static String FUTURE_HAS = "1017";

    /**
     * 1018:預り金有り&証券残無し　@　@　@　@  　@　@
     */
    public final static String ACCOUNT_BAL_NON_SECURITIES_BAL = "1018";

    /**
     * 1019:預り金無し&証券残無し　@　@　@  　@　@
     */
    public final static String NON_ACCOUNT_BAL_NON_SECURITIES_BAL = "1019";

    /**
     * 1020:株式・信用注文発生（当日） 　@　@　@　@  　@　@
     */
    public final static String STOCK_MARGIN_ORDER_GENERATION_TODAY = "1020";

    /**
     * 1021:株式・信用注文発生（翌日） 　@　@　@　@  　@　@
     */
    public final static String STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY = "1021";

    /**
     * 1022:株式・信用約定発生　@　@　@　@  　@　@
     */
    public final static String STOCK_MARGIN_EXECUTE_GENERATION = "1022";

    /**
     * 1023:全顧客　@　@　@　@  　@　@
     */
    public final static String ALL_ACCOUNT = "1023";

    /**
     * 1024:メールアドレス未登録　@　@　@　@  　@　@
     */
    public final static String MAIL_ADDRESS_NON_REGIST = "1024";

    /**
     * 1025:IPO当選　@　@　@　@  　@　@
     */
    public final static String IPO_ELECTION = "1025";

    /**
     * 1026:IPO繰上げ当選　@　@　@　@  　@　@
     */
    public final static String IPO_RETRY_ELECTION = "1026";

    /**
     * 1027:取引停止　@　@　@　@  　@　@
     */
    public final static String TRADE_SUSPENSION = "1027";

    /**
     * 1028:ログインパスワード変更要
     */
    public final static String LOGIN_PASSWORD_CHANGE = "1028";
    
    /**
     * 1029:外国証券口座開設
     */
    public final static String FEQ_ACCOUNT_OPEN = "1029";

    /**
     * 1030:外株保有
     */
    public final static String FEQ_HAS = "1030";

    /**
     * 1031:外株注文発生（当日）
     */
    public final static String FEQ_ORDER_GENERATION_TODAY = "1031";

    /**
     * 1032:外株注文発生（翌日）
     */
    public final static String FEQ_ORDER_GENERATION_NEXT_DAY = "1032";

    /**
     * 1033:外株約定発生
     */
    public final static String FEQ_EXECUTE_GENERATION = "1033";

    /**
     * 1034：モバイル専用口座開設
     */
    public final static String ONLY_MOBILE_OPEN = "1034";

    /**
     * 1035：モバイル専用口座未開設
     */
    public final static String ONLY_MOBILE_NOT_OPEN = "1035";

    /**
     * 1036：証券担保ローン口座開設
     */
    public final static String SECURED_LOAN_ACCOUNT_OPEN = "1036";

    /**
     * 1037：書面交付日より11ヶ月経過
     */
    public final static String FROM_DELIVERY_DATE_11MONTH = "1037";

    /**
     * 1038：PTS口座開設
     */
    public final static String PTS_ACCOUNT_OPEN = "1038";

    /**
     * 1039：PTS口座未開設
     */
    public final static String PTS_ACCOUNT_CLOSE = "1039";
    
    /**
     * 1041：20％割れ1日＆30％割れ5日以下
     */
    public final static String BREAK_1DAY_AND_5DAY_DOWN = "1041";

    /**
     * 1042：20％割れ1日＆30％割れ6日
     */
    public final static String BREAK_1DAY_AND_6DAY = "1042";

    /**
     * 1043：20％割れ2日＆30％割れ6日以下
     */
    public final static String BREAK_2DAY_AND_6DAY_DOWN = "1043";

    /**
     * 1044：20％割れ3日以上
     */
    public final static String BREAK_3DAY_OVER = "1044";

    /**
     * 1045：30％割れ2～4日
     */
    public final static String BREAK_2TO4DAY = "1045";

    /**
     * 1046：30％割れ5日
     */
    public final static String BREAK_5DAY = "1046";

    /**
     * 1047：30％割れ6日
     */
    public final static String BREAK_6DAY = "1047";

    /**
     * 1048：30％割れ7日以上
     */
    public final static String BREAK_7DAY_OVER = "1048";

    /**
     * 1049：一部出金停止
     */
    public final static String PART_PAYMENT_STOP = "1049";

    /**
     * 1050：全額出金停止
     */
    public final static String FULL_PAYMENT_STOP = "1050";

    /**
     * 1051：手数料割引キャンペーン
     */
    public final static String COMMISSION_DISCOUNT_CAMPAIGN = "1051";

    /**
     * 1052：　@預り金請求
     */
    public final static String FROM_DEPOSIT_REQUEST = "1052";

    /**
     * 1053：　@追証請求
     */
    public final static String ADDITIONAL_DEPOSIT_REQUEST = "1053";

    /**
     * 1054：　@不足金発生＆信用口座未開設
     */
    public final static String SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE = "1054";

    /**
     * 1055：　@不足金発生＆信用口座開設
     */
    public final static String SHORT_FALL_GENERATION_MARGIN_ACC_OPEN = "1055";

    /**
     * 1056：　@第一水準追証発生
     */
    public final static String FIRST_ADDITIONAL_DEPOSIT_OCCUR = "1056";

    /**
     * 1057：　@第二水準追証発生
     */
    public final static String SECOND_ADDITIONAL_DEPOSIT_OCCUR = "1057";

    /**
     * 1058：　@CFD口座開設
     */
    public final static String CFD_ACCOUNT_OPEN = "1058";

    /**
     * 1059：　@CFD口座未開設
     */
    public final static String CFD_ACCOUNT_CLOSE = "1059";
}
@
