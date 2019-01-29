head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioOperateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 為替保証金処理区分 定数定義インタフェイス(WEB3AioOperateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/5/7 佐藤 (SCS) 新規作成
*/
package webbroker3.common.define;

/**
 * 為替保証金処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 佐藤
 * @@version 1.0
 */
public interface WEB3AioOperateDivDef
{
    /**
     * 01:口座開設　@
     */
    public static final String CREATE_USER = "01";

    /**
     * 02：FXへの振替
     */
    public static final String DEPOSIT = "02";
    
    /**
     * 03：FXから振替
     */
    public static final String WITHDRAW = "03";
}
@
