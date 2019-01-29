head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3MutualFundBizLogicProviderForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/11 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundBizLogicProviderForMock extends WEB3MutualFundBizLogicProvider
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundBizLogicProviderForMock.class);
    /**
     * (calc�T�Z���t����)
     * �抷������̊T�Z���t�����̌v�Z���s���B<BR>
     * <BR>
     * @@param l_mfProduct - ����
     * @@param l_dblOrderQuantity - ��������
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public double calcEstimatedBuyQty(
        WEB3MutualFundProduct l_mfProduct, 
        double l_dblOrderQuantity)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty(WEB3MutualFundProduct, double)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundBizLogicProvider",
            "calcEstimatedBuyQty",
            new Class[] {WEB3MutualFundProduct.class, double.class},
            new Object[]{l_mfProduct, new Double(l_dblOrderQuantity)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundBizLogicProvider",
            "calcEstimatedBuyQty",
            new Class[] {WEB3MutualFundProduct.class, double.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                "calcEstimatedBuyQty",
                new Class[] {WEB3MutualFundProduct.class, double.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            return (double)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                "calcEstimatedBuyQty",
                new Class[] {WEB3MutualFundProduct.class, double.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimatedBuyQty(l_mfProduct,l_dblOrderQuantity);
    }
}
@
