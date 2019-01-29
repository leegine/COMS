head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InitialSetDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 初期設定区分定数定義インタフェイス(WEB3InitialSetDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/18 栄イ (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 手数料コースコード変換マスタの初期設定区分 定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3InitialSetDivDef
{
    /**
     * 0：DEFAULT（未設定）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：口座開設時作成対象
     */
    public final static String MAKE_OBJECT_WHEN_ACCOUNT_OPEN = "1";
}
@
