head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注完了リクエスト(WEB3EquityManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@魏新(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

/**
 * (株式手動発注完了リクエスト)<BR>
 * 株式手動発注完了リクエスト<BR>
 * 
 * @@author 魏新
 * @@version 1.0
 */
public class WEB3EquityManualCompleteRequest extends WEB3ManualCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password = null;

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityManualCompleteRequest() 
    {
     
    }    
}
@
