head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformVoucherDataSendDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票データ送信区分定数定義(WEB3InformVoucherDataSendDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/07 周墨洋 (中訊) 新規作成 モデルNo.074
*/
package webbroker3.inform.define;

/**
 * 伝票データ送信区分定数定義
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public interface WEB3InformVoucherDataSendDivDef
{
    /**
     *  ２：伝票データ送信区分.連絡管理データ送信
     */
    public static final String INFORM_DATA_SEND = "2";

}@
