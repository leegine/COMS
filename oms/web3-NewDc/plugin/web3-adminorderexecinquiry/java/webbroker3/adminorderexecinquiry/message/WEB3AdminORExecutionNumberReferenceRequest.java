head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE������茏���Ɖ�N�G�X�g (WEB3AdminORExecutionNumberReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 ꎉ� (���u) �d�l�ύXNo.55�C��
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminMarketDspDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminMonthlySumDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminOrderRootDspDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminSumProductTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminSummryDivDef;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE������茏���Ɖ�N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE������茏���Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminORExecutionNumberReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOREquityOrderExecutionRefInputRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * An array of branchCode<BR>
     * <BR>
     * ��The handling possible branchCodeList held by PR layer is set when branchCode
     * is not input.<BR>
     * <BR>
     */
    public String[] branchCode;

    /**
     * (�W�v�Ώۏ��i�敪�ꗗ)<BR>
     * <BR>
     * �W�v�Ώۏ��i�敪�ꗗ<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�O������<BR>
     * 4�F�@@�I�v�V����<BR>
     * 5�F�@@�敨<BR>
     * 6�F�@@���M<BR>
     * 7�F�@@����F<BR>
     * 8�F�@@MMF<BR>
     * 9�F�@@�� <BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * sumProductTypeList<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.FORIGN_STOCK<BR>
     * 4: Def.OPTION<BR>
     * 5: Def.FUTURE<BR>
     * 6: Def.MF<BR>
     * 7: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 8: Def.MMF_SET<BR>
     * 9: Def.BOND<BR>
     * <BR>
     */
    public String[] sumProductTypeList;

    /**
     * (�W�v�敪)<BR>
     * <BR>
     * �W�v�敪<BR>
     * <BR>
     * 0�F�@@����<BR>
     * 1�F�@@����<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * summryDiv<BR>
     * 0: Def.DAILY<BR>
     * 1: Def.MONTHLY<BR>
     * <BR>
     */
    public String summryDiv;

    /**
     * (���ʏW�v�Ώ۔N��)<BR>
     * <BR>
     * ���ʏW�v�Ώ۔N��<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * dailySumYm<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String dailySumYm;

    /**
     * (���ʏW�v�Ώۋ敪)<BR>
     * <BR>
     * ���ʏW�v�Ώۋ敪<BR>
     * <BR>
     * 0�F�@@�ߋ�3����<BR>
     * 1�F�@@�ߋ�6����<BR>
     * 2�F�@@�ߋ�12����<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * monthlySumDiv<BR>
     * 0: Def.PAST_THREE_MONTH<BR>
     * 1: Def.PAST_SIX_MONTH<BR>
     * 2: Def.PAST_TWELVE_MONTH<BR>
     * <BR>
     */
    public String monthlySumDiv;

    /**
     * (�����o�H�\���敪)<BR>
     * <BR>
     * �����o�H�\���敪<BR>
     * <BR>
     * 0�F�@@�ڍ�<BR>
     * 1�F�@@���v<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDspDiv<BR>
     * 0: Def.DETAIL<BR>
     * 1: Def.TOTAL<BR>
     * <BR>
     */
    public String orderRootDspDiv;

    /**
     * (�s��\���敪)<BR>
     * <BR>
     * �s��\���敪<BR>
     * <BR>
     * 0�F�@@�ڍ�<BR>
     * 1�F�@@���v<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * marketDspDiv<BR>
     * 0: Def.DETAIL<BR>
     * 1: Def.TOTAL<BR>
     * <BR>
     */
    public String marketDspDiv;
    
    

    /**
     * @@roseuid 4212FD03024A
     */
    public WEB3AdminORExecutionNumberReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���X�R�[�h.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * �@@�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j���i�敪�ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���i�敪�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�敪�ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���i�敪�ꗗ.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�敪�ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01463<BR>
     * <BR>
     * �@@�Q�|�R�jthis.���i�敪�ꗗ�̊e�v�f���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�M�p���"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�����~�j����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�I�v�V����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�敨"<BR>
     * �@@�@@�@@�@@�@@�@@�E"���M"<BR>
     * �@@�@@�@@�@@�@@�@@�E"����F"<BR>
     * �@@�@@�@@�@@�@@�@@�E"MMF"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�O������"<BR>
     * �@@�@@�@@�@@�@@�@@�E"��"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �R�j�W�v�敪�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�W�v�敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�W�v�敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01465<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�W�v�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�W�v�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"����"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01466<BR>
     * <BR>
     * �S�j���ʏW�v�Ώ۔N���`�F�b�N<BR>
     * �@@this.�W�v�敪 == "����"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�jthis.���ʏW�v�Ώ۔N�� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʏW�v�Ώ۔N����null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01467<BR>
     * <BR>
     * �@@�S�|�Q�jthis.���ʏW�v�Ώ۔N�����ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʏW�v�Ώ۔N���G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���ʏW�v�Ώ۔N�� != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���ʏW�v�Ώ۔N��.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01468<BR>
     * <BR>
     * �T�j���ʏW�v�Ώۋ敪�`�F�b�N<BR>
     * �@@this.�W�v�敪 == "����"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�jthis.���ʏW�v�Ώۋ敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʏW�v�Ώۋ敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01469<BR>
     * <BR>
     * �@@�T�|�Q�jthis.���ʏW�v�Ώۋ敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʏW�v�Ώۋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"�ߋ�3����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�ߋ�6����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�ߋ�12����"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01470<BR>
     * <BR>
     * �U�j�����o�H�\���敪�`�F�b�N<BR>
     * �@@�U�|�P�jthis.�����o�H�\���敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����o�H�\���敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01471<BR>
     * <BR>
     * �@@�U�|�Q�jthis.�����o�H�\���敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����o�H�\���敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"�ڍ�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"���v"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * �V�j�s��\���敪�`�F�b�N<BR>
     * �@@�V�|�P�jthis.�s��\���敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��\���敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01473<BR>
     * <BR>
     * �@@�V�|�Q�jthis.�s��\���敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��\���敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"�ڍ�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"���v"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01474<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     * �@@1-1)If this.branchCode== null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@1-2)If this.branchCode.length == 0<BR>
     * �@@�@@�@@�@@Throw the exception "The number of the elements of branchCode is 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * �@@1-3)Check the following for as many times as elements of this.branchCode<BR>
     * �@@�@@1-3-1)If this.branchCode meets with the following conditions,<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception "branchCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)sumProductTypeList check<BR>
     * �@@2-1)If this.sumProductTypeList == null,<BR>
     * �@@�@@�@@�@@Throw the exception "sumProductTypeList is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * �@@2-2)If this.sumProductTypeList.length == 0<BR>
     * �@@�@@�@@�@@Throw the exception "The number of the elements of sumProductTypeList is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01463<BR>
     * <BR>
     * �@@2-3)If each element of this.sumProductTypeList is not either of the following
     * values,<BR>
     * �@@�@@�@@�@@Throw the exception "sumProductTypeList has an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.EQUITY<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MARGIN<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MINI_STOCK<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.OPTION<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.FUTURE<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MF<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MIDIUM_TERM_GOV_FUND<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MMF_SET<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.FORIGN_STOCK<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.BOND<BR> 
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 3)summryDiv check<BR>
     * �@@3-1)If this.summryDiv == null,<BR>
     * �@@�@@�@@�@@Throw the exception "summryDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01465<BR>
     * <BR>
     * �@@3-2)If this.summryDiv contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the exception "summryDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.DAILY<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.MONTHLY<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01466<BR>
     * <BR>
     * 4)dailySumYm check<BR>
     * �@@Check the following if this.summryDiv == Def.DAILY<BR>
     * �@@4-1)If this.dailySumYm == null<BR>
     * �@@�@@�@@�@@Throw the exception "dailySumYm is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01467<BR>
     * <BR>
     * �@@4-2)If this.dailySumYm meets with the following conditions<BR>
     * �@@�@@�@@�@@�@@Throw the exception "dailySumYm error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.dailySumYm != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.dailySumYm.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01468<BR>
     * <BR>
     * 5)monthlySumDiv check<BR>
     * �@@Cehck the following if this.summryDiv == Def.MONTHLY<BR>
     * �@@5-1)If this.monthlySumDiv== null,<BR>
     * �@@�@@�@@�@@Throw the exception "monthlySumDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01469<BR>
     * <BR>
     * �@@5-2)If this.monthlySumDiv contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the exception "monthlySumDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.PAST_THREE_MONTH<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.PAST_SIX_MONTH<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.PAST_TWELVE_MONTH<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01470<BR>
     * <BR>
     * 6)orderRootDspDiv check<BR>
     * �@@6-1)If this.orderRootDspDiv== null,<BR>
     * �@@�@@�@@�@@Throw the exception "orderRootDspDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01471<BR>
     * <BR>
     * �@@6-2)If this.orderRootDspDiv contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the exception "orderRootDspDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.DETAIL<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.TOTAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * 7)marketDspDiv check<BR>
     * �@@7-1)if this.marketDspDiv== null,<BR>
     * �@@�@@�@@�@@ Throw the exception "marketDspDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01473<BR>
     * <BR>
     * �@@7-2)If this.marketDspDiv contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the exception "marketDspDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.DETAIL<BR>
     * �@@�@@�@@�@@�@@�@@�EDef.TOTAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01474<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 419C625C0275
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intThree = 3;
        final int l_intSix = 6;
        int l_intMin = 0;
        int l_intBranchCodeLength = 0;
        int l_intSumProductTypeListLength = 0;

        // 1-1 if branchCode is null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1-2 if branchCode.length is 0, throw Exception.
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intBranchCodeLength = this.branchCode.length;
            for (int i = 0;  i < l_intBranchCodeLength; i++)
            {
                // 1-3-1 if branchCode is not numeric & its length not eqial to 3, throw Exception.
                if ((!WEB3StringTypeUtility.isNumber(branchCode[i]))
                    || (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != l_intThree))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 2-1 if sumProductTypeList is null, throw Exception.
        if (this.sumProductTypeList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if length of sumProductTypeList is null, throw Exception.
            l_intSumProductTypeListLength = this.sumProductTypeList.length;
            if (l_intSumProductTypeListLength == l_intMin)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01463,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-3 Within loop, sumProductTypeList is not any of Def then throw Exception.
        l_intSumProductTypeListLength = this.sumProductTypeList.length;
        for (int k = 0; k < l_intSumProductTypeListLength; k++)
        {
            if ((!WEB3AdminSumProductTypeDef.EQUITY.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MARGIN.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MINI_STOCK.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.OPTION.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.FUTURE.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MF.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MIDIUM_TERM_GOV_FUND.equals
                    (this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MMF_SET.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.FORIGN_STOCK.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.BOND.equals(this.sumProductTypeList[k])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if summreyDiv is null, throw Exception
        if (this.summryDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01465,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 3-2 if summryDiv is not of Def, throw Exception.
            if ((!WEB3AdminSummryDivDef.DAILY.equals(this.summryDiv))
                && (!WEB3AdminSummryDivDef.MONTHLY.equals(this.summryDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01466,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if dailySumYm is null, throw Exception.
        if (WEB3AdminSummryDivDef.DAILY.equals(this.summryDiv))
        {
            if (this.dailySumYm == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01467,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 4-2 if dailySumYm is not Numeric or length of dailySumYm is not 6, throw Exception.
                if ((!WEB3StringTypeUtility.isNumber(this.dailySumYm))
                    || (WEB3StringTypeUtility.getByteLength(this.dailySumYm) != l_intSix))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01468,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 5-1 if  summryDiv equals MONTHLY then if monthlySumDiv is null, throw Exception.
        if ((WEB3AdminSummryDivDef.MONTHLY.equals(this.summryDiv)))
        {
            if (this.monthlySumDiv == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01469,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 5-2 if monthlySumDiv not equal to Def , throw Exception.
                if ((!WEB3AdminMonthlySumDivDef.PAST_THREE_MONTH.equals(this.monthlySumDiv))
                    && (!WEB3AdminMonthlySumDivDef.PAST_SIX_MONTH.equals(this.monthlySumDiv))
                    && (!WEB3AdminMonthlySumDivDef.PAST_TWELVE_MONTH.equals(this.monthlySumDiv)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01470,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 6-1 if orderRootDspDiv is null, throw Exception.
        if (this.orderRootDspDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01471,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 6-2 if monthlySumDiv is not any of Def, throw Exception.
            if ((!WEB3AdminOrderRootDspDivDef.DETAIL.equals(this.orderRootDspDiv))
                && (!WEB3AdminOrderRootDspDivDef.TOTAL.equals(this.orderRootDspDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01472,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if marketDspDiv is null, throw Exception.
        if (this.marketDspDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01473,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 7-2 if marketDspDiv is not any of Def, throw Exception.
            if ((!WEB3AdminMarketDspDivDef.DETAIL.equals(this.marketDspDiv))
                && (!WEB3AdminMarketDspDivDef.TOTAL.equals(this.marketDspDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01474,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORExecutionNumberReferenceResponse();
    }
}
@
