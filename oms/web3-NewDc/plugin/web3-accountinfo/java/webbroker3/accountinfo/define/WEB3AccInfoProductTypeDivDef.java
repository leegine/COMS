head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoProductTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄タイプ区分定数定義インタフェイス(WEB3AccInfoProductTypeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 劉江涛(sinocom) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 銘柄タイプ区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3AccInfoProductTypeDivDef
{

    /**
     * 1：　@株式（Equity）　@　@
     */
    public final static String EQUITY = "1";

    /**
     * 6：　@株価指数先物／オプション（Ifo）　@ 　@　@　@ 　@　@
     */
    public final static String IFO = "6";
    
}@
