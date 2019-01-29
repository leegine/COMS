head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAgreementDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約諾書区分定数定義インタフェイス(WEB3AioAgreementDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/08 譚漢江 (中訊) 新規作成
                   2006/2/23 玉岡(SRA) 仕様変更・モデル499
*/

package webbroker3.aio.define;

/**
 * 約諾書区分定数定義インタフェイス
 *                                                                     
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3AioAgreementDivDef
{
    /**
     * 0：未送信
     */
    public static final String NOT_SEND = "0";

    /**
     * 1：送信済
     */
    public static final String SENDED = "1";

    /**
     * 2：受領済
     */
    public static final String RECIEVED = "2";

    /**
     * null：全て
     */
    public static final String FULL = null;

}
@
