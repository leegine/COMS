head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�o�^�m�F���N�G�X�g (WEB3AdminPMAccProductTradeStopRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ڋq�����ʎ����~�o�^�m�F���N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�o�^�m�F���N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopRegistConfirmRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopRegistConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_acc_product_trade_stop_regist_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class);

    /**
     * �i�ڋq�����ʎ����~���j
     * �ڋq�����ʎ����~���
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD9392029F
     */
    public WEB3AdminPMAccProductTradeStopRegistConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�ڋq�����ʎ����~���`�F�b�N<BR>
     * �@@�P�|�P�jthis.�ڋq�����ʎ����~���== null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�����ʎ����~���null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01428<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�ڋq�����ʎ����~���.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class)<BR>
     * <BR>
     * 1)accProductTradeStopInfo check<BR>
     * �@@1-1) If this.accProductTradeStopInfo== null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "accProductTradeStopInfo is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01428<BR>
     * <BR>
     *   1-2) Call this.accProductTradeStopInfo.validate()<BR>
     * <BR>
     * @@roseuid 4185F60103CB
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if accProductTradeStopInfo is null, throw Exception.
        if (accProductTradeStopInfo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01428,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 Call this.accProductTradeStopInfo.validate()
            this.accProductTradeStopInfo.validate();
        }
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopRegistConfirmResponse(this);
    }
}
@
