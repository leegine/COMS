head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ServiceImpState.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス実施状態をxTradeクライアントに渡す為のメッセージクラス(WEB3ServiceImpState.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 菊地(SRA)
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;

/**
 * (サービス実施状態)<BR>
 * サービス実施状態をxTradeクライアントに渡す為のメッセージクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3ServiceImpState extends Message
{

    /**
     * 時価情報リアルディレイ区分（リアル）<BR>
     */
    public static final String QUICK_REAL = "07_Quick_Real";

    /**
     * 時価情報リアルディレイ区分（ディレイ）<BR>
     */
    public static final String QUICK_DELAY = "07_Quick_Delay";

    /**
     * (時価情報リアルディレイ区分)<BR>
     * "07_Quick_Real" ：リアル<BR>
     * "07_Quick_Delay"：ディレイ<BR>
     * ※変更する必要もないと思うので、現行に合わせる事とする。<BR>
     * 　@全体と調整する際、変更が必要となるかも知れない。<BR>
     */
    public String quickDiv;

    /**
     * 引数を使用して各種サービス実施状態を取得し、属性にセットする。<BR>
     * （setサービス実施状態メソッドを実行する）<BR>
     * @@param l_institutionID
     * @@param l_branchID
     * @@param l_accountID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A337740152
     */
    public WEB3ServiceImpState(
        long l_institutionID,
        long l_branchID,
        long l_accountID)
        throws WEB3BaseException
    {
        setServiceImpState(l_institutionID, l_branchID, l_accountID);
    }

    /**
     * デフォルト・コンストラクタ<BR>
     * xTradeの為に実装しておくが、通常は引数ありのコンストラクタを使用する。<BR>
     * @@roseuid 403ED58E0267
     */
    public WEB3ServiceImpState()
    {

    }

    /**
     * （setサービス実施状態）
     * 各サービス実施状態取得メソッドを実行し、結果を属性にセットする。<BR>
     * @@param l_institutionID
     * @@param l_branchID
     * @@param l_accountID
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A3377402A9
     */
    public void setServiceImpState(
        long l_institutionID,
        long l_branchID,
        long l_accountID)
        throws WEB3BaseException
    {
        quickDiv = getQuickDiv(l_accountID);
    }

    /**
     * （get時価情報リアルディレイ区分）
     * TODO：ダミー。常にリアルを返す。仕様が決まり次第実装。<BR>
     * 時価情報リアルディレイ区分を取得する。<BR>
     * @@param l_accountID
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A33775000A
     */
    public static String getQuickDiv(long l_accountID) throws WEB3BaseException
    {
        return QUICK_REAL;
    }
}
@
