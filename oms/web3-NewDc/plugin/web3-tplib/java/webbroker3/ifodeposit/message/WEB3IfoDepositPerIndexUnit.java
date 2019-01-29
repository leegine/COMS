head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPerIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 指数別証拠金クラス(WEB3IfoDepositPerIndexUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/03 有山　@祥子(SRA) 新規作成
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (指数別証拠金)<BR>
 * 指数別証拠金クラス。<BR>
 * 
 * @@author Shoko Ariyama (SRA)
 */
public class WEB3IfoDepositPerIndexUnit extends Message
{

    /**
     * (原資産銘柄コード)<BR>
     * 原資産銘柄コード。<BR>
     */
    public String targetProductCode;

    /**
     * (規定証拠金)<BR>
     * 規定証拠金。<BR>
     */
    public String regIfoDeposit;

    /**
     * コンストラクタ。
     */
    public WEB3IfoDepositPerIndexUnit()
    {

    }
}
@
