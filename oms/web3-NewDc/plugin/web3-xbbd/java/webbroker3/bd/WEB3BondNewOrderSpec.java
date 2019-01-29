head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����V�K�������e(WEB3BondNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
Revesion History : 2007/7/26 ������ (���u) �d�l�ύX�E���f��No.234
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondNewOrderSpec;

/**
 * (�g�����V�K�������e)<BR>
 * �g�����V�K�������e�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3BondNewOrderSpec extends BondNewOrderSpec
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondNewOrderSpec.class);    
    
    /**
     * (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     */
    private WEB3BondOrderTypeJudge bondOrderTypeJudge;
    
    /**
     * (���ϋ敪)<BR>
     */
    private String settlementDiv;
    
    /**
     * WEB3BondNewOrderSpec<BR>
     * <BR>
     * @@param l_trader - (�I�y���[�^)<BR>
     * �I�y���[�^<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_blnIsSellOrder -is���p����<BR>
     * @@param l_strIssueCode - (�񍆃R�[�h)<BR>
     * �񍆃R�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h�iWEB3�j)<BR>
     * �����R�[�h�iWEB3�j<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_dblPrice - (�P��)<BR>
     * �P��<BR>
     * @@param l_execType - BondExecutionConditionType
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44C6F09D011E
     */
    public WEB3BondNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsSellOrder, 
        String l_strIssueCode,
        String l_strProductCode,
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblPrice,
        BondExecutionConditionType l_execType,
        Date l_datDeliveryDate, 
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader, 
            l_blnIsSellOrder, 
            l_strIssueCode,
            l_strProductCode,
            l_strMarketCode,
            l_dblQuantity, 
            l_dblPrice,
            l_execType,
            l_datDeliveryDate,
            l_taxType);
    }
    
    /**
     * (create�g�����V�K�������e)<BR>
     * create�g�����V�K�������e<BR>
     * <BR>
     * �P�j�g�����V�K�������e�𐶐�����<BR>
     *  �R���X�g���N�^�i����.�I�y���[�^,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���.is���p���� ? false : true,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"0",  //�񍆃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�����R�[�h�iWEB3�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"0",  //�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.����,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�P��,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@BondExecutionConditionType.NONE,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.��n��, //������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�ŋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�j<BR>
     * <BR>
     * �Q�j�g�����V�K�������e�Ɉȉ��̑������Z�b�g����<BR>
     * �@@�@@�E��������ʔ���<BR>
     * �@@�@@�E���ϋ敪<BR>
     * <BR>
     * �R�j�g�����V�K�������e��Ԃ�<BR>
     * <BR>
     * @@param l_trader - (�I�y���[�^)<BR>
     * �I�y���[�^<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_strProductCode - (�����R�[�h�iWEB3�j)<BR>
     * �����R�[�h(WEB3)<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_dblPrice - (�P��)<BR>
     * �P��<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_strSettlementDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44C6F09D011E
     */
    public static WEB3BondNewOrderSpec createBondNewOrderSpec(
        Trader l_trader, 
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        String l_strProductCode,
        double l_dblQuantity,
        double l_dblPrice,
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate,
        String l_strSettlementDiv) 
    {
        final String STR_METHOD_NAME = 
            "createBondNewOrderSpec(Trader, WEB3BondOrderTypeJudge, String, double, double, TaxTypeEnum, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�g�����V�K�������e�𐶐�����
        WEB3BondNewOrderSpec l_bondNewOrderSpec = new WEB3BondNewOrderSpec(
            l_trader, 
            l_bondOrderTypeJudge.isSellOrder()? false : true, 
            "0", 
            l_strProductCode, 
            "0",
            l_dblQuantity,
            l_dblPrice,
            BondExecutionConditionType.NONE,
            l_datDeliveryDate,
            l_taxType);
        
        //�Q�j�g�����V�K�������e�Ɉȉ��̑������Z�b�g����
        l_bondNewOrderSpec.bondOrderTypeJudge = l_bondOrderTypeJudge;
        l_bondNewOrderSpec.settlementDiv = l_strSettlementDiv;
        
        //�R�j�g�����V�K�������e��Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_bondNewOrderSpec;
    }
    
    /**
     * (get��������ʔ���)<BR>
     * get��������ʔ���<BR>
     * <BR>
     * ��������ʔ����Ԃ�<BR>
     * @@return webbroker3.bd.WEB3BondOrderTypeJudge
     * @@roseuid 44D94EB603A9
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() 
    {
        return this.bondOrderTypeJudge;
    }
    
    /**
     * (get���ϋ敪)<BR>
     * get���ϋ敪<BR>
     * <BR>
     * ���ϋ敪��Ԃ�<BR>
     * @@return String
     * @@roseuid 44D94EDE005D
     */
    public String getSettlementDiv() 
    {
        return this.settlementDiv;
    }

    /**
     * (create�g�����V�K�������e<������>)<BR>
     * create�g�����V�K�������e<������><BR>
     * <BR>
     * �P�j�g�����V�K�������e�𐶐�����<BR>
     *  �R���X�g���N�^�i����.�I�y���[�^,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@true, //is���t <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"0",  //�񍆃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.������.�����R�[�h�iWEB3�j, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"0",  //�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.����,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.������.���t�P��, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@BondExecutionConditionType.NONE,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.������.��n��, //������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@TaxTypeEnum.���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�j<BR>
     * <BR>
     * �Q�j�g�����V�K�������e�Ɉȉ��̓��e���Z�b�g���� <BR>
     * �@@�@@�E��������ʔ���F����.��������ʔ���<BR>
     * �@@�@@�E���ϋ敪�F�u�P�F�~�݁v<BR>
     * <BR>
     * �R�j�g�����V�K�������e��Ԃ�<BR>
     * <BR>
     * @@param l_trader - (�I�y���[�^)<BR>
     * �I�y���[�^<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_bdQuantity - (����)<BR>
     * ����<BR>
     * @@return WEB3BondNewOrderSpec
     */
    public static WEB3BondNewOrderSpec createBondNewOrderSpecDomesticBond(
        Trader l_trader,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
        WEB3BondProduct l_bondProduct,
        BigDecimal l_bdQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createBondNewOrderSpecDomesticBond(Trader, WEB3BondOrderTypeJudge, WEB3BondProduct, BigDecimal)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null || l_bdQuantity == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondNewOrderSpec.class.getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�g�����V�K�������e�𐶐�����
        WEB3BondNewOrderSpec l_bondNewOrderSpec = new WEB3BondNewOrderSpec(
            l_trader,
            true,
            String.valueOf(0),
            l_bondProduct.getProductCode(),
            WEB3MarketCodeDef.DEFAULT,
            l_bdQuantity.doubleValue(),
            l_bondProduct.getBuyPrice(),
            BondExecutionConditionType.NONE,
            l_bondProduct.getDeliveryDate(),
            TaxTypeEnum.NORMAL);

        //�Q�j�g�����V�K�������e�Ɉȉ��̓��e���Z�b�g����
        l_bondNewOrderSpec.bondOrderTypeJudge = l_bondOrderTypeJudge;
        l_bondNewOrderSpec.settlementDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;

        //�R�j�g�����V�K�������e��Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_bondNewOrderSpec;
    }
}
@
