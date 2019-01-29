head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付銘柄一覧行データクラス(WEB3MutualBuyProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2006/05/15 李小健 仕様変更・モデル411
Revesion History : 2006/09/11 周捷 仕様変更・モデル491
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535
Revesion History : 2007/04/09 唐性峰 (中訊) 仕様変更・モデル562
Revesion History : 2008/04/21 王志葵 (中訊) 仕様変更・モデル593
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 投資信託買付銘柄一覧行データクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyProductGroup extends Message
{
    /**
     * 投信銘柄ID<BR>
     * <BR>
     * （画面では非表示）<BR>
     */
    public String id;
    
    /**
     * 銘柄コード
     * （画面では非表示） <BR>
     */
    public String mutualProductCode;
    
    /**
     * 銘柄名
     */
    public String mutualProductName;
    
    /**
     * 投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * 買付基準価額通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * 買付基準価額
     * ※募集期間中の銘柄の場合、募集価格 <BR>
     */
    public String constantValue;
    
    /**
     * 基準価額適用日
     * ※募集期間中の銘柄の場合、null <BR>
     */
    public Date constantValueAppDate;
    
    /**
     * 新規買付時単位口数
     * ※募集期間中の銘柄の場合、募集時単位口数 <BR>
     */
    public String newBuyUnitQty;
    
    /**
     * 新規買付時最低口数
     * ※募集期間中の銘柄の場合、募集時最低口数 <BR>
     */
    public String newBuyMinQty;
    
    /**
     * 新規買付時単位金額
     * ※募集期間中の銘柄の場合、募集時単位金額 <BR>
     */
    public String newBuyUnitAmt;
    
    /**
     * 新規買付時最低金額
     * ※募集期間中の銘柄の場合、募集時最低金額 <BR>
     */
    public String newBuyMinAmt;
    
    /**
     * 追加買付時単位口数
     * ※募集期間中の銘柄の場合、null <BR>
     */
    public String addBuyUnitQty;
    
    /**
     * 追加買付時最低口数
     * ※募集期間中の銘柄の場合、null <BR>
     */
    public String addBuyMinQty;
    
    /**
     * 追加買付時単位金額
     * ※募集期間中の銘柄の場合、null <BR>
     */
    public String addBuyUnitAmt;
    
    /**
     * 追加買付時最低金額
     * ※募集期間中の銘柄の場合、null <BR>
     */
    public String addBuyMinAmt;
    
    /**
     * 注文受付締切時間<BR>
     * <BR>
     * (HH:MM)<BR>
     */
    public String orderCloseTime;
    
    /**
     * 備考区分<BR>
     * <BR>
     * null:無し<BR>
     * (1:全部解約中)<BR>
     * (2:取扱不可)<BR>
     * 3:取引不可(買付停止中)<BR>
     * 4:緊急停止中<BR>
     * 5:取引時間外注文停止中(受付時間外)<BR>
     * 6:募集期間中 <BR>
     * <BR>
     */
    public String noteType;
    
    /**
     * (円転基準価額)<BR>
     * 円転基準価額<BR>
     * <BR>
     * 買付基準価額を円転したもの<BR>
     */
    public String yenConstantValue;
    
    /**
     * (参考レート)<BR>
     * 参考レート<BR>
     * <BR>
     * 為替レートテーブル.TTS<BR>
     * 円貨の場合null<BR>
     */
    public String referenceRate;
    
    /**
     * (募集開始日)<BR>
     * 募集開始日<BR> 
     * <BR>
     * 証券会社によって募集開始日として、募集開始日または<BR>
     * 募集開始日（SONAR）のいずれかを表示する。<BR>
     */
    public Date applyAbleStartDate;
    
    /**
     * (募集終了日)<BR>
     * 募集終了日<BR>
     * <BR>
     * 証券会社によって募集終了日として、募集終了日または<BR>
     * 募集終了日（SONAR）のいずれかを表示する。<BR>
     */
    public Date applyAbleEndDate;
    
    /**
     * (募集開始日（SONAR）)<BR>
     * 募集開始日（SONAR）<BR>
     * <BR>
     * 証券会社によって募集開始日として、募集開始日または<BR>
     * 募集開始日（SONAR）のいずれかを表示する。<BR>
     */
    public Date applyAbleStartDateSonar;
    
    /**
     * (募集終了日（SONAR）)<BR>
     * 募集終了日（SONAR）<BR>
     * <BR>
     * 証券会社によって募集終了日として、募集終了日または<BR>
     * 募集終了日（SONAR）のいずれかを表示する。<BR>
     */
    public Date applyAbleEndDateSonar;

    /**
     * (参考レート確定日)<BR>
     * 参考レート確定日<BR>
     */
    public Date referenceRateFixedDay;

    /**
     * (外貨MMFフラグ)<BR>
     * 外貨MMFフラグ <BR>
     * <BR>
     * true:銘柄が外貨MMF <BR>
     * false:銘柄が外貨MMFでない<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (新規買付時外貨単位金額)<BR>
     * 新規買付時外貨単位金額<BR>
     */
    public String newBuyFrgnUnitAmt;

    /**
     * (新規買付時外貨最低金額)<BR>
     * 新規買付時外貨最低金額<BR>
     */
    public String newBuyFrgnMinAmt;

    /**
     * (追加買付時外貨単位金額)<BR>
     * 追加買付時外貨単位金額<BR>
     */
    public String addBuyFrgnUnitAmt;

    /**
     * (追加買付時外貨最低金額)<BR>
     * 追加買付時外貨最低金額<BR>
     */
    public String addBuyFrgnMinAmt;

    /**
     * (指定方法@)<BR>
     * 指定方法@<BR>
     * <BR>
     * 0：選択指定<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     */
    public String buySelectable;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A87B000027
     */
    public WEB3MutualBuyProductGroup()
    {
    }
}
@
