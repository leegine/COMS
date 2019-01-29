head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundPositionManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3MutualFundPositionManagerForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundPositionManagerForMock extends WEB3MutualFundPositionManager
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFundPositionManagerForMock.class);

    /**
     * (Mock)
     * getAssets�̃I�[�o�[���[�h<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - (���M��O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getAssets(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            new Object[]{l_subAccount, l_strMutualFrgnMmfDisplayDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAssets",
                new Class[] {SubAccount.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            return (List)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAssets",
                new Class[] {SubAccount.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
    }

    /**
     * (calc���\�c������(Mock))<BR>
     * ���Y�ڋq�ۗ̕L����A���݉��\�ȓ����M���̉��\�c��������Ԃ��B<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     */
    public double calcSellPossiblePositionQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcSellPossiblePositionQty(SubAccount, WEB3MutualFundProduct, String))-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellPossiblePositionQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellPossiblePositionQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcSellPossiblePositionQty(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (calc��񒆊T�Z����(Mock))<BR>
     * ���Y�ڋq�́A���݉�񒆂̊T�Z������Ԃ��B<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_taxType - �ŋ敪
     * @@param l_asset - �ۗL���Y
     * @@return double
     */
    public double calcSellingEstimatedQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        TaxTypeEnum l_taxType, 
        Asset l_asset)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcSellingEstimatedQty(SubAccount, WEB3MutualFundProduct, String, Asset)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellingEstimatedQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, TaxTypeEnum.class, Asset.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_taxType, l_asset});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellingEstimatedQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, TaxTypeEnum.class, Asset.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellingEstimatedQty",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class, Asset.class}).asWEB3BaseException();

            //3)MockFor --�r asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellingEstimatedQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class,
                    TaxTypeEnum.class, Asset.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcSellingEstimatedQty(l_subAccount, l_mutualFundProduct, l_taxType, l_asset);
    }

    /**
     * (calc�]���z(Mock))<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     */
    public double calcMarketValue(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "calcMarketValue(SubAccount ,WEB3MutualFundProduct, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcMarketValue(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (calc�]�����v(Mock))<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     */
    public double calcAppraisalProfitLoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "calcAppraisalProfitLoss(SubAccount , WEB3MutualFundProduct , String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcAppraisalProfitLoss",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcAppraisalProfitLoss",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcAppraisalProfitLoss(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (get�������z��(Mock))<BR>
     * �������z����ԋp����B<BR>
     * <BR>
     * 1). �ȉ��̏����œ��M�ۗL���Y�⏕�e�[�u�����s�I�u�W�F�N�g���擾����B<BR>
     * �@@���YID = ����.���YID <BR>
     * <BR>
     * 2). �擾�����s�I�u�W�F�N�g��get�������z���c�������^�[������B<BR>
     * �@@(�����_�ȉ��؂�̂�)<BR>
     * @@param l_strAssetId - (���YID)<BR>
     * ���YID<BR>
     * @@return long
     * @@throws WEB3BaseException 
     */
    public long getUnreceiveDist(String l_strAssetId, WEB3MutualFundProduct l_mutualFundProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnreceiveDist(String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class,WEB3MutualFundProduct.class},
            new Object[]{l_strAssetId, l_mutualFundProduct});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class,WEB3MutualFundProduct.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getUnreceiveDist",
                new Class[] {String.class, WEB3MutualFundProduct.class}).asWEB3BaseException();

            //3)MockFor --�r asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getUnreceiveDist",
                new Class[] {String.class, WEB3MutualFundProduct.class}).asLong();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getUnreceiveDist(l_strAssetId, l_mutualFundProduct);
    }

    public Asset getAsset(long assetId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getAsset(long)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAsset",
            new Class[] {long.class},
            new Object[]{new Long(assetId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAsset",
            new Class[] {long.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --�r asDouble
            return (Asset)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAsset",
                new Class[] {long.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAsset(assetId);
    }

}
@
