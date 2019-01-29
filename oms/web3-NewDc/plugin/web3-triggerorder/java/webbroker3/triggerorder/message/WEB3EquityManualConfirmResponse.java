head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注確認レスポンス(WEB3EquityManualConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@魏新(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

/**
 * (株式手動発注確認レスポンス)<BR>
 * 株式手動発注確認レスポンス<BR>
 * 
 * @@author 魏新
 * @@version 1.0
 */
public class WEB3EquityManualConfirmResponse extends WEB3EquityManualCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;
    
    /**
     * (市場終了警告市場コード一覧)<BR>
     * 市場終了警告市場コード一覧<BR>
     * 
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension = null;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920271
     */
    public WEB3EquityManualConfirmResponse() 
    {
     
    }    
}
@
