head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���i�ʎ戵��~�ĊJ�Ɖ�N�G�X�g(WEB3AdminTMPStopStartReferenceRequest.java)
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
 * �i�Ǘ��ҁE���i�ʎ戵��~�ĊJ�Ɖ�N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceRequest<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMPStopStartReferenceRequest.class);

    /**
     * �i���X�R�[�h�ꗗ�j<BR>
     * <BR>
     * �����X�����`�F�b�N�Ɏg�p�B<BR>
     * �@@���X�����`�F�b�N���s��Ȃ��ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * branchTradingStatusList<BR>
     * <BR>
     * ��It uses it to check the branch authority. <BR>
     *   When the branch authority is not checked, set null.<BR>
     * <BR>
     */
    public String[] branchCodeList = null;

    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���S���X�̏ꍇ�́Anull���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * ��Null is set for every branch. <BR>
     * <BR>
     */
    public String branchCode;

    /**
     * @@roseuid 41DD3CD20040
     */
    public WEB3AdminTMPStopStartReferenceRequest()
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
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     *  1-1)If this.productCode != null check the following<BR>
     *     1-1-1)If this.productCode.length != 3    or<BR>
     *             productCode.length != number<BR>
     *             throw the following error [productCode error]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * @@roseuid 4173884301D1
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intThree = 3;

        // 1-1 if productCode != null, enter next loop.
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMPStopStartReferenceResponse(this);
    }
}
@
