head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���i�ʎ戵��~�ĊJ�ύX�������N�G�X�g(WEB3AdminTMPStopStartCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE���i�ʎ戵��~�ĊJ�ύX�������N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteRequest<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_chg_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMPStopStartCompleteRequest.class);

    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���S���X�̏ꍇ�́Anull���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �ibranchCode�j<BR>
     * <BR>
     * ��Null is set for every branch.<BR>
     */
    public String branchCode;

    /**
     * �i�Ïؔԍ��j<BR>
     * <BR>
     * �Ïؔԍ�<BR>
     * <BR>
     * password<BR>
     */
    public String password;

    /**
     * (���X�ʎ戵�󋵈ꗗ)
     * <BR>
     * ���X�ʎ戵�󋵈ꗗ<BR>
     */
    public WEB3AdminTMBranchTradingStatusUnit[] branchTradingStatusList;

    /**
     * @@roseuid 41DD3C650001
     */
    public WEB3AdminTMPStopStartCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@this.���X�R�[�h != null�̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E���X�R�[�h.length != 3<BR>
     * �@@�@@�@@�@@�@@�E���X�R�[�h != ���l<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�Ïؔԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�Ïؔԍ� == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�Ïؔԍ���null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * �R�j���X�ʎ戵�󋵈ꗗ�̃`�F�b�N<BR>
     * �@@�R�|�P�jthis.���X�ʎ戵�󋵈ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�ʎ戵�󋵈ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     * �@@�R�|�Q�jthis.���X�ʎ戵�󋵈ꗗ.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�ʎ戵�󋵈ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     * �@@�R�|�R�jthis.���X�ʎ戵�󋵈ꗗ.length�̐����A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�R�|�R�|�P�j���X�ʎ戵��.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *   1-1)If this.branchCode != null<BR>
     *     1-1-1)If "branchCode.length != 3" or<BR>
     *              "branchCode != number"<BR>
     *              Throw the following error [branchCode error]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)password check<BR>
     *   2-1)If this.password == null<BR>
     *            Throw the following error [password is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * 3)branchTradingStatusList check<BR>
     *   3-1)If this.branchTradingStatusList ==null<BR>
     *            Throw the following error [branchTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     *   3-2)If this.branchTradingStatusList.length == 0<BR>
     *            Throw the following error [branchTradingStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     *   3-3)Loop for as many branchTradingStatusList elements<BR>
     *     3-3-1)Call WEB3AdminTMBranchTradingStatusUnit.validate()<BR>
     * @@roseuid 4173850D02EB
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_branchTradingStatusListlength = 0;
        final int l_intThree = 3;
        WEB3AdminTMBranchTradingStatusUnit l_branchTradingStatusUnit = null;

        // 1-1 if branchCode != null check given condition.
        if (this.branchCode != null)
        {
            // 1-1-1 branchCode.length() != 3 OR branchCode is Not Numeric.
            if ((this.branchCode.length() != l_intThree)
                || (!WEB3StringTypeUtility.isNumber(this.branchCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if password = null, throw Exception.
        if (this.password == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1 if branchTradingStatusList = null, throw Exception.
        if (this.branchTradingStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01427,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 3-2 l_branchTradingStatusListlength = 0, throw Exception.
            l_branchTradingStatusListlength = branchTradingStatusList.length;
            if (l_branchTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01426,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 3-3 Loop for as many branchTradingStatusList elements
            for (int i = 0; i < l_branchTradingStatusListlength; i++)
            {
                l_branchTradingStatusUnit = branchTradingStatusList[i];
                //3-3-1 Call WEB3AdminTMBranchTradingStatusUnit.validate().
                l_branchTradingStatusUnit.validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMPStopStartCompleteResponse(this);
    }
}
@
