head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3NoticeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 通知種別定数定義インタフェイス(WEB3NoticeTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 通知種別 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3NoticeTypeDef
{
    /**
     * 00：デフォルト
     */
    public static final String DEFAULT = "00";

    /**
     * 01：受付系
     */
    public static final String ACCEPT_TYPE = "01";

    /**
     * 02：約定系
     */
    public static final String EXECUTED_TYPE = "02";
}
@
