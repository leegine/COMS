head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力結果(WEB3TPTradingPowerResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (取引余力結果)
 */
public class WEB3TPTradingPowerResult
{

    /**
     * (判定フラグ)<BR>
     * 
     * 取引余力チェック結果がセットされる。<BR>
     * 　@・取引余力OKの時⇒true<BR>
     * 　@・取引余力NGの時⇒false<BR>
     */
    private boolean resultFlg;

    /**
     * (取引可能額)<BR>
     * 
     * 現物株式・信用取引の時、<BR>
     * 次回取引可能額がセットされる<BR>
     */
    private double tradingPower;

    /**
     * (注意文言表示区分)
     * 
     * [1：現金不足注意文言表示]
     * 　@注文種別：現物買付／現物売付
     * 
     * 　@(*)現物顧客かつ預り証券評価制顧客の場合で、
     * 　@　@取引余力チェックＯＫだが現金不足になるような取引を
     * 　@　@行った場合にセット。
     * 
     * [2：増担保規制注意文言表示]
     * 　@注文種別：信用新規買建／信用新規売建
     * 
     * 　@(*)注文銘柄が増担保規制銘柄の場合にセット
     * 
     * [3：預り金不足注意文言表示]
     * 　@注文種別：現物買付／現物売付／信用現引／信用現渡
     * 
     * 　@(*)当該顧客が営業店部店の場合で、
     * 　@　@取引余力チェックＮＧになるような取引を
     * 　@　@行った場合にセット
     * 
     * [null：注意文言非表示
     * 　@上記以外の場合
     * 
     */
    private String attentionObjectionType;

    /**
     * (預り金不足額)
     * 取引余力結果.注意文言表示区分 = 3:預り金不足注意文言表示　@の場合に
     * その預り金不足額をセットする。
     */
    private double lackAccountBalance;
    
    /**
     * (取引余力エラー情報)<BR>
     * <BR>
     * ○判定フラグ==trueの場合<BR>
     * 　@取引余力エラー情報オブジェクトがセットされる。<BR>
     * <BR>
     * ○判定フラグ==falseの場合<BR>
     * 　@nullがセットされる。<BR>
     * <BR>
     */
    private WEB3TPTradingPowerErrorInfo tpErrorInfo;

    /**
     * (コンストラクタ)<BR>
     * @@roseuid 41593C5202CC
     */
    public WEB3TPTradingPowerResult()
    {

    }

    /**
     * (is判定フラグ)<BR>
     * this.判定フラグを返却する。<BR>
     * 
     * @@return boolean
     * @@roseuid 41593C1B00D8
     */
    public boolean isResultFlg()
    {
        return this.resultFlg;
    }

    /**
     * (set判定フラグ)<BR>
     * 引数.判定フラグをthis.判定フラグにセットする。<BR>
     * 
     * @@param l_blnResultFlg - (判定フラグ)
     * @@roseuid 41593C110230
     */
    public void setResultFlg(boolean l_blnResultFlg)
    {
        this.resultFlg = l_blnResultFlg;
    }

    /**
     * (get取引可能額)<BR>
     * this.取引可能額を返却する。<BR>
     * 
     * @@return double
     * @@roseuid 41593C2A000D
     */
    public double getTradingPower()
    {
        return this.tradingPower;
    }

    /**
     * (set取引可能額)<BR>
     * 引数.取引可能額をthis.取引可能額にセットする。<BR>
     * 
     * @@param l_dblTradingPower - (取引可能額)
     * @@roseuid 41593C23007A
     */
    public void setTradingPower(double l_dblTradingPower)
    {
        this.tradingPower = l_dblTradingPower;
    }

    /**
     * (get注意文言表示区分)<BR>
     * this.注意文言表示区分を返却する。<BR>
     * 
     * @@return String
     */
    public String getAttentionObjectionType()
    {
        return this.attentionObjectionType;
    }

    /**
     * (set注意文言表示区分)<BR>
     * 引数.注意文言表示区分をthis.注意文言表示区分にセットする。<BR>
     * 
     * @@param l_strType - (注意文言表示区分)
     */
    public void setAttentionObjectionType(String l_strType)
    {
        this.attentionObjectionType = l_strType;
    }

    /**
     *(get預り金不足額)
     *
     *this.預り金不足額を返却する。
     *
     */
    public double getLackAccountBalance()
    {
        return this.lackAccountBalance;
    }

    /**
     * (set預り金不足額)
     * 
     * 引数.預り金不足額をthis.預り金不足額にセットする。
     * 
     * @@param l_dblTradingPower - (預り金不足額)
     */
    public void setLackAccountBalance(double l_dblLackAccountBalance)
    {
        this.lackAccountBalance = l_dblLackAccountBalance;
    }

    /**
     * (get取引余力エラー情報
     * 
     * this.取引余力エラー情報を返却する。 
     * @@return double
     */
    public WEB3TPTradingPowerErrorInfo getTpErrorInfo()
    {
        return this.tpErrorInfo;
    }

    /**
     * (set取引余力エラー情報)
     * 
     * 引数.取引余力エラー情報をthis.取引余力エラー情報にセットする。
     * @@param l_tpErrorInfo - (取引余力エラー情報)
     */
    public void setTpErrorInfo(WEB3TPTradingPowerErrorInfo l_tpErrorInfo)
    {
        this.tpErrorInfo = l_tpErrorInfo;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("resultFlg", this.isResultFlg());
        l_builder.append("tradingPower", this.tradingPower);
        l_builder.append("attentionObjectionType", this.attentionObjectionType);
        l_builder.append("lackAccountBalance", this.lackAccountBalance);
        if(this.tpErrorInfo != null)
        {
            l_builder.append("tpErrorInfo", this.tpErrorInfo.toString());
        }
        else
        {
            l_builder.append("tpErrorInfo", this.tpErrorInfo);
        }


        return l_builder.toString();
    }
}
@
