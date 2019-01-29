head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLStopStartChgCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C����~�ĊJ�ύX�������N�G�X�g(WEB3AdminTMLStopStartChgCompleteRequest.java)
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
 * �i�Ǘ��ҁE���O�C����~�ĊJ�ύX�������N�G�X�g�j<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgCompleteRequest<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgCompleteRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLStopStartChgCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tml_stop_start_chg_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLStopStartChgCompleteRequest.class);
    /**
     * �i�Ïؔԍ��j<BR>
     * <BR>
     * �Ïؔԍ�<BR>
     * <BR>
     * password<BR>
     */
    public String password;

    /**
     * (���O�C�����󋵈ꗗ)
     * <BR>
     * ���O�C�����󋵈ꗗ<BR>
     */
    public WEB3AdminTMLoginPermissionStatusUnit[] loginPermissionStatusList;

    /**
     * @@roseuid 41DD356B03DA
     */
    public WEB3AdminTMLStopStartChgCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�Ïؔԍ��`�F�b�N<BR>
     * �@@�P�|�P�jthis.�Ïؔԍ� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�Ïؔԍ���null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * �Q�j���O�C�����󋵈ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���O�C�����󋵈ꗗ == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���O�C�����󋵈ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���O�C�����󋵈ꗗ.length == 0�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���O�C�����󋵈ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417<BR>
     * <BR>
     * �@@�Q�|�R�jthis.���O�C�����󋵈ꗗ�̗v�f�����ȉ��̏�����<BR>
     * �@@�@@�@@�@@�@@�J��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�Q�|�R�|�P�jthis.���O�C������.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)password check<BR>
     *   1-1)If this.password == null<BR>
     *           Throw the following error [password is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * 2)loginPermissionStatusList check<BR>
     *   2-1)If this.loginPermissionStatusList == null<BR>
     *           Throw the following error [loginPermissionStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     *   2-2)If this.loginPermissionStatusList.length == 0<BR>
     *           Throw the following error [loginPermissionStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417417417417
     * <BR>
     *   2-3)Loop for as many times as the elements of
     * this.loginPermissionStatusList<BR>
     *      2-3-1)Call this.WEB3AdminTMLoginPermissionStatusUnit.validate()<BR>
     * @@roseuid 417757A9010E
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_loginPermissionStatusListlength = 0;
        WEB3AdminTMLoginPermissionStatusUnit l_loginPermissionStatus = null;

        //Step 1-1 password = null, throw Exception
        if (this.password == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // Step 2-1 loginPermissionStatusList = nul, throw Exception.l
        if (this.loginPermissionStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01416,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else

            // Step 2-2 l_loginPermissionStatusListlength = 0, throw Exception.
            {
            l_loginPermissionStatusListlength = this.loginPermissionStatusList.length;
            if (l_loginPermissionStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01417,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // Step 2-3Loop for as many times as the elements of loginPermissionStatusList
            for (int i = 0; i < l_loginPermissionStatusListlength; i++)
            {
                l_loginPermissionStatus = this.loginPermissionStatusList[i];

                // Step 2-3-1 call validate.
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
        return new WEB3AdminTMLStopStartChgCompleteResponse(this);
    }
}
@
