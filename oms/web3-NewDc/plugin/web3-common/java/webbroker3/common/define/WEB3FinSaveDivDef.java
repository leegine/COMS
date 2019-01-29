head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FinSaveDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預金区分(WEB3FinSaveDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 預金区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3FinSaveDivDef
{

    /**
     * 1：普通預金
     */
    public final static String GENERAL_FIN_SAVE  = "1";
    
    /**
     * 2：当座預金
     */
    public final static String ACCOUNT_FIN_SAVE  = "2";

    /**
     * 3：その他
     */
    public final static String OTHER  = "3";
    
    /**
     * 4：貯蓄預金
     */
    public final static String SAVING_FIN_SAVE  = "4";
     
}
@
