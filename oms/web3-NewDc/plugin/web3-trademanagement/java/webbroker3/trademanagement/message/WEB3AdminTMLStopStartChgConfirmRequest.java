head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLStopStartChgConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���N�G�X�g(WEB3AdminTMLStopStartChgConfirmRequest.java)
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
 * �i�Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgConfirmRequest <BR>
 * <BR>
 * WEB3AdminTMLStopStartChgConfirmRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLStopStartChgConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tml_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLStopStartChgConfirmRequest.class);

    /**
     * (���O�C�����󋵈ꗗ)
     * <BR>
     * ���O�C�����󋵈ꗗ<BR>
     */
    public WEB3AdminTMLoginPermissionStatusUnit[] loginPermissionStatusList;

    /**
     * @@roseuid 41DD356101C6
     */
    public WEB3AdminTMLStopStartChgConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���O�C�����󋵈ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���O�C�����󋵈ꗗ == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���O�C�����󋵈ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���O�C�����󋵈ꗗ.length == 0�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���O�C�����󋵈ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417<BR>
     * <BR>
     * �@@�P�|�R�jthis.���O�C�����󋵈ꗗ�̗v�f�����ȉ��̏�����<BR>
     * �@@�@@�@@�@@�@@�J��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�P�|�R�|�P�jthis.���O�C������.validate()���R�[������B<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)this.loginPermissionStatusList check<BR>
     *   1-1)If this.loginPermissionStatusList == null<BR>
     *            Throw the following error [loginPermissionStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     *   1-2)If this.loginPermissionStatusList.length == 0<BR>
     *            Throw the following error [loginPermissionStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417<BR>
     * <BR>
     *   1-3)Loop for as many loginPermissionStatusList elements<BR>
     *     1-3-1)Call this.WEB3AdminTMLoginPermissionStatusUnit.validate()<BR>
     * @@roseuid 417756DE012D
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_loginPermissionStatusListlength = 0;
        WEB3AdminTMLoginPermissionStatusUnit l_loginPermissionStatus = null;

        // 1-1 loginPermissionStatusList = null, throw Exception.
        if (this.loginPermissionStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01416,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else

        {
            l_loginPermissionStatusListlength = this.loginPermissionStatusList.length;

            // 1-2 l_loginPermissionStatusListlength = 0, throw Exception.
            if (l_loginPermissionStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01417,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 1-3 Loop for as many loginPermissionStatusList elements, throw Exception.
            for (int i = 0; i < l_loginPermissionStatusListlength; i++)
            {
                l_loginPermissionStatus = this.loginPermissionStatusList[i];
                // WEB3AdminTMLoginPermissionStatusUnit.validate().
                l_loginPermissionStatus.validate();
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
        return new WEB3AdminTMLStopStartChgConfirmResponse(this);
    }
}
@
