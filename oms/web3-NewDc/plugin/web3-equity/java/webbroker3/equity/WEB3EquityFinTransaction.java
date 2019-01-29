head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFinTransaction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式顧客勘定クラス(WEB3EquityFinTransaction.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 中尾　@寿彦(SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionImpl;

/**
 * （株式顧客勘定）。<BR>
 * <BR>
 * 約定異動明細を表現する。<BR>
 * xTradeのEqTypeFinTransactionを拡張したクラス。
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3EquityFinTransaction extends EqTypeFinTransactionImpl
{

    /**
     * (コンストラクタ。)<BR>
     *<BR> 
     * @@param l_lngFinTransactionId
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3EquityFinTransaction(long l_lngFinTransactionId)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngFinTransactionId);
    }

    /**
     * (コンストラクタ。)<BR>
     *<BR>
     * @@param l_row EqtypeFinTransactionRowオブジェクト
     */
    public WEB3EquityFinTransaction(EqtypeFinTransactionRow l_row)
    {
        super(l_row);
    }
}
@
