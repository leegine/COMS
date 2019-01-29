head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OutstandingTermDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買・売 未済期間定数定義インタフェイス(WEB3OutstandingTermDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 李海波(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 買・売 未済期間　@定数定義インタフェイス
 *                                                                      
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3OutstandingTermDef
{
    
    /**
     * P'2': 翌日受渡銘柄
     */
    public static final String NEXT_DELIVERY_PRODUCT = "P'2'";

}
@
