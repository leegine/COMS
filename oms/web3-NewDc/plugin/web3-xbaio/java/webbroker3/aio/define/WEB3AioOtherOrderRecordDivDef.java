head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOtherOrderRecordDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioOtherOrderRecordDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * その他用注文件数テーブルのレコード区分　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */

public interface WEB3AioOtherOrderRecordDivDef
{
    /**
     * 0：日次明細 
     */
    public static final String DAILY_DETAIL = "0";
    
    /**
     * 1：月次合計
     */
    public static final String MONTHLY_COUNT = "1";
}
@
