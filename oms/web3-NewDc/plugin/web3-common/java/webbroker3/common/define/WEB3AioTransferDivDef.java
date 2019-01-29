head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替区分定数定義インタフェイス(WEB3AioTransferDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/12 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 振替区分定数定義インタフェイス<BR>
 * (FX振替条件マスタの振替区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AioTransferDivDef
{
    /**
     * 0:入金
     */
    public static final String CASHIN = "0";

    /**
     * 1：出金
     */
    public static final String CASHOUT = "1";
}@
