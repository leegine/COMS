head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentReserveDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3PaymentReserveDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王蘭芬(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルの出金予約実施　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3PaymentReserveDef
{
    /**
     * 0 : 未実施
     */
    public static final String NOT_ENFORCEMENT = "0";

    /**
     * 1 : 直近の振込日以降1日目まで可能
     */
    public static final String ENFORCEMENT_AFTER_1DAY = "1";    

    /**
     * 2 : 直近の振込日以降2日目まで可能
     */
    public static final String ENFORCEMENT_AFTER_2DAY = "2";    

    /**
     * 3 : 直近の振込日以降3日目まで可能
     */
    public static final String ENFORCEMENT_AFTER_3DAY = "3";    

    /**
     * 4 : 直近の振込日以降4日目まで可能
     */
    public static final String ENFORCEMENT_AFTER_4DAY = "4";    
}
@
