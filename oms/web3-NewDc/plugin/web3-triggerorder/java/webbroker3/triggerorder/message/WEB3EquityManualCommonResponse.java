head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注共通レスポンス(WEB3EquityManualCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@魏新(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (株式手動発注共通レスポンス)<BR>
 * 株式手動発注共通レスポンス<BR>
 * 
 * @@author 魏新
 * @@version 1.0
 */
public class WEB3EquityManualCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;
    
    /**
     * (株式手動発注Unit)<BR>
     * 株式手動発注Unit<BR>
     */
    public WEB3EquityManualUnit[] manualUnits;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920271
     */
    public WEB3EquityManualCommonResponse() 
    {
     
    }
    
}
@
