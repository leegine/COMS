head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EnableOrderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付可否 定数定義インタフェイス(WEB3EnableOrderDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 今井　@高史(SRA) 新規作成
Revesion History : 2004/09/10 孟　@東(sinocom) JavaDocを修正
*/
package webbroker3.common.define;

/**
 * 受付可否　@定数定義インタフェイス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3EnableOrderDef
{

    /**
     * 0 : 受付可能
     */
    public static final String ENABLE = "0";

    /**
     * 1 : 受付不可
     */
    public static final String DISABLED = "1";
   
}
@
