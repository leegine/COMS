head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoCashoutStopUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金停止情報(WEB3PvInfoCashoutStopUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/12 張騰宇(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (出金停止情報)<BR>
 * 出金停止情報クラス<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3PvInfoCashoutStopUnit extends Message 
{
    
    /**
     * (出金停止発生日)<BR>
     * 出金停止発生日<BR>
     */
    public String cashoutStopDate;
        
    /**
     * (出金停止額)<BR>
     * 出金停止額<BR>
     */
    public String cashoutStopAmount;
    
    /**
     * (出金停止情報)<BR>
     * コンストラクタ。<BR>
     */
    public WEB3PvInfoCashoutStopUnit()
    {
        
    }
    
}@
