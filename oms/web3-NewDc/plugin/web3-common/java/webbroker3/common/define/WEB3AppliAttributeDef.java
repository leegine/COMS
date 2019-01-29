head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込属性区分 定数定義定数定義インタフェイス(WEB3AppliAttributeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/06 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込属性テーブルの申込属性区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AppliAttributeDef
{
    /**
     * 1：無料対象
     */
    public final static String FREE_OBJECT  = "1";

    /**
     * 2：申込不可
     */
    public final static String CANNOT_APPLI  = "2";
}
@
