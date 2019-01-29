head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuoteTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価取得区分(WEB3QuoteTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 張宝楠 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 時価取得区分　@定数定義インタフェイス
 *                                                                      
 * @@author 張宝楠 (中訊)
 * @@version 1.0
 */
public interface WEB3QuoteTypeDef
{
    
    /**
     * ディレイ客
     */
    public static final String DELAY_CUSTOMER = "0";

    /**
     * リアル客
     */
    public static final String REAL_CUSTOMER = "1";
    
}
@
