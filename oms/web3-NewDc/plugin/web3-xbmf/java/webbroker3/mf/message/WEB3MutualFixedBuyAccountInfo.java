head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額引落口座情報(WEB3MutualFixedBuyAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信定時定額引落口座情報)<BR>
 * 投信定時定額引落口座情報<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyAccountInfo extends Message
{
    /**
     * (金融機@関区分)<BR>
     * 金融機@関区分 <BR>
     * <BR>
     * 1:銀行　@　@2:郵便局 <BR>
     */
    public String financialInstitutionDiv;
    
    /**
     * (銀行コード)<BR>
     * 銀行コード <BR>
     * <BR>
     * 非表示<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (銀行名)<BR>
     * 銀行名 <BR>
     * <BR>
     * 金融機@関区分が郵便局の場合null<BR>
     */
    public String financialInstitutionName;
    
    /**
     * (支店コード)<BR>
     * 支店コード <BR>
     *  <BR>
     * 金融機@関区分が郵便局の場合  <BR>
     * 通帳記号の2桁目から4桁目として使用する。  <BR>
     * （通帳記号の1桁目、5桁目は固定値ため表示で対応する。）<BR>
     */
    public String financialBranchCode;
    
    /**
     * (支店名)<BR>
     * 支店名 <BR>
     * <BR>
     * 金融機@関区分が郵便局の場合null<BR>
     */
    public String financialBranchName;
    
    /**
     * (預金区分)<BR>
     * 預金区分 <BR>
     * <BR>
     * 1:普通　@2:当座 <BR>
     * 金融機@関区分が郵便局の場合null<BR>
     */
    public String financialAccountDiv;
    
    /**
     * (引落口座番号)<BR>
     * 引落口座番号 <BR>
     * <BR>
     * 金融機@関区分が郵便局の場合 <BR>
     * 通帳番号の1桁目から7桁目として使用する。 <BR>
     * （通帳記号の8桁目は固定値ため表示で対応する。）<BR>
     */
    public String financialAccountCode;
    
    /**
     * (引落口座名義人（カナ）)<BR>
     * 引落口座名義人（カナ）<BR>
     */
    public String financialAccountName;
    
    /**
     * (投信定時定額引落口座情報のインスタンスを生成する。)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyAccountInfo()
    {
    }
}
@
