head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqPositionManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;

public class WEB3FeqPositionManagerForMock extends WEB3FeqPositionManager
{
    /**
     * (updateトランザクション) <BR>
     * 手数料按分計算（一口約定）を実施し、 <BR>
     * トランザクションデータを更新する。 <BR>
     * <BR>
     * 外国株式ポジションヘルパー.updateトランザクション()に <BR>
     * 委譲（deligate）する。<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)
     * @@param l_blnIsCancel - (is取消) <BR>
     * 約定取消かの判定 <BR>
     *  <BR>
     * ture：約定取消 <BR>
     * false：約定 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A808210269
     */
    public void updateTransaction(long l_lngOrderUnitId, boolean l_blnIsCancel) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqPositionManager",
            "updateTransaction",
            new Class[] {long.class, boolean.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqPositionManager",
                "updateTransaction",
                new Class[] {long.class, boolean.class}).asObject();
            return;
        }
        super.updateTransaction(l_lngOrderUnitId, l_blnIsCancel);
    }
}
@
