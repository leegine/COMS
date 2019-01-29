head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3MobileOfficeChangeStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 携帯番号・勤務先情報変更状態区分 定数定義インタフェイス(WEB3MobileOfficeChangeStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 携帯番号・勤務先情報変更状態区分 定数定義インタフェイス
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3MobileOfficeChangeStateDivDef
{
    /**
     * 0：申込可　@　@
     */
    public final static String REGIST_POSSIBLE = "0";
    
    /**
     * 1：申込中　@　@
     */
    public final static String REGISTING = "1";   
    
    /**
     * 2：確認中　@　@
     */
    public final static String CONFIRMING = "2"; 
   
}
@
