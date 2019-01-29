head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3YellowCustomerDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  Y客　@定数定義インタフェイス(WEB3YellowCustomerDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * Y客　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3YellowCustomerDef
{
    /**
      * 0：Y客以外 
      */
    public static final String NOT_YELLOW_CUSTOMER = "0";
    
    /**
     * 1 : JASDAQの呼値
     */
    public static final String YELLOW_CUSTOMER = "1";
    
}@
