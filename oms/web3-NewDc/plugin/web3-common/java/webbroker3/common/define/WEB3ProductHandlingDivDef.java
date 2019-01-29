head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProductHandlingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品取扱区分定数定義インタフェイス(WEB3ProductHandlingDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 栄イ(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * トリガー発行情報テーブルの商品取扱区分定数定義インタフェイス
 * 
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ProductHandlingDivDef
{
    /**
     * 0:取扱有り
     */
    public static final String HANDLING = "0";

    /**
     * 1:取扱なし
     */
    public static final String NON_HANDLING = "1";
}
@
