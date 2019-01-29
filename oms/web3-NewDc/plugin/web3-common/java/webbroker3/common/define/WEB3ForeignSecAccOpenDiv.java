head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForeignSecAccOpenDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  外国証券口座開設区分　@定数定義インタフェイス(WEB3ForeignSecAccOpenDiv)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客マスター.外国証券口座開設区分 定数定義インタフェイス。<BR>
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3ForeignSecAccOpenDiv
{
    
    /**
      * 0：未開設
      */
    public static final String NOT_OPEN = "0";
    
    /**
     *  1：開設
     */
    public static final String OPEN  = "1";

}
@
