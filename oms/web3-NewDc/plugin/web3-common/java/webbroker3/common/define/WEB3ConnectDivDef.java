head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ConnectDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 接続区分定数定義インタフェイス(WEB3ConnectDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/23 栄イ(中訊)　@新規作成
Revision History : 2008/09/11 陸文静 (中訊) 仕様変更ＤＢレイアウトNo.643
*/
package webbroker3.common.define;

/**
 * 外部システムSOAP接続プリファ@レンス（RPC形式）の接続区分定数定義インタフェイス
 * 
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ConnectDivDef
{
    /**
     * 01：GFT
     */
    public static final String GFT = "01";

    /**
     * 07：TFX
     */
    public static final String TFX = "07";
}
@
