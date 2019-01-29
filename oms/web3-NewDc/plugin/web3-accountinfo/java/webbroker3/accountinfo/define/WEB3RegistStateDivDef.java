head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3RegistStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込状況区分 定数定義インタフェイス(WEB3RegistStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 申込状況区分 定数定義インタフェイス
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3RegistStateDivDef
{
    /**
     * 判定待ち　@　@
     */
    public final static String WAIT_DECISION = "0";
    
    /**
     * 判定待ち（確認中）　@　@
     */
    public final static String WAIT_DECISION_CONFIRMING = "1";
    
    /**
     * 判定済み　@　@
     */
    public final static String DECISION_COMPLETE = "2";
 
}
@
