head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StorageStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3StorageStopDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 入出庫停止区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3StorageStopDivDef
{
    /**
     * 0 : 入出庫可能
     */
    public static final String STORAGE_POSSIBLE = "0";

    /**
     * 1 : 入出庫停止
     */
    public static final String STORAGE_STOP = "1";
    
    /**
     * 2 : 入庫停止
     */
    public static final String STORAGE_IN_STOP = "2";
    
    /**
     * 3 : 出庫停止
     */
    public static final String STORAGE_OUT_STOP = "3";

}
@
