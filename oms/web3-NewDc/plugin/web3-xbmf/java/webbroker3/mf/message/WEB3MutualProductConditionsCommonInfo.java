head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsCommonInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄条件登録共通情報(WEB3MutualProductConditionsCommonInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成 
Revesion History : 2006/05/18 周捷 (中訊) 仕様変更・モデル414
Revesion History : 2006/10/19 周捷 (中訊) 仕様変更・モデル499、505
Revesion History : 2007/04/09 張騰宇 (中訊) モデル548
Revesion History : 2007/10/15 孫洪江 (中訊) 仕様変更・モデル579
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (投信銘柄条件登録共通情報)<BR>
 * 投信銘柄条件登録共通情報データクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsCommonInfo extends Message 
{
    /**
     * (投信銘柄コード)<BR>
     *  投信銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * (WEBBROKER3取扱状況)<BR>
     * <BR>
     * 0:WEBBROKER3で取り扱わない　@<BR>
     * 1:WEBBROKER3で取り扱う <BR>
     * 2:郵送請求のみ <BR>
     * (nullの場合、変更無しとする)<BR>
     */
    public String web3TreatmentFlag;
    
    /**
     * (銘柄名（和名）)<BR>
     *  銘柄名（和名）
     */
    public String jpnProductName;
    
    /**
     * (銘柄名（英名）)<BR>
     *  銘柄名（英名）
     */
    public String engProductName;
    
    /**
     * (投信銘柄カテゴリーコード)<BR>
     *  投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * (買付開始日)<BR>
     *  買付開始日
     */
    public Date buyStartDate;
    
    /**
     * (買付終了日)<BR>
     *  買付終了日
     */
    public Date buyEndDate;
    
    /**
     * (解約乗換開始日)<BR>
     *  解約乗換開始日
     */
    public Date sellSwitchingStartDate;
    
    /**
     * (解約乗換終了日)<BR>
     *  解約乗換終了日
     */
    public Date sellSwitchingEndDate;
    
    /**
     * (買取請求開始日)<BR>
     *  買取請求開始日
     */
    public Date buyClaimStartDate;
    
    /**
     * (買取請求終了日)<BR>
     *  買取請求終了日
     */
    public Date buyClaimEndDate;
    
    /**
     * (募集開始日)<BR>
     *  募集開始日
     */
    public Date applyAbleStartDate;
    
    /**
     * (募集終了日)<BR>
     *  募集終了日
     */
    public Date applyAbleEndDate;
    
    /**
     * (指定方法@（買付）)<BR>
     *  指定方法@（買付）
     */
    public String buySelectable;
    
    /**
     * (最低口数（新規買付）)<BR>
     *  最低口数（新規買付）
     */
    public String newBuyMinQty;
    
    /**
     * (単位口数（新規買付）)<BR>
     *  単位口数（新規買付）
     */
    public String newBuyUnitQty;
    
    /**
     * (最低金額（新規買付）)<BR>
     *  最低金額（新規買付）
     */
    public String newBuyMinAmt;
    
    /**
     * (単位金額（新規買付）)<BR>
     *  単位金額（新規買付）
     */
    public String newBuyUnitAmt;
    
    /**
     * (最低口数（追加買付）)<BR>
     *  最低口数（追加買付）
     */
    public String addBuyMinQty;
    
    /**
     * (単位口数（追加買付）)<BR>
     *  単位口数（追加買付）
     */
    public String addBuyUnitQty;
    
    /**
     * (最低金額（追加買付）)<BR>
     *  最低金額（追加買付）
     */
    public String addBuyMinAmt;
    
    /**
     * (単位金額（追加買付）)<BR>
     *  単位金額（追加買付）
     */
    public String addBuyUnitAmt;
    
    /**
     * (指定方法@（解約）)<BR>
     *  指定方法@（解約）
     */
    public String sellSelectable;
    
    /**
     * (最低口数（解約）)<BR>
     *  最低口数（解約）
     */
    public String sellMinQty;
    
    /**
     * (単位口数（解約）)<BR>
     *  単位口数（解約）
     */
    public String sellUnitQty;
    
    /**
     * (最低金額（解約）)<BR>
     *  最低金額（解約）
     */
    public String sellMinAmt;
    
    /**
     * (単位金額（解約）)<BR>
     *  単位金額（解約）
     */
    public String sellUnitAmt;
    
    /**
     * (指定方法@（乗換）)<BR>
     *  指定方法@（乗換）
     */
    public String switchingSelectable;
    
    /**
     * (最低口数（乗換）)<BR>
     *  最低口数（乗換）
     */
    public String switchingMinQty;
    
    /**
     * (単位口数（乗換）)<BR>
     *  単位口数（乗換）
     */
    public String switchingUnitQty;
    
    /**
     * (最低金額（乗換）)<BR>
     *  最低金額（乗換）
     */
    public String switchingMinAmt;
    
    /**
     * (単位金額（乗換）)<BR>
     *  単位金額（乗換）
     */
    public String switchingUnitAmt;
    
    /**
     * (指定方法@（募集）)<BR>
     *  指定方法@（募集）
     */
    public String applySelectable;
    
    /**
     * (最低口数（募集）)<BR>
     *  最低口数（募集）
     */
    public String applyMinQty;
    
    /**
     * (単位口数（募集）)<BR>
     *  単位口数（募集）
     */
    public String applyUnitQty;
    
    /**
     * (最低金額（募集）)<BR>
     *  最低金額（募集）
     */
    public String applyMinAmt;
    
    /**
     * (単位金額（募集）)<BR>
     *  単位金額（募集）
     */
    public String applyUnitAmt;
    
    /**
     * (買付可能区分（当日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String todayBuyPossDiv;
    
    /**
     * (解約可能区分（当日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String todaySellPossDiv;
    
    /**
     * (乗換可能区分（当日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String todaySwitchingPossDiv;
    
    /**
     * (募集可能区分（当日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String todayApplyPossDiv;
    
    /**
     * (買付可能区分（翌日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String nextDayBuyPossDiv;
    
    /**
     * (解約可能区分（翌日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String nextDaySellPossDiv;
    
    /**
     * (乗換可能区分（翌日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String nextDaySwitchingPossDiv;
    
    /**
     * (募集可能区分（翌日発注分）)<BR>
     * 0：不可　@1：可
     */
    public String nextDayApplyPossDiv;
    
    /**
     * (受付締切開始時間（平日）)<BR>
     * 書式：hhmm
     */
    public String orderCloseStartTimeFull;
    
    /**
     * (受付締切終了時間（平日）)<BR>
     * 書式：hhmm
     */
    public String orderCloseEndTimeFull;
    
    /**
     * (受付締切開始時間（半日）)<BR>
     * 書式：hhmm
     */
    public String orderCloseStartTimeHalf;
    
    /**
     * (受付締切終了時間（半日）)<BR>
     * 書式：hhmm
     */
    public String orderCloseEndTimeHalf;
    
    /**
     * (買付制限区分)<BR>
     * 0：買付可　@1：乗換買付のみ可
     */
    public String buyRestrictionDiv;
    
    /**
     * (オペレーション日付)<BR>
     * 入力画面取得時の現在日付
     */
    public Date operationDate;
    
    /**
     * (受渡方法@)<BR>
     * 0：選択指定 <BR>
     * 1：銀行振込 <BR>
     * 2：証券口座入金
     */
    public String deliveryVariation;
    
    /**
     * (特定日取引銘柄区分)<BR>
     * 特定日取引銘柄区分 <BR>
     * <BR>
     * 0 ：通常銘柄<BR>　@ 
     * 1 ：買付のみ <BR>
     * 2 ：解約のみ <BR>
     * 3 ：両方 
     */
    public String unitTypeProductDiv;

    /**
     * (外貨最低金額（新規買付）)<BR>
     * 外貨最低金額（新規買付）<BR>
     */
    public String frgnMinAmtBuy;

    /**
     * (外貨単位金額（新規買付）)<BR>
     * 外貨単位金額（新規買付）<BR>
     */
    public String frgnUnitAmtBuy;

    /**
     * (外貨最低金額（追加買付）)<BR>
     * 外貨最低金額（追加買付）<BR>
     */
    public String frgnMinAmtAdd;

    /**
     * (外貨単位金額（追加買付）)<BR>
     * 外貨単位金額（追加買付）<BR>
     */
    public String frgnUnitAmtAdd;

    /**
     * (外貨最低金額（解約）)<BR>
     * 外貨最低金額（解約）<BR>
     */
    public String frgnMinAmtSell;

    /**
     * (外貨単位金額（解約）)<BR>
     * 外貨単位金額（解約）<BR>
     */
    public String frgnUnitAmtSell;

    /**
     * (募集手数料区分)<BR>
     * 募集手数料区分<BR>
     * <BR>
     * "0：なし"<BR>
     * "1：内枠"<BR>
     * "2：外枠"<BR>
     */
    public String applyCommissionDiv;

    /**
     * (投信銘柄条件登録共通情報)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 417747E00061
     */
    public WEB3MutualProductConditionsCommonInfo()
    {
    }
}
@
