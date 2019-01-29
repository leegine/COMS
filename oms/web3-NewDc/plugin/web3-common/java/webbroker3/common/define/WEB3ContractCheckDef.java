head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ContractCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ContractCheckDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 li-qiang(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 建玉チェックFLAG　@定数定義インタフェイス
 *
 * @@author 
 * @@version 1.0
 */
public interface WEB3ContractCheckDef
{
    /**
     * 5 : 日計り
     */
    public static final String DAY_TRADE = "5";

    /**
     * 1 : 建玉チェック
     */
    public static final String CONTRACT_CHECK = "1";

}@
