head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�폜�������N�G�X�g (WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�폜�������N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�폜�������N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopDeleteCompleteRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopDeleteCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_p_m_acc_product_trade_stop_delete_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static  long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class);

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
     * <BR>
     * accProductTradeStopInfo<BR>
     * <BR>
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD932C0119
     */
    public WEB3AdminPMAccProductTradeStopDeleteCompleteRequest()
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
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01428<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�ڋq�����ʎ����~���.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 4185F6210021
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 2-1 if accProductTradeStopInfo = null, throw Exception.
        if (accProductTradeStopInfo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01428,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 accProductTradeStopInfo.validate().
            this.accProductTradeStopInfo.validate();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopDeleteCompleteResponse(this);
    }
}
@
