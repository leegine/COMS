head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMStopStartChgConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g(WEB3AdminTMMStopStartChgConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�m�F���N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�m�F���N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmRequest<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMStopStartChgConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmm_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMStopStartChgConfirmRequest.class);

    /**
     * (�s��ʎ���󋵈ꗗ)
     * <BR>
     * �s��ʎ���󋵈ꗗ<BR>
     */
    public WEB3AdminTMMarketTradingStatusUnit[] marketTradingStatusList;

    /**
     * @@roseuid 41DD3A2A0169
     */
    public WEB3AdminTMMStopStartChgConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�s��ʎ���󋵈ꗗ�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�s��ʎ���󋵈ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��ʎ���󋵈ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01422<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�s��ʎ���󋵈ꗗ.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��ʎ���󋵈ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01423<BR>
     * <BR>
     * �@@�P�|�R�jthis.�s��ʎ���󋵈ꗗ.length�̐����A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�P�|�R�|�P�j�s��ʎ����.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)marketTradingStatusList check<BR>
     *   1-1)If marketTradingStatusList == null<BR>
     *            Throw the following error [marketTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01422<BR>
     * <BR>
     *   1-2)If this.marketTradingStatusList.length == 0<BR>
     *            Throw the following error [marketTradingStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01423<BR>
     * <BR>
     *   1-3)Loop for as many marketTradingStatusList elements<BR>
     *     1-3-1)Call WEB3AdminTMMarketTradingStatusUnit.validate<BR>
     * @@roseuid 41775B51013D
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_marketTradingStatusListlength = 0;
        WEB3AdminTMMarketTradingStatusUnit l_marketTradingStatusUnit = null;

        // 1-1marketTradingStatusList = null, throw Exception.
        if (this.marketTradingStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01422,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_marketTradingStatusListlength = this.marketTradingStatusList.length;

            // 1-2 l_marketTradingStatusListlength = 0, throw Exception.
            if (l_marketTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01423,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 1-3 Loop for as many marketTradingStatusList elements.
            for (int i = 0; i < l_marketTradingStatusListlength; i++)
            {
                l_marketTradingStatusUnit = this.marketTradingStatusList[i];

                // 1-3-1 Call WEB3AdminTMMarketTradingStatusUnit.validate.
                l_marketTradingStatusUnit.validate();
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
        return new WEB3AdminTMMStopStartChgConfirmResponse(this);
    }
}
@
