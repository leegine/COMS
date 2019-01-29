head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	Web3AccOpenSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査待ちソートキー名(Web3AccOpenSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/12 黄建 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 口座開設審査待ちソートキー名
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface Web3AccOpenSortKeyDef
{
    /**
     * 口座開設審査待ち.作成日時
     */
    public static final String CREATED_TIMESTAMP = "created_timestamp";

    /**
     * 口座開設審査待ち.識別コード
     */
    public static final String ACC_OPEN_REQUEST_NUMBER = "acc_open_request_number";
    
    /**
     * 口座開設審査待ち.通番
     */
    public static final String SERIAL_NO = "serial_no"; 
}
@
