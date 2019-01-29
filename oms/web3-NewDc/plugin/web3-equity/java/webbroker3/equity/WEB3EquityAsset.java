head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAsset.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保有資産クラス(WEB3EquityAsset.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 中尾　@寿彦(SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAssetUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

/**
 * （保有資産）。<BR>
 * <BR>
 * 約定ベースの残高を表現する。<BR>
 * 取得単位に関係なく、グロスで保持する。<BR>
 * xTradeのEqTypeAssetを拡張したクラス。
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3EquityAsset extends EqTypeAssetImpl
{

    /**
     * (コンストラクタ。)<BR>
     *<BR> 
     * @@param l_assetId 資産ID
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3EquityAsset(long l_lngAssetId) throws DataQueryException, DataNetworkException
    {
        super(l_lngAssetId);
    }

    /**
     * (コンストラクタ。)<BR>
     *<BR> 
     * @@param l_row EqtypeAssetRowオブジェクト
     */
    public WEB3EquityAsset(AssetRow l_row)
    {
        super(l_row);
    }

    protected EqTypeAssetUnit toAssetUnit(Row l_row)
    {
        return null;
    }
}
@
