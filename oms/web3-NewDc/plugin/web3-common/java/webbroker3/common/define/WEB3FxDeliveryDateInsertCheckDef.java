head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxDeliveryDateInsertCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX：受渡日取得チェック定数定義インタフェイス(WEB3FxDeliveryDateInsertCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/24 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * FX：受渡日取得チェック 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3FxDeliveryDateInsertCheckDef
{

    /**
     * 0：受渡日をセットしない。
     */
    public final static String DEFAULT = "0";

    /**
     * 1：受渡日をセットする。
     */
    public final static String CHECK = "1";  
} @
