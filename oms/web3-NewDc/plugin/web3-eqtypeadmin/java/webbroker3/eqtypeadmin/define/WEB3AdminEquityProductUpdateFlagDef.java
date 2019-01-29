head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityProductUpdateFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄更新フラグ定数定義インタフェイス(WEB3AdminEquityProductUpdateFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/12 劉剣（中訊）新規作成
*/
package webbroker3.eqtypeadmin.define;

/**
 * 銘柄更新フラグ 定数定義インタフェイス
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminEquityProductUpdateFlagDef
{
    /**
     * 1：銘柄更新済み
     */
    public final static String PRODUCT_UPDATE = "1";

    /**
     * 2：銘柄未更新
     */
    public final static String PRODUCT_NOT_UPDATE = "2";
}
@
