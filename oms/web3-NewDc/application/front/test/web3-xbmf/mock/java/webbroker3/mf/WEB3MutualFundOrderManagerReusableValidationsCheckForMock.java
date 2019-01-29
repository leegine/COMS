head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerReusableValidationsCheckForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������R���ʃ`�F�b�NForMock(WEB3MutualFundOrderManagerReusableValidationsCheckForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������R���ʃ`�F�b�NForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerReusableValidationsCheckForMock extends
    WEB3MutualFundOrderManagerReusableValidationsCheck
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundOrderManagerReusableValidationsCheckForMock.class);

    /**
     * (validate�ً}��~(Mock))<BR>
     * @@param l_mutualFundProduct - �g�����M����<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����@@5�F��W<BR>
     * @@throws WEB3BaseException
     */
    public void validateEmergencyStop(
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEmergencyStop(WEB3MutualFundProduct ,String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateEmergencyStop",
            new Class[] {WEB3MutualFundProduct.class, String.class},
            new Object[]{l_mutualFundProduct, l_strProcessDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateEmergencyStop",
            new Class[] {WEB3MutualFundProduct.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
    }

    /**
     * (validate�O��MMF��d����(Mock))<BR>
     * ���Y�ڋq�ŁA���łɊO��MMF�̒��������݂��邩�`�F�b�N����B<BR>
     * @@param l_subAccunt - �⏕����<BR>
     * �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * �g�����M����<BR>
     * @@param l_datBizDate - ������<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFrgnMmfDoubleOrder(
        SubAccount l_subAccunt,
        WEB3MutualFundProduct l_mfProduct,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFrgnMmfDoubleOrder(SubAccount, WEB3MutualFundProduct, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateFrgnMmfDoubleOrder",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
            new Object[]{l_subAccunt, l_mfProduct, l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateFrgnMmfDoubleOrder",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateFrgnMmfDoubleOrder(l_subAccunt, l_mfProduct, l_datBizDate);
    }

    /**
     * ���N���X�̃C���X�^���X��o�^����static���\�b�h�B<BR>
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()-->ForMock";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFundOrderManagerReusableValidationsCheckForMock.setInstance(
            new WEB3MutualFundOrderManagerReusableValidationsCheckForMock());
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is�������S��������(Mock))<BR>
     * @@param l_subAccunt - �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * @@param l_swtProduct - �����i�抷��j<BR>
     * @@param l_strAssetId - ���YID<BR>
     * @@param l_dblSellPossQty - ���\�c������<BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_strSpecifyMethod - �w����@@<BR>
     * @@param l_strSettleMethod - ���ϕ��@@<BR>
     * @@param l_strRequestMethod - �������@@<BR>
     * @@param l_strAccountDiv - �����敪<BR>
     * @@param l_datBizDate - ������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isSellQtyLimitRateExcess(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strAssetId, 
        double l_dblSellPossQty, 
        String l_strProcessDiv, 
        double l_dblQuantity, 
        String l_strSpecifyMethod, 
        String l_strSettleMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isSellQtyLimitRateExcess(SubAccount, WEB3MutualFundProduct, " +
            "WEB3MutualFundProduct, String, double, String, double, " +
            "String, String, String, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "isSellQtyLimitRateExcess",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class},
            new Object[]{
                l_mfProduct, 
                l_swtProduct, 
                l_strAssetId, 
                new Double(l_dblSellPossQty), 
                l_strProcessDiv, 
                new Double(l_dblQuantity), 
                l_strSpecifyMethod, 
                l_strSettleMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "isSellQtyLimitRateExcess",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "isSellQtyLimitRateExcess",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "isSellQtyLimitRateExcess",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isSellQtyLimitRateExcess(
            l_subAccunt, 
            l_mfProduct, 
            l_swtProduct, 
            l_strAssetId, 
            l_dblSellPossQty, 
            l_strProcessDiv, 
            l_dblQuantity, 
            l_strSpecifyMethod, 
            l_strSettleMethod, 
            l_strRequestMethod, 
            l_strAccountDiv, 
            l_datBizDate);
    }
}
@
