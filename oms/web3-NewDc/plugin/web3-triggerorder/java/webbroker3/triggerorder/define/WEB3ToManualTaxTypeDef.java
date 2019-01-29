head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToManualTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分定義インタフェイス(WEB3ToManualTaxTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 沈迪(中訊) 新規作成
*/

package webbroker3.triggerorder.define;

/**
 * 口座区分 定義インタフェイス<BR>
 * 
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3ToManualTaxTypeDef
{
    /**
     * 0：　@オプション買建口座
     */
    public static final String OPTION_BUY_TAX = "0";
    
    /**
     *  1：　@先物オプション口座
     */
    public static final String FUTURE_OPTION_TAX = "1";
    
}@
