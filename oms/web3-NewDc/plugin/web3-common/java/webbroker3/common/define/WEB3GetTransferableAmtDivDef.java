head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GetTransferableAmtDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXからの振替可能額取得区分定数定義インタフェイス(WEB3GetTransferableAmtDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/26 趙林鵬(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * FXからの振替可能額取得区分定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルのFXからの振替可能額取得区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3GetTransferableAmtDivDef
{
    /**
     * ０：取得しない
     */
    public static final String NOT_GET = "0";

    /**
     * １：取得する
     */
    public static final String GET = "1";
}
@
