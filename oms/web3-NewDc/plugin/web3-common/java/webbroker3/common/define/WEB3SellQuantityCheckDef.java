head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SellQuantityCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買付最低金額チェック定数定義インタフェイス(WEB3SellQuantityCheckDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/07 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 株式売却可能残判定方法@定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3SellQuantityCheckDef
{
    /**
     * 0：機@構保管かつ単位本人・単位他人（入庫積送中は除く）
     */
    public static final String DEFAULT = "0";

    /**
     * 1：DEFAULTに加え、集中保管分も追加
     */
    public static final String CENTRAL_CUSTODY = "1";
}
@
