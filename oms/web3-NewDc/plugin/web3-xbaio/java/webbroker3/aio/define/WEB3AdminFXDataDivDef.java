head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXDataDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : データ区分　@定数定義(WEB3AdminFXDataDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/11 周捷 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * データ区分  定数定義インタフェイス
 * <BR>
 * @@author 周捷
 * @@version 1.0
 */

public class WEB3AdminFXDataDivDef 
{
    /**
     * 1 : ヘッダーレコード件数の場合データ区分
     */
    public static final String HEADER_RECORD_COUNT = "1";
    
    /**
     * 2 : データレコード件数の場合データ区分
     */
    public static final String DATA_RECORD_COUNT = "2";
    
    /**
     * 8 : トレーラーレコード件数の場合データ区分
     */
    public static final String TRAILER_RECORD_COUNT = "8";
    
    /**
     * 9 : エンドレコード件数の場合データ区分
     */
    public static final String END_RECORD_COUNT = "9";
    
}
@
