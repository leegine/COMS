head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopModifyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�ύX�������N�G�X�g (WEB3AdminPMAccProductTradeStopModifyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �������F(SRA) �V�K�쐬
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�ύX�������N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�ύX�������N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopModifyCompleteRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopModifyCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_pm_acc_product_trade_stop_modify_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class);

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
     * �i�ڋq�����ʎ����~���j<BR>
     * <BR>
     * �ڋq�����ʎ����~���<BR>
     * <BR>
     * accProductTradeStopInfo<BR>
     * <BR>
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD93EB0167
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteRequest()
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
     * @@roseuid 4185F6B0038C
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
            //2-2 ��his.accProductTradeStopInfo.validate() is called.
            accProductTradeStopInfo.validate();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopModifyCompleteResponse(this);
    }
}
@
