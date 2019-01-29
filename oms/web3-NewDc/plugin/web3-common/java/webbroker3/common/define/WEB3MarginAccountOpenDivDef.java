head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引口座開設区分(顧客マスター)　@定数定義インタフェイス(WEB3MarginAccountOpenDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/15 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 信用取引口座開設区分(顧客マスター)　@定数定義インタフェイス。<BR>
 *    制度信用取引口座開設区分 <BR>
 *    一般信用取引口座開設区分 <BR>
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3MarginAccountOpenDivDef
{
    /**
      * 0：DEFAULT（口座なし）  
      */
    public static final String MARGIN_ACCOUNT_NOT_OPEN = "0";
    
    /**
     *  1：口座開設
     */
    public static final String MARGIN_ACCOUNT_OPEN  = "1";
    
}@
