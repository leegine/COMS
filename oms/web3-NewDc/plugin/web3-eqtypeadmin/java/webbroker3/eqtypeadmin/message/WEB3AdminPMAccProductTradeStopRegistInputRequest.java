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
filename	WEB3AdminPMAccProductTradeStopRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��N�G�X�g (WEB3AdminPMAccProductTradeStopRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopRegistInputRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopRegistInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final  static String PTYPE = "admin_pm_acc_product_trade_stop_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopRegistInputRequest.class);

    /**
     * �i���X�R�[�h�ꗗ�j<BR>
     * <BR>
     * �Ǘ��҂̕��X�R�[�h�ꗗ<BR>
     * �����X�����̃`�F�b�N�Ɏg�p�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * branchCodeList<BR>
     * <BR>
     * branchCode list<BR>
     * <BR>
     * ��it is used for validateBranchPermission<BR>
     * <BR>
     */
    public String[] branchCodeList;

    /**
     * @@roseuid 41FD938B002E
     */
    public WEB3AdminPMAccProductTradeStopRegistInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCodeList check<BR>
     *   1-1) If  this.branchCodeList == null<BR>
     *            Throw the exception "branchCodeList is null"<BR>
     * <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * @@roseuid 4185F5AF0041
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if branchCodeList is null, throw Exception.
        if (branchCodeList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopRegistInputResponse(this);
    }
}
@
