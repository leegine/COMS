head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RegistDivisionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録区分定数定義クラス(WEB3RegistDivisionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 和田　@友一(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 登録区分の定数を定義する。
 *
 * @@author 和田　@友一(SRA)
 * @@version 1.0
 */
public interface WEB3RegistDivisionDef
{
    /**
     * 0：注文繰越可能(注文繰越スキップ取消)
     */
    public final static String ORDER_TRANSFER_ACTIVE = "0";

    /**
     * 1：注文繰越停止(注文繰越スキップ)
     */
    public final static String ORDER_TRANSFER_STOP = "1";
}
@
