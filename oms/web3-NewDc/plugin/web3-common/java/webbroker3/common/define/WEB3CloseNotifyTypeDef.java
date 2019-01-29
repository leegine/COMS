head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CloseNotifyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CloseNotifyTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 失効通知区分　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3CloseNotifyTypeDef
{
    /**
     * 1 : 失効
     */
    public static final String CLOSE = "1";

    /**
     * 2 : :失効取消
     */
    public static final String CLOSE_CANCEL = "2";

    /**
     * 3 : エラー
     */
    public static final String ERROR = "3";

}
@
