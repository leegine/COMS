head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteDataQueryParamsSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報の検索条件を表すインターフェース(QuoteQueryParamsSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.prototype;

import com.fitechlabs.xtrade.kernel.data.DataException;

import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * 時価情報の検索条件を表すインターフェース<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
interface QuoteDataQueryParamsSpec
{
    
    /**
     * SQLのwhere句を表す文字列を取得する。
     * 
     * @@return SQLのwhere句を表す文字列
     */
    public String getQueryString();
    
    /**
     * SQLにバインドするオブジェクトの配列を取得する。
     * 
     * @@return SQLにバインドするオブジェクトの配列を取得する。
     */
    public Object[] getBindVars();
    
    /**
     * 新しいWeb3QuoteProtoRowのインスタンスを生成する。
     * 
     * @@return Web3QuoteProtoRowのインスタンス
     */
    public Web3QuoteProtoRow newWeb3QuoteProtoRow() throws DataException;

}
@
