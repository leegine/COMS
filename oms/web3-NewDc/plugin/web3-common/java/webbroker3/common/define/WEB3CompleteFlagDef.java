head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CompleteFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 完了画面FLAG  定数定義インタフェイス(WEB3CompleteFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 完了画面FLAG　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3CompleteFlagDef
{

    /**
     *  ０：完了画面必要なし（TOPページ） 
     */
    public static final String NO_COMPLETE_NEED_TOP_PAGE = "0";

    /**
     *  １：完了画面必要（TOPページ）　@
     */
    public static final String COMPLETE_NEED_TOP_PAGE = "1";

    /**
     *  ２：完了画面必要なし（入出金画面）　@
     */
    public static final String NO_COMPLETE_NEED_CASHTRANS_SCREEN = "2";
    /**
     *  ３：完了画面必要（入出金画面）
     */
    public static final String COMPLETE_NEED_CASHTRANS_SCREEN = "3";
     
}
@
