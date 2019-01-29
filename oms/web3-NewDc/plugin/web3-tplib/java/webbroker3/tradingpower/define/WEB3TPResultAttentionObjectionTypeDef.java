head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPResultAttentionObjectionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引余力結果.注意文言表示区分Def(WEB3TPResultAttentionObjectionTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/06/21 nakazato(DIR-ST) 新規作成
 */

package webbroker3.tradingpower.define;

/**
 * (取引余力結果.注意文言表示区分Def)
 */
public interface WEB3TPResultAttentionObjectionTypeDef
{

    /**
     * [1：現金不足注意文言表示] <BR>
     */
    public final static String LACK_CASH_AMT_ATTENTION = "1";

    /**
     * [2：増担保規制注意文言表示] <BR>
     */
    public final static String INC_DEPOSIT_REG_ATTENTION = "2";

    /**
     * [3：預り金不足注意文言表示] <BR>
     */
    public final static String LACK_ACC_BAL_ATTENTION = "3";
}@
