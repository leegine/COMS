head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : コンテキスト情報ベースクラス(WEB3RlsMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * コンテキスト情報ベースクラス
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public abstract class WEB3RlsMessageContext
{
    /**
     * 補助口座
     */
    private SubAccount subAccount;

    /**
     * 条件付注文タイプ
     */
    private int conOrderType;

    /**
     * 補助口座を取得する
     */
    public SubAccount getSubAccount()
    {
        return subAccount;
    }

    /**
     * 補助口座を設定する
     */
    public void setSubAccount(SubAccount l_SubAccount)
    {
        subAccount = l_SubAccount;
    }

    /**
     * 条件付注文タイプ
     */
    public int getConOrderType()
    {
        return conOrderType;
    }

    /**
     * 条件付注文タイプを設定する
     */
    public void setConOrderType(int l_intConOrderType)
    {
        conOrderType = l_intConOrderType;
    }

    /**
     * WEB3RlsMessageContext
     */
    public WEB3RlsMessageContext(SubAccount l_SubAccount,
                                 int l_intConOrderType)
    {
        subAccount = l_SubAccount;
        conOrderType = l_intConOrderType;
    }

}
@
