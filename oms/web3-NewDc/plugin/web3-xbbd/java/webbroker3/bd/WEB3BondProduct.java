head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ������(WEB3BondProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
                    2006/10/08 ���� (���u) �d�l�ύX�E���f��107�A110�A115
                    2006/10/10 �ęԍg (���u) �d�l�ύX�E���f��121
                    2007/02/08 �����Q (���u) �d�l�ύX�E���f��158
 Revesion History : 2007/07/12 �đo�g (���u) �d�l�ύX�E���f��204
 Revesion History : 2007/07/26 �Ӑ� (���u) �d�l�ύX�E���f��229
 Revesion History : 2008/08/13 �g�C�� (���u) �d�l�ύX�E���f��260
 Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�E���f��262
 */

package webbroker3.bd;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductImpl;

import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3PaymentDateDetDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SpecialPaymentDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3YearlyInterestPaymentsDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (������)<BR>
 * �������N���X<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondProduct extends BondProductImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondProduct.class);

    /**
     * (������)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * super(������Row)���R�[������B <BR>
     * @@param l_bondProductRow - ������Row<BR>
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@roseuid 44C44D7A028B
     */
    public WEB3BondProduct(BondProductRow l_bondProductRow) throws DataQueryException, DataNetworkException
    {
        super(l_bondProductRow);
    }

    /**
     * (������)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * super(����Row)���R�[������<BR>
     * @@param l_productRow - ����Row<BR>
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@roseuid 44C44D7A029B
     */
    public WEB3BondProduct(ProductRow l_productRow) throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (get�����R�[�h(SONAR))<BR>
     * �����R�[�h(SONAR)��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����R�[�h(SONAR)()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CEEEC901C7
     */
    public String getHostProductCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductCod = l_bondProduct.getHostProductCode();
        return l_strHostProductCod;
    }

    /**
     * (get�񍆃R�[�h(SONAR))<BR>
     * �񍆃R�[�h(SONAR)��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�񍆃R�[�h(SONAR)()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CEEEFC03CC
     */
    public String getHostProductIssueCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strProductIssueCode = l_bondProduct.getHostProductIssueCode();
        return l_strProductIssueCode;
    }

    /**
     * (get���s�z)<BR>
     * ���̔��s�z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���s�z()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A03C6
     */
    public double getIssueAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double  l_dblIssueAmount = l_bondProduct.getIssueAmount();
        return l_dblIssueAmount;
    }

    /**
     * (get�������P)<BR>
     * �������P��Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().get�������P()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CEEFA8021C
     */
    public String getInterestPaymentDay1()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strPayDay = l_bondProduct.getInterestPaymentDay1st();
        return l_strPayDay;
    }

    /**
     * (get�������Q)<BR>
     * �������Q��Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().get�������Q()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CEEFD40327
     */
    public String getInterestPaymentDay2()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strPayDay = l_bondProduct.getInterestPaymentDay2nd();
        return l_strPayDay;
    }

    /**
     * (get����J�n���iSONAR�j)<BR>
     * ����J�n���iSONAR�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().getSONAR����J�n��()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CC
     */
    public Date getHostRecruitStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitStart = l_bondProduct.getHostRecruitStartDate();
        return l_datRecruitStart;
    }

    /**
     * (get����I�����iSONAR�j)<BR>
     * ����I�����iSONAR�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get����I�����iSONAR�j()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02D9
     */
    public Date getHostRecruitEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitEnd = l_bondProduct.getHostRecruitEndDate();
        return l_datRecruitEnd;
    }

    /**
     * (get�戵�敪)<BR>
     * �戵��ʂ�Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�戵���()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A029F
     */
    public String getTradeHandleDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeHandleDiv = l_bondProduct.getTradeHandleDiv();
        return l_strTradeHandleDiv;
    }

    /**
     * (get�戵�J�n����)<BR>
     * �戵�J�n������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�戵�J�n����()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02AA
     */
    public Date getTradeStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datTradeStartDate = l_bondProduct.getTradeStartDate();
        return l_datTradeStartDate;
    }

    /**
     * (get�戵�I������)<BR>
     * �戵�I��������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�戵�I������()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02BA
     */
    public Date getTradeEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datTradeEndDate = l_bondProduct.getTradeEndDate();
        return l_datTradeEndDate;
    }

    /**
     * (get����J�n��)<BR>
     * ����J�n����Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get����J�n��()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CA
     */
    public Date getRecruitStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
        return l_datRecruitStartDate;
    }

    /**
     * (get����I����)<BR>
     * ����I������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get����I����()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CB
     */
    public Date getRecruitEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
        return l_datRecruitEndDate;
    }

    /**
     * (get�����敪)<BR>
     * �����敪��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�����敪�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A02DA
     */
    public String getTradeType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();
        return l_strTradeType;
    }

    /**
     * (get������)<BR>
     * this.getDataSourceObject().��������ԋp����B<BR>
     * @@return String
     * @@roseuid 421C51300342
     */
    public String getProductName()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strProductName = l_bondProduct.getProductName();
        return l_strProductName;
    }

    /**
     * (get���t�P��)<BR>
     * ���t(�P��)��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���t(�P��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0318
     */
    public double getBuyPrice()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblBuyPrice = l_bondProduct.getBuyPrice();
        return l_dblBuyPrice;
    }

    /**
     * (get���p�P��)<BR>
     * ���p�P����Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���p�P��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0319
     */
    public double getSellPrice()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblSellPrice = l_bondProduct.getSellPrice();
        return l_dblSellPrice;
    }

    /**
     * (get�\���P��)<BR>
     * �\���P�ʂ�Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�\���P��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A031A
     */
    public double getTradeUnit()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblTradeUnit = l_bondProduct.getTradeUnit();
        return l_dblTradeUnit;
    }

    /**
     * (get�Œ�z��)<BR>
     * �Œ�z�ʂ�Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�z��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0327
     */
    public double getMinFaceAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMinFaceAmount = l_bondProduct.getMinFaceAmount();
        return l_dblMinFaceAmount;
    }

    /**
     * (get�ō��z��)<BR>
     * �ō��z�ʂ�Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�ō��z��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0328
     */
    public double getMaxFaceAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMaxFaceAmount = l_bondProduct.getMaxFaceAmount();
        return l_dblMaxFaceAmount;
    }

    /**
     * (get�J�����_�[�A�g�s��R�[�h)<BR>
     * �J�����_�[�A�g�s��R�[�h��ԋp����<BR>
     * <BR>
     * this.getDataSourceObject().get�J�����_�[�A�g�s��R�[�h�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0329
     */
    public String getCalLinkedMarketCode()
    {
        BondProductRow l_bondProductRow =  (BondProductRow)getDataSourceObject();
        String l_strCalLinkedMarketCode = l_bondProductRow.getCalLinkedMarketCode();
        return l_strCalLinkedMarketCode;
    }

    /**
     * (get���t��n���ړ�����)<BR>
     * ���t��n���ړ�������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���t��n���ړ�����()�̖߂�l��Ԃ��B<BR>
     * @@return int
     * @@roseuid 44C44D7A0337
     */
    public int getBuyDeliveryDateShiftDays()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        int l_intBuyDeliveryDateShiftDays = l_bondProduct.getBuyDeliveryDateShiftdays();
        return l_intBuyDeliveryDateShiftDays;
    }

    /**
     * (get���p��n���ړ�����)<BR>
     * ���p��n���ړ�������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���p��n���ړ�����()�̖߂�l��Ԃ��B<BR>
     * @@return int
     * @@roseuid 44C44D7A0338
     */
    public int getSellDeliveryDateShiftDays()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        int l_intSellDeliveryDateShiftDays = l_bondProduct.getSellDeliveryDateShiftdays();
        return l_intSellDeliveryDateShiftDays;
    }

    /**
     * (get�������敪)<BR>
     * �������敪��ԋp�B<BR>
     * <BR>
     * this.getDataSourceObject().get�������敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0348
     */
    public String getAutoExecDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();
        return l_strAutoExecDiv;
    }

    /**
     * (get���ώc��)<BR>
     * ���ώc����Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���ώc��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0356
     */
    public double getAutoExecAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblAutoExecAmount = l_bondProduct.getAutoExecAmount();
        return l_dblAutoExecAmount;
    }

    /**
     * (get�������g)<BR>
     * �������g��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�������g()�̖߂�l��ԋp����B <BR>
     * @@return double
     * @@roseuid 44C44D7A0357
     */
    public double getAutoExecLimit()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblAutoExecLimit = l_bondProduct.getAutoExecLimit();
        return l_dblAutoExecLimit;
    }

    /**
     * (get�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�J�X�g�f�B�A���R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0359
     */
    public String getCustodianCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCustodianCode = l_bondProduct.getCustodianCode();
        return l_strCustodianCode;
    }

    /**
     * (getHOST������1)<BR>
     * HOST������1��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().getHOST������1()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0377
     */
    public String getHostProductName1()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductName1 = l_bondProduct.getHostProductName1();
        return l_strHostProductName1;
    }

    /**
     * (getHOST������2)<BR>
     * HOST������2��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().getHOST������2()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0385
     */
    public String getHostProductName2()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductName2 = l_bondProduct.getHostProductName2();
        return l_strHostProductName2;
    }

    /**
     * (getHOST�ȗ�������)<BR>
     * HOST�ȗ���������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().getHOST�ȗ�������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0376
     */
    public String getHostShortProductName()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostShortProductName = l_bondProduct.getHostShortProductName();
        return l_strHostShortProductName;
    }

    /**
     * (get��ʃR�[�h)<BR>
     * ��ʃR�[�h��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0375
     */
    public String getBondCategCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strBondCategCode = l_bondProduct.getBondCategCode();
        return l_strBondCategCode;
    }

    /**
     * (get�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�ʉ݃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0386
     */
    public String getCurrencyCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCurrencyCode = l_bondProduct.getCurrencyCode();
        return l_strCurrencyCode;
    }

    /**
     * (get���s�s��R�[�h)<BR>
     * ���s�s��R�[�h��Ԃ��B <BR>
     * this.getDataSourceObject().get���s�s��R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0387
     */
    public String getIssueMarketCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strIssueMarketCode = l_bondProduct.getIssueMarketCode();
        return l_strIssueMarketCode;
    }

    /**
     * (get���s�̃R�[�h)<BR>
     * ���s�̃R�[�h��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���s�̃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A0395
     */
    public String getIssueAssociationCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strIssueAssociationCode = l_bondProduct.getIssueAssociationCode();
        return l_strIssueAssociationCode;
    }

    /**
     * (get�o�ߗ��q�v�Z�^�C�v)<BR>
     * �o�ߗ��q�v�Z�^�C�v��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�o�ߗ��q�v�Z�^�C�v()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03A4
     */
    public String getAccruedInterestCalcType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strAccruedInterestCalcType = l_bondProduct.getAccruedInterestCalcType();
        return l_strAccruedInterestCalcType;
    }

    /**
     * (get�o�ߗ��q�N�Z��)<BR>
     * �o�ߗ��q�N�Z����Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�o�ߗ��q�N�Z��()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A03D3
     */
    public Date getAccruedInterestStartDay()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datAccruedInterestStartDay = l_bondProduct.getAccruedInterestStartDay();
        return l_datAccruedInterestStartDay;
    }

    /**
     * (get���ꗘ���敪)<BR>
     * ���ꗘ���敪��ԋp<BR>
     * <BR>
     * this.getDataSourceObject().get���ꗘ���敪()�̖߂�l<BR>
     * @@return String
     * @@roseuid 44C44D7A03A5
     */
    public String getSpecialPaymentDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strSpecialPaymentDiv = l_bondProduct.getSpecialPaymentDiv();
        return l_strSpecialPaymentDiv;
    }

    /**
     * (get�t���[�e�B���O���[�g�E�������ԋ敪)<BR>
     * �t���[�e�B���O���[�g�̏ꍇ�A�������ԋ敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�t���[�e�B���O���[�g�E�������ԋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03B4
     */
    public String getFloatingInterestPeriodDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestPeriodDiv = l_bondProduct.getFloatingInterestPeriodDiv();
        return l_strFloatingInterestPeriodDiv;
    }

    /**
     * (get�t���[�e�B���O���[�g.��������)<BR>
     * �t���[�e�B���O���[�g�̏ꍇ�A�������Ԃ�Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�t���[�e�B���O���[�g�E��������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C494A801DB
     */
    public String getFloatingInterestPeriod()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestPeriod = l_bondProduct.getFloatingInterestPeriod();
        return l_strFloatingInterestPeriod;
    }

    /**
     * (get�t���[�e�B���O���[�g�E�������)<BR>
     * �t���[�e�B���O���[�g�̏ꍇ�A������ނ�Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�t���[�e�B���O���[�g�E�������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03B5
     */
    public String getFloatingInterestType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestType = l_bondProduct.getFloatingInterestType();
        return l_strFloatingInterestType;
    }

    /**
     * (get�t���[�e�B���O���[�g�E�����X�v���b�h)<BR>
     * �t���[�e�B���O���[�g�̏ꍇ�A�����X�v���b�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�t���[�e�B���O���[�g�E�����X�v���b�h()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A03C4
     */
    public double getFloatingInterestSpread()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblFloatingInterestSpread = l_bondProduct.getFloatingInterestSpread();
        return l_dblFloatingInterestSpread;
    }

    /**
     * (get�t���[�e�B���O���[�g�E�~�j�}���N�[�|��)<BR>
     * �t���[�e�B���O���[�g�̏ꍇ�A�~�j�}���N�[�|����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�t���[�e�B���O���[�g�E�~�j�}���N�[�|��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A03C5
     */
    public double getFloatingMinCoupon()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblFloatingMinCoupon = l_bondProduct.getFloatingMinCoupon();
        return l_dblFloatingMinCoupon;
    }

    /**
     * (get�Ɛŋ敪)<BR>
     * �Ɛŋ敪��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�Ɛŋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03D4
     */
    public String getTaxFreeDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTaxFreeDiv = l_bondProduct.getTaxFreeDiv();
        return l_strTaxFreeDiv;
    }

    /**
     * (getS&P)<BR>
     * S&P��Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().getS&P()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03D5
     */
    public String getSAndP()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strSAndP = l_bondProduct.getSAndP();
        return l_strSAndP;
    }

    /**
     * (getMoody's)<BR>
     * Moody's��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().getMoodys()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03E3
     */
    public String getMoodys()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strMoodys = l_bondProduct.getMoodys();
        return l_strMoodys;
    }

    /**
     * (getCUSIP)<BR>
     * CUSIP��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().getCUSIP�����R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7A03E4
     */
    public String getCUSIP()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCUSIP = l_bondProduct.getCusip();
        return l_strCUSIP;
    }

    /**
     * (get����萔����)<BR>
     * ����萔������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get����萔����()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C44D7A0347
     */
    public double getMediatorCommissionRate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMediatorCommissionRate = l_bondProduct.getMediatorCommissionRate();
        return l_dblMediatorCommissionRate;
    }

    /**
     * (get�ŏI�X�V�҃R�[�h)<BR>
     * �ŏI�X�V�҃R�[�h��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�ŏI�X�V�҃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44C44D7B000A
     */
    public String getLastUpdater()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strLastUpdater = l_bondProduct.getLastUpdater();
        return l_strLastUpdater;
    }

    /**
     * (get�Ǘ��ҍX�V���t)<BR>
     * �Ǘ��ҍX�V���t��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�Ǘ��ҍX�V���t()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C452B0011F
     */
    public Date getAdminLastUpdatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datAdminLastUpdatedTimestamp = l_bondProduct.getAdminLastUpdatedTimestamp();
        return l_datAdminLastUpdatedTimestamp;
    }

    /**
     * (is�O�݌�)<BR>
     * �O�݌������ǂ������肷��B<BR>
     * <BR>
     * <BR>
     * �P�j������.�ʉ݃R�[�h==�hT0�h�i�~�jorNULL�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44C421490264
     */
    public boolean isForeignCurrency()
    {
        if ((this.getCurrencyCode() == null) ||
            WEB3GentradeCurrencyCodeDef.JPY.equals(this.getCurrencyCode()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (is�]���Ѝ�)<BR>
     * �]���Ѝ��ǂ������肷��B<BR>
     * <BR>
     * <BR>
     * �P�j������.���^�C�v==�h9�h�iCB�j�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44C421D00041
     */
    public boolean isExperienceDivWt()
    {
        if (BondTypeEnum.CB.equals(this.getBondType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get�쐬���t)<BR>
     * �������̍쐬���t��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�쐬���t()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A029D
     */
    public Date getCreatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datCreatedTimestamp = l_bondProduct.getCreatedTimestamp();
        return l_datCreatedTimestamp;
    }

    /**
     * (get�X�V���t)<BR>
     * �������̍X�V���t��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�X�V���t()�̖߂�l��Ԃ��B<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A029E
     */
    public Date getLastUpdatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datLastUpdatedTimestamp = l_bondProduct.getLastUpdatedTimestamp();
        return l_datLastUpdatedTimestamp;
    }

    /**
     * (is�O����)<BR>
     * �O�������ǂ������肷��B<BR>
     * <BR>
     * �P�j������.���^�C�v==�h4�h�i�O�����j�̏ꍇ�A����������ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44C499EE025C
     */
    public boolean isForeignBond()
    {
        if (BondTypeEnum.FOREIGN_BOND.equals(this.getBondType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get���n������)<BR>
     * get���n������ <BR>
     * <BR>
     * 1)java.util.Date�I�u�W�F�N�g�𐶐����A�Ԃ�<BR>
     * �@@new Date(����.������.getTime())<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B6A30079
     */
    public Date getForeignBizDate(Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignBizDate(Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_datBizDate == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return new Date(l_datBizDate.getTime());
    }

    /**
     * (get����)<BR>
     * get����<BR>
     * <BR>
     * 1)java.util.Date�I�u�W�F�N�g�𐶐����A�Ԃ�<BR>
     * �@@new Date(����.������.getTime())<BR>
     * @@param l_datBizDate - (������)<BR>
     * ����������<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7780378
     */
    public Date getExecDate(Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecDate(Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_datBizDate == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return new Date(l_datBizDate.getTime());
    }

    /**
     * (get���n����)<BR>
     * get���n����<BR>
     * <BR>
     * 1)java.util.Date�I�u�W�F�N�g�𐶐����A�Ԃ�<BR>
     * �@@new Date(����.���n������.getTime())<BR>
     * @@param l_datForeignBizDate - (���n������)<BR>
     * ���n������<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7AA02BD
     */
    public Date getForeignExecDate(Date l_datForeignBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignExecDate(Date) ";
        log.entering(STR_METHOD_NAME);
        if (l_datForeignBizDate == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return new Date(l_datForeignBizDate.getTime());

    }

    /**
     * (get��n��)<BR>
     * get��n��<BR>
     * <BR>
     * �P�j����.��������ʔ���is���咍��()�̏ꍇ<BR>
     * �@@�P�|�P�jthis.get���s��()��߂�<BR>
     * <BR>
     * �Q�j����.��������ʔ���.is���t����()�̏ꍇ<BR>
     * �@@�Q�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.��������.getTime()))<BR>
     * <BR>
     * �@@�Q�|�Q)�c�Ɠ��v�Z.roll(this.get���t��n���ړ�����)��߂�<BR>
     * <BR>
     * �R�j����.��������ʔ���is���p�����̏ꍇ<BR>
     * �@@�R�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.��������.getTime()))<BR>
     * <BR>
     * �@@�R�|�Q)�c�Ɠ��v�Z.roll(this.get���p��n���ړ�����)��߂�<BR>
     * @@param l_datExecDate - (����)<BR>
     * ����<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7D900F8
     */
    public Date getDeliveryDate(Date l_datExecDate,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDeliveryDate(" +
            "Date, WEB3BondOrderTypeJudge)";
        log.entering(STR_METHOD_NAME);

        if (l_datExecDate == null || l_bondOrderTypeJudge == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTmp = null;

        //�P�j����.��������ʔ���is���咍��()�̏ꍇ
        if (l_bondOrderTypeJudge.isRecruitOrder())
        {
            //�P�|�P�jthis.get���s��()��߂�
            l_datTmp = this.getIssueDate();
        }

        //�Q�j����.��������ʔ���.is���t����()�̏ꍇ
        if (l_bondOrderTypeJudge.isBuyOrder())
        {
            //�Q�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.��������.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datExecDate.getTime()));

            //�Q�|�Q)�c�Ɠ��v�Z.roll(this.get���t��n���ړ�����)��߂�
            Date l_datBizDate = l_gentradeBizDate.roll(this.getBuyDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }

        //�R�j����.��������ʔ���is���p�����̏ꍇ
        if (l_bondOrderTypeJudge.isSellOrder())
        {
            //�R�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.��������.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datExecDate.getTime()));

            //�R�|�Q)�c�Ɠ��v�Z.roll(this.get���p��n���ړ�����)��߂�
            Date l_datBizDate = l_gentradeBizDate.roll(this.getSellDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }
        log.exiting(STR_METHOD_NAME);
        return l_datTmp;
    }

    /**
     * (get���n��n��)<BR>
     * get���n��n��<BR>
     * <BR>
     * �P�j����.��������ʔ���is���咍��()�̏ꍇ<BR>
     * �@@�P�|�P�jthis.get���s��()��߂�<BR>
     * <BR>
     * �Q�j����.��������ʔ���.is���t����()�̏ꍇ<BR>
     * �@@�Q�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.���n����.getTime()))<BR>
     * <BR>
     * �@@�Q�|�Q)�c�Ɠ��v�Z.roll(this.get���t��n���ړ�����)��߂�<BR>
     * <BR>
     * �R�j����.��������ʔ���is���p�����̏ꍇ<BR>
     * �@@�R�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.���n����.getTime()))<BR>
     * <BR>
     * �@@�R�|�Q)�c�Ɠ��v�Z.roll(this.get���p��n���ړ�����)��߂�<BR>
     * @@param l_datForeignExecDate - (���n����)<BR>
     * ���n����<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@return java.util.Date
     * @@roseuid 44C4BB3A0284
     */
    public Date getForeignDeliveryDate(Date l_datForeignExecDate,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignDeliveryDate(" +
        "Date, WEB3BondOrderTypeJudge)";
        log.entering(STR_METHOD_NAME);

        if (l_datForeignExecDate == null || l_bondOrderTypeJudge == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTmp = null;

        //�P�j����.��������ʔ���is���咍��()�̏ꍇ
        if (l_bondOrderTypeJudge.isRecruitOrder())
        {
            //�P�|�P�jthis.get���s��()��߂�
            l_datTmp = this.getIssueDate();
        }

        //�Q�j����.��������ʔ���.is���t����()�̏ꍇ
        if (l_bondOrderTypeJudge.isBuyOrder())
        {
            //�Q�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.���n����.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datForeignExecDate.getTime()));

            //�Q�|�Q)�c�Ɠ��v�Z.roll(this.get���t��n���ړ�����)��߂�
            Date l_datBizDate = l_gentradeBizDate.roll(this.getBuyDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }

        //�R�j����.��������ʔ���is���p�����̏ꍇ
        if (l_bondOrderTypeJudge.isSellOrder())
        {
            //�R�|�P�jnew �c�Ɠ��v�Z(new java.sql.Timestamp(����.���n����.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datForeignExecDate.getTime()));

            //�R�|�Q)�c�Ɠ��v�Z.roll(this.get���p��n���ړ�����)��߂�
            Date l_datBizDate = l_gentradeBizDate.roll(this.getSellDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }
        log.exiting(STR_METHOD_NAME);
        return l_datTmp;
    }

    /**
     * (is����\)<BR>
     * ������������\�������ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�����敪���擾����B<BR>
     * �@@�@@this.getDataSourceObject().get�����敪���擾����B<BR>
     * <BR>
     * �Q�j�����敪 �� �h����h �̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�����敪 == �h����h �̏ꍇ�A�ȉ��̏��������{����B<BR>
     * <BR>
     * �R�|�P�j�@@���ݓ������擾����B <BR>
     * �@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B <BR>
     * <BR>
     * �R�|�Q�j�@@����J�n�����擾����B <BR>
     * �@@this.getDataSourceObject().get����J�n��()���R�[�����āA����J�n�����擾����B <BR>
     * <BR>
     * �R�|�R�j�@@����I�������擾����B <BR>
     * �@@this.getDataSourceObject().get����I����()���R�[�����āA����I�������擾����B <BR>
     * <BR>
     * �R�|�S�j�@@����J�n�����邢�͉���I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * �R�|�T�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * �@@�@@����J�n�� �� ���ݓ��� �@@���@@���ݓ��� �� ����I���� <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException 
     * @@roseuid 44C563D6036B
     */
    public boolean isRecruitPossible() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isRecruitPossible()";
        log.entering(STR_METHOD_NAME);

        //flag
        boolean l_blnRecruitPossible = false;
        String l_strTradeType = "";

        //�P�j�����敪���擾����B
        //   this.getDataSourceObject().get�����敪���擾����B
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        l_strTradeType = l_bondProduct.getTradeType();

        //�Q�j�����敪 �� �h����h �̏ꍇ�Afalse��ԋp����B
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            l_blnRecruitPossible = false;
        }

        // �R�j�����敪 == �h����h �̏ꍇ�A�ȉ��̏��������{����B
        if (WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            //�R�|�P�j�@@���������擾����B
            //       ������ԊǗ�.get������()���R�[�����A���������擾����B
            Date l_datCurrentDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(); //GtlUtils.getSystemTimestamp();

             //�R�|�Q�j�@@����J�n�����擾����B
             //   this.getDataSourceObject().get����J�n��()���R�[�����āA����J�n�����擾����B
            Date l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
            log.debug("getRecruitStartDate::::::::" + l_datRecruitStartDate);

            //�R�|�R�j�@@����I�������擾����B
            // �@@this.getDataSourceObject().get����I����()���R�[�����āA����I�������擾����B
            Date l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
            log.debug("l_datRecruitEndDate::::::::" + l_datRecruitEndDate);

            //�R�|�S�j�@@����J�n�����邢�͉���I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B
            if ((l_datRecruitStartDate == null) || (l_datRecruitEndDate == null))
            {
                l_blnRecruitPossible = false;
                return l_blnRecruitPossible;
            }

            //�R�|�T�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B
            //           ����J�n�� �� ������ �@@���@@������ �� ����I����
            if ((WEB3DateUtility.compareToDay(l_datCurrentDate, l_datRecruitStartDate) >= 0) &&
                (WEB3DateUtility.compareToDay(l_datRecruitEndDate, l_datCurrentDate) >= 0))
            {
                l_blnRecruitPossible = true;
            }
            else
            {
                l_blnRecruitPossible = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnRecruitPossible;
    }

    /**
     * (is���t�\)<BR>
     * �����������t�\�������ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�����敪���擾����B<BR>
     * �@@�@@this.getDataSourceObject().get�����敪���擾����B<BR>
     * <BR>
     * �Q�j�����敪 == �h���t�h or �h���t/���p�h�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@����ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44C563D603C8
     */
    public boolean isBuyPossible()
    {
        final String STR_METHOD_NAME = "isBuyPossible()";
        log.entering(STR_METHOD_NAME);

        //�P�j�����敪���擾����B
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();

        //�Q�j�����敪 == �h���t�h or �h���t/���p�h�̏ꍇ�Atrue��ԋp����B
        if ((WEB3BondTradeTypeDef.BUY.equals(l_strTradeType)) ||
            (WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�Ǘ��Ҏ戵�\)<BR>
     * is�Ǘ��Ҏ戵�\<BR>
     * <BR>
     * �����������Ǘ��Ҏ戵�\�����`�F�b�N����B <BR>
     * <BR>
     * �P�j�戵�敪���擾����B<BR>
     * �@@�@@this.get�戵�敪()<BR>
     * <BR>
     * �Q�j�戵�敪 �� �h�s�h�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j �Q�j�ȊO �̏ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44C73F59001C
     */
    public boolean isAdminTradePossible()
    {
        final String STR_METHOD_NAME = "isAdminTradePossible()";
        log.entering(STR_METHOD_NAME);

        String l_strTradeHandleDiv = this.getTradeHandleDiv();
        if (WEB3TradeHandleDivDef.DISABLED.equals(l_strTradeHandleDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

    }

    /**
     * (is�ڋq�戵�\)<BR>
     * �ڋq���戵�\�Ȗ������ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�戵�敪���擾����B<BR>
     * �@@�@@this.getDataSourceObject().get�戵�敪���擾����B<BR>
     * <BR>
     * �Q�j�戵�敪 �� "�Ǘ���/�ڋq"�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�戵�敪 == "�Ǘ���/�ڋq"�̏ꍇ�A�ȉ��̏��������{����B<BR>
     * <BR>
     * �R�|�P�j�@@���ݓ������擾����B <BR>
     * �@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B <BR>
     * <BR>
     * �R�|�Q�j�@@�戵�J�n�����擾����B <BR>
     * �@@this.getDataSourceObject().get�戵�J�n��()���R�[�����āA�戵�J�n�����擾����B <BR>
     * <BR>
     * �R�|�R�j�@@�戵�I�������擾����B <BR>
     * �@@this.getDataSourceObject().get�戵�I����()���R�[�����āA�戵�I�������擾����B <BR>
     * <BR>
     * �R�|�S�j�@@�戵�J�n�����邢�͎戵�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * �R�|�T�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * �@@�@@�戵�J�n�� �� ���ݓ��� �@@���@@���ݓ��� �� �戵�I���� <BR>
     * @@return boolean
     * @@roseuid 44C960C80213
     */
    public boolean isCustomerTradePossible()
    {
        final String STR_METHOD_NAME = "isCustomerTradePossible()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnCustomerTradePossible = false;
        //�P�j�戵�敪���擾����B
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeHandleDiv = l_bondProduct.getTradeHandleDiv();

        //�Q�j�戵�敪 �� "�Ǘ���/�ڋq"�̏ꍇ�Afalse��ԋp����B
        if (!WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(l_strTradeHandleDiv))
        {
            l_blnCustomerTradePossible = false;
        }

        //�R�j�戵�敪 == "�Ǘ���/�ڋq"�̏ꍇ�A�ȉ��̏��������{����B
        if (WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(l_strTradeHandleDiv))
        {
            //�R�|�P�j�@@���ݓ������擾����B
            //       ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
            Date l_datCurrentDate = GtlUtils.getSystemTimestamp();

             //�R�|�Q�j�@@�戵�J�n�����擾����B
             //   this.getDataSourceObject().get�戵�J�n��()���R�[�����āA�戵�J�n�����擾����B
            Date l_datTradeStartDate = l_bondProduct.getTradeStartDate();

            //�R�|�R�j�@@�戵�I�������擾����B
            // �@@this.getDataSourceObject().get�戵�I����()���R�[�����āA�戵�I�������擾����B
            Date l_datTradeEndDate = l_bondProduct.getTradeEndDate();

            //�R�|�S�j�@@�@@�戵�J�n�����邢�͎戵�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B
            if ((l_datTradeStartDate == null) || (l_datTradeEndDate == null))
            {
                l_blnCustomerTradePossible = false;
                return l_blnCustomerTradePossible;
            }

            //�R�|�T�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B
            //          �戵�J�n�� �� ���ݓ��� �@@���@@���ݓ��� �� �戵�I����
            if ((WEB3DateUtility.compareToSecond(l_datCurrentDate, l_datTradeStartDate) >= 0) &&
                (WEB3DateUtility.compareToSecond(l_datTradeEndDate, l_datCurrentDate) > 0))
            {
                l_blnCustomerTradePossible = true;
            }
            else
            {
                l_blnCustomerTradePossible = false;
            }

        }
        log.exiting(STR_METHOD_NAME);
        return l_blnCustomerTradePossible;
    }

    /**
     * (get�ʉ�)<BR>
     * get�ʉ�<BR>
     * <BR>
     * �i���ʁj�ʉ݁i�،���ЃR�[�h, �ʉ݃R�[�h�j�Ő��������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * ���j�ʉ݃R�[�h == null or �ʉ݃R�[�h == "T0" �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02660<BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 44CAFA9C01F5
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency()";
        log.entering(STR_METHOD_NAME);

        String l_strCurrencyCode = this.getCurrencyCode();
        if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_strCurrencyCode) ||
                l_strCurrencyCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02660,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ʉ݃R�[�h�����w��(null)�܂��͕s���Ȓl�ł��B");
        }
        WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
            this.getInstitution().getInstitutionCode(),
            this.getCurrencyCode());

        log.exiting(STR_METHOD_NAME);
        return l_currency;
    }

    /**
     * (is���p�\)<BR>
     * �����������p�\�������ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�����敪���擾����B<BR>
     * �@@�@@this.getDataSourceObject().get�����敪���擾����B<BR>
     * <BR>
     * �Q�j�����敪 == �h���p�h or �h���t/���p�h�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@����ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 44D840500102
     */
    public boolean isSellPossible()
    {
        final String STR_METHOD_NAME = "isSellPossible()";
        log.entering(STR_METHOD_NAME);

        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();

        if ((WEB3BondTradeTypeDef.SELL.equals(l_strTradeType)) ||
            (WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�o�ߗ��q�v�Z�x������)<BR>
     * �o�ߗ��q�v�Z�̌x��������s�Ȃ��B<BR>
     * <BR>
     * (1)�@@�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false��ԋp����B<BR>
     * �@@�E������.���ꗘ���敪�@@!=�@@ �h���ꗘ���������h�@@�̏ꍇ <BR>
     * �@@�E������.�N�ԗ����񐔁@@==�@@ �h�s��h(99999999)�@@�̏ꍇ <BR>
     * �@@�E������.���t�^�C�v�@@==�@@�h�ϓ����t�h�@@�̏ꍇ <BR>
     * <BR>
     * (2)�@@��L�ȊO��true��ԋp����B <BR>
     * @@return boolean
     */
    public boolean isAccruedInterestCalcWarningJudge()
    {
        final String STR_METHOD_NAME = "isAccruedInterestCalcWarningJudge()";
        log.entering(STR_METHOD_NAME);

        //(1)�@@�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false��ԋp����B
        int l_intYearlyInterestPayments = this.getYearlyInterestPayments();
        if (!WEB3SpecialPaymentDivDef.NO_SPECIAL_PAYMENT_DAY.equals(this.getSpecialPaymentDiv())
            || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME)
            || CouponTypeEnum.FLOATING_COUPON.equals(this.getCouponType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //(2)�@@��L�ȊO��true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;

    }
    /**
     * (get������)<BR>
     * get������ <BR>
     * <BR>
     * �P�j�����X�ʏ����𐶐�����B<BR>
     * �@@[����] <BR>
     * �@@�@@���XID�F����.���X.���XID<BR>
     * <BR>
     * �Q�j�����X�ʏ���.get�������ݒ�敪����'�����'<BR>
     * �@@�@@���� <BR>
     * �@@�@@����.��������ʔ���.is���偁��true�@@�̏ꍇ<BR>
     * �@@�Q�|�P�j����.���ϋ敪����'�~��' <BR>
     * �@@�@@�@@�@@�@@new �c�Ɠ��v�Z(new java.sql.Timestamp(����.����.getTime()))<BR>
     * �@@�@@�@@�@@�@@���������c�Ɠ��v�Z.roll(1) <BR>
     * <BR>
     * �@@�Q�|�Q)��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��������new Date(����.����.getTime())<BR>
     * <BR>
     * �@@�Q�|�R)��������this.get���s���@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��������new Date(����.����.getTime())<BR>
     * <BR>
     * �R�j�����X�ʏ���.get�������ݒ�敪����'�o�^���'<BR>
     * �@@�@@����<BR>
     * �@@�@@����.��������ʔ���.is���偁��true�@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�R�|�P�j�������}�X�^�[�u��n���v��nulll�łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��������this.get��n��<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Ȃ�<BR>
     * �@@�@@�@@�@@�R�|�Q�j�������}�X�^�[�u��n���v��nulll�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�Q�|�P�j����.���ϋ敪����'�~��'<BR>
     * �@@�@@�@@�@@�@@�@@�@@new �c�Ɠ��v�Z(new java.sql.Timestamp(����.����.getTime()))<BR>
     * �@@�@@�@@�@@�@@�@@�@@���������c�Ɠ��v�Z.roll(1)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�Q�|�Q)��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@��������new Date(����.����.getTime())<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�Q�|�R)��������this.get���s���@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@��������new Date(����.����.getTime())<BR>
     * <BR>
     * �S�j��L�ȊO�̏ꍇ<BR>
     * �@@��������this.get��n��<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�����F����.����<BR>
     * �@@�@@�@@��������ʔ���F����.��������ʔ���<BR>
     * <BR>
     * �T�j��������߂�<BR>
     * @@param l_datExecDate - (����)<BR>
     * ����
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���
     * @@param l_strSettlementDiv - (���ϋ敪)<BR>
     * ���ϋ敪
     * @@param l_branch - (���X)<BR>
     * ���X
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getPaymentDate(
        Date l_datExecDate, WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
        String l_strSettlementDiv, Branch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentDate(Date, WEB3BondOrderTypeJudge, String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_bondOrderTypeJudge == null || l_branch == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        Date l_datPaymentDate = null;
        //�P�j�����X�ʏ����𐶐�����B
        WEB3BondBranchCondition l_branchCondition =
            new WEB3BondBranchCondition(l_branch.getBranchId());

        //�Q�j�����X�ʏ���.get�������ݒ�敪����'�����'
        if (WEB3PaymentDateDetDivDef.EXECUTE_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv()) &&
            l_bondOrderTypeJudge.isRecruitOrder())
        {
            //�Q�|�P�j����.���ϋ敪����'�~��'
            //  new �c�Ɠ��v�Z(new java.sql.Timestamp(����.����.getTime()))
            //  ���������c�Ɠ��v�Z.roll(1)
            if (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_strSettlementDiv))
            {
                WEB3GentradeBizDate l_gentradeBizDate =
                    new WEB3GentradeBizDate(
                        new Timestamp(l_datExecDate.getTime()));
                l_datPaymentDate = l_gentradeBizDate.roll(1);
            }

            //�Q�|�Q)��L�ȊO�̏ꍇ
            //   ��������new Date(����.����.getTime())
            else
            {
                l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
            }

            //�Q�|�R)��������this.get���s���@@�̏ꍇ
            //   ��������new Date(����.����.getTime())
            if (WEB3DateUtility.compareToDay(
                l_datPaymentDate, new Timestamp(this.getIssueDate().getTime())) > 0)
            {
                l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
            }
        }

        //�R�j�����X�ʏ���.get�������ݒ�敪����'�o�^���'
        //����
        //����.��������ʔ���.is���偁��true�@@�̏ꍇ
        else if (WEB3PaymentDateDetDivDef.REGIST_DATE_BASE.equals(
            l_branchCondition.getPaymentDateSetDiv())
            && l_bondOrderTypeJudge.isRecruitOrder())
        {
            //�R�|�P�j�������}�X�^�[�u��n���v��nulll�łȂ��ꍇ�A
            //��������this.get��n��
            //[����]
            //�Ȃ�
            if (this.getDeliveryDate() != null)
            {
                l_datPaymentDate = this.getDeliveryDate();
            }
            //�R�|�Q�j�������}�X�^�[�u��n���v��nulll�̏ꍇ�A
            else
            {
                //�R�|�Q�|�P�j����.���ϋ敪����'�~��'
                //new �c�Ɠ��v�Z(new java.sql.Timestamp(����.����.getTime()))
                //���������c�Ɠ��v�Z.roll(1)
                if (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_strSettlementDiv))
                {
                    WEB3GentradeBizDate l_gentradeBizDate =
                        new WEB3GentradeBizDate(
                            new Timestamp(l_datExecDate.getTime()));
                    l_datPaymentDate = l_gentradeBizDate.roll(1);
                }
                //�R�|�Q�|�Q)��L�ȊO�̏ꍇ
                //��������new Date(����.����.getTime())
                else
                {
                    l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
                }

                //�R�|�Q�|�R)��������this.get���s���@@�̏ꍇ
                //��������new Date(����.����.getTime())
                if (WEB3DateUtility.compareToDay(
                    l_datPaymentDate, new Timestamp(this.getIssueDate().getTime())) > 0)
                {
                    l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
                }
            }
        }
        //    �S�j��L�ȊO�̏ꍇ
        //   �@@��������this.get��n��
        //   �@@�@@[����]
        //   �@@�@@�@@�����F����.����
        //   �@@�@@�@@��������ʔ���F����.��������ʔ���
        else
        {
            l_datPaymentDate = this.getDeliveryDate(l_datExecDate, l_bondOrderTypeJudge);
        }

        //�T�j��������߂�
        log.exiting(STR_METHOD_NAME);
        return l_datPaymentDate;
    }

    /**
     * (get�d�����̈בփ��[�g)<BR>
     * �d�����̈בփ��[�g��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�d�����̈בփ��[�g()�̖߂�l��Ԃ��B <BR>
     * @@return double
     */
    public double getBuyingFxRate()
    {
        //�d�����̈בփ��[�g��Ԃ��B
        //this.getDataSourceObject().get�d�����̈בփ��[�g()�̖߂�l��Ԃ��B
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        return l_bondProduct.getBuyingFxRate();
    }

    /**
     * (get������ԃ`�F�b�N�敪)<BR>
     * ������ԃ`�F�b�N�敪��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get������ԃ`�F�b�N�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getTradingTimeCheckDiv()
    {
        //������ԃ`�F�b�N�敪��Ԃ��B
        //this.getDataSourceObject().get������ԃ`�F�b�N�敪()�̖߂�l��Ԃ��B
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        return l_bondProduct.getTradingTimeCheckDiv();
    }

    /**
     * (get��n��)<BR>
     * �������̎�n����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��n��()�̖߂�l��Ԃ��B<BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        //�������̎�n����Ԃ��B
        //this.getDataSourceObject().get��n��()�̖߂�l��Ԃ��B
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getDeliveryDate();
    }

    /**
     * (get�ŏ����s����)<BR>
     * �������̍ŏ����s�����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�ŏ����s����()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getMinIssueCouponType()
    {
        //�������̍ŏ����s�����Ԃ�
        //this.getDataSourceObject().get�ŏ����s����()�̖߂�l��Ԃ�
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getMinIssueCouponType();
    }

    /**
     * (get�ژ_�����{���`�F�b�N�敪)<BR>
     * �������̖ژ_�����{���`�F�b�N�敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�ژ_�����{���`�F�b�N�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getProspectusCheckDiv()
    {
        //�������̖ژ_�����{���`�F�b�N�敪��Ԃ�
        //this.getDataSourceObject().get�ژ_�����{���`�F�b�N�敪()�̖߂�l��Ԃ�
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getProspectusCheckDiv();
    }

    /**
     * (is�l��������)<BR>
     * �l���������ǂ������肷��B<BR>
     * <BR>
     * �P�j������.���^�C�v==�h11�h�i�l�������j�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�Afalse��ԋp����<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isIndividualGovernmentBond()
    {
        final String STR_METHOD_NAME = " isIndividualGovernmentBond()";
        log.entering(STR_METHOD_NAME);

        //�P�j������.���^�C�v==�h11�h�i�l�������j�̏ꍇ�Atrue��ԋp����B
        String l_strBondType = String.valueOf(this.getBondType().intValue());
        if (WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND.equals(l_strBondType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�j�P�j�ȊO�̏ꍇ�Afalse��ԋp����
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is����������\)<BR>
     * <BR>
     * �������̉���\�Ȋ��Ԃ��ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�����敪���擾����B <BR>
     * �@@�@@this.get�����敪���擾����B <BR>
     * <BR>
     * �Q�j�����敪 �� �h����h �̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �R�j�����敪 == �h����h �̏ꍇ�A�ȉ��̏��������{����B <BR>
     * <BR>
     * �S�j�����`���l�����擾����B  <BR>
     * �@@�@@�Z�b�V�������璍���`���l�����擾����B  <BR>
     * <BR>
     * �T�j���ݓ������擾����B  <BR>
     * �@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A<BR>
     * �@@�@@�@@���ݓ������擾����B  <BR>
     * <BR>
     * �U�j�����`���l�� == "�R�[���Z���^�["or"�c�ƓX"�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏��������{����B <BR>
     * <BR>
     * �@@�U-�P�j�@@�戵�J�n�������擾����B  <BR>
     * this.get�戵�J�n����()���R�[�����āA�戵�J�n�������擾����B  <BR>
     * <BR>
     * �@@�U-�Q�j�@@�戵�I���������擾����B  <BR>
     * this.get�戵�I������()���R�[�����āA�戵�I���������擾����B  <BR>
     * <BR>
     * �@@�U-�R�j�@@�戵�J�n�������邢�͎戵�I���������ݒ肳��Ă��Ȃ�<BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�� false ��Ԃ��B  <BR>
     * <BR>
     * �@@�U-�S�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B  <BR>
     * <BR>
     * �戵�J�n���� �� ���ݓ��� �@@���@@���ݓ��� �� �戵�I������  <BR>
     * <BR>
     * �V�j����ȊO�̏ꍇ�A�ȉ��̏��������{����B  <BR>
     * <BR>
     * �@@�V-�P�j�@@����J�n�����擾����B  <BR>
     * this.get����J�n��()���R�[�����āA����J�n�����擾����B  <BR>
     * <BR>
     * �@@�V-�Q�j�@@����I�������擾����B  <BR>
     * this.get����I����()���R�[�����āA����I�������擾����B  <BR>
     * <BR>
     * �@@�V-�R�j�@@����J�n�����邢�͉���I�������ݒ肳��Ă��Ȃ�<BR>
     * �@@�@@�@@�@@�@@�ꍇ�� false ��Ԃ��B  <BR>
     * <BR>
     * �@@�V-�S�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B  <BR>
     * <BR>
     * ����J�n�� �� ���ݓ��� �@@���@@���ݓ��� �� ����I���� <BR>
     * <BR>
     * @@return boolean
     */
    public boolean isBondDomesticApplyPossible()
    {
        final String STR_METHOD_NAME = " isBondDomesticApplyPossible()";
        log.entering(STR_METHOD_NAME);

        //�P�j�����敪���擾����B
        //this.get�����敪���擾����B
        String l_strTradeType = this.getTradeType();

        //�Q�j�����敪 �� �h����h �̏ꍇ�Afalse��ԋp����B
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�j�����敪 == �h����h �̏ꍇ�A�ȉ��̏��������{����B
        //�S�j�����`���l�����擾����B
        //�Z�b�V�������璍���`���l�����擾����B
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        //�T�j���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //�U�j�����`���l�� == "�R�[���Z���^�["or"�c�ƓX"�̏ꍇ�A�ȉ��̏��������{����B
        if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChanel)
            || WEB3ChannelDef.BRANCH.equals(l_strOrderChanel))
        {
            //�U-�P�j�@@�戵�J�n�������擾����B
            Date l_datTradeStartDate = this.getTradeStartDate();

            //�U-�Q�j�@@�戵�I���������擾����B
            Date l_datTradeEndDate = this.getTradeEndDate();

            //�U-�R�j�@@�戵�J�n�������邢�͎戵�I���������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B
            if (l_datTradeStartDate == null || l_datTradeEndDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //�U-�S�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B
            //�戵�J�n���� �� ���ݓ��� �@@���@@���ݓ��� �� �戵�I������
            if (WEB3DateUtility.compareToSecond(l_datTradeStartDate, l_tmsSystemTimestamp) <= 0
                && WEB3DateUtility.compareToSecond(l_tmsSystemTimestamp, l_datTradeEndDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        else
        {
            //�V�j����ȊO�̏ꍇ�A�ȉ��̏��������{����B
            //�V-�P�j�@@����J�n�����擾����B
            Date l_datRecruitStartDate = this.getRecruitStartDate();

            //�V-�Q�j�@@����I�������擾����B
            Date l_datRecruitEndDate = this.getRecruitEndDate();

            //�V-�R�j�@@����J�n�����邢�͉���I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B
            if (l_datRecruitStartDate == null
                || l_datRecruitEndDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //�V-�S�j�@@�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B
            //����J�n�� �� ���ݓ��� �@@���@@���ݓ��� �� ����I����
            if (WEB3DateUtility.compareToSecond(l_datRecruitStartDate, l_tmsSystemTimestamp) <= 0
                && WEB3DateUtility.compareToSecond(l_tmsSystemTimestamp, l_datRecruitEndDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
    }

    /**
     * (get������������)<BR>
     * get������������<BR>
     * <BR>
     * �戵�I��������ԋp����B  <BR>
     * this.getDataSourceObject().get�戵�I������()���R�[�����āA�戵�I���������擾����B  <BR>
     * ��YYYY/MM/DD�̂� <BR>
     * @@return Date
     */
    public Date getBondDomesticBizDate()
    {
        final String STR_METHOD_NAME = " getBondDomesticBizDate()";
        log.entering(STR_METHOD_NAME);

        //�戵�I��������ԋp����
        //this.getDataSourceObject().get�戵�I������()���R�[�����āA�戵�I���������擾����
        BondProductRow l_bondProductRow =  (BondProductRow)this.getDataSourceObject();
        Date l_datTradeEndDate = l_bondProductRow.getTradeEndDate();

        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_datTradeEndDate);
    }

    /**
     * (get���助�U�`��)<BR>
     * �������̉��助�U�`����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���助�U�`��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getRecruitInvitationDiv()
    {
        final String STR_METHOD_NAME = "getRecruitInvitationDiv()";
        log.entering(STR_METHOD_NAME);

        //�������̉��助�U�`����Ԃ��B
        //this.getDataSourceObject().get���助�U�`��()�̖߂�l��Ԃ��B
        BondProductRow l_bondProductRow = (BondProductRow)this.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return l_bondProductRow.getRecruitInvitationDiv();
    }

    /**
     * (get������󂯋敪)<BR>
     * �������̉�����󂯋敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get������󂯋敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getRecruitAcceptDiv()
    {
        final String STR_METHOD_NAME = "getRecruitAcceptDiv()";
        log.entering(STR_METHOD_NAME);

        //�������̉�����󂯋敪��Ԃ��B
        //this.getDataSourceObject().get������󂯋敪()�̖߂�l��Ԃ��B
        BondProductRow l_bondProductRow = (BondProductRow)this.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return l_bondProductRow.getRecruitAcceptDiv();
    }
}
@
