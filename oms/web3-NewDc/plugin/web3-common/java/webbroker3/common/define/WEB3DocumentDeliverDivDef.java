head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付区分定数定義インタフェイス(WEB3DocumentDeliverDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 書面交付区分定数定義インタフェイス
 * 
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public interface WEB3DocumentDeliverDivDef 
{
    /**
     * 0 : 未交付
     */
    public static final String UNDELIVERY = "0";
    
    /**
     * 1 : 交付済
     */
    public static final String DELIVERY = "1";
}
@
