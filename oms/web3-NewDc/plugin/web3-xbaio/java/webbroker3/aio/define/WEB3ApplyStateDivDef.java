head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3ApplyStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込状況 定数定義インタフェイス(WEB3ApplyStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/17 何文敏(中訊) 新規作成 仕様変更・モデルNo.756
*/
package webbroker3.aio.define;

/**
 * 申込状況 定数定義インタフェイス
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public interface WEB3ApplyStateDivDef
{
    /**
     * 0：申込
     */
    public static final String APPLY = "0";

    /**
     * 1：開設
     */
    public static final String ACCOUNT_OPEN = "1";

    /**
     *  2：成約
     */
    public static final String EXECUTE = "2";

    /**
     *  3：解約
     */
    public static final String CANCEL = "3";

    /**
     *  4：閉鎖
     */
    public static final String CLOSE = "4";
}@
