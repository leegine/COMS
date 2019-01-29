head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTickMatchDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 歩み値照合区分定義インタフェイス(WEB3AdminToTickMatchDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23 肖志偉 (中訊) 新規作成
*/
package webbroker3.admintriggerorder.define;

/**
 * 歩み値照合区分 定義インタフェイス
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public interface WEB3AdminToTickMatchDivDef
{

    /**
     * 0: 正常
     */
    public static final String NORMAL = "0";
    
    /**
     * 1：　@未発注疑い
     */
    public static final String NOT_ORDER_SUSPICION = "1";
    
    /**
     * 2：　@不正発注疑い
     */
    public static final String ERROR_ORDER_SUSPICION = "2";
    
    /**
     * 3：　@発注遅延疑い
     */
    public static final String ORDER_DELAY_SUSPICION = "3";
    
    /**
     * 9：　@全てのエラー
     */
    public static final String ALL_ERROR = "9";
    
}
@
