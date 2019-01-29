head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SpecialProcessDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特殊処理区分定数定義インタフェイス(WEB3SpecialProcessDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/19 栄イ(中訊) 新規作成
Revision History : 2008/05/22 趙林鵬(中訊) ＤＢレイアウトNo.033
*/
package webbroker3.common.define;

/**
 * 特殊処理区分 定数定義インタフェイス
 * 
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SpecialProcessDivDef
{
    /**
     * null：通常サービス
     */
    public final static String NORMAL_SERVICE = null;

    /**
     * 1：外部連携サービス
     */
    public final static String OTHER_ORG_SERVICE = "1";

    /**
     * 2：債券連携
     */
    public final static String STREAM = "2";
}
@
