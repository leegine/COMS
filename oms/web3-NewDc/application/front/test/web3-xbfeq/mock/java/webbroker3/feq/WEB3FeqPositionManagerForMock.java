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
     * (update�g�����U�N�V����) <BR>
     * �萔�����v�Z�i������j�����{���A <BR>
     * �g�����U�N�V�����f�[�^���X�V����B <BR>
     * <BR>
     * �O�������|�W�V�����w���p�[.update�g�����U�N�V����()�� <BR>
     * �Ϗ��ideligate�j����B<BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)
     * @@param l_blnIsCancel - (is���) <BR>
     * ��������̔��� <BR>
     *  <BR>
     * ture�F����� <BR>
     * false�F��� <BR>
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
