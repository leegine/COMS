head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BookValueRemarkDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3BookValueRemarkDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 備考　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3BookValueRemarkDef
{
    /**
     * 0:その他
     */
    public static final String OTHER = "0";

    /**
     * 1:取消済み
     */
    public static final String CANCEL = "1";

}
@
