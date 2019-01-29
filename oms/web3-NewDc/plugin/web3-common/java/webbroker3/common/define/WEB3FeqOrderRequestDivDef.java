head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqOrderRequestDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式市場連動区分定数定義インタフェイス(WEB3FeqOrderRequestDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/07 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 外国株式市場連動区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3FeqOrderRequestDivDef
{
    /**
     * 0：非連動（MAIL）
     */
    public static final String REQUEST_MAIL = "0";

    /**
     * 1：連動（MQ）
     */
    public static final String REQUEST_MQ = "1";
}
@
