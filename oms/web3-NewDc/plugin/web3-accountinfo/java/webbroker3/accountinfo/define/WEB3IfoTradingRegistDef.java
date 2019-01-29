head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3IfoTradingRegistDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取引登録 定数定義インタフェイス(WEB3IfoTradingRegistDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;


/**
 * 先物OP取引登録 定数定義インタフェイス
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3IfoTradingRegistDef
{
    
    /**
     * 1：登録済み（OP買建取引）
     */
    public final static String REGISTED_OPTION_BUY_TRADING = "1";
    
    /**
     * 2：登録済み（先物取引）
     */
    public final static String REGISTED_FUTURES_TRADING = "2";
    
    /**
     * 3：登録済み（先物／OP取引）
     */
    public final static String REGISTED_FUTURES_OPTION_TRADING = "3";

}
@
