head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RemarkNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 摘要名定数定義インタフェイス(WEB3RemarkNameDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/25 陸文静 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 摘要名定数定義インタフェイス<BR>
 * (振替請求注文キューテーブルの摘要名の參照)<BR>
 * <BR>
 * @@author 陸文静 (中訊)
 * @@version 1.0
 */
public interface WEB3RemarkNameDef
{
    /**
     * CFDﾍﾌﾘｶｴ
     */
    public static final String TO_CFD_TRANSFER= "CFDﾍﾌﾘｶｴ";

    /**
     * CFDｶﾗﾌﾘｶｴ
     */
    public static final String FROM_CFD_TRANSFER= "CFDｶﾗﾌﾘｶｴ";
}
@
