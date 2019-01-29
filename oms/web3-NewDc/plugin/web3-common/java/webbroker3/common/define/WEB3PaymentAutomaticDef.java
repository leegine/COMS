head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentAutomaticDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 本部一括自動送金実施　@定数定義(WEB3PaymentAutomaticDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/02 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルの本部一括自動送金実施　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3PaymentAutomaticDef 
{
    /**
     * 0 : 未実施
     */
    public static final String NOT_ENFORCEMENT = "0";

    /**
     * 1 : 実施
     */
    public static final String ENFORCEMENT = "1";
}
@
