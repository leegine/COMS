head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundProductCondSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄条件内容データクラス(WEB3MutualFundProductCondSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 于美麗 (中訊) 新規作成
Revesion History : 2004/08/23 王蘭芬 (中訊) レビュー
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2005/10/18 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2007/04/09 張騰宇 (中訊)　@モデル547
*/

package webbroker3.mf;

import java.util.Date;

/**
 * 投信銘柄条件内容　@データクラス<BR>
 * <BR>
 * [投資信託管理者　@銘柄条件登録サービス]にて、<BR>
 * 管理者が入力した条件を格納する。<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductCondSpec
{

    /**
     * 投信銘柄コード
     */
    private String mutualProductCode;
    
    /**
     * 買付開始日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date buyStartDate;

    /**
     * 買付終了日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date buyEndDate;

    /**
     * 解約乗換開始日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date sellSwitchingStartDate;

    /**
     * 解約乗換終了日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date sellSwitchingEndDate;

    /**
     * 買取請求開始日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date buyClaimStartDate;

    /**
     * 買取請求終了日<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private Date buyClaimEndDate;
    
    /**
     * 募集開始日<BR>
     */
    private Date applyAbleStartDate;
    
    /**
     * 募集終了日<BR>
     */
    private Date applyAbleEndDate;

    /**
     * 指定方法@（買付）<BR>
     * <BR>
     * 0:選択指定　@3:金額　@4:口数<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String buySelectable;

    /**
     * 最低口数（新規買付）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String newBuyMinQty;

    /**
     * 単位口数（新規買付）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String newBuyUnitQty;

    /**
     * (最低金額（新規買付）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String newBuyMinAmt;

    /**
     * (単位金額（新規買付）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String newBuyUnitAmt;

    /**
     * 最低口数（追加買付）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String addBuyMinQty;

    /**
     * 単位口数（追加買付）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String addBuyUnitQty;

    /**
     * (最低金額（追加買付）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String addBuyMinAmt;

    /**
     * (単位金額（追加買付）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String addBuyUnitAmt;

    /**
     * 指定方法@（解約）<BR>
     * <BR>
     * 0:選択指定　@3:金額　@4:口数<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String sellSelectable;

    /**
     * 最低口数（解約）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String sellMinQty;

    /**
     * 単位口数（解約）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String sellUnitQty;

    /**
     * (最低金額（解約）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String sellMinAmt;

    /**
     * (単位金額（解約）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String sellUnitAmt;

    /**
     * 指定方法@（乗換）<BR>
     * <BR>
     * 0:選択指定　@3:金額　@4:口数<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String switchingSelectable;

    /**
     * 最低口数（乗換）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String switchingMinQty;

    /**
     * 単位口数（乗換）<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String switchingUnitQty;

    /**
     * (最低金額（乗換）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String switchingMinAmt;

    /**
     * (単位金額（乗換）)<BR>
     * (nullの場合、変更無しとする)<BR>
     */
    private String switchingUnitAmt;
    
    /**
     * (指定方法@（募集）)<BR>
     * 指定方法@（募集） <BR>
     * <BR>
     * 0:選択指定　@3:金額　@4:口数 <BR>
     * (nullの場合、変更無しとする) <BR>
     */
    private String applySelectable;
    
    /**
     * (最低口数（募集）)<BR>
     * 最低口数（募集） <BR>
     * (nullの場合、変更無しとする) <BR>
     */
    private String applyMinQty;
    
    /**
     * (単位口数（募集）)<BR>
     * 単位口数（募集） <BR>
     * (nullの場合、変更無しとする) <BR>
     */
    private String applyUnitQty;
    
    /**
     * (最低金額（募集）)<BR>
     * 最低金額（募集） <BR>
     * (nullの場合、変更無しとする) <BR>
     */
    private String applyMinAmt;
    
    /**
     * (単位金額（募集）)<BR>
     * 単位金額（募集） <BR>
     * (nullの場合、変更無しとする) <BR>
     */
    private String applyUnitAmt;
    
    /**
     * (買付可能区分（当日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String buyPossibleDivTheDay;
    
    /**
     * (解約可能区分（当日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String sellPossibleDivTheDay;
    
    /**
     * (乗換可能区分（当日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String swtPossibleDivTheDay;
    
    /**
     * (募集可能区分（当日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String applyPossDivTheDay;
    
    /**
     * (買付可能区分（翌日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String buyPossibleDivTheNextDay;
    
    /**
     * (解約可能区分（翌日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String sellPossibleDivTheNextDay;
    
    /**
     * (乗換可能区分（翌日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String swtPossibleDivTheNextDay;
    
    /**
     * (募集可能区分（翌日発注分）)<BR>
     *  0：不可　@<BR>
     *  1：可 <BR>
     */
    private String applyPossDivTheNextDay;
    
    /**
     * (受付締切開始時間（平日）)<BR>
     * 書式：hhmm<BR>
     */
    private String orderCloseStartTimeFull;
    
    /**
     * (受付締切終了時間（平日）)<BR>
     * 書式：hhmm<BR>
     */
    private String orderCloseEndTimeFull;
    
    /**
     * (受付締切開始時間（半日）)<BR>
     * 書式：hhmm<BR>
     */
    private String orderCloseStartTimeHalf;
    
    /**
     * (受付締切終了時間（半日）)<BR>
     * 書式：hhmm<BR>
     */
    private String orderCloseEndTimeHalf;

    /**
     * (外貨最低金額（新規買付）)<BR>
     * 外貨最低金額（新規買付）<BR>
     */
    private String buyFrgnMinAmtBuy;

    /**
     * (外貨単位金額（新規買付）)<BR>
     * 外貨単位金額（新規買付）<BR>
     */
    private String buyFrgnUnitAmtBuy;

    /**
     * (外貨最低金額（追加買付）)<BR>
     * 外貨最低金額（追加買付）<BR>
     */
    private String buyFrgnMinAmtAdd;

    /**
     * (外貨単位金額（追加買付）)<BR>
     * 外貨単位金額（追加買付）<BR>
     */
    private String buyFrgnUnitAmtAdd;

    /**
     * (外貨最低金額（解約）)<BR>
     * 外貨最低金額（解約）
     */
    private String sellFrgnMinAmtSell;

    /**
     * (外貨単位金額（解約）)<BR>
     * 外貨単位金額（解約）<BR>
     */
    private String sellFrgnUnitAmtSell;

    /**
     * (投信銘柄条件内容)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40E9338C000E
     */
    public WEB3MutualFundProductCondSpec()
    {

    }

    /**
     * (get銘柄コード)<BR>
     * this.銘柄コードを返却する。<BR>
     * @@return String
     * @@roseuid 40E92AC003C7
     */
    public String getMutualProductCode()
    {
        return this.mutualProductCode;
    }

    /**
     * (set銘柄コード)<BR>
     * 銘柄コードを設定する。<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@roseuid 40E92AC601F2
     */
    public void setMutualProductCode(String l_strProductCode)
    {
        this.mutualProductCode = l_strProductCode;
    }

    /**
     * (get買付開始日)<BR>
     * this.買付開始日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getBuyStartDate()
    {
        return this.buyStartDate;
    }

    /**
     * (set買付開始日)<BR>
     * 買付開始日を設定する。<BR>
     * @@param l_datBuyStartDate - 買付開始日
     * @@roseuid 40E92AD50146
     */
    public void setBuyStartDate(Date l_datBuyStartDate)
    {
        this.buyStartDate = l_datBuyStartDate;
    }

    /**
     * (get買付終了日)<BR>
     * this.買付終了日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92AE700F8
     */
    public Date getBuyEndDate()
    {
        return this.buyEndDate;
    }

    /**
     * (set買付終了日)<BR>
     * 買付終了日を設定する。<BR>
     * @@param l_acquiredEndDate - 買付終了日
     * @@roseuid 40E92AE700F9
     */
    public void setBuyEndDate(Date l_acquiredEndDate)
    {
        this.buyEndDate = l_acquiredEndDate;
    }

    /**
     * (get解約乗換開始日)<BR>
     * this.解約乗換開始日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92AE700FA
     */
    public Date getSellSwitchingStartDate()
    {
        return this.sellSwitchingStartDate;
    }

    /**
     * (set解約乗換開始日)<BR>
     * 解約乗換開始日を設定する。<BR>
     * @@param l_sellSwitchingStartDate - 解約乗換開始日
     * @@roseuid 40E92AE700FB
     */
    public void setSellSwitchingStartDate(Date l_sellSwitchingStartDate)
    {
        this.sellSwitchingStartDate = l_sellSwitchingStartDate;
    }

    /**
     * (get解約乗換終了日)<BR>
     * this.解約乗換終了日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E939830211
     */
    public Date getSellSwitchingEndDate()
    {
        return this.sellSwitchingEndDate;
    }

    /**
     * (set解約乗換終了日)<BR>
     * 解約乗換終了日を設定する。<BR>
     * @@param l_sellSwitchingEndDate - 解約乗換終了日
     * @@roseuid 40E9398A0127
     */
    public void setSellSwitchingEndDate(Date l_sellSwitchingEndDate)
    {
        this.sellSwitchingEndDate = l_sellSwitchingEndDate;
    }

    /**
     * (get買取請求開始日)<BR>
     * this.買取請求開始日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92AE802AD
     */
    public Date getBuyClaimStartDate()
    {
        return this.buyClaimStartDate;
    }

    /**
     * (set買取請求開始日)<BR>
     * 買取請求開始日を設定する。<BR>
     * @@param l_buyRequestStartDate - 買取請求開始日
     * @@roseuid 40E92AE802AE
     */
    public void setBuyClaimStartDate(Date l_buyRequestStartDate)
    {
        this.buyClaimStartDate = l_buyRequestStartDate;
    }

    /**
     * (get買取請求終了日)<BR>
     * this.買取請求終了日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E939C40202
     */
    public Date getBuyClaimEndDate()
    {
        return this.buyClaimEndDate;
    }

    /**
     * (set買取請求終了日)<BR>
     * 買取請求終了日を設定する。<BR>
     * @@param l_datBuyClaimEndDate - 買取請求終了日
     * @@roseuid 40E939C702AD
     */
    public void setBuyClaimEndDate(Date l_datBuyClaimEndDate)
    {
        this.buyClaimEndDate = l_datBuyClaimEndDate;
    }

    /**
     * (get指定方法@（買付）)<BR>
     * this.指定方法@（買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92AE802AF
     */
    public String getBuySelectable()
    {
        return this.buySelectable;
    }

    /**
     * (set指定方法@（買付）)<BR>
     * 指定方法@（買付）を設定する。<BR>
     * @@param l_strDesignateMethodAcquired - 指定方法@（買付）
     * @@roseuid 40E92AE802B0
     */
    public void setBuySelectable(String l_strDesignateMethodAcquired)
    {
        this.buySelectable = l_strDesignateMethodAcquired;
    }

    /**
     * (get最低口数（新規買付）)<BR>
     * this.最低口数（新規買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92AE902CD
     */
    public String getNewBuyMinQty()
    {
        return this.newBuyMinQty;
    }

    /**
     * (set最低口数（新規買付）)<BR>
     * 最低口数（新規買付）を設定する。<BR>
     * @@param l_strMinQuantityOpenAcquired - 最低口数（新規買付）
     * @@roseuid 40E92AE902CE
     */
    public void setNewBuyMinQty(String l_strMinQuantityOpenAcquired)
    {
        this.newBuyMinQty = l_strMinQuantityOpenAcquired;
    }

    /**
     * (get単位口数（新規買付）)<BR>
     * this.単位口数（新規買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92AE902CF
     */
    public String getNewBuyUnitQty()
    {
        return this.newBuyUnitQty;
    }

    /**
     * (set単位口数（新規買付）)<BR>
     * 単位口数（新規買付）を設定する。<BR>
     * @@param l_strUnitQuantityOpenAcquired - 単位口数（新規買付）
     * @@roseuid 40E92AE902D0
     */
    public void setNewBuyUnitQty(String l_strUnitQuantityOpenAcquired)
    {
        this.newBuyUnitQty = l_strUnitQuantityOpenAcquired;
    }

    /**
     * (get単位口数（追加買付）)<BR>
     * this.単位口数（追加買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92AEB03A7
     */
    public String getAddBuyUnitQty()
    {
        return this.addBuyUnitQty;
    }

    /**
     * (get最低金額（新規買付）)<BR>
     * this.最低金額（新規買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FE950051
     */
    public String getNewBuyMinAmt()
    {
        return this.newBuyMinAmt;
    }

    /**
     * (set最低金額（新規買付）)<BR>
     * 最低金額（新規買付）を設定する。<BR>
     * @@param l_strMinPriceOpenAcquired - 最低金額（新規買付）
     * @@roseuid 40E9FE950060
     */
    public void setNewBuyMinAmt(String l_strMinPriceOpenAcquired)
    {
        this.newBuyMinAmt = l_strMinPriceOpenAcquired;
    }

    /**
     * (get単位金額（新規買付）)<BR>
     * this.単位金額（新規買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FE950062
     */
    public String getNewBuyUnitAmt()
    {
        return this.newBuyUnitAmt;
    }

    /**
     * (set単位金額（新規買付）)<BR>
     * 単位金額（新規買付）を設定する。<BR>
     * @@param l_strUnitPriceOpenAcquired - 単位金額（新規買付）
     * @@roseuid 40E9FE950070
     */
    public void setNewBuyUnitAmt(String l_strUnitPriceOpenAcquired)
    {
        this.newBuyUnitAmt = l_strUnitPriceOpenAcquired;
    }

    /**
     * (set単位口数（追加買付）)<BR>
     * 単位口数（追加買付）を設定する。<BR>
     * @@param l_strUnitQuantityAddingAcquired - 単位口数（追加買付）
     * @@roseuid 40E92AEB03B9
     */
    public void setAddBuyUnitQty(String l_strUnitQuantityAddingAcquired)
    {
        this.addBuyUnitQty = l_strUnitQuantityAddingAcquired;
    }

    /**
     * (get最低口数（追加買付）)<BR>
     * this.最低口数（追加買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92AEB03B8
     */
    public String getAddBuyMinQty()
    {
        return this.addBuyMinQty;
    }

    /**
     * (set最低口数（追加買付）)<BR>
     * 最低口数（追加買付）を設定する。<BR>
     * @@param l_strMinQuantityAddingAcquired - 最低口数（追加買付）
     * @@roseuid 40E92AEB03B7
     */
    public void setAddBuyMinQty(String l_strMinQuantityAddingAcquired)
    {
        this.addBuyMinQty = l_strMinQuantityAddingAcquired;
    }

    /**
     * (get最低金額（追加買付）)<BR>
     * this.最低金額（追加買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FEF20080
     */
    public String getAddBuyMinAmt()
    {
        return this.addBuyMinAmt;
    }

    /**
     * (set最低金額（追加買付）)<BR>
     * 最低金額（追加買付）を設定する。<BR>
     * @@param l_strMinPriceAddingAcquired - 最低金額（追加買付）
     * @@roseuid 40E9FEF2008F
     */
    public void setAddBuyMinAmt(String l_strMinPriceAddingAcquired)
    {
        this.addBuyMinAmt = l_strMinPriceAddingAcquired;
    }

    /**
     * (get単位金額（追加買付）)<BR>
     * this.単位金額（追加買付）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FEF20091
     */
    public String getAddBuyUnitAmt()
    {
        return this.addBuyUnitAmt;
    }

    /**
     * (set単位金額（追加買付）)<BR>
     * 単位金額（追加買付）を設定する。<BR>
     * @@param l_strUnitPriceAddingAcquired - 単位金額（追加買付）
     * @@roseuid 40E9FEF2009F
     */
    public void setAddBuyUnitAmt(String l_strUnitPriceAddingAcquired)
    {
        this.addBuyUnitAmt = l_strUnitPriceAddingAcquired;
    }

    /**
     * (get指定方法@（解約）)<BR>
     * this.指定方法@（解約）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BA3033A
     */
    public String getSellSelectable()
    {
        return this.sellSelectable;
    }

    /**
     * (set指定方法@（解約）)<BR>
     * 指定方法@（解約）を設定する。<BR>
     * @@param l_strDesignateMethodSell - 指定方法@（解約）
     * @@roseuid 40E92BA3033B
     */
    public void setSellSelectable(String l_strDesignateMethodSell)
    {
        this.sellSelectable = l_strDesignateMethodSell;
    }

    /**
     * (get最低口数（解約）)<BR>
     * this.最低口数（解約）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BAC02BD
     */
    public String getSellMinQty()
    {
        return this.sellMinQty;
    }

    /**
     * (set最低口数（解約）)<BR>
     * 最低口数（解約）を設定する。<BR>
     * @@param l_strMinQuantitySell - 最低口数（解約）
     * @@roseuid 40E92BAC02CD
     */
    public void setSellMinQty(String l_strMinQuantitySell)
    {
        this.sellMinQty = l_strMinQuantitySell;
    }

    /**
     * (get単位口数（解約）)<BR>
     * this.最低口数（解約）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BAC02CE
     */
    public String getSellUnitQty()
    {
        return this.sellUnitQty;
    }

    /**
     * (set単位口数（解約）)<BR>
     * 単位口数（解約）を設定する。<BR>
     * @@param l_strUnitQuantitySell - 単位口数（解約）
     * @@roseuid 40E92BAC02CF
     */
    public void setSellUnitQty(String l_strUnitQuantitySell)
    {
        this.sellUnitQty = l_strUnitQuantitySell;
    }

    /**
     * (get最低金額（解約）)<BR>
     * this.最低金額（解約）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FF32010C
     */
    public String getSellMinAmt()
    {
        return this.sellMinAmt;
    }

    /**
     * (set最低金額（解約)<BR>
     * 最低金額（解約）を設定する。<BR>
     * @@param l_strMinPriceSell - 最低金額（解約）
     * @@roseuid 40E9FF32010D
     */
    public void setSellMinAmt(String l_strMinPriceSell)
    {
        this.sellMinAmt = l_strMinPriceSell;
    }

    /**
     * (get単位金額（解約）)<BR>
     * this.単位金額（解約）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FF32011C
     */
    public String getSellUnitAmt()
    {
        return this.sellUnitAmt;
    }

    /**
     * (set単位金額（解約）)<BR>
     * 単位金額（解約）を設定する。<BR>
     * @@param l_strUnitPriceSell - 単位金額（解約）
     * @@roseuid 40E9FF32012C
     */
    public void setSellUnitAmt(String l_strUnitPriceSell)
    {
        this.sellUnitAmt = l_strUnitPriceSell;
    }

    /**
     * (get指定方法@（乗換）)<BR>
     * this.指定方法@（乗換）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BB00211
     */
    public String getSwitchingSelectable()
    {
        return this.switchingSelectable;
    }

    /**
     * (set指定方法@（乗換）)<BR>
     * 指定方法@（乗換）を設定する。<BR>
     * @@param l_strDesignateMethodSwitching - 指定方法@（乗換）
     * @@roseuid 40E92BB00212
     */
    public void setSwitchingSelectable(String l_strDesignateMethodSwitching)
    {
        this.switchingSelectable = l_strDesignateMethodSwitching;
    }

    /**
     * (get最低口数（乗換）)<BR>
     * this.最低口数（乗換）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BB00213
     */
    public String getSwitchingMinQty()
    {
        return this.switchingMinQty;
    }

    /**
     * (set最低口数（乗換）)<BR>
     * 最低口数（乗換）を設定する。<BR>
     * @@param l_strMinQuantitySwitching - 最低口数（乗換）
     * @@roseuid 40E92BB00214
     */
    public void setSwitchingMinQty(String l_strMinQuantitySwitching)
    {
        this.switchingMinQty = l_strMinQuantitySwitching;
    }

    /**
     * (get単位口数（乗換）)<BR>
     * this.単位口数（乗換）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92BB00215
     */
    public String getSwitchingUnitQty()
    {
        return this.switchingUnitQty;
    }

    /**
     * (set単位口数（乗換）)<BR>
     * 単位口数（乗換）を設定する。<BR>
     * @@param l_strUnitQuantitySwitching - 単位口数（乗換）
     * @@roseuid 40E92BB00221
     */
    public void setSwitchingUnitQty(String l_strUnitQuantitySwitching)
    {
        this.switchingUnitQty = l_strUnitQuantitySwitching;
    }

    /**
     * (get最低金額（乗換）)<BR>
     * this.最低金額（乗換）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB0189
     */
    public String getSwitchingMinAmt()
    {
        return this.switchingMinAmt;
    }

    /**
     * (set最低金額（乗換）)<BR>
     * 最低金額（乗換）を設定する。<BR>
     * @@param l_strMinPriceSwitching - 最低金額（乗換）
     * @@roseuid 40E9FFAB0199
     */
    public void setSwitchingMinAmt(String l_strMinPriceSwitching)
    {
        this.switchingMinAmt = l_strMinPriceSwitching;
    }

    /**
     * (get単位金額（乗換）)<BR>
     * this.単位金額（乗換）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwitchingUnitAmt()
    {
        return this.switchingUnitAmt;
    }

    /**
     * (set単位金額（乗換）)<BR>
     * 単位金額（乗換）を設定する。<BR>
     * @@param l_strUnitPriceSwitching - 単位金額（乗換）
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwitchingUnitAmt(String l_strUnitPriceSwitching)
    {
        this.switchingUnitAmt = l_strUnitPriceSwitching;
    }
    
    /**
     * (get買付可能区分（当日発注分）)<BR>
     * this.買付可能区分（当日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getBuyPossibleDivTheDay()
    {
        return buyPossibleDivTheDay;
    }
    
    /**
     * (set買付可能区分（当日発注分）)<BR>
     * 買付可能区分（当日発注分）を設定する。<BR>
     * @@param l_strBuyPossibleDivTheDay - 買付可能区分（当日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setBuyPossibleDivTheDay(String l_strBuyPossibleDivTheDay)
    {
        this.buyPossibleDivTheDay = l_strBuyPossibleDivTheDay;
    }
    
    /**
     * (get買付可能区分（翌日発注分）)<BR>
     * this.買付可能区分（翌日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getBuyPossibleDivTheNextDay()
    {
        return buyPossibleDivTheNextDay;
    }
    
    /**
     * (set買付可能区分（翌日発注分）)<BR>
     * 買付可能区分（翌日発注分）を設定する。<BR>
     * @@param l_strBuyPossibleDivTheNextDay - 買付可能区分（翌日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setBuyPossibleDivTheNextDay(String l_strBuyPossibleDivTheNextDay)
    {
        this.buyPossibleDivTheNextDay = l_strBuyPossibleDivTheNextDay;
    }
    
    /**
     * (get注文締切終了時間（平日）)<BR>
     * this.注文締切終了時間（平日）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseEndTimeFull()
    {
        return orderCloseEndTimeFull;
    }
    
    /**
     * (set注文締切終了時間（平日）)<BR>
     * 注文締切終了時間（平日）を設定する。<BR>
     * @@param l_strOrderCloseEndTimeFull - 注文締切終了時間（平日）
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseEndTimeFull(String l_strOrderCloseEndTimeFull)
    {
        this.orderCloseEndTimeFull = l_strOrderCloseEndTimeFull;
    }
    
    /**
     * (get注文締切終了時間（半日）)<BR>
     * this.注文締切終了時間（半日）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseEndTimeHalf()
    {
        return orderCloseEndTimeHalf;
    }
    
    /**
     * (set注文締切終了時間（半日）)<BR>
     * 注文締切終了時間（半日）を設定する。<BR>
     * @@param l_strOrderCloseEndTimeHalf - 注文締切終了時間（半日）
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseEndTimeHalf(String l_strOrderCloseEndTimeHalf)
    {
        this.orderCloseEndTimeHalf = l_strOrderCloseEndTimeHalf;
    }
    
    /**
     * (get注文締切開始時間（平日）)<BR>
     * this.注文締切開始時間（平日）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseStartTimeFull()
    {
        return orderCloseStartTimeFull;
    }
    
    /**
     * (set注文締切開始時間（平日）)<BR>
     * 注文締切開始時間（平日）を設定する。<BR>
     * @@param l_strOrderCloseStartTimeFull - 注文締切開始時間（平日）
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseStartTimeFull(String l_strOrderCloseStartTimeFull)
    {
        this.orderCloseStartTimeFull = l_strOrderCloseStartTimeFull;
    }
    
    /**
     * (get注文締切開始時間（半日）)<BR>
     * this.注文締切開始時間（半日）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseStartTimeHalf()
    {
        return orderCloseStartTimeHalf;
    }
    
    /**
     * (set注文締切開始時間（半日）)<BR>
     * 注文締切開始時間（半日）を設定する。<BR>
     * @@param l_strOrderCloseStartTimeHalf - 注文締切開始時間（半日）
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseStartTimeHalf(String l_strOrderCloseStartTimeHalf)
    {
        this.orderCloseStartTimeHalf = l_strOrderCloseStartTimeHalf;
    }
    
    /**
     * (get解約可能区分（当日発注分）)<BR>
     * this.解約可能区分（当日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSellPossibleDivTheDay()
    {
        return sellPossibleDivTheDay;
    }
    
    /**
     * (set解約可能区分（当日発注分）)<BR>
     * 解約可能区分（当日発注分）を設定する。<BR>
     * @@param l_strSellPossibleDivTheDay - 解約可能区分（当日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setSellPossibleDivTheDay(String l_strSellPossibleDivTheDay)
    {
        this.sellPossibleDivTheDay = l_strSellPossibleDivTheDay;
    }
    
    /**
     * (get解約可能区分（翌日発注分）)<BR>
     * this.解約可能区分（翌日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSellPossibleDivTheNextDay()
    {
        return sellPossibleDivTheNextDay;
    }
    
    /**
     * (set解約可能区分（翌日発注分）)<BR>
     * 解約可能区分（翌日発注分）を設定する。<BR>
     * @@param l_strSellPossibleDivTheNextDay - 解約可能区分（翌日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setSellPossibleDivTheNextDay(String l_strSellPossibleDivTheNextDay)
    {
        this.sellPossibleDivTheNextDay = l_strSellPossibleDivTheNextDay;
    }
    
    /**
     * (get乗換可能区分（当日発注分）)<BR>
     * this.乗換可能区分（当日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwtPossibleDivTheDay()
    {
        return swtPossibleDivTheDay;
    }
    
    /**
     * (set乗換可能区分（当日発注分）)<BR>
     * 乗換可能区分（当日発注分）を設定する。<BR>
     * @@param l_strSwtPossibleDivTheDay - 乗換可能区分（当日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwtPossibleDivTheDay(String l_strSwtPossibleDivTheDay)
    {
        this.swtPossibleDivTheDay = l_strSwtPossibleDivTheDay;
    }
    
    /**
     * (get乗換可能区分（翌日発注分）)<BR>
     * this.乗換可能区分（翌日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwtPossibleDivTheNextDay()
    {
        return swtPossibleDivTheNextDay;
    }
    
    /**
     * (set乗換可能区分（翌日発注分）)<BR>
     * 乗換可能区分（翌日発注分）を設定する。<BR>
     * @@param l_strSwtPossibleDivTheNextDay - 乗換可能区分（翌日発注分）
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwtPossibleDivTheNextDay(String l_strSwtPossibleDivTheNextDay)
    {
        this.swtPossibleDivTheNextDay = l_strSwtPossibleDivTheNextDay;
    }
    
    /**
     * (get募集開始日)<BR>
     * this.募集開始日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getApplyAbleStartDate()
    {
        return this.applyAbleStartDate;
    }

    /**
     * (set募集開始日)<BR>
     * 買付開始日を設定する。<BR>
     * @@param l_datApplyAbleStartDate - 募集開始日
     * @@roseuid 40E92AD50146
     */
    public void setApplyAbleStartDate(Date l_datApplyAbleStartDate)
    {
        this.applyAbleStartDate = l_datApplyAbleStartDate;
    }
    
    /**
     * (get募集終了日)<BR>
     * this.募集終了日を返却する。<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getApplyAbleEndDate()
    {
        return this.applyAbleEndDate;
    }

    /**
     * (set募集終了日)<BR>
     * 募集終了日を設定する。<BR>
     * @@param l_datApplyAbleEndDate - 募集終了日
     * @@roseuid 40E92AD50146
     */
    public void setApplyAbleEndDate(Date l_datApplyAbleEndDate)
    {
        this.applyAbleEndDate = l_datApplyAbleEndDate;
    }
    
    /**
     * (get指定方法@（募集）)<BR>
     * this.指定方法@（募集）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplySelectable()
    {
        return this.applySelectable;
    }

    /**
     * (set指定方法@（募集）)<BR>
     * 指定方法@（募集）を設定する。<BR>
     * @@param l_strApplySelectable - 指定方法@（募集）
     * @@roseuid 40E92AD50146
     */
    public void setApplySelectable(String l_strApplySelectable)
    {
        this.applySelectable = l_strApplySelectable;
    }
    
    /**
     * (get最低口数（募集）)<BR>
     * this.最低口数（募集）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyMinQty()
    {
        return this.applyMinQty;
    }

    /**
     * (set最低口数（募集）)<BR>
     * 最低口数（募集）を設定する。<BR>
     * @@param l_strApplyMinQty - 最低口数（募集）
     * @@roseuid 40E92AD50146
     */
    public void setApplyMinQty(String l_strApplyMinQty)
    {
        this.applyMinQty = l_strApplyMinQty;
    }
    
    /**
     * (get単位口数（募集）)<BR>
     * this.単位口数（募集）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyUnitQty()
    {
        return this.applyUnitQty;
    }

    /**
     * (set単位口数（募集）)<BR>
     * 単位口数（募集）を設定する。<BR>
     * @@param l_strApplyUnitQty - 単位口数（募集）
     * @@roseuid 40E92AD50146
     */
    public void setApplyUnitQty(String l_strApplyUnitQty)
    {
        this.applyUnitQty = l_strApplyUnitQty;
    }
    
    /**
     * (get最低金額（募集）)<BR>
     * this.最低金額（募集）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyMinAmt()
    {
        return this.applyMinAmt;
    }

    /**
     * (set最低金額（募集）)<BR>
     * 最低金額（募集）を設定する。<BR>
     * @@param l_strApplyMinAmt - 最低金額（募集）
     * @@roseuid 40E92AD50146
     */
    public void setApplyMinAmt(String l_strApplyMinAmt)
    {
        this.applyMinAmt = l_strApplyMinAmt;
    }
    
    /**
     * (get単位金額（募集）)<BR>
     * this.単位金額（募集）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyUnitAmt()
    {
        return this.applyUnitAmt;
    }

    /**
     * (set単位金額（募集）)<BR>
     * 単位金額（募集）を設定する。<BR>
     * @@param l_strApplyUnitAmt - 単位金額（募集）
     * @@roseuid 40E92AD50146
     */
    public void setApplyUnitAmt(String l_strApplyUnitAmt)
    {
        this.applyUnitAmt = l_strApplyUnitAmt;
    }
    
    /**
     * (募集可能区分（当日発注分）)<BR>
     * this.募集可能区分（当日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyPossDivTheDay()
    {
        return this.applyPossDivTheDay;
    }

    /**
     * (set募集可能区分（当日発注分）)<BR>
     * 募集可能区分（当日発注分）を設定する。<BR>
     * @@param l_strApplyPossDivTheDay - 募集可能区分（当日発注分）
     * @@roseuid 40E92AD50146
     */
    public void setApplyPossDivTheDay(String l_strApplyPossDivTheDay)
    {
        this.applyPossDivTheDay = l_strApplyPossDivTheDay;
    }
    
    /**
     * (募集可能区分（翌日発注分）)<BR>
     * this.募集可能区分（翌日発注分）を返却する。<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyPossDivTheNextDay()
    {
        return this.applyPossDivTheNextDay;
    }

    /**
     * (set募集可能区分（翌日発注分）)<BR>
     * 募集可能区分（翌日発注分）を設定する。<BR>
     * @@param l_strApplyPossDivTheNextDay - 募集可能区分（翌日発注分）
     * @@roseuid 40E92AD50146
     */
    public void setApplyPossDivTheNextDay(String l_strApplyPossDivTheNextDay)
    {
        this.applyPossDivTheNextDay = l_strApplyPossDivTheNextDay;
    }

    /**
     * (get外貨最低金額（新規買付）)<BR>
     * this.外貨最低金額（新規買付）を返却する。<BR>
     * @@return String
     */
    public String getBuyFrgnMinAmtBuy()
    {
        return this.buyFrgnMinAmtBuy;
    }

    /**
     * (set外貨最低金額（新規買付）)<BR>
     * 外貨最低金額（新規買付）を設定する。<BR>
     * @@param l_strBuyFrgnMinAmtBuy - 外貨最低金額（新規買付）
     */
    public void setBuyFrgnMinAmtBuy(String l_strBuyFrgnMinAmtBuy)
    {
        this.buyFrgnMinAmtBuy = l_strBuyFrgnMinAmtBuy;
    }

    /**
     * (get外貨単位金額（新規買付）)<BR>
     * this.外貨単位金額（新規買付）を返却する。<BR>
     * @@return String
     */
    public String getBuyFrgnUnitAmtBuy()
    {
        return this.buyFrgnUnitAmtBuy;
    }

    /**
     * (set外貨単位金額（新規買付）)<BR>
     * 外貨単位金額（新規買付）を設定する。<BR>
     * @@param l_strBuyFrgnUnitAmtBuy - 外貨単位金額（新規買付）
     */
    public void setBuyFrgnUnitAmtBuy(String l_strBuyFrgnUnitAmtBuy)
    {
        this.buyFrgnUnitAmtBuy = l_strBuyFrgnUnitAmtBuy;
    }

    /**
     * (get外貨最低金額（追加買付）)<BR>
     * this.外貨最低金額（追加買付）を返却する。<BR>
     * @@return String
     */
    public String getBuyFrgnMinAmtAdd()
    {
        return this.buyFrgnMinAmtAdd;
    }

    /**
     * (set外貨最低金額（追加買付）)<BR>
     * 外貨最低金額（追加買付）を設定する。<BR>
     * @@param l_strBuyFrgnMinAmtAdd - 外貨最低金額（追加買付）
     */
    public void setBuyFrgnMinAmtAdd(String l_strBuyFrgnMinAmtAdd)
    {
        this.buyFrgnMinAmtAdd = l_strBuyFrgnMinAmtAdd;
    }

    /**
     * (get外貨単位金額（追加買付）)<BR>
     * this.外貨単位金額（追加買付）を返却する。<BR>
     * @@return String
     */
    public String getBuyFrgnUnitAmtAdd()
    {
        return this.buyFrgnUnitAmtAdd;
    }

    /**
     * (set外貨単位金額（追加買付）)<BR>
     * 外貨単位金額（追加買付）を設定する。<BR>
     * @@param l_strBuyFrgnUnitAmtAdd - 外貨単位金額（追加買付）
     */
    public void setBuyFrgnUnitAmtAdd(String l_strBuyFrgnUnitAmtAdd)
    {
        this.buyFrgnUnitAmtAdd = l_strBuyFrgnUnitAmtAdd;
    }

    /**
     * (get外貨最低金額（解約）)<BR>
     * this.外貨最低金額（解約）を返却する。<BR>
     * @@return String
     */
    public String getSellFrgnMinAmtSell()
    {
        return this.sellFrgnMinAmtSell;
    }

    /**
     * (set外貨最低金額（解約）)<BR>
     * 外貨最低金額（解約）を設定する。<BR>
     * @@param l_strSellFrgnMinAmtSell - 外貨最低金額（解約）
     */
    public void setSellFrgnMinAmtSell(String l_strSellFrgnMinAmtSell)
    {
        this.sellFrgnMinAmtSell = l_strSellFrgnMinAmtSell;
    }

    /**
     * (get外貨単位金額（解約）)<BR>
     * this.外貨単位金額（解約）を返却する。<BR>
     * @@return String
     */
    public String getSellFrgnUnitAmtSell()
    {
        return this.sellFrgnUnitAmtSell;
    }

    /**
     * (set外貨単位金額（解約）)<BR>
     * 外貨単位金額（解約）を設定する。<BR>
     * @@param l_strSellFrgnUnitAmtSell - 外貨単位金額（解約）
     */
    public void setSellFrgnUnitAmtSell(String l_strSellFrgnUnitAmtSell)
    {
        this.sellFrgnUnitAmtSell = l_strSellFrgnUnitAmtSell;
    }
}
@
