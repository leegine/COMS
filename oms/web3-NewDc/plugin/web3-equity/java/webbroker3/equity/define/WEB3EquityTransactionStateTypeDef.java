head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTransactionStateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理状況区分 定数定義インタフェイス(WEB3EquityTransactionStateTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 戴義波(中訊) 作成
*/
package webbroker3.equity.define;

/**
 * @@author 戴義波
 * @@version 1.0
 */
public interface WEB3EquityTransactionStateTypeDef {

    /**
     * ----------------------受付区分:受付未済------------------------------------------------
     */
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 初期値
     * 000:処理状況区分
     */
    public final static String NOT_PROMISE_INITIAL_VALUE = "000";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 取消中
     * 001:処理状況区分
     */
    public final static String NOT_PROMISE_CANCELING = "001";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 一部取消完了
     * 002:処理状況区分
     */
    public final static String NOT_PROMISE_PART_CANCELED = "002";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 全部取消完了
     * 003:処理状況区分
     */
    public final static String NOT_PROMISE_CANCELED = "003";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 取消失敗
     * 004:処理状況区分
     */
    public final static String NOT_PROMISE_CANCEL_ERROR = "004";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 訂正中
     * 005:処理状況区分
     */
    public final static String NOT_PROMISE_CHANGING = "005";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 一部訂正完了
     * 006:処理状況区分
     */
    public final static String NOT_PROMISE_PARTIALLY_CHANGED = "006";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 全部訂正完了
     * 007:処理状況区分
     */
    public final static String NOT_PROMISE_CHANGED = "007";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 訂正失敗
     * 008:処理状況区分
     */
    public final static String NOT_PROMISE_CHANGE_ERROR = "008";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 エラー
     * 009:処理状況区分
     */
    public final static String NOT_PROMISE_ERROR = "009";    

    /**
     * ----------------------受付区分:受付済--------約定状態区分 未約定----------------------------------------
     */
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 初期値
     * 100:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_INITIAL_VALUE = "100";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 取消中
     * 101:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCELING = "101";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 一部取消完了
     * 102:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_PART_CANCELED = "102";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 全部取消完了
     * 103:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCELED = "103";

    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 取消失敗
     * 104:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCEL_ERROR = "104";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 訂正中
     * 105:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGING = "105";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 一部訂正完了
     * 106:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_PARTIALLY_CHANGED = "106";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 全部訂正完了
     * 107:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGED = "107";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 訂正失敗
     * 108:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGE_ERROR = "108";
    
    /**
     * 約定状態区分 未約定, 注文訂正・取消区分 エラー
     * 109:処理状況区分
     */
    public final static String NOT_NAN_NOT_PROMISE_ERROR = "109";    

    /**
     * ----------------------受付区分:受付済--------約定状態区分 一部成立----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 110:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_INITIAL_VALUE = "110";

    /**
     * 注文訂正・取消区分 取消中
     * 111:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCELING = "111";

    /**
     * 注文訂正・取消区分 一部取消完了
     * 112:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_PART_CANCELED = "112";

    /**
     * 注文訂正・取消区分 全部取消完了
     * 113:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCELED = "113";

    /**
     * 注文訂正・取消区分 取消失敗
     * 114:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCEL_ERROR = "114";
    
    /**
     * 注文訂正・取消区分 訂正中
     * 115:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGING = "115";
    
    /**
     * 注文訂正・取消区分 一部訂正完了
     * 116:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_PARTIALLY_CHANGED = "116";
    
    /**
     * 注文訂正・取消区分 全部訂正完了
     * 117:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGED = "117";
    
    /**
     * 注文訂正・取消区分 訂正失敗
     * 118:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGE_ERROR = "118";
    
    /**
     * 注文訂正・取消区分 エラー
     * 119:処理状況区分
     */
    public final static String NOT_NAN_ONE_COMPLETE_ERROR = "119";    


    /**
     * ----------------------受付区分:受付済--------約定状態区分 全部成立----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 120:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_INITIAL_VALUE = "120";

    /**
     * 注文訂正・取消区分 取消中
     * 121:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCELING = "121";

    /**
     * 注文訂正・取消区分 一部取消完了
     * 122:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_PART_CANCELED = "122";

    /**
     * 注文訂正・取消区分 全部取消完了
     * 123:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCELED = "123";

    /**
     * 注文訂正・取消区分 取消失敗
     * 124:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCEL_ERROR = "124";
    
    /**
     * 注文訂正・取消区分 訂正中
     * 125:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGING = "125";
    
    /**
     * 注文訂正・取消区分 一部訂正完了
     * 126:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_PARTIALLY_CHANGED = "126";
    
    /**
     * 注文訂正・取消区分 全部訂正完了
     * 127:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGED = "127";
    
    /**
     * 注文訂正・取消区分 訂正失敗
     * 128:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGE_ERROR = "128";
    
    /**
     * 注文訂正・取消区分 エラー
     * 129:処理状況区分
     */
    public final static String NOT_NAN_ALL_COMPLETE_ERROR = "129";    


    /**
     * ----------------------受付区分:受付済--------約定状態区分 失効----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 130:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_INITIAL_VALUE = "130";

    /**
     * 注文訂正・取消区分 取消中
     * 131:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CANCELING = "131";

    /**
     * 注文訂正・取消区分 一部取消完了
     * 132:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_PART_CANCELED = "132";

    /**
     * 注文訂正・取消区分 全部取消完了
     * 133:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CANCELED = "133";

    /**
     * 注文訂正・取消区分 取消失敗
     * 134:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CANCEL_ERROR = "134";
    
    /**
     * 注文訂正・取消区分 訂正中
     * 135:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CHANGING = "135";
    
    /**
     * 注文訂正・取消区分 一部訂正完了
     * 136:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_PARTIALLY_CHANGED = "136";
    
    /**
     * 注文訂正・取消区分 全部訂正完了
     * 137:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CHANGED = "137";
    
    /**
     * 注文訂正・取消区分 訂正失敗
     * 138:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_CHANGE_ERROR = "138";
    
    /**
     * 注文訂正・取消区分 エラー
     * 139:処理状況区分
     */
    public final static String NOT_NAN_LAPSE_ERROR = "139";    


    /**
     * ----------------------受付区分:受付済--------約定状態区分 一部失効----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 140:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_INITIAL_VALUE = "140";

    /**
     * 注文訂正・取消区分 取消中
     * 141:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCELING = "141";

    /**
     * 注文訂正・取消区分 一部取消完了
     * 142:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_PART_CANCELED = "142";

    /**
     * 注文訂正・取消区分 全部取消完了
     * 143:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCELED = "143";

    /**
     * 注文訂正・取消区分 取消失敗
     * 144:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCEL_ERROR = "144";
    
    /**
     * 注文訂正・取消区分 訂正中
     * 145:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGING = "145";
    
    /**
     * 注文訂正・取消区分 一部訂正完了
     * 146:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_PARTIALLY_CHANGED = "146";
    
    /**
     * 注文訂正・取消区分 全部訂正完了
     * 147:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGED = "147";
    
    /**
     * 注文訂正・取消区分 訂正失敗
     * 148:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGE_ERROR = "148";
    
    /**
     * 注文訂正・取消区分 エラー
     * 149:処理状況区分
     */
    public final static String NOT_NAN_ONE_LAPSE_ERROR = "149";    

    /**
     * ----------------------受付区分:受付済--------約定状態区分 約定取消----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 150:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_INITIAL_VALUE = "150";

    /**
     * 注文訂正・取消区分 取消中
     * 151:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCELING = "151";

    /**
     * 注文訂正・取消区分 一部取消完了
     * 152:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_PART_CANCELED = "152";

    /**
     * 注文訂正・取消区分 全部取消完了
     * 153:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCELED = "153";

    /**
     * 注文訂正・取消区分 取消失敗
     * 154:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCEL_ERROR = "154";
    
    /**
     * 注文訂正・取消区分 訂正中
     * 155:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGING = "155";
    
    /**
     * 注文訂正・取消区分 一部訂正完了
     * 156:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_PARTIALLY_CHANGED = "156";
    
    /**
     * 注文訂正・取消区分 全部訂正完了
     * 157:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGED = "157";
    
    /**
     * 注文訂正・取消区分 訂正失敗
     * 158:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGE_ERROR = "158";
    
    /**
     * 注文訂正・取消区分 エラー
     * 159:処理状況区分
     */
    public final static String NOT_NAN_PROMISE_CANCEL_ERROR = "159";    

    /**
     * ----------------------受付区分:受付エラー--------約定状態区分 約定取消----------------------------------------
     */
    
    /**
     * 注文訂正・取消区分 初期値
     * 200:処理状況区分
     */
    public final static String NOT_NAN_EXEC_TYPE_ERROR = "200";

    
}
@
