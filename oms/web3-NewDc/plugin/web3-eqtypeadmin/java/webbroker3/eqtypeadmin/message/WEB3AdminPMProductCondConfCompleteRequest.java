head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������ݒ芮�����N�G�X�g (WEB3AdminPMProductCondConfCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������ݒ芮�����N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�������������ݒ芮�����N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfCompleteRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfCompleteRequest.class);

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�Ïؔԍ��j<BR>
     * <BR>
     * �Ïؔԍ�<BR>
     * <BR>
     * password<BR>
     * <BR>
     */
    public String password;

    /**
     * �i����K���ꗗ�j<BR>
     * <BR>
     * ������������K���̈ꗗ<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * �i��{���ꗗ�j<BR>
     * <BR>
     * ����������{���̈ꗗ<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * �i�M�p�������ꗗ�j<BR>
     * <BR>
     * ���������M�p�������̈ꗗ<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * �i�ϑ��ۏ؋����ꗗ�j<BR>
     * <BR>
     * ���������ϑ��ۏ؋����̈ꗗ<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * �i��p�L���،����ꗗ�j<BR>
     * <BR>
     * ����������p�L���،����̈ꗗ<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * �i���������l�i���̈ꗗ�j<BR>
     * <BR>
     * �l�i���ꗗ<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA29F8039A
     */
    public WEB3AdminPMProductCondConfCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�����R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�uthis.�����R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͖����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �Q�j�m�F���e�`�F�b�N<BR>
     * �@@�Q�|�P�j�ȉ��̃N���X�ϐ����S��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�m�F���e��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�����~�ꗗ<BR>
     * �@@�@@�@@�@@�@@�E��{���ꗗ<BR>
     * �@@�@@�@@�@@�@@�E�M�p�������ꗗ<BR>
     * �@@�@@�@@�@@�@@�E�ϑ��ۏ؋��ꗗ<BR>
     * �@@�@@�@@�@@�@@�E��p�L���،��ꗗ<BR>
     * �@@�@@�@@�@@�@@�E�l�i���ꗗ<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01440<BR>
     * <BR>
     * �@@�Q�|�Q�j��L�N���X�ϐ��ɂ��āAnull�łȂ��f�[�^��<BR>
     * �@@�@@�@@�@@�@@�f�[�^�̗v�f(=���������ݒ���)�����A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�Q�|�Q�|�P�j���������ݒ���.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * Changed as per �y���Ǘ��ҁi�C���h�����j�z�d�l�ύX�Ǘ��䒠_English.xls
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * �@@1-1) If this.productCode == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "this.productCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@1-2) If this.productCode meets with the following conditions<BR>
     * �@@�@@�@@�@@�@@Throw the exception " input productCode error"<BR>
     * �@@�@@�@@�@@�@@�Ethis.productCode.length != 5<BR>
     * �@@�@@�@@�@@�@@�Ethis.productCode != numerical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 2)confirm content check<BR>
     * �@@2-1) If the following class variables are all null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "the confirm content is null"<BR>
     *            �EtradingRegulationList<BR>
     *            �EbasicInfoList<BR>
     *            �EstockMarginInfoList<BR>
     *            �EdepositRateList<BR>
     *            �EsubstituteSecurityInfoList<BR>
     *            �EpriceInfoList<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01440<BR>
     * <BR>
     * �@@2-2) Loop the process for as many times as <BR>
     *         data elements(=WEB3AdminPMProductCondConfigUnit)<BR>
     *         if the data is not null about the class variables mentioned above.<BR>
     * �@@�@@�@@2-2-1) Call WEB3AdminPMProductCondConfigUnit.validate()<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181B2980190
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intFive = 5;
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(productCode);
        int l_intTradingRegulationListlength = 0;
        int l_intBasicInfoListlength = 0;
        int l_intStockMarginInfoListlength = 0;
        int l_intDepositRateListlength = 0;
        int l_intSubstituteSecurityInfoListlength = 0;
        int l_intPriceInfoListlength = 0;

        WEB3AdminPMProductCondConfigUnit l_productCondConfigUnit =
            new WEB3AdminPMProductCondConfigUnit();

        // 1-1 if productCode is null, throw Exception
        if (productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if productCode is Not Numeric & length not equal to 5 digits, throw Exception
            if ((!WEB3StringTypeUtility.isNumber(productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if folowing instances are null then throw Exception.
        if ((this.tradingRegulationList == null)
            && (this.basicInfoList == null)
            && (this.stockMarginInfoList == null)
            && (this.depositRateList == null)
            && (this.substituteSecurityInfoList == null)
            && (this.priceInfoList == null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01440,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2  Loop the process for as many times as data elements
            if (this.tradingRegulationList != null)
            {
                l_intTradingRegulationListlength = this.tradingRegulationList.length;
                for (int i = 0; i < l_intTradingRegulationListlength; i++)
                {
                    l_productCondConfigUnit = tradingRegulationList[i];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.basicInfoList != null)
            {
                l_intBasicInfoListlength = this.basicInfoList.length;
                for (int j = 0; j < l_intBasicInfoListlength; j++)
                {
                    l_productCondConfigUnit = basicInfoList[j];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.stockMarginInfoList != null)
            {
                l_intStockMarginInfoListlength = this.stockMarginInfoList.length;
                for (int k = 0; k < l_intStockMarginInfoListlength; k++)
                {
                    l_productCondConfigUnit = stockMarginInfoList[k];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.depositRateList != null)
            {
                l_intDepositRateListlength = this.depositRateList.length;
                for (int l = 0; l < l_intDepositRateListlength; l++)
                {
                    l_productCondConfigUnit = depositRateList[l];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.substituteSecurityInfoList != null)
            {
                l_intSubstituteSecurityInfoListlength = this.substituteSecurityInfoList.length;
                for (int m = 0; m < l_intSubstituteSecurityInfoListlength; m++)
                {
                    l_productCondConfigUnit = substituteSecurityInfoList[m];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.priceInfoList != null)
            {
                l_intPriceInfoListlength = this.priceInfoList.length;
                for (int n = 0; n < l_intPriceInfoListlength; n++)
                {
                    l_productCondConfigUnit = priceInfoList[n];
                    l_productCondConfigUnit.validate();
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondConfCompleteResponse(this);
    }
}
@
