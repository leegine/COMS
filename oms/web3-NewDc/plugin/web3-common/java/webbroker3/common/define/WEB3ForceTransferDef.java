head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForceTransferDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ForceTransferDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 王蘭芬 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 振替請求注文キューテーブルの強制振替　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3ForceTransferDef
{
    /**
     * 9:強制  
     */
    public static final String FORCE_TRANSFER = "9";
}
@
