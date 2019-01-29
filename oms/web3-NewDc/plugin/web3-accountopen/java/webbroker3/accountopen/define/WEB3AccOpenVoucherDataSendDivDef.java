head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherDataSendDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票データ送信区分定数定義(WEB3AccOpenVoucherDataSendDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/01 何文敏 (中訊) 新規作成 仕様変更・モデルNo.136
*/
package webbroker3.accountopen.define;

/**
 * 伝票データ送信区分定数定義
 *
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherDataSendDivDef
{
    /**
     *  1：口座開設データ送信
     */
    public static final String ACC_OPEN_DATA_SEND = "1";

}
@
