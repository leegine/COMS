head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwitchingProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約乗換銘柄一覧行データクラス(WEB3MutualSellSwitchingProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
Revesion History : 2004/08/25 周勇 (中訊) レビュー  
Revesion History : 2004/12/11 于美麗 (中訊) 残対応
Revesion History : 2005/10/20 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/05/15 李小健 (中訊) 仕様変更・モデル411
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
Revesion History : 2007/04/09 唐性峰 (中訊) 仕様変更・モデル562
Revesion History : 2008/04/21 王志葵 (中訊) 仕様変更・モデル593
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 投資信託解約乗換銘柄一覧行データクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellSwitchingProductGroup extends Message 
{
   
    /**
     * 投信資産ID<BR>
     * <BR>
     * （画面では非表示）<BR>
     */
    public String id;
    
    /**
     * 銘柄名
     * （画面では非表示）
     */
    public String mutualProductName;
    
	/**
	 * 銘柄ID
	 */
	public String mutualProductId;
    
    /**
     * 銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 0:一般　@1:特定　@2:その他<BR>
     */
    public String taxType;
    
    /**
     * 解約可能口数
     */
    public String sellableQty;
    
    /**
     * 解約中概算口数
     */
    public String sellingEstimatedQty;
    
    /**
     * 参考基準価額通貨コード<BR>
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
     * 参考基準価額
     */
    public String constantValue;
    
    /**
     * 基準価額適用日
     */
    public Date constantValueAppDate;
    
    /**
     * 個別元本<BR>
     * (取得価額)<BR>
     */
    public String indivPrincipal;
    
    /**
     * 評価額
     */
    public String marketValue;
    
    /**
     * 評価損益
     */
    public String appraisalProfitLoss;
    
    /**
     * 解約時単位口数
     */
    public String sellUnitQty;
    
    /**
     * 解約時最低口数
     */
    public String sellMinQty;
    
    /**
     * 解約時単位金額
     */
    public String sellUnitAmt;
    
    /**
     * 解約時最低金額
     */
    public String sellMinAmt;
    
    /**
     * 乗換時単位口数
     */
    public String switchingUnitQty;
    
    /**
     * 乗換時最低口数
     */
    public String switchingMinQty;
    
    /**
     * 乗換時単位金額
     */
    public String switchingUnitAmt;
    
    /**
     * 乗換時最低金額
     */
    public String switchingMinAmt;
    
    /**
     * 注文受付締切時間<BR>
     * <BR>
     * (MM:HH)<BR>
     */
    public String orderCloseTime;
    
    /**
     * (解約可能区分)<BR>
     * 解約可能フラグ<BR>
     * <BR>
     * null:取引可能<BR>
     * 1:全部解約中<BR>
     * 2:取扱不可<BR>
     * 3:取引不可<BR>
     * 4:緊急停止中<BR>
     * 5:取引時間外注文停止中<BR>
     */
    public String sellPossType;
    
    /**
     * (買取可能区分)<BR>
     * 買取可能フラグ<BR>
     * <BR>
     * null:取引可能<BR>
     * 1:全部解約中<BR>
     * 2:取扱不可<BR>
     * 3:取引不可<BR>
     * 4:緊急停止中<BR>
     * 5:取引時間外注文停止中<BR>
     */
    public String buyPossType;
    
    /**
     * (乗換可能区分)<BR>
     * 乗換可能フラグ<BR>
     * <BR>
     * null:取引可能<BR>
     * 1:全部解約中<BR>
     * 2:取扱不可<BR>
     * 3:取引不可<BR>
     * 4:緊急停止中<BR>
     * 5:取引時間外注文停止中<BR>
     */
    public String switchingPossType;
    
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
     * (参考レート確定日)<BR>
     * 参考レート確定日<BR>
     * <BR>
     * 円貨の場合null<BR>
     */
    public Date referenceRateFixedDay;

    /**
     * (未収分配金)<BR>
     * 未収分配金
     */
    public String unreceivedDistribution;

    /**
     * (外貨MMFフラグ)<BR>
     * 外貨MMFフラグ<BR>
     * <BR>
     * true:銘柄が外貨MMF <BR>
     * false:銘柄が外貨MMFでない<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (解約時外貨単位金額)<BR>
     * 解約時外貨単位金額<BR>
     */
    public String sellFrgnUnitAmt;

    /**
     * (解約時外貨最低金額)<BR>
     * 解約時外貨最低金額<BR>
     */
    public String sellFrgnMinAmt;

    /**
     * (指定方法@（解約）)<BR>
     * 指定方法@（解約）<BR>
     * <BR>
     * 0：選択指定<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     */
    public String sellSelectable;

    /**
     * (指定方法@（乗換）)<BR>
     * 指定方法@（乗換）<BR>
     * <BR>
     * 0：選択指定<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     */
    public String switchingSelectable;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A88AA2019E
     */
    public WEB3MutualSellSwitchingProductGroup() 
    {
     
    }
}
@
