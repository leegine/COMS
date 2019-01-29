head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoPositionBalance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPポジションバランス(WEB3IfoPositionBalance.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) 新規作成
*/
package webbroker3.ifodeposit;

/**
 * (先物OPポジションバランス)<BR>
 * ポジションバランスを表すクラス。<BR>
 */
public class WEB3IfoPositionBalance
{
    /**
     * (ポジションバランス)<BR>
     * 
     * ポジションバランス枚数<BR>
     * ※ポジションバランス区分がニュートラルの場合、0<BR>
     */
    public double positionBalance;

    /**
     * (ポジションバランス区分)<BR>
     * 0：　@ニュートラル<BR>
     * 1：　@買超過<BR>
     * 2：　@売超過<BR>
     */
    public String positionBalanceType;

    /**
     * (コンストラクタ)
     */
    public WEB3IfoPositionBalance()
    {

    }

    /**
     * (先物OPポジションバランス)<BR>
     * 
     * コンストラクタ。<BR>
     * 引数の値を同プロパティにセットする。<BR>
     * @@param l_dblPositionBalance - (ポジションバランス)<BR>
     * 
     * ポジションバランス枚数<BR>
     * @@param l_strPositionBalanceType - ポジションバランス区分
     * @@roseuid 41354D510249
     */
    public WEB3IfoPositionBalance(double l_dblPositionBalance, String l_strPositionBalanceType)
    {
        /*
         * 引数の値を同プロパティにセット
         */
        this.positionBalance = l_dblPositionBalance;
        this.positionBalanceType = l_strPositionBalanceType;
    }
}
@
